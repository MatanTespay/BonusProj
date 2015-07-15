/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.MainClass;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utils.E_Cities;
import utils.HelperClass;
import core.Address;
import exceptions.AddCustomerExeption;

import com.toedter.calendar.JDateChooser;

/**
 *The class represents the screen which enable users to add {@link core.Customer } to the {@link init.IFly } class 
 *
 * @author Matan
 */
public class AddCustomer extends MyInternalFrame {

    /**
     * Creates new form AddCustomer
     *
     * String passportNumber, double balance, String firstName, String lastName,
     * Date birthDate, String password, URL email, Address customerAddress
     *
     * @param title
     * @param type
     */
    public AddCustomer(String title, String type) {
        super(title, type, null);
        initComponents();
        setLables();
        jdBirthDate.setMaxSelectableDate(new Date());
        btnOk.setToolTipText(utils.Constants.saveButtonToolTip);
    }

    /**
     * sets the error labels to the the fields in the screen
     */
    private void setLables() {

        lblBalanceErr.setLabelFor(txtBalance);
        lblBdateErr.setLabelFor(jdBirthDate);
        lblEmailErr.setLabelFor(txtEmail);
        lblFnameErr.setLabelFor(txtFirstname);
        lblLName.setLabelFor(txtLastName);
        lblPassportErr.setLabelFor(txtPassportNumber);
        lblPasswordErr.setLabelFor(txtPassword);

    }

    /**
     * save action on {@link #btnOk} action perform 
     */
    private void saveChanges() throws MalformedURLException {
        boolean result = true;
        JLabel[] arry = {lblBalanceErr, lblBdateErr, lblEmailErr, lblFnameErr, lblLName, lblPassportErr, lblPasswordErr};
        for (JLabel lable : arry) {
            Component c =  lable.getLabelFor();
            if (c != null) {
                String tempErr = null;
                if (lable.equals(lblBalanceErr)) {
                    tempErr = HelperClass.getErrMsg(((JTextField)c).getText(), "Number");
                    
                }
                else if(lable.equals(lblPassportErr)){
                    if (((JTextField)c).getText().trim().length() > 8) 
                    tempErr = "Number cant be more then 8 digits";
                    else
                    tempErr = HelperClass.getErrMsg(((JTextField)c).getText(), "Number");
                    
                }
                else if (lable.equals(lblBdateErr)) {                    
                     if(((JDateChooser)c).getDate() == null)
                         tempErr = "Date is in incorrect format or empty";
                } else if (lable.equals(lblEmailErr)) {
                    tempErr = HelperClass.getErrMsg(((JTextField)c).getText(), "Email");

                } else if (lable.equals(lblFnameErr) || lable.equals(lblLName)) {
                    tempErr = HelperClass.getErrMsg(((JTextField)c).getText(), "Text");

                }
                else if (lable.equals(lblPasswordErr)){
                    if(((JTextField)c).getText() == null || ((JTextField)c).getText().length() == 0){
                        tempErr = "Value cant be empty";
                    }
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

        if (!addressPanel.validateInputs()) {
            result = false;
        }

        if (phonePanel.isTableEmpty()) {
            return;
        }
        
        Object[][] data = HelperClass.getTableData(phonePanel.getTblPhones());
        String[] phones = null;
        if (data != null) {
            phones= new String[data.length];
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
        }else
            result = false;

        if (result) {
            try {
                String passportNumber, password, firstName, lastName;
                double balance;
                Date birthDate;
                URL email ;              
                passportNumber = HelperClass.getFixID(txtPassportNumber.getText());
                password = txtPassword.getText();
                firstName = txtFirstname.getText();
                lastName = txtLastName.getText();
                balance = Double.parseDouble(txtBalance.getText());
                birthDate = jdBirthDate.getDate();
                email = new URL("hTTp:\\\\"+txtEmail.getText());
                Address Address = new Address(addressPanel.getTxtCountry().getText(), E_Cities.valueOf(addressPanel.getComBoxAddress().getSelectedItem().toString()), addressPanel.getTxtStreet().getText(),
                        Integer.parseInt(addressPanel.getTxtHouseNumber().getText()), phones);
                MainClass.getIfly().addCustomer(passportNumber, balance, firstName, lastName, birthDate, password, email, Address);
                JOptionPane.showInternalConfirmDialog(this, "Saved changes", "Confirmation", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                setIflyState(false);
            }catch (AddCustomerExeption | HeadlessException | NumberFormatException | MalformedURLException ex) {
                JOptionPane.showInternalConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

            }
            
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panleDetails = new javax.swing.JPanel();
        txtFirstname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPassportNumber = new javax.swing.JTextField();
        lblPassportErr = new javax.swing.JLabel();
        lblFnameErr = new javax.swing.JLabel();
        lblLName = new javax.swing.JLabel();
        lblBdateErr = new javax.swing.JLabel();
        lblPasswordErr = new javax.swing.JLabel();
        lblEmailErr = new javax.swing.JLabel();
        lblBalanceErr = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        addressPanel = new gui.AddressPanel();
        phonePanel = new gui.PhonePanel();
        txtPassword = new javax.swing.JPasswordField();
        jdBirthDate = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("Passport Number:");

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Birth Date:");

        jLabel5.setText("Password:");

        jLabel6.setText("Email:");

        jLabel7.setText("Balance");

        lblLName.setText(" ");

        lblBdateErr.setText(" ");

        lblPasswordErr.setText(" ");

        btnOk.setText("Submit");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jdBirthDate.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout panleDetailsLayout = new javax.swing.GroupLayout(panleDetails);
        panleDetails.setLayout(panleDetailsLayout);
        panleDetailsLayout.setHorizontalGroup(
            panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panleDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panleDetailsLayout.createSequentialGroup()
                            .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(panleDetailsLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(34, 34, 34)))
                            .addGap(18, 18, 18)
                            .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassportNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblPassportErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFnameErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(phonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panleDetailsLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBalanceErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panleDetailsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPasswordErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panleDetailsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblEmailErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panleDetailsLayout.createSequentialGroup()
                                .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panleDetailsLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBdateErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panleDetailsLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panleDetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panleDetailsLayout.createSequentialGroup()
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panleDetailsLayout.createSequentialGroup()
                                .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        panleDetailsLayout.setVerticalGroup(
            panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panleDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panleDetailsLayout.createSequentialGroup()
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jdBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBdateErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPasswordErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panleDetailsLayout.createSequentialGroup()
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPassportNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPassportErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(lblFnameErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panleDetailsLayout.createSequentialGroup()
                        .addComponent(lblEmailErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBalanceErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk))
                    .addGroup(panleDetailsLayout.createSequentialGroup()
                        .addComponent(lblLName, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(phonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panleDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panleDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param evt
     * @exception {@ link exceptions.AddCustomerExeption}
     */
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        
        try {
            // TODO add your handling code here:
            saveChanges();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.AddressPanel addressPanel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.calendar.JDateChooser jdBirthDate;
    private javax.swing.JLabel lblBalanceErr;
    private javax.swing.JLabel lblBdateErr;
    private javax.swing.JLabel lblEmailErr;
    private javax.swing.JLabel lblFnameErr;
    private javax.swing.JLabel lblLName;
    private javax.swing.JLabel lblPassportErr;
    private javax.swing.JLabel lblPasswordErr;
    private javax.swing.JPanel panleDetails;
    private gui.PhonePanel phonePanel;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPassportNumber;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

}
