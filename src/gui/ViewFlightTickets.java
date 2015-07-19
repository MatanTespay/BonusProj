/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import init.ComboItem;
import init.MainClass;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneConstants;
import javax.swing.ToolTipManager;

import utils.E_ClassType;
import utils.HelperClass;
import core.Customer;
import core.Flight;
import core.FlightOrder;
import exceptions.addFlightTicketExeption;
import init.JScrollableToolTip;

/**
 *The class represents the screen which enable users to add {@link core.FlightOrder.FlightTicket} for  {@link core.Customer} int given  {@link core.Flight}  

* @author Matan
*/
public class ViewFlightTickets extends MyInternalFrame {

    Object[][] classValues;
    JPanel jpMain;
    Flight flight;
    int order;
    String customerPass;
    Customer customer;
    private JComboBox cbPassenger;
    private JButton btnOk;
    boolean isBusy;
    AbstractButton button;
    JLabel money;

    /**
     * Creates new form ViewFlightTickets
     *
     * @param title 
     * @param type
     * @param parent
     * @param flight
     * @param order
     * @param c
     */
    public ViewFlightTickets(String title, String type, JInternalFrame parent, Flight flight, int order, String c) {
        super(title, type);

        this.flight = flight;
        this.parent = parent;
        this.order = order;
        this.customerPass = c;
        if (c != null) {
            this.customer = MainClass.getIfly().getCustomers().get(c);
        }
        setWindow();
        addTableToPanel();
        centerFrame();
        
        //initComponents();
    }

    /**
     * centers the window
     */
    private void centerFrame() {
        if (parent != null) {
            Rectangle r = parent.getBounds();

            Dimension desktopSize = parent.getSize();
            Dimension jInternalFrameSize = this.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            int xPos = width + (int) r.getX(), yPos = height + (int) r.getY();
            if (xPos < 0) {
                xPos = 0;
            }
            if (yPos < 0) {
                yPos = 0;
            }
            this.setLocation(xPos, yPos);

        }

    }

    /**
     * set the window title buttons state
     */
    private void setWindow() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(false);

    }

    /**
     * fill {@link #cbPassenger}}
     */
    private void fillPassengers(boolean filter) {

        if (flight != null) {
            HelperClass.fillPassangers(this, cbPassenger, flight.getFlightNumber(), filter);
        }

    }

    private void btnOkActionPerformed(ActionEvent evt) {
        if (button != null) {
            SaveReservation(button);

        }
    }

    /**

     * save reservation for the seleccted seat
    
     * @param btn {@link #button}}  
     * fill 

     */
    private void SaveReservation(AbstractButton btn) {

        if (btn == null || order == 0 || flight == null || cbPassenger.getModel().getSize() <= 0) {
            return;
        }

        String[] values = btn.getActionCommand().split(":");

        if (values.length <= 0) {
            return;
        }

        int flightNumber, orderNum;
        orderNum = this.order;
        flightNumber = this.flight.getFlightNumber();
        int rowNum = Integer.parseInt(values[0]);
        int seat = Integer.parseInt(values[1]);
        String classType = values[2];
        String passanger = ((String)((ComboItem) cbPassenger.getSelectedItem()).getKey());
        String passName = ((ComboItem) cbPassenger.getSelectedItem()).getLabel();
        try {
            MainClass.getIfly().addFlightTicketToFlightOrder(flightNumber, orderNum, passanger, seat, rowNum, classType);
            JOptionPane.showInternalConfirmDialog(this, "Saved changes", "Confirmation", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            setImg(button, -1);
            isBusy = false;
            StringBuilder b = new StringBuilder();
            b.append(rowNum).append(":")
                    .append(seat).append(":").append(classType).append(":").append(values[3]).append(":").append(passName);
            button.setToolTipText(setTextForToolTip(b.toString()));
            button.setActionCommand(null);
            button = null;
            btnOk.setEnabled(false);
            HelperClass.fillPassangers(this, cbPassenger, flightNumber, true);            
            setIflyState(false);
        } catch (addFlightTicketExeption ex) {
            JOptionPane.showInternalConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * add the dynamically created seats to {@link #jpMain}}
     */
    private void addTableToPanel() {

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        if (flight != null) {
            classValues = new Object[][]{{"Business", flight.getAirplane().getNumberOfSeatsBusinessClass()}, {"First", flight.getAirplane().getNumberOfSeatsFirstClass()}, {"Tourists", flight.getAirplane().getNumberOfSeatsTouristsClass()}};
        } else {
            shoMissingDataMsg("Flight information was missing");
            return;

        }

        if (parent != null && !(parent instanceof ViewFlights)) {
            JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            JLabel l = new JLabel("Select Customer");
            cbPassenger = new javax.swing.JComboBox();
            cbPassenger.setPreferredSize(new Dimension(100, 25));
            fillPassengers(true);
            btnOk = new javax.swing.JButton();
            btnOk.setEnabled(false);
            btnOk.setText("Submit");
            btnOk.addActionListener(new java.awt.event.ActionListener() {
                @Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOkActionPerformed(evt);
                }
            });
            btnOk.setToolTipText(utils.Constants.saveButtonToolTip);
            temp.add(l);
            temp.add(cbPassenger);
            temp.add(btnOk);

            main.add(temp);

        }

        jpMain = new JPanel();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));

        int[] size = createSeats(classValues, jpMain);
        jpMain.setPreferredSize(new Dimension(size[0], size[1]));
        JScrollPane scroller = new JScrollPane(jpMain, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        main.add(scroller);
        add(main);

        int count = flight.getAirplane().getTotalNumberSeats() > 200 ? 12 : 8;
        if (count == 8) {
            setSize(new Dimension(size[0] + 50, 480));
        } else {
            setSize(new Dimension(size[0] + 30, 480));
        }

    }

    /**
     * create the seats for the flight values  {@link #classValues}} and add it to the {@link #jpMain}}
    
     * @param values
     * @param panel
     * @return arry of table width and highest
     */
    private int[] createSeats(Object[][] values, JPanel panel) {
        JPanel one = new JPanel();
        int pW = 0, pH = 50;
        ToolTipManager.sharedInstance().setDismissDelay(15000);
        for (int k = 0; k < values.length; k++) {

            int h = 0, w = 0, totalh = 0;
            int count = flight.getAirplane().getTotalNumberSeats() > 200 ? 12 : 8;

            Dimension rt = null;
            Dimension btnD = null;
            Dimension spaceD = null;
            String clazz = (String) values[k][0];
            int first = (int) values[k][1];
            int left = first % count;
            int row = first / count;
            double tempcost = flight.getFlightCost() * E_ClassType.valueOf(clazz).getFee();

            one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
           
            JLabel l = new JLabel(clazz);
            one.add(new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0)).add(l));

            pH += l.getHeight();

            one.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            for (int i = 0; i < row; i++) {
                int t = 1;

                JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

                for (int j = 0; j < count; j++) {
                    StringBuilder b = new StringBuilder();

                    JButton btn1 = new JButton(){
                    	
                    	@Override
                        public JToolTip createToolTip() {
                    		JScrollableToolTip tip = new JScrollableToolTip(250, 120,this);
                            tip.setComponent(this);
                            return tip;
                        }
                    };
                    btn1.setPreferredSize(new Dimension(30, 30));
                    FlightOrder.FlightTicket tf = MainClass.getIfly().isTicketReservedInFlight(flight.getFlightNumber(), E_ClassType.valueOf(clazz), (i + 1), t);

                    ImageIcon icon = null;
                    if (tf != null) {

                        if (tf.getFlightNumber() == null && tf.getSeat() == 0 && tf.getRow() == 0) {
                            //seat is free
                            if (parent != null && !(parent instanceof ViewFlights)) {
                                btn1.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        doAction(e);
                                    }
                                });

                            }

                            b.append((i + 1)).append(":").append(t).append(":")
                                    .append(clazz).append(":").append(tempcost);
                            icon = new javax.swing.ImageIcon(getClass().getResource("/available_seat_img.gif"));
                        } else {
                            //set is taken

                            b.append((i + 1)).append(":").append(t).append(":")
                                    .append(clazz).append(":").append(tf.getSeatCost()).append(":").append(tf.getPassenger().getFirstName()).append(" ")
                                    .append(tf.getPassenger().getLastName());
                            icon = new javax.swing.ImageIcon(getClass().getResource("/booked_seat_img.gif"));
                        }
                    }

                    btn1.setActionCommand(b.toString());
                    btn1.setToolTipText(setTextForToolTip(b.toString()));

                    Image img = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);;
                    icon = new ImageIcon(img);
                    btn1.setIcon(icon);

                    JLabel space = new JLabel();
                    temp.add(btn1);
                    if (count == 12) {
                        if (j == 2 || j == 8) {
                            space.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));
                            temp.add(space);
                        }
                    } else if (count == 8) {
                        if (j == 3) {
                            space.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));
                            temp.add(space);
                        }
                    }

                    btnD = btn1.getPreferredSize();
                    spaceD = space.getPreferredSize();
                    w = btnD.width + spaceD.width;

                    rt = temp.getPreferredSize();
                    if (i == 0 && k == 0) {
                        pW += btnD.width + spaceD.width;
                    }
                    t++;

                }
                h = rt.height;
                pH += btnD.height;
                totalh += 50;
                one.add(temp);
                rt = one.getPreferredSize();
            }

            w *= count;
            one.setPreferredSize(new Dimension(w, h * (row + 1)));
            one.validate();

            if (left > 0) {
                JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

                int t = 1;
                for (int i = 0; i < left; i++) {
                    StringBuilder b = new StringBuilder();
                    JButton btn1 = new JButton(){
                    	
                    	@Override
                        public JToolTip createToolTip() {
                    		
                            JScrollableToolTip tip = new JScrollableToolTip(250,120,this);
                            tip.setComponent(this);
                            return tip;
                        }
                    };
                    btn1.setPreferredSize(new Dimension(30, 30));
                    FlightOrder.FlightTicket tf = MainClass.getIfly().isTicketReservedInFlight(flight.getFlightNumber(), E_ClassType.valueOf(clazz), (row + 1), t);

                    ImageIcon icon = null;
                    if (tf != null) {

                        if (tf.getFlightNumber() == null && tf.getSeat() == 0 && tf.getRow() == 0) {
                            //set is free
                            if (parent != null && !(parent instanceof ViewFlights)) {
                                btn1.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        doAction(e);
                                    }
                                });

                            }
                            b.append((row + 1)).append(":").append(t).append(":")
                                    .append(clazz).append(":").append(tempcost);
                            icon = new javax.swing.ImageIcon(getClass().getResource("/available_seat_img.gif"));
                        } else {
                            //set is taken

                            b.append((row + 1)).append(":").append(t).append(":")
                                    .append(clazz).append(":").append(tf.getSeatCost()).append(":").append(tf.getPassenger().getFirstName()).append(" ")
                                    .append(tf.getPassenger().getLastName());
                            icon = new javax.swing.ImageIcon(getClass().getResource("/booked_seat_img.gif"));
                        }
                    }

                    btn1.setActionCommand(b.toString());
                    btn1.setToolTipText(setTextForToolTip(b.toString()));

                    Image img = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);;
                    icon = new ImageIcon(img);
                    btn1.setIcon(icon);
                    JLabel space = new JLabel();
                    temp.add(btn1);
                    if (count == 12) {
                        if (i == 2 || i == 8) {
                            space.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));

                            temp.add(space);
                        }
                    } else if (count == 8) {
                        if (i == 3) {
                            space.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));

                            temp.add(space);
                        }

                    }
                    btnD = btn1.getPreferredSize();

                    t++;

                }

                pH += btnD.height;

                one.add(temp);

            }

            panel.add(one);

        }
        return new int[]{pW, pH+200};
    }

    /**
     *fired on seat selection 

     * @param e action event
     */
    private void doAction(ActionEvent e) {

        AbstractButton abstractButton = (AbstractButton) e.getSource();

        if (abstractButton.getActionCommand().equals("") || cbPassenger.getModel().getSize() <= 0) {
            return;
        }

        if (isBusy && !button.equals(abstractButton)) {
            JOptionPane.showInternalConfirmDialog(abstractButton.getParent(), "Please finish reservation for the selected seat:\n" + getSaetDeatils(button.getActionCommand()) + "\nOr uncheck it to select a new seat", "Waringn", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (isBusy && button.equals(abstractButton)) {
            isBusy = false;
            setImg(abstractButton, 0);
            button = null;
            btnOk.setEnabled(false);
            return;

        }

        button = abstractButton;
        isBusy = true;
        setImg(abstractButton, 1);
        btnOk.setEnabled(true);

    }

    /**
     * set the image for the dynamically created seat
     * @param button
     * @param t
     */
    private void setImg(AbstractButton button, int t) {

        ImageIcon icon;
        if (t == 1) {
            icon = new javax.swing.ImageIcon(getClass().getResource("/selected_seat_img.gif"));
            Image img = icon.getImage().getScaledInstance(20, 25, java.awt.Image.SCALE_SMOOTH);;
            icon = new ImageIcon(img);
        } else if (t == 0) {
            icon = new javax.swing.ImageIcon(getClass().getResource("/available_seat_img.gif"));
            Image img = icon.getImage().getScaledInstance(20, 25, java.awt.Image.SCALE_SMOOTH);;
            icon = new ImageIcon(img);
        } else {
            icon = new javax.swing.ImageIcon(getClass().getResource("/booked_seat_img.gif"));
            Image img = icon.getImage().getScaledInstance(20, 25, java.awt.Image.SCALE_SMOOTH);;
            icon = new ImageIcon(img);
        }

        button.setIcon(icon);
    }

    /**
     * preper the text tooltip for the dynamically created seat 
     * @param data the button information
     * @return the tooltip
     */
    private String setTextForToolTip(String data) {
        String[] values = data.split(":");

       String s = "";
       
        if (values.length == 5) {
        	
        	s= String.format("Seat Deatils:</b><br>Seat is located in <span><b>%s</b></span> Class Row number <span><b>%s</b></span> seat number <span><b>%s</b></span> ,"
        			+ "<br>The Ticket cost <span><b>%s</b></span>, The seat is reserved for customer :<span><b>%s</b></span></html>",
        			values[2],values[0],values[1],values[3],values[4]);
        	       
        
        } else {
        	
        	s= String.format("Seat Deatils:</b><br>Seat is located in <span><b>%s</b></span> Class Row number <span><b>%s</b></span> seat number <span><b>%s</b></span> ,"
        			+ "<br>The Ticket cost <span><b>%s</b></span>, the seat is not reserved</html>", values[2],values[0],values[1],values[3]);
        	
     
        
        }
        

        return s;
        
    }

    /**
     * get the text tooltip for the dynamically created seat
     * @param data the button information
     * @return the tooltip
     */
    private String getSaetDeatils(String data) {
        String[] values = data.split(":");
        StringBuilder b = new StringBuilder("<html><b>");

        b.append("Class: ").append(values[2]).append(" ")
                .append("Row: ").append(values[0]).append(" ")
                .append("Seat: ").append(values[1]).append("<br>").append("Ticket cost: ").append(values[3]).append("</b></html>");


        return b.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
