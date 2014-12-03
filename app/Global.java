import models.UserInfo;
import models.UserInfoDB;
import models.TopicDB;
import play.Application;
import play.GlobalSettings;


/**
 * Global class.
 * @author eduardgamiao
 *
 */
public class Global extends GlobalSettings {
  
  /**
   * Initialization method.
   * @param app The application being run.
   */
  public void onStart(Application app) {
    if (UserInfoDB.getUsers().isEmpty()) {
     Long id = UserInfoDB.addUser("Site", "Administrator", "admin@abc.com", "pw");
     UserInfo user = UserInfoDB.getUser(id);
     TopicDB.addTopic("test", "guy", "itsatesttext", "testTag", "subjectTest", 0, user);
     TopicDB.addTopic("test", "guy2", "itsatesttext", "testTag", "subjectTest", 0, user);
     TopicDB.addTopic("test", "guy3", "itsatesttext", "testTag", "subjectTest", 0, user);
     TopicDB.addTopic("test", "guy4", "itsatesttext", "testTag", "subjectTest", 0, user);
    }
    

  }

}
