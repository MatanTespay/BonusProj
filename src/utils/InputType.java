/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Matan
 */
public enum  InputType {
    
    
    TEXT("Text"),
    PASSWORD("Password"),
    NUMBER("Number"),
    DATE("Date"),
    EMAIL("Email"),
    ROLE("ROLE");

    private final String Type;

    /**
     * @param text
     */
    private InputType(final String text) {
        this.Type = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return Type;
    }
    
    public String getType() {
        return Type;
    }
    
   
}
