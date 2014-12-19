package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;

/**
 * Handles form data for Post.
 * @author Brent
 *
 */
public class PostFormData {
  
  /** Post text. */
  public String topicText;
  
  /** Post images. */
  public String image;
  
  /** Post videos. */
  public String video;

  /**
   * Validate form data.
   * @return A list of errors.
   * @author Brent
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    
    if (this.topicText == null || this.topicText.length() == 0) {
      errors.add(new ValidationError("topicText", "Text is a required field."));
    }
    
    return errors.isEmpty() ? null : errors;
  }

}
