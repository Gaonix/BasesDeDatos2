package com.andina.trading.controller;

import com.andina.trading.service.SimulacionFinancieraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controlador REST para manejar las solicitudes relacionadas con la simulación financiera en el sistema Andina Trading.
 * Proporciona un endpoint para proyectar el precio futuro de una acción basada en datos históricos.
 *
 * <p>Este controlador se encarga de gestionar las solicitudes en la ruta "/simulacion" y utiliza el servicio
 * {@link SimulacionFinancieraService} para realizar cálculos y simulaciones financieras.</p>
 *
 * <p>La anotación {@code @RestController} permite que los métodos de esta clase devuelvan directamente
 * los datos en formato JSON.</p>
 *
 * @see SimulacionFinancieraService
 * @version 1.0
 */
@RestController
@RequestMapping("/simulacion")
public class SimulacionController {

    /**
     * Servicio para realizar simulaciones financieras.
     */
    private final SimulacionFinancieraService simulacionFinancieraService;

    /**
     * Constructor para inyectar el servicio de simulación financiera.
     *
     * @param simulacionFinancieraService el servicio de simulación financiera utilizado para realizar las proyecciones de precios
     */
    public SimulacionController(SimulacionFinancieraService simulacionFinancieraService) {
        this.simulacionFinancieraService = simulacionFinancieraService;
    }

    /**
     * Endpoint para obtener una proyección de precio futuro basada en datos históricos.
     *
     * <p>Este método llama al servicio {@link SimulacionFinancieraService#proyectarPrecioFuturo(List)}
     * para calcular la proyección del precio de la acción indicada por el símbolo proporcionado.</p>
     *
     * @param simbolo el símbolo de la acción a simular (por ejemplo, "AAPL" para Apple Inc.)
     * @return la proyección del precio futuro de la acción como {@link BigDecimal}
     */
    @GetMapping("/proyectar")
    public BigDecimal proyectarPrecioFuturo(@RequestParam String simbolo) {
        List<BigDecimal> datosHistoricos = simulacionFinancieraService.obtenerDatosHistoricos(simbolo);
        return simulacionFinancieraService.proyectarPrecioFuturo(datosHistoricos);
    }
}
