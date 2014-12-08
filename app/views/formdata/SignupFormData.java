package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.UserInfoDB;
import play.data.validation.ValidationError;

/**
 * Handles the signup form's data and validation.
 * @author eduardgamiao
 *
 */
public class SignupFormData {
  
  /** User's ID. */
  public long id = -1;
  
  /** User's email. */
  public String email;
  
  /** User's Name. */
  public String name;
  
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
    
    if (this.email == null || this.email.length() == 0) {
      errors.add(new ValidationError("email", "An email address is required."));
    }
    if (this.name == null || this.name.length() == 0) {
      errors.add(new ValidationError("firstName", "First name is a required field."));
    }
    if (this.password1 == null || this.password1.length() == 0) {
      errors.add(new ValidationError("password1", "A password is required."));
    }
    if (this.password2 == null || this.password2.length() == 0) {
      errors.add(new ValidationError("password2", "Please re-enter the password."));
    }
    if (!(this.password1.equals(this.password2))) {
      errors.add(new ValidationError("password1", "The passwords do not match."));
    }
    if (!(UserInfoDB.getUser(this.email) == null)) {
      errors.add(new ValidationError("email", "The email \"" + this.email + "\" is already being used."));
    }
    if (UserInfoDB.isUserNameTaken(this.name)) {
      errors.add(new ValidationError("name", "The name \"" + this.name + "\" is already being used."));      
    }
    
    return errors.isEmpty() ? null : errors;
  }

}
