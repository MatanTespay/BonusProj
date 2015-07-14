package init;

import exceptions.AddBranchExeption;
import exceptions.AddCustomerExeption;
import exceptions.AddEmployeeExeption;
import exceptions.AddFlightExeption;
import exceptions.AddFlightToOrderExeption;
import exceptions.AddPilotOrFlightAttendantExeption;
import exceptions.AddStopToFlightExeption;
import exceptions.AddessUpdateExeption;
import exceptions.CancelOrderExeption;
import exceptions.ConnectAgentToBranchExeption;
import exceptions.PayingCustomerExeption;
import exceptions.addFlightTicketExeption;
import gui.Login;
import gui.MainWindow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utils.E_Airports;
import utils.E_Cities;
import utils.E_LicenseType;
import core.Address;
import core.Agent;
import core.Airplane;
import core.Branch;
import core.Customer;
import core.Employee;
import core.Flight;
import core.FlightAttendant;
import core.Pilot;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Main Class -The program runner
 *
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class MainClass {

    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;
    public Login log;
    
    // DB fields
    public static Connection con = null;
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_CONNECTION = "jdbc:sqlserver://localhost:1433;databaseName=LondonU2;user=TOM;password=1234;";

    
    /**
     * The main object for the program
     */
    private static IFly IFly;
    private static boolean isSaved = true;
    //private static HashMap<String,String> currentUser;
    private static AbstractMap.SimpleEntry user;

    // <editor-fold defaultstate="collapsed" desc="region">
    // </editor-fold>
    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, UnsupportedLookAndFeelException {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
        
                IFly = init.IFly.getInstance();                    //SerializeIfly();
                deserializeIfly();
                con = getDBConnection();
                new Login().setVisible(true);

            }
        });
    }//END OF ~ main

    public static IFly getIfly() {
        return IFly;
    }

    public static boolean getIsIflySaved() {
        return isSaved;
    }

    public static void setIsIflySaved(boolean  state) {
        isSaved = state;
    }

    public static void setUserData(String type, String Name, char[] pass) {
        user = IFly.getUserObject(type, Name, pass);
    }

    public static AbstractMap.SimpleEntry getUserData() {
        return user;
    }

    /**
     *
     * serialize data
     */
    public static void SerializeIfly() {

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("IFly.ser"));
            outputStream.writeObject(IFly);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    /**
     * deserialize data
     */
    public static void deserializeIfly() {

        try {
            File file = new File("IFly.ser");
            if (file.exists()) {
                inputStream = new ObjectInputStream(new FileInputStream(file));
                IFly = (IFly) inputStream.readObject();
            }else{
                IFly.resetNewIfly();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInternalConfirmDialog(null, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInternalConfirmDialog(null, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showInternalConfirmDialog(null, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showInternalConfirmDialog(null, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

                } catch (IOException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
    
    private static Connection getDBConnection() {
 
		Connection dbConnection = null;
 
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
 
		try {
			dbConnection = DriverManager.getConnection(
                             DB_CONNECTION);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
    

}// ~ END OF Class MainClass
