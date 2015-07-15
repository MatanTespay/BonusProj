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
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

/**
 *
 * @author asus
 */
public class Station extends MyInternalFrame {

    int stationID;
    String[] LineColumns = {
        "Name",
        "Color",
        "FoundedYear",
        "Type",
        "Length"};

    /**
     * Creates new form Station2
     *
     * @param title
     * @param type
     */
    public Station(String title, String type, Integer stationID) {
        super(title, type);
        this.stationID = stationID.intValue();
        initComponents();
        fillCBZone();
        fillLines();
        fillFields();

        tblLines.setRowSelectionAllowed(true);
        tblLines.setColumnSelectionAllowed(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnViewLine = new javax.swing.JButton();
        cbKiosk = new javax.swing.JCheckBox();
        tfStationID = new javax.swing.JTextField();
        lblStationID = new javax.swing.JLabel();
        lblStationName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLines = new javax.swing.JTable();
        lblLines = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnAddLine = new javax.swing.JButton();
        btnRemoveLine = new javax.swing.JButton();
        lblPlatforms = new javax.swing.JLabel();
        lblZoneNum = new javax.swing.JLabel();
        cmbZoneNum = new javax.swing.JComboBox();
        tfPlatforms = new javax.swing.JTextField();

        btnViewLine.setText("View Line");
        btnViewLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewLineActionPerformed(evt);
            }
        });

        cbKiosk.setText("Kiosk");
        cbKiosk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKioskActionPerformed(evt);
            }
        });

        tfStationID.setEnabled(false);

        lblStationID.setText("Station ID");

        lblStationName.setText("Name");

        tblLines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblLines.setColumnSelectionAllowed(true);
        tblLines.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblLines);
        tblLines.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lblLines.setText("Lines:");

        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");

        btnCancel.setText("Cancel");

        btnAddLine.setText("Add Line");

        btnRemoveLine.setText("Remove Line");
        btnRemoveLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLineActionPerformed(evt);
            }
        });

        lblPlatforms.setText("No. of platforms");

        lblZoneNum.setText("Zone Number");

        cmbZoneNum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblLines)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStationID)
                                    .addComponent(lblStationName))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addComponent(tfStationID))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblZoneNum)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbZoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPlatforms)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfPlatforms)))
                                .addGap(0, 20, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbKiosk)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAddLine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnViewLine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemoveLine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfStationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStationID)
                            .addComponent(lblZoneNum)
                            .addComponent(cmbZoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStationName)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPlatforms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPlatforms)
                            .addComponent(cbKiosk))))
                .addGap(18, 18, 18)
                .addComponent(lblLines)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnViewLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveLine)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSubmit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbKioskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKioskActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKioskActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNameActionPerformed

    private void btnViewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewLineActionPerformed
        String lineName = (String) (tblLines.getModel().getValueAt(tblLines.getSelectedRow(), 0));
        JDesktopPane desk = this.getDesktopPane();

//        if (desk != null) {
//            JInternalFrame[] frames = desk.getAllFrames();
//            for (JInternalFrame frame : frames) {
//                if (frame.getTitle().equals(title)) {
//                    MyInternalFrame theFrame = (MyInternalFrame) frame;
//                    theFrame.changeWindowButtons(false);
//
//                    theFrame.setGlassPane(theFrame.getDisabledGlassPane());
//                    theFrame.getDisabledGlassPane().activate("Please wait");
//                }
//            }
       
            Line newFrame = new Line(evt.getActionCommand(), selectedUserType, lineName);

            newFrame.setVisible(true);
            child = newFrame;
         try {
            desk.add(child);
            child.setSelected(true);

        } catch (java.beans.PropertyVetoException ex) {
        }
//        }
    }//GEN-LAST:event_btnViewLineActionPerformed

    private void btnRemoveLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLineActionPerformed
        PreparedStatement st;
        String lineName = (String) (tblLines.getModel().getValueAt(tblLines.getSelectedRow(), 0));

        try {
            st = con.prepareStatement("DELETE FROM tblStationInLine WHERE "
                    + "stationID = ? AND  lineName = ?");
            st.setInt(1, stationID);
            st.setString(2, lineName);
            st.executeUpdate();

            fillLines();
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveLineActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLine;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRemoveLine;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnViewLine;
    private javax.swing.JCheckBox cbKiosk;
    private javax.swing.JComboBox cmbZoneNum;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLines;
    private javax.swing.JLabel lblPlatforms;
    private javax.swing.JLabel lblStationID;
    private javax.swing.JLabel lblStationName;
    private javax.swing.JLabel lblZoneNum;
    private javax.swing.JTable tblLines;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPlatforms;
    private javax.swing.JTextField tfStationID;
    // End of variables declaration//GEN-END:variables

    private void fillCBZone() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery("Select * From tblZone");
            ArrayList<ComboItem> items = new ArrayList<ComboItem>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("number"), rs.getString("number")));
            }
            cmbZoneNum.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillLines() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("Select * From tblStationInLine As SIL join tblLine"
                    + " As L on SIL.lineName = L.name join tblLineColor As LC on L.name"
                    + " = LC.lineName WHERE SIL.stationID = ? ");
            st.setInt(1, stationID);
            rs = st.executeQuery();
            ArrayList<Object[]> rows = new ArrayList();
            while (rs.next()) {
                Object[] row = {rs.getString(3)/*line name*/, rs.getString(7)/*line color*/,
                    rs.getString("foundedYear"), rs.getString("lineType"), rs.getString("lineLength")};
                rows.add(row);
            }
            MyTableModel tableModel = new MyTableModel(LineColumns, rows, null);
            tblLines.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillFields() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("Select * From tblStation As S WHERE S.ID = ?");
            st.setInt(1, stationID);
            rs = st.executeQuery();

            rs.next();
            tfName.setText(rs.getString("name"));
            tfPlatforms.setText(rs.getString("platformNum"));
            tfStationID.setText(rs.getString("ID"));
            cbKiosk.setSelected(!(rs.getString("Kiosk").equals("0")));
            cmbZoneNum.setSelectedItem(rs.getString("zoneNumber"));
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
