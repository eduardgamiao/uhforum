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
  
  /**
   * Return a topic.
   * @param id The ID of the topic.
   * @return The topic matching the id.
   */
  public static Topic getTopic(Long id) {
    return Topic.find().byId(id);
  }

  /**
   * Retrieve a topic by subject.
   * @param subject The subject to search for.
   * @return A list of topics with the matching topic.
   */
  public static List<Topic> getTopicsBySubject(Subject subject) {
    return Topic.find().where().eq("subject", subject).findList();
  }
  
  /**
   * Get a list of topics sorted by date.
   * @param subject The subject of the topic to find.
   * @return A list of topics.
   */
  public static List<Topic> getTopicsBySubjectSorted(Subject subject) {
    return Topic.find().where().eq("subject", subject).orderBy("datePosted, datePosted asc").findList();
  }
  
  /**
   * Return a list of matching topics.
   * @param searchTerm The search term.
   * @return A list of topics with the search term in its title.
   */
  public static List<Topic> getTopicsBySearch(String searchTerm) {
    return Topic.find().where().icontains("title", searchTerm).findList();
    //return Topic.find().where().icontains("title", searchTerm).findPagingList(10).getPage(0).getList();
  }
  
  public static List<Topic> getTopics() {
    return Topic.find().all();
  }
}
  