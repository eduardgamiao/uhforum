package models;

import java.util.List;

/**
 * Handles database functions for subjects.
 * @author eduardgamiao
 *
 */
public class SubjectDB {
  
  public static Subject getSubjectBySubject(String subject) {
    return Subject.find().where().eq("name", subject).findUnique();
  }
  
  public static String getSubjectAcronym(String subjectTitle) {
    Subject subject = getSubjectBySubject(subjectTitle);    
    return (subject == null) ? "" : subject.getAcronym();
  }
  
  public static Subject getSubjectByAcronym(String acronym) {
    return Subject.find().where().eq("acronym", acronym.toUpperCase()).findUnique();
  }
  
  /**
   * Get a list of all subjects.
   * @return A list of subjects.
   */
  public static List<Subject> getSubjects() {
    return Subject.find().all();
  }

}
