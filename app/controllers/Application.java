package controllers;

import play.*;
import play.mvc.*;
import views.html.index;
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
      return ok(Login.render("Login"));
    }

}
