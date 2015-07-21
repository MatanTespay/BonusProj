/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package init;

import java.sql.Date;
import java.util.Objects;

/**
 *The class represents a single item {includes value and name} in a drop down list.

* @author Matan
*/
public class ComboItem implements Comparable<ComboItem>{
    private final Object key;
    private final String label;

    public ComboItem(Object key, String label) {
        this.key = key;
        this.label = label;
    }
    
    public Object getKey() {
        return this.key;
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
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass().equals(ComboItem.class)){
            return getKey().equals(((ComboItem)obj).getKey());
        } else if(obj instanceof String){
            return this.key.toString().equalsIgnoreCase(obj.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.key);
        return hash;
    }
}
