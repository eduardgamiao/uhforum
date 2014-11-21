package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class Logout extends Controller {
  
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }

}
