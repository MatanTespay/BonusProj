package core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import utils.E_ClassType;

/**
 * Class FlightOrder ~ represents a single flight associated with a single order
 *
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public class FlightOrder implements Serializable {

    private static final long serialVersionUID = -7723474905427359162L;
    //-------------------------------Class Members------------------------------
    /**
     * The flight - one of two keys
     */
    private Flight flight;
    /**
     * The flight - one of two keys
     */
    private Order order;
    /**
     * all tickets in this flight order
     */
    private Set<FlightTicket> tickets;

    /**
     * The total amount of this flight order ==> number of passengers * flight
     * cost
     */
    private double totalAmount;

    //-------------------------------Constructors-----------------------------
    /**
     * Full Constructor ~ use for initial all fields
     *
     * @param flight
     * @param order
     */
    public FlightOrder(Flight flight, Order order) {
        super();
        this.flight = flight;
        this.order = order;
        this.tickets = new HashSet<FlightTicket>();
    }

    //-------------------------------Getters And Setters------------------------------
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
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the totalAmount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the tickets
     */
    public Set<FlightTicket> getTickets() {
        return Collections.unmodifiableSet(tickets);
    }

    //-------------------------------More Methods------------------------------
    /**
     * The role of this method is to add a new ticket to the tickets set You
     * need to check if the specific place {seat and row} (not by equals method)
     * is available in this flight by checking all the flight tickets associates
     * with this flight. Do not forget calculate the total amount Please be
     * aware for the return type of this method, you should use it in IFly
     * class!
     *
     * @param passanger
     * @param seat
     * @param row
     * @param classType
     * @return the flight ticket object if the ticket added successfully or null
     * otherwise
     */
    public FlightTicket addTicket(Customer passanger, int seat, int row, String classType, int flightNum, int orderNum) {
        //TODO - Complete this method
        for (Iterator<FlightTicket> iterator = this.tickets.iterator(); iterator.hasNext();) {
            FlightTicket t = iterator.next();
            if (t.passenger.equals(passanger)) {
                return null;
            }

            if (t.seat == seat && t.row == row && t.getClassType().equals(E_ClassType.valueOf(classType))) {
                return null;
            }
        }

        FlightTicket ft = new FlightTicket(passanger, seat, row, classType, flightNum, orderNum);

        if (this.tickets.add(ft)) {
            calcTotalAmount();
            return ft;
        }

        return null;
    }// ~ END OF addPassanger

    /**
     * The role of this method is to remove a ticket from the tickets set do not
     * forget calculate the total amount
     *
     * @param ticket
     * @return true if the ticket remove successfully or false otherwise
     */
    public boolean removeTicket(FlightTicket ticket) {
        //TODO - Complete this method
        if (ticket != null && this.tickets.contains(ticket)) {
            if (this.tickets.remove(ticket)) {
                calcTotalAmount();
                return true;
            }
        }

        return false;
    }// ~ END OF removePassanger

    /**
     * This method calculate the total amount of this flight order. total amount
     * = (flight cost* ticket fee)* number of tickets
     */
    private void calcTotalAmount() {
        //TODO - Complete this method
        double total = 0;
        for (FlightTicket lightTicket : this.tickets) {
            total += (this.flight.getFlightCost() * lightTicket.classType.getFee());
        }
        this.totalAmount = total;

    }// ~ END OF calcTotalAmount

    //-------------------------------hashCode equals & toString------------------------------
    //TODO - Add more methods 

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FlightOrder [flight=" + flight.getFlightNumber() + ", order=" + order
                + ", totalAmount=" + totalAmount
                + ", flight tickets=" + tickets + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((flight == null) ? 0 : flight.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FlightOrder other = (FlightOrder) obj;
        if (flight == null) {
            if (other.flight != null) {
                return false;
            }
        } else if (!flight.equals(other.flight)) {
            return false;
        }
        if (order == null) {
            if (other.order != null) {
                return false;
            }
        } else if (!order.equals(other.order)) {
            return false;
        }
        return true;
    }

    //==========================================================================================
    // Inner class ~ FlightTicket 
    //==========================================================================================
    /**
     * Class FlightTicket ~ represents private inner class in FlightOrder with
     * details for each ticket.
     *
     * @author Java Course Team 2014
     * @author University Of Haifa-Israel
     *
     */
    public class FlightTicket implements Serializable {

        private static final long serialVersionUID = -5587663877071472169L;
        /**
         * The passenger for this ticket
         */
        private Customer passenger;
        /**
         * passenger's seat
         */
        private int seat;
        /**
         * passenger's row
         */
        private int row;
        /**
         * The passenger's class in the plane
         */
        private E_ClassType classType;

        private Integer flightNumber;
        private Integer orderNumber;

        /**
         * Full constructor
         *
         * @param passenger
         * @param seat
         * @param row
         * @param classType
         */
        public FlightTicket(Customer passenger, int seat, int row, String classType) {
            this.passenger = passenger;
            setSeat(seat);
            setRow(row);
            setClassType(classType);
        }

        public FlightTicket(Customer passenger, int seat, int row, String classType, int flightNum, int orderNum) {
            this.passenger = passenger;
            setSeat(seat);
            setRow(row);
            setClassType(classType);
            this.flightNumber = flightNum;
            this.orderNumber = orderNum;
        }

        /**
         * Partial constructor
         *
         * @param seat
         * @param row
         */
        public FlightTicket(int seat, int row) {
            setSeat(seat);
            setRow(row);
        }

        //-------------------------------Getters And Setters------------------------------
        /**
         * seat has to be positive number
         *
         * @param seat
         */
        public void setSeat(int seat) {
            if (seat > 0) {
                this.seat = seat;
            }
        }

        /**
         * row has to be positive number
         *
         * @param row
         */
        public void setRow(int row) {
            if (row > 0) {
                this.row = row;
            }
        }

        public int getSeat() {
            return seat;
        }

        public int getRow() {
            return row;
        }

        public Customer getPassenger() {
            return passenger;
        }

        public Integer getFlightNumber() {
            return flightNumber;
        }

        public Integer getOrderNumber() {
            return orderNumber;
        }

        public double getSeatCost()
        {
            if(this.getOuterType().getFlight() != null && getClassType() != null)
            return this.getOuterType().getFlight().getFlightCost()* getClassType().getFee();
            else
                return 0;
        }
        
        /**
         *
         * @return classType
         */
        public E_ClassType getClassType() {
            return classType;
        }

        /**
         * set the class type from the array classType located in Constants. if
         * the classType not exists, the default value is Economy.
         *
         * @param classType
         */
        public void setClassType(String classType) {

            for (E_ClassType c : E_ClassType.values()) {
                if (c.toString().equals(classType)) {
                    this.classType = E_ClassType.valueOf(classType);
                    return;
                }
            }
            this.classType = E_ClassType.Tourists;

        }

        private FlightOrder getOuterType() {
            return FlightOrder.this;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            //		result = prime * result + getOuterType().hashCode();
            result = prime * result
                    + ((orderNumber == null) ? 0 : orderNumber.hashCode());
            result = prime * result
                    + ((flightNumber == null) ? 0 : flightNumber.hashCode());
            result = prime * result + row;
            result = prime * result + seat;
            result = prime * result + ((classType == null) ? 0 : classType.hashCode());
            return result;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            FlightTicket other = (FlightTicket) obj;
            if (orderNumber == null) {
                if (other.orderNumber != null) {
                    return false;
                }
            } else if (!orderNumber.equals(other.orderNumber)) {
                return false;
            }
            if (flightNumber == null) {
                if (other.flightNumber != null) {
                    return false;
                }
            } else if (!flightNumber.equals(other.flightNumber)) {
                return false;
            }
            if (row != other.row) {
                return false;
            }
            if (seat != other.seat) {
                return false;
            }
            if (classType != other.classType) {
                return false;
            }
            return true;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "FlightTicket [passenger=" + passenger + ", seat=" + seat
                    + ", row=" + row + ", flight number=" + getFlight().getFlightNumber() + "]";
        }

    }// ~ END OF Inner Class FlightTicket

}// ~ END OF Class FlightOrder
