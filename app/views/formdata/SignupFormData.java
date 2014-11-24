package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.Logger;
import play.data.validation.ValidationError;

/**
 * Handles the signup form's data and validation.
 * @author eduardgamiao
 *
 */
public class SignupFormData {
  
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
    
    if (this.email == null || this.email.length() == 0) {
      errors.add(new ValidationError("email", "An email address is required."));
    }
    if (this.firstName == null || this.firstName.length() == 0) {
      errors.add(new ValidationError("firstName", "First name is a required field."));
    }
    if (this.lastName == null || this.lastName.length() == 0) {
      errors.add(new ValidationError("lastName", "Last name is a required field."));
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
    
    return errors.isEmpty() ? null : errors;
  }

}
