package com.andina.trading.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuración general de la aplicación para definir y gestionar los beans necesarios.
 * Esta clase permite configurar componentes que serán utilizados en el sistema, como el {@link RestTemplate}.
 *
 * <p>La anotación {@code @Configuration} indica que esta clase declara uno o más métodos {@code @Bean}
 * y puede ser procesada por el contenedor de Spring para generar definiciones de bean y solicitudes de servicio
 * en tiempo de ejecución.</p>
 *

 * @version 1.0
 */
@Configuration
public class AppConfig {

    /**
     * Crea y configura un bean de {@link RestTemplate} para realizar solicitudes HTTP.
     * <p>Este bean es utilizado en los servicios para hacer llamadas a APIs externas.</p>
     *
     * @return una instancia de {@link RestTemplate}
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
