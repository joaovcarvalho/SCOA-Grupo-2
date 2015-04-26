/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author JoãoVitor
 */
public class KeyNotExistsException extends Exception{

    public KeyNotExistsException() {
    }

    public KeyNotExistsException(String message) {
        super(message);
    }

    public KeyNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyNotExistsException(Throwable cause) {
        super(cause);
    }

    public KeyNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
