package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Topic;
import models.TopicDB;
import models.UserInfo;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.formdata.LoginFormData;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result Front()  {
      
      UserInfo userInfo = Secured.getUserInfo(ctx());
      List<Topic> topicList = new ArrayList<Topic>();
      topicList = TopicDB.getTopics();
      
      //List<Surfer> searchList = SearchFormDB.getSearch();          
      return ok(Front.render("Front Page", topicList));
    }
    
}
