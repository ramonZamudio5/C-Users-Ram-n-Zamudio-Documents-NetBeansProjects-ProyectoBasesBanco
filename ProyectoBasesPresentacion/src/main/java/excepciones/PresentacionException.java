/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramón Zamudio
 */
public class PresentacionException extends Exception{

    public PresentacionException() {
    }

    public PresentacionException(String message) {
        super(message);
    }

    public PresentacionException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
