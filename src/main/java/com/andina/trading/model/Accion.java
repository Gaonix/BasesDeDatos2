package com.andina.trading.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Representa una entidad de acción en el sistema de transacciones bursátiles.
 * Cada acción contiene información sobre la empresa asociada, sus precios históricos y actuales,
 * y la fecha de última actualización de los valores.
 *
 * <p>Esta clase está mapeada a la tabla "acciones" en la base de datos.</p>
 *

 * @version 1.0
 */
@Entity
@Table(name = "acciones")
public class Accion {

    /**
     * Identificador único de la acción.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accion")
    private Long idAccion;

    /**
     * Nombre de la empresa asociada a la acción.
     */
    private String nombreEmpresa;

    /**
     * Precio de apertura de la acción.
     */
    private BigDecimal precioApertura;

    /**
     * Precio más alto registrado para la acción en un período determinado.
     */
    private BigDecimal precioAlto;

    /**
     * Precio más bajo registrado para la acción en un período determinado.
     */
    private BigDecimal precioBajo;

    /**
     * Precio actual de la acción.
     */
    private BigDecimal precioActual;

    /**
     * Fecha y hora de la última actualización de los valores de la acción.
     */
    private Timestamp fechaActualizacion;

    /**
     * Relación Many-to-One con la entidad {@link Empresa}.
     * Cada acción está asociada a una única empresa.
     */
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;


    /**
     * Obtiene el identificador de la acción.
     *
     * @return el identificador de la acción
     */
    public Long getIdAccion() {
        return idAccion;
    }

    /**
     * Establece el identificador de la acción.
     *
     * @param idAccion el nuevo identificador de la acción
     */
    public void setIdAccion(Long idAccion) {
        this.idAccion = idAccion;
    }

    /**
     * Obtiene el nombre de la empresa asociada a la acción.
     *
     * @return el nombre de la empresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Establece el nombre de la empresa asociada a la acción.
     *
     * @param nombreEmpresa el nuevo nombre de la empresa
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Obtiene el precio de apertura de la acción.
     *
     * @return el precio de apertura
     */
    public BigDecimal getPrecioApertura() {
        return precioApertura;
    }

    /**
     * Establece el precio de apertura de la acción.
     *
     * @param precioApertura el nuevo precio de apertura
     */
    public void setPrecioApertura(BigDecimal precioApertura) {
        this.precioApertura = precioApertura;
    }

    /**
     * Obtiene el precio más alto registrado para la acción.
     *
     * @return el precio más alto
     */
    public BigDecimal getPrecioAlto() {
        return precioAlto;
    }

    /**
     * Establece el precio más alto registrado para la acción.
     *
     * @param precioAlto el nuevo precio más alto
     */
    public void setPrecioAlto(BigDecimal precioAlto) {
        this.precioAlto = precioAlto;
    }

    /**
     * Obtiene el precio más bajo registrado para la acción.
     *
     * @return el precio más bajo
     */
    public BigDecimal getPrecioBajo() {
        return precioBajo;
    }

    /**
     * Establece el precio más bajo registrado para la acción.
     *
     * @param precioBajo el nuevo precio más bajo
     */
    public void setPrecioBajo(BigDecimal precioBajo) {
        this.precioBajo = precioBajo;
    }

    /**
     * Obtiene el precio actual de la acción.
     *
     * @return el precio actual
     */
    public BigDecimal getPrecioActual() {
        return precioActual;
    }

    /**
     * Establece el precio actual de la acción.
     *
     * @param precioActual el nuevo precio actual
     */
    public void setPrecioActual(BigDecimal precioActual) {
        this.precioActual = precioActual;
    }

    /**
     * Obtiene la fecha y hora de la última actualización de los valores de la acción.
     *
     * @return la fecha y hora de la última actualización
     */
    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * Establece la fecha y hora de la última actualización de los valores de la acción.
     *
     * @param fechaActualizacion la nueva fecha de actualización
     */
    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * Obtiene la empresa asociada a la acción.
     *
     * @return la empresa asociada
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa asociada a la acción.
     *
     * @param empresa la nueva empresa asociada
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
