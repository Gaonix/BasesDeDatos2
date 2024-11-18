package com.andina.trading.util;

/**
 * Enumeración que define los roles de usuario en el sistema de transacciones bursátiles.
 * Cada rol especifica el tipo de usuario y sus posibles permisos dentro de la aplicación.
 *
 * <ul>
 *   <li>{@code INVERSIONISTA}: Representa a un usuario que invierte en acciones.</li>
 *   <li>{@code ADMIN}: Representa a un usuario con permisos administrativos en el sistema.</li>
 * </ul>
 *
 * <p>Esta enumeración se utiliza para gestionar y asignar permisos y accesos
 * a diferentes funcionalidades de la aplicación.</p>
 *

 * @version 1.0
 */
public enum Rol {
    INVERSIONISTA,
    ADMIN
}
