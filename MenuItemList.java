package project;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The MenuItemList class represents a list of menu items in a fast food restaurant.
 */
public class MenuItemList{
  
  private List<MenuItem> menuItems = new ArrayList<MenuItem>();
  
  /**
   * set menu item as current menu item list
   *
   */
  public MenuItemList() {
    this.menuItems = new ArrayList<MenuItem>();
  }
  
  /**
   * Adds a new menu item to the list.
   *
   * @param name         The name of the menu item.
   * @param price        The price of the menu item.
   * @param branchName   The name of the branch where the menu item is available.
   * @param description  The description of the menu item.
   * @param category     The category of the menu item.
   * @param availability The availability status of the menu item.
   */
  public void addItem(String name, double price, String branchName, String description, MenuItem.Category category, boolean availability){
    MenuItem item =  new MenuItem(name,price,branchName,description,category,availability);
    menuItems.add(item);    
  }

  /**
   * Removes a menu item from the list.
   *
   * @param item The menu item to be removed.
   */
  public void removeItem (MenuItem item){
    String name = item.getName();
    menuItems.remove(item);
    if(!menuItems.contains(item)){
      System.out.println(name + " is removed successfully");
    }
  }
  
  /**
   * Get a menu item from the list.
   *
   * @return menu item get from the list.
   */
  public List<MenuItem> getmenuItems(){
    return menuItems;
  }
  
  /**
   * Retrieves a menu item by its name.
   *
   * @param name The name of the menu item to retrieve.
   * @return The menu item with the specified name, or null if not found.
   */
  public MenuItem getItem(String name){
    for (MenuItem item : menuItems) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }
  
  /**
   * Displays the menu items to staff members.
   */
  public void displayMenuItemToStaff() {
      sortMenuItems();
      System.out.println("Menu:");
      int index = 1;
      MenuItem.Category currentCategory = null;
      for (MenuItem item : menuItems) {
          if (currentCategory != item.getCategory()) {
              currentCategory = item.getCategory();
              System.out.println("\n" + currentCategory + ":");
              index = 1;
          }
          System.out.println(
              index + ". " + item.getName() + 
              " - Price: $" + item.getPrice() + 
              " - Description: " + item.getDescription() + 
              " - Availability: " + (item.IsAvailable() ? "Available" : "Not Available")
          );
          index++;
      }
  }

  /**
   * Displays the menu items available at a specific branch to customers.
   *
   * @param branchName The name of the branch.
   * @param branch     The branch object.
   */
  public void displayMenuItemToCustomer(String branchName, Branch branch) {
      sortMenuItems();
      System.out.println("Menu for " + branchName + ":");
      int index = 1;
      MenuItem.Category currentCategory = null;
      for (MenuItem item : menuItems) {
          if (branch.getBranchName().equals(branchName)) {
              if (currentCategory != item.getCategory()) {
                  currentCategory = item.getCategory();
                  System.out.println("\n" + currentCategory + ":");
              }
              if (item.IsAvailable()) {
                  System.out.println(
                      index + ". " + item.getName() + 
                      "\n - Description: " + item.getDescription() + 
                      "\n - Price: $" + item.getPrice());
                  index++;
              }
          }
      }
  }
  
  /**
   * Sorts the menu items by category and name.
   */
  public void sortMenuItems() {
    Collections.sort(menuItems, new Comparator<MenuItem>() {
      public int compare(MenuItem item1, MenuItem item2) {
        int categoryComparison = item1.getCategory().compareTo(item2.getCategory());
        if (categoryComparison != 0) {
          return categoryComparison;
        }
        return item1.getName().compareTo(item2.getName());
      }
    });
  }
  
}
 