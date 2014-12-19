import java.util.List;
import java.util.Map;
import com.avaje.ebean.Ebean;
import models.PostDB;
import models.Subject;
import models.Topic;
import models.UserInfo;
import models.UserInfoDB;
import models.TopicDB;
import play.Application;
import play.GlobalSettings;
import play.Logger;
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
 // Populate Subject Database.
    if (Subject.find().all().isEmpty()) {
      @SuppressWarnings("unchecked")
      Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
      Ebean.save(all.get("subject"));
    } 
    
    if (UserInfoDB.getUsers().isEmpty()) {
     Long id = UserInfoDB.addUser("Site Administrator", "admin@abc.com", "pw");
     Long id2 = UserInfoDB.addUser("Eduard Gamiao", "eduard@gamiao.com", "notpw");
     UserInfo user = UserInfoDB.getUser(id);
     UserInfo user2 = UserInfoDB.getUser(id2);
     TopicDB.addTopic("test", "How's is Everyone", "itsatesttext", "testTag", "subjectTest", 0, user);
     TopicDB.addTopic("test", "Which Teachers did you enjoy", "itsatesttext", "testTag", "subjectTest", 0, user);
     TopicDB.addTopic("test", "UH General", "itsatesttext", "testTag", "subjectTest", 0, user);
     TopicDB.addTopic("test", "Campus Events", "itsatesttext", "testTag", "subjectTest", 0, user);
     Long topicID = TopicDB.addTopic("How do I succeed at French?", "hey, lol, <3 English", "I'm taking French next "
         + "semester and don't know what to expect. Help?", 
         "French", "http://i2.kym-cdn.com/photos/images/facebook/000/126/314/3cd8a33a.png", "", user);
     TopicDB.addTopic("How do I succeed at French 101?", "<3 English, all", "Read the title.", "French", 
         "http://i2.kym-cdn.com/photos/images/facebook/000/126/314/3cd8a33a.png, http://i.imgur.com/fa0Wmp2.gif", 
         "", user);
     TopicDB.addTopic("How do I succeed at French 201?", "<3 English, hey", "Read the title.", "French", 
         "http://i2.kym-cdn.com/photos/images/facebook/000/126/314/3cd8a33a.png, http://i.imgur.com/fa0Wmp2.gif", 
         "", user);
     Topic topic = TopicDB.getTopic(topicID);
     PostDB.addPost("Good luck! Seriously though, don't worry about it. French is as easy as ICS 311.", 
         "http://i.minus.com/iQ0AsS0yYNFCD.gif", "", topic, user2);
     PostDB.addPost("Thanks?", "", "", topic, user);     
     TopicDB.addTopic("Chavaux and Chaveux", "French, FR101", "Does anyone have a good way to remember which word is"
         + " which?", "French", "http://static-mb.minutebuzz.com/wp-content/uploads/2013/07/brock-davis-cheveux-"
             + "chevaux-545x736.jpg", "https://www.youtube.com/embed/uvpikUEIaLI", user2); 
    }
  }

}
