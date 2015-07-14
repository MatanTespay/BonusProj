/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 * Represents errors that occur during application execution in {@link gui.AddCustomer }
 * @author Matan
 */
public class AddCustomerExeption extends Exception {

    /**
     * Constructs a new exeption with the given message
     * @param message
     */
    public AddCustomerExeption(String message) {
        super(message);
    }
    
}
