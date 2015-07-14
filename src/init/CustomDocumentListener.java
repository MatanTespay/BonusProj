/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package init;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Matan
 */
public class CustomDocumentListener implements DocumentListener{

    MethodInterface _interface;
    public CustomDocumentListener(MethodInterface frame) {
        this._interface = frame;
    }

    @Override
        public void changedUpdate(DocumentEvent e) {
            validate(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validate(e);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {

            validate(e);
        }

        public void validate(DocumentEvent e) {
          _interface.checkField(e);
        }
}
