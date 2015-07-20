/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.jmx.remote.internal.ArrayQueue;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import init.ComboItem;
import init.KeyMembersInterface;
import static init.MainClass.con;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static utils.Constants.ADD_MODE;
import static utils.Constants.EDIT_MODE;
import static utils.Constants.INGOING;
import static utils.Constants.OUTGOING;
import static utils.HelperClass.setSelectedValue;

/**
 *
 * @author asus
 */
public class Activity extends MyInternalFrame {

    int cardNumber;
    Date purchaseDate;
    Date activityDate;
    boolean activityType;
    int stationID;
    private String stationName;
    String lineName;

    /**
     * Creates new form Activity
     *
     * @param title
     * @param type
     * @param cardNumber
     * @param purchaseDate
     * @param activityDate
     */
    public Activity(String title, String type, Integer cardNumber, Date purchaseDate, Date activityDate) {
        super(title, type);
        setMode(EDIT_MODE);
        this.cardNumber = cardNumber;
        this.purchaseDate = purchaseDate;
        this.activityDate = activityDate;
        setVaribles();
        buildForm();
        fillCmbActType();
        setDefaults();
        cmbPurchaseDate.setEnabled(false);

    }

    public Activity(String title, String type) {
        super(title, type);
        setMode(ADD_MODE);
        buildForm();
        dchActivityDate.setDate(new java.util.Date());
    }

    private void buildForm() {
        initComponents();
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCardNumber)
                            .addComponent(lblCardPurchaseDate)
                            .addComponent(lblActivityDate)
                            .addComponent(lblActivityType)
                            .addComponent(lblStation)
                            .addComponent(lblLine))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbLine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbActivityType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dchActivityDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPurchaseDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
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
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dchActivityDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActivityDate))
                .addGap(18, 18, 18)
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
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnCancel))
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
        this.purchaseDate = (Date) purchaseDateItem.getKey();
    }//GEN-LAST:event_cmbPurchaseDateActionPerformed

    private void dchActivityDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchActivityDatePropertyChange
        if (dchActivityDate.getDate() == null) {
            return;
        }
        this.activityDate = new Date(dchActivityDate.getDate().getTime());
    }//GEN-LAST:event_dchActivityDatePropertyChange

    private void cmbActivityTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbActivityTypeActionPerformed
        if (cmbActivityType.getSelectedItem() == null) {
            return;
        }
        //this.activityType = (cmbActivityType.getSelectedItem().equals("Ingoing") ? INGOING : OUTGOING);
        ComboItem actType = (ComboItem) cmbActivityType.getSelectedItem(); 
        
        this.activityType = (Boolean)actType.getKey();
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
            st.setString(2, new Timestamp(this.purchaseDate.getTime()).toString());
            st.setString(3, new Timestamp(this.activityDate.getTime()).toString());
            st.setString(4, (activityType) ? "O" : "I");
            st.setInt(5, stationID);
            st.setString(6, lineName);

            int result = st.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Changes Saved",
                    "INFORMATION MESSAGE",
                    JOptionPane.INFORMATION_MESSAGE);
            fillCmbStation();
            cmbStation.setSelectedIndex(0);

        } catch (SQLException ex) {
            String msg = "There was an error in the action";
                if(ex.getErrorCode() == 2627){ // 2627 is unique constraint (includes primary key), 2601 is unique index
                    msg = "Error cant insert dupliate keys!";
                }
                JOptionPane.showInternalConfirmDialog(this, msg ,
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
            st.setString(2, new Timestamp(this.purchaseDate.getTime()).toString());
            st.setString(3, new Timestamp(this.activityDate.getTime()).toString());
            st.setString(4, (activityType) ? "O" : "I");
            st.setInt(5, stationID);
            st.setString(6, lineName);

            int result = st.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Changes Saved",
                    "INFORMATION MESSAGE",
                    JOptionPane.INFORMATION_MESSAGE);
            fillCmbStation();
            cmbStation.setSelectedIndex(0);

        } catch (SQLException ex) {
            String msg = "There was an error in the action";
                if(ex.getErrorCode() == 2627){ // 2627 is unique constraint (includes primary key), 2601 is unique index
                    msg = "Error cant insert dupliate keys!";
                }
                JOptionPane.showInternalConfirmDialog(this, msg ,
						"Error", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.ERROR_MESSAGE);
                
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
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

            //for some reason i had to cast timestemp to string , dont ask me why !!!
            st.setString(2, new Timestamp(this.purchaseDate.getTime()).toString());
            st.setString(3, new Timestamp(this.activityDate.getTime()).toString());
            rs = st.executeQuery();

            if (rs.next()) {

                this.activityType = (rs.getString("activityType").equals("I")) ? INGOING : OUTGOING;
                this.stationID = rs.getInt("stationID");
                this.lineName = rs.getString("lineName");

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
                cmbCard.setSelectedIndex(0);
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
                items.add(new ComboItem(rs.getDate("purchaseDate"), rs.getString("purchaseDate")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbPurchaseDate.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
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
            rs = s.executeQuery("SELECT ID, name FROM tblStation");
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getInt("ID"), rs.getString("name")));
            }
            if (!items.isEmpty()) {
                Collections.sort(items);

                cmbStation.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
                ComboItem Item = (ComboItem) cmbStation.getSelectedItem();
                this.stationID = (int) Item.getKey();
                fillCmbLine(stationID);
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
                cmbLine.setSelectedIndex(0);
                
                   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setActiveness() {
        dchActivityDate.getDateEditor().setEnabled(false);
        if (getMode() == ADD_MODE) {
            // key fields
            cmbCard.setEnabled(true);
            cmbPurchaseDate.setEnabled(true);
            cmbActivityType.setEnabled(true);

            // control buttons
            btnCreate.setVisible(true);
            btnDelete.setVisible(false);
            btnDelete.setVisible(false);

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
