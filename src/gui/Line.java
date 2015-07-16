/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import static init.MainClass.con;
import init.MyTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.ListSelectionModel;

/**
 *
 * @author asus
 */
public class Line extends MyInternalFrame {

    String lineName;
    String[] LineColumns = {
        "ID",
        "Name",
        "Platforms",
        "Kiosk",
        "Zone"};

    /**
     * Creates new form Line
     *
     * @param title
     * @param type
     * @param lineName
     */
    public Line(String title, String type, String lineName) {
        super(title, type);
        this.lineName = lineName;
        initComponents();
        fillCmbColor();

        boolean isViewable = (lineName != null);
        lblStations.setVisible(isViewable);
        tblStations.setVisible(isViewable);
        btnViewStation.setVisible(isViewable);
        lblAddStations.setVisible(isViewable);
        cmbStations.setVisible(isViewable);
        btnRemoveStation.setVisible(isViewable);

        if (lineName != null) {
            fillStations();
            fillFields();
            fillCmbStations();
            tblStations.setRowSelectionAllowed(true);
            tblStations.setColumnSelectionAllowed(false);
            tblStations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }

    public Line(String title, String type, String lineName, JInternalFrame parent) {
        this(title, type, lineName);
        this.parent = parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfLength = new javax.swing.JTextField();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox();
        tfName = new javax.swing.JTextField();
        lbName = new javax.swing.JLabel();
        lblFoundation = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStations = new javax.swing.JTable();
        lblStations = new javax.swing.JLabel();
        tfFoundation = new javax.swing.JTextField();
        lblLength = new javax.swing.JLabel();
        lblKm = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        btnViewStation = new javax.swing.JButton();
        btnRemoveStation = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cmbColor = new javax.swing.JComboBox();
        cmbStations = new javax.swing.JComboBox();
        lblAddStations = new javax.swing.JLabel();

        lblType.setText("Type");

        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Underground", "Overground" }));

        lbName.setText("Line Name");

        lblFoundation.setText("Foundation Year");

        tblStations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblStations.setColumnSelectionAllowed(true);
        tblStations.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblStations);
        tblStations.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lblStations.setText("Stations:");

        tfFoundation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFoundationActionPerformed(evt);
            }
        });

        lblLength.setText("Length");

        lblKm.setText("km");

        lblColor.setText("Color");

        btnViewStation.setText("View Station");
        btnViewStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStationActionPerformed(evt);
            }
        });

        btnRemoveStation.setText("Remove Station");
        btnRemoveStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStationActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");

        btnCancel.setText("Cancel");

        cmbColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Underground", "Overground" }));

        cmbStations.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Underground", "Overground" }));
        cmbStations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStationsActionPerformed(evt);
            }
        });

        lblAddStations.setText("Add");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFoundation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfFoundation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColor)
                            .addComponent(lbName))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblType)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLength)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfLength, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblKm))
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(184, 184, 184))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnViewStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemoveStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblAddStations)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(cmbStations, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStations)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblType)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLength)
                    .addComponent(lblKm)
                    .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFoundation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFoundation))
                .addGap(18, 20, Short.MAX_VALUE)
                .addComponent(lblStations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancel)
                            .addComponent(btnSubmit)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnViewStation)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddStations))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveStation)))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfFoundationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFoundationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFoundationActionPerformed

    private void btnViewStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStationActionPerformed
        int stationID = Integer.valueOf((String) (tblStations.getModel().getValueAt(tblStations.getSelectedRow(), 0)));
        Station newFrame = new Station(evt.getActionCommand(), selectedUserType, stationID,this);
        openChildFrame(newFrame);
        //JDesktopPane desk = this.getDesktopPane();
//        Station newFrame = new Station(evt.getActionCommand(), selectedUserType, stationID);
//        newFrame.setVisible(true);
//        child = newFrame;
//        try {
//            desk.add(child);
//            child.setSelected(true);
//
//        } catch (java.beans.PropertyVetoException ex) {
//        }
    }//GEN-LAST:event_btnViewStationActionPerformed

    private void btnRemoveStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStationActionPerformed
        PreparedStatement st;
        int stationId = Integer.valueOf((String) (tblStations.getModel().getValueAt(tblStations.getSelectedRow(), 0)));

        try {
            st = con.prepareStatement("DELETE FROM tblStationInLine WHERE "
                    + "stationID = ? AND  lineName = ?");
            st.setInt(1, stationId);
            st.setString(2, lineName);
            st.executeUpdate();

            fillStations();
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveStationActionPerformed

    private void cmbStationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStationsActionPerformed
        try {
            PreparedStatement st;
            DefaultComboBoxModel model = (DefaultComboBoxModel)cmbStations.getModel();
            ComboItem item = (ComboItem)model.getSelectedItem();
            String value = item.getValue();
            int stationID = Integer.valueOf(value);

            st = con.prepareStatement("INSERT INTO tblStationInLine VALUES (?,?)");
            st.setInt(1, stationID);
            st.setString(2, lineName);
            st.executeUpdate();
            fillStations();

            int chosenRow = cmbStations.getSelectedIndex();
            ((DefaultComboBoxModel)cmbStations.getModel()).removeElementAt(chosenRow);
        } catch (SQLException | NullPointerException ex) {
//            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbStationsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRemoveStation;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnViewStation;
    private javax.swing.JComboBox cmbColor;
    private javax.swing.JComboBox cmbStations;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lblAddStations;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblFoundation;
    private javax.swing.JLabel lblKm;
    private javax.swing.JLabel lblLength;
    private javax.swing.JLabel lblStations;
    private javax.swing.JLabel lblType;
    private javax.swing.JTable tblStations;
    private javax.swing.JTextField tfFoundation;
    private javax.swing.JTextField tfLength;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

    private void fillStations() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("Select * From tblStationInLine As SIL join tblStation"
                    + " As S on SIL.stationID = S.ID WHERE SIL.lineName = ? ");
            st.setString(1, lineName);
            rs = st.executeQuery();

            ArrayList<Object[]> rows = new ArrayList();
            while (rs.next()) {
                Object[] row = {rs.getString("ID"), rs.getString("name"),
                    rs.getString("platformNum"), rs.getString("kiosk"), rs.getString("zoneNumber")};
                rows.add(row);
            }
            MyTableModel tableModel = new MyTableModel(LineColumns, rows, null);
            tblStations.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fillCmbStations() {
        Statement s;
        ResultSet rs;

        ArrayList<String> tableItems = new ArrayList<>();
        for (int row = 0; row < tblStations.getModel().getRowCount(); row++) {
            tableItems.add((String) tblStations.getModel().getValueAt(row, 0));
        }

        try {
            s = con.createStatement();
            rs = s.executeQuery("Select * From tblStation");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                if (!tableItems.contains(rs.getString("ID"))) {
                    items.add(new ComboItem(rs.getString("ID"), rs.getString("name")));
                }
            }
            Collections.sort(items);
            items.add(0, null);
            cmbStations.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillFields() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("Select * From tblLine As L join tblLineColor "
                    + "As LC on L.name = LC.lineName WHERE L.name = ?");
            st.setString(1, lineName);
            rs = st.executeQuery();

            rs.next();
            cmbColor.setSelectedItem(rs.getString(5)/*color name*/);
            tfFoundation.setText(rs.getString("foundedYear"));
            tfLength.setText(rs.getString("lineLength"));
            tfName.setText(rs.getString("name"));
            cmbType.setSelectedItem((rs.getString("lineType").equals("O")) ? "Overground" : "Underground");
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbColor() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery("Select * From tblLineColor");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("name"), rs.getString("name")));
            }
            Collections.sort(items);
            cmbColor.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
            cmbColor.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
