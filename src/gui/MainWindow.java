/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.DesktopScrollPane;
import init.CloseAction;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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

    static String selectedUserType;

    private JMenu menu, submenu;
    private JMenuItem menuItem;
    private JDesktopPane desktop;
    private DesktopScrollPane pane;
    private Login login;
    private List params;

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
        desktop = new javax.swing.JDesktopPane();
        Icon i = getIconName("Bg_Logo_Blue.jpg");
        if (i != null) {
            Image img = ((ImageIcon) i).getImage();

            desktop = new javax.swing.JDesktopPane() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            };
        }

        setBounds(0, 0, 900, 750);

        this.setLocationRelativeTo(null);
        ImageIcon mainIcon = (ImageIcon) getIconName("london1.png");
        this.setIconImage(mainIcon.getImage());
        //desktop = new JDesktopPane();
        // desktop.add(panel);
        //pane = new DesktopScrollPane(getDesktop());

        //setContentPane(pane);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        fillMenuBar();
        pack();
    }

    /**
     * Creates new form MainWindow
     *
     * @param title the tile
     */
    public MainWindow(String title) {
        super(title);
    }

    private void AdminORManagerMenu(JMenu aMenu) {
        //Manager to
        aMenu = addMenuToMenuBar("System", KeyEvent.VK_O, "System.png");
        addMenuItem(aMenu, "Add Deposit", KeyEvent.VK_D, "dollar183.png");

        //<editor-fold defaultstate="collapsed" desc="Only Admin">
        if (!selectedUserType.equals("Manager")) {
            addMenuItem(aMenu, "Users", KeyEvent.VK_U, "addUser.png");
            addMenuItem(aMenu, "Roles", KeyEvent.VK_U, "Role.png");
            addMenuItem(aMenu, "Card Length", KeyEvent.VK_B, "Calendar-icon.png");
            addMenuItem(aMenu, "Zone", KeyEvent.VK_B, "Zone.png");
        }
        //</editor-fold>

        aMenu = addMenuToMenuBar("Management", KeyEvent.VK_O, "management.png");
        submenu = addMenuToMenuBar("Activity", KeyEvent.VK_A, "travel18.png");
        addMenuItem(submenu, "Add Activity", KeyEvent.VK_B, "add.png");
        addMenuItem(submenu, "Edit Activity", KeyEvent.VK_A, "edit.png");
        aMenu.add(submenu);
        aMenu.addSeparator();

        submenu = addMenuToMenuBar("Card", KeyEvent.VK_A, "card_with_pic.png");
        addMenuItem(submenu, "Add Card", KeyEvent.VK_B, "add.png");
        addMenuItem(submenu, "Edit Card", KeyEvent.VK_A, "edit.png");
        aMenu.add(submenu);
        aMenu.addSeparator();

        submenu = addMenuToMenuBar("Line", KeyEvent.VK_A, "metro_lines.png");
        addMenuItem(submenu, "Add Line", KeyEvent.VK_B, "add.png");
        addMenuItem(submenu, "Edit Line", KeyEvent.VK_A, "edit.png");
        aMenu.add(submenu);
        aMenu.addSeparator();

        submenu = addMenuToMenuBar("Site", KeyEvent.VK_A, "map_zone.png");
        addMenuItem(submenu, "Add Site", KeyEvent.VK_B, "add.png");
        addMenuItem(submenu, "Edit Site", KeyEvent.VK_A, "edit.png");
        addMenuItem(submenu, "Add Site Type", KeyEvent.VK_A, "sitetype.png");
        aMenu.add(submenu);
        aMenu.addSeparator();

        submenu = addMenuToMenuBar("Queries", KeyEvent.VK_A, "question_mark.png");
        addMenuItem(submenu, "Remote Sites", KeyEvent.VK_B, "icon_for_the_aplication.png");
        addMenuItem(submenu, "Unused Paper Cards", KeyEvent.VK_A, "paper.png");
        addMenuItem(submenu, "Main Stations", KeyEvent.VK_A, "subway6.png");
        addMenuItem(submenu, "Most Active Stations", KeyEvent.VK_A, "train36.png");
        addMenuItem(submenu, "Cards That Have Activity Issues", KeyEvent.VK_A, "badCard.png");
        addMenuItem(submenu, "Very Active Oyster Cards", KeyEvent.VK_A, "oyster.png");
        addMenuItem(submenu, "Profitable Deposit Years", KeyEvent.VK_A, "chart.png");
        aMenu.add(submenu);
        aMenu.addSeparator();

        submenu = addMenuToMenuBar("Station", KeyEvent.VK_A, "bus46.png");
        addMenuItem(submenu, "Add Station", KeyEvent.VK_B, "add.png");
        addMenuItem(submenu, "Edit Station", KeyEvent.VK_A, "edit.png");
        aMenu.add(submenu);
        aMenu.addSeparator();

        addMenuItem(aMenu, "Export/Import CSV", KeyEvent.VK_B, "folder.png");

    }

    private void MunicipalityMenu(JMenu aMenu) {
        aMenu = addMenuToMenuBar("Management", KeyEvent.VK_O, "management.png");
        addMenuItem(aMenu, "Export/Import CSV", KeyEvent.VK_B, "folder.png");
    }

    private void UserMenu(JMenu aMenu) {
        aMenu = addMenuToMenuBar("Management", KeyEvent.VK_O, "management.png");

        addMenuItem(aMenu, "Add Card", KeyEvent.VK_B, "card_with_pic.png");
        aMenu.addSeparator();
        addMenuItem(aMenu, "Add Activity", KeyEvent.VK_B, "travel18.png");

    }

    private Icon getIconName(String name) {
        URL u = getClass().getResource("/Img/" + name + "");
        if (u != null) {
            //System.out.println(u.getFile());
            Icon i = new javax.swing.ImageIcon(u);
            return i;
        }
        return null;
    }

    /**
     * fill the {@link #MainMenuBar}}
     */
    private void fillMenuBar() {

        JMenu aMenu = addMenuToMenuBar("File", 'F', "home.png");

        ImageIcon i = (ImageIcon) getIconName("exit.png");
        CloseAction closeAction = new CloseAction("Exit", i, "Close The Application", new Integer(KeyEvent.VK_X), this);
        menuItem = new JMenuItem(closeAction);

        menu.add(menuItem);

        switch (selectedUserType) {
            case "Customer":
                UserMenu(aMenu);
                break;
            case "Municipality":
                MunicipalityMenu(aMenu);
                break;
            default:
                AdminORManagerMenu(aMenu);
                break;
        }
//        if (!selectedUserType.equals("Customer")) {
//            //aMenu = addMenuToMenuBar("Operations", KeyEvent.VK_O);
//
//            if (!selectedUserType.equals("Municipality")) {
//
//                //<editor-fold defaultstate="collapsed" desc="All Workers">
//                aMenu = addMenuToMenuBar("management", KeyEvent.VK_O);
//
//            } else {
//
//            }
//
//            submenu = addMenuToMenuBar("Activity", KeyEvent.VK_A);
//            addMenuItem(submenu, "Add Activity", KeyEvent.VK_B);
//            addMenuItem(submenu, "Edit Activity", KeyEvent.VK_A);
//            aMenu.add(submenu);
//            aMenu.addSeparator();
//
//            submenu = addMenuToMenuBar("Card", KeyEvent.VK_A);
//            addMenuItem(submenu, "Add Card", KeyEvent.VK_B);
//            addMenuItem(submenu, "Edit Card", KeyEvent.VK_A);
//            aMenu.add(submenu);
//            aMenu.addSeparator();
//
//            submenu = addMenuToMenuBar("Line", KeyEvent.VK_A);
//            addMenuItem(submenu, "Add Line", KeyEvent.VK_B);
//            addMenuItem(submenu, "Edit Line", KeyEvent.VK_A);
//            aMenu.add(submenu);
//            aMenu.addSeparator();
//
//            submenu = addMenuToMenuBar("Site", KeyEvent.VK_A);
//            addMenuItem(submenu, "Add Site", KeyEvent.VK_B);
//            addMenuItem(submenu, "Edit Site", KeyEvent.VK_A);
//            aMenu.add(submenu);
//            aMenu.addSeparator();
//
//            submenu = addMenuToMenuBar("Queries", KeyEvent.VK_A);
//            addMenuItem(submenu, "Remote Sites", KeyEvent.VK_B);
//            addMenuItem(submenu, "Unused Paper Cards", KeyEvent.VK_A);
//            addMenuItem(submenu, "Main Stations", KeyEvent.VK_A);
//            addMenuItem(submenu, "Most Active Stations", KeyEvent.VK_A);
//            addMenuItem(submenu, "Cards That Have Activity Issues", KeyEvent.VK_A);
//            addMenuItem(submenu, "Very Active Oyster Cards", KeyEvent.VK_A);
//            addMenuItem(submenu, "Profitable Deposit Years", KeyEvent.VK_A);
//            aMenu.add(submenu);
//            aMenu.addSeparator();
//
//            submenu = addMenuToMenuBar("Station", KeyEvent.VK_A);
//            addMenuItem(submenu, "Add Station", KeyEvent.VK_B);
//            addMenuItem(submenu, "Edit Station", KeyEvent.VK_A);
//            aMenu.add(submenu);
//            aMenu.addSeparator();
//
//            addMenuItem(aMenu, "Export/Import CSV", KeyEvent.VK_B);
//            aMenu.addSeparator();
//
//            //</editor-fold>
//        }

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
        Dimension desktopSize = getDesktop().getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, ((desktopSize.height + 5) - jInternalFrameSize.height) / 2);

        getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

//    private void saveIfly() {
//        if (!MainClass.getIsIflySaved()) {
//            MainClass.SerializeIfly();
//            MainClass.setIsIflySaved(true);
//        }
//    }
//    private Object createInternalFrameClass(String className, List<Object> params) {
//        try {
//
//            Class<?> clazz = Class.forName(className);
//
//            List<Class<?>> argTypes = new ArrayList<>();
//            //set the list of arg types
//            for (Object arg : params) {
//                argTypes.add(arg.getClass());
//            }
//            //create the public constructor of the class that match the params types
//            Constructor constructor = clazz.getConstructor(
//                    argTypes.toArray(new Class<?>[argTypes.size()]));
//            //create the class with the constructor
//            Object object = constructor.newInstance(
//                    params.toArray(new Object[params.size()]));
//
//            return object;
//        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane.showInternalConfirmDialog(null, e.getSource().getClass().toString());
        MyInternalFrame ifram = new MyInternalFrame();

        setParams(new ArrayList<>());;

        switch (e.getActionCommand()) {

            case "Save":
//                saveIfly();
                break;
            case "Add Activity":
                ifram = new Activity(e.getActionCommand(), selectedUserType);
                break;
            case "Edit Activity":
                ifram = new ActivityDialog(e.getActionCommand(), selectedUserType, this);
                break;
            case "Add Card":
                ifram = new Card(e.getActionCommand(), selectedUserType);
                break;
            case "Edit Card":
                ifram = new CardDialog(e.getActionCommand(), selectedUserType, this);
                break;
            case "Roles":
                ifram = new Role(e.getActionCommand(), selectedUserType);
                break;
            case "Add Station":
                ifram = new Station(e.getActionCommand(), selectedUserType);
                break;
            case "Edit Station":
                ifram = new StationDialog(e.getActionCommand(), selectedUserType, this);
                break;
            case "Card Length":
                ifram = new CardLengths(e.getActionCommand(), selectedUserType);
                break;
            case "Users":
                ifram = new User(e.getActionCommand(), selectedUserType);
                break;
            case "Add Line":
                ifram = new Line(e.getActionCommand(), selectedUserType);
                break;
            case "Edit Line":
                ifram = new LineDialog(e.getActionCommand(), selectedUserType, this);
                break;
            case "Add Site":
                ifram = new Site(e.getActionCommand(), selectedUserType);
                break;
            case "Edit Site":
                ifram = new SiteDialog(e.getActionCommand(), selectedUserType, this);
                break;
            case "Add Deposit":
                ifram = new Deposits(e.getActionCommand(), selectedUserType);
                break;
            case "Export/Import CSV":
                ifram = new ExportImportCsv(e.getActionCommand(), selectedUserType, this);
                break;
            case "Zone":
                ifram = new Zones(e.getActionCommand(), selectedUserType);
                break;
            case "Add Site Type":
                ifram = new SiteTypes(e.getActionCommand(), selectedUserType);
                break;

            case "Remote Sites":
            case "Unused Paper Cards":
            case "Main Stations":
            case "Most Active Stations":
            case "Cards That Have Activity Issues":
            case "Very Active Oyster Cards":
            case "Profitable Deposit Years":
                ifram = new QueryForm(e.getActionCommand(), selectedUserType);
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
    private JMenu addMenuToMenuBar(String menuTitle, int key, String IconName) {

        menu = new JMenu(menuTitle);
        if (key > 0) {
            menu.setMnemonic(key);
        }
        if (IconName != null && !IconName.equals("")) {
            Icon i = getIconName(IconName);
            if (i != null) {
                menu.setIcon(i);
            }

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
     * @param param the value of param
     * @return the javax.swing.JMenuItem
     */
//    private JMenuItem addMenuItem(JMenu theMenu, String itemTitle, int key, java.lang.String param) {
//        String[] values = param.split(",");
//        menuItem = new JMenuItem(values[0] + " " + values[1], key);
//        menuItem.setActionCommand(param);
//        theMenu.add(menuItem);
//        menuItem.addActionListener(this);
//        return menuItem;
//    }
    private JMenuItem addMenuItem(JMenu theMenu, String itemTitle, int key, String IconName) {

        menuItem = new JMenuItem(itemTitle, key);
        if (IconName != null && !IconName.equals("")) {
            Icon i = getIconName(IconName);
            if (i != null) {
                menuItem.setIcon(i);
            }

        }
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
//        if (!MainClass.getIsIflySaved()) {
//            int result = JOptionPane.showConfirmDialog(
//                    this,
//                    "Save changes before exit ?",
//                    "Closing",
//                    JOptionPane.YES_NO_CANCEL_OPTION);
//
//            if (result == JOptionPane.YES_OPTION) {
//                //MainClass.SerializeIfly();
//                saveIfly();
//                login.setVisible(true);
//                login.resetFocus(0);
//                this.setVisible(false);
//                //} else if (result == JOptionPane.CANCEL_OPTION) {
//
//            } else if (result == JOptionPane.NO_OPTION) {
//                MainClass.deserializeIfly();
//                login.setVisible(true);
//                login.resetFocus(0);
//                this.setVisible(false);
//            }
//
//            MainClass.setIsIflySaved(true);
//            return;
//        }

        getLogin().setVisible(true);
        getLogin().resetFocus(0);
        this.setVisible(false);

    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MainMenuBar;
    // End of variables declaration//GEN-END:variables

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public static String getSelectedUserType() {
        return selectedUserType;
    }

    /**
     * @return the desktop
     */
    public JDesktopPane getDesktop() {
        return desktop;
    }

    /**
     * @param desktop the desktop to set
     */
    public void setDesktop(JDesktopPane desktop) {
        this.desktop = desktop;
    }

    /**
     * @return the pane
     */
    public DesktopScrollPane getPane() {
        return pane;
    }

    /**
     * @param pane the pane to set
     */
    public void setPane(DesktopScrollPane pane) {
        this.pane = pane;
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }

}
