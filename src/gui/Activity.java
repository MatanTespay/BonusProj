/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import static init.MainClass.con;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static utils.Constants.ADD_MODE;
import static utils.Constants.EDIT_MODE;
import static utils.Constants.INGOING;
import static utils.Constants.OUTGOING;
import utils.HelperClass;
import static utils.HelperClass.setSelectedValue;

/**
 *
 * @author asus
 */
public class Activity extends MyInternalFrame {

    int cardNumber;
    Timestamp purchaseDate;
    Timestamp activityDate;
    boolean activityType;
    int stationID;
    private String stationName;
    String lineName;
    int stationEdit;
    String lineEdit;

    /**
     * Creates new form Activity
     *
     * @param title
     * @param type
     * @param cardNumber
     * @param purchaseDate
     * @param activityDate
     */
    public Activity(String title, String type, Integer cardNumber, Timestamp purchaseDate, Timestamp activityDate) {
        super(title, type);
        setMode(EDIT_MODE);
        this.cardNumber = cardNumber;
        this.purchaseDate = purchaseDate;
        this.activityDate = activityDate;
        initComponents();
        setVaribles();
        buildForm();
        fillCmbActType();
        setDefaults();
        cmbPurchaseDate.setEnabled(false);
        dchActivityDate.setEnabled(false);
        setSelectedValue(cmbStation, stationEdit);
        setSelectedValue(cmbLine, lineEdit);
    }

    public Activity(String title, String type) {
        super(title, type);
        initComponents();
        setMode(ADD_MODE);
        buildForm();
        dchActivityDate.setDate(new java.util.Date());
        cmbCard.setSelectedIndex(0);
        int station = Integer.parseInt(((ComboItem) cmbStation.getSelectedItem()).getKey().toString());
        fillCmbLine(station);
    }

    private void buildForm() {

        fillCmbCard();
        fillCmbStation();
        setActiveness();
        fillCmbActType();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        dchActivityDate = new com.toedter.calendar.JDateChooser();
        cmbActivityType = new javax.swing.JComboBox();
        cmbLine = new javax.swing.JComboBox();
        cmbStation = new javax.swing.JComboBox();
        lblCardNumber = new javax.swing.JLabel();
        lblActivityDate = new javax.swing.JLabel();
        lblCardPurchaseDate = new javax.swing.JLabel();
        lblActivityType = new javax.swing.JLabel();
        lblStation = new javax.swing.JLabel();
        lblLine = new javax.swing.JLabel();
        cmbPurchaseDate = new javax.swing.JComboBox();
        cmbCard = new javax.swing.JComboBox();
        btnCreate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        dchActivityDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dchActivityDatePropertyChange(evt);
            }
        });

        cmbActivityType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ingoing", "Outgoing" }));
        cmbActivityType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbActivityTypeActionPerformed(evt);
            }
        });

        cmbLine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLineActionPerformed(evt);
            }
        });

        cmbStation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStationActionPerformed(evt);
            }
        });

        lblCardNumber.setText("Card Number");

        lblActivityDate.setText("Activity Date");

        lblCardPurchaseDate.setText("Card Purchase Date");

        lblActivityType.setText("Activity Type");

        lblStation.setText("Station");

        lblLine.setText("Line");

        cmbPurchaseDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPurchaseDateActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lblCardNumber, org.jdesktop.beansbinding.ELProperty.create("${name}"), cmbCard, org.jdesktop.beansbinding.BeanProperty.create("name"));
        bindingGroup.addBinding(binding);

        cmbCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCardActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCardNumber)
                    .addComponent(lblCardPurchaseDate)
                    .addComponent(lblActivityDate)
                    .addComponent(lblActivityType)
                    .addComponent(lblStation)
                    .addComponent(lblLine)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbStation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbActivityType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dchActivityDate, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(cmbPurchaseDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCard, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbLine, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCardNumber)
                    .addComponent(cmbCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCardPurchaseDate)
                    .addComponent(cmbPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblActivityDate)
                    .addComponent(dchActivityDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbActivityType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActivityType))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStation)
                    .addComponent(cmbStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLine)
                    .addComponent(cmbLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCardActionPerformed
        try {
            ComboItem cardItem = (ComboItem) cmbCard.getSelectedItem();
            this.cardNumber = Integer.parseInt(cardItem.getKey().toString());
            cmbPurchaseDate.setEnabled(true);
            fillCmbPurchaseDate(cardNumber);
            cmbPurchaseDate.setSelectedIndex(0);
        } catch (NullPointerException ex) {
            // no item is chosen
            cmbPurchaseDate.setEnabled(false);
        }
    }//GEN-LAST:event_cmbCardActionPerformed

    private void cmbStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStationActionPerformed
        try {
            ComboItem stationItem = (ComboItem) cmbStation.getSelectedItem();
            this.stationID = (int) stationItem.getKey();
            cmbLine.setEnabled(true);
            fillCmbLine(stationID);
            cmbLine.setSelectedIndex(0);
        } catch (NullPointerException ex) {
            // no item is chosen
            cmbLine.setEnabled(false);
        }
    }//GEN-LAST:event_cmbStationActionPerformed

    private void cmbPurchaseDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPurchaseDateActionPerformed
        if (cmbPurchaseDate.getSelectedItem() == null) {
            return;
        }
        ComboItem purchaseDateItem = (ComboItem) cmbPurchaseDate.getSelectedItem();
        this.purchaseDate = (Timestamp) purchaseDateItem.getKey();
    }//GEN-LAST:event_cmbPurchaseDateActionPerformed


    private void dchActivityDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchActivityDatePropertyChange
        if (dchActivityDate.getDate() == null) {
            return;
        }
        this.activityDate = new Timestamp(dchActivityDate.getDate().getTime());
        ((JTextField) dchActivityDate.getDateEditor().getUiComponent()).setEditable(false);
    }//GEN-LAST:event_dchActivityDatePropertyChange

    private void cmbActivityTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbActivityTypeActionPerformed
        if (cmbActivityType.getSelectedItem() == null) {
            return;
        }
        //this.activityType = (cmbActivityType.getSelectedItem().equals("Ingoing") ? INGOING : OUTGOING);
        ComboItem actType = (ComboItem) cmbActivityType.getSelectedItem();

        this.activityType = (Boolean) actType.getKey();
    }//GEN-LAST:event_cmbActivityTypeActionPerformed

    private void cmbLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLineActionPerformed
        if (cmbLine.getSelectedItem() == null) {
            return;
        }
        ComboItem lineItem = (ComboItem) cmbLine.getSelectedItem();
        this.lineName = (String) lineItem.getKey();
    }//GEN-LAST:event_cmbLineActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            // TODO add your handling code here:
            PreparedStatement st;
            ResultSet rs;
            String s = "INSERT INTO [LondonU2].[dbo].[tblActivity]"
                    + "  ([cardNumber]"
                    + "  ,[cardPurchaseDate]"
                    + "  ,[activityDate]"
                    + "  ,[activityType]"
                    + "  ,[stationID]"
                    + "  ,[lineName])"
                    + "     VALUES (? ,? ,? ,? ,?,?)";
            st = con.prepareStatement(s);
            st.setInt(1, cardNumber);
            st.setTimestamp(2, this.purchaseDate);
            st.setTimestamp(3, this.activityDate);
            st.setString(4, (activityType) ? "O" : "I");
            st.setInt(5, stationID);
            st.setString(6, lineName);

            int result = st.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Changes Saved",
                    "INFORMATION MESSAGE",
                    JOptionPane.INFORMATION_MESSAGE);
//            fillCmbStation();
//            cmbStation.setSelectedIndex(0);

        } catch (SQLException ex) {
            String msg = "There was an error in the action";
            if (ex.getErrorCode() == 2627) { // 2627 is unique constraint (includes primary key), 2601 is unique index
                msg = "Error cant insert dupliate keys!";
            }
            JOptionPane.showInternalConfirmDialog(this, msg,
                    "Error", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            PreparedStatement st;
            ResultSet rs;
            String s = "DELETE FROM [LondonU2].[dbo].[tblActivity]"
                    + "   WHERE [cardNumber] = ? and "
                    + "   [cardPurchaseDate]= ? and "
                    + "   [activityDate] = ?";

            st = con.prepareStatement(s);
            st.setInt(1, cardNumber);
            st.setTimestamp(2, this.purchaseDate);
            st.setTimestamp(3, this.activityDate);

            int result = st.executeUpdate();

            if (result == 1) {
                JOptionPane.showInternalMessageDialog(this,
                        "Activity was deledted, "
                                + "Showing next activity for the card",
                        "INFORMATION MESSAGE",
                        JOptionPane.INFORMATION_MESSAGE);

                //close the frame if there  are now activites for the ticket
                if ((rs = checkForOtherAct()) == null) {
                    try {
                        JOptionPane.showInternalMessageDialog(this,
                                "No other activities for this card, closing window",
                                "INFORMATION MESSAGE",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.setClosed(true);

                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //get other activites for the ticket and set data
                    int stationID = rs.getInt("stationID");
                    String line = rs.getString("lineName");
                    Timestamp time = rs.getTimestamp("activityDate");
                    String type = rs.getString("activityType");
                    fillCmbStation();
                    setSelectedValue(cmbStation, stationID);
                    setSelectedValue(cmbLine, line);
                    setSelectedValue(cmbActivityType, (type.equals("I") ? utils.Constants.INGOING : utils.Constants.OUTGOING));
                    dchActivityDate.setDate(time);

                }
            } else {
                JOptionPane.showInternalMessageDialog(this,
                        "Erro, couldnt delete Activity",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

            //fillCmbStation();
            //cmbStation.setSelectedIndex(0);
        } catch (SQLException ex) {
            String msg = ex.getMessage();

            JOptionPane.showInternalMessageDialog(this,
                    msg,
                    "Error",
                    JOptionPane.PLAIN_MESSAGE);

            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            PreparedStatement st;

            String s = "UPDATE [LondonU2].[dbo].[tblActivity] "
                    + " SET [activityType] = ? "
                    + " ,[stationID] = ? "
                    + " ,[lineName] = ? "
                    + " WHERE [cardNumber] = ?"
                    + " and [cardPurchaseDate] = ?"
                    + " and [activityDate] = ?";

            st = con.prepareStatement(s);
            st.setString(1, (activityType) ? "O" : "I");
            st.setInt(2, this.stationID);
            st.setString(3, this.lineName);

            st.setInt(4, cardNumber);
            st.setTimestamp(5, this.purchaseDate);
            st.setTimestamp(6, this.activityDate);

            int result = st.executeUpdate();

            if (result == 1) {
                JOptionPane.showInternalMessageDialog(this,
                        "Activity was updated",
                        "INFORMATION MESSAGE",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showInternalMessageDialog(this,
                        "Erro, couldnt update activity",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();

            JOptionPane.showInternalMessageDialog(this,
                    msg,
                    "Error",
                    JOptionPane.PLAIN_MESSAGE);

            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbActivityType;
    private javax.swing.JComboBox cmbCard;
    private javax.swing.JComboBox cmbLine;
    private javax.swing.JComboBox cmbPurchaseDate;
    private javax.swing.JComboBox cmbStation;
    private com.toedter.calendar.JDateChooser dchActivityDate;
    private javax.swing.JLabel lblActivityDate;
    private javax.swing.JLabel lblActivityType;
    private javax.swing.JLabel lblCardNumber;
    private javax.swing.JLabel lblCardPurchaseDate;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblStation;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private ResultSet checkForOtherAct() {
        ResultSet rs;
        PreparedStatement st;
        try {

            String s;
            s = "SELECT TOP(1) A.* FROM tblActivity As A WHERE "
                    + "A.cardNumber = ? and A.cardPurchaseDate = ? ";
            st = con.prepareStatement(s);

            st.setInt(1, this.cardNumber);

            //for some reason i had to cast timestemp to string , dont ask me why !!!
            st.setTimestamp(2, this.purchaseDate);

            rs = st.executeQuery();

            if (rs.next()) {
                this.activityDate = rs.getTimestamp("activityDate");
                return rs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;

        }

        return null;
    }

    private void setVaribles() {
        PreparedStatement st;
        ResultSet rs;

        try {

            String s;
            s = "SELECT A.*, S.name FROM tblActivity As A "
                    + "join tblStation As S on A.stationID = S.ID "
                    + "WHERE "
                    + "A.cardNumber = ? and A.cardPurchaseDate = ? and A.activityDate = ?";
            st = con.prepareStatement(s);

            st.setInt(1, this.cardNumber);

            st.setTimestamp(2, this.purchaseDate);
            st.setTimestamp(3, this.activityDate);
            rs = st.executeQuery();

            if (rs.next()) {

                this.activityType = (rs.getString("activityType").equals("I")) ? INGOING : OUTGOING;
                this.stationEdit = this.stationID = rs.getInt("stationID");
                this.lineEdit = this.lineName = rs.getString("lineName");

                PreparedStatement st2;
                ResultSet rs2;
                String stationName;

                st = con.prepareStatement("SELECT S.ID,S.name FROM tblStation S WHERE "
                        + "S.ID = ?");
                st.setInt(1, this.stationID);
                rs = st.executeQuery();

                if (rs.next()) {
                    this.stationName = rs.getString("name");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class
                    .getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void setDefaults() {
        if (this.cardNumber > 0 && this.purchaseDate != null && this.activityDate != null
                && this.stationName != null && this.lineName != null) {

            setSelectedValue(cmbCard, String.valueOf(this.cardNumber));
            setSelectedValue(cmbPurchaseDate, this.purchaseDate.toString());
            dchActivityDate.setDate(this.activityDate);

            setSelectedValue(cmbActivityType, (this.activityType == INGOING) ? "Ingoing" : "Outgoing");
            setSelectedValue(cmbStation, this.stationName);
            setSelectedValue(cmbLine, this.lineName);
        }
    }

    private void fillCmbCard() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery("SELECT C.number FROM tblCard As C");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("number"), rs.getString("number")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbCard.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
//                if (super.getMode() == utils.Constants.ADD_MODE) {
//                    cmbCard.setSelectedIndex(0);
//                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
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
                items.add(new ComboItem(rs.getTimestamp("purchaseDate"), rs.getString("purchaseDate")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbPurchaseDate.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
//                if (super.getMode() == utils.Constants.ADD_MODE) {
//                    cmbPurchaseDate.setSelectedIndex(0);
//                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbStation() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            String query = "SELECT ID ,name"
                              + " FROM  tblStation";
            
            String outQ = "SELECT distinct sil.stationID ,s.name"
                              + " FROM  tblStation"
                               + " s  inner join  tblStationInLine sil"
                               + "  on s.ID = sil.stationID";
            
            rs = s.executeQuery(outQ);
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getInt("stationID"), rs.getString("name")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbStation.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
//                if (super.getMode() == utils.Constants.ADD_MODE) {
//                    ComboItem Item = (ComboItem) cmbStation.getSelectedItem();
//                    this.stationID = (int) Item.getKey();
//                    fillCmbLine(stationID);
//                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCmbActType() {

        ArrayList<ComboItem> items = new ArrayList<>();

        items.add(new ComboItem(utils.Constants.INGOING, "Ingoing"));
        items.add(new ComboItem(utils.Constants.OUTGOING, "Outgoing"));

        cmbActivityType.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        cmbActivityType.setSelectedIndex(0);
    }

    private void fillCmbLine(int stationID) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = con.prepareStatement("SELECT SIL.lineName FROM tblStationInLine "
                    + "As SIL WHERE SIL.stationID = ?");
            st.setInt(1, stationID);
            rs = st.executeQuery();

            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("lineName"), rs.getString("lineName")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbLine.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
//                if (super.getMode() == utils.Constants.ADD_MODE) {
//                    cmbLine.setSelectedIndex(0);
//                }
//                else{
//                    setSelectedValue(cmbLine, this.lineName);
//                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setActiveness() {

        if (getMode() == ADD_MODE) {
            // key fields
            cmbCard.setEnabled(true);
            cmbPurchaseDate.setEnabled(true);
            cmbActivityType.setEnabled(true);

            // control buttons
            btnCreate.setVisible(true);
            btnDelete.setVisible(false);
            btnUpdate.setVisible(false);

        } else { // edit mode
            // key fields
            cmbCard.setEnabled(false);
            cmbPurchaseDate.setEnabled(false);
            cmbActivityType.setEnabled(false);

            // control buttons
            btnCreate.setVisible(false);
            btnDelete.setVisible(true);
            btnUpdate.setVisible(true);
        }
    }

//    @Override
//    public List<Object[]> getKeyComponents() {
//        super.keyComponents = new ArrayList<>();
//        
//        Object[] values = new Object[3]; 
//        JComboBox comboMunber = new JComboBox();
//        JComboBox ComboPurchaseDate = new JComboBox();
//        comboMunber.putClientProperty("TableKey", "number");
//        comboMunber.putClientProperty("MyText", "Card Number");
//        comboMunber.putClientProperty("Query", "Select * from tblCard");        
//        values[0] = comboMunber;
//        
//        ComboPurchaseDate = new JComboBox();
//        ComboPurchaseDate.putClientProperty("TableKey", "cardNumber");
//        ComboPurchaseDate.putClientProperty("MyText", "Purchase date");
//        ComboPurchaseDate.putClientProperty("Query", "Select * from tblCard where number = ?");    
//        ComboPurchaseDate.putClientProperty("Chnager", comboMunber);
//   
//        values[0] = ComboPurchaseDate;  
//        
//        super.keyComponents.add(values);
//                
//        return super.keyComponents;
//    }
}
