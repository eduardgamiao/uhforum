package models;

import java.util.List;

public class TopicDB {
  
  public static long addTopic(String firstName, String lastName, String topicText,String tags, String subject, int views) {
    Topic topic = new Topic(firstName, lastName, tags, topicText, subject, views);
    topic.save();
    return topic.getId();
  }
  
  public static Topic getTopicFirst(String firstName) {
    return Topic.find().where().eq("firstName", firstName).findUnique();
  }  
    public static Topic getTopicLast(String lastName) {
      return Topic.find().where().eq("lastName", lastName).findUnique(); 
  } 
    public static Topic getTopicSubject(String subject) {
      return Topic.find().where().eq("subject", subject).findUnique(); 
    }

  public static List<Topic> getTopics() {
    return Topic.find().all();
  }

}
  