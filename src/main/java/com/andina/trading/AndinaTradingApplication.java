package com.andina.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase principal de la aplicación Andina Trading.
 * Esta clase inicia la aplicación utilizando Spring Boot y habilita la funcionalidad de tareas programadas.
 *
 * <p>La anotación {@code @SpringBootApplication} marca esta clase como la entrada principal de la aplicación
 * Spring Boot, que combina las anotaciones {@code @Configuration}, {@code @EnableAutoConfiguration} y
 * {@code @ComponentScan}.</p>
 *
 * <p>La anotación {@code @EnableScheduling} permite la ejecución de tareas programadas definidas en la aplicación.</p>
 *
 * @author Danniel Parra, Gabriel Mera
 * @version 1.0
 */
@SpringBootApplication
@EnableScheduling
public class AndinaTradingApplication {

	/**
	 * Método principal que inicia la aplicación Andina Trading.
	 *
	 * @param args argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(AndinaTradingApplication.class, args);
	}
}
