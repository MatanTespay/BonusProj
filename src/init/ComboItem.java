/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package init;

import java.sql.Date;

/**
 *The class represents a single item {includes value and name} in a drop down list.

* @author Matan
*/
public class ComboItem implements Comparable<ComboItem>{
    private final String value;
    private final String label;

    public ComboItem(String value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label;
    }
    
    @Override
    public int compareTo(ComboItem o) {
        try{
            // the label represents an integer
            Integer thisIntLabel = Integer.valueOf(this.label);
            Integer otherIntLabel = Integer.valueOf(o.label);
            return thisIntLabel.compareTo(otherIntLabel);
        } catch (NumberFormatException ex1){
            try{
                // the label represents a date
                Date thisDateLabel = Date.valueOf(this.label);
                Date otherDateLabel = Date.valueOf(o.label);
                return thisDateLabel.compareTo(otherDateLabel);
            } catch (IllegalArgumentException ex2){
                // the string is just a string
                return this.label.compareTo(o.label);
            }
        }
         
        
    }
    
}
