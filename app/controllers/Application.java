package controllers;

import play.*;
import play.mvc.*;
import views.html.index;
import views.html.*;

public class Application extends Controller {

  /**
   * Displays the index page.
   * @return The index view.
   */
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
