package com.andina.trading.controller;

import com.andina.trading.model.Usuarios;
import com.andina.trading.repository.UsuariosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Clase de pruebas de integración para el controlador {@link UsuariosController}.
 * Verifica que los endpoints relacionados con la gestión de usuarios funcionen correctamente y devuelvan
 * las vistas esperadas.
 *
 * <p>La clase utiliza {@code @SpringBootTest} para cargar el contexto de la aplicación y
 * {@code @AutoConfigureMockMvc} para configurar el objeto {@link MockMvc} que permite realizar
 * solicitudes HTTP simuladas.</p>
 *
 * @version 1.0

 */
@SpringBootTest
@AutoConfigureMockMvc
public class UsuariosControllerTest {

    /**
     * Objeto {@link MockMvc} inyectado para simular solicitudes HTTP y probar los endpoints del controlador.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Repositorio de usuarios inyectado para acceder a los datos de prueba en la base de datos.
     */
    @Autowired
    private UsuariosRepository usuariosRepository;

    /**
     * Prueba de integración para el método que lista los usuarios.
     * <p>Verifica que una solicitud GET al endpoint "/listausuarios" devuelva un estado HTTP 200 (OK)
     * y que la vista retornada sea "listausuarios".</p>
     *
     * @throws Exception si ocurre un error durante la ejecución de la solicitud
     */
    @Test
    public void testListarUsuarios() throws Exception {
        // Realiza una solicitud GET al endpoint "/listausuarios" y verifica que la respuesta sea HTTP 200 y la vista correcta
        mockMvc.perform(get("/listausuarios")
                        .contentType(MediaType.APPLICATION_JSON))  // Configura el tipo de contenido de la solicitud
                .andExpect(status().isOk())  // Verifica que el estado de la respuesta sea 200 (OK)
                .andExpect(view().name("listausuarios"));  // Verifica que la vista retornada se llame "listausuarios"
    }
}
