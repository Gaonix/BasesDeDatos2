package com.andina.trading.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

/**
 * Entidad que representa una empresa participante en el sistema de transacciones bursátiles.
 * Almacena información básica sobre la empresa, como su nombre, país de origen y situación económica.
 *
 * <p>Esta clase está mapeada a la tabla "empresas" en la base de datos.</p>
 *

 * @version 1.0
 */
@Entity
@Table(name = "empresas")
public class Empresa {

    /**
     * Identificador único de la empresa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private int idEmpresa;

    /**
     * Nombre de la empresa. Debe ser único.
     */
    private String nombreEmpresa;

    /**
     * País de origen de la empresa.
     */
    private String pais;

    /**
     * Situación económica de la empresa.
     */
    private String situacionEconomica;

    /**
     * Fecha y hora de creación de la empresa en el sistema.
     * Esta fecha se establece automáticamente en el momento de la creación.
     */
    private Timestamp creadoEn = new Timestamp(System.currentTimeMillis());


    /**
     * Obtiene el identificador de la empresa.
     *
     * @return el identificador de la empresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Establece el identificador de la empresa.
     *
     * @param idEmpresa el nuevo identificador de la empresa
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * Obtiene el nombre de la empresa.
     *
     * @return el nombre de la empresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Establece el nombre de la empresa.
     *
     * @param nombreEmpresa el nuevo nombre de la empresa
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Obtiene el país de origen de la empresa.
     *
     * @return el país de origen de la empresa
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país de origen de la empresa.
     *
     * @param pais el nuevo país de origen de la empresa
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtiene la situación económica de la empresa.
     *
     * @return la situación económica de la empresa
     */
    public String getSituacionEconomica() {
        return situacionEconomica;
    }

    /**
     * Establece la situación económica de la empresa.
     *
     * @param situacionEconomica la nueva situación económica de la empresa
     */
    public void setSituacionEconomica(String situacionEconomica) {
        this.situacionEconomica = situacionEconomica;
    }

    /**
     * Obtiene la fecha y hora de creación de la empresa en el sistema.
     *
     * @return la fecha de creación de la empresa
     */
    public Timestamp getCreadoEn() {
        return creadoEn;
    }

    /**
     * Establece la fecha y hora de creación de la empresa en el sistema.
     *
     * @param creadoEn la nueva fecha de creación de la empresa
     */
    public void setCreadoEn(Timestamp creadoEn) {
        this.creadoEn = creadoEn;
    }
}
