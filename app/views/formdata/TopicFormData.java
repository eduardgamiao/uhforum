package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.Subject;
import models.UserInfoDB;
import models.TopicDB;
import play.data.validation.ValidationError;

/**
 * Handles form data for User Account model.
 * @author eduardgamiao
 *
 */
public class TopicFormData {
  
  public String title;
  public String topicText;
  public String tags;
  public String image;
  public String video;
  public String subject;
  

  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    
    if(this.title == null || this.title.length() == 0) {
      errors.add(new ValidationError("title", "Title is a required field."));
    }
    
    return errors.isEmpty() ? null : errors;
  }

}
