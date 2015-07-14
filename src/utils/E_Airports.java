package utils;

public enum E_Airports {
	
	AMS("Amsterdam","Holland"), 
	BRC("Barcelona","Spain"), TLV("Tel-aviv","Israel"), 
	MAD("Madrid","Spain"), LND("London","England"), MNC("Manchester","England"),
	HFA("Haifa","Israel"), SAO("Sao-Paulo","Brazil"),
	RIO ("Rio de janeiro","Brazil"), 
	KYV("Kyev","Russia");

	/**
	 * airport's city
	 */
	private final String city;

	/**
	 * airport's country
	 */
	private final String country;

	//-------------------------------------------------------------Constructor------------------------------------------------------------------
	E_Airports(String city, String country){
		this.city = city;
		this.country = country;
	}

	//-------------------------------------------------------------Methods----------------------------------------------------------------------
	public String getCity() { 
		return city; 
	}
	public String getCountry() { 
		return country; 
	}

}
