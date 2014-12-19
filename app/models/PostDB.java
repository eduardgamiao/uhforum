package models;

import java.util.Date;
import java.util.List;
import views.formdata.PostFormData;

public class PostDB {
  
  public static long addPost(String firstName, String lastName, String topicText, String subject) {
    Post post = new Post(firstName, lastName, topicText, subject);
    post.save();
    return post.getId();
  }
  
  public static long addPost(String firstName, String lastName, String topicText, String subject, UserInfo user) {
    Post post = new Post(firstName, lastName, topicText, subject, user);
    post.save();
    return post.getId();
  }
  
  /**
   * Add a post to the database.
   * @param topicText The text of the topic.
   * @param topic The topic being posted to.
   * @param user The poster of the post.
   * @return The ID of the post.
   */
  public static long addPost(PostFormData posts, Topic topic, UserInfo user) {
    Post post = new Post(posts.topicText,posts.image,posts.video, topic, user);
    topic.setDatePosted(new Date());
    post.save();
    topic.save();
    return post.getId();
  }
  
  /**
   * Add a post to the database.
   * @param text The text of the topic.
   * @param image Image links.
   * @param video Video links
   * @param topic The topic being posted to.
   * @param user The poster of the post.
   * @return The ID of the post.
   */
  public static long addPost(String text, String image, String video, Topic topic, UserInfo user) {
    Post post = new Post(text, image, video, topic, user);
    topic.setDatePosted(new Date());
    post.save();
    topic.save();
    return post.getId();
  }
  
  
  
/*  public static Post getPostFirst(String firstName) {
    return Post.find().where().eq("firstName", firstName).findUnique();
  }
    
    public static Post getPostLast(String lastName) {
      return Post.find().where().eq("lastName", lastName).findUnique(); 
  } 
    public static Post getSubject(String subject) {
      return Post.find().where().eq("subject", subject).findUnique();
    }*/

  public static List<Post> getPosts() {
    return Post.find().all();
  }

}
  