package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;
import views.formdata.LoginFormData;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /**
     * Renders the login page for the website.
     * @return The Login view.
     */
    public static Result login() {
      Form<LoginFormData> formData = Form.form(LoginFormData.class);
      return ok(Login.render("Login", formData));
    }
    
    public static Result postLogin() {
      Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();
      
      if(formData.hasErrors() == true) {
        return badRequest(Login.render("Login", formData));
      }
      else {
        session().clear();
        session("email", formData.get().email);
        return ok(index.render("Logged In!"));
      }
    }
    
}
