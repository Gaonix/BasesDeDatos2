package com.andina.trading.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PentahoController {

    @Autowired
    private PentahoETLService pentahoETLService;

    @GetMapping("/ejecutar-etl")
    public String ejecutarETL() {
        try {
            pentahoETLService.ejecutarTransformacion();
            return "Transformación ejecutada correctamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Hubo un error al ejecutar la transformación.";
        }
    }
}
