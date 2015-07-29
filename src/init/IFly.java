//package init;
//
//import java.io.Serializable;
//import java.net.URL;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.AbstractMap;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.PriorityQueue;
//import java.util.Set;
//import java.util.TreeSet;
//
////import utils.AgentsComparator;
//import utils.Constants;
//import utils.E_Airports;
//import utils.E_Cities;
//import utils.E_ClassType;
//import exceptions.AddBranchExeption;
//import exceptions.AddCustomerExeption;
//import exceptions.AddEmployeeExeption;
//import exceptions.AddFlightExeption;
//import exceptions.AddFlightToOrderExeption;
//import exceptions.AddPilotOrFlightAttendantExeption;
//import exceptions.AddStopToFlightExeption;
//import exceptions.AddessUpdateExeption;
//import exceptions.CancelOrderExeption;
//import exceptions.ConnectAgentToBranchExeption;
//import exceptions.PayingCustomerExeption;
//import exceptions.addFlightTicketExeption;
//
///**
// * This IFly object represents the class system
// *
// * @author Java Course Team 2014
// * @author University Of Haifa-Israel
// */
//public class IFly implements Serializable {
//
//    private static IFly instantData;
//    private static final long serialVersionUID = -2412740169914328818L;
//    //-------------------------------Class Members------------------------------
//    /**
//     * All IFly employees (agents, flight attendants, pilots)
//     */
//    private HashMap<Integer, Employee> employees;
//    /**
//     * All IFly branches
//     */
//    private HashMap<Integer, Branch> branches;
//    /**
//     * All IFly customers
//     */
//    private HashMap<String, Customer> customers;
//    /**
//     * All IFly flights
//     */
//    private HashMap<Integer, Flight> flights;
//
//    //-------------------------------Constructors------------------------------
//    /**
//     * Full Constructor ~ use for initial all data structures
//     */
//    private IFly() {
//        employees = new HashMap<Integer, Employee>();
//        branches = new HashMap<Integer, Branch>();
//        customers = new HashMap<String, Customer>();
//        flights = new HashMap<Integer, Flight>();
//    }
//
//    public static IFly getInstance() {
//        if (instantData == null) {
//            instantData = new IFly();
//        }
//
//        return instantData;
//    }
//
//    //-----------------------------------------Getters--------------------------------------
//    /**
//     * @return the employees
//     */
//    public Map<Integer, Employee> getEmployees() {
//        return Collections.unmodifiableMap(employees);
//    }
//
//    /**
//     * @return the branches
//     */
//    public Map<Integer, Branch> getBranches() {
//        return Collections.unmodifiableMap(branches);
//    }
//
//    /**
//     * @return the customers
//     */
//    public Map<String, Customer> getCustomers() {
//        return Collections.unmodifiableMap(customers);
//    }
//
//    /**
//     * @return the flights
//     */
//    public Map<Integer, Flight> getFlights() {
//        return Collections.unmodifiableMap(flights);
//    }
//
//    //-------------------------------Add && Remove Methods------------------------------
//    /**
//     * this method add new branch to our company IIF the branch not exists
//     * already and the details are valid. to create new branch you need address
//     * object first
//     *
//     * @param branchNumber
//     * @param branchName
//     * @param city
//     * @param country
//     * @param street
//     * @param houseNumber
//     * @param phoneNumber
//     * @return true if the branch added successfully, false otherwise
//     * @throws exceptions.AddBranchExeption
//     */
//    public boolean addBranch(int branchNumber, String branchName, E_Cities city, String country,
//            String street, int houseNumber, String[] phoneNumber) throws AddBranchExeption {
//
//        if (branchName != null && branchNumber > 0 && city != null
//                && street != null && houseNumber > 0 && phoneNumber != null && country != null) {
//            if (!branches.containsKey(branchNumber)) {
//                Address branchAddress = new Address(country, city, street, houseNumber, phoneNumber);
//                Branch branchToAdd = new Branch(branchNumber, branchName,
//                        branchAddress);
//                if (branches.put(branchNumber, branchToAdd) == null) {
//                    return true;
//                }
//            }
//
//            throw new AddBranchExeption("Error, Branch number alredy exist");
//        } else {
//            throw new AddBranchExeption("Error in data");
//        }
//
//    } //~ END OF addBranch
//
//    /**
//     * Creates and adds new employee into the appropriate data-structure
//     *
//     * @param employee
//     * @return true if this employee added successfully or false otherwise
//     * @throws exceptions.AddEmployeeExeption
//     */
//    public boolean addEmployee(Employee employee) throws AddEmployeeExeption {
//        //TODO - Complete this method
//        if (employee != null) {
//            if (!this.employees.containsKey(employee.getEmployeeNumber())) {
//                if (employee.getBirthDate().equals(employee.getStartWorkingDate())
//                        || employee.getBirthDate().after(employee.getStartWorkingDate())) {
//                    throw new AddEmployeeExeption("Dates are not consistent");
//                }
//                if (this.employees.put(employee.getEmployeeNumber(), employee) == null) {
//                    return true;
//                }
//                throw new AddEmployeeExeption("Error, employee number alredy exist");
//            }
//            throw new AddEmployeeExeption("Error, employee number alredy exist");
//        } else {
//            throw new AddEmployeeExeption("Error in data");
//        }
//
//    }// ~ END OF addEmployee
//
//    /**
//     * Creates and adds new customer into the appropriate data-structure spatial
//     * validity: - passport number length need to be as it represented in
//     * Constants class, and contains only digits.
//     *
//     * @see Constants#PASSPORT_NUMBER_SIZE
//     * @param passportNumber
//     * @param firstName
//     * @param lastName
//     * @param birthDate
//     * @param password
//     * @param email
//     * @param customerAddress
//     * @return true if succeeded to add
//     */
//    public boolean addCustomer(String passportNumber, double balance, String firstName, String lastName,
//            Date birthDate, String password, URL email, Address customerAddress) throws AddCustomerExeption {
//        //check validity first
//        if (passportNumber != null && firstName != null && lastName != null && birthDate != null
//                && password != null && email != null && customerAddress != null && balance > 0) {
//            if (passportNumber.length() == Constants.PASSPORT_NUMBER_SIZE) {
//                for (int i = 0; i < passportNumber.length(); i++) {
//                    if (!Character.isDigit(passportNumber.charAt(i))) {
//                        return false;
//                    }
//                }
//            } else {
//                throw new AddCustomerExeption("Passport must be 8 digits");
//            }
//
//            //creating a new customer with his full constructor
//            Customer customer = new Customer(Integer.parseInt(passportNumber), balance, firstName, lastName, birthDate, password, email, customerAddress);
//
//            if (!customers.containsKey(passportNumber)) {
//                customers.put(passportNumber, customer); // add this customer
//                return true;
//            } else {
//                throw new AddCustomerExeption("Customer id alredy exist");
//            }
//        } else {
//            throw new AddCustomerExeption("Error in data");
//        }
//
//    }// ~ END OF addCustomer
//
//    /**
//     * This method add new Flight to our company IIF this conditions are valid:
//     * - landing date has to be after the take off date - flight doesn't exists
//     * - all the variables are valid( number>0 & objects != null) - source and
//     * destination can't be the same airport
//     *
//     * @param flightNumber
//     * @param flightDateAndTimeSource
//     * @param flightDateAndTimeDestination
//     * @param flightCost
//     * @param airplane
//     * @return true if Flight added successfully, false otherwise
//     * @throws exceptions.AddFlightExeption
//     */
//    public boolean addFlight(int flightNumber, Date flightDateAndTimeSource,
//            Date flightDateAndTimeDestination, double flightCost, E_Airports source, E_Airports destination,
//            Airplane airplane) throws AddFlightExeption {
//        if (flightNumber > 0 && flightDateAndTimeSource != null && flightDateAndTimeDestination != null
//                && flightCost > 0 && airplane != null && source != null && destination != null) {
//            if (flightDateAndTimeSource.before(flightDateAndTimeDestination)) {
//                if (!flights.containsKey(flightNumber)) {
//                    if (!source.equals(destination)) {
//                        Flight flightToAdd = new Flight(flightNumber, flightDateAndTimeSource, flightDateAndTimeDestination, source, destination, flightCost, airplane);
//                        if (flights.put(flightNumber, flightToAdd) == null) {
//                            return true;
//                        }
//                        throw new AddFlightExeption("Error, couldnt't add flight");
//                    }
//                    throw new AddFlightExeption("Error, source and destination cant be the same");
//                }
//                throw new AddFlightExeption("Error, flight already exist");
//            }
//            throw new AddFlightExeption("Dates are not consistent");
//        } else {
//            throw new AddFlightExeption("Error in data");
//        }
//    }// ~ END OF addFlight
//
//    /**
//     * this method adds stop to specific flight IIF the stop is not already in
//     * part of the flight path. Hint! the path is not only the step in's.
//     * stepNumber of the stepIn represent the stop number
//     *
//     * @param flightNumber
//     * @param stepLocation
//     * @return true if step added successfully, false otherwise
//     * @throws exceptions.AddStopToFlightExeption
//     */
//    public boolean addStepInToFlight(int flightNumber, E_Airports stepLocation) throws AddStopToFlightExeption {
//
//        if (flightNumber > 0 && stepLocation != null) {
//            if (flights.containsKey(flightNumber)) {
//                Flight flight = flights.get(flightNumber);
//                if (!flight.getDestination().equals(stepLocation) && !flight.getSource().equals(stepLocation)) {
//                    for (StepIn s : flight.getStops()) {
//                        if (s.getAirport().equals(stepLocation)) {
//                            throw new AddStopToFlightExeption("Error, this stop is alredy exist in this flight");
//                        }
//                    }
//                    StepIn stopToAdd = new StepIn(flight.getStops().size() + 1, flight, stepLocation);
//                    if (flight.addStop(stopToAdd)) {
//                        return true;
//                    } else {
//                        throw new AddStopToFlightExeption("Error, this stop is alredy exist in this flight");
//                    }
//                }
//            }
//            throw new AddStopToFlightExeption("Error doesn't exist");
//        } else {
//            throw new AddStopToFlightExeption("Error in data");
//        }
//    } //~ END OF addStepInToFlight
//
//    /**
//     * This method first add a given flight to a given employee (pilot or
//     * FlightAttendant) and do the opposite (use instanceof operation). If the
//     * employee unsuccessfully added to the flight ==> Roll back is needed
//     *
//     * @param employee
//     * @param flight
//     * @return true if the connection added successfully, false otherwise
//     * @throws exceptions.AddPilotOrFlightAttendantExeption
//     */
//    public boolean addPilotOrFlightAttendantToFlight(Employee employee, Flight flight) throws AddPilotOrFlightAttendantExeption {
//        //TODO - Complete this method
//
//        if (employee != null && flight != null) {
//
//            if (employee instanceof FlightAttendant) {
//                FlightAttendant tempFA = (FlightAttendant) employee;
//                if (tempFA.getFlights().contains(flight)) {
//                    throw new AddPilotOrFlightAttendantExeption("Error, Flight Attendant alredy assigned to this flight");
//                }
//
//                if (tempFA.addFlight(flight)) {
//                    if (flight.addFlightAttendant(tempFA)) {
//                        return true;
//                    } else {
//                        tempFA.removeFlight(flight);
//                        throw new AddPilotOrFlightAttendantExeption("Error, couldnt add Flight Attendant to flight\nThe flight overlaps another flight assignment");
//                    }
//                } else {
//                    throw new AddPilotOrFlightAttendantExeption("Error, couldnt add Flight Attendant to flight\nThe flight overlaps another flight assignment");
//                }
//
//            } else if (employee instanceof Pilot) {
//                Pilot tempP = (Pilot) employee;
//                if (tempP.addFlight(flight)) {
//                    if (flight.addPilot(tempP)) {
//                        return true;
//                    } else {
//                        tempP.removeFlight(flight);
//                        throw new AddPilotOrFlightAttendantExeption("Error, couldnt add Pilot to flight");
//
//                    }
//                }
//                throw new AddPilotOrFlightAttendantExeption("Error, couldnt add Pilot to flight, pilot alredy assigned to this flight");
//            }
//        }
//
//        throw new AddPilotOrFlightAttendantExeption("Error in data");
//    }// ~ END OF addPilotOrFlightAttendantToFlight
//
//    /**
//     * this method connect existing agent to existing branch IIF the branch and
//     * the agent exists unsuccessfully
//     *
//     * @param agentNumber
//     * @param branchNumber
//     * @return true if succeeded, false otherwise
//     */
//    public boolean connectAgentToBranch(int agentNumber, int branchNumber) throws ConnectAgentToBranchExeption {
//        if (agentNumber > 0 && branchNumber > 0) {
//            if (employees.containsKey(agentNumber) && branches.containsKey(branchNumber)) {
//                Employee agent = employees.get(agentNumber);
//                Branch branch = branches.get(branchNumber);
//                if (agent instanceof Agent) {
//                    if (!branch.getAgents().contains(agent)) {
//                        if (branch.addAgent((Agent) agent)) {
//                            ((Agent) agent).setWorkBranch(branch);
//                            return true;
//                        }
//                        throw new ConnectAgentToBranchExeption("Error,could't connect agent to Branch");
//                    }
//                    throw new ConnectAgentToBranchExeption("Error, agent is alredy connect to this branch");
//                }
//
//            }
//            throw new ConnectAgentToBranchExeption("Error, Agent/Branch doesnt exist");
//
//        } else {
//            throw new ConnectAgentToBranchExeption("Error in data");
//        }
//
//    }// ~ END OF connectAgentToBranch
//
//    /**
//     * This method add a new flight order to order. If the order is already
//     * exist in the customer's orders, than just add it to the existing order.
//     * If there no such order in the customer's orders, than create a new order
//     * and add it to customer's orders and agent's orders and associate this
//     * flight order with the flight and order.
//     * <h3> do not forget to set this customer as a responsible for this order.
//     *
//     * @param agentNumber
//     * @param customerPassport
//     * @param orderNumber
//     * @param flightNumber
//     * @return true if the FlightOrder added successfully, false otherwise
//     */
//    public boolean addFlightToOrder(int agentNumber, String customerPassport, int orderNumber, int flightNumber) throws AddFlightToOrderExeption {
//        //TODO - Complete this method
//        Agent theAgent = new Agent(agentNumber);
//        Customer theCustomer = new Customer(Integer.parseInt(customerPassport));
//        Flight theflight = new Flight(flightNumber);
//
//        if (agentNumber > 0 && customerPassport != null
//                && orderNumber > 0 && flightNumber > 0) {
//            if (employees.containsKey(theAgent.getEmployeeNumber()) && customers.containsKey(customerPassport)
//                    && flights.containsKey(flightNumber)) {
//
//                Order theOrder = new Order(orderNumber);
//
//                theCustomer = customers.get(customerPassport);
//                theflight = flights.get(theflight.getFlightNumber());
//
//                if (theCustomer.getOrders().contains(theOrder)) {
//
//                    theOrder = theCustomer.getOrders().get(theCustomer.getOrders().indexOf(theOrder));
//                    FlightOrder flightOrder = new FlightOrder(theflight,
//                            theOrder);
//
//                    if (theOrder.getFlightOrders().contains(flightOrder)) {
//                        //return false;
//                        throw new AddFlightToOrderExeption("Error, this customer already has a flight order\n"
//                                + "With the selected flight and the order number");
//                    }
//
//                    if (theOrder.addFlightOrder(flightOrder)) {
//                        if (theflight.addFlightOrder(flightOrder)) {
//                            return true;
//                        } else {
//                            theOrder.removeFlightOrder(flightOrder);
//                            throw new AddFlightToOrderExeption("Error, couldnt add the flight order");
//                        }
//                    }
//
//                    //order exist but couldn't add the flight order
//                    throw new AddFlightToOrderExeption("Error, couldnt add the flight order");
//
//                }
//
//                theAgent = (Agent) employees.get(agentNumber);
//                theOrder = new Order(orderNumber, false, theCustomer, null,
//                        theAgent);
//                FlightOrder tempFlight = new FlightOrder(theflight, theOrder);
//
//                if (theOrder.addFlightOrder(tempFlight)) {
//                    if (theflight.addFlightOrder(tempFlight)) {
//                        theCustomer.addOrder(theOrder);
//                        theAgent.addOrder(theOrder);
//                        return true;
//                    } else {
//                        theOrder.removeFlightOrder(tempFlight);
//                        //return false;
//                        throw new AddFlightToOrderExeption("Error, couldnt add the flight order");
//                    }
//                } else {
//                    //couldn't add the flight order to a new order
//                    //return false;
//                    throw new AddFlightToOrderExeption("Error, couldnt add the flight order");
//                }
//
//            }
//        }
//
//        throw new AddFlightToOrderExeption("Error in data");
//    }// ~ END OF addFlightOrder
//
//    /**
//     * This method add a new flight ticket to an existing flight order. Given
//     * the passenger's passport number, this method also add this ticket to this
//     * passenger IFF he is'nt a passenger on this specific flight order and the
//     * exact seat is available
//     *
//     * @param flightNumber
//     * @param orderNumber
//     * @param passportNumber
//     * @param seat
//     * @param row
//     * @param classType
//     * @return true if the ticket added successfully, false otherwise
//     */
//    public boolean addFlightTicketToFlightOrder(int flightNumber, int orderNumber, String passportNumber,
//            int seat, int row, String classType) throws addFlightTicketExeption {
//		//TODO - Complete this method
//
//        //validity
//        if (flightNumber < 0 || orderNumber < 0 || passportNumber == null || seat < 0 || row < 0 || classType == null) {
//            //return false;
//            throw new addFlightTicketExeption("Error in data");
//        }
//
//        Customer thePassenger = (customers.containsKey(passportNumber)) ? customers.get(passportNumber) : null;
//        Flight theflight = flights.containsKey(flightNumber) ? flights.get(flightNumber) : null;
//        if (thePassenger == null || theflight == null) {
//            throw new addFlightTicketExeption("Error in data");
//        }
//
//        FlightOrder tempFO = new FlightOrder(new Flight(flightNumber), new Order(orderNumber));
//
//        for (Map.Entry<Integer, Flight> entry : flights.entrySet()) {
//
//            //search for the flight order
//            if (entry.getValue().getFlightOrders().contains(tempFO)) {
//
//                tempFO = entry.getValue().getFlightOrders().get(entry.getValue().getFlightOrders().indexOf(tempFO));
//
//                FlightTicket ticket = tempFO.addTicket(thePassenger, seat, row, classType, flightNumber, orderNumber);
//                if (ticket != null) {
//                    boolean result = thePassenger.addFlightTicket(ticket);
//                    if (result) {
//                        return true;
//                    } else {
//                        throw new addFlightTicketExeption("Error, couldnt add ticket for this customer");
//                    }
//                } else {
//                    throw new addFlightTicketExeption("Error, couldnt add ticket for this customer");
//                }
//            }
//        }
//
//        throw new addFlightTicketExeption("Error in data");
//    }// ~ END OF addFlightTicketToFlightOrder
//
//    /**
//     * this method change the address of existing customer IIF the customer
//     * already exists and the detail are valid.
//     *
//     * @param customerId
//     * @param country
//     * @param city
//     * @param street
//     * @param houseNumber
//     * @param phonenumbers
//     * @return true if succeeded, false otherwise.
//     */
//    public boolean changeCustomerAddress(String customerId, String country, E_Cities city, String street, int houseNumber, String[] phonenumbers)
//            throws AddessUpdateExeption {
//        if (customerId != null && country != null && city != null && street != null && houseNumber > 0 && phonenumbers != null && customers.containsKey(customerId)) {
//            Customer tempCustomer = customers.get(customerId);
//            Address addressToChange = new Address(country, city, street, houseNumber, phonenumbers);
//            tempCustomer.setCustomerAddress(addressToChange);
//            return true;
//
//        } else {
//            throw new AddessUpdateExeption("There was an error in updating data");
//        }
//
//    } // ~ END OF changeCustomerAddress
//
//    /**
//     * this method cancel order from the system by order number (Primary Key).
//     * completely canceled order IIF all related objects will delete from order.
//     * <b>Hint!</b> disconnect the order from the flight (flightOrder), use
//     * helper method! * ~ use disconnectFlightOrder
//     *
//     * @param orderNumber
//     * @return true if order completely canceled, false otherwise
//     * @throws exceptions.CancelOrderExeption
//     */
//    public boolean cancelOrder(int orderNumber) throws CancelOrderExeption {
//        Order orderToRemove;
//        if (orderNumber > 0) {
//            orderToRemove = new Order(orderNumber);
//            for (Customer c : this.customers.values()) {
//                if (c.getOrders().contains(orderToRemove)) {
//                    orderToRemove = c.getOrders().get(c.getOrders().indexOf(orderToRemove));
//
//                    List<FlightOrder> data = new ArrayList<>(orderToRemove.getFlightOrders());
//
//                    for (int i = 0; i < data.size(); i++) {
//                        disconnectFlightOrder(data.get(i));
//                    }
//
////                    for (FlightOrder flightOrder : data) {
////                        disconnectFlightOrder(flightOrder);
////                    }
//                    for (Employee employee : this.employees.values()) {
//                        if (employee instanceof Agent) {
//                            if (((Agent) employee).getOrders().contains(orderToRemove)) {
//                                ((Agent) employee).removeOrder(orderToRemove);
//                                break;
//                            }
//                        }
//                    }
//
//                    c.removeOrder(orderToRemove);
//
//                    return true;
//                }
//            }
//
//        }
//        throw new CancelOrderExeption("There was an error in updating data");
//    }// ~ END OF cancelOrder
//
//    /**
//     * this method disconnect the flight and the order from flight order from
//     * both sided.
//     *
//     * @param flightOrder
//     */
//    private void disconnectFlightOrder(FlightOrder flightOrder) {
//        if (flightOrder != null) {
//            if (flightOrder.getFlight() != null) {
//                flightOrder.getFlight().removeFlightOrder(flightOrder);
//            }
//            if (flightOrder.getOrder() != null) {
//                flightOrder.getOrder().removeFlightOrder(flightOrder);
//            }
//
//        }
//    }// ~ END OF disconnectFlightOrder
//
//    /**
//     * this Method associate Customer to Pay for order IIF the customer have
//     * enough money to pay and the order didn't paid already
//     *
//     * @param orderNumber
//     * @param customerNumber
//     * @return true if succeeded, false otherwise
//     * @throws exceptions.PayingCustomerExeption
//     */
//    public boolean associateCustomerPayingOrder(int orderNumber, String customerNumber) throws PayingCustomerExeption {
//
//        if (orderNumber > 0 && customerNumber != null) {
//
//            Order tempOrder = new Order(orderNumber);
//            Customer cust = this.customers.get(customerNumber);
//            if (cust != null) {
//
//                if (cust.getOrders().indexOf(tempOrder) != -1) {
//                    tempOrder = cust.getOrders().get(cust.getOrders().indexOf(tempOrder));
//                    if (tempOrder.isPaid() == false && cust.payOrder(tempOrder.getOrderCost())) {
//                        tempOrder.setPaid(true);
//                        return true;
//                    }
//                }
//            }
//
//        }
//        throw new PayingCustomerExeption("There was an error on updating data");
//
//    }
//	//-------------------------------Queries------------------------------
//
//    //===================================================
//    // 					HW_2_Queries
//    //===================================================
//    /**
//     * This query returns X number of most popular flights. Most popular flight
//     * is a flight with the highest number of passengers that bought tickets.
//     * <b>Hint!</b>The number of tickets is represented by the number of
//     * customers included in the related FlightOrder objects of the Flight
//     *
//     * @return list of flights
//     */
//    public List<Flight> getTheTopXPopularFlights(int x) {
//        // TODO complete this method
//        return getTopFlights(x, new ArrayList<Flight>());
//
//    }// ~END OF getTheTopXPopularFlights
//
//    private List<Flight> getTopFlights(int amount, ArrayList<Flight> data) {
//
//        if (amount == 0) {
//            return new ArrayList<Flight>();
//        }
//        int max = 0;
//        Flight temp = null;
//        getTopFlights(amount - 1, data);
//        for (Flight flight : flights.values()) {
//            int numOfPassengers = 0;
//            for (FlightOrder fO : flight.getFlightOrders()) {
//                if (fO != null) {
//                    if (fO.getTickets() != null) {
//                        numOfPassengers += fO.getTickets().size();
//                    }
//                }
//            }
//
//            if (numOfPassengers >= max && !data.contains(flight)) {
//                max = numOfPassengers;
//                temp = flight;
//            }
//
//        }
//
//        data.add(temp);
//        return data;
//    }
//
//    /**
//     * This method return the most profitable order. The most Profitable order
//     * is the order with the highest amount of money from all the orders existed
//     * in the company. <b>use the helper method!</b>
//     *
//     * @see Order#getOrderCost()
//     * @return order or null
//     */
//    public Order getTheMostProfitableOrder() {
//        // TODO complete this method
//        Order profitableOrder = null;
//
//        double maxOrderCost = 0;
//        for (Customer customer : customers.values()) {
//            for (Order order : customer.getOrders()) {
//                if (order != null && order.getOrderCost() > maxOrderCost) {
//                    maxOrderCost = order.getOrderCost();
//                    profitableOrder = order;
//                }
//
//            }
//        }
//
//        return profitableOrder;
//
//    }// ~ END OF getTheMostProfitableOrder
//
//    /**
//     * super agent is an agent thats sells more then 10 flight tickets and those
//     * tickets belong to a paid order.
//     *
//     * @return an agent
//     */
//    public List<Employee> getAllSuperAgents() {
//        List<Employee> superAgents = new ArrayList<Employee>();
//        int count = 0;
//        for (Employee agent : this.employees.values()) {
//            count = 0;
//            if (agent instanceof Agent) {
//                for (Order o : ((Agent) agent).getOrders()) {
//                    if (o.isPaid() == true) {
//                        for (FlightOrder fo : o.getFlightOrders()) {
//                            count += fo.getTickets().size();
//                        }
//                    }
//                }
//                if (count > 10) {
//                    superAgents.add(agent);
//                }
//            }
//        }
//        return superAgents;
//    }// ~ END OF getAllSuperAgents
//
//    /**
//     * <b> Using any data-structure is not allowed <b>. only what we already
//     * have. The method returns the orders of the profitable customer, the
//     * customer with the highest paid orders number.
//     * <b>Hint:<b> create and use a method that counts paid orders for a given
//     * customer
//     *
//     * @return profitable customer
//     */
//    public List<Order> getAllOrdersOfMostProfitableCustomer() {
//        int numberOfOrders = 0;
//        Customer profitableCustomer = null;
//
//        for (Customer customer : customers.values()) {
//            if (customer.getOrders().size() > numberOfOrders) {
//                numberOfOrders = customer.getOrders().size();
//                profitableCustomer = customer;
//            }
//        }
//
//        if (profitableCustomer != null) {
//            return profitableCustomer.getOrders();
//        }
//
//        return null;
//    } // ~ END OF getAllOrdersOfMostProfitableCustomer
//
//    /**
//     * employee of the month is the employee that take part in the highest
//     * number of flights, soars in the given month, and have at list 2 stops
//     *
//     * @param month
//     * @return employee
//     */
//    @SuppressWarnings("deprecation")
//    public Employee employeeOfTheMonth(Date month) {
//        // TODO complete this method
//        int max = 0;
//        Employee employeeOfTheMonth = null;
//        int maxEmployee = 0;
//        for (Employee e : this.employees.values()) {
//            ArrayList<String> h = new ArrayList<>();
//            customers.containsKey(h);
//            max = 0;
//            if (e instanceof Pilot || e instanceof FlightAttendant) {
//                Set<Flight> flights = null;
//                if (e instanceof Pilot) {
//                    flights = ((Pilot) e).getFlights();
//                } else {
//                    flights = ((FlightAttendant) e).getFlights();
//                }
//
//                for (Flight f : flights) {
//                    if (f.getFlightDateAndTimeSource().getMonth() + 1 == month.getMonth() && f.getFlightDateAndTimeSource().getYear() + 1900 == month.getYear() + 1900 && f.getStops().size() >= 2) {
//                        max += 1;
//                    }
//                }
//
//                if (max > maxEmployee) {
//                    maxEmployee = max;
//                    employeeOfTheMonth = e;
//                }
//            }
//        }
//        return employeeOfTheMonth;
////        int flightNum = 0;
////        Employee e = null;
////
////        for (Map.Entry<Integer, Employee> emp : employees.entrySet()) {
////            int empFlights = 0;
////            if (emp.getValue() instanceof Pilot) {
////                for (Flight f : ((Pilot) emp.getValue()).getFlights()) {
////                    if (f.getFlightDateAndTimeSource().getMonth() + 1 == month.getMonth() &&
////                            f.getFlightDateAndTimeSource().getYear()+1900 == month.getYear()) {
////                        if (f.getStops().size() > 1) {
////                            empFlights++;
////                        }
////                    }
////                }
////            } else if (emp.getValue() instanceof FlightAttendant) {
////                for (Flight f : ((FlightAttendant) emp.getValue()).getFlights()) {
////                    if (f.getFlightDateAndTimeSource().getMonth() + 1 == month.getMonth() &&
////                            f.getFlightDateAndTimeSource().getYear()+1900 == month.getYear()) {
////                        if (f.getStops().size() > 1) {
////                            empFlights++;
////                        }
////                    }
////                }
////            }
////
////            if (empFlights > flightNum) {
////                flightNum = empFlights;
////                e = emp.getValue();
////            }
////        }
////
////        return e;
//    }// ~ END OF employeeOfTheMonth
//
//    //public Object getInfo(String fname,String)
//    /**
//     * this method return all the summer flights in this year sorted by stops
//     *
//     * @see Constants#SUMMER_MONTHES
//     * @return TreeSet<Flight
//     */
//    @SuppressWarnings("deprecation")
//    public TreeSet<Flight> getAllSummerFlightsSortedByNumberOfStops() {
//        // TODO complete this method
//        TreeSet<Flight> tree = new TreeSet<Flight>();
//        /*for (Entry<Integer, Flight> entry : flights.entrySet()) {
//         if(isFlightInSummer(entry.getValue()))
//         tree.add(entry.getValue());
//         }*/
//
//        for (Flight f : flights.values()) {
//            if (isFlightInSummer(f)) {
//                tree.add(f);
//            }
//        }
//        return tree;
//
//    } // ~ END OF getAllSummerFlightsSortedByNumberOfStops
//
//    /**
//     * This method return all this summer flights that soars from or lands in a
//     * given airport sorted by flight cost in an ascending order.
//     *
//     * @param location
//     * @return flights
//     */
//    @SuppressWarnings("deprecation")
//    public ArrayList<Flight> getAllThisSummerFlightsByLocation(E_Airports location) {
//        // TODO complete this method
//        ArrayList<Flight> list = new ArrayList<Flight>();
//
//        for (Entry<Integer, Flight> entry : flights.entrySet()) {
//            if (isFlightInSummer(entry.getValue())
//                    && (entry.getValue().getSource().equals(location) || entry.getValue().getDestination().equals(location))) {
//                list.add(entry.getValue());
//            }
//        }
//
//        Collections.sort(list, new Comparator<Flight>() {
//            @Override
//            public int compare(Flight f1, Flight f2) {
//                // TODO Auto-generated method stub
//                return new Double(f1.getFlightCost()).compareTo(f2.getFlightCost());
//                //return (int) (f1.getFlightCost() - f2.getFlightCost());
//            }
//        });
//
//        return list;
//    } // ~ END OF getAllThisSummerFlightsByLocation
//
//    /**
//     * potential customers for Agents is a customer who lives in the same city
//     * where Agent lives but the customer never ordered from the agent. this
//     * method Retrieve <Agent,List<Customer>> of agent with list of potential
//     * customers for each Agent IIF the Agent have any, otherwise the Agent will
//     * exclude from the map
//     *
//     * @return <Agent,List<Customer>>
//     */
//    public Map<Agent, List<Customer>> getPotentialCustomersForAgents() {
//        // TODO complete this method
//        ArrayList<Customer> customersInCity = null;
//        Map<Agent, List<Customer>> potentialCustomers = new HashMap<Agent, List<Customer>>();
//
//        for (Entry<Integer, Employee> eEntry : employees.entrySet()) {
//            customersInCity = new ArrayList<Customer>();
//
//            if (eEntry.getValue() instanceof Agent) {
//
//                for (Entry<String, Customer> cEntry : customers.entrySet()) {
//                    if (cEntry.getValue().getCustomerAddress().getCity().equals(eEntry.getValue().getAddress().getCity())) {
//                        customersInCity.add(cEntry.getValue());
//                    }
//                }
//
//                for (Customer customer : customers.values()) {
//                    for (Order o : customer.getOrders()) {
//                        if (o.getAgent().equals(eEntry.getValue()) && customersInCity.contains(customer)) {
//                            customersInCity.remove(o.getResponsibleCustomer());
//                        }
//                    }
//                }
//
//                if (customersInCity.size() > 0) {
//                    potentialCustomers.put((Agent) eEntry.getValue(), customersInCity);
//                }
//            }
//        }
//
//        return potentialCustomers;
//    } // ~ END OF getPotentialCustomersForAgents
//
//    /**
//     * Retrieve Refugees - Potential customer for branch is a customer how lives
//     * in a city where IFly owns a branch but the customer never bought from it.
//     * This method return a Map with a list of potential customers for each
//     * branch.
//     * <b>Warning:<b> branch that as no such customers will not show up in your
//     * Map.
//     *
//     * @return map with potential customers for each branch
//     */
//    public Map<Branch, List<Customer>> getPotentialCustomersForBranch() {
//        // TODO complete this method
//        ArrayList<Customer> customersInCity = null;
//        Map<Branch, List<Customer>> potentialCustomers = new HashMap<Branch, List<Customer>>();
//
//        for (Entry<Integer, Branch> bEntry : branches.entrySet()) {
//            customersInCity = new ArrayList<Customer>();
//            for (Entry<String, Customer> cEntry : customers.entrySet()) {
//                if (cEntry.getValue().getCustomerAddress().getCity().equals(bEntry.getValue().getBranchAddress().getCity())) {
//                    customersInCity.add(cEntry.getValue());
//                }
//            }
//
//            for (Customer customer : customers.values()) {
//                for (Order o : customer.getOrders()) {
//                    if (o.getAgent().getWorkBranch().equals(bEntry.getValue()) && customersInCity.contains(customer)) {
//                        customersInCity.remove(o.getResponsibleCustomer());
//                    }
//                }
//            }
//
//            if (customersInCity.size() > 0) {
//                potentialCustomers.put(bEntry.getValue(), customersInCity);
//            }
//
//        }
//
//        return potentialCustomers;
//    } // ~ END OF getPotentialCustomersForBranch
//
//    /**
//     * This method return all flights sorted by occupancy in an ascending order.
//     * Occupancy of flight: (number of occupied seats)/(airplane size)
//     * <b>Hint:<b> first get all flight into a map where value is the occupancy
//     * then try sorting the "Map.Entry" by using a proper data structure.
//     *
//     * @return priority queue of sorted flights and their occupancy
//     */
//    public PriorityQueue<Entry<Flight, Double>> getFlightsSortedByOccupancy() {
//        // TODO complete this method
//        if (this.flights.size() <= 0) {
//            return null;
//        }
//
//        PriorityQueue<Entry<Flight, Double>> pq
//                = new PriorityQueue<Entry<Flight, Double>>(this.flights.size(), new Comparator<Entry<Flight, Double>>() {
//
//                    @Override
//                    public int compare(Entry<Flight, Double> f1, Entry<Flight, Double> f2) {
//                        if (f1.getKey().getFlightOccupancy() > f2.getKey().getFlightOccupancy()) {
//                            return 1;
//                        } else if (f1.getKey().getFlightOccupancy() == f2.getKey().getFlightOccupancy()) {
//                            return 0;
//                        }
//
//                        return -1;
//
//                    }
//                });
//
//        for (Entry<Integer, Flight> f : flights.entrySet()) {
//
//            pq.add(new AbstractMap.SimpleEntry<Flight, Double>(f.getValue(), f.getValue().getFlightOccupancy()));
//        }
//
//        return pq;
//    } // ~ END OF getFlightsSortedByOccupancy
//
//    /**
//     * This method find all this year summer workers and sort them by their
//     * seniority in a descending order. This year summer workers are workers
//     * that have more than 0 number of this year summer assignments.
//     *
//     * @see Employee#getNumberOfThisYearSummerAssignments()
//     * @return list of employees
//     */
//    public List<Employee> getAllThisSummerWorkEmployeesSortedBySeniority() {
//        // TODO complete this method	
//        List<Employee> list = new ArrayList<Employee>();
//
//        Comparator<Employee> compEmp = new Comparator<Employee>() {
//
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                // TODO Auto-generated method stub
//                return o2.getEmployeeSeniority() - o1.getEmployeeSeniority();
//            }
//        };
//
//        for (Entry<Integer, Employee> entry : employees.entrySet()) {
//            if (entry.getValue().getNumberOfThisYearSummerAssignments() > 0) {
//                list.add(entry.getValue());
//            }
//        }
//
//        Collections.sort(list, compEmp);
//
//        return list;
//    } // ~ END OF getAllEmployeesSortedBySeniority
//
//    /**
//     * This method return for each branch a list of agents working in it. The
//     * agents list is sorted by agent rating, if two agents have the same
//     * rating, than sort them by their number of assignment each of them need to
//     * do on this summer.
//     * <b>Hint:<b> build for each branch in the hashMap a new sorting array
//     * list. Use a comparator that answer the sorting mission and sort the array
//     * list before you put it in the hashMap.
//     *
//     * @return map of agents as a value of branch keys
//     */
//    public Map<Branch, ArrayList<Agent>> getBranchesAgentsSortedByRating() {
//        // TODO complete this method
//
//        Comparator<Agent> empComp = new Comparator<Agent>() {
//
//            private double assignmentCompare(Agent o1, Agent o2) {
//                return o1.getNumberOfThisYearSummerAssignments()
//                        - o2.getNumberOfThisYearSummerAssignments();
//
//            }
//
//            @Override
//            public int compare(Agent o1, Agent o2) {
//                // TODO Auto-generated method stub
//                double rating = o1.calcRating() - o2.calcRating();
//                return (int) ((rating != 0) ? rating : assignmentCompare(o1, o2));
//
//            }
//        };
//
//        Map<Branch, ArrayList<Agent>> result = new HashMap<>();
//
//        for (Entry<Integer, Branch> entry : branches.entrySet()) {
//            ArrayList<Agent> a = new ArrayList<Agent>();
//            a.addAll(entry.getValue().getAgents());
//            Collections.sort(a, empComp);
//            result.put(entry.getValue(), a);
//
//        }
//
//        return result;
//    } // ~ END OF getBranchesAgentsSortedByRating
//
//    //===================================
//    // $$$       Bonus Methods       $$$
//    //===================================
//    /**
//     * This method find the best flight back. The given flight number belong to
//     * the source flight and now, by using this flight details and vacation
//     * time, you need to find flight back from the given flight destination to
//     * the given flight source. The returning flight have to return between:
//     * (given flight destination date) + (vacationDays) - 1 TO (given flight
//     * destination date) + (vacationDays) + 1.
//     * <b>Best flight means:<b> the flight back with minimum steps (stops)
//     * includes. If this method (findTheBestFlightBack) didn't find a direct
//     * flight, than you need to search for a connection flight (contains exactly
//     * 2 flights no more no less) with a helper method:
//     *
//     * @see IFly#getPairFlightsBack
//     * @param flightNumberSource
//     * @param vacationDays
//     * @return list of flights - because if we call the helper method, we need
//     * to return list of two flights
//     */
//    @SuppressWarnings("deprecation")
//    public List<Flight> findTheBestFlightBack(int flightNumberSource, int vacationDays) {
//        // TODO complete this method
//        List<Flight> result = new ArrayList<Flight>();
//        Flight source = this.flights.get(flightNumberSource);
//        if (source == null) {
//            return null;
//        }
//        int minStop = 0;
//        boolean isFirst = true;
//        int d = source.getFlightDateAndTimeDestination().getDate();
//        int y = source.getFlightDateAndTimeDestination().getYear();
//        int m = source.getFlightDateAndTimeDestination().getMonth();
//        Date startDate = new Date(y, m, (vacationDays) + (d - 1));
//        Date endDate = new Date(y, m, (vacationDays) + (d + 1));
//
//        //region get direct flights
//        for (Entry<Integer, Flight> entry : flights.entrySet()) {
//            //if the flight is a direct flight from destination back to the source
//            if (entry.getValue().getDestination().equals(source.getSource())) {
//                //if the flights in the time range
//                if ((startDate.compareTo(entry.getValue().getFlightDateAndTimeSource())
//                        * entry.getValue().getFlightDateAndTimeSource().compareTo(endDate)) > 0) {
//                    if (isFirst) {
//                        minStop = entry.getValue().getStops().size();
//                        result.add(entry.getValue());
//                        isFirst = false;
//                        /*} else if (entry.getValue().getStopset().size() == minStop) {
//                         result.add(entry.getValue());*/
//                    } else if (entry.getValue().getStops().size() < minStop) {
//                        minStop = entry.getValue().getStops().size();
//                        result.clear();
//                        result.add(entry.getValue());
//                    }
//                }
//            }
//        }
//
//        if (result.size() > 0) {
//            return result;
//        }
//
//        result = getPairFlightsBack(source, startDate, endDate);
//        return result;
//        //end_region
//
//    } // ~ END OF findTheBestFlightBack
//
//    /**
//     * This is a helper method for the method - findTheBestFlightBack. This
//     * method gets the income flight and two dates that represents range to find
//     * flights back. the method will take apart when there is no direct flight
//     * back. You need to find exactly two flights, that their connection is at
//     * the same day (e.g: flight1 lands in 1.1.2015 and flight2 soars in
//     * 1.1.2015). The chosen pair of flights back has to be the pair with the
//     * minimum number of stops (sum of the first and the second flight's stops).
//     *
//     * @param incomeFlight
//     * @param minRange - the minimum edge for soars
//     * @param maxRange - the maximum edge for soars
//     * @return List of two flights {firstFlight, secondFlight}
//     */
//    @SuppressWarnings("deprecation")
//    private List<Flight> getPairFlightsBack(Flight incomeFlight, Date minRange, Date maxRange) {
//        // TODO complete this method
//        List<Flight> FlightsFromDestination = getFlightsFromDestinationInRange(incomeFlight, minRange, maxRange);
//        Map<ArrayList<Flight>, Integer> pairFlights = new HashMap<ArrayList<Flight>, Integer>();
//        List<Flight> result = new ArrayList<Flight>();
//
//        for (Flight f : FlightsFromDestination) {
//            for (Entry<Integer, Flight> entry : this.flights.entrySet()) {
//                //if the second flight's source is the first flight's destination
//                if (entry.getValue().getSource().equals(f.getDestination())
//                        && entry.getValue().getDestination().equals(incomeFlight.getSource())) {
//                    //if the second flights soars in the day of the first flight arrival
//                    if (entry.getValue().getFlightDateAndTimeSource().equals(f.getFlightDateAndTimeDestination())) {
//                        ArrayList<Flight> a = new ArrayList<Flight>();
//                        a.add(f);
//                        a.add(entry.getValue());
//                        pairFlights.put(a, f.getStops().size() + entry.getValue().getStops().size());
//                    }
//                }
//            }
//        }
//
//        int minStops = 0;
//        boolean isFirst = true;
//        //find the pair which have the min' stops
//        for (Entry<ArrayList<Flight>, Integer> pair : pairFlights.entrySet()) {
//            if (isFirst) {
//                result.addAll(pair.getKey());
//                minStops = pair.getValue();
//                isFirst = false;
//            }
//
//            if (pair.getValue() < minStops) {
//                minStops = pair.getValue();
//                result.clear();
//                result.addAll(pair.getKey());
//            }
//        }
//
//        return result;
//    }// ~ END OF getPairFlightsBack
//
//    /**
//     * this function searches for all flights that soars from the destination of
//     * the given incoming flight between the given time range
//     *
//     * @param incomeFlight - the original flight
//     * @param minRange - the earlier date for the searched flight
//     * @param maxRange - the later date for the searched flight
//     * @return a list of flight
//     */
//    private List<Flight> getFlightsFromDestinationInRange(Flight incomeFlight, Date minRange, Date maxRange) {
//        List<Flight> result = new ArrayList<Flight>();
//
//        for (Entry<Integer, Flight> entry : flights.entrySet()) {
//            if (entry.getValue().getSource().equals(incomeFlight.getDestination())) {
//                if ((minRange.compareTo(entry.getValue().getFlightDateAndTimeSource())
//                        * entry.getValue().getFlightDateAndTimeSource().compareTo(maxRange)) > 0) {
//                    result.add(entry.getValue());
//                }
//            }
//        }
//
//        return result;
//    }
//
//    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
//    /**
//     * this function checks if the given flight is in the summer months
//     *
//     * @param flight - the flight to check
//     * @return true/false - true , if the flight is in the summer;otherwise
//     * false.
//     */
//    @SuppressWarnings("deprecation")
//    public static boolean isFlightInSummer(Flight flight) {
//
//        if (flight == null) {
//            return false;
//        }
//        if (flight.getFlightDateAndTimeSource().getYear() == new Date().getYear()) {
//
//            int mon = flight.getFlightDateAndTimeSource().getMonth() + 1;
//            if (Constants.SUMMER_MONTHES[0] == mon
//                    || Constants.SUMMER_MONTHES[1] == mon
//                    || Constants.SUMMER_MONTHES[2] == mon) {
//                return true;
//            }
//
//            /*if(Arrays.asList(utils.Constants.SUMMER_MONTHES).contains(flight.getFlightDateAndTimeSource().getMonth()))
//             return true;
//			
//             List<int[]> a = Arrays.asList(utils.Constants.SUMMER_MONTHES);
//             if(a.contains(flight.getFlightDateAndTimeSource().getMonth()+1))
//             return true;*/
//        }
//
//        return false;
//    }
//
//    /**
//     * this function gets to flights and checks whether they overlaps
//     *
//     * @param a - the first flight
//     * @param b - the second flight
//     * @return true/false - true if the flight overlaps ; false otherwise.
//     */
//    public static boolean isFlightsOverlap(Flight a, Flight b) {
//
//        Date startA = a.getFlightDateAndTimeSource();
//        Date endA = a.getFlightDateAndTimeDestination();
//        Date startB = b.getFlightDateAndTimeSource();
//        Date endB = b.getFlightDateAndTimeDestination();
//
//        if (startA.compareTo(startB) * startB.compareTo(endA) > 0) {
//            return true;
//        }
//
//        if (startB.compareTo(startA) * startA.compareTo(endB) > 0) {
//            return true;
//        }
//
//        return false;
//    }
//
//    /**
//     * check credentials of user , by id , and user type
//     * @param UserType user yupe
//     * @param id the id to check
//     * @param thePass the password to check
//     * @return AbstractMap.SimpleEntry represnt the user information and key
//     */
//    public AbstractMap.SimpleEntry getUserObject(String UserType, String id, char[] thePass) {
//
//        if (UserType == null || id == null || thePass == null) {
//            return null;
//        }
//        String tempPass;
//        String tempName;
//        String currectpass;
//
//        StringBuilder builder = new StringBuilder();
//        for (char s : thePass) {
//            builder.append(s);
//        }
//        currectpass = builder.toString();
//
//        if (UserType != null) {
//            if (UserType.equals("Agent") || UserType.equals("Admin")) {
//                for (Entry<Integer, Employee> emp : employees.entrySet()) {
//                    if (emp.getValue() instanceof Agent) {
//                        tempPass = ((Agent) emp.getValue()).getPassword();
//                        //tempName = ((Agent) emp.getValue()).getFirstName();
//                        if (tempPass.length() == currectpass.length()) {
//                            if (tempPass.equals(currectpass) && emp.getKey().equals(Integer.parseInt(id))) {
//                                return new AbstractMap.SimpleEntry(emp.getKey(), emp.getValue());
//                            }
//                        }
//                    }
//                }
//            } else if (UserType.equals("Customer") || UserType.equals("Admin")) {
//                for (Entry<String, Customer> cust : customers.entrySet()) {
//
//                    tempPass = cust.getValue().getPassword();
//                    //tempName = cust.getValue().getFirstName();
//                    if (cust.getKey().length() == id.length() && tempPass.length() == currectpass.length()) {
//                        if (tempPass.equals(currectpass) && cust.getKey().equals(id)) {
//                            return new AbstractMap.SimpleEntry(cust.getKey(), cust.getValue());
//
//                        }
//                    }
//
//                }
//            }
//
//        }
//        return null;
//    }
//
//    /** get order of given owner , {@link core.Customer} or {@link core.Agent} 
//     * @param key the owner id
//     * @return an abstract entry represent the key of the owner, and arrylist of its orders
//     */
//    public AbstractMap.SimpleEntry getOrdersforOwner(Object key) {
//
//        AbstractMap.SimpleEntry<Object, ArrayList<Order>> a;
//
//        Comparator<Order> empComp = new Comparator<Order>() {
//
//            @Override
//            public int compare(Order o1, Order o2) {
//                // TODO Auto-generated method stub
//                return o1.getOrderNumber()
//                        - o2.getOrderNumber();
//
//            }
//        };
//
//        if (key instanceof String) {
//            for (Entry<String, Customer> entry : customers.entrySet()) {
//                if (entry.getKey().equals(key)) {
//                    ArrayList<Order> al = new ArrayList<Order>();
//                    al.addAll(entry.getValue().getOrders());
//                    Collections.sort(al, empComp);
//                    a = new AbstractMap.SimpleEntry(entry.getValue(), al);
//                    return a;
//                }
//            }
//        } else {
//
//            for (Entry<Integer, Employee> entry : employees.entrySet()) {
//                if (entry.getValue() instanceof Agent) {
//                    if (entry.getKey().equals(key)) {
//                        Agent agent = (Agent) entry.getValue();
//                        ArrayList<Order> al = new ArrayList<Order>();
//                        al.addAll(agent.getOrders());
//                        Collections.sort(al, empComp);
//                        a = new AbstractMap.SimpleEntry(entry.getValue(), al);
//                        return a;
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    /** get all order of all {@link #customers}
//     * @return List<Order> of all orders
//     */
//    public List<Order> getAllIrders() {
//        List<Order> list = new ArrayList<>();
//        for (Entry<String, Customer> entry : customers.entrySet()) {
//
//            Customer customer = entry.getValue();
//            if (!customer.getOrders().isEmpty()) {
//                for (Order order : customer.getOrders()) {
//                    list.add(order);
//                }
//            }
//
//        }
//
//        return list;
//    }
//
//    /**
//     * get a brach by id
//     * @param agentId the id
//     * @return the branch
//     */
//    public Branch getBranchByAgentID(int agentId) {
//
//        for (Map.Entry<Integer, Employee> emp : employees.entrySet()) {
//            if (emp.getValue() instanceof Agent) {
//                if (emp.getValue().getEmployeeNumber() == agentId) {
//                    Branch b = ((Agent) emp.getValue()).getWorkBranch();
//                    return b;
//                }
//
//            }
//        }
//        return null;
//    }
//
//    /**
//     * get all flights which matches the dates
//     * @param source the source airport
//     * @param destination the s=destination airport
//     * @param fromDate the min' date to filter
//     * @param toDate the max date to filter
//     * @return a map of flights
//     */
//    public Map<Integer, Flight> getSearchedFlights(E_Airports source, E_Airports destination, Date fromDate, Date toDate) {
//
//        Map<Integer, Flight> result = new HashMap<>();
//
//        for (Entry<Integer, Flight> entry : flights.entrySet()) {
//            Integer integer = entry.getKey();
//            Flight flight = entry.getValue();
//            if ((flight.getSource().equals(source) && flight.getDestination().equals(destination))) {
//                if (fromDate != null && toDate != null) {
//                    if ((fromDate.before(flight.getFlightDateAndTimeSource())
//                            || fromDate.equals(flight.getFlightDateAndTimeSource()))
//                            && (flight.getFlightDateAndTimeDestination().before(toDate)
//                            || flight.getFlightDateAndTimeDestination().equals(toDate))) {
//                        result.put(integer, flight);
//                    }
//                } else {
//                    result.put(integer, flight);
//                }
//            }
//
//        }
//        return result;
//    }
//
//    /**
//     * get all flight included in the order
//     * @param ordeNumber the order number
//     * @return a map of flights
//     */
//    public Map<Integer, Flight> getFlightsForSelectedOrder(int ordeNumber) {
//        Map<Integer, Flight> result = new HashMap<>();
//
//        for (Entry<Integer, Flight> entry : flights.entrySet()) {
//            Integer integer = entry.getKey();
//            Flight flight = entry.getValue();
//
//            for (FlightOrder fo : flight.getFlightOrders()) {
//                if (fo.getOrder().getOrderNumber() == ordeNumber) {
//                    result.put(integer, flight);
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * get all passanger for flight
//     * @param flightNum flight numer
//     * @param filter if true , get all customers which aren't  on the flight, otherswise fill all customers
//     * @return a map of customers
//     */
//    public Map<String, Customer> getPassangersForFlightOrder(int flightNum, boolean filter) {
//        Map<String, Customer> passangers = new HashMap<String, Customer>();
//        Map<String, Customer> customersTemp = new HashMap<String, Customer>(customers);
//
//        Flight flight = flights.get(flightNum);
//
//        for (FlightOrder flightOrder : flight.getFlightOrders()) {
//            for (FlightTicket flightTicket : flightOrder.getTickets()) {
//                for (Entry<String, Customer> custEntry : customers.entrySet()) {
//                    String string = custEntry.getKey();
//                    Customer customer = custEntry.getValue();
//                    if (flightTicket.getPassenger() != null && flightTicket.getPassenger().equals(customer)) {
//                        passangers.put(string, customer);
//                        break;
//                    }
//                }
//            }
//        }
//
//        //get customer which are not on the flight order. otherwise get only passangers in the flightorder if any
//        if (filter && passangers.size() > 0) {
//            customersTemp.keySet().removeAll(passangers.keySet());
//            return customersTemp;
//        } else {
//            return customersTemp;
//        }
//
//    }
//
//    /**
//     * check if a ticket with the given seat and row is reserved in the given class within the given flight
//     * @param flightNum the flight numer
//     * @param clazz the class type
//     * @param row the row number
//     * @param seat he seat numer
//     * @return if the seat is reserved return the ticket, otherwise, return a new empty ticket
//     */
//    public FlightTicket isTicketReservedInFlight(int flightNum, E_ClassType clazz, int row, int seat) {
//
//        Flight flight = flights.get(flightNum);
//
//        //No flight to check
//        if (flight == null) {
//            return null;
//        }
//
//        for (FlightOrder flightOrder : flight.getFlightOrders()) {
//            for (FlightTicket flightTicket : flightOrder.getTickets()) {
//                if (flightTicket.getClassType().equals(clazz) && flightTicket.getRow() == row && flightTicket.getSeat() == seat) {
//                    //ticket reserved
//                    if (flightTicket.getPassenger() != null) {
//                        return flightTicket;
//                    }
//                }
//            }
//        }
//
//        //ticket is not reserved
//        FlightOrder.FlightTicket tf = new FlightOrder(null, null).new FlightTicket(-1, -1);
//
//        return tf;
//
//    }
//
//    /**
//     * @param number , if given , exclude this airplane
//     * @return a list of airplanes
//     */
//    public ArrayList<Airplane> getAirplaines(Integer number) {
//
//        ArrayList<Airplane> res = new ArrayList<>();
//
//        for (Entry<Integer, Flight> entry : flights.entrySet()) {
//
//            Flight flight = entry.getValue();
//
//            if (number != null) {
//                if (number.equals(flight.getAirplane().getAirplaneNumber())) {
//                    res.add(flight.getAirplane());
//                    return res;
//                }
//
//            } else {
//            	if(!res.contains(flight.getAirplane()))
//                res.add(flight.getAirplane());
//            }
//        }
//
//        return res;
//    }
//
//    /**
//     * get all tickets of the customer
//     * @param customerId
//     * @return a list of tickets
//     */
//    public ArrayList<Object[]> getTicketsInfoCustomer(int customerId) {
//        ArrayList<Object[]> result = new ArrayList<>();
//        DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
//        for (Entry<Integer, Flight> entry : flights.entrySet()) {
//            Integer integer = entry.getKey();
//            Flight flight = entry.getValue();
//            for (FlightOrder flightOrder : flight.getFlightOrders()) {
//                for (FlightTicket flightTicket : flightOrder.getTickets()) {
//                    if (flightTicket.getPassenger().getPassportNumber() == customerId) {
//                        result.add(
//                                new Object[]{
//                                    flightOrder.getOrder().getOrderNumber(),
//                                    flight.getFlightNumber(),
//                                    flight.getSource().getCity(),
//                                    flight.getDestination().getCity(),
//                                    outputFormatter.format(flight.getFlightDateAndTimeSource()),
//                                    outputFormatter.format(flight.getFlightDateAndTimeDestination()),
//                                    flightTicket.getRow(),
//                                    flightTicket.getSeat(),
//                                    flightTicket.getClassType().name(),
//                                    String.format("%.2f", flight.getFlightCost() * flightTicket.getClassType().getFee())
//                                }
//                        );
//                    }
//                }
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * get the order by the given id
//     * @param orderID
//     * @return {@link core.Order}
//     */
//    public Order getOrderById(int orderID) {
//
//        for (Entry<String, Customer> entry : customers.entrySet()) {
//            String string = entry.getKey();
//            Customer customer = entry.getValue();
//            for (Order order : customer.getOrders()) {
//                if (order.getOrderNumber() == orderID) {
//                    return order;
//                }
//            }
//        }
//
//        return null;
//    }
//    
//    /**
//     * reset the data in case user doesn't save changes in a new instance of the class
//     */
//    public void resetNewIfly(){
//        employees = new HashMap<Integer, Employee>();
//        branches = new HashMap<Integer, Branch>();
//        customers = new HashMap<String, Customer>();
//        flights = new HashMap<Integer, Flight>();
//    }
////</editor-fold>
//}// ~ END OF Class IFly
//
