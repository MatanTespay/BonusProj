package utils;

/**
 * Class Constants ~ all constants necessary for the system
 *
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public final class Constants {

    /**
     * All summer months month
     */
    public final static int[] SUMMER_MONTHES = {7, 8, 9};

    /**
     * Step types
     */
    public final static String[] STEP_TYPES = {"Source", "Stopover", "Destination"};

//	/**
//	 * The maximum of phone numbers for a single entity
//	 */
//	public static final int MAX_OF_PHONE_NUMBERS = 2;
    /**
     * The tax
     */
    public static final double TAX = 0.13;

    /**
     * The maximum number of pilots in a single flight
     */
    public static final int MAX_NUMBER_OF_PILOTS = 2;

    /**
     * The standard size of a passport number
     */
    public static final int PASSPORT_NUMBER_SIZE = 8;

    public static final String[] PHONE_PREFIX = {"02", "04", "08", "09", "050", "052", "053", "054", "057", "058", "077", "073"};

    public static final String EMAIL_REGEX = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b";

    public static final String saveButtonToolTip = "<html><b>The changes will be pending to the system<br>"
            + "Choose \'save\' from the menu-bar (File>save)<br>"
            + "To confirm changes.</html>";

    public static boolean ADD_MODE = false;
    
    public static boolean EDIT_MODE = true;
    
    public static boolean PAPER = false;
    
    public static boolean OYSTER = true;
    
    public static boolean OVERGROUND = false;
    
    public static boolean UNDERGROUND = true;
    
    public static boolean INGOING = false;
    
    public static boolean OUTGOING = true;

}// ~ END OF Class Constants
