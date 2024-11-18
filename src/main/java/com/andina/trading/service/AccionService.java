package com.andina.trading.service;

import com.andina.trading.model.Accion;
import com.andina.trading.model.Empresa;
import com.andina.trading.repository.AccionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Servicio que gestiona la lógica de negocio relacionada con las acciones en el sistema de transacciones bursátiles.
 * Este servicio permite actualizar los precios de las acciones utilizando la API de Yahoo Finance
 * y gestionar el almacenamiento de las acciones en la base de datos.
 *
 * <p>La clase se encarga de obtener datos de precios de acciones en tiempo real y persistirlos en la base de datos.</p>
 *
 
 * @version 1.0
 */
@Service
public class AccionService {

    private final AccionRepository accionRepository;
    private final EmpresaService empresaService;

    /**
     * Clave de la API de RapidAPI para autenticar las solicitudes.
     */
    @Value("${rapidapi.key}")
    private String rapidApiKey;

    /**
     * Lista de símbolos de las empresas cuyas acciones se deben actualizar.
     */
    private final List<String> simbolosEmpresas = Arrays.asList("AAPL", "MSFT", "GOOGL", "AMZN", "TSLA", "META", "NFLX", "NVDA");

    /**
     * Constructor para inyectar los repositorios y servicios necesarios.
     *
     * @param accionRepository el repositorio de acciones
     * @param empresaService el servicio de gestión de empresas
     */
    public AccionService(AccionRepository accionRepository, EmpresaService empresaService) {
        this.accionRepository = accionRepository;
        this.empresaService = empresaService;
    }

    /**
     * Actualiza los precios de las acciones mediante la API de Yahoo Finance.
     * Para cada símbolo de empresa en la lista de símbolos, realiza una solicitud a la API,
     * obtiene el precio actual y actualiza la base de datos con una nueva instancia de {@link Accion}.
     */
    public void actualizarPreciosAcciones() {
        RestTemplate restTemplate = new RestTemplate();

        for (String simbolo : simbolosEmpresas) {
            String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols=" + simbolo;
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
            headers.set("X-RapidAPI-Key", rapidApiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                JSONObject jsonObject = new JSONObject(response.getBody());
                JSONObject quote = jsonObject.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0);

                BigDecimal precioActual = BigDecimal.valueOf(quote.getDouble("regularMarketPrice"));
                Timestamp fechaActualizacion = new Timestamp(System.currentTimeMillis());

                Empresa empresa = empresaService.obtenerOcrearEmpresa(simbolo, "US", "Estable");

                Accion nuevaAccion = new Accion();
                nuevaAccion.setNombreEmpresa(simbolo);
                nuevaAccion.setPrecioActual(precioActual);
                nuevaAccion.setFechaActualizacion(fechaActualizacion);
                nuevaAccion.setEmpresa(empresa);

                accionRepository.save(nuevaAccion);

            } catch (Exception e) {
                System.out.println("Error al actualizar el precio para " + simbolo + ": " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene todas las acciones almacenadas en la base de datos.
     *
     * @return una lista de entidades {@link Accion}
     */
    public List<Accion> obtenerAcciones() {
        return accionRepository.findAll();
    }
}
