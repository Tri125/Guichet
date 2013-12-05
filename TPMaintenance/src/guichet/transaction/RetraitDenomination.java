package guichet.transaction;

import guichet.Guichet;
import guichet.Session;
import guichet.physique.ConsoleClient;
import compte.Argent;
import compte.Carte;
import compte.InformationCompte;
import compte.Message;



/** Representation d'une transaction de retrait de denomination de billets
 */
public class RetraitDenomination extends Retrait
{
	
	 /** Constructor
    *
    *  @param atm le guichet utilise pour communiquer avec le client
    *  @param session la session dans laquelle la transaction est realisee
    *  @param card la carte client
    *  @param pin le NIP entre par le client
    */
	public RetraitDenomination(Guichet atm, Session session, Carte card, int pin) 
	{
		super(atm, session, card, pin);
	}
	
	/** Recupere des details de la transaction avec le client
    *
    *  @return message a la banque pour initier cette transaction 
    *  @exception ConsoleClient.Cancelled si le client annule la transaction
    */
    protected Message getSpecificsFromCustomer() throws ConsoleClient.Cancelled
    {
        from = atm.getCustomerConsole().readMenuChoice(
            "Compte a partir duquel on retire",
            InformationCompte.ACCOUNT_NAMES);
                     
        String amountMessage = "";
        boolean validAmount = false;
        
        while (! validAmount)
        {
        	int multiple = atm.getCustomerConsole().readBillNumber(
        			 amountMessage + "Multiples de " + DENOMINATEUR + "$");  
        	
        	amount = new Argent(multiple * DENOMINATEUR);
            validAmount = atm.getCashDispenser().checkCashOnHand(amount);

            if (! validAmount)
                amountMessage = "Fonds insuffisants\n";
        }
        
        return new Message(Message.WITHDRAWAL, 
                           card, pin, serialNumber, from, -1, amount);

    }
	
	
    /** Le denominateur auquel on retire de l'argent
     */ 
    private final int DENOMINATEUR = 5;

}
