package controllers;

import models.UserInfo;
import models.UserInfoDB;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.LoginFormData;
import views.formdata.SearchFormData;
import views.formdata.SignupFormData;
import views.html.Signup;
import views.html.index;
import views.html.Login;
import views.html.Profile;

/**
 * Handles user login, logout, signup and profiles.
 * @author eduardgamiao
 *
 */
public class Users extends Controller {
  
  /**
   * Returns the signup view.
   * @return The signup Result view.
   */
  public static Result signup() {
    Form<SignupFormData> formData = Form.form(SignupFormData.class);
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    return ok(Signup.render("Signup", formData, searchFormData));
  }
  
  /**
   * Handles the post signup event.
   * @return The user's profile page.
   */
  public static Result postSignup() {
    Form<SignupFormData> formData = Form.form(SignupFormData.class).bindFromRequest();
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);    
    
    if (formData.hasErrors()) {
      return badRequest(Signup.render("Signup", formData, searchFormData));
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
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    return ok(Login.render("Login", formData, searchFormData));
  }
  
  /**
   * Handles post login functions.
   * @return The index page if successful, otherwise the login page.
   */
  public static Result postLogin() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    
    if (formData.hasErrors()) {
      return badRequest(Login.render("Login", formData, searchFormData));
    }
    else {
      session().clear();
      session("email", formData.get().email);
      return ok(index.render("UH Forum", searchFormData));
    }
  }
  
  /**
   * Returns the profile page.
   * @param id The ID of the user.
   * @return The user's profile page (if it exists). The index page if not a valid user.
   */
  public static Result viewProfile(Long id) {
    UserInfo user = UserInfoDB.getUser(id);
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    if (user != null) {
      return ok(Profile.render("View Profile", user, searchFormData));
    }
    return redirect(routes.Application.index());
  }
  
  /**
   * Handles logout function.
   * @return The index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }
  
}
