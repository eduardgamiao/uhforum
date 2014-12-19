package views.formdata;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import models.Subject;
import models.SubjectDB;

/**
 * Represents surfer types.
 */

public class SubjectTypes {
  
  private static List<Subject> types = SubjectDB.getSubjects();
  
  /**
   * Returns an initialized Map of surfer types (when no type is selected).
   * @return The surfer type map.
   */
  public static Map<Subject, Boolean> getTypes() {
    Map<Subject, Boolean> typeMap = new LinkedHashMap<>();
    for (Subject type : types) {
      typeMap.put(type, false);
    }
    return typeMap;
  }
  
  /**
   * Returns a Map of the selected surfer type.
   * @param surfType The type of surfer.
   * @return The surfer type map.
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
   * @param surfType The surfer type.
   * @return True if valid, else false.
   */
  public static boolean isType(Subject subjectType) {
    return SubjectTypes.getTypes().keySet().contains(subjectType);
  }
}
