import models.UserInfo;
import models.UserInfoDB;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {
  
  public void onStart(Application app) {
    if(UserInfoDB.getUsers().isEmpty()) {
     UserInfoDB.addUser("Site", "Administrator", "admin@abc.com", "pw");
    }
  }

}
