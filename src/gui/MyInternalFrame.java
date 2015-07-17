/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.InputValidator;
import init.MainClass;
import java.awt.Color;

import java.util.AbstractMap;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import utils.HelperClass;

/**
 *
 * The class represents the root object of the internal frames in the system.
 *
 * @author Matan
 *
 */
/* Used by InternalFrameDemo.java. */
public class MyInternalFrame extends JInternalFrame {

    static int openFrameCount = 0;
    static int xOffset = 0, yOffset = 0;
    String selectedUserType;
    AbstractMap.SimpleEntry selectedCustomer;
    DisabledGlassPane glassPane;
    JInternalFrame parent;
    JInternalFrame child;
    protected ArrayList<InputValidator> validators;
    private boolean mode;

    /**
     *
     * @param title the value of title
     * @param userType the value of userType
     */
    public MyInternalFrame(String title, String userType) {

        super(title,
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        ++openFrameCount;
        selectedUserType = userType;
        //...Create the GUI and put it in the window..;
        //Set the window's location.

        setLocation(xOffset, yOffset);
        glassPane = new DisabledGlassPane();
        //ToolTipManager.sharedInstance().registerComponent(this);

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                SetActivate();
            }

            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }

            @Override
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {

            }

            @Override
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
    }

    MyInternalFrame() {
        super();
    }

    /**
     * returns the frame's glass pane
     *
     * @return returns the glass pane
     */
    public DisabledGlassPane getDisabledGlassPane() {
        return glassPane;
    }

    /**
     * changes the state of the window title buttons according to the given
     * state
     *
     * @param state
     */
    public void changeWindowButtons(boolean state) {
        setEnabled(state);
        setMaximizable(state);
        setIconifiable(state);
        setClosable(state);
    }

    public boolean isInputOk() {
        boolean result = true;
        for (InputValidator validator : validators) {
            JTextField txt = (JTextField) validator.getComponent();
            validator.setErrMsg(HelperClass.getErrMsg(txt.getText(), validator.getInputType().getType()));
            if (validator.getErrMsg() != null) {
                result = false;
                validator.getErrLable().setText(validator.getErrMsg());
                validator.getErrLable().setForeground(Color.red);
            } else {
                validator.getErrLable().setText("");
            }
        }
        return result;
    }

    /**
     * tries to set the frame as selected in the desktop pane
     */
    public void SetActivate() {

        JDesktopPane desk = this.getDesktopPane();
        if (desk != null) {

            JInternalFrame[] frames = desk.getAllFrames();
            JInternalFrame theFrame = null;
            for (JInternalFrame frame : frames) {
                if (child != null && frame.equals(child)) {
                    theFrame = child;
                }
            }

            if (theFrame == null) {
                return;
            }

            if (desk.getSelectedFrame() != null && desk.getSelectedFrame().equals(this)) {

                try {

                    theFrame.setSelected(true);

                } catch (java.beans.PropertyVetoException e) {
                }

            }
        }

    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
        // TODO add your handling code here:
        if (parent != null) {

            MyInternalFrame theFrame = ((MyInternalFrame) parent);
            theFrame.changeWindowButtons(true);
            theFrame.setGlassPane(theFrame.getDisabledGlassPane());
            theFrame.getDisabledGlassPane().deactivate();

        }
    }

    /**
     * show a diakog messsage with the given string
     *
     * @param s
     */
    public void shoMissingDataMsg(String s) {
        JOptionPane.showInternalConfirmDialog(this, "Cant process request\n" + s, "Information", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * changes the save state of the {@link init.IFly}}
     *
     * @param state
     */
    public void setIflyState(boolean state) {
        MainClass.setIsIflySaved(state);
    }

    public void openChildFrame(MyInternalFrame child) {
        JDesktopPane desk = this.getDesktopPane();

        if (desk != null) {
            JInternalFrame[] frames = desk.getAllFrames();
            for (JInternalFrame frame : frames) {
                if (frame.getTitle().equals(title)) {
                    MyInternalFrame theFrame = (MyInternalFrame) frame;
                    theFrame.changeWindowButtons(false);

                    theFrame.setGlassPane(theFrame.getDisabledGlassPane());
                    theFrame.getDisabledGlassPane().activate("Please wait");
                }
            }

            child.setVisible(true);
            this.child = child;
            try {
                desk.add(child);
                child.setSelected(true);

            } catch (java.beans.PropertyVetoException ex) {
            }
        }
    }

    /**
     * @return the mode
     */
    public boolean getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(boolean mode) {
        this.mode = mode;
    }
}
