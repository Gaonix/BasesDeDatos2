package com.andina.trading.service;

import com.andina.trading.repository.AccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Clase de pruebas unitarias para el servicio {@link SimulacionFinancieraService}.
 * Verifica la funcionalidad de los métodos de simulación financiera, incluyendo
 * la proyección de precios futuros basada en datos históricos.
 *
 * <p>Las pruebas utilizan Mockito para simular el comportamiento del repositorio de acciones,
 * garantizando que la lógica de negocio de {@link SimulacionFinancieraService} funcione correctamente.</p>
 *
 * @version 1.0
 
 */
public class SimulacionFinancieraServiceTest {

    /**
     * Repositorio de acciones simulado para las pruebas.
     */
    private AccionRepository accionRepository;

    /**
     * Instancia de {@link SimulacionFinancieraService} que se probará.
     */
    private SimulacionFinancieraService simulacionFinancieraService;

    /**
     * Configura los componentes simulados y la instancia de {@link SimulacionFinancieraService}
     * antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        accionRepository = mock(AccionRepository.class);
        simulacionFinancieraService = new SimulacionFinancieraService(accionRepository);
    }

    /**
     * Prueba el método {@link SimulacionFinancieraService#proyectarPrecioFuturo(List)} para verificar
     * que devuelve el precio proyectado correcto basado en los datos históricos proporcionados.
     */
    @Test
    public void testProyectarPrecioFuturo() {
        // Datos de prueba: lista de precios históricos
        List<BigDecimal> preciosHistoricos = Arrays.asList(
                BigDecimal.valueOf(100.00),
                BigDecimal.valueOf(110.00),
                BigDecimal.valueOf(120.00)
        );

        // Llama al método a probar
        BigDecimal precioProyectado = simulacionFinancieraService.proyectarPrecioFuturo(preciosHistoricos);

        // Verifica el resultado esperado
        assertEquals(BigDecimal.valueOf(110.00).setScale(2), precioProyectado.setScale(2),
                "El precio proyectado debe ser el promedio de los precios históricos");
    }
}
