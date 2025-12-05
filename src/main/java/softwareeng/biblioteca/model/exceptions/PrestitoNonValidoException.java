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
public class PrestitoNonValidoException extends RuntimeException {

    /**
     * Creates a new instance of <code>PrestitoNonValidoException</code> without
     * detail message.
     */
    public PrestitoNonValidoException() {
    }

    /**
     * Constructs an instance of <code>PrestitoNonValidoException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PrestitoNonValidoException(String msg) {
        super(msg);
    }
}
