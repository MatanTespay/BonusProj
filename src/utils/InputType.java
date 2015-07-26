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
    
    BYTE("Byte"),
    CHAR1("Char1"),
    DATE("Date"),
    DOUBLE("Double"),
    EMAIL("Email"),
    FLOAT("Float"),
    INT("Int"),
    LONG("Long"),
    PASSWORD("Password"),
    ROLE("Role"),
    SHORT("Short"),
    TEXT("Text"),
    YEAR_RANGE("INT_RANGE");

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
