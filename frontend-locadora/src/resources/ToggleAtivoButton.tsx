import React from "react";
import { Button, useRecordContext, useNotify, useRefresh } from "react-admin";
import CheckIcon from "@mui/icons-material/Check";
import CloseIcon from "@mui/icons-material/Close";

const API_BASE = import.meta.env.VITE_API_URL || "http://localhost:8080";

interface ToggleAtivoButtonProps {
  /** Força o resource/endpoint a usar (ex: 'socios' ou 'dependentes') */
  resourceOverride?: string;
  /** Força o nome do campo booleano no record (ex: 'estaAtivo' ou 'estahAtivo') */
  activeFieldOverride?: string;
  /** HTTP method: PATCH or PUT */
  method?: "PATCH" | "PUT";
}

const ToggleAtivoButton: React.FC<ToggleAtivoButtonProps> = ({
  resourceOverride,
  activeFieldOverride,
  method = "PATCH",
}) => {
  const record = useRecordContext<Record<string, any>>();
  const notify = useNotify();
  const refresh = useRefresh();

  if (!record) return null;

  // Detecta campo ativo (prioridade: prop override > propriedades conhecidas)
  const detectField = (): string => {
    if (activeFieldOverride && Object.prototype.hasOwnProperty.call(record, activeFieldOverride))
      return activeFieldOverride;
    for (const f of ["estaAtivo", "estahAtivo", "ativo", "isActive"]) {
      if (Object.prototype.hasOwnProperty.call(record, f)) return f;
    }
    return "estaAtivo"; // fallback
  };

  const activeField = detectField();
  const isActive: boolean = Boolean(record[activeField]);
  const id = record.id;

  // resource: prefira override, senão tente inferir da rota (se quiser)
// agora usamos resourceOverride obrigatório no uso prático para evitar problemas de contexto
  const resource = resourceOverride ?? "socios";

  const url = `${API_BASE}/${resource}/estaAtivo/${id}`;
  // Se seu backend usa 'estahAtivo' no corpo, envie exatamente activeField detectado
  const body = { [activeField]: !isActive };

  const handleClick = async () => {
    console.log("[ToggleAtivo] clicking", { resource, id, activeField, isActive, url, body, method });

    try {
      const res = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
        credentials: "include",
      });

      console.log("[ToggleAtivo] response status", res.status);

      const text = await res.text();
      let parsed;
      try { parsed = text ? JSON.parse(text) : undefined; } catch (e) { parsed = text; }

      console.log("[ToggleAtivo] response body", parsed);

      if (!res.ok) {
        throw new Error(parsed || `HTTP ${res.status}`);
      }

      notify("Status atualizado!", { type: "success" });
      refresh();
    } catch (err: any) {
      console.error("[ToggleAtivo] erro", err);
      notify("Erro ao atualizar: " + (err.message ?? ""), { type: "error" });
    }
  };

  return (
    <Button
      label={isActive ? "Desativar" : "Ativar"}
      onClick={handleClick}
      startIcon={isActive ? <CloseIcon /> : <CheckIcon />}
      variant="contained"
      sx={{
        backgroundColor: isActive ? "#d32f2f" : "#2e7d32",
        color: "#fff",
        "&:hover": { backgroundColor: isActive ? "#b71c1c" : "#1b5e20" },
      }}
    />
  );
};

export default ToggleAtivoButton;
