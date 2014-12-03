package models;

import java.util.List;
import org.h2.expression.ExpressionList;

public class TopicDB {
  
  public static long addTopic(String firstName, String lastName, String topicText,String tags, String subject, int views) {
    Topic topic = new Topic(firstName, lastName, tags, topicText, subject, views);
    topic.save();
    return topic.getId();
  }
  
  public static long addTopic(String firstName, String lastName, String topicText,String tags, String subject, 
                              int views, UserInfo user) {
    Topic topic = new Topic(firstName, lastName, tags, topicText, subject, views, user);
    topic.save();
    return topic.getId();
  }
  
  public static List<Topic> getTopicFirst(String firstName) {
    List<Topic> test = Topic.find().where().eq("firstName", firstName).findList();  
    return test;
  }  
    /*public static Topic getTopicLast(String lastName) {
      return Topic.find().where().eq("lastName", lastName); 
  } 
    public static Topic getTopicSubject(String subject) {
      return Topic.find().where().eq("subject", subject); 
    }*/

  public static List<Topic> getTopics() {
    return Topic.find().all();
  }

}
  