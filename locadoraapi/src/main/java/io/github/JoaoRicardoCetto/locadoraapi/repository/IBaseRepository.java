package io.github.JoaoRicardoCetto.locadoraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface IBaseRepository<T, UUID> extends JpaRepository<T, UUID> {

}
