package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * Represents a subject.
 * @author eduardgamiao
 */
@Entity
public class Subject extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private long id;
  private String name;
  private String acronym;
  
  @OneToMany (mappedBy = "subject", cascade = CascadeType.ALL)
  private List<Topic> topics = new ArrayList<Topic>();
  
  /**
   * Constructor.
   * @param name Name of the subject.
   * @param acronym Acronym of the subject.
   * @author eduardgamiao
   */
  public Subject(String name, String acronym) {
    this.setName(name);
    this.setAcronym(acronym);    
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
   * @return the acronym
   */
  public String getAcronym() {
    return acronym;
  }

  /**
   * @param acronym the acronym to set
   */
  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }
  
  /**
   * Finder method for database functions.
   * @return A Finder instance.
   * @author eduardgamiao
   */
  public static Finder<Long, Subject> find() {
    return new Finder<Long, Subject>(Long.class, Subject.class);
  }

}
