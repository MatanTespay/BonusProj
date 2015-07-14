/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.IFly;
import init.MainClass;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;

import utils.HelperClass;

/**
*
 *The class represents the screen which enable users to login the system , redirect the user to the {@link gui.MainWindow}
@author Matan
*/
public class Login extends javax.swing.JFrame {

    private static String OK = "ok";
    private static IFly IFlylog;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        usertxtPassword.setActionCommand(OK);
        btnLogin.setActionCommand(OK);
        IFlylog = MainClass.getIfly();
        fillComboBox();
        userTxtName.requestFocusInWindow();
    }
    /**
     * fill {@link #userTypeComboBox}}
     */
    private void fillComboBox() {
        String[] usersStrings = {"Admin", "Agent", "Customer"};
        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(usersStrings));
        userTypeComboBox.setSelectedIndex(0);
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
        usertxtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        userLblName = new javax.swing.JLabel();
        userTxtName = new javax.swing.JTextField();
        userLblPassword = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        userTypeComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(350, 100, 0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        usertxtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
                usertxtPasswordKeyPressed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        userLblName.setText("User id:");

        userTxtName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
                userTxtNameKeyPressed(evt);
            }
        });

        userLblPassword.setText("Password:");

        jLabel1.setText("User:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLblPassword)
                    .addComponent(userLblName)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(userTxtName)
                        .addComponent(usertxtPassword)
                        .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLblName)
                    .addComponent(userTxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLblPassword)
                    .addComponent(usertxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloud.jpg"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("IFly AirLines Company");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:

        String cmd = evt.getActionCommand();

        if (OK.equals(cmd)) { //Process the password.
            DoAction();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void usertxtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usertxtPasswordKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DoAction();
        }
    }//GEN-LAST:event_usertxtPasswordKeyPressed

    private void userTxtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTxtNameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DoAction();
        }
    }//GEN-LAST:event_userTxtNameKeyPressed

    /**
     * fire action perform for {@link #btnLogin}}
     */
    private void DoAction() {
        char[] input = usertxtPassword.getPassword();
        String userTitle = (String) userTypeComboBox.getSelectedItem();

        if (isNamePasswordCorrect(input, userTxtName.getText())) {

            /*JOptionPane.showMessageDialog(this,
             "Success! You typed the right password.");*/
            userTitle = (userTitle.equals("Admin") ? "Administration" : userTitle);

            StringBuilder sb = new StringBuilder();
            sb.append("IFly AirLines Company - ").append(userTitle).append(" Mode");
            //MainClass.getCurrentUser().put((String) userTypeComboBox.getSelectedItem(), input.toString());
            
            setVisible(false);
            userTxtName.setText(null);
            usertxtPassword.setText(null);
            new MainWindow(sb.toString(), (String) userTypeComboBox.getSelectedItem(),this).setVisible(true);
            //dispose();
            //MainClass.SerializeIfly();
            
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid password or User Name. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }

        //Zero out the possible password, for security.
        Arrays.fill(input, '0');

        resetFocus(0);

        
    }

    /**
     * Validates user credentials {@link #btnLogin}}
     */
    private boolean isNamePasswordCorrect(char[] input, String id) {
        boolean isCorrect = true;
        char[] adminPassword = {'A', 'd', 'm', 'i', 'n'};
        String selectedUserType = (String) userTypeComboBox.getSelectedItem();
        Object user = null;
        
        switch (selectedUserType) {
            case "Admin":
                if (input.length != adminPassword.length) {
                    isCorrect = false;
                } else {
                    if (!Arrays.equals(input, adminPassword) || !id.equals("Admin")) {
                        isCorrect = false;
                    }
                }
                
                break;
            case "Agent":
                MainClass.setUserData("Agent", userTxtName.getText(), input);
                if (MainClass.getUserData() == null) {
                    return false;
                }
                break;
            case "Customer":
                MainClass.setUserData("Customer", HelperClass.getFixID(userTxtName.getText()), input);
                if (MainClass.getUserData() == null) {
                    return false;
                }
                break;
            default:
                isCorrect = false;
                break;
        }

        //Zero out the password.
        Arrays.fill(adminPassword, '0');

        return isCorrect;
    }

    /**
     * reset focus in the window 
     */
    public void resetFocus(int i) {
        //(i == 0) ?  usertxtPassword.requestFocusInWindow() : usertxtPassword.requestFocusInWindow();
        
        if (i == 0) {
            userTxtName.requestFocusInWindow();
           
        }
        else   {
             usertxtPassword.requestFocusInWindow();
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel userLblName;
    private javax.swing.JLabel userLblPassword;
    private javax.swing.JTextField userTxtName;
    private javax.swing.JComboBox userTypeComboBox;
    private javax.swing.JPasswordField usertxtPassword;
    // End of variables declaration//GEN-END:variables
}
