/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;
import init.MyTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.JTableHeader;

import utils.E_Airports;
import utils.HelperClass;
import core.Agent;
import core.Branch;
import core.Customer;
import core.Employee;
import core.Flight;
import core.FlightAttendant;
import core.Order;
import core.Pilot;

/**
 * The class represents the screen which enable users to use the system's
 * queries
 * 
 * @author Matan
 */
public class ViewQueries extends MyInternalFrame {

	MyTableModel model;
	ArrayList<Object[]> dataRows;

	String[] orderColumns = { "#", "Order #",
			"<html><center>Responsible<br>Customer</html>", "Cost",
			"<html><center># of<br>Flight orders</html>", "Is Paid" };
	String[] employeeColumns = { "#",
			"<html><center>Employee<br>Number</html>", "Job", "Name",
			"Birth Date", "<html><center>Start Working<br>Date</html>",
			"<html><center>License Type<br>Or Is Herald</html>" };

	String[] customerAgentColumns = { "#",
			"<html><center>Agent<br>Name</html>",
			"<html><center>Passport<br>Number</html>",
			"<html><center>Customer<br>Name</html>", "Birth Date", "Email",
			"Adress" };
	String[] customerBranchColumns = { "#", "Branch Name",
			"<html><center>Passport<br>Number</html>",
			"<html><center>Customer<br>Name</html>", "Birth Date", "Email",
			"Adress" };
	String[] agentsBranchColumns = { "#", "Branch #", "Branch Name", "Agent #",
			"<html><center>Agent<br>Name</html>", "Birth Date",
			"<html><center>start Working<br>Date</html>" };

	int flightNum;
	String CMD = "back";
	String[] flightColumns = { "#", "flight #", "Cost", "Source",
			"Destination", "Stops", "departure", "arrival" };
	String[] flightOccupancyColumns = { "#", "flight #", "Cost", "Source",
			"Destination", "Stops", "departure", "arrival", "Occupancy" };
	DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private static final int HEADER_HEIGHT = 32;

	/**
	 * Creates new form ViewQueries
	 * 
	 * @param title
	 * @param type
	 */
	public ViewQueries(String title, String type) {

		super(title, type);

		// initComponents();
		model = new MyTableModel(new String[] {});
		initControls();
		setActionPanel();
		finishInit();
		lblInfo.setVisible(true);

	}

	/**
	 * Creates new form ViewQueries
	 * 
	 */
	public ViewQueries(String title, String type, JInternalFrame parent,
			int number) {

		super(title, type);
		this.parent = parent;
		// initComponents();
		model = new MyTableModel(new String[] {});
		flightNum = number;
		initControls();
		setActionPanel();
		finishInit();
		lblInfo.setVisible(true);

	}

	/**
	 * add a {@link core.Flight} to the table model {@link #dataRows}
	 * 
	 * @param flight
	 *            flight number
	 * @param idx
	 *            index flight in model
	 */
	private void addFlightToModel(Flight flight, int idx) {

		if (flight == null) {
			return;
		}

		Object[] row = {
				idx,
				flight.getFlightNumber(),
				flight.getFlightCost(),
				flight.getSource().getCity(),
				flight.getDestination().getCity(),
				flight.getStops().size(),
				outputFormatter.format(flight.getFlightDateAndTimeSource()),
				outputFormatter
						.format(flight.getFlightDateAndTimeDestination()), };
		dataRows.add(row);
	}

	/**
	 * fill with {@link core.Employee} the table model {@link #dataRows}
	 * 
	 */
	private void fillEmployeeOfTheMonth() {
		Date d = jDateMonth.getDate();
		if (d == null) {
			lblErr.setText("Date is missing");
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
			return;
		}
		dataRows = new ArrayList<>();
		int idx = 0;
		Employee employeeOfTheMonth = MainClass.getIfly().employeeOfTheMonth(d);
		if (employeeOfTheMonth != null) {

			addEmpToModel(employeeOfTheMonth, ++idx);

			model = new MyTableModel(employeeColumns, dataRows);
			tblData.setModel(model);
			lblErr.setVisible(false);

			customizeTable();

		} else {
			lblErr.setText("No employees to show");
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
		}
	}

	/**
	 * fill with {@link core.Flight} the table model {@link #dataRows} on
	 * TopXFlight query
	 * 
	 */
	private void fillTopXtable() {
		dataRows = new ArrayList<>();

		int number = (int) jSpinner1.getValue();
		model = new MyTableModel(flightColumns);
		tblData.setModel(model);
		List<Flight> theTopXPopularFlights = MainClass.getIfly()
				.getTheTopXPopularFlights(number);

		lblErr.setText("No flights to show");

		if (theTopXPopularFlights.isEmpty()) {
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
			return;
		} else if (theTopXPopularFlights.get(0) == null) {
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
			return;
		}

		int idx = 0;
		for (Flight flight : theTopXPopularFlights) {
			addFlightToModel(flight, ++idx);
		}

		model = new MyTableModel(flightColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Flight} the table model {@link #dataRows} on
	 * flightsByOccupancy query

	 */
	private void flightsByOccupancy() {
		model = new MyTableModel(flightOccupancyColumns);
		tblData.setModel(model);
		dataRows = new ArrayList<>();

		PriorityQueue<Map.Entry<Flight, Double>> flights = MainClass.getIfly()
				.getFlightsSortedByOccupancy();

		if (flights == null || flights.isEmpty()) {
			lblErr.setText("No flights to show");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.BLACK);
			return;
		}

		int idx = 0;
		for (Map.Entry<Flight, Double> entry : flights) {
			Flight flight = entry.getKey();

			Object[] row = {
					++idx,
					flight.getFlightNumber(),
					flight.getFlightCost(),
					flight.getSource().getCity(),
					flight.getDestination().getCity(),
					flight.getStops().size(),
					outputFormatter.format(flight.getFlightDateAndTimeSource()),
					outputFormatter.format(flight
							.getFlightDateAndTimeDestination()),
					String.format("%.3f", entry.getValue()) };
			dataRows.add(row);

		}

		model = new MyTableModel(flightOccupancyColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Flight} the table model {@link #dataRows} on
	 * fillSummerFlightsByLocation query
	 * 

	 */
	private void fillSummerFlightsByLocation() {
		if (cbLocation.getModel().getSize() <= 0) {
			lblErr.setText("Location is missing");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.red);
			return;
		} else {
			lblErr.setText("");
			lblErr.setVisible(false);
		}
		E_Airports airports = E_Airports.valueOf(((ComboItem) cbLocation
				.getSelectedItem()).getValue());
		model = new MyTableModel(flightColumns);
		tblData.setModel(model);
		dataRows = new ArrayList<>();
		if (airports != null) {

			int idx = 0;
			ArrayList<Flight> flights = MainClass.getIfly()
					.getAllThisSummerFlightsByLocation(airports);
			if (!flights.isEmpty()) {

				for (Flight flight : flights) {
					addFlightToModel(flight, ++idx);
				}
				model = new MyTableModel(flightColumns, dataRows);
				tblData.setModel(model);
			} else {
				lblErr.setText("No flights to show");
				lblErr.setVisible(true);
				lblErr.setForeground(Color.BLACK);
			}

		}

	}

	/**
	 * fill with {@link core.Flight} the table model {@link #dataRows} on
	 * fillBestFlight query
	 * 

	 */
	private void fillBestFlight() {
		if (cbFligthSource.getModel().getSize() <= 0) {
			lblErr.setText("Source flight is missing");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.BLACK);
			return;
		} else {
			lblErr.setText("");
			lblErr.setVisible(false);
		}

		model = new MyTableModel(flightColumns);
		tblData.setModel(model);
		dataRows = new ArrayList<>();
		int vicationDays = (int) jSpinner1.getValue();
		int flightNum = Integer.parseInt(((ComboItem) cbFligthSource
				.getSelectedItem()).getValue());
		List<Flight> findTheBestFlightBack = MainClass.getIfly()
				.findTheBestFlightBack(flightNum, vicationDays);
		if (findTheBestFlightBack.isEmpty()) {
			lblErr.setText("No flights to show");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.BLACK);
			tblData.setModel(model);
			return;

		}
		int idx = 0;
		for (Flight flight : findTheBestFlightBack) {
			addFlightToModel(flight, ++idx);
		}
		model = new MyTableModel(flightColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Employee} the table model {@link #dataRows}
	 * 
	 */
	/**
	 * @param emp the employee to add
	 * @param idx
	 */
	private void addEmpToModel(Employee emp, int idx) {

		if (emp == null) {
			return;
		}
		if (emp instanceof Pilot) {
			Pilot p = (Pilot) emp;

			Object[] row = {
					idx,
					p.getEmployeeNumber(),
					"Pilot",
					HelperClass.setName(new String[] { p.getFirstName(),
							p.getLastName() }),
					outputFormatter.format(p.getBirthDate()),
					outputFormatter.format(p.getStartWorkingDate()),
					p.getLicenseType().name(), };
			dataRows.add(row);
		} else if (emp instanceof FlightAttendant) {

			FlightAttendant fa = (FlightAttendant) emp;
			Object[] row = {
					idx,
					fa.getEmployeeNumber(),
					"<html><center>Flight<br>Attendant</html>",
					HelperClass.setName(new String[] { fa.getFirstName(),
							fa.getLastName() }),
					outputFormatter.format(fa.getBirthDate()),
					outputFormatter.format(fa.getStartWorkingDate()),
					fa.isHerald() == true ? "Yes" : "No" };
			dataRows.add(row);
		} else if (emp instanceof Agent) {
			Agent a = (Agent) emp;
			Object[] row = {
					idx,
					a.getEmployeeNumber(),
					"Agent",
					HelperClass.setName(new String[] { a.getFirstName(),
							a.getLastName() }),
					outputFormatter.format(a.getBirthDate()),
					outputFormatter.format(a.getStartWorkingDate()), "---", };
			dataRows.add(row);
		}
	}

	/**
	 * fill with {@link core.Agent} the table model {@link #dataRows} on
	 * SuperAgents query
	 * 
	 */
	private void fillSuperAgents() {

		List<Employee> allSuperAgents = MainClass.getIfly().getAllSuperAgents();
		dataRows = new ArrayList<>();
		model = new MyTableModel(agentsBranchColumns);
		tblData.setModel(model);

		int idx = 0;
		if (allSuperAgents.isEmpty()) {
			lblErr.setText("No Agents to show");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.BLACK);
			tblData.setModel(model);
			return;
		} else {
			for (Employee emp : allSuperAgents) {

				if (emp == null) {
					continue;
				}

				addAgentToModel(emp, ++idx);
			}
		}

		model = new MyTableModel(employeeColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Agent} the table model {@link #dataRows} on
	 * addAgentToModel query
	 * 
	 * @param emp employee to add
	 * @param idx
	 */
	private void addAgentToModel(Employee emp, int idx) {

		if (emp instanceof Agent) {
			Agent a = (Agent) emp;
			String[] values = { a.getFirstName(), a.getLastName() };
			Object[] row = { idx, a.getWorkBranch().getbranchNumber(),
					a.getWorkBranch().getbranchName(), a.getEmployeeNumber(),
					HelperClass.setName(values),
					outputFormatter.format(a.getBirthDate()),
					outputFormatter.format(a.getStartWorkingDate()) };

			dataRows.add(row);
		}

	}

	/**
	 * fill with {@link core.Employee} the table model {@link #dataRows} on
	 * SummerEmployeesBySeniority query
	 * 
	 */
	private void fillSummerEmployeesBySeniority() {

		List<Employee> allSuperAgents = MainClass.getIfly()
				.getAllThisSummerWorkEmployeesSortedBySeniority();
		dataRows = new ArrayList<>();
		int idx = 0;
		model = new MyTableModel(employeeColumns);
		tblData.setModel(model);
		if (allSuperAgents.isEmpty()) {
			lblErr.setText("No employees to show");
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
			return;
		}

		for (Employee emp : allSuperAgents) {

			if (emp == null) {
				continue;
			}

			addEmpToModel(emp, ++idx);
		}

		model = new MyTableModel(employeeColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Agent} the table model {@link #dataRows} on
	 * BranchesAgentsByRating query
	 * 
	 */
	private void fillBranchesAgentsByRating() {

		dataRows = new ArrayList<>();
		model = new MyTableModel(agentsBranchColumns);
		tblData.setModel(model);

		int idx = 0;
		Map<Branch, ArrayList<Agent>> agents = MainClass.getIfly()
				.getBranchesAgentsSortedByRating();
		if (agents.isEmpty()) {
			lblErr.setText("No Agent to show");
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
			return;
		}

		for (Map.Entry<Branch, ArrayList<Agent>> entry : agents.entrySet()) {

			ArrayList<Agent> arrayList = entry.getValue();
			for (Agent agent : arrayList) {
				addAgentToModel(agent, ++idx);
			}
		}

		model = new MyTableModel(agentsBranchColumns, dataRows);
		tblData.setModel(model);

	}

	/**
	 * add a {@link core.Order} to the table model {@link #dataRows}
	 * 
	 * @param order
	 * @param idx
	 */
	private void addOrderToModel(Order order, int idx) {

		Object[] row = {
				idx,
				order.getOrderNumber(),
				HelperClass.setName(new String[] {
						order.getResponsibleCustomer().getFirstName(),
						order.getResponsibleCustomer().getLastName() }),
				order.getOrderCost(), order.getFlightOrders().size(),
				order.isPaid() == true ? "Yes" : "No" };
		dataRows.add(row);
	}

	/**
	 * fill with {@link core.Order} the table model {@link #dataRows} on
	 * ProfitableOrder query
	 * 
	 */
	private void fillProfitableOrder() {
		dataRows = new ArrayList<>();
		model = new MyTableModel(orderColumns);
		tblData.setModel(model);

		int idx = 0;
		Order order = MainClass.getIfly().getTheMostProfitableOrder();

		if (order == null) {
			lblErr.setText("No orders to show");
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
			return;
		}

		addOrderToModel(order, ++idx);

		model = new MyTableModel(orderColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Order} the table model {@link #dataRows} on
	 * OrdersForProfitableCustomer query

	 */
	private void fillOrdersForProfitableCustomer() {
		dataRows = new ArrayList<>();
		model = new MyTableModel(orderColumns);
		tblData.setModel(model);

		int idx = 0;
		List<Order> orders = MainClass.getIfly()
				.getAllOrdersOfMostProfitableCustomer();

		if (orders == null || orders.isEmpty()) {
			lblErr.setText("No orders to show");
			lblErr.setForeground(Color.BLACK);
			lblErr.setVisible(true);
			return;
		}

		for (Order order : orders) {
			addOrderToModel(order, ++idx);
		}

		model = new MyTableModel(orderColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Flight} the table model {@link #dataRows} on
	 * SummerFlightsByStops query

	 */
	private void fillSummerFlightsByStops() {
		model = new MyTableModel(flightColumns);
		tblData.setModel(model);
		dataRows = new ArrayList<>();
		TreeSet<Flight> flights = MainClass.getIfly()
				.getAllSummerFlightsSortedByNumberOfStops();

		if (!flights.isEmpty()) {
			int idx = 0;

			for (Flight flight : flights) {
				addFlightToModel(flight, ++idx);
			}

		} else {
			lblErr.setText("No flight to show");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.BLACK);
		}

		model = new MyTableModel(flightColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * add a {@link core.Customer} the table model {@link #dataRows}

	 * @param c
	 * @param i
	 * @param owner
	 */
	private void addCustomerToModel(Customer c, int i, Object owner) {

		String[] value = null;

		if (owner instanceof Branch) {
			value = new String[] { ((Branch) owner).getbranchName() };
		} else if (owner instanceof Agent) {
			value = new String[] { ((Agent) owner).getFirstName(),
					((Agent) owner).getLastName() };
		}

		String[] name = { c.getFirstName(), c.getLastName() };
		String[] address = {
				c.getCustomerAddress().getCountry() + " ,"
						+ c.getCustomerAddress().getCity().name(),
				c.getCustomerAddress().getStreet() + " "
						+ c.getCustomerAddress().getHouseNumber() };
		Object[] row = { i, HelperClass.setName(value), c.getPassportNumber(),
				HelperClass.setName(name),
				outputFormatter.format(c.getBirthDate()),
				c.getEmail().toString().substring(7),
				HelperClass.setName(address) };

		dataRows.add(row);
	}

	/**
	 * fill with {@link core.Customer} the table model {@link #dataRows} on
	 * potentialCustomersForBranch query
	 * 
	 */
	private void fillpotentialCustomersForBranch() {
		model = new MyTableModel(customerBranchColumns);
		tblData.setModel(model);
		dataRows = new ArrayList<>();
		Map<Branch, List<Customer>> customers = MainClass.getIfly()
				.getPotentialCustomersForBranch();

		if (customers.isEmpty()) {
			lblErr.setText("No customers to show");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.BLACK);
			return;
		}

		int idx = 0;
		for (Map.Entry<Branch, List<Customer>> entry : customers.entrySet()) {
			Branch b = entry.getKey();
			List<Customer> list = entry.getValue();
			for (Customer customer : list) {
				addCustomerToModel(customer, ++idx, b);
			}
		}

		model = new MyTableModel(customerBranchColumns, dataRows);
		tblData.setModel(model);
	}

	/**
	 * fill with {@link core.Customer} the table model {@link #dataRows} on
	 * potentialCustomersForAgents query
	 * 
	 */
	private void fillpotentialCustomersForAgents() {

		model = new MyTableModel(customerAgentColumns);
		tblData.setModel(model);
		dataRows = new ArrayList<>();
		Map<Agent, List<Customer>> customers = MainClass.getIfly()
				.getPotentialCustomersForAgents();

		if (customers.isEmpty()) {
			lblErr.setText("No customers to show");
			lblErr.setVisible(true);
			lblErr.setForeground(Color.BLACK);
			return;
		}

		int idx = 0;
		for (Map.Entry<Agent, List<Customer>> entry : customers.entrySet()) {
			Agent a = entry.getKey();
			List<Customer> list = entry.getValue();
			for (Customer customer : list) {
				addCustomerToModel(customer, ++idx, a);
			}
		}

		model = new MyTableModel(customerAgentColumns, dataRows);
		tblData.setModel(model);
	}

	private void btnOkActionPerformed(ActionEvent evt) {

		switch (title) {
		case "Get The Top X Popular Flights":
			fillTopXtable();
			break;
		case "Employee Of The Month":
			fillEmployeeOfTheMonth();
			break;
		case "Get All This Summer Flights By Location":
			fillSummerFlightsByLocation();
			break;
		case "Find The Best Flight Back":
			fillBestFlight();
			break;
		case "Get All Super Agents":
			fillSuperAgents();
			break;
		case "Get All This Summer Work Employees Sorted By Seniority":
			fillSummerEmployeesBySeniority();
			break;
		case "Get Branches Agents Sorted By Rating":
			fillBranchesAgentsByRating();
			break;
		case "Get The Most Profitable Order":
			fillProfitableOrder();
			break;
		case "Get All Orders Of Most Profitable Customer":
			fillOrdersForProfitableCustomer();
			break;
		case "Get All Summer Flights Sorted By Number Of Stops":
			fillSummerFlightsByStops();
			break;
		case "Get Potential Customers For Branch":
			fillpotentialCustomersForBranch();
			break;
		case "Get Potential Customers For Agents":
			fillpotentialCustomersForAgents();
			break;
		case "Get Flights Sorted By Occupancy":
			flightsByOccupancy();
			break;
		default:

		}

		customizeTable();
	}

	/**
	 * customize the style of the table
	 */
	private void customizeTable() {
		tblData.setRowHeight(HEADER_HEIGHT);
		tblData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (tblData.getModel().getRowCount() <= 0) {
			return;
		}
		HelperClass.aligmentCell(tblData);
		HelperClass.setCellWidth(tblData);
	}

	/**
	 * customize the style of the table
	 */
	private void fillLocations() {
		HelperClass.fillAirportsCombo(this, cbLocation, null);

	}

    /**
     * 
     * fill {@link #cbFligthSource}}
     */
	private void fillFlightCombo() {

		HelperClass.fillFlightsCombo(this, cbFligthSource, false);

		if (flightNum > 0) {
			cbFligthSource.setActionCommand("ignore");
			ComboItem item = HelperClass
					.getItemIndex(cbFligthSource, flightNum);
			cbFligthSource.setSelectedItem(item);
			cbFligthSource.setActionCommand(null);

		}

		setFlightInfo();
	}

    /**
     * 
     * set infromation for the selectd flight in  {@link #cbFligthSource}}
     */
	private void setFlightInfo() {
		if (cbFligthSource.getModel().getSize() <= 0) {
			return;
		}

		int fNum = Integer.parseInt(((ComboItem) cbFligthSource
				.getSelectedItem()).getValue());
		Flight f = MainClass.getIfly().getFlights().get(fNum);
		String d = this.outputFormatter.format(f
				.getFlightDateAndTimeDestination());
		String s = this.outputFormatter.format(f.getFlightDateAndTimeSource());
		StringBuilder sb = new StringBuilder("<html>Flight Details:<br>");
		sb.append("Source: ").append(f.getSource().getCity())
				.append("  Destination: ").append(f.getDestination().getCity())
				.append("<br>departure: ").append(s).append("  arrival: ")
				.append(d).append("<html>");

		lblInfo.setText(sb.toString());
		lblInfo.setVisible(true);
	}

	private void initControls() {
		actionPanel = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jSpinner1 = new javax.swing.JSpinner();
		SpinnerModel splinerModel = new SpinnerNumberModel(1, // initial value
				1, // min
				100, // max
				1);
		jSpinner1.setModel(splinerModel);
		tblScroll = new javax.swing.JScrollPane();
		tblData = new javax.swing.JTable();
		jLabel1.setText("Select number of flight:");
		// /---
		jLabel2 = new javax.swing.JLabel();
		jDateMonth = new com.toedter.calendar.JDateChooser();
		jDateMonth.setDateFormatString("dd/MM/yyyy");
		jLabel2.setText("Select date:");
		// --
		jLabel3 = new javax.swing.JLabel();
		cbLocation = new javax.swing.JComboBox();
		jLabel3.setText("Select airport:");
		// --
		jLabel4 = new javax.swing.JLabel();
		cbFligthSource = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		txtVDays = new javax.swing.JTextField();
		jLabel4.setText("Select Flight:");
		jLabel5.setText("Vication days:");

		lblInfo = new javax.swing.JLabel();

		lblErr = new javax.swing.JLabel();
		lblErr.setText("No Data To Show");
		lblErr.setVisible(false);
		btnOk = new javax.swing.JButton();
		btnOk.setText("Submit");
		btnOk.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOkActionPerformed(evt);
			}
		});

	}

	
	/**
	 * set the window visuality for the query TopXPopularFlights
	 */
	private void setTopXPopularFlights() {
		javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(
				actionPanel);
		actionPanel.setLayout(actionPanelLayout);
		actionPanelLayout.setHorizontalGroup(actionPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						actionPanelLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addGap(18, 18, 18)
								.addComponent(jSpinner1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										51,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		actionPanelLayout
				.setVerticalGroup(actionPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								actionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												actionPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																jSpinner1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(29, Short.MAX_VALUE)));
	}
	
	/**
	 * set the window visuality for the query EmployeeOfMonth
	 */

	private void setEmployeeOfMonth() {

		javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(
				actionPanel);
		actionPanel.setLayout(actionPanelLayout);
		actionPanelLayout.setHorizontalGroup(actionPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						actionPanelLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel2)
								.addGap(18, 18, 18)
								.addComponent(jDateMonth,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										115,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		actionPanelLayout
				.setVerticalGroup(actionPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								actionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												actionPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jDateMonth,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel2))
										.addContainerGap(29, Short.MAX_VALUE)));
	}

	/**
	 * set the window visuality for the query SummerFlightsByLocation
	 */
	private void setSummerFlightsByLocation() {
		javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(
				actionPanel);
		actionPanel.setLayout(actionPanelLayout);
		actionPanelLayout.setHorizontalGroup(actionPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						actionPanelLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel3)
								.addGap(18, 18, 18)
								.addComponent(cbLocation,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										115,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		actionPanelLayout
				.setVerticalGroup(actionPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								actionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												actionPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																cbLocation,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(29, Short.MAX_VALUE)));
	}

	/**
	 * set the window visuality for the query BestFlightBack
	 */
	private void setBestFlightBack() {
		javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(
				actionPanel);
		actionPanel.setLayout(actionPanelLayout);

		cbFligthSource.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbFligthSourceActionPerformed(evt);
			}
		});
		actionPanel.setLayout(actionPanelLayout);
		actionPanelLayout
				.setHorizontalGroup(actionPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								actionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												actionPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																lblInfo,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																actionPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				cbFligthSource,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				115,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel5)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jSpinner1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				50,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		actionPanelLayout
				.setVerticalGroup(actionPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								actionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												actionPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																cbFligthSource,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5)
														.addComponent(
																jSpinner1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												lblInfo,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												18, Short.MAX_VALUE)
										.addContainerGap()));

	}

	
	/**
	 * preper the window for the select query
	 */
	private void setActionPanel() {
		switch (title) {
		case "Get The Top X Popular Flights":
			setTopXPopularFlights();

			break;
		case "Employee Of The Month":

			setEmployeeOfMonth();
			break;
		case "Get All This Summer Flights By Location":

			setSummerFlightsByLocation();
			fillLocations();

			break;
		case "Find The Best Flight Back":
			fillFlightCombo();
			setBestFlightBack();

			break;
		default:

		}
	}

	/**
	 * finish bulding the window
	 */
	private void finishInit() {
		tblData.setModel(new MyTableModel());
		tblData.setFillsViewportHeight(true);
		tblScroll.setViewportView(tblData);

		tblData.setTableHeader(new JTableHeader(tblData.getColumnModel()) {
			@Override
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = HEADER_HEIGHT;
				return d;
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														actionPanel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						tblScroll,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						590,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										lblErr,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										214,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										btnOk)))
																.addGap(0,
																		10,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(actionPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(31, 31, 31)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(lblErr)
												.addComponent(btnOk))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(tblScroll,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										241,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		actionPanel = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		cbFligthSource = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		txtVDays = new javax.swing.JTextField();
		tblScroll = new javax.swing.JScrollPane();
		tblData = new javax.swing.JTable();
		lblErr = new javax.swing.JLabel();
		btnOk = new javax.swing.JButton();

		jLabel4.setText("Select Flight:");

		cbFligthSource.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cbFligthSourceActionPerformed(evt);
			}
		});

		jLabel5.setText("Vication days:");

		javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(
				actionPanel);
		actionPanel.setLayout(actionPanelLayout);
		actionPanelLayout.setHorizontalGroup(actionPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						actionPanelLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel4)
								.addGap(18, 18, 18)
								.addComponent(cbFligthSource,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										115,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(jLabel5)
								.addGap(18, 18, 18)
								.addComponent(txtVDays,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		actionPanelLayout
				.setVerticalGroup(actionPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								actionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												actionPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																cbFligthSource,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5)
														.addComponent(
																txtVDays,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(40, Short.MAX_VALUE)));

		tblData.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { {}, {}, {}, {} }, new String[] {

				}));
		tblData.setFillsViewportHeight(true);
		tblScroll.setViewportView(tblData);

		lblErr.setText("No Data To Show");

		btnOk.setText("Submit");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														actionPanel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						tblScroll,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						590,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										lblErr,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										214,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										btnOk)))
																.addGap(0,
																		10,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(actionPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(lblErr)
												.addComponent(btnOk))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(tblScroll,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										241,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(29, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void cbFligthSourceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbFligthSourceActionPerformed
		// TODO add your handling code here:
		JComboBox box = (JComboBox) evt.getSource();
		if (box.getActionCommand() != null
				&& box.getActionCommand().equals(CMD)) {
			return;
		}

		setFlightInfo();
	}// GEN-LAST:event_cbFligthSourceActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel actionPanel;
	private javax.swing.JButton btnOk;
	private javax.swing.JComboBox cbFligthSource;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel lblErr;
	private javax.swing.JTable tblData;
	private javax.swing.JScrollPane tblScroll;
	private javax.swing.JTextField txtVDays;
	// End of variables declaration//GEN-END:variables

	private javax.swing.JLabel jLabel1;
	private javax.swing.JSpinner jSpinner1;
	private com.toedter.calendar.JDateChooser jDateMonth;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JComboBox cbLocation;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel lblInfo;

}
