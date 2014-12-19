package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Subject;
import models.Topic;
import models.TopicDB;
import models.UserInfo;
import models.UserInfoDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.EditProfileFormData;
import views.formdata.LoginFormData;
import views.formdata.SignupFormData;
import views.formdata.SubjectTypes;
import views.formdata.TopicFormData;
import views.html.*;

/**
 * Handles user login, logout, signup and profiles.
 * @author eduardgamiao
 *
 */
public class Users extends Controller {
  
  /**
   * Post a topic.
   * @return The new topic page.
   * @author Brent
   */
  public static Result postTopic() {
    Form<TopicFormData> formData = Form.form(TopicFormData.class);
    Map<Subject, Boolean> subjectTypeMap = SubjectTypes.getTypes();
    return ok(PostTopics.render("Post Topic", formData, subjectTypeMap));
  }
  
  /**
   * Returns the signup view.
   * @return The signup Result view.
   * @author eduardgamiao
   */
  public static Result signup() {
    Form<SignupFormData> formData = Form.form(SignupFormData.class);
    return ok(Signup.render("Signup", formData));
  }
  
  /**
   * Handles the post signup event.
   * @return The user's profile page.
   * @author eduardgamiao
   */
  public static Result postSignup() {
    Form<SignupFormData> formData = Form.form(SignupFormData.class).bindFromRequest();
    
    if (formData.hasErrors()) {
      return badRequest(Signup.render("Signup", formData));
    }
    SignupFormData data = formData.get();
    Long id = UserInfoDB.addUser(data);
    session().clear();
    session("email", data.email);
    return redirect(routes.Users.viewProfile(id));
  }
  
  /**
   * Renders the login page for the website.
   * @return The Login view.
   * @author eduardgamiao
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    return ok(Login.render("Login", formData));
  }
  
  /**
   * Handles post login functions.
   * @return The index page if successful, otherwise the login page.
   * @author eduardgamiao
   */
  public static Result postLogin() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();
    
    if (formData.hasErrors()) {
      return badRequest(Login.render("Login", formData));
    }
    else {
      List<Topic> topicList = new ArrayList<Topic>();
      topicList = TopicDB.getTopics();
      session().clear();
      session("email", formData.get().email);
      return ok(Front.render("UH Forum", topicList));
    }
  }
  
  /**
   * Returns the profile page.
   * @param id The ID of the user.
   * @return The user's profile page (if it exists). The index page if not a valid user.
   * @author eduardgamiao
   */
  public static Result viewProfile(Long id) {
    UserInfo user = UserInfoDB.getUser(id);
    if (user != null) {
      return ok(Profile.render("View Profile", user));
    }
    return redirect(routes.Application.index());
  }
  
  /**
   * User information management.
   * @param id The ID of the user.
   * @return The user edit page (if valid) or the index page.
   * @author eduardgamiao
   */
  @Security.Authenticated(Secured.class)
  public static Result editProfile(Long id) {
    UserInfo user = UserInfoDB.getUser(id);
    if (user != null) {
      if (Secured.getUserInfo(ctx()).getId() == id) {
        EditProfileFormData data = new EditProfileFormData(user);
        Form<EditProfileFormData> formData = Form.form(EditProfileFormData.class).fill(data);
        return ok(EditProfile.render(formData, user));
      }
    }
    return redirect(routes.Users.viewProfile(Secured.getUserInfo(ctx()).getId()));
  }
  
  /**
   * Handles edit profile submission.
   * @param id The ID of the user.
   * @return The profile page of the user or the edit profile form if the form has errors.
   * @author eduardgamiao
   */
  @Security.Authenticated(Secured.class)
  public static Result postEditProfile(Long id) {
    Form<EditProfileFormData> formData = Form.form(EditProfileFormData.class).bindFromRequest();
    UserInfo user = UserInfoDB.getUser(id);
    if (formData.hasErrors() || user == null) {
      return badRequest(EditProfile.render(formData, user));
    }
    EditProfileFormData data = formData.get();
    UserInfoDB.addUser(data);
    session().clear();
    session("email", formData.get().email);
    return redirect(routes.Users.viewProfile(id));
  }
  
  /**
   * Handles logout function.
   * @return The index page.
   * @author eduardgamiao
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }
  
}
