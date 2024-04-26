package project;

/**
 * The PaymentMethod class represents a method of payment, such as Credit Card or Online Payment.
 */
public class PaymentMethod {
  
    private String name;
  
    /**
     * Constructs a PaymentMethod object with the specified name.
     *
     * @param name The name of the payment method.
     */
    public PaymentMethod(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name of the payment method.
     *
     * @return The name of the payment method.
     */
    public String getName() {
        return name;
    }
}

	/**
	 * The CreditCard class represents a credit card payment method.
	 */
	class CreditCard extends PaymentMethod {
	    private String cardNumber;
	    private String expiryDate;
	    private String securityCode;

    /**
     * Constructs a CreditCard object with the specified card number, expiry date, and security code.
     *
     * @param cardNumber   The credit card number.
     * @param expiryDate   The expiry date of the credit card.
     * @param securityCode The security code of the credit card.
     */
    public CreditCard(String cardNumber, String expiryDate, String securityCode) {
        super("Credit Card");
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
    }
}

	/**
	 * The OnlinePayment class represents an online payment method.
	 */
class OnlinePayment extends PaymentMethod {
    private String platform;
    private String username;
    private String password;

    /**
     * Constructs an OnlinePayment object with the specified platform, username, and password.
     *
     * @param platform The online payment platform.
     * @param username The username for the online payment.
     * @param password The password for the online payment.
     */
    public OnlinePayment(String platform, String username, String password) {
        super("Online Payment");
        this.platform = platform;
        this.username = username;
        this.password = password;
    }
}

