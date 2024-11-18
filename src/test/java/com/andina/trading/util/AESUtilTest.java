package com.andina.trading.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase de pruebas unitarias para la utilidad {@link AESUtil}.
 * Verifica la funcionalidad de los métodos de cifrado y descifrado utilizando el algoritmo AES.
 *
 * <p>La clase comprueba que los datos cifrados se puedan descifrar correctamente y que el texto
 * resultante coincida con el original.</p>
 *
 * @version 1.0

 */
public class AESUtilTest {

    /**
     * Prueba el método de cifrado y descifrado de {@link AESUtil} para verificar que el texto cifrado
     * pueda ser descifrado correctamente y coincida con el texto original.
     *
     * @throws Exception si ocurre algún error durante el proceso de cifrado o descifrado
     */
    @Test
    public void testEncryptAndDecrypt() throws Exception {
        // Texto de prueba para cifrar y descifrar
        String textoOriginal = "TextoDePrueba";

        // Cifra el texto original
        String textoCifrado = AESUtil.encrypt(textoOriginal);

        // Descifra el texto cifrado
        String textoDescifrado = AESUtil.decrypt(textoCifrado);

        // Verifica que el texto descifrado coincida con el original
        assertEquals(textoOriginal, textoDescifrado,
                "El texto descifrado debe coincidir con el texto original");
    }
}
