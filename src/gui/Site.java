/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.InputValidator;
import init.MainClass;
import static init.MainClass.con;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
import utils.Queries;
import utils.QueryCombobox;

/**
 *
 * @author asus
 */
public class Site extends MyInternalFrame {

    private short siteID;
    private String siteName;
    private String description;
    private int foundedYear;
    private String type;
    private double exitDistance;
    private double nearSiteDistance;
    private short stationID;
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
    public Site(String title, String type, short siteID) {
        super(title, type);
        initComponents();
        setMode(EDIT_MODE);
        this.siteID = siteID;
        exitDistance = -1;
        nearSiteDistance = -1;
        setVariables();
        buildForm();
        setDefaults();
    }

    public Site(String title, String type) {
        super(title, type);
        initComponents();
        setMode(ADD_MODE);
        this.siteID = 0;
        this.tfID.setText("(auto number)");
        exitDistance = -1;
        nearSiteDistance = -1;
        buildForm();
        cmbType.setSelectedIndex(0);
    }

    private void buildForm() {

        taDescription.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                setDescription();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setDescription();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

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
        nameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.NAME));

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
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        sfsPanel = new javax.swing.JPanel();
        btnAddNearSite = new javax.swing.JButton();
        tfDistToSite = new javax.swing.JTextField();
        cmbNearSite = new javax.swing.JComboBox();
        btnRemoveSite = new javax.swing.JButton();
        lblSite = new javax.swing.JLabel();
        lblDistToSite = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNearbySites = new javax.swing.JTable();
        sfePanel = new javax.swing.JPanel();
        cmbStation = new javax.swing.JComboBox();
        btnAddExit = new javax.swing.JButton();
        btnRemoveExit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNearbyExits = new javax.swing.JTable();
        lblLine = new javax.swing.JLabel();
        lblDistToExit = new javax.swing.JLabel();
        lblStation = new javax.swing.JLabel();
        cmbLine = new javax.swing.JComboBox();
        tfDistToExit = new javax.swing.JTextField();

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

        ychFoundation.setEndYear(2015);
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

        sfsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nearby Sites"));

        btnAddNearSite.setText("Add site");
        btnAddNearSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNearSiteActionPerformed(evt);
            }
        });

        cmbNearSite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNearSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNearSiteActionPerformed(evt);
            }
        });

        btnRemoveSite.setText("Remove site");
        btnRemoveSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSiteActionPerformed(evt);
            }
        });

        lblSite.setText("Site");

        lblDistToSite.setText("Distance (km)");

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

        javax.swing.GroupLayout sfsPanelLayout = new javax.swing.GroupLayout(sfsPanel);
        sfsPanel.setLayout(sfsPanelLayout);
        sfsPanelLayout.setHorizontalGroup(
            sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sfsPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sfsPanelLayout.createSequentialGroup()
                        .addComponent(btnAddNearSite, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoveSite))
                    .addGroup(sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sfsPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblDistToSite)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfDistToSite))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sfsPanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblSite)
                            .addGap(51, 51, 51)
                            .addComponent(cmbNearSite, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        sfsPanelLayout.setVerticalGroup(
            sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sfsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(sfsPanelLayout.createSequentialGroup()
                        .addGroup(sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbNearSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSite))
                        .addGap(10, 10, 10)
                        .addGroup(sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDistToSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDistToSite))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sfsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddNearSite)
                            .addComponent(btnRemoveSite)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        sfePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nearby Exits"));

        cmbStation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStationActionPerformed(evt);
            }
        });

        btnAddExit.setText("Add exit");
        btnAddExit.setPreferredSize(new java.awt.Dimension(71, 23));
        btnAddExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddExitActionPerformed(evt);
            }
        });

        btnRemoveExit.setText("Remove exit");
        btnRemoveExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveExitActionPerformed(evt);
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

        lblLine.setText("Line");

        lblDistToExit.setText("Distance (km)");

        lblStation.setText("Station");

        cmbLine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sfePanelLayout = new javax.swing.GroupLayout(sfePanel);
        sfePanel.setLayout(sfePanelLayout);
        sfePanelLayout.setHorizontalGroup(
            sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sfePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sfePanelLayout.createSequentialGroup()
                        .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStation)
                            .addComponent(lblLine))
                        .addGap(41, 41, 41)
                        .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbLine, 0, 114, Short.MAX_VALUE)
                            .addComponent(cmbStation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(sfePanelLayout.createSequentialGroup()
                        .addComponent(lblDistToExit)
                        .addGap(18, 18, 18)
                        .addComponent(tfDistToExit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sfePanelLayout.createSequentialGroup()
                        .addComponent(btnAddExit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveExit)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        sfePanelLayout.setVerticalGroup(
            sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sfePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(sfePanelLayout.createSequentialGroup()
                        .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLine))
                        .addGap(10, 10, 10)
                        .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDistToExit)
                            .addComponent(tfDistToExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sfePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveExit)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sfePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblDescription)
                                    .addGap(626, 626, 626))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblID)
                                    .addComponent(lblName))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFoundation)
                                    .addComponent(Type))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbType, 0, 119, Short.MAX_VALUE)
                                    .addComponent(ychFoundation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(sfsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblName)
                                .addGap(5, 5, 5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Type))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFoundation)
                        .addGap(35, 35, 35)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sfePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sfsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setDescription() {
        String textDescription = taDescription.getText();
        if (textDescription.trim().length() == 0) {
            this.description = "";
        } else if (textDescription.length() > 150) {
            this.description = null;
        } else {
            this.description = textDescription;
        }

        btnSave.setEnabled(isOkToSave());

    }

    private void setName() {
        if (tfName.getText() != null && !tfName.getText().equals("")) {

            this.siteName = tfName.getText();
        } else {
            this.siteName = null;
        }

        btnSave.setEnabled(isOkToSave());

    }

    private void setNearDistance(JTextField field) {

        if (field.getText() != null && !field.getText().equals("")) {
            double d = Double.valueOf(field.getText());

            if (d > utils.Constants.MIN_SFS_DISTANCE && d <= utils.Constants.MAX_SFS_DISTANCE) {

                nearSiteDistance = d;

            } else {
                nearSiteDistance = -1;
            }

        } else {
            nearSiteDistance = -1;
        }
        btnAddNearSite.setEnabled(isOkToAddNearSite());
    }

    private void setExitDistance(JTextField field) {
        if (field.getText() != null && !field.getText().equals("")) {
            double d = Double.valueOf(field.getText());

            if (d > utils.Constants.MIN_SFE_DISTANCE && d <= utils.Constants.MAX_SFE_DISTANCE) {
                exitDistance = d;
            } else {
                exitDistance = -1;
            }
        } else {
            exitDistance = -1;
        }
        btnAddExit.setEnabled(isOkToAddExit());

    }

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        this.siteName = tfName.getText();
    }//GEN-LAST:event_tfNameActionPerformed

    private void btnAddExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddExitActionPerformed
        PreparedStatement st;
        ComboItem stationItem;
        ComboItem lineItem;
        short stationNumber;
        String lineName;
        double distance;

        try {

            stationItem = (ComboItem) cmbStation.getSelectedItem();
            stationNumber = Short.valueOf(stationItem.getKey().toString());

            lineItem = (ComboItem) cmbLine.getSelectedItem();
            lineName = (String) lineItem.getKey();

            distance = this.exitDistance;

            st = con.prepareStatement(utils.Queries.INSERT_SFE);
            st.setShort(1, siteID);
            st.setShort(2, stationNumber);
            st.setString(3, lineName);
            st.setDouble(4, distance);
            int result = st.executeUpdate();
            if (result == 1) {
                JOptionPane.showInternalMessageDialog(this,
                        "Exit " + stationItem.getLabel() + " - " + lineName + " was saved successfully.",
                        "Hooray!",
                        JOptionPane.PLAIN_MESSAGE);

                fillNearByExits();

            } else {
                JOptionPane.showInternalMessageDialog(this,
                        "There was some errors adding the exit\n"
                        + "Between " + stationItem.getLabel() + "and " + lineName
                        + "Please Dont try later.",
                        "Bummer!",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            switch (ex.getErrorCode()) {
                case 2627:
                    JOptionPane.showInternalMessageDialog(this,
                            "This exit  already exists. Please be original and pick another option.",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);

                    Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddExitActionPerformed

    private void btnRemoveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveExitActionPerformed
        PreparedStatement st;
        int stationNumber;
        String lineName;
        int selectedRow;
        TableModel model;

        try {
           
        
            model = tblNearbyExits.getModel();
            selectedRow = tblNearbyExits.getSelectedRow();

            stationNumber = Short.valueOf(model.getValueAt(selectedRow, 0).toString());
            String stationName = model.getValueAt(selectedRow, 1).toString();
            lineName = String.valueOf(model.getValueAt(selectedRow, 2));

            st = con.prepareStatement(utils.Queries.DELETE_SFE);
            st.setInt(1, siteID);
            st.setInt(2, stationNumber);
            st.setString(3, lineName);
            int result = st.executeUpdate();
            
            if (result == 1) {
                JOptionPane.showInternalMessageDialog(this,
                        "Exit " + stationName + " - " + lineName + " was removed successfully.",
                        "Hooray!",
                        JOptionPane.PLAIN_MESSAGE);

                fillNearByExits();

            } else {
                JOptionPane.showInternalMessageDialog(this,
                        "There was some errors removing the exit\n"
                        + "Between " +  stationName + " and " + lineName
                        + "Please Dont try later.",
                        "Bummer!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NullPointerException ex) {
                     Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        PreparedStatement st;
        PreparedStatement getSiteID;
        try {

            if (getMode() == ADD_MODE) {
                st = con.prepareStatement(utils.Queries.INSERT_SITE);
            } else {
                st = con.prepareStatement(utils.Queries.UPDATE_SITE);
            }

            st.setString(1, this.siteName);
            st.setString(2, this.description);
            st.setInt(3, this.foundedYear);
            st.setString(4, this.type);

            if (getMode() == ADD_MODE) {

                st.executeUpdate();

                //get the id of the inserted site
                getSiteID = con.prepareStatement(Queries.SELECT_SITEID_BY_NAME);
                getSiteID.setString(1, siteName);

                ResultSet rs = getSiteID.executeQuery();
                if (rs.next()) {
                    this.siteID = rs.getShort("ID");
                    tfID.setText(String.valueOf(this.siteID));

                    setMode(EDIT_MODE);
                    setActiveness();

                    JOptionPane.showInternalMessageDialog(this,
                            "Site \"" + siteName + "\" was saved successfully.",
                            "Hooray!",
                            JOptionPane.PLAIN_MESSAGE);

                } else {
                    JOptionPane.showInternalMessageDialog(this,
                            "There was an error retrieving the saved \"" + siteName + "\" id.",
                            "Bummer!",
                            JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                st.setShort(5, siteID);

                int result = st.executeUpdate();

                if (result == 1) {
                    JOptionPane.showInternalMessageDialog(this,
                            "Site \"" + siteName + "\" was saved successfully.",
                            "Hooray!",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showInternalMessageDialog(this,
                            "There was some errors updauting the site\n"
                            + "Please Dont try later.",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException ex) {
            switch (ex.getErrorCode()) {
                case 2627:
                    JOptionPane.showInternalMessageDialog(this,
                            "Station name " + siteName + "already exists. Please be original and pick another name.",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);
            }
            System.err.println("Error code: " + ex.getErrorCode() + "\nError Message: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        PreparedStatement st;

        try {
            st = con.prepareStatement(utils.Queries.DELETE_SITE);
            st.setShort(1, this.siteID);
            int result = st.executeUpdate();

            if (result == 1) {
                JOptionPane.showInternalMessageDialog(this,
                        "Site \"" + siteName + "\" was deleted successfully.",
                        "Hooray!",
                        JOptionPane.PLAIN_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showInternalMessageDialog(this,
                        "There was some errors deleting the site\n"
                        + "Please Dont try later.",
                        "Bummer!",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {

            System.err.println("Error code: " + ex.getErrorCode() + "\nError Message: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDActionPerformed
        this.siteID = Short.valueOf(tfID.getText());
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
            this.stationID = (Short) (item).getKey();
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

    private void btnRemoveSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSiteActionPerformed
        PreparedStatement st;
        int selectedRow;
        TableModel model;
        short otherSiteId;

        try {

            model = tblNearbySites.getModel();
            selectedRow = tblNearbySites.getSelectedRow();

            otherSiteId = Short.valueOf(model.getValueAt(selectedRow, 0).toString());

            st = con.prepareStatement(utils.Queries.DELETE_SFS);
            st.setInt(1, siteID);
            st.setInt(2, otherSiteId);

            int result = st.executeUpdate();

            if (result == 1) {
                JOptionPane.showInternalMessageDialog(this,
                    "Conection " + siteName + " - " + model.getValueAt(selectedRow, 1) + " was removed successfully.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

                fillNearBySites();

            } else {
                JOptionPane.showInternalMessageDialog(this,
                    "There was some errors adding the connection\n"
                    + "Between " +  siteName + " and " + model.getValueAt(selectedRow, 1)
                    + "Please Dont try later.",
                    "Bummer!",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveSiteActionPerformed

    private void btnAddNearSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNearSiteActionPerformed
        PreparedStatement st;
        Connection con ;
        ComboItem siteItem;
        short otherSiteNumber;
        double distance;

        try {
            con = MainClass.con;
            con.setAutoCommit(false);
            st = con.prepareStatement(utils.Queries.INSERT_SFS);
            
            siteItem = (ComboItem) cmbNearSite.getSelectedItem();
            otherSiteNumber = Short.valueOf(siteItem.getKey().toString());
            distance = Double.parseDouble(tfDistToSite.getText());

            st.setShort(1, siteID);
            st.setShort(2, otherSiteNumber);
            st.setDouble(3, distance);
            
            st.addBatch();
            
            st.setShort(1, otherSiteNumber);
            st.setShort(2, siteID);            
            st.setDouble(3, distance);
            st.addBatch();
            
            int[] result = st.executeBatch();
            con.commit();
            

            if (result.length == 2) {
                JOptionPane.showInternalMessageDialog(this,
                    "Near site : "  + siteItem.getLabel() + " was saved successfully.",
                    "Hooray!",
                    JOptionPane.PLAIN_MESSAGE);

                fillNearBySites();

            } else {
                JOptionPane.showInternalMessageDialog(this,
                    "There was some errors adding the site\n"
                    + "Between " +  siteName + " and " + siteItem.getLabel()
                    + "\nPlease Dont try later.",
                    "Bummer!",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            switch (ex.getErrorCode()) {
                case 2627:
                    JOptionPane.showInternalMessageDialog(this,
                            "This site  already exists. Please be original and pick another option.",
                            "Bummer!",
                            JOptionPane.ERROR_MESSAGE);

                    Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddNearSiteActionPerformed

    private boolean isOkToAddExit() {
        boolean result = !(lineName == null || exitDistance < 0);
        return result;
    }

    private boolean isOkToAddNearSite() {

        boolean result = !(nearSiteID == 0 || nearSiteDistance < 0);
        return result;
    }

    private boolean isOkToSave() {
        if (siteName == null || lineName == null || lineName.isEmpty() || description == null) {
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
    private javax.swing.JLabel lblSite;
    private javax.swing.JLabel lblStation;
    private javax.swing.JPanel sfePanel;
    private javax.swing.JPanel sfsPanel;
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
            this.siteName = rs.getString("name");
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
            
//            ComboItem typeItem = (ComboItem) cmbType.getSelectedItem();
//            this.type = (String) typeItem.getKey();
            
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
            //nearByExitsModel.bindComboBox(cmbStation, 0, 1);
            
            nearByExitsModel.bindButtons(tableButtons);
            this.tblNearbyExits.setModel(nearByExitsModel);
            nearByExitsModel.fillTable();

            //rs = st.executeQuery();
//            ArrayList<Object[]> rows = new ArrayList();
//            while (rs.next()) {
//                Object[] row = {rs.getString("stationID"), rs.getString("siteName")/*station siteName*/,
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
//                Object[] row = {rs.getString("siteID2"), rs.getString("siteName"),
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

            PreparedStatement getAllStations = con.prepareStatement(utils.Queries.SELECT_ONLY_STATION_WITH_LINES);
            QueryCombobox cqb = new QueryCombobox(cmbStation, Short.class, getAllStations);
            // set models to comboboxes   
            cmbStation.setModel(cqb);
            cmbStation.setSelectedIndex(0);

        } catch (SQLException ex) {
            Logger.getLogger(Site.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbLines() {
//        PreparedStatement st;
//        ResultSet rs;
        try {
            PreparedStatement getAllLines = con.prepareStatement(utils.Queries.SELECT_LINES_OR_STATION__BY_NAME);
            QueryCombobox qCb = new QueryCombobox(cmbLine, cmbStation, String.class, getAllLines);
            HashSet<JButton> tableButtons = new HashSet<>();
            qCb.bindButtons(tableButtons);
            cmbLine.setModel(qCb);
            cmbLine.setSelectedIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(Site.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbSites() {
//        Statement s;
//        ResultSet rs;
        try {
            PreparedStatement getAllSites = con.prepareStatement(utils.Queries.SELECT_ALL_SITES);
            getAllSites.setInt(1, this.siteID);
            
            // set models to comboboxes   
            cmbNearSite.setModel(new QueryCombobox(cmbNearSite, Short.class, getAllSites));
            cmbNearSite.setSelectedIndex(0);
//            s = con.createStatement();
//            rs = s.executeQuery("SELECT ID, siteName FROM tblSite");
//            ArrayList<ComboItem> items = new ArrayList<>();
//            while (rs.next()) {
//                items.add(new ComboItem(rs.getInt("ID"), rs.getString("siteName")));
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
        tfName.setText(this.siteName);
        ychFoundation.setYear(this.foundedYear);
        setSelectedValue(cmbType, this.type);
        
        taDescription.setText(this.description);
    }

    private void setActiveness() {
        tfID.setEnabled(false);

        if (getMode() == ADD_MODE) {

            // control buttons
            btnSave.setEnabled(false);
            btnDelete.setEnabled(false);
            tfDistToExit.setEditable(false);
            tfDistToSite.setEditable(false);
        } else { // edit mode

            // control buttons
            btnSave.setEnabled(true);
            btnDelete.setEnabled(true);
            tfDistToExit.setEditable(true);
            tfDistToSite.setEditable(true);
        }
    }
}
