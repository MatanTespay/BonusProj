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
public class Deposits extends MyInternalFrame {

    private Short addFromYear;
    private Short addToYear;
    private Short addPrice;

    private Short updateFromYear;
    private Short updateToYear;
    private Short updatePrice;

    /**
     * Creates new form GeneralParameters
     *
     * @param title
     * @param type
     */
    public Deposits(String title, String type) {
        super(title, type);
        setMode(EDIT_MODE);
        initComponents();

        btnDelete.setEnabled(false);

        // set document listeners
        JSpinner addToYearSpinner = (JSpinner) ychAddTo.getSpinner();
        JTextField addToYearText = (JTextField) addToYearSpinner.getEditor();
        addToYearText.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    addToYear = Short.valueOf(addToYearText.getText());
                } catch (NumberFormatException ex) {
                    addToYear = null;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    addToYear = Short.valueOf(addToYearText.getText());
                } catch (NumberFormatException ex) {
                    addToYear = null;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        JSpinner addFromYearSpinner = (JSpinner) ychAddFrom.getSpinner();
        JTextField addFromYearText = (JTextField) addFromYearSpinner.getEditor();
        addFromYearText.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    addFromYear = Short.valueOf(addFromYearText.getText());
                    ychAddTo.setMinimum(addFromYear);
                } catch (NumberFormatException ex) {
                    addFromYear = null;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    addFromYear = Short.valueOf(addFromYearText.getText());
                    ychAddTo.setMinimum(addFromYear);
                } catch (NumberFormatException ex) {
                    addFromYear = null;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        JSpinner updateFromYearSpinner = (JSpinner) ychUpdateFrom.getSpinner();
        JTextField updateFromYearText = (JTextField) updateFromYearSpinner.getEditor();
        updateFromYearText.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    updateFromYear = Short.valueOf(updateFromYearText.getText());
                    ychUpdateTo.setMinimum(updateFromYear);
                } catch (NumberFormatException ex) {
                    updateFromYear = null;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    updateFromYear = Short.valueOf(updateFromYearText.getText());
                    ychUpdateTo.setMinimum(updateFromYear);
                } catch (NumberFormatException ex) {
                    updateFromYear = null;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        JSpinner updateToYearSpinner = (JSpinner) ychUpdateTo.getSpinner();
        JTextField updateToYearText = (JTextField) updateToYearSpinner.getEditor();
        updateToYearText.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    updateToYear = Short.valueOf(updateToYearText.getText());
                } catch (NumberFormatException ex) {
                    updateToYear = null;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    updateToYear = Short.valueOf(updateToYearText.getText());
                } catch (NumberFormatException ex) {
                    updateToYear = null;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        JSpinner.NumberEditor addPriceEditor = (JSpinner.NumberEditor) spnAddPrice.getEditor();
        JTextField addPriceTextField = addPriceEditor.getTextField();
        addPriceTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    addPrice = Short.parseShort(addPriceTextField.getText());
                } catch (NumberFormatException ex) {
                    addPrice = null;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    addPrice = Short.parseShort(addPriceTextField.getText());
                } catch (NumberFormatException ex) {
                    addPrice = null;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        JSpinner.NumberEditor updatePriceEditor = (JSpinner.NumberEditor) spnUpdatePrice.getEditor();
        JTextField updatePriceTextField = updatePriceEditor.getTextField();
        updatePriceTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    updatePrice = Short.parseShort(updatePriceTextField.getText());
                } catch (NumberFormatException ex) {
                    updatePrice = null;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    updatePrice = Short.parseShort(updatePriceTextField.getText());
                } catch (NumberFormatException ex) {
                    updatePrice = null;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        // set year choosers
        initYearChoosers();

        // set table model and listener
        tblDeposits.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isRowSelected = tblDeposits.getSelectedRow() != -1;
                btnDelete.setEnabled(isRowSelected);
                btnUpdate.setEnabled(isRowSelected);

                if (isRowSelected) {
                    int selectedRow = tblDeposits.getSelectedRow();
                    Short selectedFrom = Short.parseShort(tblDeposits.getValueAt(selectedRow, 0).toString());
                    Short selectedTo = Short.parseShort(tblDeposits.getValueAt(selectedRow, 1).toString());

                    ychUpdateFrom.setYear(selectedFrom);
                    ychUpdateTo.setYear(selectedTo);
                }
            }
        });
        fillDeposits();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pAddDeposit = new javax.swing.JPanel();
        lblAddFrom = new javax.swing.JLabel();
        ychAddFrom = new com.toedter.calendar.JYearChooser();
        ychAddTo = new com.toedter.calendar.JYearChooser();
        btnAdd = new javax.swing.JButton();
        lblAddIs = new javax.swing.JLabel();
        lblAddTo = new javax.swing.JLabel();
        spnAddPrice = new javax.swing.JSpinner();
        pDepositHistory = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDeposits = new javax.swing.JTable();
        pEditDeposit = new javax.swing.JPanel();
        lblUpdateFrom = new javax.swing.JLabel();
        ychUpdateFrom = new com.toedter.calendar.JYearChooser();
        ychUpdateTo = new com.toedter.calendar.JYearChooser();
        btnUpdate = new javax.swing.JButton();
        lblUpdateIs = new javax.swing.JLabel();
        lblUpdateTo = new javax.swing.JLabel();
        spnUpdatePrice = new javax.swing.JSpinner();
        btnDelete = new javax.swing.JButton();

        pAddDeposit.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Deposit"));
        pAddDeposit.setName(""); // NOI18N

        lblAddFrom.setText("From");

        ychAddFrom.setEndYear(Short.MAX_VALUE);
        ychAddFrom.setMaximum(Short.MAX_VALUE);

        ychAddTo.setEndYear(Short.MAX_VALUE);
        ychAddTo.setMaximum(Short.MAX_VALUE);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblAddIs.setText("is");

        lblAddTo.setText("to");

        spnAddPrice.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)32767), Short.valueOf((short)1)));

        javax.swing.GroupLayout pAddDepositLayout = new javax.swing.GroupLayout(pAddDeposit);
        pAddDeposit.setLayout(pAddDepositLayout);
        pAddDepositLayout.setHorizontalGroup(
            pAddDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddDepositLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddFrom)
                .addGap(12, 12, 12)
                .addComponent(ychAddFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAddTo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ychAddTo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAddIs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pAddDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnAddPrice))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pAddDepositLayout.setVerticalGroup(
            pAddDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddDepositLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pAddDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pAddDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAddIs)
                        .addComponent(spnAddPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAddTo)
                    .addComponent(lblAddFrom)
                    .addComponent(ychAddTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ychAddFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pDepositHistory.setBorder(javax.swing.BorderFactory.createTitledBorder("Deposit History"));

        tblDeposits.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDeposits);

        javax.swing.GroupLayout pDepositHistoryLayout = new javax.swing.GroupLayout(pDepositHistory);
        pDepositHistory.setLayout(pDepositHistoryLayout);
        pDepositHistoryLayout.setHorizontalGroup(
            pDepositHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDepositHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addContainerGap())
        );
        pDepositHistoryLayout.setVerticalGroup(
            pDepositHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDepositHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pEditDeposit.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Deposit"));
        pEditDeposit.setName(""); // NOI18N

        lblUpdateFrom.setText("From");

        ychUpdateFrom.setEndYear(Short.MAX_VALUE);
        ychUpdateFrom.setMaximum(Short.MAX_VALUE);

        ychUpdateTo.setEndYear(Short.MAX_VALUE);
        ychUpdateTo.setMaximum(Short.MAX_VALUE);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblUpdateIs.setText("is");

        lblUpdateTo.setText("to");

        spnUpdatePrice.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)32767), Short.valueOf((short)1)));

        btnDelete.setText("Remove");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pEditDepositLayout = new javax.swing.GroupLayout(pEditDeposit);
        pEditDeposit.setLayout(pEditDepositLayout);
        pEditDepositLayout.setHorizontalGroup(
            pEditDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditDepositLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pEditDepositLayout.createSequentialGroup()
                        .addComponent(lblUpdateFrom)
                        .addGap(12, 12, 12)
                        .addComponent(ychUpdateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUpdateTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ychUpdateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUpdateIs))
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pEditDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnUpdatePrice))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pEditDepositLayout.setVerticalGroup(
            pEditDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditDepositLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pEditDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pEditDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUpdateIs)
                        .addComponent(spnUpdatePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUpdateTo)
                    .addComponent(lblUpdateFrom)
                    .addComponent(ychUpdateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ychUpdateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pEditDepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDepositHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pAddDeposit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pEditDeposit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pDepositHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pAddDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pEditDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        int currentYear = new java.util.Date().getYear() + 1900;
        if (updateToYear < currentYear) {
            int choice = JOptionPane.showInternalOptionDialog(this,
                    "Removing a deposit from the past may result consistency issues.\n"
                    + "Are yaur sure you want to proceed?",
                    "Bummer!", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);
            if (choice == JOptionPane.NO_OPTION) {
                return;
            }
        }
        try {

            PreparedStatement st = con.prepareStatement(Queries.DELETE_DEPOSIT);
            st.setInt(1, updateFromYear);
            st.setInt(2, updateToYear);
            st.executeUpdate();

            initYearChoosers();
            fillDeposits();

            JOptionPane.showInternalMessageDialog(this,
                    "Deposit was removed successfully.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(Deposits.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        try {

            PreparedStatement st = con.prepareStatement(Queries.INSERT_DEPOSIT);
            st.setInt(1, addFromYear);
            st.setInt(2, addToYear);
            st.setDouble(3, addPrice);
            st.executeUpdate();

            initYearChoosers();
            fillDeposits();

            JOptionPane.showInternalMessageDialog(this,
                    "Deposit was added successfully.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        try {

            PreparedStatement st = con.prepareStatement(Queries.UPDATE_DEPOSIT);
            st.setShort(1, updatePrice);
            st.setShort(2, updateFromYear);
            st.setShort(3, updateToYear);
            st.executeUpdate();

            fillDeposits();

            JOptionPane.showInternalMessageDialog(this,
                    "Deposit was updated successfully.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(Deposits.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddFrom;
    private javax.swing.JLabel lblAddIs;
    private javax.swing.JLabel lblAddTo;
    private javax.swing.JLabel lblUpdateFrom;
    private javax.swing.JLabel lblUpdateIs;
    private javax.swing.JLabel lblUpdateTo;
    private javax.swing.JPanel pAddDeposit;
    private javax.swing.JPanel pDepositHistory;
    private javax.swing.JPanel pEditDeposit;
    private javax.swing.JSpinner spnAddPrice;
    private javax.swing.JSpinner spnUpdatePrice;
    private javax.swing.JTable tblDeposits;
    private com.toedter.calendar.JYearChooser ychAddFrom;
    private com.toedter.calendar.JYearChooser ychAddTo;
    private com.toedter.calendar.JYearChooser ychUpdateFrom;
    private com.toedter.calendar.JYearChooser ychUpdateTo;
    // End of variables declaration//GEN-END:variables

    private void fillDeposits() {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(Queries.SELECT_ALL_DEPOSITS);
            tblDeposits.setModel(HelperClass.buildTableModel(rs));
        } catch (SQLException e) {

        }
    }

    private void initYearChoosers() {
        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(Queries.NEXT_DEPOSIT_YEAR);
            rs.next();
            int nextDepositYear = rs.getInt("next year");

            ychAddFrom.setStartYear(nextDepositYear);

            ychAddFrom.setYear(nextDepositYear);
            ychAddTo.setYear(nextDepositYear + 1);

            ychAddFrom.setEnabled(false);
            ychUpdateFrom.setEnabled(false);
            ychUpdateTo.setEnabled(false);

        } catch (SQLException e) {
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }
}
