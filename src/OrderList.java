package project;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

/**
 * The OrderList class represents a list of orders in a fast food restaurant.
 */
public class OrderList {
  
/**
 * Enumeration for different statuses of an order.
 */
  public enum OrderStatus{PROCESSING, READYTOPICKUP, COMPLETED, CANCELLED};
  private List<Order> orders = new ArrayList<Order>();
  private LocalTime readyTime;
  private OrderStatus orderStatus;
  private boolean paid;
  
  /**
   * Constructs an OrderList object with initial values.
   */
  public OrderList(){
    this.orders = new ArrayList<Order>();
    this.orderStatus = OrderStatus.PROCESSING;
    this.readyTime = null;
    this.paid = false;
  }
  
  /**
   * Checks if the order has been paid.
   *
   * @return True if the order has been paid, false otherwise.
   */
  public boolean getPaid(){
    return this.paid;
  }
  
  /**
   * Sets the paid status of the order.
   */
  public void setPaid(){
    this.paid=true;
  }
  
  /**
   * Adds an order to the list.
   *
   * @param order The order to be added.
   */
  public void addOrder(Order order) {
      orders.add(order);
  }
  
  /**
   * Removes an order from the list.
   *
   * @param order The order to be removed.
   */
  public void removeOrder(Order order) {
      orders.remove(order);
  }
  
  /**
   * Gets the list of orders.
   *
   * @return The list of orders.
   */
  public List<Order> getOrders() {
      return orders;
  }
  
  /**
   * Displays details of all orders in the list.
   */
  public void displayOrderDetails() {
      for (Order order : orders) {
          MenuItem item = order.getItem();
          System.out.println("Item: " + item.getName() + "\n" +
          "Quantity: " + order.getQuantity() + "\n" +
           "Takeaway: " + order.getTakeaway() + "\n" +
           "Remark: " + order.getRemark() + "\n"+
           "Status: " + getStatus().name()+ "\n");
      }
  }
  
  /**
   * Gets the status of the order list.
   *
   * @return The status of the order list.
   */
  public OrderStatus getStatus() {
    return this.orderStatus;
  }
  
  /**
   * Sets the status of the order list.
   *
   * @param orderStatus The status to set for the order list.
   */
  public void setStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
    if(orderStatus == OrderStatus.READYTOPICKUP){
      setReadyTime();
    }
  }
  
  /**
   * Gets the time when the order is ready for pickup.
   *
   * @return The time when the order is ready for pickup.
   */
  public LocalTime getReadyTime() {
    return readyTime;
  }
  
  /**
   * Sets the time when the order is ready for pickup.
   */
  public void setReadyTime() {
    readyTime=LocalTime.now();;
  }
  
  /**
   * Calculates the total price of all orders in the list.
   *
   * @return The total price of all orders.
   */
  public double getTotalPrice() {
    double totalAmount = 0;
      for (Order order : orders) {
            MenuItem item = order.getItem();
            totalAmount += item.getPrice()*order.getQuantity();
        }
        return totalAmount;
    }
}