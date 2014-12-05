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
  
  /**
   * Add topic to database.
   * @param title Title of topic.
   * @param tags Tags of topic.
   * @param topicText Text of topic.
   * @param subject Subject of topic.
   * @param images Images for the topic.
   * @param videos Videos of the topic.
   * @param user Topic creator.
   * @return The ID of the topic being created.
   */
  public static long addTopic(String title, String tags, String topicText, String subject, String images, String videos,
      UserInfo user) {
    Topic topic = new Topic(title, tags, topicText, subject, images, videos, user);
    topic.save();
    return topic.getUser().getId();
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

  /**
   * Retrieve a topic by subject.
   * @param subject The subject to search for.
   * @return A list of topics with the matching topic.
   */
  public static List<Topic> getTopicsBySubject(String subject) {
    return Topic.find().where().eq("subject", subject).findList();
  }
  
  public static List<Topic> getTopicsBySubjectSorted(String subject) {
    return Topic.find().where().eq("subject", subject).orderBy("datePosted, datePosted asc").findList();
  }
  
  public static List<Topic> getTopics() {
    return Topic.find().all();
  }
}
  