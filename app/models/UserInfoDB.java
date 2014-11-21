package models;

import java.util.List;

public class UserInfoDB {
  
  public static long addUser(String firstName, String lastName, String email, String password) {
    UserInfo user = new UserInfo(firstName, lastName, email, password);
    user.save();
    return user.getId();
  }
  
  public static UserInfo getUser(String email) {
    return UserInfo.find().where().eq("email", email).findUnique();      
  }

  public static List<UserInfo> getUsers() {
    return UserInfo.find().all();
  }

  public static boolean isValidPassword(String email, String password) {
    if((email != null && email.length() != 0) && (password != null && password.length() != 0)) {
      UserInfo user = getUser(email);
      if(user != null) {
        return user.getPassword().equals(password);
      }
      return false;
    }
    return false;
  }

}
