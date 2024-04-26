package project;
import java.util.Scanner;

/**
 * The StaffInterface class provides methods to handle staff interactions with the system.
 */
public class StaffInterface {
	
	/**
     * Handles staff operations based on user input.
     *
     * @param branch The branch associated with the staff member.
     * @param staff  The staff member interacting with the system.
     * @param sc     The Scanner object for input.
     */
  public static void handler(Branch branch, Staff staff, Scanner sc){
    boolean exit = false;
    System.out.println("\nWelcome, " + staff.getName() + "!\n");
    while (!exit) {
      System.out.println("1. Change Password\n" +
         "2. Display New Orders\n" +
         "3. View Details of a particular order\n" +
         "4. Process Order\n" +
         "0. Back\n" +
         "Enter your choice: ");
      int id, choice = sc.nextInt();
      sc.nextLine();
      switch (choice) {
        case 1:
          System.out.println("Enter new password:");
          String newPassword = sc.nextLine();
          staff.setPassword(newPassword);
          break;
        case 2:
          branch.displayNewOrder();
          break;
        case 3:
          System.out.println("Enter the orderId: ");
          id = sc.nextInt();
          if(branch.getOrderById(id)!=null)
            branch.displayOrderDetails(id);
          else System.out.println("OrderId "+ id +" is not found");
          break;
        case 4:
          System.out.println("Enter the orderId: ");
          id = sc.nextInt();
          if(branch.getOrderById(id)!=null){
            branch.processOrder(id);
            System.out.println("Order status updated.");
          }
          else System.out.println("OrderId "+ id +" is not found");
          break;
        case 0:
          System.out.println("Logging out. Goodbye!\n");
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please enter a number between 0 and 4.\n");
      }
    }
  }
}