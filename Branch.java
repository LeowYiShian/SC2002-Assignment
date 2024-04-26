package project;
import java.util.HashMap;
import java.util.Map;

/**
 * The Branch class represents a branch of a fast food restaurant.
 */
public class Branch {
    private String branchName;
    private String location;
    private StaffList staffList;
    private Map<Integer, OrderList> orderMap;
    private MenuItemList menu;
    private int order_Id = 1; 
    
    /**
     * Constructs a Branch object with the specified attributes.
     *
     * @param branchName  The name of the branch.
     * @param location    The location of the branch.
     * @param staffQuota  The quota of staff members allowed for the branch.
     */
    public Branch(String branchName, String location, int staffQuota){
        this.branchName = branchName;
        this.location = location;
        this.staffList = new StaffList(staffQuota);
        this.orderMap = new HashMap<Integer, OrderList>();
        this.menu = new MenuItemList();
    }
  
    /**
     * Returns the name of the branch.
     *
     * @return The name of the branch.
     */
    public String getName() {
        return branchName;
    }
  
    /**
     * Sets the name of the branch.
     *
     * @param name The new name of the branch.
     */
    public void setName(String name) {
        branchName = name;
    }
  
    /**
     * Returns the location of the branch.
     *
     * @return The location of the branch.
     */
    public String getLocation() {
        return location;
    }
  
    /**
     * Sets the location of the branch.
     *
     * @param loc The new location of the branch.
     */
    public void setLocation(String loc) {
        location = loc;
    }
  
    /**
     * Returns the current order ID.
     *
     * @return The current order ID.
     */
    public int getorder_Id() {
        return order_Id;
    }
  
    /**
     * Increments the order ID.
     */
    public void increment_order_Id() {
        order_Id++;
    }
  
    /**
     * Returns the map of orders associated with their IDs.
     *
     * @return The map of orders.
     */
    public Map<Integer, OrderList> getorderMap(){
      return orderMap;
    }
  
    /**
     * Returns the staff list of the branch.
     *
     * @return The staff list of the branch.
     */
    public StaffList getStaffList() {
        return staffList;
    }
  
    /**
     * Returns the menu of the branch.
     *
     * @return The menu of the branch.
     */
    public MenuItemList getMenu() {
        return menu;
    }
  
    /**
     * Returns the branchName of the branch.
     *
     * @return The branchName of the branch.
     */
    public String getBranchName() {
        return branchName;
     }
  
    /**
     * Allows a staff member to log in to the branch.
     *
     * @param loginID  The login ID of the staff member.
     * @param password The password of the staff member.
     * @return The logged-in staff member if authentication is successful, otherwise null.
     */
    public Staff login(String loginID, String password) {
        for (Staff staff : staffList.getStaffList()) {
            if (staff.getLoginID().equals(loginID) && staff.getPassword().equals(password)) {
                return staff;
            }
        }
        return null;
    }
  
    /**
     * Adds an order list to the branch's order map.
     *
     * @param orderList The order list to add.
     * @param orderId   The ID of the order list.
     */
    public void addOrderList(OrderList orderList, int orderId) {
        orderMap.put(orderId, orderList);
    }  
  
    /**
     * Removes an order list from the branch's order map.
     *
     * @param orderId The ID of the order list to remove.
     */
    public void removeOrderList(int orderId) {
        orderMap.remove(orderId);
    }
  
    /**
     * Displays the details of a specific order.
     *
     * @param orderId The ID of the order to display.
     */
    public void displayOrderDetails(int orderId) {
        OrderList orderList = orderMap.get(orderId);
        if (orderList != null) {
            orderList.displayOrderDetails();
        } else {
            System.out.println("No orders found for orderId " + orderId + "\n");
        }
    }
  
    /**
     * Displays all new orders that are ready for processing.
     */
    public void displayNewOrder() {
        if (orderMap.isEmpty()) {
            System.out.println("No order found\n");
        }
        boolean unprocessedOrder = true;
        for (Map.Entry<Integer, OrderList> entry : orderMap.entrySet()) {
            System.out.println("Order Id: "+entry.getKey());
            OrderList orderList = entry.getValue();
            if (orderList.getStatus() == OrderList.OrderStatus.PROCESSING) {
                orderList.displayOrderDetails();
                System.out.println();
                unprocessedOrder=false;
            }
        }
        if(unprocessedOrder){
            System.out.println("No unprocessed order");
        }
    }
  
    /**
     * Retrieves an order list by its ID.
     *
     * @param orderId The ID of the order list to retrieve.
     * @return The order list corresponding to the given ID, or null if not found.
     */
    public OrderList getOrderById(int orderId) {
        return orderMap.get(orderId);
    }
  
    /**
     * Tracks the status of an order.
     *
     * @param orderId The ID of the order to track.
     */
  public void TrackOrderStatus(int orderId) {
      OrderList orderList = getOrderById(orderId);
      if (orderList == null) {
          System.out.println("No orders found for Order ID " + orderId);
          return;
      }
      System.out.println("Status: " + orderList.getStatus());
  }
  
  /**
   * Change order status as ready for pickup.
   *
   * @param orderId The ID of the order to process.
   */
  public void processOrder(int orderId) {
    OrderList orderList = getOrderById(orderId);
    if (orderList == null) {
        System.out.println("No orders found for Order ID " + orderId);
        return;
    }
    orderList.setStatus(OrderList.OrderStatus.READYTOPICKUP);
  }

  /**
   * Change order status as Completed.
   *
   * @param orderId The ID of the order to collect.
   */
  public void collectFood(int orderId) {
      OrderList orderList = getOrderById(orderId);
      if (orderList == null) {
          System.out.println("No orders found for Order ID " + orderId);
          return;
      }
      if(orderList.getStatus() == OrderList.OrderStatus.READYTOPICKUP){
        orderList.setStatus(OrderList.OrderStatus.COMPLETED);
        System.out.println("Food successfully collected!");
      }
      else
        System.out.println("Your food has not been processed.");
  }
  
}