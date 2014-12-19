package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;

/**
 * Form data for tag searching.
 * @author eduardgamiao
 */
public class TagSearchFormData {
  
  /** Search tag. */
  public String tag;
  
  /** Subject. */
  public String subject;
  
  /**
   * Validate form data.
   * @return A list of errors.
   * @author eduardgamiao
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    
    if (this.tag == null || this.tag.length() == 0) {
      errors.add(new ValidationError("tag", "Please enter a tag to search for."));
    }
    
    return (errors.isEmpty()) ? null : errors;
  }
  
}