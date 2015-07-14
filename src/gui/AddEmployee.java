/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utils.E_Cities;
import utils.E_LicenseType;
import utils.HelperClass;
import core.Address;
import core.Agent;
import core.Employee;
import core.FlightAttendant;
import core.Pilot;
import exceptions.AddEmployeeExeption;
import com.toedter.calendar.JDateChooser;

/**
 *The class represents the screen which enable users to add {@link core.Employee } to the {@link init.IFly } class 
 *
 * @author Matan
 */
public class AddEmployee extends MyInternalFrame {

    /**
     * Creates new form AddEmployee
     *
     * @param title
     * @param type
     */
    public AddEmployee(String title, String type) {
        super(title, type);
        initComponents();
        setEmpChoice(title);
        setLables();
        jdBirthDate.setMaxSelectableDate(new Date());
        btnOk.setToolTipText(utils.Constants.saveButtonToolTip);
    }

    /**
     * sets the error labels to the the fields in the screen
     */
    private void setLables() {

        lblEmpNumberErr.setLabelFor(txtEmpNumber);
        lblFnameErr.setLabelFor(txtFirstname);
        lblLNameErr.setLabelFor(txtLastName);
        lblBdateErr.setLabelFor(jdBirthDate);
        lblStartWorkingDateErr.setLabelFor(jdStartWorkDate);
        lblPasswordErr.setLabelFor(txtPassword);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInfo = new javax.swing.JPanel();
        txtFirstname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmpNumber = new javax.swing.JTextField();
        lblEmpNumberErr = new javax.swing.JLabel();
        lblFnameErr = new javax.swing.JLabel();
        lblLNameErr = new javax.swing.JLabel();
        lblBdateErr = new javax.swing.JLabel();
        lblStartWorkingDateErr = new javax.swing.JLabel();
        lblPasswordErr = new javax.swing.JLabel();
        addressPanel = new gui.AddressPanel();
        btnOk = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        phonePanel = new gui.PhonePanel();
        jdBirthDate = new com.toedter.calendar.JDateChooser();
        jdStartWorkDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        cBoxIsHerald = new javax.swing.JCheckBox();
        lblLicensetype = new javax.swing.JLabel();
        cbLicenseType = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("Employee Number:");

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Birth Date:");

        jLabel5.setText("Start working Date:");

        jLabel6.setText("Password:");

        lblLNameErr.setText(" ");

        lblBdateErr.setText(" ");

        lblStartWorkingDateErr.setText(" ");

        btnOk.setText("Submit");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jdBirthDate.setDateFormatString("dd/MM/yyyy");
        jdBirthDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdBirthDatePropertyChange(evt);
            }
        });

        jdStartWorkDate.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoLayout.createSequentialGroup()
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmpNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblEmpNumberErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLNameErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFnameErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStartWorkingDateErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPasswordErr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdStartWorkDate, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoLayout.createSequentialGroup()
                                .addComponent(lblBdateErr, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInfoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                        .addComponent(phonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))))
                .addContainerGap())
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jdBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBdateErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jdStartWorkDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStartWorkingDateErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtEmpNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEmpNumberErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(lblFnameErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPasswordErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLNameErr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk))
                    .addComponent(phonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        cBoxIsHerald.setText("is Herald");

        lblLicensetype.setText("License Type:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cBoxIsHerald)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLicensetype)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbLicenseType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cBoxIsHerald)
                    .addComponent(cbLicenseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLicensetype))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * save action on {@link #btnOk} action perform 
     * @exception {@ link exceptions.AddEmployeeExeption}
     */
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        boolean result = true;
        JLabel[] arry = {lblEmpNumberErr, lblPasswordErr, lblFnameErr, lblLNameErr, lblBdateErr,
            lblStartWorkingDateErr};
        for (JLabel lable : arry) {
            Component c =  lable.getLabelFor();
            if (c != null) {
                String tempErr = null;
                if (lable.equals(lblEmpNumberErr)) {
                    tempErr = HelperClass.getErrMsg(((JTextField)c).getText(), "Number");
                } else if (lable.equals(lblPasswordErr)) {
                    if (((JTextField)c).getText() == null || ((JTextField)c).getText().length() == 0) {
                        tempErr = "Value cant be empty";
                    }
                } else if (lable.equals(lblFnameErr) || lable.equals(lblLNameErr)) {
                    tempErr = HelperClass.getErrMsg(((JTextField)c).getText(), "Text");
                } else if (lable.equals(lblBdateErr) || lable.equals(lblStartWorkingDateErr)) {
                   if(((JDateChooser)c).getDate() == null)                       
                         tempErr = "Date is in incorrect format or empty";
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
            /*
             agent
             int employeeNumber, String firstName, String lastName,
             Date birthDate, Date startWorkingDate, String password,
             Address address
             */
            String password, firstName, lastName;
            int employeeNumber;
            Date birthDate, startWorkingDate;

            Address address;
            employeeNumber = Integer.parseInt(txtEmpNumber.getText());
            password = txtPassword.getText();
            firstName = txtFirstname.getText();
            lastName = txtLastName.getText();
            birthDate = jdBirthDate.getDate();
            startWorkingDate = jdStartWorkDate.getDate();
            address = new Address(addressPanel.getTxtCountry().getText(), E_Cities.valueOf(addressPanel.getComBoxAddress().getSelectedItem().toString()), addressPanel.getTxtStreet().getText(),
                    Integer.parseInt(addressPanel.getTxtHouseNumber().getText()), phones);
            
            Employee emp = null;
            switch (title) {
                case "Add Agent":
                    emp = new Agent(employeeNumber, firstName, lastName, birthDate, startWorkingDate, password, address);                   
                    break;
                case "Add Flight Attendant":
                    boolean isHerald = cBoxIsHerald.isSelected();
                    emp = new FlightAttendant(employeeNumber, firstName, lastName, birthDate, startWorkingDate, password, address, isHerald);
                    break;
                case "Add Pilot":
                    E_LicenseType type = E_LicenseType.valueOf(cbLicenseType.getSelectedItem().toString());
                    emp  = new Pilot(employeeNumber, firstName, lastName, birthDate, startWorkingDate, password, address, type);
                    break;
            }
            if(emp != null)
            try {
                MainClass.getIfly().addEmployee(emp);
                JOptionPane.showInternalConfirmDialog(this, "Saved changes",
                        "Confirmation", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                setIflyState(false);
            } catch (AddEmployeeExeption ex) {
              JOptionPane.showInternalConfirmDialog(this,
                        ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
            
        }

    }//GEN-LAST:event_btnOkActionPerformed

    private void jdBirthDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdBirthDatePropertyChange
        // TODO add your handling code here:
        
        if ("date".equals(evt.getPropertyName())) {
            String s = evt.getPropertyName();

            Date d;
            d = (Date) evt.getNewValue();

            jdStartWorkDate.setDate(d);
            jdStartWorkDate.setMinSelectableDate(d);

        }
        
    }//GEN-LAST:event_jdBirthDatePropertyChange

    //private void setEmpTypeChoices
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.AddressPanel addressPanel;
    private javax.swing.JButton btnOk;
    private javax.swing.JCheckBox cBoxIsHerald;
    private javax.swing.JComboBox cbLicenseType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser jdBirthDate;
    private com.toedter.calendar.JDateChooser jdStartWorkDate;
    private javax.swing.JLabel lblBdateErr;
    private javax.swing.JLabel lblEmpNumberErr;
    private javax.swing.JLabel lblFnameErr;
    private javax.swing.JLabel lblLNameErr;
    private javax.swing.JLabel lblLicensetype;
    private javax.swing.JLabel lblPasswordErr;
    private javax.swing.JLabel lblStartWorkingDateErr;
    private javax.swing.JPanel panelInfo;
    private gui.PhonePanel phonePanel;
    private javax.swing.JTextField txtEmpNumber;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    private void setEmpChoice(String title) {
        cBoxIsHerald.setVisible(false);

        cbLicenseType.setVisible(false);
        lblLicensetype.setVisible(false);
        switch (title) {
            case "Add Flight Attendant":
                cBoxIsHerald.setVisible(true);
                break;
            case "Add Pilot":
                cbLicenseType.setVisible(true);
                lblLicensetype.setVisible(true);
                fillLicenseType();
                break;
        }
    }

    private void fillLicenseType() {
        E_LicenseType[] types = utils.E_LicenseType.values();

        ComboItem[] items = new ComboItem[types.length];

        for (int i = 0; i < types.length; i++) {

            items[i] = new ComboItem(types[i].name(), types[i].name());
        }

        cbLicenseType.setModel(new javax.swing.DefaultComboBoxModel(items));
        cbLicenseType.setSelectedIndex(0);
    }

}
