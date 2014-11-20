package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
  public class Post extends Model {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    private String firstName;
    private String topicText;
    
    public Post(String firstName, String topicText) {
      this.firstName = firstName;
      this.topicText = topicText;
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

}