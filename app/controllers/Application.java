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
    
    public static Result Front() {
      return ok(Front.render("Home"));
  }
    
}
