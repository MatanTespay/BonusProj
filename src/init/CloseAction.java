/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *The class represents the an AbstractAction which enable users to close the  {@link gui.MainWindow}} 

* @author Matan
*/
public class CloseAction extends AbstractAction {

    JFrame f;

    public CloseAction() {
    }

    public CloseAction(String text, ImageIcon icon,
            String desc, Integer mnemonic, JFrame f) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
        this.f = f;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = "";
        for (Frame frame : Frame.getFrames()) {
             
            if (frame.isActive()) {
            WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
            frame.dispatchEvent(windowClosing);
            }
        }
    }

}
