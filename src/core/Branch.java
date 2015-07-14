package core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Branch ~ represent a single branch of the company
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class Branch implements Serializable {

	private static final long serialVersionUID = 5410157127919590931L;
	//region members 
	/**
	 * Branch's number - the key
	 */
	private Integer branchNumber;
	/**
	 * Branch's name
	 */
	private String branchName;
	/**
	 * All branch's orders
	 */
	private Set<Agent> agents;
	/**
	 * Branch's address
	 */
	private Address branchAddress;
//end_region

	//region constructors 
	/**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param branchNumber
	 * @param branchName
	 * @param branchAddress
	 */
	public Branch(Integer branchNumber, String branchName, Address branchAddress) {
		super();
		this.branchNumber = branchNumber;
		this.branchName = branchName;
		this.agents = new HashSet<Agent>();
		this.branchAddress = branchAddress;
	}
	
	/**
	 * Partial Constructor ~ use for initial key fields
	 * 
	 * @param branchNumber
	 */
	public Branch(Integer branchNumber) {
		super();
		this.branchNumber = branchNumber;
	}
//end_region

	//region setter-getter 
	/**
	 * @return the branchNumber
	 */
	public long getbranchNumber() {
		return branchNumber;
	}

	/**
	 * @param branchNumber the branchNumber to set
	 */
	public void setbranchNumber(Integer branchNumber) {
		this.branchNumber = branchNumber;
	}

	/**
	 * @return the branchName
	 */
	public String getbranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setbranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the orders
	 */
	public Set<Agent> getAgents() {
		return Collections.unmodifiableSet(agents);
	}

	/**
	 * @return the branchAddress
	 */
	public Address getBranchAddress() {
		return branchAddress;
	}

	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(Address branchAddress) {
		this.branchAddress = branchAddress;
	}
//end_region

	//region functions 
	/**
	 * The role of this method is to add a new
	 * agent to the agents set
	 * @param agent
	 * @return true if the agent added successfully or false otherwise
	 */
	public boolean addAgent(Agent agent){
		//TO DO - Complete this method
		if(agent != null && !this.agents.contains(agent))
			return this.agents.add(agent);
		return false;
	}// ~ END OF addAgent
	
	/**
	 * The role of this method is to remove
	 * an agent from the agents set
	 * @param agent
	 * @return true if the agent removed successfuly or false otherwise
	 */
	public boolean removeAgent(Agent agent){
		//TO DO - Complete this method
		if(agent != null && this.agents.contains(agent))
			return this.agents.remove(agent);
		return false;
	}// ~ END OF removeAgent
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Branch [branchNumber=" + branchNumber + ", branchName="
				+ branchName + ", agents=" + agents + ", branchAddress="
				+ branchAddress + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (branchNumber ^ (branchNumber >>> 32));
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
		Branch other = (Branch) obj;
		if (branchNumber != other.branchNumber)
			return false;
		return true;
	}
//end_region
	
}// ~ END OF Class Branch
