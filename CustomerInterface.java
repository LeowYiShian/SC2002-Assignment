package project;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.List;

/**
 * The CustomerInterface class provides an interface for customers to interact with the restaurant system.
 */
public class CustomerInterface {
  private static Map<Integer,OrderList> orderMap;
  
  /**
   * Handles the user interaction for a customer.
   *
   * @param branch The branch where the customer is ordering from.
   * @param sc     The Scanner object for user input.
   */
    public static void handler(Branch branch, Scanner sc) {
        orderMap = branch.getorderMap();
        boolean exit = false;
        System.out.println("Welcome to " + branch.getName());
        boolean newCustomer = true;
        System.out.println("Have you ordered before? (y/n)");
        if(sc.next().charAt(0)=='y'){
          newCustomer = false;
        }
        int orderId;
        if(!newCustomer){
          System.out.println("What is your orderId?");
          orderId = sc.nextInt();
          if (branch.getOrderById(orderId)==null){
            System.out.println("orderId is not found.");
            return;
          }
        }
        else{
          orderId = branch.getorder_Id();
        }
        while (!exit) {
            System.out.println("\n1. Place Order\n" +
               "2. Make Payment\n" +
               "3. Track Order Status\n" +
               "4. Collect Food\n" +
               "0. Back\n" +
               "Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println();
            switch (choice) {
              case 1:
                  placeOrder(branch,orderId,sc);
                  break;
              case 2:
                  OrderList orderList = branch.getOrderById(orderId);
                  if(orderList!=null){
                      if(Payment.MakePayment(orderList.getTotalPrice(),sc)){
                          printReceipt(branch, orderId);
                          orderList.setPaid();
                      }                   
                  }
                  else System.out.println("Please placed an order first.");      
                  break;
              case 3:
                  if(branch.getOrderById(orderId)!=null){
                      branch.TrackOrderStatus(orderId);
                  }
                  else System.out.println("Please placed an order first.");
                      break;
              case 4:
                  if(branch.getOrderById(orderId)!=null){
                      if(!branch.getOrderById(orderId).getPaid()){
                        System.out.println("Please pay first before collecting your food.");
                        break;
                      }
                      else
                        branch.collectFood(orderId);
                  }
                  else System.out.println("Please placed an order first.");
                      break;
              case 0:
                  System.out.println("Thank you for visiting. Goodbye!\n");
                  if(newCustomer) branch.increment_order_Id();
                  exit = true;
                  break;
              default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 4.\n");
            }
        }
    }

    /**
     * Allow customer to place order.
     *
     * @param branch  The branch where the order is placed.
     * @param orderId The ID of the order.
     * @param sc      The Scanner object for user input.
     */
  public static void placeOrder(Branch branch, int orderId, Scanner sc) {
    System.out.println("Placing Order...");
    branch.getMenu().displayMenuItemToCustomer(branch.getBranchName(), branch);
    System.out.println();
    while (true) {
      OrderList orderList = branch.getOrderById(orderId);
      System.out.println("Enter the index of the item you want to order (0 to finish): ");
      int index = sc.nextInt();
      
      if (index == 0){
        if(orderList==null){
          System.out.println("Please order at least 1 item.");
          continue;
        }
        else{
          System.out.println("Order placed successfully.\n"+"Your order ID: "+ orderId);
          break;
        }
      } 
      else if (index < 1 || index > branch.getMenu().getmenuItems().size()) {
        System.out.println("Invalid index. Please enter a valid index.\n");
        continue;
      }
      
      MenuItem menuItem = branch.getMenu().getmenuItems().get(index - 1);
      System.out.println("You selected: " + menuItem.getName());
      System.out.println("Enter quantity: ");
      int quantity = sc.nextInt();
      System.out.println("Do you want to takeaway or dine-in? (t/d): ");
      char takeawayOption = sc.next().charAt(0);
      Boolean takeaway = takeawayOption == 't';
      sc.nextLine();
      System.out.println("Enter any remarks (optional): ");
      String remarks = sc.nextLine();
      
      Order orderItem = new Order(menuItem, quantity, takeaway, remarks);
      if(orderList!=null){
        Boolean found = false;
        for(Order order:orderList.getOrders()){
          if(order.getItem().getName().equals(orderItem.getItem().getName())){
            order.setQuantity(order.getQuantity() + quantity);
            System.out.println("Quantity updated for existing item.\n");
            found=true;
            break;
          }
        }
      if(!found){
        orderList.addOrder(orderItem);
        System.out.println("Item added to order. Please select next item\n");
      }          
    }
    else{
        orderList = new OrderList();
        orderList.addOrder(orderItem);
        orderMap.put(orderId, orderList);
        System.out.println("Item added to order.\n");  
      }
  }
}
  
  /**
   * Prints the receipt for the customer's order.
   *
   * @param branch  The branch where the order was placed.
   * @param orderId The ID of the order.
   */
    public static void printReceipt(Branch branch, int orderId) {
        OrderList orderList = branch.getOrderById(orderId);
        String linebreak = "---------------------------------------------";
        System.out.println(linebreak);
        System.out.println("Branch: " + branch.getName());
        System.out.println("Receipt for Order ID: " + orderId);
        System.out.println(linebreak);
        System.out.println("Items purchased:");
        List<Order> orders = orderList.getOrders();
        int i=1;
        for (Order order : orders) {
            MenuItem item = order.getItem();
            int quantity = order.getQuantity();
            double unitPrice = item.getPrice();
            System.out.println(i++ + ". " + item.getName() +
                               " : " + unitPrice + 
                               " * " + quantity +
                               "\t\t" + formatCurrency(unitPrice * quantity));
          }
      System.out.println(linebreak);
      double totalAmount = orderList.getTotalPrice();
      System.out.println("Total: \t\t\t\t\t" + formatCurrency(totalAmount));
      System.out.println(linebreak);
    }
    
    /**
     * Formats a currency amount for display.
     *
     * @param amount The amount to format.
     * @return The formatted currency string.
     */
    private static String formatCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return "$" + formatter.format(amount);
    }
}