package models;

import java.util.Date;
import java.util.List;
import views.formdata.PostFormData;

/**
 * Handles database operations for Post.
 * @author Brent
 *
 */
public class PostDB {
  
  /**
   * Add post to database.
   * @param firstName The user's first name.
   * @param lastName The user's last name.
   * @param topicText The post text.
   * @param subject The post's subject.
   * @return The ID of the post.
   * @author Brent
   */
  public static long addPost(String firstName, String lastName, String topicText, String subject) {
    Post post = new Post(firstName, lastName, topicText, subject);
    post.save();
    return post.getId();
  }
  
  /**
   * Add post to database.
   * @param firstName The user's first name.
   * @param lastName The user's last name.
   * @param topicText The post's text.
   * @param subject The post's subject.
   * @param user The user.
   * @return The ID of the post.
   * @author Brent
   */
  public static long addPost(String firstName, String lastName, String topicText, String subject, UserInfo user) {
    Post post = new Post(firstName, lastName, topicText, subject, user);
    post.save();
    return post.getId();
  }
  
  /**
   * Add a post to the database.
   * @param posts The formdata for posts.
   * @param topic The topic being posted to.
   * @param user The poster of the post.
   * @return The ID of the post.
   * @author Brent
   */
  public static long addPost(PostFormData posts, Topic topic, UserInfo user) {
    Post post = new Post(posts.topicText, posts.image, posts.video, topic, user);
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
   * @author Brent
   */
  public static long addPost(String text, String image, String video, Topic topic, UserInfo user) {
    Post post = new Post(text, image, video, topic, user);
    topic.setDatePosted(new Date());
    post.save();
    topic.save();
    return post.getId();
  }

  /**
   * Get all posts.
   * @return A list of all posts in the database.
   * @author Brent
   */
  public static List<Post> getPosts() {
    return Post.find().all();
  }

}
  