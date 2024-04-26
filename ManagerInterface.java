package project;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The ManagerInterface class provides methods for managing a branch's operations.
 */
public class ManagerInterface {
	
	/**
     * Handles manager interface functionalities.
     *
     * @param branch The branch object.
     * @param staff  The staff object.
     * @param sc     The Scanner object for input.
     */
    public static void handler(Branch branch, Staff staff, Scanner sc) {
        System.out.println("\nWelcome, " + staff.getName() + "!");
        boolean exit = false;
        while (!exit) {
          System.out.println("\n1. Change Password\n" +
                               "2. Display New Orders\n" +
                               "3. View Details of a particular order\n" +
                               "4. Process Order\n" +
                               "5. Display Staff List\n" +
                               "6. Display Menu\n" +
                               "7. Manage Menu\n" +
                               "8. Clear OrderList\n" +
                               "0. Back\n" +
                               "Enter your choice: ");
            int id, choice = sc.nextInt();
            switch (choice) {
              case 1:
                sc.nextLine();
                System.out.println("Enter new password");
                String newPassword = sc.nextLine();
                staff.setPassword(newPassword);
                break;
              case 2:
                branch.displayNewOrder();
                break;
              case 3:
                System.out.println("Enter the order ID: ");
                id = sc.nextInt();
                if(branch.getOrderById(id)!=null)
                  branch.displayOrderDetails(id);
                else System.out.println("OrderId "+ id +" is not found");
                break;
              case 4:
                System.out.println("Enter the order ID: ");
                id = sc.nextInt();
                if(branch.getOrderById(id)!=null){
                  branch.processOrder(id);
                  System.out.println("Order status updated.");
                }
                else System.out.println("OrderId "+ id +" is not found");
                break;
              case 5:
                branch.getStaffList().displayStaffList();;
                break;
              case 6:
                branch.getMenu().displayMenuItemToStaff();
                break;
              case 7:
                manageMenu(branch,sc);
                break;
              case 8:
                clearOrder(branch);
                break;
              case 0:
                System.out.println("Logging out. Goodbye!\n");
                exit = true;
                break;
              default:
                System.out.println("Invalid choice. Please enter a number between 0 and 6.\n");
            }
        }
    }
  
    /**
     * Manages the menu of the branch.
     *
     * @param branch The branch object.
     * @param sc     The Scanner object for input.
     */
  public static void manageMenu(Branch branch, Scanner sc) {
    MenuItemList menu = branch.getMenu();
    System.out.println("Managing Menu:\n" +
                       "1. Add Item\n" +
                       "2. Edit / Remove Item\n"+
                       "0. Back\n");
    int choice = sc.nextInt();
    sc.nextLine();
    switch (choice) {
      case 1:
        System.out.println("Enter the new item name:");
        String itemName = sc.nextLine();
        boolean duplicate = false;
        for(MenuItem Item:branch.getMenu().getmenuItems()){
          if(Item.getName().equals(itemName)){
            System.out.println("Item already exists.");
            duplicate = true;
            break;
          }
        }
        if(duplicate) break;
        System.out.println("Enter the new price:");
        double price = sc.nextDouble();
        if(price<=0){
          if(price<=0){
            System.out.println("Invalid price, enter a positive number");
            break;
          };
        }
        sc.nextLine();
        System.out.println("Enter the new description:");
        String description = sc.nextLine();
        System.out.println("Enter the new category(SETMEAL, BURGER, SIDE, DRINK):");
        String categoryStr = sc.nextLine();
        MenuItem.Category category = MenuItem.Category.valueOf(categoryStr.toUpperCase());
        System.out.println("Enter the new availability(true/false):");
        boolean availability = sc.nextBoolean();
        menu.addItem(itemName, price, branch.getName(), description, category, availability);
        System.out.println("Item successfully added into ItemList as follows:");
        menu.displayMenuItemToStaff();
        break;
      case 2:
        edit_removeItem(branch, menu, sc);
        menu.displayMenuItemToStaff();
        break;
      case 0:
        System.out.println("Back...");
        break;
        }
    }
  
  /**
   * Edits or removes items from the menu.
   *
   * @param branch The branch object.
   * @param menu   The menu object.
   * @param sc     The Scanner object for input.
   */
  public static void edit_removeItem (Branch branch, MenuItemList menu, Scanner sc) {
    System.out.println("1. Edit Item\n" +
                       "2. Remove Item\n" +
                       "0. Back\n" +
                       "Enter your choice: ");
    int choice1 = sc.nextInt();
    sc.nextLine();
    switch (choice1) {
      case 1:
          updateExistingMenu(branch, menu, sc);
          break;
      case 2:
          System.out.println("Enter the name of the item to edit: ");
          String itemNameToEdit1 = sc.nextLine();
          MenuItem item1 = menu.getItem(itemNameToEdit1);
          String name = item1.getName();
          menu.getmenuItems().remove(item1);
          System.out.println( name +" has been removed.");
          break;
      case 0:
          System.out.println("Back...");
          break;
      default:
          System.out.println("Invalid choice!");
          break;
    }
  }

  /**
   * Updates existing menu items.
   *
   * @param branch The branch object.
   * @param menu   The menu object.
   * @param sc     The Scanner object for input.
   */
  public static void updateExistingMenu (Branch branch, MenuItemList menu, Scanner sc) {
      System.out.println("Enter the name of the item to edit: ");
      String itemNameToEdit = sc.nextLine();
      MenuItem item = menu.getItem(itemNameToEdit);
    System.out.println("Update Existing Menu:\n" +
                       "1. Change Description\n" +
                       "2. Change Price\n" +
                       "3. Change Ailability\n" +
                       "0. Back\n" +
                       "Enter your choice: ");
      int editChoice = sc.nextInt();
      sc.nextLine();
      switch (editChoice) {
        case 1:
            System.out.println("Enter the description of the item: ");
            String name = sc.nextLine();
            System.out.println();
            item.setDescription(name);
            if(item.getDescription().equals(name)){
              System.out.println("Description changed successfully\n");
            }
            break;
        case 2:
            System.out.println("Enter the price of the item: ");
            double newprice = sc.nextDouble();
            if(newprice<=0){
              System.out.println("Invalid price, enter a positive number");
              break;
            };
            item.setPrice(newprice);
            System.out.println("Price changed successfully");
            break;
        case 3:
            System.out.println("Enter the availability of the item (true/false): ");
            boolean newAvailability = sc.nextBoolean();
            item.setAvailability(newAvailability);
            System.out.println("Availability changed successfully");
            break;
        case 0:
            System.out.println("Back...");
            break;
        default:
            System.out.println("Invalid choice!");
            break;
      }
  }
  
  /**
   * Clears orders based on certain conditions.
   *
   * @param branch The branch object.
   */
  public static void clearOrder(Branch branch){
    Map<Integer, OrderList> orderMap = branch.getorderMap();
    LocalTime currentTime = LocalTime.now();
        List<Integer> ordersToRemove = new ArrayList<>();
        for (Map.Entry<Integer, OrderList> entry : orderMap.entrySet()) {
            OrderList orderList = entry.getValue();
            LocalTime readyTime = orderList.getReadyTime();
            long nanos = currentTime.toNanoOfDay() - readyTime.toNanoOfDay(), seconds = nanos / 1_000_000_000;
            if (orderList.getStatus() == OrderList.OrderStatus.READYTOPICKUP) {
                if (seconds > 20) {
                    System.out.println("Cancelling order...");
                    orderList.setStatus(OrderList.OrderStatus.CANCELLED);
                }
            }
            else if (orderList.getStatus() == OrderList.OrderStatus.COMPLETED) {
                System.out.println("Removing order...");
                ordersToRemove.add(entry.getKey());
            }
        }
        for (int orderId : ordersToRemove) {
            branch.removeOrderList(orderId);
        }
  }
}

