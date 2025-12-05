/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model.exceptions;

/**
 *
 * @author cashrules
 */
public class EliminazioneNonValidaException extends RuntimeException {

    /**
     * Creates a new instance of <code>EliminazioneNonValidaException</code>
     * without detail message.
     */
    public EliminazioneNonValidaException() {
    }

    /**
     * Constructs an instance of <code>EliminazioneNonValidaException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public EliminazioneNonValidaException(String msg) {
        super(msg);
    }
}
