/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Clientes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Encriptador para usar en el numero de telefono
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class Encriptador {

    private static final String ALGORITMO = "AES";

    /**
     * Metodo para encriptar
     *
     * @param texto
     * @return
     * @throws Exception
     */
    public static String encriptar(String texto) throws Exception {
        SecretKey clave = obtenerClaveSecreta();
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] textoEncriptado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    private static SecretKey obtenerClaveSecreta() {
        // Genera una clave secreta de 128 bits
        String clave = "1234567890123456";
        return new SecretKeySpec(clave.getBytes(), ALGORITMO);
    }

    /**
     * Metodo para poder desencriptar de manera momentanea y que solo lo muestre
     * en los interfaces gráficos
     *
     * @param textoEncriptado
     * @return
     * @throws Exception
     */
    public static String desencriptar(String textoEncriptado) throws Exception {
        SecretKey clave = obtenerClaveSecreta();
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] textoDesencriptado = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
        return new String(textoDesencriptado);
    }
}
