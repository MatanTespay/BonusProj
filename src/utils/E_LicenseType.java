package utils;

/**
 * Class LicenseType ~ represent the pilots license types names use in the system
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public enum E_LicenseType {
	
	//-------------------------------------------------------------Values----------------------------------------------------------------
	Student_Pilot(100), Sport_Pilot(200), Private_Pilot(300), Commercial_Pilot(400), Airline_Transport_Pilot(500);
	
	//-------------------------------------------------------------Class Members----------------------------------------------------------------
	private final int maxNumberOfPassangers;
	
	//-------------------------------------------------------------Constructor------------------------------------------------------------------
	E_LicenseType(int maxNumberOfPassangers){
		this.maxNumberOfPassangers = maxNumberOfPassangers;
	}
	
	//-------------------------------------------------------------Methods----------------------------------------------------------------------
	public int getMaxNumberOfPassangers() { 
		return maxNumberOfPassangers; 
	}
	
}// ~ END OF Enum Class LicenseType
