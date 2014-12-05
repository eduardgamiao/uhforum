package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
  public class Topic extends Model {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    private String firstName;
    private String title;
    private String tags;
    @Lob
    private String topicText;
    private String subject;
    private int views;
    @Lob
    private String images;
    @Lob
    private String videos;
    private Date datePosted;
    
    @ManyToOne
    private Post posts;
    
    @ManyToOne
    private UserInfo user;    

    public Topic(String firstName, String title, String tags, String topicText, String subject, int views) {
      this.firstName = firstName;
      this.title = title;
      this.tags = tags;
      this.topicText = topicText;
      this.subject = subject;
      this.views = views;
      this.setDatePosted(new Date());
    }
    
    public Topic(String firstName, String title, String tags, String topicText, String subject, int views,
                 UserInfo user) {
      this.firstName = firstName;
      this.title = title;
      this.tags = tags;
      this.topicText = topicText;
      this.subject = subject;
      this.views = views;
      this.setDatePosted(new Date());
      this.user = user;
    }
    
    /**
     * Constructor.
     * @param title Title.
     * @param tags Tags.
     * @param topicText Topic text.
     * @param subject Subject.
     * @param images Images.
     * @param videos Videos.
     * @param user Topic creator.
     */
    public Topic(String title, String tags, String topicText, String subject, String images, String videos,
        UserInfo user) {
      this.title = title;
      this.tags = tags;
      this.topicText = topicText;
      this.subject = subject;
      this.images = images;
      this.videos = videos;
      this.setDatePosted(new Date());
      this.user = user;
    }

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
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
     * @return the title
     */
    public String getTitle() {
      return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
      this.title = title;
    }

    /**
     * @return the tags
     */
    public String getTags() {
      return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
      this.tags = tags;
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
     * @return the views
     */
    public int getViews() {
      return views;
    }

    /**
     * @param views the views to set
     */
    public void setViews(int views) {
      this.views = views;
    }
    
    /**
     * @return the images
     */
    public String getImages() {
      return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(String images) {
      this.images = images;
    }

    /**
     * @return the videos
     */
    public String getVideos() {
      return videos;
    }

    /**
     * @param videos the videos to set
     */
    public void setVideos(String videos) {
      this.videos = videos;
    }

    /**
     * @return the datePosted
     */
    public Date getDatePosted() {
      return datePosted;
    }

    /**
     * @param datePosted the datePosted to set
     */
    public void setDatePosted(Date datePosted) {
      this.datePosted = datePosted;
    }

    /**
     * @return the user
     */
    public UserInfo getUser() {
      return user;
    }

    public static Finder<Long, Topic> find() {
      return new Finder<Long, Topic>(Long.class, Topic.class);
    }

}