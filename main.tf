terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
  required_version = ">= 1.2.0"
}

provider "aws" {
  region = var.region
}

#############################
# Variables (customize)
#############################
variable "region" {
  description = "AWS region to deploy"
  type        = string
  default     = "us-east-1"
}

variable "cluster_name" {
  description = "ECS cluster name"
  type        = string
  default     = "locadora-cluster"
}

# IMPORTANT: replace with the actual LabRole ARN from your AWS Academy environment.
# Example: "arn:aws:iam::123456789012:role/LabRole"
variable "execution_role_arn" {
  description = "Execution role ARN (LabRole) required by task definition"
  type        = string
  default     = "arn:aws:iam::123456789012:role/LabRole"
}

# Images taken from your docker-compose file; change if you pushed to ECR or different tags
variable "frontend_image" {
  description = "Frontend image (Docker Hub or ECR)"
  type        = string
  default     = "joaomcetto/locadora-frontend:1.0"
}

variable "backend_image" {
  description = "Backend image (Docker Hub or ECR)"
  type        = string
  default     = "joaomcetto/locadora-backend:1.0"
}

# If you have your own postgres image specify it here; otherwise a public postgres tag is used.
variable "postgres_image" {
  description = "Postgres image to use for DB container (replace if you use RDS instead)"
  type        = string
  default     = "postgres:14-alpine"
}

# pgAdmin image
variable "pgadmin_image" {
  description = "pgAdmin image"
  type        = string
  default     = "dpage/pgadmin4:6.20"
}

# CPU / memory for the task and per-container reservations
variable "task_cpu" {
  description = "ECS Task CPU (256 = 0.25 vCPU)"
  type        = number
  default     = 1024
}

variable "task_memory" {
  description = "ECS Task memory (in MiB)"
  type        = number
  default     = 2048
}

#############################
# Discover existing VPC and subnets (do NOT hardcode IDs)
#############################
# Use the default VPC (common in AWS Academy learner labs).
data "aws_vpc" "default" {
  default = true
}

data "aws_subnets" "available" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}

#############################
# ECS Cluster
#############################
resource "aws_ecs_cluster" "cluster" {
  name = var.cluster_name
}

#############################
# Security Group (allow inbound to required ports)
#############################
resource "aws_security_group" "fargate_sg" {
  name        = "${var.cluster_name}-sg"
  description = "Allow inbound for application containers"
  vpc_id      = data.aws_vpc.default.id

  # Ingress: open app/pg ports. Adjust as needed to match your compose ports.
  ingress {
    description      = "Allow frontend (React) 3000"
    from_port        = 3000
    to_port          = 3000
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  ingress {
    description      = "Allow backend (Spring) 8080 (adjust if different)"
    from_port        = 8080
    to_port          = 8080
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  ingress {
    description = "Allow Postgres port 5432 (if you need external access)"
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # In production restrict to internal CIDRs only
  }

  ingress {
    description = "Allow pgAdmin (web UI) - container port 80 (adjust if mapped differently)"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Egress: allow all outbound (Fargate needs outbound to pull images)
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.cluster_name}-sg"
  }
}

#############################
# Task Definition: convert docker-compose -> container_definitions via jsonencode
# We place all compose containers in a single task for simplicity.
#############################
locals {
  container_definitions = jsonencode([
    {
      name  = "react"
      image = var.frontend_image
      essential = true
      portMappings = [
        {
          containerPort = 3000
          protocol      = "tcp"
        }
      ]
      # If your react app needs env vars, add them here as list of objects:
      # environment = [{ name = "API_URL", value = "http://spring:8080" }]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = "/ecs/locadora"
          "awslogs-region"        = var.region
          "awslogs-stream-prefix" = "react"
        }
      }
    },

    {
      name  = "spring"
      image = var.backend_image
      essential = true
      portMappings = [
        {
          containerPort = 8080
          protocol      = "tcp"
        }
      ]
      # Example: if your backend needs environment variables to connect to postgres:
      environment = [
        { name = "SPRING_DATASOURCE_URL",  value = "jdbc:postgresql://localhost:5432/locadora" },
        { name = "SPRING_DATASOURCE_USERNAME", value = "postgres" },
        { name = "SPRING_DATASOURCE_PASSWORD", value = "postgres" }
      ]
      dependsOn = [
        { containerName = "db-postgres", condition = "START" }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = "/ecs/locadora"
          "awslogs-region"        = var.region
          "awslogs-stream-prefix" = "spring"
        }
      }
    },

    {
      name  = "db-postgres"
      image = var.postgres_image
      essential = true
      portMappings = [
        {
          containerPort = 5432
          protocol      = "tcp"
        }
      ]
      environment = [
        { name = "POSTGRES_DB", value = "locadora" },
        { name = "POSTGRES_USER", value = "postgres" },
        { name = "POSTGRES_PASSWORD", value = "postgres" }
      ]
      # NOTE: Fargate does NOT support host volumes. For persistence, add EFS and mountPoint definitions.
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = "/ecs/locadora"
          "awslogs-region"        = var.region
          "awslogs-stream-prefix" = "postgres"
        }
      }
    },

    {
      name  = "pgadmin"
      image = var.pgadmin_image
      essential = false
      portMappings = [
        {
          containerPort = 80
          protocol      = "tcp"
        }
      ]
      environment = [
        { name = "PGADMIN_DEFAULT_EMAIL", value = "admin@locadora.local" },
        { name = "PGADMIN_DEFAULT_PASSWORD", value = "admin" }
      ]
      dependsOn = [
        { containerName = "db-postgres", condition = "START" }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = "/ecs/locadora"
          "awslogs-region"        = var.region
          "awslogs-stream-prefix" = "pgadmin"
        }
      }
    }
  ])
}

resource "aws_ecs_task_definition" "locadora_task" {
  family                   = "locadora-task"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = tostring(var.task_cpu)
  memory                   = tostring(var.task_memory)
  execution_role_arn       = var.execution_role_arn
  # task_role_arn can be set if your containers need AWS permissions (e.g., S3). Omitted for brevity.

  container_definitions = local.container_definitions

  # If you need to add volumes (e.g., EFS) define below and refer inside container mountPoints.
  # volume { name = "efs-volume" ... }
}

#############################
# ECS Service (one service that runs the task)
#############################
resource "aws_ecs_service" "locadora_service" {
  name            = "locadora-service"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.locadora_task.arn
  launch_type     = "FARGATE"
  desired_count   = 1

  network_configuration {
    subnets         = data.aws_subnets.available.ids
    security_groups = [aws_security_group.fargate_sg.id]
    assign_public_ip = true
  }

  # Without a load balancer the service will be reachable via public IP assigned to the ENI.
  # In production prefer an ALB in front of the service and split frontend/backend services.
  depends_on = [
    aws_ecs_task_definition.locadora_task
  ]
}

#############################
# CloudWatch Log Group for all containers (optional)
#############################
resource "aws_cloudwatch_log_group" "locadora" {
  name              = "/ecs/locadora"
  retention_in_days = 7
}

output "cluster_id" {
  value = aws_ecs_cluster.cluster.id
}

output "service_name" {
  value = aws_ecs_service.locadora_service.name
}

output "task_definition" {
  value = aws_ecs_task_definition.locadora_task.arn
}
