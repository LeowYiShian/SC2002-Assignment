package project;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

/**
 * The HQ (Headquarters) class manages branches and administrative functions.
 */
public class HQ {
  
    private static List<Branch>branchList;
    private static List<Admin>adminList;
    private static StaffList allStaffList;
  
    /**
     * Constructs a new HQ object.
     */
    public HQ() {
      branchList = new ArrayList<Branch>();
      adminList = new ArrayList<Admin>();
      allStaffList = new StaffList(1000);
    }
    
    /**
     * Adds a new branch to the list of branches.
     *
     * @param branchName  The name of the branch.
     * @param location    The location of the branch.
     * @param staffQuota  The staff quota of the branch.
     */
    public static void addBranch(String branchName, String location, int staffQuota) {
      Branch branch = new Branch(branchName, location, staffQuota);
      branchList.add(branch);
  }
    
    /**
     * Retrieves the list of administrators.
     *
     * @return The list of administrators.
     */
    public static List<Admin> getAdminList(){
      return adminList;
    }
    
    /**
     * Adds an administrator to the list of administrators.
     *
     * @param admin The administrator to add.
     */
    public static void addAdmin(Admin admin){
      adminList.add(admin);
    }
  
    /**
     * Logs in an administrator.
     *
     * @param loginID The login ID of the administrator.
     * @param password The password of the administrator.
     * @return The logged-in administrator, or null if login fails.
     */
    public static Admin login(String loginID, String password){
      for (Admin admin:adminList) {
        if (admin.getLoginID().equals(loginID) && admin.getPassword().equals(password)) {
            return admin;
        }
      }
      return null;
    }
  
    /**
     * Closes a branch with the specified name.
     *
     * @param name The name of the branch to close.
     * @return true if the branch was successfully closed, false otherwise.
     */
    public static boolean closeBranch(String name) {
      for(Branch branch : branchList){
        if(branch.getName().equals(name))
          branchList.remove(branch);
          return true;
      }
      return false;
  }
    
    /**
     * Retrieves a branch by its name.
     *
     * @param name The name of the branch to retrieve.
     * @return The branch with the specified name, or null if not found.
     */
    public static Branch getBranch(String name){
      for(Branch branch:branchList){
        if(branch.getName().equals(name)){
          return branch;
        }
      }
      return null;
    }
  
  /**
   * Retrieves a branch by its ID.
   *
   * @param id The ID of the branch to retrieve.
   * @return The branch with the specified ID, or null if not found.
   */
    public static Branch getBranchById(int id){
      for(Branch branch:branchList){
        if(id==0){
          return branch;
        }
        id--;
      }
      return null;
    }
    
    /**
     * Retrieves the list of branches.
     *
     * @return The list of branches.
     */
    public static List<Branch> getBranchList() {
        return branchList;
    }
  
    /**
     * Displays the name of branches.
     *
     */
    public static void displayBranch(){
      System.out.println("Branch List:");
      for (int i = 0; i < branchList.size(); i++) {
          System.out.println((i + 1) + ". " + branchList.get(i).getName());
      }
    }
    
    /**
     * Sort staff according to branch
     *
     * @return The sorted staff.
     */
  public static void sortStaff() {
    Collections.sort(allStaffList.getStaffList(), new Comparator<Staff>() {
        public int compare(Staff staff1, Staff staff2) {
            int branchComparison = staff1.getBranch().compareTo(staff2.getBranch());
            if (branchComparison != 0) {
                return branchComparison;
            } else {
                return staff1.getName().compareTo(staff2.getName());
            }
        }
    });
  } 
  
  /**
   * Retrieves the list of all staff.
   *
   * @return The list of all staff.
   */
  public static StaffList getAllStaffList() {
      allStaffList.getStaffList().clear();
      for (Branch branch : branchList) {
        for(Staff staff : branch.getStaffList().getStaffList()){
          allStaffList.getStaffList().add(staff);
        }
      }
      return allStaffList;
  }
  
  /**
   * Filters the staff list based on the specified branch.
   *
   * @param branch The branch to filter by.
   * @return The filtered staff list.
   */
  public static StaffList filter_method(String branch){
    StaffList filteredList = new StaffList(1000);
      for (Staff staff : allStaffList.getStaffList()) {
        if(staff.getBranch().equals(branch)){
          filteredList.getStaffList().add(staff);
        }
      }
    return filteredList;
  }
  
  /**
   * Filters the staff list based on the specified gender.
   *
   * @param gender The gender to filter by.
   * @return The filtered staff list.
   */
  public static StaffList filter_method(Staff.Gender gender){
    StaffList filteredList = new StaffList(1000);
      for (Staff staff : allStaffList.getStaffList()) {
        if(staff.getGender()==gender){
          filteredList.addStaff(staff);
        }
      }
    return filteredList;
  }
  
  /**
   * Filters the staff list based on the specified role.
   *
   * @param role The role to filter by.
   * @return The filtered staff list.
   */
  public static StaffList filter_method(Staff.Role role){
    StaffList filteredList = new StaffList(1000);
      for (Staff staff : allStaffList.getStaffList()) {
        if(staff.getRole()==role){
          filteredList.addStaff(staff);
        }
      }
    return filteredList;
  }
  
  /**
   * Filters the staff list based on the specified age range.
   *
   * @param minAge The minimum age in the range.
   * @param maxAge The maximum age in the range.
   * @return The filtered staff list.
   */
  public static StaffList filter_method(int minAge, int maxAge){
    StaffList filteredList = new StaffList(1000);
      for (Staff staff : allStaffList.getStaffList()) {
        if(staff.getAge()>=minAge && staff.getAge()<=maxAge){
          filteredList.addStaff(staff);
        }
      }
    return filteredList;
  }
}
