package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.UserInfoDB;
import play.data.validation.ValidationError;

/**
 * Handles form data for User Account model.
 * @author eduardgamiao
 *
 */
public class LoginFormData {
  
  public String email;
  public String password;

  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    
    if(this.email == null || this.email.length() == 0) {
      errors.add(new ValidationError("email", "Email is a required field."));
    }
    if(this.password == null || this.password.length() == 0) {
      errors.add(new ValidationError("password", "Password is a required field."));
    }
    if(UserInfoDB.getUser(this.email) == null) {
      errors.add(new ValidationError("email", "The email " + this.email + " is not a valid email"));
    }
    if(!UserInfoDB.isValidPassword(this.email, this.password)) {
      errors.add(new ValidationError("password", "The password is incorrect."));
    }
    
    return errors.isEmpty() ? null : errors;
  }

}
