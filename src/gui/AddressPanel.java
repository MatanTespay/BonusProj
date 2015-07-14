/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import init.ComboItem;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utils.E_Cities;
import utils.HelperClass;

/**
 *The class represents the Address information which included in the relevant screens.

 * @author Matan
 */
public class AddressPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddressPanel
     */
    public AddressPanel() {
        initComponents();
        fillCity();
    }

    private void fillCity(){
        E_Cities[] cities = utils.E_Cities.values();        
        ComboItem[] items = new ComboItem[cities.length];

        for (int i = 0; i < cities.length; i++) {
            if(cities[i]!= null)
            items[i] = new ComboItem(cities[i].name(), cities[i].name());
        }
        
        ComBoxAddress.setModel(new javax.swing.DefaultComboBoxModel(items));        
        ComBoxAddress.setSelectedIndex(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errHouse = new javax.swing.JLabel();
        errStreet = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtHouseNumber = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtStreet = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ComBoxAddress = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JLabel();

        errHouse.setText(" ");

        errStreet.setText(" ");

        jLabel12.setText("house Number:");

        jLabel11.setText("Street:");

        jLabel6.setText("City:");

        ComBoxAddress.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComBoxAddressActionPerformed(evt);
            }
        });

        jLabel9.setText("Country:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComBoxAddress, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStreet, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(txtCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(errHouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtHouseNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(ComBoxAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtHouseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errHouse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ComBoxAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComBoxAddressActionPerformed
        // TODO add your handling code here:
        JComboBox cb = (JComboBox) evt.getSource();        
        String city = cb.getSelectedItem().toString();
        txtCountry.setText(E_Cities.valueOf(city).getCountry());
        setLables();
    }//GEN-LAST:event_ComBoxAddressActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComBoxAddress;
    private javax.swing.JLabel errHouse;
    private javax.swing.JLabel errStreet;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel txtCountry;
    private javax.swing.JTextField txtHouseNumber;
    private javax.swing.JTextField txtStreet;
    // End of variables declaration//GEN-END:variables

    private void setLables(){
        errHouse.setLabelFor(txtHouseNumber);
         errStreet.setLabelFor(txtStreet);
         
    }
    
    public boolean validateInputs(){
        boolean result = true;
        JLabel[] arry = {errHouse, errStreet};
         for (JLabel lable : arry) {
            JTextField tf = (JTextField) lable.getLabelFor();
            if (tf != null) {
                String tempErr = null;
                if (lable.equals(errStreet)){
                    tempErr = HelperClass.getErrMsg(tf.getText(), "Text");
                }
                else if(lable.equals(errHouse)){
                    tempErr = HelperClass.getErrMsg(tf.getText(), "Number");
                }
                
                if (tempErr != null) {
                    result = false;
                    lable.setText(tempErr);
                    lable.setForeground(Color.red);
                } else {
                    lable.setText("");
                }
            }
        }
         
        return result;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    public void setComBoxAddress(JComboBox ComBoxAddress) {
        this.ComBoxAddress = ComBoxAddress;
    }
    
    public void setErrHouse(JLabel errHouse) {
        this.errHouse = errHouse;
    }
    
    public void setErrStreet(JLabel errStreet) {
        this.errStreet = errStreet;
    }
    
    public void setTxtCountry(JLabel txtCountry) {
        this.txtCountry = txtCountry;
    }
    
    public void setTxtHouseNumber(JTextField txtHouseNumber) {
        this.txtHouseNumber = txtHouseNumber;
    }
    
    public void setTxtStreet(JTextField txtStreet) {
        this.txtStreet = txtStreet;
    }
    
    
    public JComboBox getComBoxAddress() {
        return ComBoxAddress;
    }
    
    public JLabel getErrHouse() {
        return errHouse;
    }
    
    public JLabel getErrStreet() {
        return errStreet;
    }
    
    public JLabel getTxtCountry() {
        return txtCountry;
    }
    
    public JTextField getTxtHouseNumber() {
        return txtHouseNumber;
    }
    
    public JTextField getTxtStreet() {
        return txtStreet;
    }
//</editor-fold>


}
