package utils;

/**
 * Interface Rating ~ for calculatin rating or score of
 * an Agents and Customers
 * @author Java Course Team 2014
 * @author University Of Haifa-Israel
 */
public interface Rating {

	/**
	 * @return the entity's primary key
	 */
	public int getPrimaryKey();
	
	/**
	 * Calculate the rating of this entity
	 */
	public double calcRating();
	
}// ~ END OF Interface Rating
