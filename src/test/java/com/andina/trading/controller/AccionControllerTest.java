package com.andina.trading.controller;

import com.andina.trading.service.AccionService;
import com.andina.trading.service.EmpresaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Clase de pruebas de integración para el controlador {@link AccionController}.
 * Verifica que el endpoint de visualización de acciones funcione correctamente y retorne la vista esperada.
 *
 * <p>La clase utiliza {@code @SpringBootTest} para cargar el contexto completo de la aplicación y
 * {@code @AutoConfigureMockMvc} para configurar el objeto {@link MockMvc} que permite simular solicitudes HTTP.</p>
 *
 * @version 1.0

 */
@SpringBootTest
@AutoConfigureMockMvc
public class AccionControllerTest {

    /**
     * Objeto {@link MockMvc} inyectado para simular solicitudes HTTP y probar los endpoints del controlador.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Prueba de integración para el método que muestra la lista de acciones.
     * <p>Verifica que una solicitud GET al endpoint "/acciones" devuelva un estado HTTP 200 (OK)
     * y que la vista retornada sea la plantilla "acciones".</p>
     *
     * @throws Exception si ocurre un error durante la ejecución de la solicitud
     */
    @Test
    public void testMostrarAcciones() throws Exception {
        // Realiza una solicitud GET al endpoint "/acciones" y verifica que la respuesta sea HTTP 200 y la vista correcta
        mockMvc.perform(get("/acciones"))
                .andExpect(status().isOk())  // Verifica que el estado de la respuesta sea 200 (OK)
                .andExpect(view().name("acciones"));  // Verifica que la vista retornada se llame "acciones"
    }
}
