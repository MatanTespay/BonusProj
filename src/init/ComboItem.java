/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package init;

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
        return this.label.compareTo(o.label);
    }
    
}
