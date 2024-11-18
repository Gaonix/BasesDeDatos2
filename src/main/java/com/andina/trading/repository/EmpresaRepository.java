package com.andina.trading.repository;

import com.andina.trading.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la entidad {@link Empresa}. Proporciona métodos CRUD y consultas adicionales
 * para la gestión de empresas en la base de datos.
 *
 * <p>Permite realizar operaciones de persistencia y consultas personalizadas sobre la entidad Empresa.</p>
 *

 * @version 1.0
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    /**
     * Encuentra una empresa en la base de datos por su nombre.
     *
     * @param nombreEmpresa el nombre de la empresa
     * @return un {@link Optional} que contiene la empresa encontrada, o vacío si no existe
     */
    Optional<Empresa> findByNombreEmpresa(String nombreEmpresa);
}
