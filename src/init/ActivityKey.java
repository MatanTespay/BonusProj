/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class ActivityKey {

    private Card card;
    private Date activityDate;

    public ActivityKey(Card card, Date activityDate) {
        this.card = card;
        this.activityDate = activityDate;
    }
    
     public ActivityKey(int cardNumber, Date purchaseDate, Date activityDate) {
        this (new Card (cardNumber, purchaseDate),activityDate);
        
    }

    /**
     * @return the card
     */
    public Card getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * @return the activityDate
     */
    public Date getActivityDate() {
        return activityDate;
    }

    /**
     * @param activityDate the activityDate to set
     */
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}
