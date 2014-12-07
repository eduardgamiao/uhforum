package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * User model.
 * @author eduardgamiao
 *
 */
@Entity
public class UserInfo extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  private String name;
  private String email;
  private String password;
  private String avatarURL;
  
  @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
  private List<Topic> topics = new ArrayList<Topic>();
  
  @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
  private List<Post> posts = new ArrayList<Post>();
  
  /**
   * Constructor.
   * @param name Name.
   * @param email Email.
   * @param password Password.
   */
  public UserInfo(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.setPassword(password);
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * Return a list of topics.
   * @return A list of topics.
   */
  public List<Topic> getTopics() {
    return topics;
  }
  
  /**
   * Return a list of post.
   * @return A list of post.
   */
  public List<Post> getPosts() {
    return posts;
  }

  /**
   * @return the avatarURL
   */
  public String getAvatarURL() {
    return avatarURL;
  }

  /**
   * @param avatarURL the avatarURL to set
   */
  public void setAvatarURL(String avatarURL) {
    this.avatarURL = avatarURL;
  }
  
  /**
   * Check if user has an avatar URL.
   * @return True if the user has an avatar url, false otherwise.
   */
  public boolean hasAvatar() {
    return (this.avatarURL != null && this.avatarURL.length() == 0);
  }

  /**
   * Finder method for database functions.
   * @return A Finder instance.
   */
  public static Finder<Long, UserInfo> find() {
    return new Finder<Long, UserInfo>(Long.class, UserInfo.class);
  }
}
