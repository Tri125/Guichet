package simulation;

import java.awt.TextField;


/** Le nombre de billet pour chaque denominations
 * Classe pour lier GUI et PanneauBillets avec le rajout des billets de 5$
 */
class Billets 
{
	
    /** Constructor.
    *
    *  @param billets20 Le nombre de billets de 20$
    *  @param billets5  Le nombre de billets de 5$
    */
	Billets(int billets20, int billets5)
	{
		this.billets20 = billets20;
		this.billets5  = billets5;
	}
	
	public int getBillets20()
	{
		return billets20;
	}
	
	public int getBillets5()
	{
		return billets5;
	}
	
    /** Les attributs dans lequels le nombre de billets doit etre entre
     */
    private int billets20;
    
    private int billets5;
	

}
