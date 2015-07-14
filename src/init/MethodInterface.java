/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package init;

import javax.swing.event.DocumentEvent;

/**
 *
 * @author Matan
 */
public interface MethodInterface {
    
    
    public void saveChanges();
    
    public boolean checkField(DocumentEvent e);
}
