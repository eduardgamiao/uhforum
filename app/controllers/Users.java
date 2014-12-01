package controllers;

import models.UserInfoDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.LoginFormData;
import views.formdata.SignupFormData;
import views.html.Signup;
import views.html.index;
import views.html.Login;

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
    return ok(Signup.render("Signup", formData));
  }
  
  /**
   * Handles the post signup event.
   * @return The user's profile page.
   */
  public static Result postSignup() {
    Form<SignupFormData> formData = Form.form(SignupFormData.class).bindFromRequest();
    
    if (formData.hasErrors()) {
      return badRequest(Signup.render("Signup", formData));
    }
    SignupFormData data = formData.get();
    UserInfoDB.addUser(data);
    return redirect(routes.Application.index());
  }
  
  /**
   * Renders the login page for the website.
   * @return The Login view.
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    return ok(Login.render("Login", formData));
  }
  
  /**
   * Handles post login functions.
   * @return The index page if successful, otherwise the login page.
   */
  public static Result postLogin() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();
    
    if (formData.hasErrors()) {
      return badRequest(Login.render("Login", formData));
    }
    else {
      session().clear();
      session("email", formData.get().email);
      return ok(index.render("Logged In!"));
    }
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
