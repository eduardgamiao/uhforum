package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;

/**
 * Handles form data for User Account model.
 * @author Brent
 *
 */
public class TopicFormData {
  
  /** Topic title. */
  public String title;
  
  /** Topic text. */
  public String topicText;
  
  /** Topic tags. */
  public String tags;
  
  /** Topic images. */
  public String image;
  
  /** Topic videos. */
  public String video;
  
  /** Topic subject. */
  public String subject;
  
  /**
   * Validate form data.
   * @return A list of errors.
   * @author Brent
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    
    if (this.title == null || this.title.length() == 0) {
      errors.add(new ValidationError("title", "Title is a required field."));
    }
    
    return errors.isEmpty() ? null : errors;
  }

}
