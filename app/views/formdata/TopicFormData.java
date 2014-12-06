package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.UserInfoDB;
import play.Logger;
import play.data.validation.ValidationError;

/**
 * Handles the signup form's data and validation.
 * @author eduardgamiao
 *
 */
public class TopicFormData {
  
  /** User's ID. */
  public long id = -1;
  
  /** User's email. */
  public String email;
  
  /** User's First Name. */
  public String firstName;
  
  /** User's last name. */
  public String lastName;
  
  /** First password input. */
  public String password1 = "";
  
  /** Second password input. */
  public String password2 = "";
  
  /**
   * Validates the form data.
   * @return A list of ValidationErrors.
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    
    return errors.isEmpty() ? null : errors;
  }

}
