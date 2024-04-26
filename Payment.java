package project;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Payment class manages payment methods and facilitates payments for orders.
 */
public class Payment{
	
 /**
 * List to store available payment methods.
 */
  public static List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

  /**
   * Constructs a Payment object and initializes the payment methods.
   */
  public Payment (){
    paymentMethods.add(new PaymentMethod("Credit Card"));
    paymentMethods.add(new PaymentMethod("Online Payment"));
  }

  /**
   * Adds a new payment method to the list of available payment methods.
   *
   * @param method The payment method to be added.
   */
  public static void addPaymentMethod(PaymentMethod method) {
      if (!paymentMethods.contains(method)) {
            paymentMethods.add(method);
          System.out.println("Payment method '" + method.getName() + "' added successfully.\n");
      } else {
          System.out.println("Payment method '" + method.getName() + "' already exists.\n");
      }
  }
  
  /**
   * Removes a payment method from the list of available payment methods.
   *
   * @param method The payment method to be removed.
   */
  public static void removePaymentMethod(PaymentMethod method) {
      if (paymentMethods.contains(method)) {
            paymentMethods.remove(method);
          System.out.println("Payment method '" + method.getName() + "' deleted successfully.\n");
      } else {
          System.out.println("Payment method '" + method.getName() + "' does not exist.\n");
      }
  }
  
  /**
   * Retrieves a payment method by its index in the list.
   *
   * @param id The index of the payment method.
   * @return The payment method at the specified index, or null if not found.
   */
  public static PaymentMethod getPaymentMethod(int id){
    int i=0;
    for (PaymentMethod method : paymentMethods){
      if(i==id) return method;
      i++;
    }
    return null;
  }

  /**
   * Makes a payment with the specified total amount using the selected payment method.
   *
   * @param total The total amount of the payment.
   * @param sc    The Scanner object for user input.
   * @return True if the payment is successful, false otherwise.
   */
  public static boolean MakePayment(double total, Scanner sc){
    System.out.printf("Total Amount: %.2f\n" , total); 
    if (selectPaymentMethod(sc)){
      System.out.println("Payment successful!");
      return true;
    }
    else 
      return false; 
  }

  /**
   * Displays the available payment methods.
   */
  public static void displayPaymentMethods() {
      int i=1;
      System.out.println("Payment Methods:");
      for (PaymentMethod method : paymentMethods){
        System.out.printf("%d. %s%n", i, method.getName());
        i++;
      }
      System.out.println();
  }

  /**
   * Allows the user to select a payment method from the available options.
   *
   * @param sc The Scanner object for user input.
   * @return True if a payment method is selected successfully, false otherwise.
   */
public static boolean selectPaymentMethod(Scanner sc){
  displayPaymentMethods();
  System.out.println("Enter payment method:");
  int id = sc.nextInt();
  PaymentMethod selectedMethod = getPaymentMethod(id-1);
  sc.nextLine();
  if(selectedMethod != null){
    switch(selectedMethod.getName()){
      case "Credit Card":
        System.out.println("Enter credit card number:");
        String cardNumber = sc.nextLine();
        System.out.println("Enter credit card expiry date (MM/YY):");
        String expiryDate = sc.nextLine();
        System.out.println("Enter credit card CVV:");
        String cvv = sc.nextLine();
        CreditCard card = new CreditCard(cardNumber, expiryDate, cvv);
        break;
      case "Online Payment":
        System.out.println("Enter Platform:");
        String platform = sc.nextLine();
        System.out.println("Enter Username:");
        String username = sc.nextLine();
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        OnlinePayment onlinePayment = new OnlinePayment(platform, username, password);
        break; 
    }
    return true;
  }
  else
    return false;
}
}
