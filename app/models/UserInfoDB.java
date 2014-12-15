package models;

import java.util.List;
import views.formdata.EditProfileFormData;
import views.formdata.SignupFormData;

/**
 * Manages the user database.
 * @author eduardgamiao
 */
public class UserInfoDB {
  
  /**
   * Add user to database.
   * @param formData The SignupFormData.
   * @return The ID of the added user.
   * @author eduardgamiao
   */
  public static long addUser(SignupFormData formData) {
    UserInfo user;
    if (formData.id == -1) {
      user = new UserInfo(formData.name, formData.email, formData.password1);
    }
    else {
      user = UserInfoDB.getUser(formData.id);
      user.setName(formData.name);
      user.setEmail(formData.email);
      user.setPassword(formData.password1);
    }
    user.save();
    return user.getId();
  }
  
  /**
   * Update user data.
   * @param formData The processed form data.
   * @return The ID of the edited user.
   * @author eduardgamiao
   */
  public static Long addUser(EditProfileFormData formData) {
    UserInfo user;
    if (formData.id == -1) {
      user = new UserInfo(formData.name, formData.email, formData.password1);
    }
    else {
      user = UserInfoDB.getUser(formData.id);
      user.setName(formData.name);
      user.setEmail(formData.email);
      if (!(formData.password1 == null || formData.password1.isEmpty())) {
        if (formData.verifyPassword.equals(user.getPassword())) {
          user.setPassword(formData.password1);
        }
      }
      if (!(formData.avatarURL == null || formData.avatarURL.length() == 0)) {
        user.setAvatarURL(formData.avatarURL);
      }
      else {
        user.setAvatarURL("");
      }
    }
    user.save();
    return user.getId();  
  }
  
  /**
   * Adds a user to the database.
   * @param name The username.
   * @param email The email of the user.
   * @param password The password of the user.
   * @return The ID of the user.
   * @author eduardgamiao
   */
  public static long addUser(String name, String email, String password) {
    UserInfo user = new UserInfo(name, email, password);
    user.save();
    return user.getId();
  }
  
  /**
   * Adds a user to the database.
   * @param name The username.
   * @param email The email of the user.
   * @param password The password of the user.
   * @param avatar  User's avatar URL.
   * @return The ID of the user.
   * @author eduardgamiao
   */
  public static long addUser(String name, String email, String password, String avatar) {
    UserInfo user = new UserInfo(name, email, password, avatar);
    user.save();
    return user.getId();
  }
  
  /**
   * Returns a user by ID.
   * @param id The ID of the user.
   * @return The user that matches the ID, if they exist, otherwise null.
   * @author eduardgamiao
   */
  public static UserInfo getUser(long id) {
    return UserInfo.find().byId(id);
  }
  
  /**
   * Return a user based on email.
   * @param email The email of the user.
   * @return The user matching the specified email, otherwise null
   * @author eduardgamiao.
   */
  public static UserInfo getUser(String email) {
    return UserInfo.find().where().eq("email", email).findUnique();      
  }
  
  /**
   * Check if a username is taken.
   * @param name The name to check.
   * @return True if the username is taken, false otherwise.
   * @author eduardgamiao
   */
  public static boolean isUserNameTaken(String name) {
    return (UserInfo.find().where().eq("name", name).findUnique() != null);        
  }

  /**
   * Get a list of all users.
   * @return A list of all users.
   * @author eduardgamiao
   */
  public static List<UserInfo> getUsers() {
    return UserInfo.find().all();
  }

  /**
   * Check if the password is valid.
   * @param email Email of the user.
   * @param password Password of the user.
   * @return True if the password matches, otherwise false. 
   * @author eduardgamiao
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
   * @author eduardgamiao
   */
  public static boolean doesUserExist(String email) {
    return (getUser(email) == null);
  }
  
  /**
   * Get a user by their name.
   * @param name The name of the user.
   * @return The user with the maching name (if it exists), otherwise null.
   * @author eduardgamiao
   */
  public static UserInfo getUserByName(String name) {
    return UserInfo.find().where().eq("name", name).findUnique();
  }


}
