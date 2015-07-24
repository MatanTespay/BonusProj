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
import static init.MainClass.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;
import static utils.Constants.ADD_MODE;
import static utils.Constants.EDIT_MODE;
import utils.InputType;
import utils.Queries;

/**
 *
 * @author asus
 */
public class Line extends MyInternalFrame {

    private String lineName;
    private int foundedYear;
    private String lineType;
    private Double lineLength;
    private String colorName;

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
        this.lineType = "U";
    }

    private void buildForm() {
        try {
            PreparedStatement getAllStations = con.prepareStatement("SELECT ID, name FROM tblStation");

            // set models to comboboxes   
            cmbStation.setModel(new QueryCombobox(cmbStation, Integer.class, getAllStations));
            setStationTblModel();

            // set visibility and enablement
            setActiveness();

            // set document listeners to text fields
            tfName.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    lineName = tfName.getText();
                    btnSave.setEnabled(isOkToSave());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    lineName = tfName.getText();
                    btnSave.setEnabled(isOkToSave());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            tfLength.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        lineLength = Double.valueOf(tfLength.getText());
                    } catch (NumberFormatException ex) {
                        lineLength = null;
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        lineLength = Double.valueOf(tfLength.getText());
                    } catch (NumberFormatException ex) {
                        lineLength = null;
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            tfColor.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    colorName = tfColor.getText();
                    btnSave.setEnabled(isOkToSave());

                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    colorName = tfColor.getText();
                    btnSave.setEnabled(isOkToSave());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });

            // set document filters to text fields
            PlainDocument nameDoc = (PlainDocument) tfName.getDocument();
            nameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT));

            PlainDocument distanceDoc = (PlainDocument) tfLength.getDocument();
            distanceDoc.setDocumentFilter(new utils.MyDocFilter(InputType.DOUBLE));

            PlainDocument colorDoc = (PlainDocument) tfColor.getDocument();
            colorDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT));

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
        tfName = new javax.swing.JTextField();
        cmbType = new javax.swing.JComboBox();
        tfLength = new javax.swing.JTextField();
        lblKm = new javax.swing.JLabel();
        lblLength = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        tfColor = new javax.swing.JTextField();
        pStations = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStations = new javax.swing.JTable();
        btnViewStation = new javax.swing.JButton();
        cmbStation = new javax.swing.JComboBox();
        btnAddStation = new javax.swing.JButton();
        btnRemoveStation = new javax.swing.JButton();

        pDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        lbName.setText("Line Name*");

        lblColor.setText("Color*");

        lblFoundation.setText("Foundation Year");

        ychFoundationYear.setStartYear(1863);
        ychFoundationYear.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ychFoundationYearPropertyChange(evt);
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

        tfLength.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tfLengthPropertyChange(evt);
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

        tfColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfColorActionPerformed(evt);
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
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfColor, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(lblColor)
                    .addComponent(tfColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(pStations, javax.swing.GroupLayout.PREFERRED_SIZE, 495, Short.MAX_VALUE)
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
            deleteSIL.setString(2, lineName);
            deleteSIL.executeUpdate();

            JOptionPane.showInternalMessageDialog(this,
                    "Satation was removed successfully from this line! What a crappy station it was.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

            btnDelete.setEnabled(isOkToDelete());

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(this,
                    "Error code: " + e.getErrorCode() + ". Go figure it yourself!",
                    "Bummer!",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRemoveStationActionPerformed

    private void cmbStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStationActionPerformed

    }//GEN-LAST:event_cmbStationActionPerformed

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        this.lineName = tfName.getText();

        if (lineName == null) {
            btnSave.setEnabled(false);
        } else {
            btnSave.setEnabled(true);
        }
    }//GEN-LAST:event_tfNameActionPerformed

    private void ychFoundationYearPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ychFoundationYearPropertyChange
        this.foundedYear = ychFoundationYear.getYear();
    }//GEN-LAST:event_ychFoundationYearPropertyChange

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        String strType = (String) cmbType.getModel().getSelectedItem();
        this.lineType = (strType.equals("Overground")) ? "O" : "U";
    }//GEN-LAST:event_cmbTypeActionPerformed

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
            insertSIL.setString(2, lineName);
            insertSIL.executeUpdate();

            JOptionPane.showInternalMessageDialog(this,
                    "Satation was added successfully to this line! Oh yeah!",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

            btnDelete.setEnabled(isOkToDelete());

        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(this,
                    "Error code: " + e.getErrorCode() + ". Go figure it yourself!",
                    "Bummer!",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddStationActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        PreparedStatement insertLine;
        PreparedStatement insertColor;
        PreparedStatement updateColor;
        PreparedStatement updateLine;

        try {
            con.setAutoCommit(false);
            if (getMode() == ADD_MODE) {
                insertLine = con.prepareStatement(Queries.INSERT_LINE);
                insertLine.setString(1, lineName);
                insertLine.setInt(2, foundedYear);
                insertLine.setString(3, lineType);
                if (lineLength != null) {
                    insertLine.setDouble(4, lineLength);
                } else {
                    insertLine.setNull(4, java.sql.Types.DOUBLE);
                }
                insertLine.executeUpdate();

                insertColor = con.prepareStatement(Queries.INSERT_COLOR);
                insertColor.setString(1, colorName);
                insertColor.setString(2, lineName);
                insertColor.executeUpdate();

                con.commit();
                JOptionPane.showInternalMessageDialog(this,
                        "Line was added successfully!",
                        "Hooray!",
                        JOptionPane.PLAIN_MESSAGE);
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

                con.commit();
                JOptionPane.showInternalMessageDialog(this,
                        "Line was updated successfully!",
                        "Hooray!",
                        JOptionPane.PLAIN_MESSAGE);
            }

            setMode(EDIT_MODE);
            setActiveness();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 515:
                    JOptionPane.showInternalMessageDialog(this,
                            "Line name \"" + lineName + "\" has already been taken.",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
                default:
                    JOptionPane.showInternalMessageDialog(this,
                            "Error code: " + e.getErrorCode() + ". Go figure it yourself!",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
            }
            System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            if (con != null) {
                try {
                    JOptionPane.showInternalMessageDialog(this,
                            "Transaction is being rolled back. You're screwed! Haha!",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
                    con.rollback();
                } catch (SQLException excep) {
                    JOptionPane.showInternalMessageDialog(this,
                            "Error code: " + e.getErrorCode() + ". Go figure it yourself!",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
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
        PreparedStatement deleteLine;
        try {
            deleteLine = con.prepareStatement(Queries.DELETE_LINE);
            deleteLine.setString(1, lineName);
            deleteLine.executeUpdate();
            JOptionPane.showInternalMessageDialog(this,
                    "Line was deleted successfully! Who needed it anyway?",
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

    private void tfLengthPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfLengthPropertyChange

    }//GEN-LAST:event_tfLengthPropertyChange

    private void tfColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfColorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStation;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRemoveStation;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnViewStation;
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
    private javax.swing.JTextField tfColor;
    private javax.swing.JTextField tfLength;
    private javax.swing.JTextField tfName;
    private com.toedter.calendar.JYearChooser ychFoundationYear;
    // End of variables declaration//GEN-END:variables

    private void setStationTblModel() {
        ArrayList<Column> cols = new ArrayList<>();
        cols
                .add(new Column("ID", "ID", Integer.class
                        ));
        cols.add(
                new Column("Name", "Name", String.class
                ));
        cols.add(
                new Column("Platforms", "platformNum", Integer.class
                ));
        cols.add(
                new Column("Kiosk", "Kiosk", Boolean.class
                ));
        cols.add(
                new Column("Zone", "zoneNumber", Integer.class
                ));

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
            
            btnDelete.setEnabled(isOkToDelete());
        } catch (SQLException ex) {

        }
    }

    private void setVariables() {
        ResultSet rs;
        try {
            PreparedStatement selectLine = con.prepareStatement(Queries.SELECT_LINE_AND_COLOR);
            selectLine.setString(1, lineName);
            rs = selectLine.executeQuery();

            rs.next();
            this.colorName = rs.getString(5)/*color name*/;
            this.foundedYear = rs.getInt("foundedYear");
            this.lineLength = (rs.getDouble("lineLength") != 0) ? rs.getDouble("lineLength") : null;
            this.lineName = rs.getString("name");
            this.lineType = rs.getString("lineType");

        } catch (SQLException ex) {
            Logger.getLogger(Line.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDefaults() {
        tfName.setText(this.lineName);
        tfColor.setText(this.colorName);
        ychFoundationYear.setYear(this.foundedYear);
        cmbType.setSelectedItem((this.lineType.equals("O")) ? "Overground" : "Underground");
        tfLength.setText(String.valueOf(this.lineLength));
    }

    private void setActiveness() {
        if (getMode() == ADD_MODE) {
            tfName.setEnabled(true);
            pStations.setVisible(false);
        } else {
            // edit mode
            tfName.setEnabled(false);
            pStations.setVisible(true);
        }
        btnSave.setEnabled(isOkToSave());
        btnSave.setEnabled(isOkToDelete());
    }

    private boolean isOkToSave() {
        if (lineName == null || lineName.isEmpty()) {
            // saving is not allowed without line name
            btnSave.setToolTipText("The line must have a name and a color");
            return false;
        }
        if (colorName == null || colorName.isEmpty()) {
            // saving is not allowed without color name
            btnSave.setToolTipText("The line must have a name and a color");
            return false;
        }
        btnSave.setToolTipText(null);
        return true;
    }
    
    private boolean isOkToDelete(){
        if (tblStations.getModel().getRowCount() > 0){
            btnDelete.setToolTipText("Deleting the line is not allowed since it has stations");
            return false;
        } 
        return true;
    }

}
