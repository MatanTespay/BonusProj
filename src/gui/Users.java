/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.toedter.calendar.JDateChooser;
import init.ComboItem;
import init.InputValidator;
import init.MainClass;
import static init.MainClass.con;
import init.MyTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import utils.Queries;

/**
 *
 * @author Matan
 */
public class Users extends MyInternalFrame {

    String[] UserColumns = {
        "UserName",
        "Password",
        "RoleName"};
    boolean isEditState = false;
    boolean isDeleteState = false;
    int editedRiwIdx;

    /**
     * Creates new form Users
     *
     * @param title
     * @param type
     */
    public Users(String title, String type) {
        super(title, type);
        setMode(utils.Constants.EDIT_MODE);
        initComponents();
        fillCbUserType();
        FillUsersTable();
        super.validators = new ArrayList<InputValidator>() {
            {
                add(new InputValidator(txtPass, utils.InputType.PASSWORD, lblerrPass, null));
                add(new InputValidator(txtUserName, utils.InputType.TEXT, lblerrUserName, null));

            }
        };

        setLables();
        setTableSelection();
        btnRemove.setEnabled(false);
        btnEdit.setEnabled(false);

    }

    private void setLables() {
        lblerrPass.setLabelFor(txtPass);
        lblerrUserName.setLabelFor(txtUserName);
    }

    private void setTableSelection() {
        ListSelectionModel selectionModel = tblUsers.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableSelection();
                }
            }

            private void tableSelection() {
                btnRemove.setEnabled(true);
                btnEdit.setEnabled(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblRole = new javax.swing.JLabel();
        cbRoleType = new javax.swing.JComboBox();
        lblUserName = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        btnAddUser = new javax.swing.JButton();
        lblerrUserName = new javax.swing.JLabel();
        lblerrPass = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        lblRole.setText("User Role");

        cbRoleType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblUserName.setText("User Name:");

        lblPass.setText("User Pasword:");

        btnAddUser.setText("Save");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblerrUserName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblRole)
                                .addGap(18, 18, 18)
                                .addComponent(cbRoleType, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblUserName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblerrPass, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPass)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRole)
                    .addComponent(cbRoleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUserName)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPass)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblerrUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblerrPass, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRemove)
                        .addComponent(btnEdit))
                    .addComponent(btnAddUser))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblUsers);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        saveChange();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        isEditState = true;
        String name = (String) (tblUsers.getModel().getValueAt(tblUsers.getSelectedRow(), 0));
        String pass = (String) (tblUsers.getModel().getValueAt(tblUsers.getSelectedRow(), 1));
        txtPass.setText(pass);
        txtUserName.setText(name);
        editedRiwIdx = tblUsers.getSelectedRow();
        btnRemove.setEnabled(false);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        isDeleteState = true;
        editedRiwIdx = tblUsers.getSelectedRow();
        saveChange();
    }//GEN-LAST:event_btnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox cbRoleType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblerrPass;
    private javax.swing.JLabel lblerrUserName;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

    private void fillCbUserType() {
        Statement s;
        ResultSet rs;
        try {
            s = con.createStatement();
            rs = s.executeQuery(Queries.SELECT_ALL_ROLES);
            ArrayList<ComboItem> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new ComboItem(rs.getString("RoleID"), rs.getString("RoleName")));
            }
            this.cbRoleType.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void FillUsersTable() {

        ResultSet rs;
        try {
            PreparedStatement stmt;

            stmt = con.prepareStatement(Queries.SELECT_ALL_USERS_ANDTHEIR_ROLES);
            rs = stmt.executeQuery();
            ArrayList<Object[]> rows = new ArrayList();
            while (rs.next()) {
                Object[] row = {rs.getString("UserName"), rs.getString("Password"), rs.getString("RoleName")};
                rows.add(row);
            }
            MyTableModel tableModel = new MyTableModel(this.UserColumns, rows, null);
            tblUsers.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveChange() {
        boolean result = true;
        if (!isDeleteState) {

            result = super.isInputOk();

        }

        if (result) {
            //connect to db and add user

            try {

                PreparedStatement stmt;

                if (!isEditState && !isDeleteState) {

                    int RoleID = Integer.parseInt((String) ((ComboItem) cbRoleType.getSelectedItem()).getKey());
                    stmt = con.prepareStatement(Queries.INSERT_USER);
                    stmt.setString(1, txtUserName.getText());
                    stmt.setString(2, txtPass.getText());
                    stmt.setInt(3, RoleID);
                    // execute insert SQL stetement                
                    stmt.executeUpdate();
                    txtUserName.setText("");
                    txtPass.setText("");

                    JOptionPane.showMessageDialog(this,
                            "User was added successfully",
                            "INFORMATION MESSAGE",
                            JOptionPane.INFORMATION_MESSAGE);

                    FillUsersTable();

                } else if (isEditState) {
                    /*
                     TODO
                     why do we need to update username and password
                     if they are given as parameters. I think you should
                     use Queries.UPDATE_USER
                     */

                    stmt = con.prepareStatement(utils.Queries.UPDATE_USER,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

                    //set params
                    int RoleID = Integer.parseInt((String) ((ComboItem) cbRoleType.getSelectedItem()).getKey());
                    String oldName = (String) tblUsers.getModel().getValueAt(editedRiwIdx, 0);

                    stmt.setString(1, txtUserName.getText());
                    stmt.setString(2, txtPass.getText());
                    stmt.setInt(3, RoleID);
                    stmt.setString(4, oldName);

                    stmt.executeUpdate();

                    FillUsersTable();

                    btnEdit.setEnabled(false);
                    btnRemove.setEnabled(false);
                    txtUserName.setText("");
                    txtPass.setText("");

                    JOptionPane.showMessageDialog(this,
                            "Changes Saved",
                            "INFORMATION MESSAGE",
                            JOptionPane.INFORMATION_MESSAGE);

//                    String up = "UPDATE [LondonU2].[dbo].[tblCard]"
//                            + " SET [purchaseDate] = ?"
//                            + " WHERE number = 14";
//                    stmt = con.prepareStatement(up,
//                            ResultSet.TYPE_SCROLL_SENSITIVE,
//                            ResultSet.CONCUR_UPDATABLE);
//                    Timestamp l = new Timestamp(dater.getDate().getTime());
//                    stmt.setTimestamp(1, l);
//                    
//                    stmt.executeUpdate();
                    isEditState = false;

                } else {

                    stmt = MainClass.con.prepareStatement(Queries.DELETE_USER);
                    String oldName = (String) tblUsers.getModel().getValueAt(editedRiwIdx, 0);

                    stmt.setString(1, oldName);

                    stmt.executeUpdate();

                    FillUsersTable();

                    btnEdit.setEnabled(false);
                    btnRemove.setEnabled(false);

                    isDeleteState = false;

                    JOptionPane.showMessageDialog(this,
                            "User was deleted successfully",
                            "INFORMATION MESSAGE",
                            JOptionPane.INFORMATION_MESSAGE);

                    txtUserName.setText("");
                    txtPass.setText("");

                }
            } catch (SQLServerException ex) {
                String msg = "There was an error in the action";
                if (ex.getErrorCode() == 2627) { // 2627 is unique constraint (includes primary key), 2601 is unique index
                    msg = "This User Name alredy exit!";
                }
                JOptionPane.showInternalConfirmDialog(this, msg,
                        "Error", JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.ERROR_MESSAGE);

                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public MyTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        // data of the table
        ArrayList<Object[]> data = new ArrayList<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (String co : UserColumns) {
                row.add(rs.getObject(co));
            }
            data.add(row.toArray(new Object[row.size()]));
        }

        return new MyTableModel(UserColumns, data);

    }

    class mydate extends JDateChooser {

    }

}
