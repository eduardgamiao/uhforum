package controllers;

import models.Subject;
import models.SubjectDB;
import models.TopicDB;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.SearchFormData;
import views.html.SearchResults;
import views.html.TagResults;

public class Search extends Controller {
  
  public static Result search(String term, Integer currentPage) {
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class).bindFromRequest();
    SearchFormData formData = searchFormData.get();     
    return ok(SearchResults.render(term, currentPage));
  }
  
  public static Result pageSearch(String term, Integer currentPage) {
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class).bindFromRequest();
    SearchFormData formData = searchFormData.get();    
    return ok(SearchResults.render(term, currentPage));
  }
  
  public static Result searchByTag(String tag, String subjectTitle, Integer currentPage) {
    Subject subject = SubjectDB.getSubjectBySubject(subjectTitle);
    return ok(TagResults.render(tag, subject, currentPage));
  }

}
