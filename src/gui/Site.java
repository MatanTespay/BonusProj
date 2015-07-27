/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.InputValidator;
import static init.MainClass.con;
import init.MyTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.text.PlainDocument;
import utils.Column;
import static utils.Constants.ADD_MODE;
import static utils.Constants.EDIT_MODE;
import utils.CustomTableModel;
import static utils.HelperClass.setSelectedValue;
import utils.InputType;
import utils.QueryCombobox;

/**
 *
 * @author asus
 */
public class Site extends MyInternalFrame {

    private int siteID;
    private String name;
    private String description;
    private int foundedYear;
    private String type;
    private double exitDistance;
    private double nearSiteDistance;
    private short sataionId;
    private String lineName;
    private short nearSiteID;
    private final String[] SFEColumns = {
        "ID",
        "Station Name",
        "Line Name",
        "Distance"};

    private final String[] SFSColumns = {
        "ID",
        "Name",
        "Foundation Year",
        "Type",
        "Distance"};

    /**
     * Creates new form Site
     *
     * @param title
     * @param type
     * @param siteID
     */
    public Site(String title, String type, int siteID) {
        super(title, type);
        initComponents();
        setMode(EDIT_MODE);
        this.siteID = siteID;
        setVariables();
        buildForm();
        setDefaults();
    }

    public Site(String title, String type) {
        super(title, type);
        initComponents();
        setMode(ADD_MODE);
        this.siteID = 0;
        buildForm();
    }

    private void buildForm() {

        tfDistToExit.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                setExitDistance(tfDistToExit);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setExitDistance(tfDistToExit);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        tfDistToSite.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                setNearDistance(tfDistToSite);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setNearDistance(tfDistToSite);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        tfName.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                setName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        // set document filters
        PlainDocument distanceExitDoc = (PlainDocument) tfDistToExit.getDocument();
        distanceExitDoc.setDocumentFilter(new utils.MyDocFilter(InputType.DOUBLE));

        PlainDocument distanceNearSiteDoc = (PlainDocument) tfDistToSite.getDocument();
        distanceNearSiteDoc.setDocumentFilter(new utils.MyDocFilter(InputType.DOUBLE));

        PlainDocument nameDoc = (PlainDocument) tfName.getDocument();
        nameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT));

        fillCmbType();

        fillCmbStations();
        fillCmbLines();
        fillCmbSites();

        fillNearByExits();
        fillNearBySites();
//        setTableProperties(tblNearbyExits);
//        setTableProperties(tblNearbySites);
        setActiveness();
        super.validators = new ArrayList<InputValidator>() {
            {
                add(new InputValidator(tfDistToExit, utils.InputType.DOUBLE, null, null));
                add(new InputValidator(tfDistToSite, utils.InputType.DOUBLE, null, null));
                add(new InputValidator(tfID, utils.InputType.INT, null, null));
                add(new InputValidator(tfName, utils.InputType.TEXT, null, null));
                add(new InputValidator(taDescription, utils.InputType.TEXT, null, null));
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfName = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        lblFoundation = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        Type = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        ychFoundation = new com.toedter.calendar.JYearChooser();
        cmbType = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNearbyExits = new javax.swing.JTable();
        lblNearbyExits = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNearbySites = new javax.swing.JTable();
        lblNearbySites = new javax.swing.JLabel();
        cmbStation = new javax.swing.JComboBox();
        lblStation = new javax.swing.JLabel();
        lblLine = new javax.swing.JLabel();
        cmbLine = new javax.swing.JComboBox();
        lblDistToExit = new javax.swing.JLabel();
        tfDistToExit = new javax.swing.JTextField();
        btnAddExit = new javax.swing.JButton();
        lblSite = new javax.swing.JLabel();
        cmbNearSite = new javax.swing.JComboBox();
        lblDistToSite = new javax.swing.JLabel();
        tfDistToSite = new javax.swing.JTextField();
        btnAddNearSite = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRemoveExit = new javax.swing.JButton();
        btnRemoveSite = new javax.swing.JButton();

        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        lblDescription.setText("Description");

        tfID.setEnabled(false);
        tfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });

        lblFoundation.setText("Foundation Year");

        lblID.setText("Site ID");

        lblName.setText("Name");

        Type.setText("Type");

        taDescription.setColumns(20);
        taDescription.setRows(5);
        taDescription.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                taDescriptionPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(taDescription);

        ychFoundation.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ychFoundationPropertyChange(evt);
            }
        });

        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });

        tblNearbyExits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblNearbyExits);

        lblNearbyExits.setText("Nearby Exits");

        tblNearbySites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblNearbySites);

        lblNearbySites.setText("Nearby Sites");

        cmbStation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStationActionPerformed(evt);
            }
        });

        lblStation.setText("Station");

        lblLine.setText("Line");

        cmbLine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLineActionPerformed(evt);
            }
        });

        lblDistToExit.setText("Distance (km)");

        btnAddExit.setText("Add exit");
        btnAddExit.setPreferredSize(new java.awt.Dimension(71, 23));
        btnAddExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddExitActionPerformed(evt);
            }
        });

        lblSite.setText("Site");

        cmbNearSite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNearSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNearSiteActionPerformed(evt);
            }
        });

        lblDistToSite.setText("Distance (km)");

        btnAddNearSite.setText("Add site");
        btnAddNearSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNearSiteActionPerformed(evt);
            }
        });

        btnSave.setText("Save Site");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete Site");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRemoveExit.setText("Remove exit");
        btnRemoveExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveExitActionPerformed(evt);
            }
        });

        btnRemoveSite.setText("Remove site");
        btnRemoveSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID)
                            .addComponent(lblName))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(tfID))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFoundation)
                            .addComponent(Type))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbType, 0, 119, Short.MAX_VALUE)
                            .addComponent(ychFoundation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDescription)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNearbyExits)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblStation)
                                            .addComponent(lblLine)
                                            .addComponent(lblDistToExit)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnAddExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNearbySites)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(lblDistToSite))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(btnAddNearSite, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(516, 516, 516)
                                        .addComponent(lblSite)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDistToExit, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbLine, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbStation, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfDistToSite, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRemoveExit)
                                    .addComponent(btnDelete)
                                    .addComponent(btnRemoveSite))
                                .addGap(0, 25, Short.MAX_VALUE))
                            .addComponent(cmbNearSite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblID))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(ychFoundation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName)
                            .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Type)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFoundation)
                        .addGap(35, 35, 35)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDescription))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnDelete)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNearbyExits)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLine))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDistToExit)
                            .addComponent(tfDistToExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveExit)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNearbySites)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbNearSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSite))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDistToSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDistToSite))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddNearSite)
                            .addComponent(btnRemoveSite)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setName() {
        if (tfName.getText() != null && !tfName.getText().equals("")) {

            this.name = tfName.getText();
        }

        btnSave.setEnabled(isOkToSave());

    }

    private void setNearDistance(JTextField field) {
        if (field.getText() != null && !field.getText().equals("")) {
            double d = Double.valueOf(field.getText());

            if (d > utils.Constants.MIN_DISTANCE_OF_EXIT && d < utils.Constants.MAX_DISTANCE_OF_EXIT) {

                        nearSiteDistance = Double.valueOf(field.getText());
                       
            }
        }
        btnAddNearSite.setEnabled(isOkToAddNearSite());
    }
    
    private void setExitDistance(JTextField field) {
        if (field.getText() != null && !field.getText().equals("")) {
            double d = Double.valueOf(field.getText());

            if (d > utils.Constants.MIN_DISTANCE_OF_EXIT && d < utils.Constants.MAX_DISTANCE_OF_EXIT) {
                 exitDistance = Double.valueOf(field.getText());
            }
        }
        btnAddExit.setEnabled(isOkToAddExit());

    }

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        this.name = tfName.getText();
    }//GEN-LAST:event_tfNameActionPerformed

    private void btnAddExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddExitActionPerformed
        PreparedStatement st;
        ComboItem stationItem;
        ComboItem lineItem;
        int stationNumber;
        String lineName;
        double distance;

        try {
            stationItem = (ComboItem) cmbStation.getSelectedItem();
            stationNumber = Integer.parseInt(stationItem.getKey().toString());
            lineItem = (ComboItem) cmbLine.getSelectedItem();
            lineName = (String) lineItem.getKey();
            distance = this.exitDistance;
            //distance = Double.parseDouble(tfDistToExit.getText());

            st = con.prepareStatement("INSERT INTO tblSiteFromExit VALUES (?,?,?,?)");
            st.setInt(1, siteID);
            st.setInt(2, stationNumber);
            st.setString(3, lineName);
            st.setDouble(4, distance);
            st.executeUpdate();
            fillNearByExits();

        } catch (SQLException | NullPointerException ex) {
            //            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddExitActionPerformed

    private void btnAddNearSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNearSiteActionPerformed
        PreparedStatement st;
        ComboItem siteItem;
        int otherSiteNumber;
        double distance;

        try {
            siteItem = (ComboItem) cmbNearSite.getSelectedItem();
            otherSiteNumber = Integer.parseInt(siteItem.getKey().toString());
            distance = Double.parseDouble(tfDistToExit.getText());

            st = con.prepareStatement("INSERT INTO tblSiteFromSite VALUES (?,?,?)");
            st.setInt(1, siteID);
            st.setInt(2, otherSiteNumber);
            st.setDouble(3, distance);
            st.executeUpdate();
            fillNearBySites();

        } catch (SQLException | NullPointerException ex) {
            //            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddNearSiteActionPerformed

    private void btnRemoveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveExitActionPerformed
        PreparedStatement st;
        int stationNumber;
        String lineName;
        int selectedRow;
        TableModel model;

        try {
            model = tblNearbySites.getModel();
            selectedRow = tblNearbySites.getSelectedRow();

            stationNumber = Integer.parseInt((String) model.getValueAt(selectedRow, 0));
            lineName = String.valueOf(model.getValueAt(selectedRow, 1));

            st = con.prepareStatement("DELETE FROM tblSite WHERE "
                    + " siteID = ? and stationID = ? and lineName = ?");
            st.setInt(1, siteID);
            st.setInt(2, stationNumber);
            st.setString(3, lineName);
            st.executeUpdate();
            fillNearByExits();

        } catch (SQLException | NullPointerException ex) {
            //            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveExitActionPerformed

    private void btnRemoveSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSiteActionPerformed
        PreparedStatement st;
        int otherSiteNum;

        try {
            otherSiteNum = Integer.parseInt((String) tblNearbySites.getModel()
                    .getValueAt(tblNearbySites.getSelectedRow(), 0));
            st = con.prepareStatement("DELETE FROM tblSiteFromSite WHERE "
                    + " siteID2 = ?");
            st.setInt(1, otherSiteNum);
            st.executeUpdate();
            fillNearBySites();

        } catch (SQLException | NullPointerException ex) {
            //            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveSiteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        PreparedStatement st;

        try {

            st = con.prepareStatement("INSERT INTO tblSite VALUES (?,?,?,?,?)");
            st.setInt(1, this.siteID);
            st.setString(2, this.name);
            st.setString(3, this.description);
            st.setInt(4, this.foundedYear);
            st.setString(5, this.type);
            st.executeUpdate();

        } catch (SQLException | NullPointerException ex) {
            //            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        PreparedStatement st;

        try {
            st = con.prepareStatement("DELETE FROM tblSite WHERE "
                    + "number = ?");
            st.setInt(1, this.siteID);
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDActionPerformed
        this.siteID = Integer.valueOf(tfID.getText());
    }//GEN-LAST:event_tfIDActionPerformed

    private void ychFoundationPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ychFoundationPropertyChange
        this.foundedYear = ychFoundation.getYear();
    }//GEN-LAST:event_ychFoundationPropertyChange

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        ComboItem typeItem = (ComboItem) cmbType.getSelectedItem();
        this.type = (String) typeItem.getKey();
    }//GEN-LAST:event_cmbTypeActionPerformed

    private void taDescriptionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_taDescriptionPropertyChange
        this.description = taDescription.getText();
    }//GEN-LAST:event_taDescriptionPropertyChange

    private void cmbStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStationActionPerformed
        // TODO add your handling code here:
        ComboItem item = ((ComboItem) cmbStation.getSelectedItem());
        if (item != null) {
            this.sataionId = (Short) (item).getKey();
        }

    }//GEN-LAST:event_cmbStationActionPerformed

    private void cmbLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLineActionPerformed
        // TODO add your handling code here:
        ComboItem item = ((ComboItem) cmbLine.getSelectedItem());
        if (item != null) {
            this.lineName = (String) (item).getKey();
        }

        btnAddExit.setEnabled(isOkToAddExit());
    }//GEN-LAST:event_cmbLineActionPerformed

    private void cmbNearSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNearSiteActionPerformed
        // TODO add your handling code here:
        ComboItem item = ((ComboItem) cmbNearSite.getSelectedItem());
        if (item != null) {
            this.nearSiteID = (Short) (item).getKey();
        }

        btnAddNearSite.setEnabled(isOkToAddNearSite());
    }//GEN-LAST:event_cmbNearSiteActionPerformed

    private boolean isOkToAddExit() {

        return !(lineName == null || exitDistance == 0);
    }

    private boolean isOkToAddNearSite() {

        return !(nearSiteID == 0 || exitDistance == 0);
    }

    private boolean isOkToSave() {
        if (name == null || lineName.isEmpty()) {
            btnSave.setToolTipText("The site must have a name");
            return false;
        }

        btnSave.setToolTipText(null);
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Type;
    private javax.swing.JButton btnAddExit;
    private javax.swing.JButton btnAddNearSite;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRemoveExit;
    private javax.swing.JButton btnRemoveSite;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbLine;
    private javax.swing.JComboBox cmbNearSite;
    private javax.swing.JComboBox cmbStation;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblDistToExit;
    private javax.swing.JLabel lblDistToSite;
    private javax.swing.JLabel lblFoundation;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNearbyExits;
    private javax.swing.JLabel lblNearbySites;
    private javax.swing.JLabel lblSite;
    private javax.swing.JLabel lblStation;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tblNearbyExits;
    private javax.swing.JTable tblNearbySites;
    private javax.swing.JTextField tfDistToExit;
    private javax.swing.JTextField tfDistToSite;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfName;
    private com.toedter.calendar.JYearChooser ychFoundation;
    // End of variables declaration//GEN-END:variables

    private void setVariables() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement(utils.Queries.SELECT_SITE_BY_SITEID);
            st.setInt(1, this.siteID);
            rs = st.executeQuery();

            rs.next();
            this.name = rs.getString("name");
            this.description = rs.getString("siteDescription");
            this.foundedYear = rs.getInt("foundedYear");
            this.type = rs.getString("siteType");

        } catch (SQLException ex) {
            Logger.getLogger(Site.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbType() {
        PreparedStatement s;
        ResultSet rs;
        try {

            s = con.prepareCall("Select * From tblSiteType");
            cmbType.setModel(new QueryCombobox(cmbType, String.class, s));

//            rs = s.executeQuery();
//            ArrayList<ComboItem> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(new ComboItem(rs.getString("siteType"), rs.getString("siteType")));
//            }
//            Collections.sort(items);
//            //items.add(0, null);
//            cmbType.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Site.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillNearByExits() {
        PreparedStatement q_getNearExits;
        ResultSet rs;
        CustomTableModel nearByExitsModel;
        ArrayList<Column> cols = new ArrayList<>();
        cols.add(new Column("ID", "stationID", Short.class));
        cols.add(new Column("Station Name", "Name", String.class));
        cols.add(new Column("Line Name", "lineName", String.class));
        cols.add(new Column("Distance", "Distance", Double.class));

        try {

            q_getNearExits = con.prepareStatement(utils.Queries.SELECT_SFE_BY_SITEID);
            q_getNearExits.setInt(1, siteID);
            nearByExitsModel = new CustomTableModel(tblNearbyExits, cols, q_getNearExits);
            HashSet<JButton> tableButtons = new HashSet<>();
            tableButtons.add(this.btnRemoveExit);
            nearByExitsModel.bindComboBox(cmbStation, 0, 1);
            nearByExitsModel.bindButtons(tableButtons);
            this.tblNearbyExits.setModel(nearByExitsModel);
            nearByExitsModel.fillTable();

            //rs = st.executeQuery();
//            ArrayList<Object[]> rows = new ArrayList();
//            while (rs.next()) {
//                Object[] row = {rs.getString("stationID"), rs.getString("name")/*station name*/,
//                    rs.getString("lineName"), rs.getString("distance")};
//                rows.add(row);
//            }
//            MyTableModel tableModel = new MyTableModel(SFEColumns, rows, null);
//            tblNearbyExits.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Site.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillNearBySites() {
//        PreparedStatement st;
//        ResultSet rs;
        try {

            PreparedStatement q_getNearSites;
            ResultSet rs;
            CustomTableModel nearBySiteModel;
            ArrayList<Column> cols = new ArrayList<>();
            cols.add(new Column("ID", "ID", Short.class));
            cols.add(new Column("Site Name", "Name", String.class));
            cols.add(new Column("Foundation Year", "foundedYear", Short.class));
            cols.add(new Column("Distance", "Distance", Double.class));

            q_getNearSites = con.prepareStatement(utils.Queries.SELECT_SFS_BY_SITEID);
            q_getNearSites.setInt(1, siteID);
            nearBySiteModel = new CustomTableModel(tblNearbySites, cols, q_getNearSites);
            HashSet<JButton> tableButtons = new HashSet<>();
            tableButtons.add(this.btnRemoveSite);
            nearBySiteModel.bindComboBox(cmbNearSite, 0, 1);
            nearBySiteModel.bindButtons(tableButtons);
            this.tblNearbySites.setModel(nearBySiteModel);
            nearBySiteModel.fillTable();

//            st = con.prepareStatement("SELECT * FROM tblSiteFromSite As SFS "
//                    + "join tblSite As S on SFS.siteID2 = S.ID WHERE S.ID = ?");
//            st.setInt(1, siteID);
//            rs = st.executeQuery();
//            ArrayList<Object[]> rows = new ArrayList();
//            while (rs.next()) {
//                Object[] row = {rs.getString("siteID2"), rs.getString("name"),
//                    rs.getString("foundedYear"), rs.getString("siteType"),
//                    rs.getString("distance")};
//                rows.add(row);
//
//                MyTableModel tableModel = new MyTableModel(SFSColumns, rows, null);
//                tblNearbySites.setModel(tableModel);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(Site.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbStations() {
//        Statement s;
//        ResultSet rs;
        try {

            PreparedStatement getAllStations = con.prepareStatement("SELECT ID, name FROM tblStation");

            // set models to comboboxes   
            cmbStation.setModel(new QueryCombobox(cmbStation, Short.class, getAllStations));

//            s = con.createStatement();
//            rs = s.executeQuery("SELECT ID, name FROM tblStation");
//            ArrayList<ComboItem> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(new ComboItem(rs.getInt("ID"), rs.getString("name")));
//            }
//            Collections.sort(items);
//            items.add(0, null);
//            cmbStation.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Site.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbLines() {
//        PreparedStatement st;
//        ResultSet rs;
        try {
            PreparedStatement getAllLines = con.prepareStatement(utils.Queries.SELECT_LINE_NAME_FOR_STATION_NAME);
            QueryCombobox qCb = new QueryCombobox(cmbLine, cmbStation, String.class, getAllLines);
            HashSet<JButton> tableButtons = new HashSet<>();
            tableButtons.add(this.btnRemoveExit);
            tableButtons.add(this.btnAddExit);
            qCb.bindButtons(tableButtons);
            cmbLine.setModel(qCb);

//            if (stationID == 0) {
//                // show all lines
//                st = con.prepareStatement("SELECT lineName FROM tblStationInLine");
//            } else {
//                st = con.prepareStatement("SELECT SIL.lineName FROM tblStationInLine "
//                        + "As SIL WHERE SIL.stationID = ?");
//                st.setInt(1, stationID);
//            }
//            rs = st.executeQuery();
//
//            ArrayList<ComboItem> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(new ComboItem(rs.getString("lineName"), rs.getString("lineName")));
//            }
//            Collections.sort(items);
//            items.add(0, null);
//            cmbLine.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Site.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbSites() {
//        Statement s;
//        ResultSet rs;
        try {
            PreparedStatement getAllSites = con.prepareStatement(utils.Queries.SELECT_AllSITES);

            // set models to comboboxes   
            cmbNearSite.setModel(new QueryCombobox(cmbNearSite, Short.class, getAllSites));

//            s = con.createStatement();
//            rs = s.executeQuery("SELECT ID, name FROM tblSite");
//            ArrayList<ComboItem> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(new ComboItem(rs.getInt("ID"), rs.getString("name")));
//            }
//            Collections.sort(items);
//            items.add(0, null);
//            cmbSite.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Site.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDefaults() {
        tfID.setText(String.valueOf(this.siteID));
        tfName.setText(this.name);
        ychFoundation.setYear(this.foundedYear);
        setSelectedValue(cmbType, this.type);
        taDescription.setText(this.description);
    }

    private void setActiveness() {
        if (getMode() == ADD_MODE) {
            // key fields
            tfID.setEnabled(true);

            // control buttons
            btnSave.setVisible(true);
            btnDelete.setEnabled(false);

        } else { // edit mode
            // key fields
            tfID.setEnabled(false);

            // control buttons
            btnSave.setEnabled(false);
            btnDelete.setEnabled(true);

        }
    }
}
