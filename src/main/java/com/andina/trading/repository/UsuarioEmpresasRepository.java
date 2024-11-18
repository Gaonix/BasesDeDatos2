package com.andina.trading.repository;

import com.andina.trading.model.UsuarioEmpresa;
import com.andina.trading.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad {@link UsuarioEmpresa}. Proporciona métodos CRUD y consultas adicionales
 * para la gestión de la relación entre usuarios y empresas en la base de datos.
 *
 * <p>Permite realizar operaciones de persistencia y consultas personalizadas sobre la entidad UsuarioEmpresa.</p>
 *

 * @version 1.0
 */
public interface UsuarioEmpresasRepository extends JpaRepository<UsuarioEmpresa, Long> {

    /**
     * Encuentra todas las relaciones de UsuarioEmpresa asociadas a un usuario específico.
     *
     * @param usuario la entidad {@link Usuarios} para la que se buscan las relaciones
     * @return una lista de entidades {@link UsuarioEmpresa} asociadas al usuario
     */
    List<UsuarioEmpresa> findByUsuario(Usuarios usuario);

    /**
     * Encuentra una relación de UsuarioEmpresa específica en función del usuario y el nombre de la empresa.
     *
     * @param usuario la entidad {@link Usuarios} asociada a la relación
     * @param empresa el nombre de la empresa asociada a la relación
     * @return un {@link Optional} que contiene la relación de UsuarioEmpresa encontrada, o vacío si no existe
     */
    Optional<UsuarioEmpresa> findByUsuarioAndNombreEmpresa(Usuarios usuario, String empresa);
}
