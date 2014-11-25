import java.util.List;

public class TopicDB {
  
  public static long addPost(String firstName, String lastName, String topicText, String subject) {
    Topic post = new Topic(firstName, lastName, topicText, subject);
    topic.save();
    return post.getId();
  }
  
  public static Topic getTopicFirst(String firstName) {
    return Post.find().where().eq("firstName", firstName).findUnique();
    
    public static Topic getTopicLast(String lastName) {
      return Topic.find().where().eq("lastName", lastName).findUnique(); 
  } 
    public static Topic getTopicSubject(String subject) {
      return Topic.find().where().eq("subject", subject).findUnique(); 
    }

  public static List<UserInfo> getTopics() {
    return Topic.find().all();
  }

}