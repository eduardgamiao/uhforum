package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
  public class Post extends Model {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @Column(columnDefinition = "TEXT")
    private String topicText;
    private String subject;
    private Date datePosted;
    
    @ManyToOne
    private Topic topic;
    
    @ManyToOne
    private UserInfo user;    

    public Post(String firstName, String topicText, String lastName, String subject) {
      this.firstName = firstName;
      this.topicText = topicText;
      this.lastName = lastName;
      this.subject = subject;
      this.setDatePosted(new Date());
    }
    
    public Post(String firstName, String topicText, String lastName, String subject,
        UserInfo user) {
      this.firstName = firstName;
      this.topicText = topicText;
      this.subject = subject;
      this.setDatePosted(new Date());
      this.user = user;
    }
    
    /**
     * Constructor.
     * @param topicText The text of the post.
     * @param topic The topic of the post.
     * @param user The poster.
     */
    public Post(String topicText, Topic topic, UserInfo user) {
      this.topicText = topicText;
      this.topic = topic;
      this.user = user;
      this.setDatePosted(new Date());
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
     * @return the firstName
     */
    public String getFirstName() {
      return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    /**
     * @return the topicText
     */
    public String getTopicText() {
      return topicText;
    }

    /**
     * @param topicText the topicText to set
     */
    public void setTopicText(String topicText) {
      this.topicText = topicText;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
      return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
      return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
      this.subject = subject;
    }
    
    /**
     * @return the datePosted
     */
    public String getDatePosted() {
      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
      String date = dateFormat.format(this.datePosted);
      return date;
    }

    /**
     * @param datePosted the datePosted to set
     */
    public void setDatePosted(Date datePosted) {
      this.datePosted = datePosted;
    }
    
    /**
     * @return the topic
     */
    public Topic getTopic() {
      return this.topic;
    }

    /**
     * @return the user
     */
    public UserInfo getUser() {
      return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserInfo user) {
      this.user = user;
    }
    
    public static Finder<Long, Post> find() {
      return new Finder<Long, Post>(Long.class, Post.class);
    }

}