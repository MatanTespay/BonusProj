package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.E_Cities;

/**
 * Class Address ~ represent the address used by some entities in the system
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class Address implements Serializable{
    private static final long serialVersionUID = -2821285164602576052L;
	//-------------------------------Class Members------------------------------
	/**
	 * The country
	 */
	private String country;
	/**
	 * The city
	 */
	private E_Cities city;
	/**
	 * The street
	 */
	private String street;
	/**
	 * The house number 
	 */
	private int houseNumber;
	/**
	 * The phone number
	 */
	private String[] phoneNumber;
	

	//-------------------------------Constructors-----------------------------
	/**
	 * Full constructor ~ use for initial all fields
	 * 
	 * @param country
	 * @param city
	 * @param street
	 * @param houseNumber
	 * @param phoneNumber
	 */
	public Address(String country, E_Cities city, String street, int houseNumber,
			String[] phoneNumber) {
		super();
		this.country = city.getCountry();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.phoneNumber = phoneNumber;
		
	}
	
	//-------------------------------Getters And Setters------------------------------
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public E_Cities getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(E_Cities city) {
		this.city = city;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the houseNumber
	 */
	public int getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber the housNumber to set
	 */
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * @return the phoneNumber
	 */
	public String[] getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String[] phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	//-------------------------------hashCode equals & toString------------------------------
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String phones = " {";
		
		for(String phone : phoneNumber)
			phones += phone;
		phones += "} ";
		return "Address [country=" + country + ", city=" + city + ", street="
				+ street + ", housNumber=" + houseNumber + ", phoneNumber="
				+ phones + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + houseNumber;
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (city != other.city)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (houseNumber != other.houseNumber)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	
}// ~ END OF Class Address
