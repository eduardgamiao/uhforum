package views.formdata;

/**
 * Handles search for the website.
 * @author eduardgamiao
 *
 */
public class SearchFormData {
  
  /** The search term. */
  public String term = "";
  
  /** Subject. */
  public String subject;
  
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
    this.term = searchTerm;
  }

}
