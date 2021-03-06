/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import static init.MainClass.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Queries;

/**
 *
 * @author Matan
 */
public class ActivityDialog extends MyInternalFrame {

    int cardNumber;
    Timestamp purchaseDate;
    Timestamp activityDate;
    MainWindow mainWindow;

    /**
     * Creates new form ActivityDialog
     *
     * @param title
     * @param type
     * @param parent
     */
    public ActivityDialog(String title, String type, MainWindow parent) {
        super(title, type);
        initComponents();
        this.mainWindow = parent;
        cmbPDate.setEnabled(false);
        fillCmbCard();
        
        if (!(cmbCard.getItemCount() >0)) {
         btnOK.setEnabled(false);         
            
        }
    }

    private void fillCmbCard() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery(Queries.SELECT_CARD_NUMBERS_WITH_ACTIVITIES);
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("number"), rs.getString("number")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbCard.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
                if (cmbCard.getModel().getSize() > 0) {
                    cmbCard.setSelectedIndex(0);
                    ComboItem numberItem = (ComboItem) cmbCard.getSelectedItem();
                    this.cardNumber = Integer.parseInt(numberItem.getKey().toString());
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillcmbPDate(int cardNumber) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement(Queries.SELECT_PURCHASE_DATES_OF_CARD);
            st.setInt(1, cardNumber);
            rs = st.executeQuery();

            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getTimestamp("purchaseDate"), rs.getTimestamp("purchaseDate").toString()));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbPDate.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));

                ComboItem purchaseDateItem = (ComboItem) cmbPDate.getSelectedItem();
                this.purchaseDate = (Timestamp) purchaseDateItem.getKey();
                if (cmbPDate.getModel().getSize() > 0) {
                    cmbPDate.setSelectedIndex(0);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbAct() {
        PreparedStatement s;
        ResultSet rs;
        try {
            s = con.prepareCall(Queries.SELECT_ACTIVITIES_OF_CARD);

            s.setInt(1, cardNumber);
            s.setString(2, new Timestamp(this.purchaseDate.getTime()).toString());

            rs = s.executeQuery();
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getTimestamp("activityDate"), rs.getTimestamp("activityDate").toString()));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbActDate.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
                if (cmbActDate.getModel().getSize() > 0) {
                    cmbActDate.setSelectedIndex(0);

                    ComboItem act = (ComboItem) cmbActDate.getSelectedItem();
                    this.activityDate = (Timestamp) act.getKey();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDialog.class.getName()).log(Level.SEVERE, null, ex);
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

        lblNumber = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        cmbCard = new javax.swing.JComboBox();
        cmbPDate = new javax.swing.JComboBox();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cmbActDate = new javax.swing.JComboBox();
        lblActivity = new javax.swing.JLabel();

        lblNumber.setText("Card Number:");

        lblDate.setText("Purchase Date");

        cmbCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCardActionPerformed(evt);
            }
        });

        cmbPDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPDateActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cmbActDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbActDateActionPerformed(evt);
            }
        });

        lblActivity.setText("Activity Date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNumber)
                        .addGap(18, 18, 18)
                        .addComponent(cmbCard, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate)
                            .addComponent(lblActivity))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbActDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber)
                    .addComponent(cmbCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(cmbPDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbActDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActivity))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnCancel))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCardActionPerformed
        // TODO add your handling code here:
        try {
            ComboItem cardItem = (ComboItem) cmbCard.getSelectedItem();
            cardNumber = Integer.parseInt(cardItem.getKey().toString());
            cmbPDate.setEnabled(true);
            fillcmbPDate(cardNumber);
        } catch (NullPointerException ex) {
            // no item is chosen
            cmbPDate.setEnabled(false);
        }
    }//GEN-LAST:event_cmbCardActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        if (this.purchaseDate != null && this.activityDate != null && this.cardNumber > 0) {

            this.setVisible(false);
            mainWindow.getDesktop().remove(this);
            Activity act = new Activity(title, getSelectedUserType(), this.cardNumber, this.purchaseDate, this.activityDate);
            mainWindow.createFrame(act);
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void cmbPDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPDateActionPerformed
        // TODO add your handling code here:
        if (cmbPDate.getSelectedItem() == null) {
            return;
        }
        ComboItem purchaseDateItem = (ComboItem) cmbPDate.getSelectedItem();
        this.purchaseDate = (Timestamp) purchaseDateItem.getKey();
        fillCmbAct();
    }//GEN-LAST:event_cmbPDateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
        mainWindow.getDesktop().remove(this);

    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmbActDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbActDateActionPerformed
        // TODO add your handling code here:
        ComboItem cardItem = (ComboItem) cmbActDate.getSelectedItem();
        this.activityDate = (Timestamp) cardItem.getKey();

    }//GEN-LAST:event_cmbActDateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cmbActDate;
    private javax.swing.JComboBox cmbCard;
    private javax.swing.JComboBox cmbPDate;
    private javax.swing.JLabel lblActivity;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNumber;
    // End of variables declaration//GEN-END:variables
}
