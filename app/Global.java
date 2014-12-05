import java.util.List;
import java.util.Map;
import com.avaje.ebean.Ebean;
import models.Subject;
import models.UserInfo;
import models.UserInfoDB;
import models.TopicDB;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;


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
     TopicDB.addTopic("How do I succeed at French?", "lol, <3 English", "Read the title.", "French", 
         "http://i2.kym-cdn.com/photos/images/facebook/000/126/314/3cd8a33a.png, http://i.imgur.com/fa0Wmp2.gif", 
         "", user);
    }
    
    // Populate Subject Database.
    if (Subject.find().all().isEmpty()) {
      @SuppressWarnings("unchecked")
      Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
      Ebean.save(all.get("subject"));
      Subject general = new Subject("General", "GENERAL");
      general.save();
    }    

  }

}
