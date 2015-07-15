/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.AbstractMap;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import utils.HelperClass;
import core.Order;

/**
 *The class represents the screen which enable users to watch {@link core.Order} for  {@link core.Customer} or  {@link core.Agent} 

* @author Matan
*/
public class ViewOrders extends MyInternalFrame {

    AbstractMap.SimpleEntry<Object, ArrayList<Order>> data;
    String[] agentOrdercolumn = {"Order #",
        "<html><center>Responsible<br>Customer</html>",
        "Cost",
        "Is Paid"};
    String[] cutomerOrdercolumn = {"Order #",
        "<html><center>Agent<br>Name</html>",
        "Cost",
        "Is Paid"};

    String[] flightOrdercolumn = {"Flight #",
        "Order Number",
        "Total Cost"};
    private static int HEADER_HEIGHT = 32;
    OrdersTablePanel tablePanel;

    /**
     * Creates new form ViewOrders
     *
     * @param title
     * @param type
     */
    public ViewOrders(String title, String type) {
        super(title, type, null);
        if (selectedUserType.equals("Admin")) {
            initComponents();
            fillAgentCombo();
            fillCustomers();
            rbCust.setSelected(true);
            setControlState(combAgents, false);
            lblErr.setVisible(false);
        } else {
            initNonAdminComponents();
        }
        tablePanel = new OrdersTablePanel(this);
        tblScroller.setViewportView(tablePanel);
        tablePanel.clearTables(0);
        tablePanel.fillOrdersTable();

    }

    //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    public JComboBox getCombAgents() {
        return combAgents;
    }

    public JComboBox getCombCustomers() {
        return combCustomers;
    }

    public JRadioButton getRbAgent() {
        return rbAgent;
    }

    public JRadioButton getRbCust() {
        return rbCust;
    }

    public JLabel getLblErr() {
        return lblErr;
    }
//</editor-fold>  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.ButtonGroup();
        jpManager = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rbAgent = new javax.swing.JRadioButton();
        rbCust = new javax.swing.JRadioButton();
        jpAgentsSelect = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        combAgents = new javax.swing.JComboBox();
        jpCustSelect = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        combCustomers = new javax.swing.JComboBox();
        lblErr = new javax.swing.JLabel();
        tblScroller = new javax.swing.JScrollPane();

        jpManager.setPreferredSize(new java.awt.Dimension(576, 10));

        jLabel1.setText("Select Orders For: ");

        bg.add(rbAgent);
        rbAgent.setText("Agents");
        rbAgent.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAgentActionPerformed(evt);
            }
        });

        bg.add(rbCust);
        rbCust.setText("Customers");
        rbCust.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCustActionPerformed(evt);
            }
        });

        jLabel2.setText("Select agent:");

        combAgents.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                combAgentsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpAgentsSelectLayout = new javax.swing.GroupLayout(jpAgentsSelect);
        jpAgentsSelect.setLayout(jpAgentsSelectLayout);
        jpAgentsSelectLayout.setHorizontalGroup(
            jpAgentsSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAgentsSelectLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combAgents, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpAgentsSelectLayout.setVerticalGroup(
            jpAgentsSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAgentsSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAgentsSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combAgents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setText("Select customer:");

        combCustomers.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                combCustomersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpCustSelectLayout = new javax.swing.GroupLayout(jpCustSelect);
        jpCustSelect.setLayout(jpCustSelectLayout);
        jpCustSelectLayout.setHorizontalGroup(
            jpCustSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCustSelectLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(combCustomers, 0, 120, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jpCustSelectLayout.setVerticalGroup(
            jpCustSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCustSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCustSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpManagerLayout = new javax.swing.GroupLayout(jpManager);
        jpManager.setLayout(jpManagerLayout);
        jpManagerLayout.setHorizontalGroup(
            jpManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpManagerLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbCust)
                        .addGap(18, 18, 18)
                        .addComponent(rbAgent)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpManagerLayout.createSequentialGroup()
                        .addGroup(jpManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpManagerLayout.createSequentialGroup()
                                .addComponent(jpCustSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpAgentsSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblErr, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE))))
        );
        jpManagerLayout.setVerticalGroup(
            jpManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpManagerLayout.createSequentialGroup()
                .addGroup(jpManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rbAgent)
                    .addComponent(rbCust))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCustSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpAgentsSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblErr, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblScroller.setPreferredSize(new java.awt.Dimension(490, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpManager, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 260, Short.MAX_VALUE))
                    .addComponent(tblScroller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jpManager, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tblScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combCustomersActionPerformed
        // TODO add your handling code here:
        tablePanel.fillOrdersTable();
        tablePanel.clearTables(0);
    }//GEN-LAST:event_combCustomersActionPerformed

    private void combAgentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combAgentsActionPerformed
        // TODO add your handling code here:
        tablePanel.fillOrdersTable();
        tablePanel.clearTables(0);
    }//GEN-LAST:event_combAgentsActionPerformed

    private void rbCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCustActionPerformed
        // TODO add your handling code here:
        JRadioButton choice = (JRadioButton) evt.getSource();
        if (choice != null && choice.isSelected()) {
            setControlState(combAgents, false);
            setControlState(combCustomers, true);
            tablePanel.setIsAgentView(false);
            tablePanel.setIsCustView(true);
            tablePanel.fillOrdersTable();
            tablePanel.clearTables(0);
        }
    }//GEN-LAST:event_rbCustActionPerformed

    private void rbAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAgentActionPerformed
        // TODO add your handling code here:
        JRadioButton choice = (JRadioButton) evt.getSource();
        if (choice != null && choice.isSelected()) {
            setControlState(combCustomers, false);
            setControlState(combAgents, true);
            tablePanel.setIsAgentView(true);
            tablePanel.setIsCustView(false);
            tablePanel.fillOrdersTable();
            tablePanel.clearTables(0);
        }
    }//GEN-LAST:event_rbAgentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg;
    private javax.swing.JComboBox combAgents;
    private javax.swing.JComboBox combCustomers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jpAgentsSelect;
    private javax.swing.JPanel jpCustSelect;
    private javax.swing.JPanel jpManager;
    private javax.swing.JLabel lblErr;
    private javax.swing.JRadioButton rbAgent;
    private javax.swing.JRadioButton rbCust;
    private javax.swing.JScrollPane tblScroller;
    // End of variables declaration//GEN-END:variables

    private void setControlState(JComponent comp, boolean state) {
        comp.setEnabled(state);
    }
    
    

    /**
     * 
     * fill {@link #combAgents}}
     */
    private void fillAgentCombo() {
        HelperClass.fillAgentCombo(this, combAgents);
    }
    
    /**
     * 
     * fill {@link #combCustomers}}
     */

    private void fillCustomers() {
        HelperClass.fillCustomersCombo(this, combCustomers, false);
    }

    /**
     * 
     * set the window in non admin state
     */
    private void initNonAdminComponents() {
       	bg = new javax.swing.ButtonGroup();
        tblScroller = new javax.swing.JScrollPane();
        lblErr = new javax.swing.JLabel();

        tblScroller.setPreferredSize(new java.awt.Dimension(490, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblErr, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblErr, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tblScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }

}
