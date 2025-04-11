/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NegocioException;

/**
 * Clase NegocioException
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class NegocioException extends Exception {

    public NegocioException() {
    }

    /**
     *
     * @param message mensaje
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     *
     * @param message mensaje 
     * @param cause causa
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause causa
     */
    public NegocioException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message mensaje
     * @param cause causa
     * @param enableSuppression supresión de la excepción
     * @param writableStackTrace Indica si el seguimiento de pila debe ser registrado
     */
    public NegocioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
