/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import init.ComboItem;
import init.MainClass;
import static init.MainClass.con;
import init.MyTableModel;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import static utils.Constants.EDIT_MODE;
import utils.HelperClass;
import utils.InputType;
import utils.Queries;

/**
 *
 * @author asus
 */
public class Role extends MyInternalFrame {

    String[] TableColumns = {
        "# Role",
        "RoleName"};
    int lastIndx = -1;
    PreparedStatement stmt;
    ResultSet rs;
    String q;

    /**
     * Creates new form SiteType
     *
     * @param title
     * @param type
     */
    public Role(String title, String type) {
        super(title, type);
        setMode(EDIT_MODE);
        initComponents();

        FillRolesTable();

        int rows = tblRoles.getRowCount();
        Dimension d = tblRoles.getPreferredSize();
        jScrollPane1.setPreferredSize(
                new Dimension(d.width, tblRoles.getRowHeight() * rows + 1));
        tfAddName.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                setName(tfAddName);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setName(tfAddName);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        PlainDocument addNameDoc = (PlainDocument) tfAddName.getDocument();
        addNameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT50));

        PlainDocument updateNameDoc = (PlainDocument) tfUpdateName.getDocument();
        updateNameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT50));

        tblRoles.setRowSelectionAllowed(true);

        ListSelectionModel rowSelectionModel = tblRoles.getSelectionModel();
        rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableSelection();
                }
            }

            private void tableSelection() {

                String roleName = null;
                int selectedRow = tblRoles.getSelectedRow();

                btnUpdate.setEnabled(true);
                btnRemove.setEnabled(true);

                roleName = (String) tblRoles.getValueAt(selectedRow, 1);

                if (roleName != null) {
                    //Show(name+ "\n"+pass+ "\n"+role+ " ");
                    tfUpdateName.setText(roleName);

                } else {
                    //Show("????\n"+ name+ "\n"+pass+ "\n"+role+ " ");
                }
            }

        });
        btnUpdate.setEnabled(false);
        btnRemove.setEnabled(false);
        btnAdd.setEnabled(false);
    }

    private void FillRolesTable() {

        try {

            stmt = con.prepareStatement(Queries.SELECT_ALL_ROLES,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();
            ArrayList<Object[]> rows = new ArrayList();
            while (rs.next()) {
                Object[] row = {rs.getString("RoleID"), rs.getString("RoleName")};
                rows.add(row);
            }
            rs.last();
            lastIndx = rs.getInt("RoleID");

            MyTableModel tableModel = new MyTableModel(this.TableColumns, rows, null);
            tblRoles.setModel(tableModel);
            closeResource();

        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeResource() throws SQLException {
        rs.close();
        stmt.close();
        q = null;
    }

    private void setName(JTextField field) {
        if (field.getText() != null && !field.getText().equals("")
                && field.getText().length() <= 50) {
            btnAdd.setEnabled(true);

        } else {
            btnAdd.setEnabled(false);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRoles = new javax.swing.JTable();
        pEditType = new javax.swing.JPanel();
        tfUpdateName = new javax.swing.JTextField();
        lblUpdateName = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        pAddType.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Role"));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddTypeLayout.createSequentialGroup()
                        .addComponent(lblAddName)
                        .addGap(40, 40, 40)
                        .addComponent(tfAddName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pAddTypeLayout.setVerticalGroup(
            pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddTypeLayout.createSequentialGroup()
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAddName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        pExistingTypes.setBorder(javax.swing.BorderFactory.createTitledBorder("Exisiting Roles"));

        tblRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblRoles.setMaximumSize(new java.awt.Dimension(0, 50000));
        jScrollPane1.setViewportView(tblRoles);

        javax.swing.GroupLayout pExistingTypesLayout = new javax.swing.GroupLayout(pExistingTypes);
        pExistingTypes.setLayout(pExistingTypesLayout);
        pExistingTypesLayout.setHorizontalGroup(
            pExistingTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExistingTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pExistingTypesLayout.setVerticalGroup(
            pExistingTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExistingTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pEditType.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Role"));

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
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditTypeLayout.createSequentialGroup()
                        .addComponent(lblUpdateName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(tfUpdateName, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(pEditTypeLayout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pEditTypeLayout.setVerticalGroup(
            pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditTypeLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUpdateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUpdateName))
                .addGap(18, 18, 18)
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(btnUpdate))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pExistingTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pAddType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pEditType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pExistingTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pAddType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pEditType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            stmt = con.prepareStatement(Queries.SELECT_ROLE_ID_OF_ROLE_BY_NAME,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, tfAddName.getText().toLowerCase());

            rs = stmt.executeQuery();
            if (rs.next()) {
                //role already exist
                JOptionPane.showMessageDialog(this,
                        "Role name alredy exist",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                //adding new role

                stmt = con.prepareStatement(Queries.INSERT_ROLE);

                if (tblRoles.getRowCount() == 0) {
                    lastIndx = 1;
                }

                stmt.setInt(1, lastIndx + 1);
                stmt.setString(2, tfAddName.getText());

                int result = stmt.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(this,
                            "Role was added successfully",
                            "INFORMATION MESSAGE",
                            JOptionPane.INFORMATION_MESSAGE);

                    
                    FillRolesTable();
                    btnAdd.setEnabled(false);
                   tfAddName.setText("");

                } else {
                    JOptionPane.showInternalMessageDialog(this,
                            "Error, could not aad role.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        try {
            int editedRiwIdx = tblRoles.getSelectedRow();

            stmt = MainClass.con.prepareStatement(Queries.DELETE_ROLE);
            int roleId = Integer.parseInt(tblRoles.getModel().getValueAt(editedRiwIdx, 0).toString());

            stmt.setInt(1, roleId);

            int result = stmt.executeUpdate();
            if (result == 1) {
                JOptionPane.showMessageDialog(this,
                        "Role was deleted successfully",
                        "INFORMATION MESSAGE",
                        JOptionPane.INFORMATION_MESSAGE);
                FillRolesTable();

                btnUpdate.setEnabled(false);
                btnRemove.setEnabled(false);
                tblRoles.clearSelection();
                tfUpdateName.setText("");
            } else {
                JOptionPane.showInternalMessageDialog(this,
                        "Error, could not delete role.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            if (ex.getErrorCode() == 2627) { // 2627 is unique constraint (includes primary key), 2601 is unique index
                msg = "This role Name alredy exit!";
            }
            JOptionPane.showInternalConfirmDialog(this, msg,
                    "Error", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        try {
            int editedRiwIdx = tblRoles.getSelectedRow();

            stmt = con.prepareStatement(Queries.UPDATE_ROLE,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            //set params
            String newName = tfUpdateName.getText();
            //String oldName = (String) tblRoles.getModel().getValueAt(editedRiwIdx, 1);
            int roleIdx = Integer.parseInt(tblRoles.getModel().getValueAt(editedRiwIdx, 0).toString());

            stmt.setString(1, newName);
            stmt.setInt(2, roleIdx);

            int result = stmt.executeUpdate();
            if (result == 1) {
                FillRolesTable();
                JOptionPane.showMessageDialog(this,
                        "Role was updated successfully",
                        "INFORMATION MESSAGE",
                        JOptionPane.INFORMATION_MESSAGE);

                btnUpdate.setEnabled(false);
                btnRemove.setEnabled(false);
                tfUpdateName.setText("");
            } else {
                JOptionPane.showInternalMessageDialog(this,
                        "Error, could not update role.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            if (ex.getErrorCode() == 2627) { // 2627 is unique constraint (includes primary key), 2601 is unique index
                msg = "This User Name alredy exit!";
            }
            JOptionPane.showInternalConfirmDialog(this, msg,
                    "Error", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddName;
    private javax.swing.JLabel lblUpdateName;
    private javax.swing.JPanel pAddType;
    private javax.swing.JPanel pEditType;
    private javax.swing.JPanel pExistingTypes;
    private javax.swing.JTable tblRoles;
    private javax.swing.JTextField tfAddName;
    private javax.swing.JTextField tfUpdateName;
    // End of variables declaration//GEN-END:variables

}
