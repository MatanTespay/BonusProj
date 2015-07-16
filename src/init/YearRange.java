/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import exceptions.YearRangeException;

/**
 *
 * @author asus
 */
public class YearRange {
    private int from;
    private int to;
    
    public YearRange (int from, int to) throws YearRangeException{
        if (from < 1863 || from > to)
            throw new YearRangeException();            
    }
    /**
     * @return the from
     */
    public int getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public int getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(int to) {
        this.to = to;
    }
}
