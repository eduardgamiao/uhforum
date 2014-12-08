package controllers;

import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.SearchFormData;
import views.html.SearchResults;

public class Search extends Controller {
  
  public static Result search() {
    Form<SearchFormData> newSearchFormData = Form.form(SearchFormData.class);
    Form<SearchFormData> searchFormData = Form.form(SearchFormData.class).bindFromRequest();
    SearchFormData formData = searchFormData.get();     
    return ok(SearchResults.render(formData.searchTerm, newSearchFormData));
  }
  
  public static Result searchByTag(String term, String subject, String tag) {
    Form<SearchFormData> newSearchFormData = Form.form(SearchFormData.class);
    Logger.debug(term + "|" + subject + "|" + tag);
    return TODO;
  }

}
