package guichet.transaction;

import compte.InformationCompte;
import compte.Carte;
import compte.Message;
import compte.Argent;
import compte.Recu;
import compte.Etat;
import guichet.Guichet;
import guichet.Session;
import guichet.physique.*;


/** Representation d'une transaction de paiement de facture
 */
public class Facture extends Transaction
{
	
	 /** Constructor
    *
    *  @param atm le guichet utilise pour communiquer avec le client
    *  @param session la session dans laquelle la transaction est realisee
    *  @param card la carte client
    *  @param pin le NIP entre par le client
    */
    public Facture(Guichet atm, Session session, Carte card, int pin)
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
    	numBill = atm.getCustomerConsole().readBillNumber("Numero de la facture a payer");
    	
    	amount = atm.getCustomerConsole().readAmount("Montant de la facture a payer");
    	
    	from = atm.getCustomerConsole().readMenuChoice(
    			"Compte a partir duquel on paie",
                InformationCompte.ACCOUNT_NAMES);
    	
    	return new Message(Message.FACTURE, 
                            card, pin, serialNumber, from, numBill, amount);
    }
    
    
    protected Recu completeTransaction()
    {
        //atm.getCashDispenser().dispenseCash(amount);
        return new Recu(this.atm, this.card, this, this.balances) {
            {
                detailsPortion = new String[3];
                detailsPortion[0] = "PAIEMENT À PARTIR DE: " + 
                                    InformationCompte.ACCOUNT_ABBREVIATIONS[from];
                detailsPortion[1] = "Montant: " + amount.toString();
                detailsPortion[2] = "Numero de la facture: " + numBill;
            }
        };
    }
    
    
    /** Compte duquel on paie
     */ 
    private int from;
    
    /** Montant d'argent de la facture
     */
    private Argent amount;     
    
    /** Numéro de la facture qu'on paye
     */
    private int numBill;
}
