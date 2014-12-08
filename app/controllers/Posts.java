package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Topic;
import models.TopicDB;
import models.UserInfo;
import models.UserInfoDB;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.LoginFormData;
import views.formdata.PostFormData;
import views.formdata.SearchFormData;
import views.formdata.SignupFormData;
import views.formdata.TopicFormData;
import views.html.Front;
import views.html.Signup;
import views.html.index;
import views.html.Login;
import views.html.Profile;

/**
 * Handles user login, logout, signup and profiles.
 * @author eduardgamiao
 *
 */
public class Posts extends Controller {
  
  /**
   * Returns the signup view.
   * @return The signup Result view.
   */
  public static Result postTopic() {
    Form<TopicFormData> formData = Form.form(TopicFormData.class);
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    return ok(PostTopic.render("Post Topic", formData, searchFormData));
  }
  
  /**
   * Handles the post signup event.
   * @return The user's profile page.
   */
  /*public static Result postPosts() {
    Form<PostFormData> formData = Form.form(PostFormData.class).bindFromRequest();
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);    
    
    if (formData.hasErrors()) {
      return badRequest(PostTopic.render("Post", formData, searchFormData));
    }
    PostFormData data = formData.get();
    Long id = Topic.addTopic(data);
    session().clear();
    session("email", data.email);
    return redirect(routes.Users.viewProfile(id));
  }*/
  
}
