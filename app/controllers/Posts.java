package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Subject;
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
import views.formdata.SubjectTypes;
import views.formdata.TopicFormData;
import views.html.*;

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
  @Security.Authenticated(Secured.class)
  public static Result postTopic() {
    Form<TopicFormData> formData = Form.form(TopicFormData.class).bindFromRequest();
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    Map<Subject, Boolean> subjectTypeMap = SubjectTypes.getTypes();
    return ok(PostTopics.render("Post Topic", formData, searchFormData, subjectTypeMap));
  }
  
  @Security.Authenticated(Secured.class)
  public static Result inputTopic() {
    List<Topic> topicList = new ArrayList<Topic>();
    topicList = TopicDB.getTopics();
    Form<TopicFormData> formData = Form.form(TopicFormData.class).bindFromRequest();
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    TopicFormData data = formData.get();
    System.out.println(data.subject);
    UserInfo user = Secured.getUserInfo(ctx());
    TopicDB.addTopic(data, user);
    return ok(Front.render("Front", topicList, searchFormData));
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
