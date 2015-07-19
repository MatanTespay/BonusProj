/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import static init.MainClass.con;
import init.MyTableModel;
import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import static utils.Constants.ADD_MODE;
import static utils.Constants.EDIT_MODE;
import static utils.Constants.OYSTER;
import static utils.Constants.PAPER;
import static utils.HelperClass.setSelectedValue;
import static utils.HelperClass.setTableProperties;

/**
 *
 * @author asus
 */
public class Card extends MyInternalFrame {

    private int cardNumber;
    private Date purchaseDate;
    private boolean type;
    private ImageIcon picture;
    private JFileChooser picFileChooser;
    private boolean isTourist;
    private final String[] programColumns = {"#", "Zone", "Length"};

    ;
    
    /**
     * Creates new form Card
     *
     * @param title
     * @param type
     * @param cardNumber
     * @param purchaseDate
     */
    public Card(String title, String type, int cardNumber, Date purchaseDate) {
        super(title, type);
        setMode(EDIT_MODE);
        this.cardNumber = cardNumber;
        this.purchaseDate = purchaseDate;
        setVariables();
        buildForm();
        setDefaults();
    }

    public Card(String title, String type) {
        super(title, type);
        setMode(ADD_MODE);
        this.purchaseDate = null;
        buildForm();
    }

    private void buildForm() {
        initComponents();
        fillCmbCard();
        fillCmbZone();
        fillCmbLength();
        setTableProperties(tblPrograms);
        setActiveness();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCardNumber = new javax.swing.JLabel();
        lblPurchaseDate = new javax.swing.JLabel();
        cmbCard = new javax.swing.JComboBox();
        cmbType = new javax.swing.JComboBox();
        cmbPurchaseDate = new javax.swing.JComboBox();
        lblType = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrograms = new javax.swing.JTable();
        lblPrograms = new javax.swing.JLabel();
        lblPicture = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        cmbLength = new javax.swing.JComboBox();
        lblZone = new javax.swing.JLabel();
        lblLength = new javax.swing.JLabel();
        cmbZone = new javax.swing.JComboBox();
        btnRemove = new javax.swing.JButton();
        chbIsTourist = new javax.swing.JCheckBox();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        lblCardNumber.setText("Card Number");

        lblPurchaseDate.setText("Purchase Date");

        cmbCard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCardActionPerformed(evt);
            }
        });

        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Paper", "Oyster" }));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });

        cmbPurchaseDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPurchaseDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPurchaseDateActionPerformed(evt);
            }
        });

        lblType.setText("Type");

        tblPrograms.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPrograms);

        lblPrograms.setText("Programs:");

        btnBrowse.setText("Browse Image");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        cmbLength.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblZone.setText("Zone");

        lblLength.setText("Length");

        cmbZone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        chbIsTourist.setText("Tourist");
        chbIsTourist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbIsTouristActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");

        btnUpdate.setText("Update");

        btnDelete.setText("Delete");

        btnCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnRemove)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAdd))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblLength)
                                                    .addComponent(lblZone))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cmbLength, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cmbZone, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCardNumber)
                                            .addComponent(lblPurchaseDate)
                                            .addComponent(lblType))
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbPurchaseDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(117, 117, 117))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(chbIsTourist)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrograms)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBrowse))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCardNumber)
                            .addComponent(cmbCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPurchaseDate)
                            .addComponent(cmbPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblType))
                        .addGap(18, 18, 18)
                        .addComponent(chbIsTourist)))
                .addGap(12, 12, 12)
                .addComponent(lblPrograms)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbZone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblZone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLength))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnRemove)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCardActionPerformed
        try {
            ComboItem cardItem = (ComboItem) cmbCard.getSelectedItem();
            this.cardNumber = (int) cardItem.getKey();
            cmbPurchaseDate.setEnabled(true);
            fillCmbPurchaseDate(cardNumber);
        } catch (NullPointerException ex) {
            // no item is chosen
            cmbPurchaseDate.setEnabled(false);
        }
    }//GEN-LAST:event_cmbCardActionPerformed

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        String strType = (String) cmbType.getSelectedItem();
        this.type = (strType.equals("Oyster")) ? OYSTER : PAPER;
        modifyFormToCardType();
    }//GEN-LAST:event_cmbTypeActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        int o = picFileChooser.showOpenDialog(this);
        if (o == JFileChooser.APPROVE_OPTION) {
            File f = picFileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon("" + f);
            lblPicture.setIcon(icon);
            this.picture = icon;
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String tableName = (this.type == PAPER) ? "tblPaperCardAreas" : "tblOysterCardAreas";
        ComboItem zoneItem;
        ComboItem lengthItem;
        int zone;
        double length;

        PreparedStatement st;
        try {
            zoneItem = (ComboItem) cmbZone.getSelectedItem();
            lengthItem = (ComboItem) cmbLength.getSelectedItem();
            zone = (int) zoneItem.getKey();
            length = (double) lengthItem.getKey();

            st = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?,?,?,?)");
            st.setInt(1, this.cardNumber);
            st.setDate(2, this.purchaseDate);
            st.setInt(3, zone);
            st.setDouble(4, length);
            st.executeUpdate();
            fillPrograms();

        } catch (SQLException | NullPointerException ex) {
//            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cmbPurchaseDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPurchaseDateActionPerformed
        ComboItem purchaseDateItem = (ComboItem) cmbPurchaseDate.getSelectedItem();
        this.purchaseDate = (Date) purchaseDateItem.getKey();
    }//GEN-LAST:event_cmbPurchaseDateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        String tableName = (this.type == PAPER) ? "tblPaperCardAreas" : "tblOysterCardAreas";
        ComboItem zoneItem;
        ComboItem lengthItem;
        int zone;
        double length;

        PreparedStatement st;
        try {
            zoneItem = (ComboItem) cmbZone.getSelectedItem();
            lengthItem = (ComboItem) cmbLength.getSelectedItem();
            zone = (int) zoneItem.getKey();
            length = (double) lengthItem.getKey();

            st = con.prepareStatement("DELETE FROM " + tableName + " WHERE cardNumber = ? "
                    + "and cardPurchaseDate = ? and zoneNumber = ? and cardLength = ?");
            st.setInt(1, this.cardNumber);
            st.setDate(2, this.purchaseDate);
            st.setInt(3, zone);
            st.setDouble(4, length);
            st.executeUpdate();
            fillPrograms();

        } catch (SQLException | NullPointerException ex) {
//            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void chbIsTouristActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbIsTouristActionPerformed
        this.isTourist = chbIsTourist.isSelected();
    }//GEN-LAST:event_chbIsTouristActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chbIsTourist;
    private javax.swing.JComboBox cmbCard;
    private javax.swing.JComboBox cmbLength;
    private javax.swing.JComboBox cmbPurchaseDate;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JComboBox cmbZone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCardNumber;
    private javax.swing.JLabel lblLength;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JLabel lblPrograms;
    private javax.swing.JLabel lblPurchaseDate;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblZone;
    private javax.swing.JTable tblPrograms;
    // End of variables declaration//GEN-END:variables

    private void fillCmbCard() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery("SELECT C.number FROM tblCard As C");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getInt("number"), rs.getString("number")));
            }
            Collections.sort(items);
            items.add(0, null);
            cmbCard.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbPurchaseDate(int cardNumber) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("SELECT C.purchaseDate FROM tblCard As C WHERE C.number = ?");
            st.setInt(1, cardNumber);
            rs = st.executeQuery();

            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getDate("purchaseDate"), rs.getString("purchaseDate")));
            }
            Collections.sort(items);
            items.add(0, null);
            cmbPurchaseDate.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillPrograms() {
        PreparedStatement st;
        ResultSet rs;

        try {
            String tableName = this.type == PAPER ? "tblPaperCardAreas" : "tblOysterCardAreas";
            st = con.prepareStatement("SELECT * FROM " + tableName + " As PCA "
                    + "JOIN tblCardLengths As CL on PCA.cardLength = CL.lengthDescription "
                    + "WHERE PCA.cardNumber = ? and PCA.cardPurchaseDate = ?");
            st.setInt(1, this.cardNumber);
            st.setDate(2, this.purchaseDate);
            rs = st.executeQuery();
            ArrayList<Object[]> rows = new ArrayList();
            int i = 0;
            while (rs.next()) {
                Object[] row = {i, rs.getInt("zoneNumber"), rs.getString("lengthDescription")};
                rows.add(row);
                i++;
            }
            MyTableModel tableModel = new MyTableModel(programColumns, rows, null);
            tblPrograms.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(Card.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbZone() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery("SELECT * FROM tblZone");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getInt("number"), rs.getString("number")));
            }
            Collections.sort(items);
            items.add(0, null);
            cmbCard.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbLength() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery("SELECT * FROM tblCardLengths");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getDouble("cardLength"), rs.getString("lengthDescription")));
            }
            Collections.sort(items);
            items.add(0, null);
            cmbCard.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDefaults() {
        setSelectedValue(cmbCard, String.valueOf(this.cardNumber));
        setSelectedValue(cmbPurchaseDate, String.valueOf(this.purchaseDate));
        setSelectedValue(cmbType, (this.type == PAPER) ? "Paper" : "Oyster");
        // TODO: get picture from DB

        if (this.type == OYSTER) {
            lblPicture.setIcon(this.picture); //TODO: CHECK THIS
        } else {
            // paper
            chbIsTourist.setSelected(this.isTourist);
        }
    }

    private void setVariables() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("Select * From tblOysterCard WHERE"
                    + "number = ? and purchaseDate = ?");
            st.setInt(1, cardNumber);
            st.setDate(1, purchaseDate);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                // result set not empty - the card is Oyster
                this.type = OYSTER;
                rs.next();
                this.picture = rs.getObject("picture", ImageIcon.class); //TODO: FIX THIS IMPORT

            } else {
                // result set is empty - the card is Paper
                this.type = PAPER;
                rs.next();
                this.isTourist = rs.getBoolean("isTourist"); // //TODO: CHECK IF CORRECT
            }

        } catch (SQLException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setActiveness() {
        if (getMode() == ADD_MODE) {
            // key fields
            cmbCard.setEnabled(true);
            cmbPurchaseDate.setEnabled(true);
            cmbType.setEnabled(true);

            // control buttons
            btnCreate.setVisible(true);
            btnDelete.setVisible(false);
            btnUpdate.setVisible(false);

        } else { // edit mode
            // key fields
            cmbCard.setEnabled(false);
            cmbPurchaseDate.setEnabled(false);
            cmbType.setEnabled(false);

            // control buttons
            btnCreate.setVisible(false);
            btnDelete.setVisible(true);
            btnUpdate.setVisible(true);
        }
        modifyFormToCardType();
    }

    private void modifyFormToCardType() {
        if (this.type == OYSTER) {
            lblPicture.setVisible(true);
            btnBrowse.setVisible(true);
            chbIsTourist.setVisible(false);
        } else {
            // paper
            lblPicture.setVisible(false);
            btnBrowse.setVisible(false);
            chbIsTourist.setVisible(false);
        }
        fillPrograms();
    }

}
