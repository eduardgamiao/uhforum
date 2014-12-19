package controllers;

import java.util.Map;
import models.Subject;
import models.SubjectDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.SearchFormData;
import views.formdata.SubjectTypes;
import views.formdata.TagSearchFormData;
import views.html.SearchResults;
import views.html.TagResults;

/**
 * Handles all searching functions.
 * @author eduardgamiao
 *
 */
public class Search extends Controller {
  
  /**
   * Search for topic.
   * @param term The search term.
   * @param subjectTitle The subject name;
   * @param currentPage The current paging list page.
   * @return The search result page.
   * @author eduardgamiao
   */
  public static Result search(String term, String subjectTitle, Integer currentPage) {
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
    searchFormData.data().put("term", request().getQueryString("term"));
    Subject subject = SubjectDB.getSubjectBySubject(request().getQueryString("subject"));
    Map<Subject, Boolean> subjectMap;
    if (subject != null) {
      searchFormData.data().put("subject", request().getQueryString("subject"));
      subjectMap = SubjectTypes.getTypes(subject);
    }
    else {
      subjectMap = SubjectTypes.getTypes();      
    }
    return ok(SearchResults.render(searchFormData, subjectMap, term, subjectTitle, currentPage));
  }
  
  /**
   * Search by tag.
   * @param tag The tag.
   * @param subjectTitle The subject name.
   * @param currentPage The current paging list page.
   * @return The tag result page.
   * @author eduardgamiao
   */
  public static Result searchByTag(String tag, String subjectTitle, Integer currentPage) {
    Form<TagSearchFormData> tagFormData = Form.form(TagSearchFormData.class);
    Subject subject = SubjectDB.getSubjectBySubject(request().getQueryString("subject"));
    tagFormData.data().put("tag", request().getQueryString("tag"));
    Map<Subject, Boolean> subjectMap;
    if (subject != null) {
      tagFormData.data().put("subject", request().getQueryString("subject"));
      subjectMap = SubjectTypes.getTypes(subject);
    }
    else {
      subjectMap = SubjectTypes.getTypes();      
    }
    return ok(TagResults.render(tagFormData, subjectMap, tag, subject, currentPage));
  }

}
