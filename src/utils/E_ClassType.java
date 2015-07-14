package utils;

/**
 * Class ClassType ~ represent the Classes types of a single flight
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public enum E_ClassType {
	//-------------------------------------------------------------Values---------------------------------------------------------------------
	First(1.4), Business(1.2) , Tourists(1.0);
	
	//-------------------------------------------------------------Class Members----------------------------------------------------------------
	private final Double fee;
	
	//-------------------------------------------------------------Constructor------------------------------------------------------------------
	E_ClassType(double fee){
		this.fee = fee;
	}
	
	//-------------------------------------------------------------Methods----------------------------------------------------------------------
	public double getFee() { 
		return fee; 
	}

}// ~ END OF Class ClassType
