package project;

/**
 * The Order class represents an order placed by a customer.
 */
public class Order {
  private MenuItem item;
  private int quantity;
  private Boolean takeaway;
  private String remark;
  
  /**
   * Constructs an Order object with the specified properties.
   *
   * @param item     The menu item being ordered.
   * @param quantity The quantity of the menu item.
   * @param takeaway Specifies if the order is for takeaway.
   * @param remark   Any remarks or special instructions for the order.
   */
  public Order(MenuItem item, int quantity, Boolean takeaway, String remark) {
    this.item = item;
    this.quantity = quantity;
    this.takeaway = takeaway;
    this.remark = remark;
  }
  
  /**
   * Gets the menu item of the order.
   *
   * @return The menu item of the order.
   */
  public MenuItem getItem() {
    return this.item;
  }
  
  /**
   * Gets the quantity of the menu item in the order.
   *
   * @return The quantity of the menu item.
   */
  public int getQuantity() {
    return this.quantity;
  }
  
  /**
   * Sets the quantity of the menu item in the order.
   *
   * @param quantity The quantity to set for the menu item.
   */
  public void setQuantity(int quantity) {
    this.quantity=quantity;
  }
  
  /**
   * Checks if the order is for takeaway.
   *
   * @return True if the order is for takeaway, false otherwise.
   */
  public Boolean getTakeaway(){
    return this.takeaway;
  }
  
  /**
   * Gets any remarks or special instructions for the order.
   *
   * @return The remarks or special instructions for the order.
   */
  public String getRemark() {
    return this.remark;
  }
}

