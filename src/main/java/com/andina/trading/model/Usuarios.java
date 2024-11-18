package com.andina.trading.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import com.andina.trading.util.Rol;

/**
 * Entidad que representa un usuario en el sistema de transacciones bursátiles.
 * Almacena información básica sobre el usuario, como su nombre, correo electrónico,
 * contraseña, rol, autenticación de doble factor y balance.
 *
 * <p>Esta clase está mapeada a la tabla "usuarios" en la base de datos.</p>
 *
 
 * @version 1.0
 */
@Entity
@Table(name = "usuarios")
public class Usuarios {

	/**
	 * Identificador único del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	/**
	 * Nombre del usuario.
	 */
	private String nombre;

	/**
	 * Correo electrónico del usuario.
	 */
	private String email;

	/**
	 * Contraseña del usuario, almacenada de forma cifrada.
	 */
	private String contrasena;

	/**
	 * Rol del usuario en el sistema.
	 */
	@Enumerated(EnumType.STRING)
	private Rol rol;

	/**
	 * Indica si el usuario tiene activada la autenticación de doble factor.
	 */
	private Boolean autenticacionDobleFactor;

	/**
	 * Fecha y hora de creación del usuario en el sistema.
	 * Se establece automáticamente en el momento de la creación.
	 */
	private Timestamp creadoEn;

	/**
	 * Balance financiero del usuario, representado en formato decimal.
	 */
	private BigDecimal balance;

	/**
	 * Constructor por defecto.
	 */
	public Usuarios() {}

	/**
	 * Constructor para crear un usuario con los detalles proporcionados.
	 *
	 * @param nombre el nombre del usuario
	 * @param email el correo electrónico del usuario
	 * @param contrasena la contraseña del usuario
	 * @param rol el rol del usuario en el sistema
	 * @param autenticacionDobleFactor si el usuario tiene activada la autenticación de doble factor
	 * @param balance el balance financiero del usuario
	 */
	public Usuarios(String nombre, String email, String contrasena, Rol rol, Boolean autenticacionDobleFactor, BigDecimal balance) {
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
		this.rol = rol;
		this.autenticacionDobleFactor = autenticacionDobleFactor;
		this.balance = balance;
		this.creadoEn = new Timestamp(System.currentTimeMillis());
	}


	/**
	 * Obtiene el identificador del usuario.
	 *
	 * @return el identificador del usuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Establece el identificador del usuario.
	 *
	 * @param idUsuario el nuevo identificador del usuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Obtiene el nombre del usuario.
	 *
	 * @return el nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 *
	 * @param nombre el nuevo nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 *
	 * @return el correo electrónico del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 *
	 * @param email el nuevo correo electrónico del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 *
	 * @return la contraseña del usuario
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param contrasena la nueva contraseña del usuario
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Obtiene el rol del usuario en el sistema.
	 *
	 * @return el rol del usuario
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * Establece el rol del usuario en el sistema.
	 *
	 * @param rol el nuevo rol del usuario
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * Verifica si el usuario tiene activada la autenticación de doble factor.
	 *
	 * @return true si la autenticación de doble factor está activada, false en caso contrario
	 */
	public Boolean getAutenticacionDobleFactor() {
		return autenticacionDobleFactor;
	}

	/**
	 * Establece si el usuario tiene activada la autenticación de doble factor.
	 *
	 * @param autenticacionDobleFactor true para activar la autenticación de doble factor, false para desactivarla
	 */
	public void setAutenticacionDobleFactor(Boolean autenticacionDobleFactor) {
		this.autenticacionDobleFactor = autenticacionDobleFactor;
	}

	/**
	 * Obtiene la fecha y hora de creación del usuario.
	 *
	 * @return la fecha de creación del usuario
	 */
	public Timestamp getCreadoEn() {
		return creadoEn;
	}

	/**
	 * Establece la fecha y hora de creación del usuario.
	 *
	 * @param creadoEn la nueva fecha de creación del usuario
	 */
	public void setCreadoEn(Timestamp creadoEn) {
		this.creadoEn = creadoEn;
	}

	/**
	 * Obtiene el balance financiero del usuario.
	 *
	 * @return el balance financiero del usuario
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Establece el balance financiero del usuario.
	 *
	 * @param balance el nuevo balance financiero del usuario
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
