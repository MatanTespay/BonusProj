/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import static init.MainClass.con;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.NumberAxis;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.PlainDocument;
import utils.Column;
import static utils.Constants.ADD_MODE;
import static utils.Constants.EDIT_MODE;
import utils.CustomTableModel;
import static utils.HelperClass.setSelectedValue;
import utils.InputType;
import utils.Queries;
import utils.QueryCombobox;

/**
 *
 * @author asus
 */
public class Station extends MyInternalFrame {

    private short stationID;
    private String stationName;
    private Byte platformNum;
    private boolean isKiosk;
    private int zoneNumber;
    JTextField editor;

    /**
     * Creates new form Station2
     *
     * @param title
     * @param type
     * @param stationID
     */
    public Station(String title, String type, short stationID) {
        super(title, type);
        setMode(EDIT_MODE);
        this.stationID = stationID;
        initComponents();
        setVariables();
        buildForm();
        setDefaults();
    }

    public Station(String title, String type) {
        super(title, type);
        setMode(ADD_MODE);
        initComponents();
        tfStationID.setText("(auto number)");
        buildForm();
        this.zoneNumber = 1;
        this.platformNum = 2;
    }

    public Station(String title, String type, short stationID, JInternalFrame parent) {
        this(title, type, stationID);
        this.parent = parent;
    }

    private void buildForm() {

        try {
            
            PreparedStatement getAllZones = con.prepareStatement("SELECT * FROM tblZone");
            PreparedStatement getAllLines = con.prepareStatement("SELECT name FROM tblLine");

            // set models to comboboxe
            cmbZone.setModel(new QueryCombobox(cmbZone, String.class, getAllZones));
            cmbLine.setModel(new QueryCombobox(cmbLine, String.class, getAllLines));

            // set visibility and enablement
            setActiveness();

            // set document listeners to text fields
            tfName.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    stationName = tfName.getText();
                    btnSave.setEnabled(isOkToSave());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    stationName = tfName.getText();
                    btnSave.setEnabled(isOkToSave());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });

            JSpinner.NumberEditor platformEditor = (JSpinner.NumberEditor) spnPlatforms.getEditor();
            JTextField platformTextField = platformEditor.getTextField();
            platformTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        platformNum = Byte.valueOf(platformTextField.getText());
                    } catch (NumberFormatException ex) {
                        platformNum = null;
                    }
                    btnSave.setEnabled(isOkToSave());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        platformNum = Byte.valueOf(platformTextField.getText());
                    } catch (NumberFormatException ex) {
                        platformNum = null;
                    }
                    btnSave.setEnabled(isOkToSave());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });

            // set document filters to text fields
            PlainDocument stationIdDoc = (PlainDocument) tfStationID.getDocument();
            stationIdDoc.setDocumentFilter(new utils.MyDocFilter(InputType.SHORT));

            PlainDocument nameDoc = (PlainDocument) tfName.getDocument();
            nameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT));

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
        lblStationID = new javax.swing.JLabel();
        lblStationName = new javax.swing.JLabel();
        tfStationID = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        lblPlatforms = new javax.swing.JLabel();
        lblZoneNum = new javax.swing.JLabel();
        cmbZone = new javax.swing.JComboBox();
        spnPlatforms = new javax.swing.JSpinner();
        chbKiosk = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        pLines = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLines = new javax.swing.JTable();
        btnViewLine = new javax.swing.JButton();
        cmbLine = new javax.swing.JComboBox();
        btnAddLine = new javax.swing.JButton();
        btnRemoveLine = new javax.swing.JButton();

        pDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        lblStationID.setText("Station ID");

        lblStationName.setText("Name");

        tfStationID.setEnabled(false);

        lblPlatforms.setText("No. of platforms");

        lblZoneNum.setText("Zone Number");

        cmbZone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbZone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbZoneActionPerformed(evt);
            }
        });

        spnPlatforms.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)2), Short.valueOf((short)2), Short.valueOf((short)8), Short.valueOf((short)1)));
        spnPlatforms.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spnPlatformsPropertyChange(evt);
            }
        });

        chbKiosk.setText("Kiosk");
        chbKiosk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbKioskActionPerformed(evt);
            }
        });

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
                    .addComponent(lblStationID)
                    .addComponent(lblStationName))
                .addGap(18, 18, 18)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfStationID, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(tfName))
                .addGap(18, 18, 18)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(lblZoneNum)
                        .addGap(18, 18, 18)
                        .addComponent(cmbZone, 0, 86, Short.MAX_VALUE))
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(lblPlatforms)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnPlatforms)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chbKiosk)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDetailsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pDetailsLayout.setVerticalGroup(
            pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfStationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStationID)
                            .addComponent(lblZoneNum)
                            .addComponent(cmbZone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStationName)))
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlatforms)
                            .addComponent(chbKiosk)
                            .addComponent(spnPlatforms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel)
                    .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnDelete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pLines.setBorder(javax.swing.BorderFactory.createTitledBorder("Lines"));

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

        btnViewLine.setText("View");
        btnViewLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewLineActionPerformed(evt);
            }
        });

        cmbLine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLineActionPerformed(evt);
            }
        });

        btnAddLine.setText("Add");
        btnAddLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLineActionPerformed(evt);
            }
        });

        btnRemoveLine.setText("Remove");
        btnRemoveLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pLinesLayout = new javax.swing.GroupLayout(pLines);
        pLines.setLayout(pLinesLayout);
        pLinesLayout.setHorizontalGroup(
            pLinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLinesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pLinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnViewLine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemoveLine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbLine, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddLine, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pLinesLayout.setVerticalGroup(
            pLinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLinesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pLinesLayout.createSequentialGroup()
                        .addComponent(btnViewLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoveLine)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pLines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chbKioskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbKioskActionPerformed
        this.isKiosk = chbKiosk.isSelected();
    }//GEN-LAST:event_chbKioskActionPerformed

    private void btnViewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewLineActionPerformed
        String lineName = (String) (tblLines.getModel().getValueAt(tblLines.getSelectedRow(), 0));
        Line newFrame = new Line(evt.getActionCommand(), getSelectedUserType(), lineName, this);
        this.openChildFrame(newFrame);

    }//GEN-LAST:event_btnViewLineActionPerformed

    private void btnRemoveLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLineActionPerformed
        PreparedStatement deleteSIL;
        Object value;
        String lineName = "";

        try {
            value = tblLines.getModel().getValueAt(tblLines.getSelectedRow(), 0);
            lineName = value.toString();

            deleteSIL = con.prepareStatement(Queries.DELETE_STATION_IN_LINE);
            deleteSIL.setInt(1, stationID);
            deleteSIL.setString(2, lineName);
            deleteSIL.executeUpdate();
            
            ((CustomTableModel) tblLines.getModel()).fillTable();
            
            JOptionPane.showInternalMessageDialog(this,
                    "Line was removed successfully from this station! What a crappy line it was.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

            btnDelete.setEnabled(isOkToDelete());

        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 547:
                    JOptionPane.showInternalMessageDialog(this,
                            "This line can not be renoved from this station. There are 2 possibilities:\n"
                            + "1. Activites have already been made in this line in this station.\n"
                            + "2. There are nearby sites related to this line in this station.\n\n"
                            + "Please check the above in order to continue.",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }
    }//GEN-LAST:event_btnRemoveLineActionPerformed

    private void cmbLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLineActionPerformed
        btnAddLine.setEnabled(cmbLine.getItemCount() != 0);
    }//GEN-LAST:event_cmbLineActionPerformed

    private void cmbZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbZoneActionPerformed
        ComboItem zoneItem = (ComboItem) cmbZone.getSelectedItem();
        this.zoneNumber = Integer.parseInt(zoneItem.getKey().toString());
    }//GEN-LAST:event_cmbZoneActionPerformed

    private void spnPlatformsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spnPlatformsPropertyChange
        this.platformNum = Byte.parseByte(spnPlatforms.getValue().toString());
    }//GEN-LAST:event_spnPlatformsPropertyChange

    private void btnAddLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLineActionPerformed
        ComboItem selectedStation;
        String lineName;
        PreparedStatement insertSIL;

        try {
            selectedStation = (ComboItem) ((QueryCombobox) cmbLine.getModel()).getSelectedItem();
            lineName = selectedStation.getKey().toString();

            insertSIL = con.prepareStatement(Queries.INSERT_STATION_IN_LINE);
            insertSIL.setInt(1, stationID);
            insertSIL.setString(2, lineName);
            insertSIL.executeUpdate();
            
            ((CustomTableModel) tblLines.getModel()).fillTable();
            
            JOptionPane.showInternalMessageDialog(this,
                    "Line was added successfully to this station! Oh yeah!",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);
            
            btnDelete.setEnabled(isOkToDelete());

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(this,
                    "Station name " + stationName + "already exists. Please be original and pick another name.",
                    "Bummer!",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddLineActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        PreparedStatement insertStation;
        PreparedStatement updateStation;
        PreparedStatement getStationID;

        try {
            if (getMode() == ADD_MODE) {
                insertStation = con.prepareStatement(Queries.INSERT_STATION);
                insertStation.setString(1, stationName);
                insertStation.setByte(2, platformNum);
                insertStation.setBoolean(3, isKiosk);
                insertStation.setInt(4, zoneNumber);
                insertStation.executeUpdate();

                // after creating the new station we want to show its new autonumber
                getStationID = con.prepareStatement(Queries.SELECT_STATION_ID_BY_NAME);
                ResultSet rs = getStationID.executeQuery();
                this.stationID = rs.getShort("ID");
                tfStationID.setText(String.valueOf(stationID));
                //change mode to edit after saving
                setMode(EDIT_MODE);
                
            } else {
                //edit mode
                updateStation = con.prepareStatement(Queries.UPDATE_STATION);
                updateStation.setString(1, stationName);
                updateStation.setByte(2, platformNum);
                updateStation.setBoolean(3, isKiosk);
                updateStation.setInt(4, zoneNumber);
                updateStation.setInt(5, stationID);
                updateStation.executeUpdate();
            }
            JOptionPane.showInternalMessageDialog(this,
                    "Station \"" + stationName + "\" was saved successfully.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 2627:
                    JOptionPane.showInternalMessageDialog(this,
                            "Station name " + stationName + "already exists. Please be original and pick another name.",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
            }
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
//        if (getUserQueries().isEmpty()) {
//            this.dispose();
//            return;
//        }
//
//        int option = JOptionPane.showInternalOptionDialog(this, "You have unsaved data.\n"
//                + "Are you sure you want to exit?", "Warning!", JOptionPane.YES_NO_OPTION,
//                JOptionPane.WARNING_MESSAGE, null, null, null);
//
//        switch (option) {
//            case JOptionPane.YES_OPTION:
//                this.dispose();
//            case JOptionPane.NO_OPTION:
//                // do nothing
//                break;
//        }

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        PreparedStatement deleteStation;
        try {
            deleteStation = con.prepareStatement(Queries.DELETE_STATION);
            deleteStation.setInt(1, stationID);
            deleteStation.executeUpdate();
            
            JOptionPane.showInternalMessageDialog(this,
                    "Station was deleted successfully! Who needed it anyway?",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);
            this.dispose();
            
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(this,
                    "Error code: " + e.getErrorCode() + ". Go figure it yourself!",
                    "Bummer!",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLine;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRemoveLine;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnViewLine;
    private javax.swing.JCheckBox chbKiosk;
    private javax.swing.JComboBox cmbLine;
    private javax.swing.JComboBox cmbZone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPlatforms;
    private javax.swing.JLabel lblStationID;
    private javax.swing.JLabel lblStationName;
    private javax.swing.JLabel lblZoneNum;
    private javax.swing.JPanel pDetails;
    private javax.swing.JPanel pLines;
    private javax.swing.JSpinner spnPlatforms;
    private javax.swing.JTable tblLines;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfStationID;
    // End of variables declaration//GEN-END:variables

    private void setLineTableModel() {

        ArrayList<Column> cols = new ArrayList<>();
        cols.add(new Column("Name", "name", String.class));
        cols.add(new Column("Color", "colorName", String.class));
        cols.add(new Column("Foundation year", "foundedYear", Integer.class));
        cols.add(new Column("Type", "lineType", String.class));
        cols.add(new Column("Length", "lineLength", Double.class));
        try {
            PreparedStatement getAllLines = con.prepareStatement(Queries.SELECT_LINES_OF_STATION);
            getAllLines.setInt(1, stationID);

            CustomTableModel lineTableModel = new CustomTableModel(tblLines, cols, getAllLines);
            lineTableModel.bindComboBox(cmbLine, 0, 0);
            
            HashSet<JButton> tableButtons = new HashSet<>();
            tableButtons.add(btnRemoveLine);
            tableButtons.add(btnViewLine);
            lineTableModel.bindButtons(tableButtons);
            
            tblLines.setModel(lineTableModel);
            lineTableModel.fillTable();
            
            btnDelete.setEnabled(isOkToDelete());

        } catch (SQLException ex) {

        }
    }

    private void setVariables() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement(Queries.SELECT_STATION);
            st.setInt(1, stationID);
            rs = st.executeQuery();

            rs.next();
            this.stationName = rs.getString("name");
            this.platformNum = rs.getByte("platformNum");
            this.isKiosk = rs.getBoolean("Kiosk");
            this.zoneNumber = rs.getInt("zoneNumber");

        } catch (SQLException ex) {
            Logger.getLogger(Station.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDefaults() {
        tfStationID.setText(String.valueOf(stationID));
        tfName.setText(this.stationName);
        //Set the zoneID to the value from the class field and not to index 0.
        setSelectedValue(cmbZone, String.valueOf(this.zoneNumber)); //WHAT FOR? <- ALL FOR LOVE!
        spnPlatforms.setValue(this.platformNum);
        chbKiosk.setSelected(this.isKiosk);
        cmbLine.setSelectedIndex(0);
    }

    private void setActiveness() {

        tfStationID.setEnabled(false);

        if (getMode() == ADD_MODE) {
            pLines.setVisible(false);

        } else {
            // edit mode
            setLineTableModel();
            pLines.setVisible(true);
        }
        btnSave.setEnabled(isOkToSave());
        btnDelete.setEnabled(isOkToDelete());
    }

    private boolean isOkToSave() {
        if (getMode() == EDIT_MODE && (stationName == null || stationName.isEmpty())) {
            btnSave.setToolTipText("The station must have a name");
            return false;
        }
        if (platformNum == null || platformNum < 2 || platformNum > 8) {
            btnSave.setToolTipText("The station must have a name");
            return false;
        }
        btnSave.setToolTipText(null);
        return true;
    }

    private boolean isOkToDelete() {
        if (getMode() == ADD_MODE) {
            return false;
        }
        
        if (tblLines.getModel().getRowCount() > 0) {
            btnDelete.setToolTipText("Deleting the station is not allowed since it has lines");
            return false;
        }
        return true;
    }
}
