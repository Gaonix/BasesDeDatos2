package com.andina.trading.service;

import com.andina.trading.model.Accion;
import com.andina.trading.repository.AccionRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio para gestionar simulaciones financieras de precios de acciones.
 * Permite obtener datos históricos de precios y proyectar valores futuros
 * mediante técnicas de análisis simple, como la media móvil.
 *
 * <p>
 * Este servicio interactúa con la API de Yahoo Finance para obtener datos de
 * mercado y realizar simulaciones de proyección de precios futuros de acciones.
 * </p>
 *
 * @author Danniel Parra, Gabriel Mera
 * @version 1.0
 */
@Service
public class SimulacionFinancieraService {

	private final AccionRepository accionRepository;

	/**
	 * Clave de la API de RapidAPI utilizada para autenticar las solicitudes HTTP.
	 */
	@Value("${rapidapi.key}")
	private String rapidApiKey;

	/**
	 * Constructor para inyectar el repositorio de acciones.
	 *
	 * @param accionRepository el repositorio que maneja la persistencia de acciones
	 */
	public SimulacionFinancieraService(AccionRepository accionRepository) {
		this.accionRepository = accionRepository;
	}

	/**
	 * Obtiene los precios históricos de una acción específica usando la API de
	 * Yahoo Finance.
	 * <p>
	 * Realiza una solicitud HTTP a la API de Yahoo Finance para obtener los precios
	 * de mercado de una acción determinada y los almacena en una lista para su
	 * análisis.
	 * </p>
	 *
	 * @param simbolo el símbolo de la acción (por ejemplo, "AAPL" para Apple Inc.)
	 * @return una lista de precios históricos de la acción
	 */
	public List<BigDecimal> obtenerDatosHistoricos(String simbolo) {
		RestTemplate restTemplate = new RestTemplate();
		List<BigDecimal> preciosHistoricos = new ArrayList<>();

		String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols="
				+ simbolo;

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-RapidAPI-Host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
		headers.set("X-RapidAPI-Key", rapidApiKey);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			JSONObject jsonObject = new JSONObject(response.getBody());
			JSONArray historicalData = jsonObject.getJSONObject("quoteResponse").getJSONArray("result");

			for (int i = 0; i < historicalData.length(); i++) {
				JSONObject data = historicalData.getJSONObject(i);
				preciosHistoricos.add(BigDecimal.valueOf(data.getDouble("regularMarketPrice")));
			}

		} catch (Exception e) {
			System.out.println("Error al obtener datos históricos para " + simbolo + ": " + e.getMessage());
		}

		return preciosHistoricos;
	}

	/**
	 * Realiza una simulación de proyección del precio futuro de una acción usando
	 * una técnica de media móvil simple.
	 * <p>
	 * Calcula el precio proyectado como el promedio de los precios históricos
	 * proporcionados.
	 * </p>
	 *
	 * @param preciosHistoricos lista de precios históricos de la acción
	 * @return el precio futuro proyectado, o {@code BigDecimal.ZERO} si no hay
	 *         datos históricos
	 */
	public BigDecimal proyectarPrecioFuturo(List<BigDecimal> preciosHistoricos) {
		BigDecimal total = BigDecimal.ZERO;

		for (BigDecimal precio : preciosHistoricos) {
			total = total.add(precio);
		}

		return preciosHistoricos.isEmpty() ? BigDecimal.ZERO
				: total.divide(BigDecimal.valueOf(preciosHistoricos.size()), BigDecimal.ROUND_HALF_UP);
	}

//	public byte[] exportToPdf(List<BigDecimal> proyectarPrecioFuturo) throws JRException, FileNotFoundException {
//		
//		return JasperExportManager.exportReportToPdf(getReport(proyectarPrecioFuturo));
//		
//	}
//
//	private JasperPrint getReport(List<BigDecimal> proyectarPrecioFuturo) throws FileNotFoundException, JRException {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("SimData", new JRBeanCollectionDataSource(proyectarPrecioFuturo));
//
//		JasperPrint report = JasperFillManager.fillReport(
//				JasperCompileManager.compileReport(
//						ResourceUtils.getFile("classpath:customerProductsReport.jrxml").getAbsolutePath()),
//				params, new JRBeanCollectionDataSource(proyectarPrecioFuturo));
//
//		return report;
//	}

}
