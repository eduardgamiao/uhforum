package models;

import java.util.List;
import views.formdata.SignupFormData;

/**
 * Manages the user database.
 * @author eduardgamiao
 *
 */
public class UserInfoDB {
  
  /**
   * Add user to database.
   * @param formData The SignupFormData.
   * @return The ID of the added user.
   */
  public static long addUser(SignupFormData formData) {
    UserInfo user;
    if (formData.id == -1) {
      user = new UserInfo(formData.firstName, formData.lastName, formData.email, formData.password1);
    }
    else {
      user = UserInfoDB.getUser(formData.id);
      user.setFirstName(formData.firstName);
      user.setLastName(formData.lastName);
      user.setEmail(formData.email);
      user.setPassword(formData.password1);
    }
    user.save();
    return user.getId();
  }
  
  /**
   * Adds a user to the database.
   * @param firstName The first name of the user.
   * @param lastName The last name of the user.
   * @param email The email of the user.
   * @param password The password of the user.
   * @return The ID of the user.
   */
  public static long addUser(String firstName, String lastName, String email, String password) {
    UserInfo user = new UserInfo(firstName, lastName, email, password);
    user.save();
    return user.getId();
  }
  
  /**
   * Returns a user by ID.
   * @param id The ID of the user.
   * @return The user that matches the ID, if they exist, otherwise null.
   */
  public static UserInfo getUser(long id) {
    return UserInfo.find().byId(id);
  }
  
  /**
   * Return a user based on email.
   * @param email The email of the user.
   * @return The user matching the specified email, otherwise null.
   */
  public static UserInfo getUser(String email) {
    return UserInfo.find().where().eq("email", email).findUnique();      
  }

  /**
   * Get a list of all users.
   * @return A list of all users.
   */
  public static List<UserInfo> getUsers() {
    return UserInfo.find().all();
  }

  /**
   * Check if the password is valid.
   * @param email Email of the user.
   * @param password Password of the user.
   * @return True if the password matches, otherwise false. 
   */
  public static boolean isValidPassword(String email, String password) {
    if ((email != null && email.length() != 0) && (password != null && password.length() != 0)) {
      UserInfo user = getUser(email);
      if (user != null) {
        return user.getPassword().equals(password);
      }
      return false;
    }
    return false;
  }
  
  /**
   * Check if a user exists.
   * @param email The email of the user.
   * @return True if the user exists, false otherwise.
   */
  public static boolean doesUserExist(String email) {
    return (getUser(email) == null);
  }

}
