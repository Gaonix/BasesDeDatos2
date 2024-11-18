package com.andina.trading.service;

import com.andina.trading.model.Accion;
import com.andina.trading.repository.AccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Clase de pruebas unitarias para el servicio {@link AccionService}.
 * Verifica la funcionalidad de los métodos relacionados con la obtención y gestión de acciones.
 *
 * <p>Las pruebas utilizan Mockito para simular el comportamiento de los componentes y garantizar
 * que la lógica de negocio de {@link AccionService} funcione correctamente.</p>
 *
 * @version 1.0

 */
public class AccionServiceTest {

    /**
     * Repositorio de acciones simulado para las pruebas.
     */
    private AccionRepository accionRepository;

    /**
     * Servicio de empresas simulado para las pruebas.
     */
    private EmpresaService empresaService;

    /**
     * Instancia de {@link AccionService} que se probará.
     */
    private AccionService accionService;

    /**
     * Configura los componentes simulados y la instancia de {@link AccionService} antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        accionRepository = mock(AccionRepository.class);
        empresaService = mock(EmpresaService.class);
        accionService = new AccionService(accionRepository, empresaService);
    }

    /**
     * Prueba el método {@link AccionService#obtenerAcciones()} para verificar que devuelve la lista
     * de acciones correctamente desde el repositorio.
     */
    @Test
    public void testObtenerAcciones() {
        // Crea datos de prueba
        Accion accion1 = new Accion();
        accion1.setNombreEmpresa("AAPL");
        accion1.setPrecioActual(BigDecimal.valueOf(150.00));
        accion1.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

        Accion accion2 = new Accion();
        accion2.setNombreEmpresa("GOOGL");
        accion2.setPrecioActual(BigDecimal.valueOf(2800.00));
        accion2.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

        // Configura el comportamiento del repositorio simulado
        when(accionRepository.findAll()).thenReturn(Arrays.asList(accion1, accion2));

        // Llama al método a probar y realiza las comprobaciones
        List<Accion> acciones = accionService.obtenerAcciones();
        assertEquals(2, acciones.size(), "El número de acciones devuelto debe ser 2");
        assertEquals("AAPL", acciones.get(0).getNombreEmpresa(), "La primera acción debe ser AAPL");
        assertEquals("GOOGL", acciones.get(1).getNombreEmpresa(), "La segunda acción debe ser GOOGL");
    }
}
