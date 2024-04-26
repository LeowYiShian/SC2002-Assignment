package project;

/**
 * The MenuItem class represents an item in the menu of a fast food restaurant.
 */
public class MenuItem{
  
/**
 * Enumeration for different categories of menu items.
 */
  public enum Category {SETMEAL, BURGER, SIDE, DRINK};
  
  
  private String name;
  private double price;
  private String description;
  private String branchName;
  private Category category;
  private boolean availability;
  
  /**
   * Constructs a MenuItem object with the specified properties.
   *
   * @param name        The name of the menu item.
   * @param price       The price of the menu item.
   * @param branchName  The name of the branch where the menu item is available.
   * @param description The description of the menu item.
   * @param category    The category of the menu item.
   * @param availability The availability status of the menu item.
   */
  public MenuItem(String name, double price, String branchName, String description, Category category, boolean availability){
    this.name = name;
    this.price = price;
    this.branchName = branchName;
    this.description = description;
    this.category = category;
    this.availability = availability;
  }
  
  /**
   * Gets the name of the menu item.
   *
   * @return The name of the menu item.
   */
  public String getName() {
   return name;
  }
  
  /**
   * Sets the name of the menu item.
   *
   * @param name The name to set for the menu item.
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Gets the price of the menu item.
   *
   * @return The price of the menu item.
   */
  public double getPrice() {
    return price;
  }
  
  /**
   * Sets the price of the menu item.
   *
   * @param price The price to set for the menu item.
   */
  public void setPrice(double price) {
    this.price = price;
  }
  
  /**
   * Gets the description of the menu item.
   *
   * @return The description of the menu item.
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * Sets the description of the menu item.
  *
  * @param description The description to set for the menu item.
  */
  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * Gets the category of the menu item.
   *
   * @return The category of the menu item.
   */
  public Category getCategory() {
    return category;
  }
  
  /**
   * Sets the category of the menu item.
   *
   * @param category The category to set for the menu item.
   */
  public void setCategory(Category category) {
    this.category = category;
  }
  
  /**
   * Checks if the menu item is available.
   *
   * @return True if the menu item is available, false otherwise.
   */
  public boolean IsAvailable() {
    return availability;
  }
  
  /**
   * Sets the availability status of the menu item.
   *
   * @param availability The availability status to set for the menu item.
   */
  public void setAvailability(boolean availability) {
    this.availability = availability;
  }  
  
}