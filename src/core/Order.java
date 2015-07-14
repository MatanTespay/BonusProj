/**
 * 
 */
package core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import utils.Constants;

/**Class Order ~ represents a single Order associated with a single Agent or customer
 * @author Matan Tespay
 *
 */
public class Order implements Serializable{
private static final long serialVersionUID = 1669428093697897375L;
	//region members
	private Agent agent;
	private Set<FlightOrder> flightOrders;
	boolean isPaid;
	private int orderNumber;
	private Customer responsibleCustomer;
	//end_region
	
	//region setter/getters
	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}


	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}


	/**
	 * @return the flightOrders
	 */
	public Set<FlightOrder> getFlightOrders() {
		return Collections.unmodifiableSet(flightOrders);
	}



	/**
	 * @return the isPaid
	 */
	public boolean isPaid() {
		return isPaid;
	}


	/**
	 * @param isPaid the isPaid to set
	 */
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}


	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
		return orderNumber;
	}


	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}


	/**
	 * @return the responsibleCustomer
	 */
	public Customer getResponsibleCustomer() {
		return responsibleCustomer;
	}


	/**
	 * @param responsibleCustomer the responsibleCustomer to set
	 */
	public void setResponsibleCustomer(Customer responsibleCustomer) {
		this.responsibleCustomer = responsibleCustomer;
	}
	
//end_region
	
	//region constructors
	/**
	 * Partial constructor - use for initiate key fields
	 * @param orderNumber
	 */
	public Order(int orderNumber) {
		// TODO Auto-generated constructor stub
		this.orderNumber = orderNumber;
	}
	
		/**
		 * full constructor - use for initiate all fields
		 * @param orderNumber
		 * @param isPaid
		 * @param responsibleCustomer
		 * @param flight
		 * @param agent
		 */
		public Order(int orderNumber, boolean isPaid, Customer responsibleCustomer,
			Flight flight, Agent agent) {
		this.agent = agent;
		this.isPaid = isPaid;
		this.orderNumber = orderNumber;
		this.responsibleCustomer = responsibleCustomer;
		this.flightOrders = new HashSet<FlightOrder>();
	}
	//end_region
	
	//region Functions
	/**
	 * @return the cost of the order
	 */
	public double getOrderCost() {
		double totalAmont = 0;
		for (FlightOrder flightOrder : getFlightOrders())
			if (flightOrder != null)
				totalAmont += flightOrder.getTotalAmount();
		return totalAmont + Constants.TAX * totalAmont;
	}
	
	/**
	 * This method add a new flight order to the flight orders array IFF the
	 * given flightOrder doesn't exists already .
	 * 
	 * @param flightOrder
	 *            - The filghtOrder to add
	 * @return - true if flight order added successfully or false otherwise
	 */
	public boolean addFlightOrder(FlightOrder flightOrder) {

		if (flightOrder != null && !this.flightOrders.contains(flightOrder)){
			if(this.flightOrders.add(flightOrder))
				return true;			
		}
		return false;
	}
	
	/**
	 * This method remove a given flightOrder from the flightOrders array IFF
	 * the flightOrder exists. 
	 * 
	 * @param flightOrder
	 *            - The filghtOrder to remove
	 * @return - true if flight order removed successfully or false otherwise
	 */
	public boolean removeFlightOrder(FlightOrder flightOrder) {
	
		if (flightOrder != null && this.flightOrders.contains(flightOrder)){
			if(this.flightOrders.remove(flightOrder))
				return true;			
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "FlightOrder [orderNumber=" + orderNumber + ", isPaid=" + isPaid
				 + ", responsibleCustomer=" + responsibleCustomer + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderNumber;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderNumber != other.orderNumber)
			return false;
		return true;
	}
//end_region

	
}
