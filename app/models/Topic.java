package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.Logger;
import play.db.ebean.Model;

/**
 * Topic model.
 * @author Brent
 */
@Entity
  public class Topic extends Model {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    private String firstName;
    private String title;
    private String tags = "";
    @Column(columnDefinition = "TEXT")
    private String topicText;
    private int views;
    @Column(columnDefinition = "TEXT")
    private String images = "";
    @Column(columnDefinition = "TEXT")
    private String videos = "";
    private Date datePosted;
    
    @OneToMany (mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<Post>();
    
    @ManyToOne
    private UserInfo user;    
    
    @ManyToOne
    private Subject subject = SubjectDB.getSubjectBySubject("General");

    /**
     * Constructor.
     * @param firstName User's first name.
     * @param title Topic title.
     * @param tags Topic tags.
     * @param topicText Topic text.
     * @param subjectTitle Subject name.
     * @param views Amount of views.
     * @author Brent
     */
    public Topic(String firstName, String title, String tags, String topicText, String subjectTitle, int views) {
      this.firstName = firstName;
      this.title = title;
      this.tags = tags;
      this.topicText = topicText;
      this.views = views;
      this.setDatePosted(new Date());
      Subject subject = SubjectDB.getSubjectBySubject(subjectTitle);
      Logger.debug("" + (subject == null));
      if (subject != null) {
        this.subject = subject;
      }
    }
    
    /**
     * Constructor.
     * @param firstName User's first name.
     * @param title Topic title.
     * @param tags Topic tag.
     * @param topicText Topic text.
     * @param subjectTitle Topic subject name.
     * @param views The page views for the topic.
     * @param user The topic creator.
     * @author Brent
     */
    public Topic(String firstName, String title, String tags, String topicText, String subjectTitle, int views,
                 UserInfo user) {
      this.firstName = firstName;
      this.title = title;
      this.tags = tags;
      this.topicText = topicText;      
      this.views = views;
      this.setDatePosted(new Date());
      this.user = user;
      Subject subject = SubjectDB.getSubjectBySubject(subjectTitle);
      Logger.debug("" + (subject == null));
      if (subject != null) {
        this.subject = subject;
      }
    }
    
    /**
     * Constructor.
     * @param title Title.
     * @param tags Tags.
     * @param topicText Topic text.
     * @param subjectTitle Subject.
     * @param images Images.
     * @param videos Videos.
     * @param user Topic creator.
     * @author Brent
     */
    public Topic(String title, String tags, String topicText, String subjectTitle, String images, String videos,
        UserInfo user) {
      this.title = title;
      this.tags = tags;
      this.topicText = topicText;
      this.images = images;
      this.videos = videos;
      this.setDatePosted(new Date());
      this.user = user;
      Subject subject = SubjectDB.getSubjectBySubject(subjectTitle);
      Logger.debug("" + (subject == null));
      if (subject != null) {
        this.subject = subject;
      }
    }

    /**
     * Get the subject.
     * @return The subject.
     * @author Brent
     */
    public Subject getSubject() {
      return subject;
    }
    
    /**
     * Set the subject.
     * @param subject The subject.
     * @author Brent
     */
    public void setSubject(Subject subject) {
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
    public List<String> getTags() {
      List<String> tags = new ArrayList<String>();
      if (this.tags != null || this.tags.length() > 0) {
        for (String current : this.tags.split(",")) {
          tags.add(current.trim());
        }
      }
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
    public List<String> getImages() {
      List<String> images = new ArrayList<String>();
      if (!(this.images == null || this.images.length() == 0)) {
        for (String current : this.images.split(",")) {
          images.add(current.trim());
        }
      }
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
    public List<String> getVideos() {
      List<String> videos = new ArrayList<String>();
      if (!(this.videos == null || this.videos.length() == 0)) {
        for (String current : this.videos.split(",")) {
          videos.add(current.trim());
        }
      }
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
     * @return the posts
     */
    public List<Post> getPosts() {
      return posts;
    }

    /**
     * @return the user
     */
    public UserInfo getUser() {
      return user;
    }

    /**
     * Ebean finder method.
     * @return Finder instance for Topic.
     * @author Brent
     */
    public static Finder<Long, Topic> find() {
      return new Finder<Long, Topic>(Long.class, Topic.class);
    }

}