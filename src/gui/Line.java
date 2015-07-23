/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.QueryCombobox;
import utils.Column;
import utils.CustomTableModel;
import init.ComboItem;
import init.InputValidator;
import static init.MainClass.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import static utils.Constants.ADD_MODE;
import static utils.Constants.EDIT_MODE;
import utils.Queries;

/**
 *
 * @author asus
 */
public class Line extends MyInternalFrame {

    private String lineName;
    private int foundedYear;
    private String lineType;
    private double lineLength;
    private String colorName;
    private PreparedStatement getLine;

    /**
     * Creates new form Line
     *
     * @param title
     * @param type
     * @param lineName
     */
    public Line(String title, String type, String lineName) {
        super(title, type);
        setMode(EDIT_MODE);
        this.lineName = lineName;
        initComponents();
        setVariables();
        buildForm();
        setDefaults();
    }

    public Line(String title, String type, String lineName, JInternalFrame parent) {
        this(title, type, lineName);
        this.parent = parent;
    }

    public Line(String title, String type) {
        super(title, type);
        setMode(ADD_MODE);
        initComponents();
        buildForm();
    }

    private void buildForm() {
        try {
            PreparedStatement getAllColors = con.prepareStatement("SELECT name FROM tblLineColor");
            PreparedStatement getAllStations = con.prepareStatement("SELECT ID, name FROM tblStation");

            cmbColor.setModel(new QueryCombobox(cmbColor, String.class, getAllColors));
            cmbStation.setModel(new QueryCombobox(cmbStation, Integer.class, getAllStations));
            setStationTblModel();

            setActiveness();

            super.validators = new ArrayList<InputValidator>() {
                {
                    add(new InputValidator(tfLength, utils.InputType.DOUBLE, null, null));
                    add(new InputValidator(tfName, utils.InputType.TEXT, null, null));
                }
            };
        } catch (SQLException ex) {

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

        pDetails = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblFoundation = new javax.swing.JLabel();
        ychFoundationYear = new com.toedter.calendar.JYearChooser();
        cmbColor = new javax.swing.JComboBox();
        tfName = new javax.swing.JTextField();
        cmbType = new javax.swing.JComboBox();
        tfLength = new javax.swing.JTextField();
        lblKm = new javax.swing.JLabel();
        lblLength = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        pStations = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStations = new javax.swing.JTable();
        btnViewStation = new javax.swing.JButton();
        cmbStation = new javax.swing.JComboBox();
        btnAddStation = new javax.swing.JButton();
        btnRemoveStation = new javax.swing.JButton();

        pDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        lbName.setText("Line Name");

        lblColor.setText("Color");

        lblFoundation.setText("Foundation Year");

        ychFoundationYear.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ychFoundationYearPropertyChange(evt);
            }
        });

        cmbColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Underground", "Overground" }));
        cmbColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbColorActionPerformed(evt);
            }
        });

        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Underground", "Overground" }));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });

        tfLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLengthActionPerformed(evt);
            }
        });

        lblKm.setText("km");

        lblLength.setText("Length");

        lblType.setText("Type");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pDetailsLayout = new javax.swing.GroupLayout(pDetails);
        pDetails.setLayout(pDetailsLayout);
        pDetailsLayout.setHorizontalGroup(
            pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(lblFoundation)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDetailsLayout.createSequentialGroup()
                        .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColor)
                            .addComponent(lbName))
                        .addGap(40, 40, 40)))
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ychFoundationYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbColor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDetailsLayout.createSequentialGroup()
                        .addComponent(lblType)
                        .addGap(20, 20, 20))
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(lblLength)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(tfLength, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblKm))
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDetailsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pDetailsLayout.setVerticalGroup(
            pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblType)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLength)
                    .addComponent(lblKm)
                    .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ychFoundationYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFoundation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        pStations.setBorder(javax.swing.BorderFactory.createTitledBorder("Stations"));

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

        btnViewStation.setText("View");
        btnViewStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStationActionPerformed(evt);
            }
        });

        cmbStation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Underground", "Overground" }));
        cmbStation.setName(""); // NOI18N
        cmbStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStationActionPerformed(evt);
            }
        });

        btnAddStation.setText("Add");
        btnAddStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStationActionPerformed(evt);
            }
        });

        btnRemoveStation.setText("Remove");
        btnRemoveStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pStationsLayout = new javax.swing.GroupLayout(pStations);
        pStations.setLayout(pStationsLayout);
        pStationsLayout.setHorizontalGroup(
            pStationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pStationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnViewStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemoveStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbStation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddStation, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pStationsLayout.setVerticalGroup(
            pStationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pStationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pStationsLayout.createSequentialGroup()
                        .addComponent(btnViewStation)
                        .addGap(10, 10, 10)
                        .addComponent(cmbStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddStation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoveStation)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pStations, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pStations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStationActionPerformed
        int stationID = Integer.parseInt(tblStations.getModel().getValueAt(tblStations.getSelectedRow(), 0).toString());
        Station newFrame = new Station(evt.getActionCommand(), getSelectedUserType(), stationID, this);
        openChildFrame(newFrame);
    }//GEN-LAST:event_btnViewStationActionPerformed

    private void btnRemoveStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStationActionPerformed
        PreparedStatement deleteSIL;
        Object value;
        int stationID;

        try {
            value = tblStations.getModel().getValueAt(tblStations.getSelectedRow(), 0);
            stationID = Integer.parseInt(value.toString());
            ((CustomTableModel) tblStations.getModel()).removeRow(tblStations.getSelectedRow());

            deleteSIL = con.prepareStatement(Queries.DELETE_STATION_IN_LINE);
            deleteSIL.setInt(1, stationID);
            deleteSIL.setString(1, lineName);
            deleteSIL.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRemoveStationActionPerformed

    private void cmbStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStationActionPerformed

    }//GEN-LAST:event_cmbStationActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        this.lineName = tfName.getText();
    }//GEN-LAST:event_tfNameActionPerformed

    private void cmbColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbColorActionPerformed
        ComboItem colorItem = (ComboItem) cmbColor.getModel().getSelectedItem();
        this.colorName = (String) colorItem.getKey();
    }//GEN-LAST:event_cmbColorActionPerformed

    private void ychFoundationYearPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ychFoundationYearPropertyChange
        this.foundedYear = ychFoundationYear.getYear();
    }//GEN-LAST:event_ychFoundationYearPropertyChange

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        String strType = (String) cmbType.getModel().getSelectedItem();
        this.lineType = (strType.equals("Overground")) ? "O" : "U";
    }//GEN-LAST:event_cmbTypeActionPerformed

    private void tfLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfLengthActionPerformed
        this.lineLength = Double.valueOf(tfLength.getText());
    }//GEN-LAST:event_tfLengthActionPerformed

    private void btnAddStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStationActionPerformed
        ComboItem selectedStation;
        int stationID;
        PreparedStatement insertSIL;

        try {
            selectedStation = (ComboItem) ((QueryCombobox) cmbStation.getModel()).getSelectedItem();
            stationID = Integer.valueOf(selectedStation.getKey().toString());
            ((CustomTableModel) tblStations.getModel()).addRow(stationID);

            insertSIL = con.prepareStatement(Queries.INSERT_STATION_IN_LINE);
            insertSIL.setInt(1, stationID);
            insertSIL.setString(1, lineName);
            insertSIL.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddStationActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        PreparedStatement deleteColor;
        PreparedStatement deleteLine;
        PreparedStatement updateColor;
        PreparedStatement updateLine;

        try {
            con.setAutoCommit(false);
            if (getMode() == ADD_MODE) {
                deleteColor = con.prepareStatement(Queries.INSERT_LINE);
                deleteColor.setString(1, lineName);
                deleteColor.setInt(2, foundedYear);
                deleteColor.setString(3, lineType);
                deleteColor.setDouble(4, lineLength);
                deleteColor.executeUpdate();

                deleteLine = con.prepareStatement(Queries.INSERT_COLOR);
                deleteLine.setString(1, colorName);
                deleteLine.setString(2, lineName);
                deleteLine.executeUpdate();
            } else {
                // view mode
                updateColor = con.prepareStatement(Queries.UPDATE_LINE);
                updateColor.setString(1, lineName);
                updateColor.setInt(2, foundedYear);
                updateColor.setString(3, lineType);
                updateColor.setDouble(4, lineLength);
                updateColor.executeUpdate();

                updateLine = con.prepareStatement(Queries.UPDATE_COLOR);
                updateLine.setString(1, colorName);
                updateLine.setString(2, lineName);
                updateLine.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch (SQLException excep) {
                    System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Line.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
//        cancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        PreparedStatement deleteColor;
        PreparedStatement deleteLine;

        try {
            con.setAutoCommit(false);
            deleteColor = con.prepareStatement(Queries.DELETE_COLOR);
            deleteColor.setString(1, lineName);
            deleteColor.executeUpdate();

            deleteLine = con.prepareStatement(Queries.DELETE_LINE);
            deleteLine.setString(1, lineName);
            deleteLine.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch (SQLException excep) {
                    System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Line.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStation;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRemoveStation;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnViewStation;
    private javax.swing.JComboBox cmbColor;
    private javax.swing.JComboBox cmbStation;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblFoundation;
    private javax.swing.JLabel lblKm;
    private javax.swing.JLabel lblLength;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel pDetails;
    private javax.swing.JPanel pStations;
    private javax.swing.JTable tblStations;
    private javax.swing.JTextField tfLength;
    private javax.swing.JTextField tfName;
    private com.toedter.calendar.JYearChooser ychFoundationYear;
    // End of variables declaration//GEN-END:variables

    private void setStationTblModel() {
        ArrayList<Column> cols = new ArrayList<>();
        cols.add(new Column("ID", "ID", Integer.class));
        cols.add(new Column("Name", "Name", String.class));
        cols.add(new Column("Platforms", "platformNum", Integer.class));
        cols.add(new Column("Kiosk", "Kiosk", Boolean.class));
        cols.add(new Column("Zone", "zoneNumber", Integer.class));

        PreparedStatement getAllStations;
        CustomTableModel stationTblModel;

        try {
            getAllStations = con.prepareStatement("Select * From tblStationInLine "
                    + "As SIL join tblStation As S on SIL.stationID = S.ID WHERE SIL.lineName = ?");
            getAllStations.setString(1, lineName);
            PreparedStatement addStation = con.prepareStatement("Select * From tblStation WHERE ID = ?");

            stationTblModel = new CustomTableModel(tblStations, cols, addStation, getAllStations);
            stationTblModel.bindComboBox(cmbStation, 0, 1);
            stationTblModel.fillTable();
            tblStations.setModel(stationTblModel);
        } catch (SQLException ex) {

        }
    }

    private void setVariables() {
        ResultSet rs;
        try {
            getLine = con.prepareStatement("Select * From tblLine As L join tblLineColor "
                    + "As LC on L.name = LC.lineName WHERE L.name = ?");
            getLine.setString(1, lineName);
            rs = getLine.executeQuery();

            rs.next();
            this.colorName = rs.getString(5)/*color name*/;
            this.foundedYear = rs.getInt("foundedYear");
            this.lineLength = rs.getDouble("lineLength");
            this.lineName = rs.getString("name");
            this.lineType = rs.getString("lineType");

        } catch (SQLException ex) {
            Logger.getLogger(Line.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDefaults() {
        tfName.setText(this.lineName);
        cmbColor.getModel().setSelectedItem(colorName);
        ychFoundationYear.setYear(this.foundedYear);
        cmbType.setSelectedItem((this.lineType == "O") ? "Overground" : "Underground");
        tfLength.setText(String.valueOf(this.lineLength));
    }

    private void setActiveness() {
        if (getMode() == ADD_MODE) {
            tfName.setEnabled(true);
        } else {
            // edit mode
            tfName.setEnabled(false);
        }
    }
}
