package compte;/** Representations d'un message du guichet vers la banque. *  les messages vers la banque utilisent un format fixe avec des slots fixes, chaque transaction un ceratin nomre d'entre eux. *  une transaction cree une ou plusieurs sous-classes en utilisant les slots dont elle a besoin, et etiquettant les slots non-utilises 'pas besoin'. */public class Message{    /** Constructor     *     *  @param messageCode identifie le type du message     *  @param card la carte du client     *  @param pin Le PIN entre par le client     *  @param serialNumber le numero de serie de la transaction     *  @param fromAccount le type du compte source de la transaction - peut etre -1 si      *  	   la trasaction ne necessite pas de compte source (ex. depot)     *  @param toAccount le type du compte destination de la transaction - peut etre -1 si      *  	   la trasaction ne necessite pas de compte destination (ex. retrait)     *  @param amount le montant de la transaction - peut etre null      *  	   si la transaction n'a pas de montant (ex. une demande de solde)     */    public Message(int messageCode, Carte card, int pin,                    int serialNumber, int fromAccount, int toAccount, Argent amount)    {        this.messageCode = messageCode;        this.card = card;        this.pin = pin;        this.serialNumber = serialNumber;        this.fromAccount = fromAccount;        this.toAccount = toAccount;        this.amount = amount;    }        /** CCree une representation string imprimable du message     *     *  @return representation string      */    public String toString()    {        String result = "";                switch (messageCode)        {            case WITHDRAWAL:                            result += "RETRAIT";                break;                            case INITIATE_DEPOSIT:                            result += "INIT_DEP";                break;                            case COMPLETE_DEPOSIT:                            result += "COMP_DEP";                break;                            case TRANSFER:                            result += "TRANSFERT";                break;                            case INQUIRY:                            result += "DEMANDE ";                break;                            case FACTURE:            	            	result += "PAIE_FACTURE";            	break;        }                result += " #CARTE " + card.getNumber();        result += " #TRANS " + serialNumber;        if (fromAccount >= 0)            result += " SRC  " + fromAccount;        else            result += " PAS SRC";        if (toAccount >= 0)            result += " DEST  " + toAccount;        else            result += " PAS DEST";        if (! amount.lessEqual(new Argent(0)))            result += " " + amount;        else            result += " PAS MONTANT";                return result;    }                     /** Met le PIN a une nouvelle valeur     *     *  @param pin Le nouveau PIN a mettre     */    public void setPIN(int pin)    {        this.pin = pin;    }        /** Accessor du code du message     *     *  @return le code identifiant le type du message     */    public int getMessageCode()    {        return messageCode;    }        /** Accessor de la carte client     *     *  @return la carte client     */    public Carte getCard()    {        return card;    }        /** Accessor du PIN     *     *  @return le PIN entre par le client     */    public int getPIN()    {        return pin;    }        /** Accessor du numero de serie de la transaction     *     *  @return le numero de serie de la transaction     */    public int getSerialNumber()    {        return serialNumber;    }        /** Accessor du compte "source"      *     *  @return le type du compte "source"      */    public int getFromAccount()    {        return fromAccount;    }        /** Accessor du comtpe destination     *     *  @return le type du comtpe destination     */    public int getToAccount()    {        return toAccount;    }        /** Accessor du montant     *     *  @return le montant de la transaction     */    public Argent getAmount()    {        return amount;    }        // Valeurs possibles du code messageCode        /** valeur messageCode pour message de retrait     */     public static final int WITHDRAWAL = 0;        /** valeur messageCode pour message d'initiation de depot     */     public static final int INITIATE_DEPOSIT = 1;        /** valeur messageCode pour message de completion de depot      */     public static final int COMPLETE_DEPOSIT = 2;        /** valeur messageCode pour message de trandfer entre compte     */     public static final int TRANSFER = 3;        /** valeur messageCode pour message de demande de solde     */     public static final int INQUIRY = 4;        /** valeur messageCode pour message de paiement de facture     */     public static final int FACTURE = 5;    // Slots du message - certains peuvent ne pas etre utilises    /** Code identifiant le type du message - une des valeurs ci-haut     */    private int messageCode;        /** La carte client     */    private Carte card;        /** PIN entre par le client     */    private int pin;        /** numero de serie de la transaction     */    private int serialNumber;        /** compte source - si besoin (sinon - 1)     */    private int fromAccount;        /** compte destination - si besoin (sinon - 1)     */    private int toAccount;        /** montant de la transaction - si besoin (sinon $0.00)     */    private Argent amount;}