/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import java.awt.Component;
import javax.swing.JLabel;
import utils.InputType;

/**
 *
 * @author Matan
 */
public class InputValidator {
    private Component component;
    private InputType type;
    private JLabel errLable;
    private String errMsg;

    public InputValidator(Component component, InputType inputType, JLabel errLable, String errMsg) {
        this.component = component;
        this.type = inputType;
        this.errLable = errLable;
        this.errMsg = errMsg;
    }

    
    public Component getComponent() {
        return component;
    }

    public InputType getInputType() {
        return type;
    }

    public JLabel getErrLable() {
        return errLable;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public void setInputType(InputType inputType) {
        this.type = inputType;
    }

    public void setErrLable(JLabel errLable) {
        this.errLable = errLable;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
    
}
