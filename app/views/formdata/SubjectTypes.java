package views.formdata;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import models.Subject;
import models.SubjectDB;

/**
 * Handles subject types for forms.
 * @author Brent
 */
public class SubjectTypes {
  
  private static List<Subject> types = SubjectDB.getSubjects();
  
  /**
   * Returns an initialized Map of subjects.
   * @return The subject map.
   * @author Brent, eduardgamiao
   */
  public static Map<Subject, Boolean> getTypes() {
    Map<Subject, Boolean> typeMap = new LinkedHashMap<>();
    for (Subject type : types) {
      typeMap.put(type, false);
    }
    return typeMap;
  }
  
  /**
   * Returns a Map of the selected subjects.
   * @param subjectTypes The type of subject.
   * @return The subject type map.
   * @author Brent, eduardgamiao
   */
  public static Map<Subject, Boolean> getTypes(Subject subjectTypes) {
    Map<Subject, Boolean> typeMap = SubjectTypes.getTypes();
    if (isType(subjectTypes)) {
      typeMap.put(subjectTypes,  true);
    }
    return typeMap;
  }

  /**
   * Returns true if surfType is valid type.
   * @param subjectType The subject type.
   * @return True if valid, else false.
   * @author Brent
   */
  public static boolean isType(Subject subjectType) {
    return SubjectTypes.getTypes().keySet().contains(subjectType);
  }
}
