package com.andina.trading.model;

import jakarta.persistence.*;

/**
 * Entidad que representa la relación entre un usuario y una empresa en el sistema de transacciones bursátiles.
 * Almacena la información sobre las empresas asociadas a los usuarios, incluyendo la cantidad de acciones
 * que poseen y otros detalles relevantes.
 *
 * <p>Esta clase está mapeada a la tabla "usuario_empresas" en la base de datos.</p>
 *
 
 * @version 1.0
 */
@Entity
@Table(name = "usuario_empresas")
public class UsuarioEmpresa {

    /**
     * Identificador único de la relación usuario-empresa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario asociado a la relación.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;

    /**
     * Empresa asociada a la relación.
     */
    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    /**
     * Cantidad de acciones que el usuario posee de la empresa.
     */
    private int cantidad;

    /**
     * Nombre de la empresa asociada, utilizado para facilitar el acceso a la información.
     */
    private String nombreEmpresa;

    /**
     * Nombre del usuario, utilizado para almacenar el nombre del usuario asociado a la relación.
     */
    private String nombreUsuario; // Nuevo campo para almacenar el nombre del usuario


    /**
     * Obtiene el identificador de la relación usuario-empresa.
     *
     * @return el identificador de la relación
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la relación usuario-empresa.
     *
     * @param id el nuevo identificador de la relación
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario asociado a la relación.
     *
     * @return el usuario asociado
     */
    public Usuarios getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado a la relación.
     *
     * @param usuario el nuevo usuario asociado
     */
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la empresa asociada a la relación.
     *
     * @return la empresa asociada
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa asociada a la relación.
     *
     * @param empresa la nueva empresa asociada
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene la cantidad de acciones que el usuario posee de la empresa.
     *
     * @return la cantidad de acciones
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de acciones que el usuario posee de la empresa.
     *
     * @param cantidad la nueva cantidad de acciones
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el nombre de la empresa asociada.
     *
     * @return el nombre de la empresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Establece el nombre de la empresa asociada.
     *
     * @param nombreEmpresa el nuevo nombre de la empresa
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Obtiene el nombre del usuario asociado a la relación.
     *
     * @return el nombre del usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del usuario asociado a la relación.
     *
     * @param nombreUsuario el nuevo nombre del usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
