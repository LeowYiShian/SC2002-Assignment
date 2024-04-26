package project;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The StaffList class represents a list of staff members.
 * It manages the staff members, their quota, and the number of managers.
 */
public class StaffList {
  private List<Staff> staffList;
  private int staffQuota;
  private int numOfStaff;
  private int numOfManager;
  
  /**
   * The StaffList is a list of staff members.
   */
  public StaffList(int staffQuota) {
    this.staffList = new ArrayList<Staff>();
    this.staffQuota = staffQuota;
    this.numOfStaff = 0;
    this.numOfManager = 0;
  }
  
  /**
   * Sets the staff quota for the branch.
   *
   * @param quota The staff quota to set.
   */
  public void setStaffQuota(int quota){
    staffQuota=quota;
  }
  
  /**
   * Gets the staff quota for the branch.
   *
   * @return The staff quota.
   */
  public int getStaffQuota(){
    return staffQuota;
  }
  
  /**
   * Displays the list of staff members.
   */
  public void displayStaffList() {
    for(Staff staff : this.staffList){
      System.out.println("Name: " + staff.getName() + "\n" +
         "Gender: " + staff.getGender() + "\n" +
         "Age: " + staff.getAge() + "\n" +
         "Branch: " + staff.getBranch() + "\n" +
         "Role: " + staff.getRole() + "\n");
    }
  }

  /**
   * Gets the staff list for the branch.
   *
   * @return The staff list.
   */
  public List<Staff> getStaffList() {
    return this.staffList;
  }
  
  /**
   * Gets the number of staff members.
   *
   * @return The number of staff members.
   */
  public int getNumOfStaff(){
    return numOfStaff;
  }
  
  /**
   * Gets the number of managers.
   *
   * @return The number of managers.
   */
  public int getNumOfManager(){
    return numOfManager;
  }
  
  /**
   * Sets the number of staff members.
   *
   * @param n The number of staff members to set.
   */
  public void setNumOfStaff(int n) {
    numOfStaff=n;
  }
  
  /**
   * Sets the number of managers.
   *
   * @param n The number of managers to set.
   */
  public void setNumOfManager(int n) {
    numOfManager=n;
  }
  
  /**
   * Gets a staff member by their login ID.
   *
   * @param loginID The login ID of the staff member.
   * @return The staff member with the specified login ID, or null if not found.
   */
  public Staff getStaffById(String loginID) {
    for (Staff staff : staffList) {
      if (staff.getLoginID().equals(loginID)) {
        return staff;
      }
    }
    return null;
  }
  
  /**
   * Gets a staff member by their name.
   *
   * @param name The name of the staff member.
   * @return The staff member with the specified name, or null if not found.
   */
  public Staff getStaffByName(String name) {
    for (Staff staff : staffList) {
      if (staff.getName().equals(name)) {
        return staff;
      }
    }
    return null;
  }
  
  /**
   * Adds a new staff member to the list.
   *
   * @param staff The staff member to add.
   */
  public void addStaff(Staff staff) {
    if (staffList.size() < staffQuota) {
    if (staff.getRole() == Staff.Role.STAFF) {
      numOfStaff++;
    }
    else if (staff.getRole() == Staff.Role.MANAGER) {
      numOfManager++;
    }
    this.staffList.add(staff);
  }
  else {System.out.println("Cannot add more staff. Staff quota reached.");}
  }
  
  /**
   * Removes a staff member from the list by their name.
   *
   * @param staffName The name of the staff member to remove.
   */
  public void removeStaff(String staffName) {
    Iterator<Staff> iterator = this.staffList.iterator();
      while (iterator.hasNext()) {
        Staff staff = iterator.next();
          if (staff.getName().equals(staffName)) {
            if (staff.getRole() == Staff.Role.STAFF) {
              numOfStaff--;
            }
            else if (staff.getRole() == Staff.Role.MANAGER) {
              numOfManager--;
            }
            iterator.remove();
            return;
          }
      }
      System.out.println("Staff member with name " + staffName + " not found.");
  }
}
