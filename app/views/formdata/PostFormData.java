package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.UserInfoDB;
import models.TopicDB;
import play.data.validation.ValidationError;

/**
 * Handles form data for User Account model.
 * @author eduardgamiao
 *
 */
public class PostFormData {
  
  public String topicText;
  public String image;
  public String video;

  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    
    if(this.topicText == null || this.topicText.length() == 0) {
      errors.add(new ValidationError("topicText", "Text is a required field."));
    }
    
    return errors.isEmpty() ? null : errors;
  }

}
