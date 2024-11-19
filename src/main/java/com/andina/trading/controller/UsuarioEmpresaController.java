package com.andina.trading.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.andina.trading.service.AccionService;
import com.andina.trading.service.EmpresaService;
import com.andina.trading.service.UsuarioEmpresaService;

import net.sf.jasperreports.engine.JRException;

/**
 * Controlador para manejar los reportes del sistema Andina Trading.
 * Mostrando el nombre del cliente, nombre de la empresa y cantidad de acciones.
 *
 * <p>Este controlador gestiona las solicitudes en la ruta "/reporte" y utiliza los servicios de
 * {@link UsuarioEmpresaService}} para obtener la información necesaria.</p>
 *
 * <p>La anotación {@code @Controller} define esta clase como un controlador en Spring MVC.
 * La anotación {@code @RequestMapping} establece la ruta base para todas las solicitudes a este controlador.</p>
 *
 * @author 
 * @version 1.0
 */

@Controller
public class UsuarioEmpresaController {
	/**
     * Servicio para gestionar operaciones relacionando empresas con el usuario.
     */
	@Autowired
    private UsuarioEmpresaService usuarioEmpresaService;
	
	@GetMapping("/reporte")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("Reporte", "Reporte.pdf");
        return ResponseEntity.ok().headers(headers).body(usuarioEmpresaService.exportarPdf());
    }

}
