package com.andina.trading.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.andina.trading.service.AccionService;

/**
 * Programador de tareas para la actualización automática de los precios de las acciones.
 * Esta clase utiliza un proceso programado para invocar la actualización de precios
 * en intervalos regulares, asegurando que los datos de las acciones se mantengan actualizados.
 *
 * <p>La anotación {@code @Component} indica que esta clase es un componente de Spring,
 * y {@code @Scheduled} configura la tarea programada.</p>
 *
 * <p>El método de actualización se ejecuta con un retraso inicial de 1 segundo y se repite
 * cada 60 segundos.</p>
 *

 * @version 1.0
 */
@Component
public class ActualizacionScheduler {

    private final AccionService accionService;

    /**
     * Constructor para inyectar el servicio de acciones.
     *
     * @param accionService el servicio de acciones utilizado para actualizar los precios
     */
    public ActualizacionScheduler(AccionService accionService) {
        this.accionService = accionService;
    }

    /**
     * Método programado que actualiza automáticamente los precios de las acciones.
     * Se ejecuta con un retraso inicial de 1 segundo y luego se repite cada 60 segundos.
     * Invoca el método {@link AccionService#actualizarPreciosAcciones()} para realizar la actualización.
     */
    @Scheduled(initialDelay = 1000, fixedRate = 60000) // cada 1 minuto
    public void actualizarPreciosAutomaticamente() {
        accionService.actualizarPreciosAcciones();
    }
}
