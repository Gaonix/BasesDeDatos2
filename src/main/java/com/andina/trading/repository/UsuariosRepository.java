package com.andina.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.andina.trading.model.Usuarios;
import java.util.Optional;

/**
 * Repositorio para la entidad {@link Usuarios}. Extiende de {@link JpaRepository},
 * proporcionando métodos CRUD y consultas adicionales para la gestión de usuarios en la base de datos.
 *
 * <p>Permite realizar operaciones de persistencia y consultas personalizadas sobre la entidad Usuarios.</p>
 *

 * @version 1.0
 */
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    /**
     * Encuentra un usuario en la base de datos por su correo electrónico y contraseña.
     * Este método es útil para la autenticación de usuarios.
     *
     * @param email el correo electrónico del usuario
     * @param contrasena la contraseña del usuario
     * @return un {@link Optional} que contiene el usuario encontrado, o vacío si no existe
     */
    Optional<Usuarios> findByEmailAndContrasena(String email, String contrasena);

    /**
     * Encuentra un usuario en la base de datos por su correo electrónico.
     *
     * @param email el correo electrónico del usuario
     * @return la entidad {@link Usuarios} encontrada, o null si no existe
     */
    Usuarios findByEmail(String email);
}
