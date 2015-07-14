package core;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import utils.Rating;
import core.FlightOrder.FlightTicket;

/**
 * Class Customer ~ represent the entity who responsible to order flights 
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class Customer implements Rating , Serializable{
	
	private static final long serialVersionUID = -3656438166821959561L;

    public static Iterable<Map.Entry<Integer, Employee>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }
	//region members 
	/**
	 * Customer's passport number - the key
	 */
	private int passportNumber;
	
	/**
	 * Customer's first name
	 */
	private String firstName;
	
	/**
	 * Customer's sure name
	 */
	private String lastName;
	
	/**
	 * Customer's date of birth
	 */
	private Date birthDate;
	
	/**
	 * Customer's password for enter the system
	 */
	private String password;
	
	/**
	 * Customer's email
	 */
	private URL email;
	
	/**
	 * Customer's address
	 */
	private Address customerAddress;
	
	/**
	 * Customer's orders - as a responsible customer
	 */
	private ArrayList<Order> orders;
	
	/**
	 * Customer's tickets
	 */
	private ArrayList<FlightTicket> flightTickets;
	
	/**
	 * This field represent the rating of this customer
	 * calculate by summing the cost of all orders he made ever
	 * After finishing calculate set the rating in this rating field
	 */
	private double rating;
	
	/**
	 * customer's balance 
	 */
	private double balance;
//end_region

	//region constructors 
	/**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param passportNumber
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param password
	 * @param email
	 */
	public Customer(int passportNumber, double balance, String firstName, String lastName,
			Date birthDate, String password, URL email, Address customerAddress) {

		super();
		this.passportNumber = passportNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.password = password;
		this.email = email;
		this.customerAddress = customerAddress;
		this.balance = balance;
		this.orders = new ArrayList<Order>();
		flightTickets = new ArrayList<>();
		
	}

	/**
	 * Partial Constructor ~ use for initial key fields
	 * 
	 * @param passportNumber
	 */
	public Customer(int passportNumber) {
		super();
		this.passportNumber = passportNumber;
	}
//end_region


	//region setter-getter 

	/**
	 * @return the passportNumber
	 */
	public int getPassportNumber() {
		return passportNumber;
	}

	/**
	 * @param passportNumber the passportNumber to set
	 */
	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public URL getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(URL email) {
		this.email = email;
	}

	/**
	 * @return the customerAddress
	 */
	public Address getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return Collections.unmodifiableList(orders);
	}
	
	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
	
	/**
	 * @return the flightTickets
	 */
	public List<FlightTicket> getFlightTickets() {
		return Collections.unmodifiableList(flightTickets);
	}

	/**
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}
//end_region

	/**
	 * The role of this method is to add a new
	 * order to the orders arrayList
	 * @param order
	 * @return true if the order added successfully or false otherwise
	 */
	public boolean addOrder(Order order){
		//TO DO - complete this method
		if(order != null && this.orders.contains(order)) return false;
		
		if(this.orders.add(order))
			return true;
		
		return false;
	}// ~ END OF Method addOrder
	
	/**
	 * The role of this method is to remove a 
	 * order from the orders arrayList
	 * @param order
	 * @return true if the order remove successfully or false otherwise
	 */
	public boolean removeOrder(Order order){
		//TO DO - complete this method
		if (order != null) {
			if(this.orders.contains(order)) 
				if(this.orders.remove(order))
					return true;
		}
		return false;
	}// ~ END OF Method removeOrder

	/**
	 * The role of this method is to add a new
	 * flight ticket to the FlightTickets arrayList
	 * @param flightTicket
	 * @return true if the ticket added successfully or false otherwise
	 */
	public boolean addFlightTicket(FlightTicket flightTicket){
		//TO DO - complete this method
		if (flightTicket == null) return false;

		if (!this.flightTickets.contains(flightTicket))
			if (this.flightTickets.add(flightTicket))
				return true;

		return false;
	}
		
	/**
	 * this method update the customer's balance IIF the customer have enough money
	 * @param amount
	 * @return true if , false otherwise 
	 */
	public boolean payOrder(double amount)
	{
		if(amount<=0 || this.getBalance()-amount<0)
			return false;
		else
		{
			this.balance-=amount;
			return true;
		}
	}// ~ END OF Method payOrder

	//-------------------------------hashCode equals & toString------------------------------
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [passportNumber=" + passportNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", password=" + password + ", email=" + email
				+ ", customerAddress=" + customerAddress + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + passportNumber;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (passportNumber != other.passportNumber)
			return false;
		return true;
	}

	@Override
	public int getPrimaryKey() {
		// TO DO Auto-generated method stub
		return this.getPassportNumber();
	}

	@Override
	public double calcRating() {
		// TO DO Auto-generated method stub
		double amount = 0;
		for (Order o : this.getOrders()) {
			amount+= o.getOrderCost();
		}
		
		if(this.getOrders().size() > 0)
			return (amount/this.getOrders().size());
		
		return 0;
	}

}// ~ END OF Class Customer
