/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.InputValidator;
import static init.MainClass.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import static utils.Constants.EDIT_MODE;
import utils.Queries;

/**
 *
 * @author asus
 */
public class SiteTypes extends MyInternalFrame {

    /**
     * Creates new form SiteType
     * @param title
     * @param type
     */
    public SiteTypes (String title, String type) {
        super(title, type);
        setMode(EDIT_MODE);
        initComponents();
        fillTypes();
        super.validators = new ArrayList<InputValidator>() {
            {
                add(new InputValidator(tfType, utils.InputType.TEXT, null, null));
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

        lblSiteType = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsttypes = new javax.swing.JList();
        lblTypes = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        tfType = new javax.swing.JTextField();

        lblSiteType.setText("Type");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        lsttypes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lsttypes);

        lblTypes.setText("Types");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");

        btnCancel.setText("Cancel");

        tfType.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTypes)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblSiteType)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(lblSiteType)
                    .addComponent(tfType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(lblTypes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSubmit))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        PreparedStatement st;
        String siteType;
        try {
            siteType = tfType.getText();

            st = con.prepareStatement(Queries.INSERT_SYTE_TYPE);
            st.setString(1, siteType);
            st.executeUpdate();
            fillTypes();

        } catch (SQLException | NullPointerException ex) {
            //            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        PreparedStatement st;
        ComboItem typeItem;
        String siteType;
        
        try {
            typeItem = (ComboItem)(lsttypes.getSelectedValue());
            siteType = (String)typeItem.getKey();
            st = con.prepareStatement(Queries.DELETE_SITE_TYPE);
            st.setString(1, siteType);
            st.executeUpdate();

            fillTypes();
        } catch (SQLException ex) {
            Logger.getLogger(SiteTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSiteType;
    private javax.swing.JLabel lblTypes;
    private javax.swing.JList lsttypes;
    private javax.swing.JTextField tfType;
    // End of variables declaration//GEN-END:variables

    private void fillTypes() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery(Queries.SELECT_ALL_SITE_TYPES);

            Set<ComboItem> items = new TreeSet<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("siteType"), rs.getString("siteType")));
            }

            DefaultListModel listModel = new DefaultListModel();
            for (ComboItem ci : items) {
                listModel.addElement(ci);
            }
            lsttypes.setModel(listModel);

        } catch (SQLException ex) {
            Logger.getLogger(SiteTypes.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
