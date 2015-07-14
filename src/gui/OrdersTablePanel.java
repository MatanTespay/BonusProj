/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;
import init.MyTableModel;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import utils.HelperClass;
import core.Agent;
import core.Customer;
import core.FlightOrder;
import core.Order;

/**
 *The class represents the panel showed in the {@link gui.ViewOrders} screen.
 * @author Matan
 */
public class OrdersTablePanel extends javax.swing.JPanel {

    AbstractMap.SimpleEntry<Object, ArrayList<Order>> data;
    String[] agentOrdercolumn = {"Order #",
        "<html><center>Responsible<br>Customer</html>",
        "Cost",
        "<html><center># of<br>Flight orders</html>",
        "Is Paid"};
    String[] cutomerOrdercolumn = {"Order #",
        "<html><center>Agent<br>Name</html>",
        "Cost",
        "<html><center># of<br>Flight orders</html>",
        "Is Paid"};

    String[] flightOrdercolumn = {"Order Number",
        "Flight #",
        "# of Tickets",
        "Total Cost"};
    //this.flight.getFlightCost() * lightTicket.classType.getFee()
    String[] ticketsColumn = {
        "<html><center>Passenger<br>name</html>",
        "Row #",
        "Seat #",
        "class type",
        "Ticket cost"};

    String[] flightColumns = {"flight #", "Source", "Destination", "Cost", "Stops", "departure", "arrival"};

    ViewOrders parent;
    JTable[] array;
    private static final int HEADER_HEIGHT = 32;
    boolean isAgentView = false;
    boolean isCustView = false;
    Object key = null;
    MyTableModel tableModel = new MyTableModel();
    StringBuilder sb = new StringBuilder();

    /**
     * Creates new form OrdersTablePanel
     *
     * @param frame
     */
    public OrdersTablePanel(ViewOrders frame) {
        this.parent = frame;
        initComponents();
        ListSelectionModel selectionModel = tblOrders.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    orderSelection();
                }
            }
        });

        selectionModel = tblFlightOrders.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    foSelection();
                }
            }
        });

        tblOrders.setRowHeight(HEADER_HEIGHT);
        tblTickets.setRowHeight(HEADER_HEIGHT);

        array = new JTable[]{tblOrders, tblFlightOrders, tblFlights, tblTickets};
    }

    //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    public JTable getTblFlightOrders() {
        return tblFlightOrders;
    }

    public JTable getTblFlights() {
        return tblFlights;
    }

    public JTable getTblOrders() {
        return tblOrders;
    }

    public JTable getTblTickets() {
        return tblTickets;
    }

    public void setTblFlightOrders(JTable tblFlightOrders) {
        this.tblFlightOrders = tblFlightOrders;
    }

    public void setTblFlights(JTable tblFlights) {
        this.tblFlights = tblFlights;
    }

    public void setTblOrders(JTable tblOrders) {
        this.tblOrders = tblOrders;
    }

    public void setTblTickets(JTable tblTickets) {
        this.tblTickets = tblTickets;
    }

    public void setIsAgentView(boolean isAgentView) {
        this.isAgentView = isAgentView;
    }

    public void setIsCustView(boolean isCustView) {
        this.isCustView = isCustView;
    }

//</editor-fold>
    /**
     * set the window state according to the user information
     */
    private void setStatus() {
        if (parent == null) {
            return;
        }

        if (parent.selectedUserType.equals("Admin")) {
            if (parent.getRbAgent().isSelected()) {
                tableModel = new MyTableModel(agentOrdercolumn);
                if (parent.getCombAgents().getModel().getSize() > 0) {
                    isAgentView = true;
                    key = Integer.parseInt(((ComboItem) parent.getCombAgents().getSelectedItem()).getValue());
                    sb.append(((ComboItem) parent.getCombAgents().getSelectedItem()).getLabel());
                }
            } else if (parent.getRbCust().isSelected()) {
                tableModel = new MyTableModel(cutomerOrdercolumn);
                if (parent.getCombCustomers().getModel().getSize() > 0) {
                    isCustView = true;
                    key = ((ComboItem) parent.getCombCustomers().getSelectedItem()).getValue();
                    sb.append(((ComboItem) parent.getCombCustomers().getSelectedItem()).getLabel());
                }
            }
        } else {
            if (MainClass.getUserData() != null) {
                key = MainClass.getUserData().getKey();
                if (MainClass.getUserData().getValue() instanceof Agent) {
                    tableModel = new MyTableModel(agentOrdercolumn);
                    Agent a = ((Agent) MainClass.getUserData().getValue());
                    sb.append(a.getFirstName()).append(" ").append(a.getLastName());
                    isAgentView = true;
                } else if (MainClass.getUserData().getValue() instanceof Customer) {
                    tableModel = new MyTableModel(cutomerOrdercolumn);
                    isCustView = true;
                    Customer c = ((Customer) MainClass.getUserData().getValue());
                    sb.append(c.getFirstName()).append(" ").append(c.getLastName());
                }
            }
        }

    }

    /**
     * fired on table selection change in {@link #tblFlightOrders}} table
     */
    private void foSelection() {

        if (tblFlightOrders.getSelectedRow() <= -1) {
            return;
        }

        int oId = (int) tblFlightOrders.getValueAt(tblFlightOrders.getSelectedRow(), 0);
        int fId = (int) tblFlightOrders.getValueAt(tblFlightOrders.getSelectedRow(), 1);
        MyTableModel model = new MyTableModel(ticketsColumn);
        MyTableModel modelflight = new MyTableModel(flightColumns);
        if (data != null) {
            ArrayList<Object[]> dataRow = new ArrayList<>();
            ArrayList<Object[]> dataRowFlights = new ArrayList<>();
            DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");

            for (Order order : data.getValue()) {
                if (oId != order.getOrderNumber()) {
                    continue;
                }
                for (FlightOrder flightOrder : order.getFlightOrders()) {
                    if (fId != flightOrder.getFlight().getFlightNumber()) {
                        continue;
                    }
                    //data for flight table
                    Object[] flightrow = {flightOrder.getFlight().getFlightNumber(),
                        flightOrder.getFlight().getSource().getCity(),
                        flightOrder.getFlight().getDestination().getCity(),
                        flightOrder.getFlight().getFlightCost(),
                        flightOrder.getFlight().getStops().size(),
                        outputFormatter.format(flightOrder.getFlight().getFlightDateAndTimeSource()),
                        outputFormatter.format(flightOrder.getFlight().getFlightDateAndTimeDestination())};
                    dataRowFlights.add(flightrow);

                    //data for tickests table
                    for (FlightOrder.FlightTicket flightTicket : flightOrder.getTickets()) {
                        String[] values = {flightTicket.getPassenger().getFirstName(), flightTicket.getPassenger().getLastName()};
                        Object[] row = {
                            HelperClass.setName(values),
                            flightTicket.getRow(),
                            flightTicket.getSeat(),
                            flightTicket.getClassType().name(),
                            String.format("%.2f", flightOrder.getFlight().getFlightCost() * flightTicket.getClassType().getFee())};
                        dataRow.add(row);
                    }
                    break;
                }
                break;

            }
            model = new MyTableModel(ticketsColumn, dataRow);
            modelflight = new MyTableModel(flightColumns, dataRowFlights);
        }

        tblTickets.setModel(model);
        tblTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        HelperClass.aligmentCell(tblTickets);

        tblFlights.setModel(modelflight);
        tblFlights.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        HelperClass.aligmentCell(tblFlights);

    }

    
    /**
     * fill {@link #tblOrders}}
     */
    public void fillOrdersTable() {

        sb = new StringBuilder();

        setStatus();

        sb.append(" has no orders to show");

        parent.getLblErr().setText(sb.toString());

        if (parent.selectedUserType.equals("Admin")) {
            if (parent.getRbAgent().isSelected() && parent.getCombAgents().getModel().getSize() <= 0) {
                parent.getLblErr().setText("Agents are missing");
            } else if (parent.getRbCust().isSelected() && parent.getCombCustomers().getModel().getSize() <= 0) {
                parent.getLblErr().setText("Customers are missing");
            }
        }

        ArrayList<Order> o;
        data = MainClass.getIfly().getOrdersforOwner(key);

        if (data != null) {
            o = data.getValue();

            ArrayList<Object[]> dataRow = new ArrayList<>();;
            if (o.size() > 0) {

                for (Order order : o) {
                    if (isAgentView) {
                        String[] values = {order.getResponsibleCustomer().getFirstName(), order.getResponsibleCustomer().getLastName()};
                        Object[] row = {order.getOrderNumber(),
                            HelperClass.setName(values),
                            String.format("%.2f", order.getOrderCost()),
                            order.getFlightOrders().size(),
                            order.isPaid() == true ? "Yes" : "No"};
                        dataRow.add(row);
                    } else if (isCustView) {
                        String[] values = {order.getAgent().getFirstName(), order.getAgent().getLastName()};
                        Object[] row = {order.getOrderNumber(), HelperClass.setName(values), String.format("%.2f", order.getOrderCost()),
                            order.getFlightOrders().size(), order.isPaid() == true ? "Yes" : "No"};
                        dataRow.add(row);
                    }
                }

                if (isCustView) {
                    tableModel = new MyTableModel(cutomerOrdercolumn, dataRow, null);

                } else if (isAgentView) {
                    tableModel = new MyTableModel(agentOrdercolumn, dataRow, null);
                }

                tblOrders.setModel(tableModel);
                tblOrders.setFillsViewportHeight(true);
                tblOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                tblOrders.getTableHeader().setResizingAllowed(true);
                parent.getLblErr().setVisible(false);
            } else {
                tblOrders.setModel(tableModel);
                parent.getLblErr().setVisible(true);
            }
        } else {
            parent.getLblErr().setVisible(true);
        }

        tblOrders.setModel(tableModel);

        tblOrders.setTableHeader(new JTableHeader(tblOrders.getColumnModel()) {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = HEADER_HEIGHT;
                return d;
            }
        });
        HelperClass.aligmentCell(tblOrders);
    }

    /**
     * fired on table selection change {@link #tblOrders}}
     */
    private void orderSelection() {

        if (tblOrders.getSelectedRow() <= -1) {
            return;
        }
        clearTables(1);
        int id = (int) tblOrders.getValueAt(tblOrders.getSelectedRow(), 0);
        MyTableModel model = new MyTableModel(flightOrdercolumn);
        if (data != null) {
            ArrayList<Object[]> dataRow = new ArrayList<>();;
            for (Order order : data.getValue()) {
                if (id != order.getOrderNumber()) {
                    continue;
                }
                for (FlightOrder flightOrder : order.getFlightOrders()) {
                    Object[] row = {id,
                        flightOrder.getFlight().getFlightNumber(), flightOrder.getTickets().size(), String.format("%.2f",
                        flightOrder.getTotalAmount())
                    };
                    dataRow.add(row);
                }
                break;
            }
            model = new MyTableModel(flightOrdercolumn, dataRow);

        }

        tblFlightOrders.setModel(model);
        tblFlightOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        HelperClass.aligmentCell(tblFlightOrders);
    }

    /**
     * clear selection to all tables exept the given table
     * @param startIdx
     */
    public void clearTables(int startIdx) {

        for (; startIdx < 3; startIdx++) {
            if (startIdx == 0) {
                tblFlightOrders.setModel(new MyTableModel(flightOrdercolumn));
            } else if (startIdx == 1) {
                tblTickets.setModel(new MyTableModel(ticketsColumn));
            } else if (startIdx == 2) {
                tblFlights.setModel(new MyTableModel(flightColumns));
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

        jScrollPane3 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFlightOrders = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTickets = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblFlights = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(0, 340));

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblOrders.setFillsViewportHeight(true);
        jScrollPane3.setViewportView(tblOrders);

        tblFlightOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblFlightOrders.setFillsViewportHeight(true);
        jScrollPane2.setViewportView(tblFlightOrders);

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
        tblTickets.setRequestFocusEnabled(false);
        jScrollPane4.setViewportView(tblTickets);

        tblFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblFlights.setFillsViewportHeight(true);
        tblFlights.setRequestFocusEnabled(false);
        jScrollPane5.setViewportView(tblFlights);

        jLabel1.setText("Orders");

        jLabel2.setText("Flight Orders");

        jLabel3.setText("Flights");

        jLabel4.setText("Tickests");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(164, 164, 164))
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(173, 173, 173))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblFlightOrders;
    private javax.swing.JTable tblFlights;
    private javax.swing.JTable tblOrders;
    private javax.swing.JTable tblTickets;
    // End of variables declaration//GEN-END:variables
}
