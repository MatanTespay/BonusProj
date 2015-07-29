/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static init.MainClass.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static utils.Constants.EDIT_MODE;
import utils.HelperClass;
import utils.Queries;

/**
 *
 * @author asus
 */
public class CardLengths extends MyInternalFrame {

    private Short length;
    private String description;
    private char sign = '1';
    private String period;

    /**
     * Creates new form CardLength
     *
     * @param title
     * @param type
     */
    public CardLengths(String title, String type) {
        super(title, type);
        setMode(EDIT_MODE);
        initComponents();
        fillLengths();
        tfDescription.setEnabled(false);

        JSpinner.NumberEditor lengthEditor = (JSpinner.NumberEditor) spnLength.getEditor();
        JTextField lengthTextField = lengthEditor.getTextField();
        lengthTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    length = Short.parseShort(lengthTextField.getText());
                } catch (NumberFormatException ex) {
                    length = null;
                }
                updateDescription();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    length = Short.parseShort(lengthTextField.getText());
                } catch (NumberFormatException ex) {
                    length = null;
                }
                updateDescription();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        tblLengths.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isRowSelected = tblLengths.getSelectedRow() != -1;
                btnRemove.setEnabled(isRowSelected);
                btnUpdate.setEnabled(isRowSelected);

                if (isRowSelected) {
                    int selectedRow = tblLengths.getSelectedRow();
                    tfValueToUpdate.setText(tblLengths.getModel().getValueAt(selectedRow, 2).toString());
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pExistingLengths = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLengths = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        tfValueToUpdate = new javax.swing.JTextField();
        pAddLength = new javax.swing.JPanel();
        lblLength = new javax.swing.JLabel();
        cmbSign = new javax.swing.JComboBox();
        lblLength1 = new javax.swing.JLabel();
        spnLength = new javax.swing.JSpinner();
        cmbPeriod = new javax.swing.JComboBox();
        lblDescription = new javax.swing.JLabel();
        tfDescription = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();

        pExistingLengths.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Lengths"), "Existing lengths"));

        tblLengths.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLengths);

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pExistingLengthsLayout = new javax.swing.GroupLayout(pExistingLengths);
        pExistingLengths.setLayout(pExistingLengthsLayout);
        pExistingLengthsLayout.setHorizontalGroup(
            pExistingLengthsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExistingLengthsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pExistingLengthsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pExistingLengthsLayout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(tfValueToUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)))
                .addContainerGap())
        );
        pExistingLengthsLayout.setVerticalGroup(
            pExistingLengthsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExistingLengthsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pExistingLengthsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(btnUpdate)
                    .addComponent(tfValueToUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pAddLength.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Length"));

        lblLength.setText("Sign");

        cmbSign.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
        cmbSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSignActionPerformed(evt);
            }
        });

        lblLength1.setText("Length");

        spnLength.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)1), Short.valueOf((short)1), null, Short.valueOf((short)1)));

        cmbPeriod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "Week", "Month", "Year" }));
        cmbPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeriodActionPerformed(evt);
            }
        });

        lblDescription.setText("Description");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pAddLengthLayout = new javax.swing.GroupLayout(pAddLength);
        pAddLength.setLayout(pAddLengthLayout);
        pAddLengthLayout.setHorizontalGroup(
            pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddLengthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pAddLengthLayout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfDescription))
                    .addGroup(pAddLengthLayout.createSequentialGroup()
                        .addGroup(pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLength)
                            .addComponent(lblLength1))
                        .addGap(39, 39, 39)
                        .addGroup(pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnLength)
                            .addComponent(cmbSign, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddLengthLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pAddLengthLayout.setVerticalGroup(
            pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddLengthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLength))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLength1)
                    .addGroup(pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pAddLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescription)
                    .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pAddLength, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pExistingLengths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pAddLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pExistingLengths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        try {

            int choice = JOptionPane.showInternalOptionDialog(this,
                    "Changing existing lengths may cause consistency issues!\n"
                    + "Are you sure you want to proceed?",
                    "Bummer!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);
            if (choice == JOptionPane.NO_OPTION) {
                return;
            }

            PreparedStatement st = con.prepareStatement(Queries.INSERT_LENGTH);
            st.setString(1, String.valueOf(sign));
            st.setString(2, description);
            st.executeUpdate();

            fillLengths();

        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 2627:
                    if (e.getMessage().contains("The duplicate key value is (2)")) {
                        JOptionPane.showInternalMessageDialog(this,
                                "This sign is already in use! Pick another one",
                                "Bummer!",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (e.getMessage().contains("The duplicate key value is (1)")) {
                        JOptionPane.showInternalMessageDialog(this,
                                "This length is already in use! Pick another one",
                                "Bummer!",
                                JOptionPane.ERROR_MESSAGE);
                    }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed

        int choice = JOptionPane.showInternalOptionDialog(this,
                "Changing lengths may cause consistency problems!\n"
                + "Are you sure you want to proceed?",
                "Bummer!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, null, null);
        if (choice == JOptionPane.NO_OPTION) {
            return;
        }

        PreparedStatement st;

        try {
            char selectedSign = (tblLengths.getModel().getValueAt(
                    tblLengths.getSelectedRow(), 0)).toString().charAt(0);
            st = con.prepareStatement(Queries.DELETE_LENGTH);
            st.setString(1, String.valueOf(selectedSign));
            st.executeUpdate();

            fillLengths();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 547:
                    JOptionPane.showInternalMessageDialog(this,
                            "This length can not be deleted since\n"
                            + "it hase already been in use!",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cmbSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSignActionPerformed
        this.sign = cmbSign.getSelectedItem().toString().charAt(0);
    }//GEN-LAST:event_cmbSignActionPerformed

    private void cmbPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeriodActionPerformed
        this.period = cmbPeriod.getSelectedItem().toString();
        updateDescription();
    }//GEN-LAST:event_cmbPeriodActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {

            int choice = JOptionPane.showInternalOptionDialog(this,
                    "Changing existing lengths may cause consistency issues!\n"
                    + "Are you sure you want to proceed?",
                    "Bummer!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);
            if (choice == JOptionPane.NO_OPTION) {
                return;
            }

            char selectedLength = tblLengths.getModel().getValueAt(
                    tblLengths.getSelectedRow(), 0).toString().charAt(0);

            PreparedStatement st = con.prepareStatement(Queries.UPDATE_LENGTH);
            st.setString(1, tfValueToUpdate.getText());
            st.setString(2, String.valueOf(selectedLength));
            st.executeUpdate();

            fillLengths();

        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 2627:
                    JOptionPane.showInternalMessageDialog(this,
                            "This length is already in use! Pick another one",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbPeriod;
    private javax.swing.JComboBox cmbSign;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblLength;
    private javax.swing.JLabel lblLength1;
    private javax.swing.JPanel pAddLength;
    private javax.swing.JPanel pExistingLengths;
    private javax.swing.JSpinner spnLength;
    private javax.swing.JTable tblLengths;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfValueToUpdate;
    // End of variables declaration//GEN-END:variables

    private void fillLengths() {

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(Queries.SELECT_ALL_LENGTHS);
            tblLengths.setModel(HelperClass.buildTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(CardLengths.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDescription() {
        String num = spnLength.getValue().toString();
        boolean isOne = (num.equals("1"));
        String period = cmbPeriod.getSelectedItem().toString();

        tfDescription.setText(spnLength.getValue().toString() + " " + period + ((isOne) ? "" : "s"));

        btnAdd.setEnabled(isOkToAdd());
    }

    private boolean isOkToAdd() {
        return true;
    }

}
