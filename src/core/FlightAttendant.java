package core;

import init.IFly;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**Class FlightAttendant ~ represent a Flight Attendant in the <b>Flight</b> object
 * 
 * @author Matan Tespay
 *
 */
public class FlightAttendant extends Employee implements Serializable{

	private static final long serialVersionUID = -2433611801969649219L;
	boolean isHerald;
	private Set<Flight> flights;

	
	public boolean isHerald() {
		return isHerald;
	}

	public void setHerald(boolean isHerald) {
		this.isHerald = isHerald;
	}

	public Set<Flight> getFlights() {
		return Collections.unmodifiableSet(flights);
	}


	public FlightAttendant(int employeeNumber, String firstName,
			String lastName, Date birthDate, Date startWorkingDate,
			String password, Address address, boolean isHerald) {
		super(employeeNumber, firstName, lastName, birthDate, startWorkingDate,
				password, address);
		this.isHerald = isHerald;
		this.flights = new HashSet<Flight>(); 

	}

	public FlightAttendant(int employeeNumber) {
		super(employeeNumber);
		this.flights = new HashSet<Flight>(); 
	}

	
	/**
	 * This method add a new flight to this FlightAttendant flights data base.
	 * hint - he cannot be in more than one flight at the same time
	 * 
	 * @param flight
	 * @return true if succeded adding flight , false otherwise
	 */
	public boolean addFlight(Flight flight) {
		if (flight != null && !flights.contains(flight)) {

			for (Flight f : flights) {
				//check whether the two date ranges overlap, if so he can't participate in the flight
				if (f.getFlightDateAndTimeSource().before(flight.getFlightDateAndTimeDestination()) &&
					f.getFlightDateAndTimeDestination().after(flight.getFlightDateAndTimeSource()))
					return false;
			}

			return flights.add(flight);
		}
		return false;
	}

	public boolean removeFlight(Flight flight) {
		if (flight != null && flights.contains(flight))
			return flights.remove(flight);
		return false;
	}

	/*
	 * This method calculate the number of assignments (flights to participate
	 * in) this FlightAttendant need/needed to participate in for this summer
	 * months.
	 * 
	 * @see core.Employee#getNumberOfThisYearSummerAssignments()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int getNumberOfThisYearSummerAssignments() {
		// TODO Auto-generated method stub
		int count = 0;
		Date today = new Date();
		for (Flight f : this.flights) {
			if(f.getFlightDateAndTimeSource().getYear() == today.getYear() && IFly.isFlightInSummer(f))
				count++;
			
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.Employee#toString()
	 */
	@Override
	public String toString() {
		return "FlightAttendant [" + super.toString() + ", " + "isHerald=" + isHerald + "]";
	}
	
	


}
