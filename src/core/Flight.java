package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.E_Airports;

/**
 * Class Flight ~ represent a single flight in thr system
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class Flight implements Comparable<Flight> , Serializable {
	
private static final long serialVersionUID = -1612146150508410863L;
	//region members 
	/**
	 * The flight number - the key
	 */
	private int flightNumber;
	/**
	 * The time when the flight soar from the source
	 */
	private Date flightDateAndTimeSource;
	/**
	 * The time when the flight land in the destination
	 */
	private Date flightDateAndTimeDestination;
	/**
	 * The cost for this flight
	 */
	private double flightCost;
	/**
	 * The pilots for this flight
	 */
	private ArrayList<Pilot> pilots;
	/**
	 * The FlightAttendants for this flight
	 */
	private Set<FlightAttendant> FlightAttendants;
	/**
	 * This flight airplane
	 */
	private Airplane airplane;
	/**
	 * The flight source airport
	 */
	private E_Airports source;
	/**
	 * The flight destination airport
	 */
	private E_Airports destination;
	/**
	 * collection of all stop in this Flight
	 */
	private Set<StepIn> stops;
	
	private ArrayList<FlightOrder> flightOrders;
//end_region


	//region constructors 
	/**
	 * full Constructor ~ use for initial all fields
	 * @param flightNumber
	 * @param flightDateAndTimeSource
	 * @param flightDateAndTimeDestination
	 * @param flightCost
	 */
	public Flight(int flightNumber, Date flightDateAndTimeSource,
			Date flightDateAndTimeDestination, E_Airports source, E_Airports destination, double flightCost, Airplane airplane) {
		super();
		this.flightNumber = flightNumber;
		this.flightDateAndTimeSource = flightDateAndTimeSource;
		this.flightDateAndTimeDestination = flightDateAndTimeDestination;
		this.flightCost = flightCost;
		this.FlightAttendants = new HashSet<FlightAttendant>();
		this.pilots = new ArrayList<Pilot>();
		this.stops= new HashSet<StepIn>();
		this.airplane = airplane;
		this.source = source;
		this.destination = destination;
		flightOrders = new ArrayList<FlightOrder>();
	}

	/**
	 * Partial Constructor ~ use for initial key fields
	 * 
	 * @param flightNumber
	 */
	public Flight(int flightNumber) {
		super();
		this.flightNumber = flightNumber;
	}
//end_region


	//region setter-getter 
	/**
	 * @return the flightNumber
	 */
	public int getFlightNumber() {
		return flightNumber;
	}

	public List<Pilot> getPilots() {
		return Collections.unmodifiableList(pilots);
	}

	/**
	 * @param flightNumber the flightNumber to set
	 */
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	/**
	 * @return the flightDateAndTimeSource
	 */
	public Date getFlightDateAndTimeSource() {
		return flightDateAndTimeSource;
	}

	/**
	 * @param flightDateAndTimeSource the flightDateAndTimeSource to set
	 */
	public void setFlightDateAndTimeSource(Date flightDateAndTimeSource) {
		this.flightDateAndTimeSource = flightDateAndTimeSource;
	}

	/**
	 * @return the flightDateAndTimeDestination
	 */
	public Date getFlightDateAndTimeDestination() {
		return flightDateAndTimeDestination;
	}

	/**
	 * @param flightDateAndTimeDestination the flightDateAndTimeDestination to set
	 */
	public void setFlightDateAndTimeDestination(Date flightDateAndTimeDestination) {
		this.flightDateAndTimeDestination = flightDateAndTimeDestination;
	}

	/**
	 * @return the source
	 */
	public E_Airports getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(E_Airports source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public E_Airports getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(E_Airports destination) {
		this.destination = destination;
	}

	/**
	 * @return the stops
	 */
	public Set<StepIn> getStops() {
		return Collections.unmodifiableSet(stops);
	}

	/**
	 * @return the flightCost
	 */
	public double getFlightCost() {
		return flightCost;
	}

	/**
	 * @param flightCost the flightCost to set
	 */
	public void setFlightCost(double flightCost) {
		this.flightCost = flightCost;
	}

	/**
	 * @return the FlightAttendants
	 */
	public Set<FlightAttendant> getFlightAttendants() {
		return Collections.unmodifiableSet(FlightAttendants);
	}
	
	/**
	 * @return the airplane
	 */
	public Airplane getAirplane() {
		return airplane;
	}

	/**
	 * @param airplane the airplane to set
	 */
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
	
	/**
	 * @return the flightOrders
	 */
	public List<FlightOrder> getFlightOrders() {
		return Collections.unmodifiableList(flightOrders);
	}
	
//end_region

	

	/**
	 * this method add new stop to this Flight IIF this step not exists already
	 * @param step
	 * @return true if FlightAttendant added successfully, false otherwise
	 */
	public boolean addStop(StepIn step)
	{
		if(step != null && !stops.contains(step))
			return stops.add(step);
		return false;
	}
	
	
	/**
	 * this method remove stop from this Flight IIF this step already exists
	 * @param step
	 * @return true if FlightAttendant removed successfully, false otherwise
	 */
	public boolean removeStop(StepIn step)
	{
		if(step != null && stops.contains(step))
			return stops.remove(step);
		return false;
	}
	
	/**
	 * this method add new FlightAttendant to this Flight IIF the FlightAttendant not exists in this flight already
	 * @param FlightAttendant
	 * @return true if FlightAttendant added successfully, false otherwise
	 */
	public boolean addFlightAttendant(FlightAttendant FlightAttendant)
	{
		if(FlightAttendant != null && !this.FlightAttendants.contains(FlightAttendant))
			return this.FlightAttendants.add(FlightAttendant);
		return false;
		
	}
	
	/**
	 * this method removes FlightAttendant from this flight
	 * @param FlightAttendant
	 * @return true if FlightAttendant removed successfully, false otherwise
	 */
	public boolean removeFlightAttendant(FlightAttendant FlightAttendant)
	{
		if(FlightAttendant != null && this.FlightAttendants.contains(FlightAttendant))
			return this.FlightAttendants.remove(FlightAttendant);
		return false;
		
	}

	/**
	 * This method add a new pilot to this flight.
	 * <b>Hint:<b> you need to check something important before add a pilot.
	 * @param pilot
	 * @return true if the pilot has been added, false otherwise
	 */
	public boolean addPilot(Pilot pilot){
		//TODO - Complete this method
		if(pilot != null && !this.pilots.contains(pilot) && this.pilots.size() < utils.Constants.MAX_NUMBER_OF_PILOTS){
			if(pilot.getFlights().contains(this) &&
					pilot.getLicenseType().getMaxNumberOfPassangers() >= this.airplane.getTotalNumberSeats())
			return this.pilots.add(pilot);
		}
		return false;
	}//END OF ~ addPilot
	
	/**
	 * * This method remove a given pilot from this flight.
	 * @param pilot
	 * @return true if the pilot has been removed, false otherwise
	 */
	public boolean removePilot(Pilot pilot){
		//TODO - Complete this method
		if(pilot != null && this.pilots.contains(pilot))
			return this.pilots.remove(pilot);
		
		return false;
	}//END OF ~ removePilot
	
	/**
	 * This method add a new flight order to this 
	 * flight if it isn't already exist.
	 * @param flightOrder
	 * @return true if the flight order has been added, false otherwise
	 */
	public boolean addFlightOrder(FlightOrder flightOrder){
		//TODO - Complete this method
		if(flightOrder != null && !flightOrders.contains(flightOrder))
			return flightOrders.add(flightOrder);
		return false;
	}//END OF ~ addFlightOrder
	
	/**
	 * This method 
	 * @param flightOrder
	 * @return true if the flight order has been removed, false otherwise
	 */
	public boolean removeFlightOrder(FlightOrder flightOrder){
		//TODO - Complete this method
		if(flightOrder != null && flightOrders.contains(flightOrder))
			return flightOrders.remove(flightOrder);
		return false;
	}//END OF ~ removeFlightOrder
	
	/**
	 * this function calculates the amount of tickets in the flight
	 * according to the flightOrders set
	 * @return int - tickets amount
	 */
	public double getFlightOccupancy(){
		
		double count = 0;
		
		for (FlightOrder fo : this.flightOrders) {
			count+=fo.getTickets().size();
		}
		
		return (count/this.airplane.getTotalNumberSeats());
	}
	//-------------------------------hashCode equals & toString------------------------------
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String theString = "========================Flight - " + flightNumber + " details============================\n";
		
		theString += "Source date: " + flightDateAndTimeSource + " Destination date: " + flightDateAndTimeDestination;
		
		theString += "\nFlight cost: " + flightCost + "\nFlight pilots:\n";
		
		for(Employee emp : pilots)
			theString += emp + "\n";
		
		theString += "Flight FlightAttendants:\n";
		
		for(Employee emp : FlightAttendants)
			theString += emp + "\n";
		
		theString += "Flight airplane: " + airplane + "\n";
		
		theString += "Flight source: " + source + "\nFlight destination: " + destination + "\nFlight stops:" + "\n";
		int i = 1;
		for(StepIn step : stops)
			theString += (i++) + " - " + step + "\n";
		
		theString += "Flight orders: \n";
		
		for(FlightOrder fo : flightOrders)
			theString += fo + "\n";

		return theString;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + flightNumber;
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
		Flight other = (Flight) obj;
		if (flightNumber != other.flightNumber)
			return false;
		return true;
	}

	/**
	 * this Compare method compares between the given flight stops size and the size of stops
	 * in this object 
	 */
	@Override
	public int compareTo(Flight o) {
		
		return this.stops.size()-o.stops.size();
	}

}// ~ END OF Class Flight
