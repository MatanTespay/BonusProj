package core;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Employee ~ represent an employee of our company
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public abstract class Employee implements Serializable {
private static final long serialVersionUID = 8762248072562793928L;
	//-------------------------------Class Members------------------------------
	/**
	 * The employee number - the key
	 */
	protected int employeeNumber;
	/**
	 * The first name of this employee
	 */
	protected String firstName;
	/**
	 * The last name of this employee
	 */
	protected String lastName;
	/**
	 * The date of birth of this employee
	 */
	protected Date birthDate;
	/**
	 * The date when this employee started work in the company
	 */
	protected Date startWorkingDate;
	/**
	 * The password for recognizing this employee in the system
	 */
	protected String password;
	/**
	 * The address of this employee
	 */
	protected Address address;

	//-------------------------------Constructors-----------------------------
	/**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param employeeNumber
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param startWorkingDate
	 * @param password
	 * @param address
	 */
	public Employee(int employeeNumber, String firstName, String lastName,
			Date birthDate, Date startWorkingDate, String password,
			Address address) {
		super();
		this.employeeNumber = employeeNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.startWorkingDate = startWorkingDate;
		this.password = password;
		this.address = address;
	}

	/**
	 * Partial Constructor ~ use for initial key fields
	 * 
	 * @param employeeNumber
	 */
	public Employee(int employeeNumber) {
		super();
		this.employeeNumber = employeeNumber;
	}

	//-------------------------------Getters And Setters------------------------------

	/**
	 * @return the employeeNumber
	 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber the employeeNumber to set
	 */
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the startWorkingDate
	 */
	public Date getStartWorkingDate() {
		return startWorkingDate;
	}

	/**
	 * @param startWorkingDate the startWorkingDate to set
	 */
	public void setStartWorkingDate(Date startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Address getAddress() {
		return address;
	}

	//-------------------------------More Methods - Abstract Methods------------------------------
	/**
	 * This method calculate this employee seniority (in years).
	 * If he started to work in this year, than his seniority is 0 years.
	 * @return employee seniority
	 */
	@SuppressWarnings("deprecation")
	public int getEmployeeSeniority(){
		return new Date().getYear() - this.startWorkingDate.getYear();
	}
	
	/**
	 * Abstract method - need to be implemented
	 */
	public abstract int getNumberOfThisYearSummerAssignments();
	
	//-------------------------------hashCode equals & toString------------------------------
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "employeeNumber=" + employeeNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", startWorkingDate=" + startWorkingDate
				+ ", password=" + password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeNumber;
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
		Employee other = (Employee) obj;
		if (employeeNumber != other.employeeNumber)
			return false;
		return true;
	}
	
}// ~ END OF Class Employee
