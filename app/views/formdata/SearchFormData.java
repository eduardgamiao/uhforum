package views.formdata;

/**
 * Handles search for the website.
 * @author eduardgamiao
 *
 */
public class SearchFormData {
  
  /**
   * The search term.
   */
  public String searchTerm = "";
  
  /**
   * Blank constructor.
   */
  public SearchFormData() {
    
  }
  
  /**
   * Constructor.
   * @param searchTerm The search term.
   */
  public SearchFormData(String searchTerm) {
    this.searchTerm = searchTerm;
  }

}
