package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.Subject;
import models.SubjectDB;
import models.Topic;
import models.TopicDB;
import models.UserInfo;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.formdata.LoginFormData;
import views.formdata.SearchFormData;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
        return ok(index.render("Your new application is ready.", searchFormData));
    }
    
    public static Result Front()  {
      
      UserInfo userInfo = Secured.getUserInfo(ctx());
      List<Topic> topicList = new ArrayList<Topic>();
      topicList = TopicDB.getTopics();
      Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
      
      //List<Surfer> searchList = SearchFormDB.getSearch();          
      return ok(Front.render("Front Page", topicList, searchFormData));
    }
    
    /**
     * Render the subject specific topic listing.
     * @param acronym The acronym of the subject.
     * @return The subject topic listing.
     */
    public static Result subjectView(String acronym) {
      Subject subject = SubjectDB.getSubjectByAcronym(acronym);
      if (subject == null) {
        return redirect(routes.Application.index());
      }
      List<Topic> topics = TopicDB.getTopicsBySubject(subject);
      HashMap<String, Integer> tags = new HashMap<String, Integer>();
      for (Topic topic : topics) {
        String [] topicTags = topic.getTags().split(",");
        for (String current : topicTags) {
          current = current.trim();
          if (tags.containsKey(current)) {
            tags.put(current, tags.get(current) + 1);
          }
          else {
            tags.put(current, 1);
          }
        }
      }
      Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
      return ok(SubjectList.render(subject, tags, searchFormData));
    }
    
    public static Result viewTopic(String subjectAcronym, Long id) {
      Topic topic = TopicDB.getTopic(id);
      Subject subject = SubjectDB.getSubjectByAcronym(subjectAcronym);
      Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
      if (topic == null) {        
        return badRequest(index.render("UH Forum", searchFormData));
      }
      if (topic.getSubject().getName().equals(subject.getName())) {
        return ok(ViewTopic.render(topic.getTitle(), topic, topic.getPosts(), searchFormData));
      }
      return badRequest(index.render("UH Forum", searchFormData));     
    }
    
    public static Result search() {
      return TODO;
    }
    
}
