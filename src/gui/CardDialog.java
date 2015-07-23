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
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matan
 */
public class CardDialog extends MyInternalFrame {

    int cardNumber;
    Date purchaseDate;
    MainWindow mainWindow;
    /**
     * Creates new form CardDialog
     */
    public CardDialog(String title, String type, MainWindow parent) {
        super(title, type);
        initComponents();
        this.mainWindow = parent;
        cmbPDate.setEnabled(false);
        fillCmbCard();
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
        bntOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        lblNumber.setText("Caed Number:");

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

        bntOK.setText("OK");
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNumber)
                        .addGap(18, 18, 18)
                        .addComponent(cmbCard, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addGap(18, 18, 18)
                        .addComponent(cmbPDate, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber)
                    .addComponent(cmbCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(cmbPDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOK)
                    .addComponent(btnCancel))
                .addContainerGap(38, Short.MAX_VALUE))
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

    private void cmbPDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPDateActionPerformed
        // TODO add your handling code here:
        if (cmbPDate.getSelectedItem() == null) {
            return;
        }
        ComboItem purchaseDateItem = (ComboItem) cmbPDate.getSelectedItem();
        this.purchaseDate = (Date) purchaseDateItem.getKey();
        
    }//GEN-LAST:event_cmbPDateActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        // TODO add your handling code here:
        if (this.purchaseDate != null && this.cardNumber > 0) {

            this.setVisible(false);
            mainWindow.desktop.remove(this);
            
            Card c = new Card(title, getSelectedUserType(),this.cardNumber,this.purchaseDate);
            mainWindow.createFrame(c);
        }
    }//GEN-LAST:event_bntOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
        mainWindow.desktop.remove(this);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void fillCmbCard() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery("SELECT C.number FROM tblCard C");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("number"), rs.getString("number")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbCard.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
                cmbCard.setSelectedIndex(0);

                ComboItem numberItem = (ComboItem) cmbCard.getSelectedItem();
                this.cardNumber = Integer.parseInt(numberItem.getKey().toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillcmbPDate(int cardNumber) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("SELECT C.purchaseDate FROM tblCard As C WHERE C.number = ?");
            st.setInt(1, cardNumber);
            rs = st.executeQuery();

            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getDate("purchaseDate"), rs.getDate("purchaseDate").toString()));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbPDate.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));

                ComboItem purchaseDateItem = (ComboItem) cmbPDate.getSelectedItem();
                this.purchaseDate = (Date) purchaseDateItem.getKey();
                cmbPDate.setSelectedIndex(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntOK;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox cmbCard;
    private javax.swing.JComboBox cmbPDate;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNumber;
    // End of variables declaration//GEN-END:variables
}
