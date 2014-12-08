package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
    public static Result viewSubject(String acronym) {
      Subject subject = SubjectDB.getSubjectByAcronym(acronym);
      if (subject == null) {
        return redirect(routes.Application.index());
      }
      List<Topic> topics = TopicDB.getTopicsBySubject(subject);            
      Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
      return ok(ViewSubject.render(subject, getTags(topics), searchFormData));
    }
    
    public static Result viewTopic(String subjectAcronym, Long id) {
      Topic topic = TopicDB.getTopic(id);
      Subject subject = SubjectDB.getSubjectByAcronym(subjectAcronym);
      Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
      if (topic == null) {        
        return redirect(routes.Application.viewSubject(subjectAcronym));
      }
      if (topic.getSubject().getName().equals(subject.getName())) {
        TopicDB.addView(topic.getId());
        return ok(ViewTopic.render(topic.getTitle(), topic, topic.getPosts(), searchFormData));
      }
      return redirect(routes.Application.viewSubject(subjectAcronym));    
    }    
    
    /**
     * Return a hashmap of tags.
     * @param topics The list of topics to compile.
     * @return A hashmap of tags.
     */
    private static TreeMap<String, Integer> getTags(List<Topic> topics) {
      HashMap<String, Integer> tags = new HashMap<String, Integer>();
      TagComparator comparator = new TagComparator(tags);
      TreeMap<String, Integer> sortedTags = new TreeMap<String, Integer>(comparator);
      for (Topic topic : topics) {
        String [] topicTags = topic.getTags();
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
      sortedTags.putAll(tags);
      return sortedTags;
    }        
}

class TagComparator implements Comparator<String> {
      Map<String, Integer> map;
      public TagComparator(Map<String, Integer> map) {
        this.map = map;
      }

      public int compare(String key1, String key2) {
        if (map.get(key1) < map.get(key2)) {
          return 1;        
        }
        else if (map.get(key1) > map.get(key2)) {
          return -1;
        }
        else {
         return key1.compareTo(key2); 
        }
      }
    }