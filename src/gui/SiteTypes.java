/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static init.MainClass.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.PlainDocument;
import static utils.Constants.EDIT_MODE;
import utils.InputType;
import utils.Queries;

/**
 *
 * @author asus
 */
public class SiteTypes extends MyInternalFrame {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    /**
     * Creates new form SiteType
     *
     * @param title
     * @param type
     */
    public SiteTypes(String title, String type) {
        super(title, type);
        setMode(EDIT_MODE);
        initComponents();
        fillTypes();

        PlainDocument addNameDoc = (PlainDocument) tfAddName.getDocument();
        addNameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT15));

        PlainDocument updateNameDoc = (PlainDocument) tfUpdateName.getDocument();
        updateNameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT15));

        lstTypes.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isItemSelected = lstTypes.getSelectedIndex() != -1;
                btnRemove.setEnabled(isItemSelected);
                btnUpdate.setEnabled(isItemSelected);
                if (isItemSelected) {
                    tfUpdateName.setText(lstTypes.getSelectedValue().toString());
                }
            }
        });
        if (lstTypes.getModel().getSize() > 0) {
            lstTypes.setSelectedIndex(0);
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

        pAddType = new javax.swing.JPanel();
        tfAddName = new javax.swing.JTextField();
        lblAddName = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        pExistingTypes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTypes = new javax.swing.JList();
        pEditType = new javax.swing.JPanel();
        tfUpdateName = new javax.swing.JTextField();
        lblUpdateName = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        pAddType.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Site Type"));

        lblAddName.setText("Name");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pAddTypeLayout = new javax.swing.GroupLayout(pAddType);
        pAddType.setLayout(pAddTypeLayout);
        pAddTypeLayout.setHorizontalGroup(
            pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pAddTypeLayout.createSequentialGroup()
                        .addComponent(lblAddName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfAddName))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddTypeLayout.createSequentialGroup()
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pAddTypeLayout.setVerticalGroup(
            pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAddName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pExistingTypes.setBorder(javax.swing.BorderFactory.createTitledBorder("Exisiting Site Types"));

        lstTypes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstTypes);

        javax.swing.GroupLayout pExistingTypesLayout = new javax.swing.GroupLayout(pExistingTypes);
        pExistingTypes.setLayout(pExistingTypesLayout);
        pExistingTypesLayout.setHorizontalGroup(
            pExistingTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExistingTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );
        pExistingTypesLayout.setVerticalGroup(
            pExistingTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExistingTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        pEditType.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Site Type"));

        lblUpdateName.setText("Name");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pEditTypeLayout = new javax.swing.GroupLayout(pEditType);
        pEditType.setLayout(pEditTypeLayout);
        pEditTypeLayout.setHorizontalGroup(
            pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUpdateName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditTypeLayout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate))
                    .addComponent(tfUpdateName))
                .addContainerGap())
        );
        pEditTypeLayout.setVerticalGroup(
            pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUpdateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUpdateName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pExistingTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pEditType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pAddType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pExistingTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pAddType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pEditType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        PreparedStatement st;
        String siteType;
        try {
            siteType = tfAddName.getText();

            st = con.prepareStatement(Queries.INSERT_SITE_TYPE);
            st.setString(1, siteType);
            st.executeUpdate();

            fillTypes();

        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                JOptionPane.showInternalMessageDialog(this,
                        "This type already exists!",
                        "Bummer!",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed

        try {

            String siteType = lstTypes.getSelectedValue().toString();
            PreparedStatement st = con.prepareStatement(Queries.DELETE_SITE_TYPE);
            st.setString(1, siteType);
            st.executeUpdate();

            fillTypes();

        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showInternalMessageDialog(this,
                        "This type is already in use!",
                        "Bummer!",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        try {
            String newTypeName = tfUpdateName.getText();
            String oldTypeName = lstTypes.getSelectedValue().toString();

            PreparedStatement st = con.prepareStatement(Queries.UPDATE_SITE_TYPE);
            st.setString(1, newTypeName);
            st.setString(2, oldTypeName);
            st.executeUpdate();

            fillTypes();

        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showInternalMessageDialog(this,
                        "This type is already in use!",
                        "Bummer!",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            } else {
                System.err.println("Error code: " + e.getErrorCode() + "\nError Message: " + e.getMessage());
            }
        }


    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAddName;
    private javax.swing.JLabel lblUpdateName;
    private javax.swing.JList lstTypes;
    private javax.swing.JPanel pAddType;
    private javax.swing.JPanel pEditType;
    private javax.swing.JPanel pExistingTypes;
    private javax.swing.JTextField tfAddName;
    private javax.swing.JTextField tfUpdateName;
    // End of variables declaration//GEN-END:variables

    private void fillTypes() {

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(Queries.SELECT_ALL_SITE_TYPES);

            Set<String> items = new TreeSet<>();
            while (rs.next()) {
                items.add(rs.getString("siteType"));
            }

            DefaultListModel listModel = new DefaultListModel();
            for (String st : items) {
                listModel.addElement(st);
            }
            lstTypes.setModel(listModel);

        } catch (SQLException ex) {
            Logger.getLogger(SiteTypes.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
