package com.andina.trading.service;

import com.andina.trading.model.Empresa;
import com.andina.trading.repository.EmpresaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Clase de pruebas unitarias para el servicio {@link EmpresaService}.
 * Verifica la funcionalidad de los métodos relacionados con la gestión de empresas,
 * incluyendo la obtención y creación de nuevas empresas.
 *
 * <p>Las pruebas utilizan Mockito para simular el comportamiento del repositorio y
 * garantizar que la lógica de negocio de {@link EmpresaService} funcione correctamente.</p>
 *
 * @version 1.0
 
 */
public class EmpresaServiceTest {

    /**
     * Repositorio de empresas simulado para las pruebas.
     */
    private EmpresaRepository empresaRepository;

    /**
     * Instancia de {@link EmpresaService} que se probará.
     */
    private EmpresaService empresaService;

    /**
     * Configura los componentes simulados y la instancia de {@link EmpresaService} antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        empresaRepository = mock(EmpresaRepository.class);
        empresaService = new EmpresaService(empresaRepository);
    }

    /**
     * Prueba el método {@link EmpresaService#obtenerOcrearEmpresa(String, String, String)} para
     * verificar que se devuelve una empresa existente o se crea una nueva si no existe.
     */
    @Test
    public void testObtenerOcrearEmpresa() {
        // Datos de prueba
        String nombreEmpresa = "AAPL";
        String pais = "US";
        String situacionEconomica = "Estable";

        // Configura la respuesta del repositorio simulado para devolver una empresa vacía
        when(empresaRepository.findByNombreEmpresa(nombreEmpresa)).thenReturn(Optional.empty());

        // Configura la respuesta del repositorio simulado al guardar una empresa
        Empresa empresa = new Empresa();
        empresa.setNombreEmpresa(nombreEmpresa);
        empresa.setPais(pais);
        empresa.setSituacionEconomica(situacionEconomica);
        when(empresaRepository.save(Mockito.any(Empresa.class))).thenReturn(empresa);

        // Llama al método a probar
        Empresa resultado = empresaService.obtenerOcrearEmpresa(nombreEmpresa, pais, situacionEconomica);

        // Verifica los resultados
        assertEquals(nombreEmpresa, resultado.getNombreEmpresa(), "El nombre de la empresa debe coincidir");
        assertEquals(pais, resultado.getPais(), "El país de la empresa debe coincidir");
    }
}
