/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;
import init.MyTableModel;

import java.util.ArrayList;

import javax.swing.ListSelectionModel;

import utils.HelperClass;
import core.Customer;

/**
 *The class represents the screen which enable users to watch {@link core.FlightOrder.FlightTicket} for  {@link core.Customer} in any  {@link core.Flight}}  

* @author Matan
*/
public class ViewTickets extends MyInternalFrame {

    String[] column = {"Order #",
        "Flight #",
        "Source",
        "Destination",
        "departure",
        "arrival",
        "Row",
        "Seat",
        "Class",
        "Ticket Cost"};
    /**
     * Creates new form ViewTickets
     *
     * @param title
     * @param type
     */
    public ViewTickets(String title, String type) {
        super(title, type);
        //initComponents();        
        initCustComponents();
        if (selectedUserType.equals("Customer")) {
            initCutomerComponents();
            finishInitComponents();
            selectedCustomer = MainClass.getUserData();
        }
        else{
            initNonCustomerComponents();
            finishInitComponents();
            HelperClass.fillCustomersCombo(this, cbCustomers, false);
        }
        
        fillTable();
        //lblNotickets.setVisible(false);
        
        
        
    }

    /**
     * set the window visibility to a customer view
     */
    private void initCutomerComponents() {
          lblNotickets = new javax.swing.JLabel();
         lblNotickets.setText(" has no tickets to show");
         
        javax.swing.GroupLayout panelAdminLayout = new javax.swing.GroupLayout(panelAdmin);
        panelAdmin.setLayout(panelAdminLayout);
        panelAdminLayout.setHorizontalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                 .addComponent(lblNotickets)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        cbCustomers.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillTable();
            }
        });
        
        panelAdminLayout.setVerticalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                 .addComponent(lblNotickets)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    /**
     * set the window visibility to a non-customer view
     */
    private void initNonCustomerComponents() {
      javax.swing.GroupLayout panelAdminLayout = new javax.swing.GroupLayout(panelAdmin);
        panelAdmin.setLayout(panelAdminLayout);
        panelAdminLayout.setHorizontalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAdminLayout.createSequentialGroup()
                        .addComponent(lblCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(cbCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNotickets))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        cbCustomers.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillTable();
            }
        });
        
        panelAdminLayout.setVerticalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomer)
                    .addComponent(cbCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblNotickets)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * initiate controls
     */
    private void initCustComponents() {
      panelAdmin = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        cbCustomers = new javax.swing.JComboBox();
        lblNotickets = new javax.swing.JLabel();
         lblNotickets.setText(" has no tickets to show");
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTickets = new javax.swing.JTable();

        lblCustomer.setText("Select customer: ");

       
    }
    
    /**
     * finishes initiate of window controls
     */
    private void finishInitComponents(){
        
       tblTickets.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tblTickets);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }

    
    
    /**
     * fill tickest table information
     */
    private void fillTable(){
        if(MainClass.getIfly() == null){
            return;
        }
        Customer c= null;
        StringBuilder sb = new StringBuilder();
        int custId;
        if (!selectedUserType.equals("Customer")) {
            if(cbCustomers.getModel().getSize() <= 0) {   
                lblNotickets.setText("Customers are missing");
                lblNotickets.setVisible(true);
                return;
            }
            
            custId = Integer.parseInt((String)((ComboItem)cbCustomers.getSelectedItem()).getKey());
            sb.append(((ComboItem)cbCustomers.getSelectedItem()).getLabel()).append(" has no tickets to show");
        }
        else{
            if(selectedCustomer == null){
                return;
            }
            c = ((Customer)selectedCustomer.getValue());
            custId = c.getPassportNumber();
            sb.append(c.getFirstName()).append(" ").append(c.getLastName()).append(" has no tickets to show");                                 
        }
        
        MyTableModel model = new MyTableModel(column);
        
        ArrayList<Object[]> ticketsInfoCustomer = MainClass.getIfly().getTicketsInfoCustomer(custId);
        if (ticketsInfoCustomer.size() > 0) {
            
            model = new MyTableModel(column,ticketsInfoCustomer);
            lblNotickets.setVisible(false);
        }
        else{
           lblNotickets.setText(sb.toString());
            lblNotickets.setVisible(true);
        }
        
        tblTickets.setModel(model);
        tblTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        HelperClass.aligmentCell(tblTickets);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAdmin = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        cbCustomers = new javax.swing.JComboBox();
        lblNotickets = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTickets = new javax.swing.JTable();

        lblCustomer.setText("Select customer: ");

        lblNotickets.setText(" has no tickets to show");

        javax.swing.GroupLayout panelAdminLayout = new javax.swing.GroupLayout(panelAdmin);
        panelAdmin.setLayout(panelAdminLayout);
        panelAdminLayout.setHorizontalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAdminLayout.createSequentialGroup()
                        .addComponent(lblCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(cbCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNotickets))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAdminLayout.setVerticalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomer)
                    .addComponent(cbCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblNotickets)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tblTickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblTickets.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tblTickets);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbCustomers;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblNotickets;
    private javax.swing.JPanel panelAdmin;
    private javax.swing.JTable tblTickets;
    // End of variables declaration//GEN-END:variables
}
