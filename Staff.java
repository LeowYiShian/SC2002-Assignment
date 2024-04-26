package project;

/**
 * The Staff class represents a staff member of the fast food restaurant.
 */
public class Staff {  
	
 /**
 * Enumeration representing the gender of a staff member.
 */
  public enum Gender {
    MALE, FEMALE   
  }
  
  /**
   * Enumeration representing the role of a staff member.
   */
  public enum Role {
    STAFF, MANAGER, ADMIN
  }
  
  private String name;
  private String loginID;
  private Gender gender;
  private int age;
  private String branch;
  private Role role;
  private String password;

  /**
   * Constructs a Staff object with the specified details.
   *
   * @param name     The name of the staff member.
   * @param loginID  The login ID of the staff member.
   * @param gender   The gender of the staff member.
   * @param age      The age of the staff member.
   * @param branch   The branch where the staff member works.
   * @param role     The role of the staff member.
   * @param password The password of the staff member.
   */
  public Staff(String name, String loginID, Gender gender, int age, String branch, Role role, String password) {
    this.name = name;
    this.loginID = loginID;
    this.gender = gender;
    this.age = age;
    this.branch = branch;
    this.role = role;
    this.password = (password != null && !password.isEmpty()) ? password : "password";
  }
  
  /**
   * Gets the name of the staff member.
   *
   * @return The name of the staff member.
   */
  public String getName() {
    return name;
  }
  
  /**
   * Sets the name of the staff member.
   *
   * @param name The name of the staff member.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the login ID of the staff member.
   *
   * @return The login ID of the staff member.
   */
  public String getLoginID() {
    return loginID;
  }
  
  /**
   * Sets the login ID of the staff member.
   *
   * @param loginID The login ID of the staff member.
   */
  public void setLoginID(String loginID) {
    this.loginID = loginID;
  }

  /**
   * Gets the gender of the staff member.
   *
   * @return The gender of the staff member.
   */
  public Gender getGender() {
    return gender;
  }
  
  /**
   * Sets the gender of the staff member.
   *
   * @param gender The gender of the staff member.
   */
  public void setGender(Gender gender) {
    this.gender = gender;
  }

  /**
   * Gets the age of the staff member.
   *
   * @return The age of the staff member.
   */
  public int getAge() {
    return age;
  }
  
  /**
   * Sets the age of the staff member.
   *
   * @param age The age of the staff member.
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the branch where the staff member works.
   *
   * @return The branch where the staff member works.
   */
  public String getBranch() {
    return branch;
  }
  
  /**
   * Sets the branch where the staff member works.
   *
   * @param branch The branch where the staff member works.
   */
  public void setBranch(String branch) {
    this.branch = branch;
  }

  /**
   * Gets the role of the staff member.
   *
   * @return The role of the staff member.
   */
  public Role getRole() {
    return role;
  }
  
  /**
   * Sets the role of the staff member.
   *
   * @param role The role of the staff member.
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Gets the password of the staff member.
   *
   * @return The password of the staff member.
   */
  public String getPassword() {
    return password;
  }
 
  /**
   * Sets the password of the staff member.
   *
   * @param password The password of the staff member.
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
}
