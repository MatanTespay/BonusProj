/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import init.ComboItem;
import static init.MainClass.con;
import init.MyTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.PlainDocument;
import static utils.Constants.EDIT_MODE;
import utils.HelperClass;
import utils.InputType;
import utils.Queries;

/**
 *
 * @author asus
 */
public class User extends MyInternalFrame {

    String[] UserColumns = {
        "UserName",
        "Password",
        "RoleName"};

    /**
     * Creates new form SiteType
     *
     * @param title
     * @param type
     */
    public User(String title, String type) {
        super(title, type);
        setMode(EDIT_MODE);
        initComponents();
        FillUsersTable();
        fillCbUserType();

        PlainDocument addNameDoc = (PlainDocument) tfAddName.getDocument();
        addNameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT15));

        PlainDocument addPassDoc = (PlainDocument) tfAddPass.getDocument();
        addPassDoc.setDocumentFilter(new utils.MyDocFilter(InputType.PASSWORD));

        PlainDocument updateNameDoc = (PlainDocument) tfUpdateName.getDocument();
        updateNameDoc.setDocumentFilter(new utils.MyDocFilter(InputType.TEXT15));

        PlainDocument updatePassDoc = (PlainDocument) tfUpdatePass.getDocument();
        updatePassDoc.setDocumentFilter(new utils.MyDocFilter(InputType.PASSWORD));

        tblUsers.setRowSelectionAllowed(true);
        
        ListSelectionModel rowSelectionModel = tblUsers.getSelectionModel();
        rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String name = null;
                String pass = null;
                String role = null;
                int[] selectedRow = tblUsers.getSelectedRows();
                int[] selectedColumns = tblUsers.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    name = (String) tblUsers.getValueAt(selectedRow[i], 0);
                    pass = (String) tblUsers.getValueAt(selectedRow[i], 1);
                    role = (String) tblUsers.getValueAt(selectedRow[i], 2);
                }
                
                if (name != null && pass != null && role != null) {
                    tfUpdateName.setText(name);
                    tfUpdatePass.setText(pass);
                    HelperClass.setSelectedValue(cbRoleTypeUpdate,role);
                }
               
            }

        });

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
            DefaultComboBoxModel model1 = new javax.swing.DefaultComboBoxModel(items.toArray());
            DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(items.toArray());
            this.cbRoleType.setModel(model1);
            this.cbRoleTypeUpdate.setModel(model);

            if (cbRoleType.getItemCount() > 0) {
                cbRoleType.setSelectedIndex(0);
            }

            if (cbRoleTypeUpdate.getItemCount() > 0) {
                cbRoleTypeUpdate.setSelectedIndex(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
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
        lblAddPass = new javax.swing.JLabel();
        tfAddPass = new javax.swing.JTextField();
        lblRole = new javax.swing.JLabel();
        cbRoleType = new javax.swing.JComboBox();
        pExistingTypes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        pEditType = new javax.swing.JPanel();
        tfUpdateName = new javax.swing.JTextField();
        lblUpdateName = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        tfUpdatePass = new javax.swing.JTextField();
        lblUpdatePass = new javax.swing.JLabel();
        lblRoleUpdate = new javax.swing.JLabel();
        cbRoleTypeUpdate = new javax.swing.JComboBox();

        pAddType.setBorder(javax.swing.BorderFactory.createTitledBorder("Add User"));

        lblAddName.setText("Name");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblAddPass.setText("Password");

        lblRole.setText("User Role");

        cbRoleType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pAddTypeLayout = new javax.swing.GroupLayout(pAddType);
        pAddType.setLayout(pAddTypeLayout);
        pAddTypeLayout.setHorizontalGroup(
            pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pAddTypeLayout.createSequentialGroup()
                        .addComponent(lblRole)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbRoleType, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddTypeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddTypeLayout.createSequentialGroup()
                                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddTypeLayout.createSequentialGroup()
                                        .addComponent(lblAddName)
                                        .addGap(40, 40, 40))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddTypeLayout.createSequentialGroup()
                                        .addComponent(lblAddPass)
                                        .addGap(21, 21, 21)))
                                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfAddPass, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(tfAddName))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pAddTypeLayout.setVerticalGroup(
            pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddTypeLayout.createSequentialGroup()
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAddName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddPass)
                    .addComponent(tfAddPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAddTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRoleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        pExistingTypes.setBorder(javax.swing.BorderFactory.createTitledBorder("Exisiting Users"));

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
        tblUsers.setMaximumSize(new java.awt.Dimension(0, 50000));
        tblUsers.setPreferredSize(new java.awt.Dimension(32767, 70767));
        jScrollPane1.setViewportView(tblUsers);

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

        pEditType.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit User"));

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

        lblUpdatePass.setText("Password");

        lblRoleUpdate.setText("User Role");

        cbRoleTypeUpdate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pEditTypeLayout = new javax.swing.GroupLayout(pEditType);
        pEditType.setLayout(pEditTypeLayout);
        pEditTypeLayout.setHorizontalGroup(
            pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditTypeLayout.createSequentialGroup()
                        .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUpdateName)
                            .addComponent(lblUpdatePass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfUpdatePass, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(tfUpdateName))
                        .addGap(14, 14, 14))
                    .addGroup(pEditTypeLayout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pEditTypeLayout.createSequentialGroup()
                        .addComponent(lblRoleUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbRoleTypeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pEditTypeLayout.setVerticalGroup(
            pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditTypeLayout.createSequentialGroup()
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUpdateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUpdateName))
                .addGap(11, 11, 11)
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUpdatePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUpdatePass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEditTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRoleTypeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRoleUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pExistingTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pAddType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pEditType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        PreparedStatement stmt;
        try{
                 int RoleID = Integer.parseInt((String) ((ComboItem) cbRoleType.getSelectedItem()).getKey());
                    stmt = con.prepareStatement(Queries.INSERT_USER);
                    stmt.setString(1, tfAddName.getText());
                    stmt.setString(2, tfAddPass.getText());
                    stmt.setInt(3, RoleID);
                    // execute insert SQL stetement                
                    stmt.executeUpdate();
                    tfAddName.setText("");
                    tfAddPass.setText("");

                    JOptionPane.showMessageDialog(this,
                            "User was added successfully",
                            "INFORMATION MESSAGE",
                            JOptionPane.INFORMATION_MESSAGE);

                    FillUsersTable();
        
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
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed


    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed


    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cbRoleType;
    private javax.swing.JComboBox cbRoleTypeUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddName;
    private javax.swing.JLabel lblAddPass;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblRoleUpdate;
    private javax.swing.JLabel lblUpdateName;
    private javax.swing.JLabel lblUpdatePass;
    private javax.swing.JPanel pAddType;
    private javax.swing.JPanel pEditType;
    private javax.swing.JPanel pExistingTypes;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField tfAddName;
    private javax.swing.JTextField tfAddPass;
    private javax.swing.JTextField tfUpdateName;
    private javax.swing.JTextField tfUpdatePass;
    // End of variables declaration//GEN-END:variables

   
}
