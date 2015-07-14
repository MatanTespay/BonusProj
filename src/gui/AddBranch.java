/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;
import init.MethodInterface;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import utils.E_Cities;
import utils.HelperClass;
import exceptions.AddBranchExeption;

/**
 *The class represents the screen which enable users to add {@link core.Branch } to the {@link init.IFly } class
 *
 *
 * @author Matan
 */
public class AddBranch extends MyInternalFrame implements MethodInterface {

    /**
     * Creates new form AddBranch
     *
     * @param title
     * @param type
     */
    public AddBranch(String title, String type) {
        super(title, type);
        initComponents();
        fillCbCity();
        //txtHouseNumber ,txtStreet,txtbranchName,txtbranchNumber

        lblBNameErr.setLabelFor(txtbranchName);
        lblBNumberErr.setLabelFor(txtbranchNumber);
        lblHNumberErr.setLabelFor(txtHouseNumber);
        lblSreetErr.setLabelFor(txtStreet);
        btnOK.setToolTipText(utils.Constants.saveButtonToolTip);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblbranchNumber = new javax.swing.JLabel();
        lblbranchName = new javax.swing.JLabel();
        lblstreet = new javax.swing.JLabel();
        lblhouseNumber = new javax.swing.JLabel();
        txtbranchName = new javax.swing.JTextField();
        txtbranchNumber = new javax.swing.JTextField();
        txtStreet = new javax.swing.JTextField();
        txtHouseNumber = new javax.swing.JTextField();
        lblBNumberErr = new javax.swing.JLabel();
        lblBNameErr = new javax.swing.JLabel();
        lblHNumberErr = new javax.swing.JLabel();
        lblSreetErr = new javax.swing.JLabel();
        cbCity = new javax.swing.JComboBox();
        lblcity = new javax.swing.JLabel();
        lblcountry = new javax.swing.JLabel();
        txtCountry = new javax.swing.JLabel();
        phonePanel = new gui.PhonePanel();
        btnOK = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        lblbranchNumber.setText("Branch Number:");

        lblbranchName.setText("Branch Name:");

        lblstreet.setText("Street:");

        lblhouseNumber.setText("House Number:");

        cbCity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCity.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCityActionPerformed(evt);
            }
        });

        lblcity.setText("City:");

        lblcountry.setText("Country:");

        txtCountry.setText("txtCountry");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHNumberErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSreetErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBNumberErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblbranchNumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblbranchName, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbranchName)
                            .addComponent(txtbranchNumber)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblstreet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblhouseNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHouseNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addComponent(lblBNameErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcity)
                            .addComponent(lblcountry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCity, 0, 122, Short.MAX_VALUE)
                            .addComponent(txtCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblbranchNumber)
                    .addComponent(txtbranchNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBNumberErr, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblbranchName)
                    .addComponent(txtbranchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(lblBNameErr, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblstreet)
                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSreetErr, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblhouseNumber)
                    .addComponent(txtHouseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblHNumberErr, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcity))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcountry)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        btnOK.setText("Submit");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(phonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOK)
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(phonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOK)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCityActionPerformed
        // TODO add your handling code here:
        JComboBox cb = (JComboBox) evt.getSource();
        if (cb.getModel().getSize() <= 0) {
            return;
        }
        String city = cb.getSelectedItem().toString();
        txtCountry.setText(E_Cities.valueOf(city).getCountry());
    }//GEN-LAST:event_cbCityActionPerformed

    /**
     * @param evt
     * @exception {@link exceptions.AddBranchExeption }
     */
    private void btnOKActionPerJformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        saveChanges();
    }//GEN-LAST:event_btnOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cbCity;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBNameErr;
    private javax.swing.JLabel lblBNumberErr;
    private javax.swing.JLabel lblHNumberErr;
    private javax.swing.JLabel lblSreetErr;
    private javax.swing.JLabel lblbranchName;
    private javax.swing.JLabel lblbranchNumber;
    private javax.swing.JLabel lblcity;
    private javax.swing.JLabel lblcountry;
    private javax.swing.JLabel lblhouseNumber;
    private javax.swing.JLabel lblstreet;
    private gui.PhonePanel phonePanel;
    private javax.swing.JLabel txtCountry;
    private javax.swing.JTextField txtHouseNumber;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtbranchName;
    private javax.swing.JTextField txtbranchNumber;
    // End of variables declaration//GEN-END:variables

    /**
     * populates the {@link #cbCity}
     */
    private void fillCbCity() {
        E_Cities[] cities = utils.E_Cities.values();

        ComboItem[] items = new ComboItem[cities.length];

        for (int i = 0; i < cities.length; i++) {

            items[i] = new ComboItem(cities[i].name(), cities[i].name());
        }

        cbCity.setModel(new javax.swing.DefaultComboBoxModel(items));
        E_Cities c = E_Cities.valueOf(cbCity.getSelectedItem().toString());
        txtCountry.setText(c.getCountry());
    }

    /**
     * save action on {@link #btnOK} action perform 
     */
    @Override
    public void saveChanges() {

        boolean result = true;
        JLabel[] arry = {lblBNumberErr, lblBNameErr, lblSreetErr, lblHNumberErr};
        for (JLabel lable : arry) {
            String tempErr = null;
            JTextField tf = (JTextField) lable.getLabelFor();
            if (tf != null && lable.equals(lblBNumberErr) || tf != null && lable.equals(lblHNumberErr)) {
                tempErr = HelperClass.getErrMsg(tf.getText(), "Number");

            } else if (tf != null && lable.equals(lblBNameErr) || tf != null && lable.equals(lblSreetErr)) {
                tempErr = HelperClass.getErrMsg(tf.getText(), "Text");
            }

            if (tempErr != null) {
                result = false;
                lable.setText(tempErr);
                lable.setForeground(Color.red);
            } else {
                lable.setText("");
            }
        }

        if (phonePanel.isTableEmpty()) {
            return;
        }
        Object[][] data = HelperClass.getTableData(phonePanel.getTblPhones());
        String[] phones = null;
        if (data != null) {
            phones = new String[data.length];
            if (data.length == 0) {
                result = false;
                phonePanel.getLblPhoneErr().setText("Missing Phone Number");
                phonePanel.getLblPhoneErr().setForeground(Color.red);
            } else {
                for (int i = 0; i < data.length; i++) {
                    phones[i] = (String) data[i][1];
                }
                phonePanel.getLblPhoneErr().setText("");
            }
        }
        else
            result = false;

        if (result) {
            //MainClass.getIfly().add
            int branchNumber, houseNumber;
            E_Cities city;
            String country, street, branchName;

            branchNumber = Integer.parseInt(txtbranchNumber.getText());
            houseNumber = Integer.parseInt(txtHouseNumber.getText());
            city = E_Cities.valueOf(cbCity.getSelectedItem().toString());
            country = lblcountry.getText();
            street = txtStreet.getText();
            branchName = txtbranchName.getText();

            try {
                MainClass.getIfly().addBranch(branchNumber, branchName, city, country, street, houseNumber, phones);
                JOptionPane.showInternalConfirmDialog(this, "Saved changes", "Confirmation", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                setIflyState(false);
            } catch (AddBranchExeption ex) {
                JOptionPane.showInternalConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    @Override
    public boolean checkField(DocumentEvent e) {
        boolean result = true;
        return result;
    }

}
