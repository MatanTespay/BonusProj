/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import init.ComboItem;
import init.MainClass;
import init.MyTableModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import core.Agent;
import core.Airplane;
import core.Branch;
import core.Customer;
import core.Employee;
import core.Flight;
import core.FlightAttendant;
import core.Order;
import core.Pilot;

/**
 * The class provide helper functions used in the the system
 *
 * @author Matan
 *
 */
public class HelperClass {

    /**
     * validates if the value is only number
     *
     * @param s the value to validate
     * @return true if value is number ; false otherwise
     */
    public static boolean isOnlyNumber(String s) {

        Pattern pattern = Pattern.compile("\\d+");
        boolean result;

        result = pattern.matcher(s).matches();
        return result;
    }

    /**
     * validates if the value is only 7 digit number, used in
     * {@link gui.PhonePanel}}
     *
     * @param s the value to validate
     * @return true if value is ok ; false otherwise
     */
    public static boolean isPhoneNumber(String s, String sf) {

        Pattern pattern;

        pattern = Pattern.compile(sf);
        boolean result;

        result = pattern.matcher(s).matches();
        return result;
    }

    /**
     * validates if the value is only letters , used in {@link gui.PhonePanel}}
     *
     * @param s the value to validate
     * @return true if value is ok ; false otherwise
     */
    public static boolean isOnlyLetters(String s) {

        //^(?=.*[a-zA-Z])(?=[-' '_]+)(?=.*\d)(?=.*(_|[^\w])).+$
        //\b(?:\w+\W+){1}(?=[-' '_]+(?:\w+\W+))\b.
        //
        Pattern pattern = Pattern.compile("[a-zA-Z]+([' '-_]?)[a-zA-Z]+");
        boolean result;

        result = pattern.matcher(s).matches();
        return result;
    }

    /**
     * validates if the value is date
     *
     * @param dateToValidate value to validate
     * @param dateFromat date
     * @return true if value is date, otherwise false
     */
    public static boolean isDate(String dateToValidate, String dateFromat) {
        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * validates if the value is email
     *
     * @return true if value is ok ; false otherwise
     * @param text value to validate
     * @return true if it's email otherwise false
     */
    private static boolean isValidEmail(String text) {
        if (text == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX);
        boolean result;

        result = pattern.matcher(text).matches();
        return result;
    }

    /**
     * get the error messgae , if any, after validation complete.
     *
     * @param text the value to validate
     * @param type the validation type performed
     * @return a message error if any error occurred, null if value passed
     * validtion
     */
    public static String getErrMsg(String text, String type) {
        String s = null;
        if (text != null) {
            if (text.trim().length() != 0) {
                switch (type) {
                    case "Text":
                        text = text.trim();

                        if (isOnlyLetters(text)) {
                            return null;
                        } else {
                            s = "Only letters";
                        }
                        break;
                    case "Number":
                        if (isOnlyNumber(text)) {
                            s = checkInteger(text);
                        } else {
                            s = "Only numbers";
                        }
                        break;
                    case "Date":
                        if (isDate(text, "dd/mm/yyyy")) {
                            return null;
                        } else {
                            s = "Invlid input (use - dd/mm/yyyy)";
                        }
                        break;
                    case "Email":
                        if (isValidEmail(text)) {
                            return null;
                        } else {
                            s = "Invlid Email address";
                        }
                        break;
                    case "Password":
                        if (text.length() < 4) {
                            return null;
                        } else {
                            s = "Minimum 4 characters";
                        }
                        break;
                }

                return s;
            }
        }

        return "Value cant be empty";

    }

    /**
     * get the table rows data.
     *
     * @param table the table
     * @return returns the table data
     */
    public static Object[][] getTableData(JTable table) {
        if (table == null) {
            return null;
        }

        MyTableModel dtm = (MyTableModel) table.getModel();
        if (dtm.getRowCount() > 0) {
            int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
            Object[][] tableData = new Object[nRow][nCol];
            for (int i = 0; i < nRow; i++) {
                for (int j = 0; j < nCol; j++) {
                    tableData[i][j] = dtm.getValueAt(i, j);
                }
            }
            return tableData;
        }
        return null;
    }

    /**
     * fill branches combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @param branchToRemove ,if given , exclude this branch
     */
    public static void fillBranchesComBo(JInternalFrame frame, JComboBox combo, Long branchToRemove) {

        try {
            Map<Integer, Branch> branches = MainClass.getIfly().getBranches();
            if (branches.isEmpty()) {
                return;
            }
            List<ComboItem> tempData = new ArrayList<ComboItem>();
            for (Map.Entry<Integer, Branch> entry : branches.entrySet()) {
                long integer = entry.getKey().longValue();
                if (branchToRemove != null && integer == branchToRemove) {
                    continue;
                }

                tempData.add(new ComboItem("" + integer + "", entry.getValue().getbranchName()));
            }
//            tempData.sort(new Comparator<ComboItem>() {
//
//                @Override
//                public int compare(ComboItem o1, ComboItem o2) {
//                   return o1.getLabel().compareTo(o2.getLabel());
//                   
//                }
//            });
            ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
            combo.setModel(new DefaultComboBoxModel(items));
        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting branches data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * fill orders combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the combcomBoBoxobox to fill
     * @param owner ,if given , fill comBoBox with the owner's orders, otherwise
     * fill all orders
     */
    public static void fillOrdersCombo(JInternalFrame frame, JComboBox combo, Object owner) {

        try {
            List<Order> list = MainClass.getIfly().getAllIrders();
            if (list.isEmpty()) {
                return;
            }
            List<ComboItem> tempData = new ArrayList<ComboItem>();

            if (owner != null) {
                if (owner instanceof Agent) {
                    Agent a = ((Agent) owner);
                    for (Order o : list) {
                        if (a.getOrders().contains(o)) {
                            tempData.add(new ComboItem("" + o.getOrderNumber() + "", "" + o.getOrderNumber() + ""));
                        }
                    }

                } else if (owner instanceof Customer) {
                    Customer c = ((Customer) owner);
                    for (Order o : list) {
                        if (c.getOrders().contains(o)) {
                            tempData.add(new ComboItem("" + o.getOrderNumber() + "", "" + o.getOrderNumber() + ""));
                        }
                    }

                }
            } else {
                for (Order o : list) {
                    tempData.add(new ComboItem("" + o.getOrderNumber() + "", "" + o.getOrderNumber() + ""));
                }
            }

            ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
            combo.setModel(new DefaultComboBoxModel(items));
        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting orders data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * fill airport combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @param airPortToRemove ,if given , exclude this airport
     */
    public static void fillAirportsCombo(JInternalFrame frame, JComboBox combo, ArrayList<E_Airports> airPortToRemove) {

        try {
            List<E_Airports> data = Arrays.asList(E_Airports.values());
            if (data.isEmpty()) {
                return;
            }
            List<ComboItem> tempData = new ArrayList<ComboItem>();
            for (E_Airports e_Airports : data) {
                if (airPortToRemove != null && airPortToRemove.contains(e_Airports)) {
                    continue;
                }
                tempData.add(new ComboItem(e_Airports.name(), e_Airports.getCity()));
            }
            ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
            combo.setModel(new DefaultComboBoxModel(items));
        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting airports data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * fill flights combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @param fillterFlights ,if given , exclude flights having 2 pilots
     */
    public static void fillFlightsCombo(JInternalFrame frame, JComboBox combo, boolean fillterFlights) {

        try {
            Map<Integer, Flight> flights = MainClass.getIfly().getFlights();
            List<ComboItem> tempData = new ArrayList<ComboItem>();
            if (flights.isEmpty()) {
                return;
            }
            for (Map.Entry<Integer, Flight> entry : flights.entrySet()) {
                if (fillterFlights) {
                    if (entry.getValue().getPilots().size() == 2) {
                        continue;
                    }
                }

                Integer integer = entry.getKey();

                ComboItem item = new ComboItem("" + integer + "", "" + integer + "");
                tempData.add(item);
            }
            ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
            combo.setModel(new DefaultComboBoxModel(items));

        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting flights data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * fill pilots combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param pilotsCombo the comBoBox to fill
     * @param flight
     */
    public static void fillPilotsCombo(JInternalFrame frame, JComboBox pilotsCombo, Flight flight) {
        try {
            Map<Integer, Employee> employess = MainClass.getIfly().getEmployees();
            if (employess.isEmpty()) {
                return;
            }
            List<ComboItem> pilots = new ArrayList<ComboItem>();
            for (Map.Entry<Integer, Employee> emp : employess.entrySet()) {
                if (emp.getValue() instanceof Pilot) {
                    Pilot p = ((Pilot) emp.getValue());
                    if (flight != null && (flight.getPilots().contains(p) || flight.getAirplane().getTotalNumberSeats() > p.getLicenseType().getMaxNumberOfPassangers())) {
                        continue;
                    }

                    ComboItem item = new ComboItem("" + emp.getValue().getEmployeeNumber()
                            + "", emp.getValue().getFirstName() + " " + emp.getValue().getLastName());
                    pilots.add(item);
                }
            }

            ComboItem[] plilotsItems = pilots.toArray(new ComboItem[pilots.size()]);
            pilotsCombo.setModel(new DefaultComboBoxModel(plilotsItems));
        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting pilots data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * fill FlightAttendant combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param fACombo the comBoBox to fill
     * @param flight exclude FlightAttendant that assigned to this flight
     */
    public static void fillFlightAttendantCombo(JInternalFrame frame, JComboBox fACombo, Flight flight) {

        try {
            Map<Integer, Employee> employess = MainClass.getIfly().getEmployees();
            if (employess.isEmpty()) {
                return;
            }
            List<ComboItem> fA = new ArrayList<ComboItem>();

            for (Map.Entry<Integer, Employee> emp : employess.entrySet()) {

                if (emp.getValue() instanceof FlightAttendant) {
                    FlightAttendant ft = (FlightAttendant) emp.getValue();
                    if (flight != null && flight.getFlightAttendants().contains(ft)) {
                        continue;
                    }
                    ComboItem item = new ComboItem("" + emp.getValue().getEmployeeNumber()
                            + "", emp.getValue().getFirstName() + " " + emp.getValue().getLastName());
                    fA.add(item);
                }

            }

            ComboItem[] fAItems = fA.toArray(new ComboItem[fA.size()]);

            fACombo.setModel(new DefaultComboBoxModel(fAItems));

        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting flights data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * fill customers combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @param isWithoutOrders if true exclude customer without orders, otherwise
     * fill all
     */
    public static void fillCustomersCombo(JInternalFrame frame, JComboBox combo, boolean isWithoutOrders) {

        try {
            Map<String, Customer> customers = MainClass.getIfly().getCustomers();
            if (customers.isEmpty()) {
                return;
            }
            List<ComboItem> tempData = new ArrayList<ComboItem>();
            for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                String string = entry.getKey();
                Customer customer = entry.getValue();

                if (isWithoutOrders) {
                    if (customer.getOrders().isEmpty()) {
                        continue;
                    }
                }

                ComboItem item = new ComboItem(string, customer.getFirstName() + " " + customer.getLastName());
                tempData.add(item);
            }

            ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
            combo.setModel(new DefaultComboBoxModel(items));
        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting flights data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * fill flight orders combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @param orderNumber the order number of the flight orders to fill
     */
    public static void getFlightsForSelectedOrder(JInternalFrame frame, JComboBox combo, int orderNumber) {

        Map<Integer, Flight> flightsForSelectedOrder = MainClass.getIfly().getFlightsForSelectedOrder(orderNumber);
        if (flightsForSelectedOrder.isEmpty()) {
            return;
        }
        List<ComboItem> tempData = new ArrayList<ComboItem>();

        for (Map.Entry<Integer, Flight> entry : flightsForSelectedOrder.entrySet()) {

            Integer integer = entry.getKey();
            ComboItem item = new ComboItem("" + integer + "", "" + integer + "");
            tempData.add(item);
        }
        ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
        combo.setModel(new DefaultComboBoxModel(items));
    }

    /**
     * fill agents combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @return the id of the first item
     *
     */
    public static Long fillAgentCombo(JInternalFrame frame, JComboBox combo) {
        Map<Integer, Employee> emps = MainClass.getIfly().getEmployees();
        if (emps.isEmpty()) {
            return null;
        }
        List<ComboItem> tempData = new ArrayList<ComboItem>();
        int idx = 0;
        Long branchNum = null;

        for (Map.Entry<Integer, Employee> entry : emps.entrySet()) {
            Integer integer = entry.getKey();
            Employee employee = entry.getValue();
            if (employee instanceof Agent) {
                tempData.add(new ComboItem(integer.toString(), employee.getFirstName() + " " + employee.getLastName()));
                if (idx == 0 && ((Agent) employee).getWorkBranch() != null) {
                    branchNum = ((Agent) employee).getWorkBranch().getbranchNumber();
                }
                idx++;
            }
        }

        ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
        combo.setModel(new DefaultComboBoxModel(items));

        return branchNum;
    }

    /**
     * fill passangers combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @param filter , if true , fill all customers which aren't on the flight,
     * otherswise fill all customers
     */
    public static void fillPassangers(JInternalFrame frame, JComboBox combo, int flight, boolean filter) {

        try {
            Map<String, Customer> passangersForFlightOrder = MainClass.getIfly().getPassangersForFlightOrder(flight, filter);
            if (passangersForFlightOrder.isEmpty()) {
                combo.setModel(new DefaultComboBoxModel());
                return;
            }
            List<ComboItem> tempData = new ArrayList<ComboItem>();

            for (Map.Entry<String, Customer> entry : passangersForFlightOrder.entrySet()) {
                String string = entry.getKey();
                Customer customer = entry.getValue();

                ComboItem item = new ComboItem(string, customer.getFirstName() + " " + customer.getLastName());
                tempData.add(item);
            }

            ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
            combo.setModel(new DefaultComboBoxModel(items));
        } catch (NullPointerException e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting passangers data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * set an id of customer to fit the Key format of {@link init.IFly}} class
     *
     * @param id the id to fix
     * @return the id
     */
    public static String getFixID(String id) {
        if (id == null) {
            return null;
        }
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 8 - id.length(); i++) {
            b.append("0");
        }
        return b.append(id).toString();
    }

    public static void aligmentCell(JTable table) {
        if (table == null) {
            return;
        }

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int j = 0; j < table.getColumnCount(); j++) {
            table.getColumnModel().getColumn(j).setCellRenderer(leftRenderer);

        }

    }

    /**
     * check if a string represent a number
     *
     * @param s the value to validate
     * @return a string of the error , if occurred
     */
    public static String checkInteger(String s) {
        String err = null;
        int val;
        try {
            val = Integer.parseInt(s);
            return err;
        } catch (NumberFormatException e) {
            // It's OK to ignore "e" here because returning a default value is the documented behaviour on invalid input.
            return "Invalid input, numer is to big";
        }
    }

    /**
     * fill airplanes combobox
     *
     * @param frame the parent frame of the comBoBox
     * @param combo the comBoBox to fill
     * @param number , if given , exclude this airplane, otherwise fill all
     * airplaines
     */
    public static void fillAirplaines(JInternalFrame frame, JComboBox combo, Integer number) {

        try {
            List<ComboItem> tempData = new ArrayList<ComboItem>();
            ArrayList<Airplane> airplaines = MainClass.getIfly().getAirplaines(number);
            for (Airplane airplane : airplaines) {
                String val = String.valueOf(airplane.getAirplaneNumber());
                tempData.add(new ComboItem(val, val));
            }

            ComboItem[] items = tempData.toArray(new ComboItem[tempData.size()]);
            combo.setModel(new DefaultComboBoxModel(items));

        } catch (Exception e) {
            JOptionPane.showInternalConfirmDialog(frame, "Error in getting airplaines data", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * set a name of a given entity , to be presented in a table
     *
     * @param values
     * @return the name of the entity
     */
    public static String setName(String[] values) {
        StringBuilder b = new StringBuilder("<html><center>");

        for (String value : values) {
            if (value != null) {
                b.append(value).append("<br>");
            }
        }
        b.append("</html>");
        return b.toString();
    }

    /**
     * set a cell width of a table
     *
     * @param table
     */
    public static void setCellWidth(JTable table) {

        TableColumn column = null;
        for (int i = 0; i < 3; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(25);
            }
            break;
        }
    }

    /**
     * check if a given value exist in a comBoBox
     *
     * @param combo the combo to check
     * @param value the value to search
     * @return {@link init.ComboItem } represent the given value
     */
    public static ComboItem getItemIndex(JComboBox combo, Object value) {
        if (combo.getModel().getSize() > 0) {
            int count = combo.getItemCount();
            for (int i = 0; i < count; i++) {
                ComboItem item = ((ComboItem) combo.getItemAt(i));
                if (item.getValue().equals("" + value + "")) {
                    return item;
                }
            }
        }
        return null;
    }
}
