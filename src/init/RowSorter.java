/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import java.util.Comparator;
import javax.swing.DefaultRowSorter;

/**
 *
 * @author asus
 */
public class RowSorter extends DefaultRowSorter{
    
    public RowSorter(){
        this.setComparator(0, new Comparator<Object[]>() {

            @Override
            public int compare(Object[] o1, Object[] o2) {
               if (o1[0] instanceof String && o2[0] instanceof String){
                   return ((String)o1[0]).compareTo((String)o2[0]);
               } else{
                   return o1[0].hashCode()-o2[0].hashCode();
               }
            }
        });
    }
}