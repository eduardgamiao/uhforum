package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Post;
import models.PostDB;
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
    Form<TopicFormData> formData = Form.form(TopicFormData.class);
    Map<Subject, Boolean> subjectTypeMap = SubjectTypes.getTypes();
    return ok(PostTopics.render("Post Topic", formData, subjectTypeMap));
  }
  
  @Security.Authenticated(Secured.class)
  public static Result inputTopic() {
    List<Topic> topicList = new ArrayList<Topic>();
    topicList = TopicDB.getTopics();
    Form<TopicFormData> formData = Form.form(TopicFormData.class).bindFromRequest();
    Map<Subject, Boolean> subjectTypeMap = SubjectTypes.getTypes();
    if (formData.hasErrors()) {
      return badRequest(PostTopics.render("Post Topic", formData, subjectTypeMap));
    }
    TopicFormData data = formData.get();
    System.out.println(data.subject);
    UserInfo user = Secured.getUserInfo(ctx());
    TopicDB.addTopic(data, user);
    return ok(Front.render("Front", topicList));
  }
  
  public static Result inputPost() {
    List<Topic> topicList = new ArrayList<Topic>();
    topicList = TopicDB.getTopics();
    List<Post> postList = new ArrayList<Post>();
    postList = PostDB.getPosts();
    Form<PostFormData> formData = Form.form(PostFormData.class).bindFromRequest();
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    Map<Subject, Boolean> subjectTypeMap = SubjectTypes.getTypes();
    if (formData.hasErrors()) {
      return badRequest(PostPosts.render("Post Topic", formData, searchFormData, subjectTypeMap));
    }
    PostFormData data = formData.get();
    UserInfo user = Secured.getUserInfo(ctx());
    String topicTitle = Secured.getTopic(ctx());
    return ok(Front.render("Front", topicList));
  }
  
}
