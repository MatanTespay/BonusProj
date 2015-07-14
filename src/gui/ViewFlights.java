/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;
import init.MyTableModel;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import utils.E_Airports;
import utils.HelperClass;
import core.Flight;

/**
 *The class represents the screen which enable users to search {@link core.Flight} and watch  its {@link core.FlightOrder.FlightTicket}

* @author Matan
*/
public class ViewFlights extends MyInternalFrame {

    String[] columns = {"flight #", "Cost", "Stops", "departure", "arrival"};
    int selectFlightNumber;
    MyTableModel tableModel = null;

    /**
     * Creates new form FlightView
     */
    public ViewFlights(String title, String type) {
        super(title, type);
        initComponents();
        fillCbAirports();
        lblNOFlights.setVisible(false);
        lblDatesErr.setVisible(false);
        btnTickets.setEnabled(false);
        if (selectedUserType.equals("Customer")) {
            //ckbFilterDates.setVisible(false);
            jDateTo.setMinSelectableDate(new Date());
            jdateFrom.setMinSelectableDate(new Date());
            jDateTo.setEnabled(true);
            jdateFrom.setEnabled(true);
            jDateTo.setEnabled(ckbFilterDates.isSelected());
            jdateFrom.setEnabled(ckbFilterDates.isSelected());
        } else {
            jDateTo.setEnabled(ckbFilterDates.isSelected());
            jdateFrom.setEnabled(ckbFilterDates.isSelected());
        }
        tableModel = new MyTableModel(columns);
        tblFlights.setModel(tableModel);
        ListSelectionModel selectionModel = tblFlights.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableRowSelection(tblFlights);
                }
            }
        });
    }

    /**
     * fired on table selection changes 
     * fill {@link #tblFlights}}
     
     * @param table
     */
    private void tableRowSelection(JTable table) {

        int numRows = table.getRowCount();
        TableModel model = table.getModel();
        for (int i = 0; i < numRows; i++) {
            if (table.getSelectedRow() == i) {
                selectFlightNumber = Integer.parseInt(model.getValueAt(i, 0).toString());
                double cost = Double.parseDouble(model.getValueAt(i, 2).toString());
                btnTickets.setEnabled(true);
                break;
            }
        }
    }

    /**
     * fill {@link #cbSource}}
     */
    private void fillCbAirports() {

        HelperClass.fillAirportsCombo(this, cbSource, null);
        if (cbSource.getModel().getSize() <= 0) {
            return;
        }
        String name = ((ComboItem) cbSource.getSelectedItem()).getValue();
        E_Airports airPort = E_Airports.valueOf(name);
        ArrayList<E_Airports> list = new ArrayList<E_Airports>();
        list.add(airPort);
        HelperClass.fillAirportsCombo(this, cbDestination, list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbSource = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cbDestination = new javax.swing.JComboBox();
        lblNOFlights = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jdateFrom = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jDateTo = new com.toedter.calendar.JDateChooser();
        ckbFilterDates = new javax.swing.JCheckBox();
        lblDatesErr = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFlights = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        btnTickets = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(587, 570));

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 126));

        jLabel2.setText("Select Destination:");

        cbSource.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSourceActionPerformed(evt);
            }
        });

        jLabel1.setText("Select source:");

        lblNOFlights.setText("No flight were found");

        jLabel7.setText("From date:");

        jdateFrom.setDateFormatString("dd/MM/yyyy");
        jdateFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdateFromPropertyChange(evt);
            }
        });

        jLabel8.setText("To date:");

        jDateTo.setDateFormatString("dd/MM/yyyy");
        jDateTo.setMinSelectableDate(null);

        ckbFilterDates.setText("Filter Dates");
        ckbFilterDates.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbFilterDatesActionPerformed(evt);
            }
        });

        lblDatesErr.setText("Select dates or uncheck 'filter dates' option");

        tblFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFlights.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tblFlights);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ckbFilterDates)
                                .addGap(18, 18, 18)
                                .addComponent(lblDatesErr)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSource, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(cbDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNOFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 352, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFilterDates)
                    .addComponent(lblDatesErr))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNOFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnTickets.setText("View Tickets");
        btnTickets.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTicketsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTickets)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(btnTickets))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSourceActionPerformed
        // TODO add your handling code here:
        if (cbSource.getModel().getSize() <= 0) {
            return;
        }
        String name = ((ComboItem) cbSource.getSelectedItem()).getValue();
        E_Airports airPort = E_Airports.valueOf(name);
        ArrayList<E_Airports> list = new ArrayList<E_Airports>();
        list.add(airPort);
        HelperClass.fillAirportsCombo(this, cbDestination, list);
    }//GEN-LAST:event_cbSourceActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        getFlightResult();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jdateFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdateFromPropertyChange
        // TODO add your handling code here:

        if ("date".equals(evt.getPropertyName())) {
            String s = evt.getPropertyName();

            Date d;
            d = (Date) evt.getNewValue();

            jDateTo.setDate(d);
            jDateTo.setMinSelectableDate(d);

        }
    }//GEN-LAST:event_jdateFromPropertyChange

    private void ckbFilterDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbFilterDatesActionPerformed
        // TODO add your handling code here:
        jDateTo.setEnabled(ckbFilterDates.isSelected());
        jdateFrom.setEnabled(ckbFilterDates.isSelected());
        lblDatesErr.setVisible(false);
    }//GEN-LAST:event_ckbFilterDatesActionPerformed

    private void btnTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTicketsActionPerformed
        // TODO add your handling code here:

        JDesktopPane desk = this.getDesktopPane();

        if (desk != null) {
            JInternalFrame[] frames = desk.getAllFrames();
            for (JInternalFrame frame : frames) {
                if (frame.getTitle().equals(title)) {
                    MyInternalFrame theFrame = (MyInternalFrame) frame;
                    theFrame.changeWindowButtons(false);

                    theFrame.setGlassPane(theFrame.getDisabledGlassPane());
                    theFrame.getDisabledGlassPane().activate("Please wait");
                }
            }

            Flight flight = MainClass.getIfly().getFlights().get(selectFlightNumber);
            ViewFlightTickets newFrame
                    = new ViewFlightTickets("View Flight Ticket - Flight No. " + flight.getFlightNumber(), selectedUserType, this, flight, -1, null);
            newFrame.setVisible(true);
            child = newFrame;
            try {
                desk.add(child);
                child.setSelected(true);

            } catch (java.beans.PropertyVetoException e) {
            }

        }
    }//GEN-LAST:event_btnTicketsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTickets;
    private javax.swing.JComboBox cbDestination;
    private javax.swing.JComboBox cbSource;
    private javax.swing.JCheckBox ckbFilterDates;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdateFrom;
    private javax.swing.JLabel lblDatesErr;
    private javax.swing.JLabel lblNOFlights;
    private javax.swing.JTable tblFlights;
    // End of variables declaration//GEN-END:variables

    /**
     * get flight result from  {@link init.IFly}}
     * 
     */
     
    private void getFlightResult() {
        E_Airports source, destination;
        Date fromDate, toDate;
        toDate = null;
        fromDate = null;
        tableModel = new MyTableModel(columns);
        tblFlights.setModel(tableModel);
        btnTickets.setEnabled(false);
        if (cbSource.getModel().getSize() <= 0 || cbDestination.getModel().getSize() <= 0) {
            return;
        }
        source = E_Airports.valueOf(((ComboItem) cbSource.getSelectedItem()).getValue());
        destination = E_Airports.valueOf(((ComboItem) cbDestination.getSelectedItem()).getValue());

        if (selectedUserType.equals("Customer")) {
            if (ckbFilterDates.isSelected()) {
                fromDate = jdateFrom.getDate();
                toDate = jDateTo.getDate();
                if (fromDate == null || toDate == null) {
                    lblDatesErr.setVisible(true);
                    lblDatesErr.setForeground(Color.red);
                    return;
                }
            } else {
                fromDate = new Date();
                toDate = new Date(Long.MAX_VALUE);
            }

        } else {
            if (ckbFilterDates.isSelected()) {
                fromDate = jdateFrom.getDate();
                toDate = jDateTo.getDate();
                if (fromDate == null || toDate == null) {
                    lblDatesErr.setVisible(true);
                    lblDatesErr.setForeground(Color.red);
                    return;
                }
            }
        }

        lblDatesErr.setVisible(false);
        DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Map<Integer, Flight> flights;

        flights = MainClass.getIfly().getSearchedFlights(source, destination, fromDate, toDate);

        ArrayList<Object[]> tempData = new ArrayList<Object[]>();

        for (Map.Entry<Integer, Flight> entry : flights.entrySet()) {
            Integer integer = entry.getKey();
            Flight flight = entry.getValue();

            tempData.add(new Object[]{integer, flight.getFlightCost(),
                flight.getStops().size(),
                outputFormatter.format(flight.getFlightDateAndTimeSource()),
                outputFormatter.format(flight.getFlightDateAndTimeDestination())});
        }
        if (tempData.size() > 0) {
            tableModel = new MyTableModel(columns, tempData, null);
            tblFlights.setModel(tableModel);
            lblNOFlights.setVisible(false);
            HelperClass.aligmentCell(tblFlights);
        } else {
            lblNOFlights.setVisible(true);
            tableModel = new MyTableModel(columns);
            tblFlights.setModel(tableModel);
        }

    }
}
