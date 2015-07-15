/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;

import javax.swing.JOptionPane;

import utils.HelperClass;
import core.Order;
import exceptions.CancelOrderExeption;


/**
 *
  *The class represents the screen which enable users to remove {@link core.Order} from {@link init.IFly}
 
 * @author Matan
 */
public class RemoveOrder extends MyInternalFrame {

    Order o;

    /**
     * Creates new form CancelOrder
     *
     * @param title
     * @param type
     */
    public RemoveOrder(String title, String type) {
        super(title, type, null);
        initComponents();
        fillOrders();
        btnOk.setToolTipText(utils.Constants.saveButtonToolTip);
        setOrderInfo();
    }

    /**
     * set order information
     */
    private void setOrderInfo() {
        if (cbOrders.getModel().getSize() > 0) {
            o = MainClass.getIfly().getOrderById(Integer.parseInt(((ComboItem) cbOrders.getSelectedItem()).getValue()));
            if (o != null) {
                cName.setText(o.getResponsibleCustomer().getFirstName() + " " + o.getResponsibleCustomer().getLastName());
                aName.setText(o.getAgent().getFirstName() + " " + o.getAgent().getLastName());
                oCost.setText(String.format("%.2f", o.getOrderCost()));
            }

        } else {
            cName.setVisible(false);
            aName.setVisible(false);
            oCost.setVisible(false);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbOrders = new javax.swing.JComboBox();
        btnOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cName = new javax.swing.JLabel();
        aName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        oCost = new javax.swing.JLabel();

        jLabel1.setText("Select order:");

        cbOrders.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOrdersActionPerformed(evt);
            }
        });

        btnOk.setText("Submit");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel2.setText("Customer:");

        jLabel3.setText("Agent:");

        cName.setText("cName");

        aName.setText("aName");

        jLabel4.setText("Order cost:");

        oCost.setText("oCost");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oCost)
                            .addComponent(aName)
                            .addComponent(cName)
                            .addComponent(cbOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 146, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(oCost))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(aName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        int OrderToRemove;
        if (cbOrders.getModel().getSize() <= 0) {
            shoMissingDataMsg("Orders are missing");
            return;
        }

        OrderToRemove = Integer.parseInt(((ComboItem) cbOrders.getSelectedItem()).getValue());
        try {
            MainClass.getIfly().cancelOrder(OrderToRemove);
            JOptionPane.showInternalConfirmDialog(this, "Saved changes", "Confirmation", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            HelperClass.fillOrdersCombo(this, cbOrders, null);
            setIflyState(false);
        } catch (CancelOrderExeption ex) {
            JOptionPane.showInternalConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void cbOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOrdersActionPerformed
        // TODO add your handling code here:
        setOrderInfo();
    }//GEN-LAST:event_cbOrdersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aName;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel cName;
    private javax.swing.JComboBox cbOrders;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel oCost;
    // End of variables declaration//GEN-END:variables

    /**
     * fill {@link #cbOrders}}
     */
    private void fillOrders() {
        HelperClass.fillOrdersCombo(this, cbOrders, null);
    }
}
