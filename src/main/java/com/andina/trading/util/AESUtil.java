package com.andina.trading.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Clase utilitaria para realizar operaciones de cifrado y descifrado de datos utilizando el algoritmo AES.
 * Proporciona métodos estáticos para cifrar y descifrar texto en formato Base64.
 *
 * <p>Esta clase está diseñada para facilitar el cifrado de texto y mantener la seguridad de los datos
 * mediante el uso de un algoritmo de cifrado simétrico (AES).</p>
 *

 * @version 1.0
 */
public class AESUtil {

    /** Nombre del algoritmo de cifrado utilizado (AES). */
    private static final String ALGORITHM = "AES";

    /** Clave secreta fija utilizada para el cifrado y descifrado de datos. */
    private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    /**
     * Cifra los datos proporcionados utilizando el algoritmo AES.
     *
     * @param data Texto en formato de cadena que se desea cifrar.
     * @return Una cadena de texto que representa los datos cifrados en Base64.
     * @throws Exception si ocurre un error durante el proceso de cifrado.
     */
    public static String encrypt(String data) throws Exception {
        SecretKey key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    /**
     * Descifra los datos proporcionados en formato Base64 utilizando el algoritmo AES.
     *
     * @param encryptedData Datos cifrados en formato de cadena Base64.
     * @return Una cadena de texto que representa los datos descifrados en texto plano.
     * @throws Exception si ocurre un error durante el proceso de descifrado.
     */
    public static String decrypt(String encryptedData) throws Exception {
        SecretKey key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    /**
     * Genera la clave secreta necesaria para el cifrado y descifrado de los datos.
     *
     * @return Clave secreta de tipo {@link SecretKey} que se utiliza en las operaciones de cifrado y descifrado.
     */
    private static SecretKey generateKey() {
        return new SecretKeySpec(keyValue, ALGORITHM);
    }
}
