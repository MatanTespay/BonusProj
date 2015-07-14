package core;

import java.io.Serializable;

import utils.E_Airports;

/**
 * Class StepIn ~ represent the stops of a single flight
 * from the beginning of the trip till the land 
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class StepIn implements Serializable {
private static final long serialVersionUID = 6017126656720221327L;
	//-------------------------------Class Members------------------------------
	/**
	 * The flight that stop in this step - part of the key
	 */
	private Flight flight;
	/**
	 * The airport that represent this step - part of the key
	 */
	private E_Airports airport;
	/**
	 * The step number
	 */
	private int stepNumber;


	//-------------------------------Constructors-----------------------------
	/**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param stepNumber
	 * @param flight
	 * @param airport
	 */
	public StepIn(int stepNumber, Flight flight,
			E_Airports airport) {
		super();
		this.stepNumber = stepNumber;
		this.flight = flight;
		this.airport = airport;
	}

	/**
	 * Partial Constructor ~ use for initial key fields
	 * 
	 * @param stepNumber
	 */
	public StepIn(int stepNumber) {
		super();
		this.stepNumber = stepNumber;
	}
	
	//-------------------------------Getters And Setters------------------------------
	/**
	 * @return the stepNumber
	 */
	public int getStepNumber() {
		return stepNumber;
	}

	/**
	 * @param stepNumber the stepNumber to set
	 */
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}




	/**
	 * @return the flight
	 */
	public Flight getFlight() {
		return flight;
	}

	/**
	 * @param flight the flight to set
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	/**
	 * @return the airport
	 */
	public E_Airports getAirport() {
		return airport;
	}

	/**
	 * @param airport the airport to set
	 */
	public void setAirport(E_Airports airport) {
		this.airport = airport;
	}
	

	//-------------------------------hashCode equals & toString------------------------------
	@Override
	public String toString() {
		return "StepIn [airport: " + airport+" "+airport.getCity()+", "+ airport.getCountry() + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airport == null) ? 0 : airport.hashCode());
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
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
		StepIn other = (StepIn) obj;
		if (airport == null) {
			if (other.airport != null)
				return false;
		} else if (!airport.equals(other.airport))
			return false;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		return true;
	}
	
}// ~ END OF Class StepIn
