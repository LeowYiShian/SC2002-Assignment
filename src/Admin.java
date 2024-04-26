package project;
import java.util.Scanner;

/**
 * The Admin class represents an administrative staff member who has privileges
 * to perform administrative tasks such as editing staff accounts, managing payments,
 * and filtering staff information.
 */
public class Admin extends Staff{
	

    /**
     * Constructs an Admin object with the specified attributes.
     *
     * @param name      The name of the admin.
     * @param loginID   The login ID of the admin.
     * @param gender    The gender of the admin.
     * @param age       The age of the admin.
     * @param branch    The branch to which the admin belongs.
     * @param role      The role of the admin (STAFF or MANAGER).
     * @param password  The password of the admin.
     */
    public Admin(String name, String loginID, Staff.Gender gender, int age, String branch, Staff.Role role, String password){
        super(name,loginID,gender,age, branch,role,password);
    }
    
    /**
     * Allows the admin to edit staff accounts by adding, editing or removing staff members.
     *
     * @param branch The branch where the staff accounts are managed.
     * @param sc     The Scanner object used for user input.
     */
    public static void editStaffAccount(Branch branch, Scanner sc) {
      boolean exit = false;
      int choice;
      while (!exit) {
        System.out.println("\nEditing Staff Account:\n" +
                           "1. Add Staff\n" +
                           "2. Remove Staff\n" +
                           "0. Back\n" +
                           "Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();
        String name;
        Staff staff;
        switch (choice) {
          case 1:
            System.out.println("Enter name of staff to be added:");
            name = sc.nextLine();
            System.out.println("Enter login ID:");
            String loginID = sc.nextLine();
            System.out.println("Enter gender (MALE/FEMALE):");
            String gender = sc.nextLine();
            Staff.Gender genderEnum;
            if(gender.equals("MALE")){
              genderEnum = Staff.Gender.MALE;
            }
            else if(gender.equals("FEMALE")){
              genderEnum = Staff.Gender.FEMALE;
            }
            else{
              System.out.println("Error, please make sure you enter MALE or FEMALE correctly (case-sensitive)\n");
              break;
            }
            System.out.println("Enter age:");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter role: STAFF/MANAGER");
            String role = sc.nextLine();
            Staff.Role roleEnum;
            if(role.equals("STAFF")){
              roleEnum = Staff.Role.STAFF;
            }
            else if(role.equals("MANAGER")){
              roleEnum = Staff.Role.MANAGER;
            }
            else{
              System.out.println("Error, please make sure you enter STAFF or MANAGER correctly (case-sensitive)\n");
              break;
            }
            System.out.println("The default password of the new staff is \" password \".");
            staff = new Staff(name, loginID, genderEnum, age, branch.getName(), roleEnum, "password");
            branch.getStaffList().addStaff(staff);
            break;
          case 2:
            System.out.println("Enter name of staff to be removed:");
            name = sc.nextLine();
            staff = branch.getStaffList().getStaffByName(name);
            branch.getStaffList().getStaffList().remove(staff);
            break;
          case 0:
            System.out.println("Done editing staff account.\n");
            exit = true;
            break;
          default:
            System.out.println("Invalid choice. Please enter a number between 0 and 2.\n");
        }
      }
    }            
    
    /**
     * Promotes a staff member to the role of branch manager if quota conditions are met.
     *
     * @param staffName The name of the staff member to be promoted.
     * @param branch    The branch where the staff member is employed.
     * @return True if the promotion is successful, false otherwise.
     */
  public static boolean promoteToBranchManager(String staffName, Branch branch) {
    StaffList staffList = branch.getStaffList();
    Staff staff = staffList.getStaffByName(staffName);
    if(staff==null){
      System.out.println("Staff not found in the branch.");
      return false;
    };
    int currentNumOfManagers = staffList.getNumOfManager();
    int currentNumOfStaffs = staffList.getNumOfStaff();
    if (currentNumOfManagers + 1 <= quotaOfManager(currentNumOfStaffs)) {
      staff.setRole(Staff.Role.MANAGER);
      staffList.setNumOfManager(currentNumOfManagers + 1);
      staffList.setNumOfStaff(currentNumOfStaffs - 1);
      System.out.println(staffName + " is promoted to Manager successfully.");
      return true;
    }
    else {
      System.out.println("Cannot promote to manager. Branch already has maximum number of managers.");
      return false;
    }
  }
  
  /**
   * Determines the quota of managers based on the number of staff members in a branch.
   *
   * @param numOfStaffs The number of staff members in the branch.
   * @return The quota of managers for the branch.
   */
  public static int quotaOfManager(int numOfStaffs) {
    int quota = 0;
    if (numOfStaffs >= 1 && numOfStaffs <= 4){
      quota = 1;
    }
    else if (numOfStaffs >= 5 && numOfStaffs <= 8){
      quota = 2;
    }
    else if (numOfStaffs >= 9 && numOfStaffs <= 15){
      quota = 3;
    }
    return quota;
  }
  
  /**
   * Allows the admin to edit payment methods by adding or removing payment options.
   *
   * @param sc The Scanner object used for user input.
   */
  public static void editPayment(Scanner sc) {
    int choice;
    boolean exit = false;
    while (!exit) {
      System.out.println("Editing Payment:\n" +
                         "1. Display Payment Methods\n" +
                         "2. Add Payment Method\n" +
                         "3. Remove Payment Method\n" +
                         "0. Back\n" +
                         "Enter your choice: ");
      choice = sc.nextInt();
      sc.nextLine();
      String name;
      switch (choice) {
        case 1:
          Payment.displayPaymentMethods();
          break;
        case 2:
          System.out.print("Enter the payment method type to add: ");
          name = sc.nextLine();
          PaymentMethod methodToAdd = new PaymentMethod(name);
          Payment.addPaymentMethod(methodToAdd);
          break;
        case 3:
          System.out.print("Enter the payment method to remove: ");
          int id = sc.nextInt();
          PaymentMethod paymentMethod = Payment.getPaymentMethod(id);
          if(paymentMethod == null){
          System.out.println("Payment method does not exist.\n");
          }
          else{
            Payment.removePaymentMethod(paymentMethod);
          }
          break;
        case 0:
          System.out.println("Done editing payment.\n");
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please enter a number between 0 and 3.\n");
      }
    }
  }
  
  /**
   * Transfers a staff member from one branch to another, subject to quota conditions.
   *
   * @param staffName            The name of the staff member to be transferred.
   * @param sourceBranchName     The name of the source branch.
   * @param destinationBranchName The name of the destination branch.
   * @return True if the transfer is successful, false otherwise.
   */
  public static boolean transferStaff(String staffName, String sourceBranchName, String destinationBranchName) {
      Branch sourceBranch = null;
      Branch destinationBranch = null;
        for (Branch branch : HQ.getBranchList()) {
            if (branch.getName().equals(sourceBranchName)) {
                sourceBranch = branch;
            }
            if (branch.getName().equals(destinationBranchName)) {
                destinationBranch = branch;
            }
        }
        if (sourceBranch == null || destinationBranch == null) {
            System.out.println("Source or destination branch is not found.");
            return false;
        }
        if(sourceBranch.getStaffList().getNumOfManager()-1 <= quotaOfManager(sourceBranch.getStaffList().getNumOfStaff()-1)||
          destinationBranch.getStaffList().getNumOfManager()+1 >= quotaOfManager(destinationBranch.getStaffList().getNumOfStaff()-1)){
          System.out.println("Cannot transfer staff, manager quota not meet");
          return false;
        }
        Staff staff = sourceBranch.getStaffList().getStaffByName(staffName);
        if (staff == null) {
            System.out.println("Staff is not found in the source branch.");
            return false;
        }
        if (sourceBranch.getStaffList().getStaffList().remove(staff)) {destinationBranch.getStaffList().addStaff(staff);
            return true;
        }
        return false;
  }
  
  /**
   * Allows the admin to filter staff members based on different criteria.
   *
   * @param sc The Scanner object used for user input.
   */
  public static void filter(Scanner sc){
    StaffList filterStaffList = null;
    System.out.println("1. Filter By branch\n"+
                       "2. Filter by gender\n"+
                       "3. Filter by role\n"+
                       "4. Filter by age\n" +
                       "Enter your choice.\n");
    int choice = sc.nextInt();
    sc.nextLine();
    switch(choice){
      case 1:
        System.out.println("Enter branch name");
        String branch = sc.nextLine();
        filterStaffList=HQ.filter_method(branch);
        break;
      case 2:
        System.out.println("Enter gender (MALE/FEMALE)");
        String gender_str = sc.nextLine();
        Staff.Gender gender = null;
        if(gender_str.equals("MALE")){
          gender = Staff.Gender.MALE;
        }
        else if(gender_str.equals("FEMALE")){
          gender = Staff.Gender.FEMALE;
        }
        filterStaffList=HQ.filter_method(gender);
        break;
      case 3:
        System.out.println("Enter role (STAFF/MANAGER)");
        String role_str = sc.nextLine();
        Staff.Role role = null;
        if(role_str.equals("STAFF")){
          role = Staff.Role.STAFF;
        }
        else if(role_str.equals("MANAGER")){
          role = Staff.Role.MANAGER;
        }
        filterStaffList=HQ.filter_method(role);
        break;
      case 4:
        System.out.println("Enter min age");
        int minAge = sc.nextInt();
        System.out.println("Enter max age");
        int maxAge = sc.nextInt();
        filterStaffList=HQ.filter_method(minAge,maxAge);
        break;
      default:
      System.out.println("Invalid choice. Please enter a number between 0 and 4.\n");
        break;
    }
    filterStaffList.displayStaffList();
  }  
}
