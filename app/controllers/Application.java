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
      
      return ok(SubjectList.render(subject, tags));
    }
    
    public static Result viewTopic(String subjectAcronym, Long id) {
      Topic topic = TopicDB.getTopic(id);
      Subject subject = SubjectDB.getSubjectByAcronym(subjectAcronym);
      if (topic == null) {
        return badRequest(index.render("Nope."));
      }
      if (topic.getSubject().getName().equals(subject.getName())) {
        return ok(ViewTopic.render(topic.getTitle(), topic, topic.getPosts()));
      }
      return badRequest(index.render("Nope."));     
    }
    
}
