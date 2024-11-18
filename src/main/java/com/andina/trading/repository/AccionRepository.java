package com.andina.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.andina.trading.model.Accion;
import java.util.Optional;

/**
 * Repositorio para la entidad {@link Accion}. Extiende de {@link JpaRepository},
 * proporcionando métodos CRUD y consultas adicionales para la gestión de acciones en la base de datos.
 *
 * <p>Permite realizar operaciones de persistencia y consultas personalizadas sobre la entidad Accion.</p>
 *
 * @version 1.0
 */
public interface AccionRepository extends JpaRepository<Accion, Long> {

    /**
     * Encuentra una acción en la base de datos por el nombre de la empresa.
     *
     * @param nombreEmpresa el nombre de la empresa asociada a la acción
     * @return un {@link Optional} que contiene la acción encontrada, o vacío si no existe
     */
    Optional<Accion> findByNombreEmpresa(String nombreEmpresa);

    /**
     * Encuentra la acción más reciente de una empresa específica, ordenada por la fecha de actualización.
     *
     * @param nombreEmpresa el nombre de la empresa asociada a la acción
     * @return un {@link Optional} que contiene la acción más reciente, o vacío si no existe
     */
    Optional<Accion> findTopByNombreEmpresaOrderByFechaActualizacionDesc(String nombreEmpresa);
}
