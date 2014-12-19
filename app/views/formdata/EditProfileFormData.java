package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.UserInfo;
import models.UserInfoDB;

/**
 * Handles profile edits.
 * @author eduardgamiao
 */
public class EditProfileFormData {
  
  /** User ID. */
  public long id = -1;
  
  /** User email. */
  public String email;
  
  /** Username. */
  public String name;
  
  /** User's password. */
  public String verifyPassword = "";
  
  /** First password entry. */
  public String password1 = "";
  
  /** Second password entry. */
  public String password2 = "";
  
  /** User's avatar URL. */
  public String avatarURL;
  
  /**
   * Default constructor.
   * @author eduardgamiao
   */
  public EditProfileFormData() {
    
  }
  
  /**
   * Constructor.
   * @param user User being edited.
   * @author eduardgamiao
   */
  public EditProfileFormData(UserInfo user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.name = user.getName();
    this.avatarURL = user.getAvatarURL();
  }
  
  /**
   * Validate the form data.
   * @return A list of errors.
   * @author eduardgamiao
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    UserInfo editedUser = UserInfoDB.getUser(this.id);
    
    if (this.verifyPassword == null || this.verifyPassword.length() == 0) {
      errors.add(new ValidationError("verifyPassword", ""));     
    }
    else if (!(verifyPassword.equals(editedUser.getPassword())) || editedUser == null) {
      errors.add(new ValidationError("verifyPassword", "Incorrect password."));
    }
    if (this.email != null || this.email.length() > 0) {
      UserInfo user = UserInfoDB.getUser(this.email);
      if (user != null && user.getId() != editedUser.getId()) {
        errors.add(new ValidationError("email", "The email \"" + this.email + "\" is already in use."));
      }
    }
    if (this.name != null || this.name.length() > 0) {
      UserInfo user = UserInfoDB.getUserByName(this.name);
      if (user != null && user.getId() != editedUser.getId()) {
        errors.add(new ValidationError("name", "The username \"" + this.name + "\" is already in use."));
      }
    }
    if ((this.password1 != null || this.password1.length() > 0) 
        && (this.password2 != null || this.password2.length() > 0)) {
      if (!(this.password1.equals(this.password2))) {
        errors.add(new ValidationError("password1", "The passwords do not match."));       
      }
    }
    
    return errors.isEmpty() ? null : errors;
  }

}
