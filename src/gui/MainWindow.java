/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.CloseAction;
import init.MainClass;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * The class represents the main window of the application
 *
 * @author Matan
 */
/**
 * @author Matan
 *
 */
public class MainWindow extends javax.swing.JFrame implements ActionListener {

    String selectedUserType;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JDesktopPane desktop;
    DesktopScrollPane pane;
    Login login;

    /**
     * Creates new form MainWindow
     *
     * @param Title
     * @param userType
     * @param log
     */
    public MainWindow(String Title, String userType, Login log) {
        super(Title);
        this.login = log;
        selectedUserType = userType;
        initComponents();
        int inset = 100;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(300, 40,
                866,
                668);

        desktop = new JDesktopPane();
        //desktop.add(panel);
        pane = new DesktopScrollPane(desktop);

        setContentPane(pane);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        fillMenuBar();

    }

    /**
     * Creates new form MainWindow
     *
     * @param title the tile
     */
    public MainWindow(String title) {
        super(title);
    }

    /**
     * fill the {@link #MainMenuBar}}
     */
    private void fillMenuBar() {

        //<editor-fold defaultstate="collapsed" desc="File Menu">
        JMenu aMenu = addMenuToMenuBar("File", 'F');
        //addMenuItem(aMenu, "Save", KeyEvent.VK_S);

        CloseAction closeAction = new CloseAction("Exit", null, "Close The Application", new Integer(KeyEvent.VK_X), this);
        menuItem = new JMenuItem(closeAction);

        menu.add(menuItem);
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Operations Menu">
        if (!selectedUserType.equals("Customer")) {
            aMenu = addMenuToMenuBar("Operations", KeyEvent.VK_O);

            if (!selectedUserType.equals("Agent")) {
//branch
                submenu = addMenuToMenuBar("Branch  ", KeyEvent.VK_B);
                addMenuItem(submenu, "Add Activity", KeyEvent.VK_B);
                addMenuItem(submenu, "Add Agent To Branch", KeyEvent.VK_A);
                aMenu.add(submenu);
                aMenu.addSeparator();
                //Agents
                submenu = addMenuToMenuBar("Employees   ", KeyEvent.VK_F);
                addMenuItem(submenu, "Add Station", KeyEvent.VK_A);
                //addMenuItem(submenu, "Add Flight Attendant", KeyEvent.VK_F);
                addMenuItem(submenu, "Add User", KeyEvent.VK_F);
                addMenuItem(submenu, "Add Line", KeyEvent.VK_F);
                addMenuItem(submenu, "Add Site", KeyEvent.VK_P);
                aMenu.add(submenu);
                aMenu.addSeparator();

                //<editor-fold defaultstate="collapsed" desc="Only Admin">
                if (!selectedUserType.equals("Manager")) {

                    aMenu = addMenuToMenuBar("System", KeyEvent.VK_O);
                    addMenuItem(aMenu, "Add User", KeyEvent.VK_U);
                    addMenuItem(aMenu, "Add Role", KeyEvent.VK_U);
                    addMenuItem(aMenu, "Add Deposit", KeyEvent.VK_D);
                    addMenuItem(aMenu, "Add Card Length", KeyEvent.VK_B);

                }

            //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc=" Admin + Manger">
                //</editor-fold>
            }

            //<editor-fold defaultstate="collapsed" desc="All Workers">
            aMenu = addMenuToMenuBar("management", KeyEvent.VK_O);
            addMenuItem(aMenu, "Add Station", KeyEvent.VK_A);
            addMenuItem(aMenu, "Add Line", KeyEvent.VK_F);
            addMenuItem(aMenu, "Add Site", KeyEvent.VK_P);
            addMenuItem(aMenu, "Add Activity", KeyEvent.VK_B);
            addMenuItem(aMenu, "Add Card", KeyEvent.VK_B);
            addMenuItem(aMenu, "Export/Import CSV", KeyEvent.VK_B);

            //</editor-fold>
        }
    }

    /**
     * creates the required internal frame by the given name and add it to the
     * {@link #desktop}}
     *
     * @param frame
     */
    public void createFrame(MyInternalFrame frame) {
        //MyInternalFrame frame = new MyInternalFrame(title);
        frame.setVisible(true); //necessary as of 1.3

        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    private void saveIfly() {
        if (!MainClass.getIsIflySaved()) {
            MainClass.SerializeIfly();
            MainClass.setIsIflySaved(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane.showInternalConfirmDialog(null, e.getSource().getClass().toString());
        MyInternalFrame ifram = new MyInternalFrame();
        switch (e.getActionCommand()) {

            case "Save":
                saveIfly();
                break;
            case "Add Activity":
                ifram = new Activity(e.getActionCommand(), selectedUserType, 1, new Date(1951, 03, 14), new Date(1951, 03, 14));

                break;
            case "Add Role":
                ifram = new AddRole(e.getActionCommand(), selectedUserType);

                break;
            case "Add Station":
                ifram = new Station(e.getActionCommand(), selectedUserType, 9);
                break;
            //Add Card "Add Card Length"
            case "Add Card":
                ifram = new Card(e.getActionCommand(), selectedUserType);
                break;
            case "Add Card Length":
                ifram = new CardLengths(e.getActionCommand(), selectedUserType);
                break;
            case "Add User":
                ifram = new Users(e.getActionCommand(), selectedUserType);
                break;
            //Add User
            case "Add Line":
                ifram = new Line(e.getActionCommand(), selectedUserType, null);
                break;
            case "Add Site":
                ifram = new Site(e.getActionCommand(), selectedUserType, 2);
                break;
            case "Add Deposit": {
                ifram = new GeneralParameters(e.getActionCommand(), selectedUserType);
                break;
            }
            case "Update customer Details":
                ifram = new UpdateCustomerDetails(e.getActionCommand(), selectedUserType);

                break;
            case "Update Paying Customer":
                ifram = new UpdatePayingCustomer(e.getActionCommand(), selectedUserType);

                break;

            case "Export/Import CSV":
                ifram = new ExportImportCsv(e.getActionCommand(), selectedUserType);
                break;
            case "Add Stop To Flight":
                ifram = new AddStop(e.getActionCommand(), selectedUserType);
                break;
            case "Add Pilot To Flight":
            case "Add FlightAttendant To Flight":
                ifram = new addPilotOrFlightAttendantToFlight(e.getActionCommand(), selectedUserType);
                break;
            case "Add Flight Order":
                ifram = new AddFlightOrder(e.getActionCommand(), selectedUserType);
                break;
            case "Remove Order":
                ifram = new RemoveOrder(e.getActionCommand(), selectedUserType);
                break;
            case "Add Flight Ticket":
                ifram = new addViewFlightTicket(e.getActionCommand(), selectedUserType);
                break;
            case "View orders":
                ifram = new ViewOrders(e.getActionCommand(), selectedUserType);
                break;
            case "View flights":
                ifram = new ViewFlights(e.getActionCommand(), selectedUserType);
                break;
            case "View tickets":
                ifram = new ViewTickets(e.getActionCommand(), selectedUserType);
                break;
            case "Get All Super Agents":
            case "Employee Of The Month":
            case "Get All This Summer Work Employees Sorted By Seniority":
            case "Get Branches Agents Sorted By Rating":
            case "Get The Top X Popular Flights":
            case "Get The Most Profitable Order":
            case "Get All Orders Of Most Profitable Customer":
            case "Get All Summer Flights Sorted By Number Of Stops":
            case "Get All This Summer Flights By Location":
            case "Get Potential Customers For Branch":
            case "Get Potential Customers For Agents":
            case "Get Flights Sorted By Occupancy":
            case "Find The Best Flight Back":
                ifram = new ViewQueries(e.getActionCommand(), selectedUserType);
                break;

        }
        createFrame(ifram);

    }

    /**
     * add new menu bar and add it to the {@link #MainMenuBar}}
     *
     * @param menuTitle
     * @param key
     * @return the menu bar
     */
    private JMenu addMenuToMenuBar(String menuTitle, int key) {
        menu = new JMenu(menuTitle);
        if (key > 0) {
            menu.setMnemonic(key);
        }

        MainMenuBar.add(menu);
        MainMenuBar.add(menu);
        return menu;

    }

    /**
     * add new menu item to a given menu
     *
     * @param theMenu
     * @param itemTitle
     * @param key
     * @return return the menu item
     */
    private JMenuItem addMenuItem(JMenu theMenu, String itemTitle, int key) {
        menuItem = new JMenuItem(itemTitle, key);
        menuItem.setActionCommand(itemTitle);
        theMenu.add(menuItem);
        menuItem.addActionListener(this);
        return menuItem;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainMenuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
			public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            @Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:


    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (!MainClass.getIsIflySaved()) {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Save changes before exit ?",
                    "Closing",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                //MainClass.SerializeIfly();
                saveIfly();
                login.setVisible(true);
                login.resetFocus(0);
                this.setVisible(false);
                //} else if (result == JOptionPane.CANCEL_OPTION) {

            } else if (result == JOptionPane.NO_OPTION) {
                MainClass.deserializeIfly();
                login.setVisible(true);
                login.resetFocus(0);
                this.setVisible(false);
            }

            MainClass.setIsIflySaved(true);
            return;
        }

        login.setVisible(true);
        login.resetFocus(0);
        this.setVisible(false);

    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MainMenuBar;
    // End of variables declaration//GEN-END:variables

}
