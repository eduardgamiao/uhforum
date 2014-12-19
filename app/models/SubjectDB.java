package models;

import java.util.List;

/**
 * Handles database functions for subjects.
 * @author eduardgamiao
 *
 */
public class SubjectDB {
  
  /**
   * Get a subject by name.
   * @param subject The subject.
   * @return The subject matching the given name.
   */
  public static Subject getSubjectBySubject(String subject) {
    return Subject.find().where().eq("name", subject).findUnique();
  }
  
  /**
   * Get the acronym of the subject matching the subject.
   * @param subjectTitle The subject's title.
   * @return The subject matching the title.
   */
  public static String getSubjectAcronym(String subjectTitle) {
    Subject subject = getSubjectBySubject(subjectTitle);    
    return (subject == null) ? "" : subject.getAcronym();
  }
  
  /**
   * Get a subject based on acronym.
   * @param acronym The acronym.
   * @return The subject matching the acronym.
   */
  public static Subject getSubjectByAcronym(String acronym) {
    return Subject.find().where().eq("acronym", acronym.toUpperCase()).findUnique();
  }
  
  /**
   * Get a list of all subjects.
   * @return A list of all subjects.
   */
  public static List<Subject> getSubjects() {
    return Subject.find().all();
  }

}
