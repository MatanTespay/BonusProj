package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConvertUtil {

    // List of all date formats that we want to parse.
    // Add your own format here.
    private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
        private static final long serialVersionUID = 1L;

        {
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy")); //2014-06-08 00:00:00
            add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); //2014-06-08 00:00:00
        }
    };

    /**
     * Convert String with various formats into java.util.Date
     *
     * @param input Date as a string
     * @return java.util.Date object if input string is parsed successfully else
     * returns null
     */
    public static Date convertToDate(String input) {
        Date date = null;
        if (null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                //Shhh.. try other formats
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }

    public static Integer convertToInt(String input) {
      
        Integer i = null;
        if (null == input) {
            return null;
        }
        try {
            i = Integer.parseInt(input);
            return i;
        } catch (NumberFormatException ex) {
            return null;
        }

    }

    public static Double convertToDouble(String input) {
        Double d = null;
        if (null == input) {
            return null;
        }

        for (SimpleDateFormat format : dateFormats) {

            try {
                d = Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                 
            }

        }

        return d;
    }
}
