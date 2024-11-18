package com.andina.trading.controller;

import com.andina.trading.service.SimulacionFinancieraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de pruebas de integración para el controlador {@link SimulacionController}.
 * Verifica que los endpoints relacionados con la simulación financiera funcionen correctamente.
 *
 * <p>Esta clase utiliza {@code @SpringBootTest} para cargar el contexto de la aplicación y
 * {@code @AutoConfigureMockMvc} para configurar el objeto {@link MockMvc} que simula las solicitudes HTTP.</p>
 *
 * @version 1.0

 */
@SpringBootTest
@AutoConfigureMockMvc
public class SimulacionControllerTest {

    /**
     * Objeto {@link MockMvc} inyectado para simular solicitudes HTTP y probar los endpoints del controlador.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Prueba de integración para el endpoint de proyección de precios futuros.
     * <p>Verifica que la solicitud GET al endpoint "/simulacion/proyectar" con el parámetro
     * {@code simbolo=AAPL} devuelva un estado HTTP 200 (OK).</p>
     *
     * @throws Exception si ocurre un error durante la ejecución de la solicitud
     */
    @Test
    public void testProyectarPrecioFuturo() throws Exception {
        // Realiza una solicitud GET al endpoint y verifica que la respuesta tenga un estado HTTP 200 (OK)
        mockMvc.perform(get("/simulacion/proyectar?simbolo=AAPL"))
                .andExpect(status().isOk());
    }
}
