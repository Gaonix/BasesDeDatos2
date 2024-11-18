package com.andina.trading.controller;

import com.andina.trading.model.Usuarios;
import com.andina.trading.service.AccionService;
import com.andina.trading.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para manejar las solicitudes relacionadas con las acciones y empresas en el sistema Andina Trading.
 * Proporciona una vista que muestra las acciones y las empresas registradas.
 *
 * <p>Este controlador gestiona las solicitudes en la ruta "/acciones" y utiliza los servicios de
 * {@link AccionService} y {@link EmpresaService} para obtener la información necesaria.</p>
 *
 * <p>La anotación {@code @Controller} define esta clase como un controlador en Spring MVC.
 * La anotación {@code @RequestMapping} establece la ruta base para todas las solicitudes a este controlador.</p>
 *
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/acciones")
public class AccionController {

    /**
     * Servicio para gestionar operaciones relacionadas con acciones.
     */
    @Autowired
    private AccionService accionService;

    /**
     * Servicio para gestionar operaciones relacionadas con empresas.
     */
    @Autowired
    private EmpresaService empresaService;

    /**
     * Maneja las solicitudes GET en la ruta "/acciones" para mostrar una lista de acciones y empresas.
     *
     * <p>Este método recupera las listas de acciones y empresas a través de los servicios {@link AccionService}
     * y {@link EmpresaService}, y las añade al modelo para su visualización en la vista "acciones".</p>
     *
     * @param model el modelo de Spring utilizado para pasar datos a la vista
     * @return el nombre de la vista que se renderizará ("acciones")
     */
    @GetMapping
    public String mostrarAcciones(Model model) {
        model.addAttribute("acciones", accionService.obtenerAcciones());
        model.addAttribute("empresas", empresaService.obtenerEmpresas());
        return "acciones";
    }
}
