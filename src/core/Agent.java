package core;

import init.IFly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Constants;
import utils.Rating;

/**
 * Class Agent ~ represent the employee that responsible for order handling and
 * produce flight tickets
 * 
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class Agent extends Employee implements Rating, Serializable {
private static final long serialVersionUID = -5605268810734694871L;
	// region members
	/**
	 * All agent's orders
	 */
	private ArrayList<Order> orders;

	/**
	 * The branch where this agent work in
	 */
	private Branch workBranch;

	// end_region

	// region constructors
	/**
	 * Full constructor ~ use for initial all fields
	 * 
	 * @param employeeNumber
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param startWorkingDate
	 * @param password
	 * @param address
	 */
	public Agent(int employeeNumber, String firstName, String lastName,
			Date birthDate, Date startWorkingDate, String password,
			Address address) {
		super(employeeNumber, firstName, lastName, birthDate, startWorkingDate,
				password, address);
		this.orders = new ArrayList<Order>();
	}

	/**
	 * Partial constructor ~ use for key filed
	 * @param employeeNumber
	 */
	public Agent(int employeeNumber) {
		super(employeeNumber);
		this.orders = new ArrayList<Order>();
	}

	// end_region

	// region setter-getter
	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return Collections.unmodifiableList(orders);
	}

	/**
	 * @return the workBranch
	 */
	public Branch getWorkBranch() {
		return workBranch;
	}

	/**
	 * This method set a new branch to this agent. If necessary - remove this
	 * agent from his last branch
	 * 
	 * @param workBranch
	 *            the workBranch to set
	 */
	public void setWorkBranch(Branch workBranch) {
		// TO DO

		if (this.workBranch != null) {
			if (this.workBranch.removeAgent(this))
				this.workBranch = workBranch;
		} else
			this.workBranch = workBranch;
	}

	// end_region

	// region functions

	// TO DO - Add more methods
	/**
	 * The role of this method is to add a new order to the orders set
	 * 
	 * @param order the order to add
	 * @return true if this order had successfully added or false otherwise
	 */
	public boolean addOrder(Order order) {
		// TO DO - complete this method
		if (order != null && !orders.contains(order))
			return orders.add(order);
		return false;
	}// ~ END OF Method addOrder

	/**
	 * The role of this method is to remove a order from the orders set
	 * 
	 * @param order - the order to remove
	 * @return true if this order removed successfuly or false otherwise
	 */
	public boolean removeOrder(Order order) {
		// TO DO - complete this method
		if (order != null && orders.contains(order))
			return orders.remove(order);
		return false;
	}// ~ END OF Method removeOrder

	/**
	 * This method find all orders that handle by this Agent and have at list
	 * one flight that soars in this summer months <b> hint:<\b> use the method
	 * - getFlightDateAndTimeSource and compare the month to this summer months
	 * and the year to the same year of this summer months. This method is a
	 * tool for the implemented method - calcRating.
	 * 
	 * @return set of orders for flights soars in the last month
	 */
	@SuppressWarnings("deprecation")
	private Set<Order> getAllOrdersMadeInSummertMonths() {
		// TO DO - complete this method
		Date today = new Date();
		Set<Order> s = new HashSet<Order>();

		for (Order order : orders) {
			for (FlightOrder fo : order.getFlightOrders()) {
				if (fo.getFlight().getFlightDateAndTimeSource().getYear() == today
						.getYear()) {
						if(IFly.isFlightInSummer(fo.getFlight())){
							s.add(order);
							break;
						}
				}
			}
		}

		return s;

	}// ~ END OF Method getAllOrdersMadeInTheLastMonth

	/**
	 * This method calculate the number of assignments (flight order to handle)
	 * this agent need/needed to handle with this summer months.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int getNumberOfThisYearSummerAssignments() {
		// TO DO - complete this method
		int numberOfAssignments = 0;
		Date today = new Date();

		for (Order order : orders) {
			for (FlightOrder flightOrder : order.getFlightOrders()) {
				if (flightOrder.getFlight().getFlightDateAndTimeSource()
						.getYear() == today.getYear()) {
					int mon = flightOrder.getFlight()
							.getFlightDateAndTimeSource().getMonth() + 1;
					if (Constants.SUMMER_MONTHES[0] == mon
							|| Constants.SUMMER_MONTHES[1] == mon
							|| Constants.SUMMER_MONTHES[2] == mon) {
						numberOfAssignments++;
					}
				}
			}
		}

		return numberOfAssignments;
	}// END OF ~ getNumberOfSummerAssignments

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agent [" + super.toString() + ", orders=" + orders + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.Rating#getPrimaryKey()
	 */
	@Override
	public int getPrimaryKey() {
		// TO DO Auto-generated method stub
		return this.getEmployeeNumber();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utils.Rating#calcRating()
	 */
	@Override
	public double calcRating() {
		// TO DO Auto-generated method stub

		double orderSum = 0;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Set<Order> summerOrders = getAllOrdersMadeInSummertMonths();
		for (Order order : summerOrders) {
			if (!customers.contains(order.getResponsibleCustomer()))
				customers.add(order.getResponsibleCustomer());

			orderSum += order.getOrderCost();
		}

		if (summerOrders.size() > 0)
			return (orderSum / summerOrders.size()) * customers.size();

		return 0;
	}
	// end_region

}// ~ END OF Class Agent
