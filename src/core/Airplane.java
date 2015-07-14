package core;

import java.io.Serializable;

/**Class Airplane ~ represent the Airplane in the <b>Flight</b> object
 * @author Matan Tespay
 *
 */
public class Airplane implements Serializable{
private static final long serialVersionUID = -5763783245918097347L;
	//region members
		private int airplaneNumber;
	private int numberOfSeatsBusinessClass;
	private int numberOfSeatsFirstClass;
	private int numberOfSeatsTouristsClass;
	//end_region

	//region setters getters
		/**
		 * @return the airplane number
		 */
		public int getAirplaneNumber() {
		return airplaneNumber;
	}

	/** 
	 * set the airplane number
	 * @param airplaneNumber
	 */
	public void setAirplaneNumber(int airplaneNumber) {
		this.airplaneNumber = airplaneNumber;
	}

	/**
	 * @return the Number Of Seats Business Class 
	 */
	public int getNumberOfSeatsBusinessClass() {
		return numberOfSeatsBusinessClass;
	}

	/**
	 * set Number Of Seats Business Class
	 * @param numberOfSeatsBusinessClass
	 */
	public void setNumberOfSeatsBusinessClass(int numberOfSeatsBusinessClass) {
		this.numberOfSeatsBusinessClass = numberOfSeatsBusinessClass;
	}

	/**
	 * @return Number Of Seats First Class
	 */
	public int getNumberOfSeatsFirstClass() {
		return numberOfSeatsFirstClass;
	}

	/** set number Of Seats First Class
	 * @param numberOfSeatsFirstClass
	 */
	public void setNumberOfSeatsFirstClass(int numberOfSeatsFirstClass) {
		this.numberOfSeatsFirstClass = numberOfSeatsFirstClass;
	}

	/**
	 * @return Number Of Seats Tourists Class
	 */
	public int getNumberOfSeatsTouristsClass() {
		return numberOfSeatsTouristsClass;
	}

	/**
	 * set number Of Seats Tourists Class
	 * @param numberOfSeatsTouristsClass
	 */
	public void setNumberOfSeatsTouristsClass(int numberOfSeatsTouristsClass) {
		this.numberOfSeatsTouristsClass = numberOfSeatsTouristsClass;
	}
	/**
	 * this method return the total number of seats in this airplane
	 * 
	 * @return Total Number Seats
	 */
	public int getTotalNumberSeats() {
		return this.numberOfSeatsBusinessClass + this.numberOfSeatsFirstClass
				+ this.numberOfSeatsTouristsClass;
	}
	//end_region

	//region constructors
		/**
		 * partial constructor - use for initiate key filed
		 * @param airplaneNumber
		 */
		public Airplane(int airplaneNumber) {
		this.airplaneNumber = airplaneNumber;
	}

	/**
	 * full constructor - used for initiate all fields 
	 * @param airplaneNumber
	 * @param numberOfSeatsBusinessClass
	 * @param numberOfSeatsFirstClass
	 * @param numberOfSeatsTouristsClass
	 */
	public Airplane(int airplaneNumber, int numberOfSeatsBusinessClass,
			int numberOfSeatsFirstClass, int numberOfSeatsTouristsClass) {

		this.airplaneNumber = airplaneNumber;
		this.numberOfSeatsBusinessClass = numberOfSeatsBusinessClass;
		this.numberOfSeatsFirstClass = numberOfSeatsFirstClass;
		this.numberOfSeatsTouristsClass = numberOfSeatsTouristsClass;
	}
	//end_region

	//region functions
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + airplaneNumber;
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
		Airplane other = (Airplane) obj;
		if (airplaneNumber != other.airplaneNumber)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Airplane [airplaneNumber=" + airplaneNumber
				+ ", numberOfSeatsBusinessClass=" + numberOfSeatsBusinessClass
				+ ", numberOfSeatsFirstClass=" + numberOfSeatsFirstClass
				+ ", numberOfSeatsTouristsClass=" + numberOfSeatsTouristsClass
				+ "]";
	}

//end_region

}
