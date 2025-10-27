package io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface IBaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {

}
