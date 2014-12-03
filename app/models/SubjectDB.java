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
  
  public static Subject getSubjectByAcronym(String acronym) {
    return Subject.find().where().eq("acronym", acronym.toUpperCase()).findUnique();
  }
  
  public static List<Subject> getSubjects() {
    return Subject.find().all();
  }

}
