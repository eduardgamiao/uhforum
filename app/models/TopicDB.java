package models;

import java.util.List;
import org.h2.expression.ExpressionList;
import play.Logger;
import views.formdata.TopicFormData;
import com.avaje.ebean.PagingList;

public class TopicDB {
    private static final int PAGE_SIZE = 10;
  
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
  
  public static void addTopic(TopicFormData formData, UserInfo user) {
    Topic topic = new Topic(formData.title, formData.tags, formData.topicText, formData.subject, formData.image, formData.video, user);
    topic.save();
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
   * @author eduardgamiao
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
    return Topic.find().where().eq("subject", subject).order().desc("date_posted").findList();
  }
  
  /**
   * Get a list of topics sorted by date.
   * @param subject The subject of the topic to find.
   * @return A list of topics.
   */
  public static List<Topic> getTopicsBySubjectSorted(Subject subject) {
    return Topic.find().where().eq("subject", subject).order().desc("date_posted").findList();
  }
  
  /**
   * Return a list of matching topics.
   * @param searchTerm The search term.
   * @param subject Subject title.
   * @return A list of topics with the search term in its title.
   */
  public static PagingList<Topic> getTopicsBySearch(String searchTerm, String subject) {
    if (!subject.isEmpty()) {
      return Topic.find().where().eq("subject.name", subject).icontains("title", searchTerm)
             .findPagingList(PAGE_SIZE);
    }
    else {
      return Topic.find().where().icontains("title", searchTerm).findPagingList(PAGE_SIZE);
    }
  }
  
  /**
   * Return a list of matching topics.
   * @param tag The tag to search for.
   * @param subject The subject of the topics.
   * @return A list of matching topics.
   */
  public static PagingList<Topic> getTopicByTag(String tag, Subject subject) {
    if (subject == null) {
      return Topic.find().where().icontains("tags", tag)
             .order().desc("date_posted").findPagingList(PAGE_SIZE);      
    }
    else {
    return Topic.find().where().eq("subject", subject).icontains("tags", tag)
           .order().desc("date_posted").findPagingList(PAGE_SIZE);
    }
  }
  
  /**
   * Increment the view of a page.
   * @param id The ID of the page to view.
   * @author eduardgamiao
   */
  public static void addView(Long id) {
    Topic topic = TopicDB.getTopic(id);
    if (topic != null) {
      topic.setViews(topic.getViews() + 1);
      topic.save();
    }
  }
  
  public static List<Topic> getTopics() {
    return Topic.find().all();
  }
}
  