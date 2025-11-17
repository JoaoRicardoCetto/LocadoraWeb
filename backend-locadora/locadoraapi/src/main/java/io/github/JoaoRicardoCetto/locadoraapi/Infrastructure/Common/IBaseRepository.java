package io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common;

import io.github.JoaoRicardoCetto.locadoraapi.model.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface IBaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {

}
