package core;

import init.IFly;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import utils.E_LicenseType;

/**Class Pilot ~ represent the Pilot in the <b>Flight</b> object
 * 
 * @author Matan Tespay
 *
 */
public class Pilot extends Employee implements Serializable {
private static final long serialVersionUID = -6711496480176555119L;
	// region members
	private Set<Flight> flights;
	private E_LicenseType licenseType;

	// end_region

	// region setter-getter
	/**
	 * @return the flights
	 */
	public Set<Flight> getFlights() {
		return Collections.unmodifiableSet(this.flights);
	}

	/**
	 * @return the License Type 
	 */
	public E_LicenseType getLicenseType() {
		return licenseType;
	}

	/** set License Type
	 * @param licenseType
	 */
	public void setLicenseType(E_LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	// end_region

	//region constructors 
		/**
		 * partial constructor - use to initiate key fields
		 * @param employeeNumber
		 */
		public Pilot(int employeeNumber) {
		super(employeeNumber);
	}

	/**
	 * Full constructor - use to initiate all fields
	 * @param employeeNumber
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param startWorkingDate
	 * @param password
	 * @param address
	 * @param licenseType
	 */
	public Pilot(int employeeNumber, String firstName, String lastName,
			Date birthDate, Date startWorkingDate, String password,
			Address address, E_LicenseType licenseType) {
		/*(int employeeNumber, String firstName, String lastName,
				Date birthDate, Date startWorkingDate, String password,
				Address address)*/
		super(employeeNumber, firstName, lastName, birthDate,
				startWorkingDate, password, address);
		this.licenseType = licenseType;
		this.flights = new HashSet<Flight>();
	}
	//end_region


	//region functions 
	/**
	 * This method add a new flight to this pilot flights data base. hint - he
	 * cannot be in more than one flight at the same time hint - he can
	 * participate in this flight only if his license type compatible with
	 * flight airplane
	 * 
	 * @param flight
	 * @return true if succeded adding flight , false otherwise
	 */
	public boolean addFlight(Flight flight) {

		boolean isFlightOverlap = false;
		if (flight != null && !flights.contains(flight)) {

			for (Flight f : flights) {
				//check whether the two date ranges overlap, if so he can't participate in the flight
				isFlightOverlap = IFly.isFlightsOverlap(f, flight);
				if(isFlightOverlap)
					return false;
				
				if (f.getFlightDateAndTimeSource().before(flight.getFlightDateAndTimeDestination()) &&
						f.getFlightDateAndTimeDestination().after(flight.getFlightDateAndTimeSource()))
						return false;
			}

			if (flight.getAirplane().getTotalNumberSeats() > this
					.getLicenseType().getMaxNumberOfPassangers())
				return false;

			if (this.flights.add(flight))
				return true;
		}
		return false;
	}

	/**
	 * This method remove flight from this pilot flights data base.
	 * 
	 * @param flight
	 * @return true if succeded removing  flight , false otherwise
	 */
	public boolean removeFlight(Flight flight) {
		if (flight != null && flights.contains(flight))
			if (this.flights.remove(flight))
				return true;
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getNumberOfThisYearSummerAssignments() {
		// TO DO Auto-generated method stub

		int assignments = 0;
		Date today = new Date();
		for (Flight f : this.flights) {
			if (f.getFlightDateAndTimeSource().getYear() == today.getYear()) {
				if(IFly.isFlightInSummer(f)){
					assignments++;
					//break;
				}				
			}
		}
		return assignments;
	}

	@Override
	public String toString() {
		return "Pilot [" + super.toString() + ", licenseType= " + licenseType
				+ "]";
	}
//end_region

}
