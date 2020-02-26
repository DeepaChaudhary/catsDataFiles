package cats.selenium.bdd.stepdef;

import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchFunctionality_StepDef extends CATSCucumberConfig {
	
	public String website;
	public ExcelReader ormData;
	public String language; 
	
	@Before()
	  public void launch(Scenario scenario) throws IOException {
		String ormFile=System.getProperty("user.dir")+"/../DataFiles/ORM.xlsx";
		this.ormData=new ExcelReader(ormFile);
		scenario.write("I am in before launch");
		website= catsVariable.getSuite("site");
		language=catsVariable.getSuite("lang").toLowerCase();
		catsBeforeScenario(scenario); // this must be called all the time to invoke CATS framework
	    disableAutoAssertion();
	  }

	  @BeforeStep
	  public void beforeStep(Scenario scenario) {
	    scenario.write("in cats Hybrid");
	    catsBeforeStep(); // this must be called all the time to invoke CATS framework
	    System.out.println(scenario.getStatus());
	  }

	  @AfterStep
	  public void afterStep() {
	    catsAfterStep(); // this must be called all the time to invoke CATS framework
	  }
	  
	  /*****************************************************************************************************************************************
	   *****************************************************************************************************************************************
	   *************** To call your locator Please Use "CustomRules.locatorPresentInSite(website+".Language.CookieAccept",this.ormData)"********
	   *****************************************************************************************************************************************
	   *****************************************************************************************************************************************
	   **********************Start writing your Step definition below this sectio***************************************************************
	   *****************************************************************************************************************************************
	   */	  
	 
	  
	String temp="LaunchSite";
	
	@Given("^Closing cookie header if it is there$")
	public void Closing_cookie_header_if_it_is_there() throws Throwable {
		try {
			if(catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Search.cookieClose",this.ormData)))
			{
				catsAction.click(CustomRules.locatorPresentInSite(website+".Search.cookieClose",this.ormData));
			}
		}
			catch(Exception e){
			
			System.out.println("Cookie Header is not present");
		}
	}
	
    @When("^User click on cross icon in Search Box$")
    public void user_click_on_cross_icon_in_search_box() throws Throwable {
    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Search.SearchResultOverlayClose",this.ormData));
    	catsAction.click(CustomRules.locatorPresentInSite(website+".Search.SearchResultOverlayClose",this.ormData));
    	
    }

    @Then("^Search overlay will be closed in site$")
    public void search_overlay_will_be_closed_in_site() throws Throwable {
    	catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Search.HeaderSearchInputBox",this.ormData));
    	
//    	if(catsAction.verifyElementPresent(website+".Search.HeaderSearchInputBox"))
//    		{
//    		System.out.println("Search Overlays is not closed");
//    		}
//    		else
//    		{
//    			System.out.println("Search Overlays is closed");
//    		}
  }

    @And("^User clicks on search icon in header$")
    public void user_clicks_on_search_icon_in_header() throws Throwable {
    	//catsAction.click("${{MiralGlobal.ORMfile.<<site>>}}.Search.HeaderSearchIcon");       
    	catsAction.click(CustomRules.locatorPresentInSite(website+".Search.HeaderSearchIcon",this.ormData));
    }
    
    @When("^User clicks anywhere in search bar in site$")
    public void user_clicks_anywhere_in_search_bar_in_site() throws Throwable {
    	catsAction.click(CustomRules.locatorPresentInSite(website+".Search.HeaderSearchInputBox",this.ormData));  	       
    }

    @When("^User clicks on search icon in header to submit the serach$")
    public void user_clicks_on_search_icon_in_header_to_submit_the_serach() throws Throwable {
    	catsAction.click(CustomRules.locatorPresentInSite(website+".Search.SearchResultIconSubmit",this.ormData));
    }

    @Then("^User should be able to type search \"([^\"]*)\" in site$")
    public void user_should_be_able_to_type_search_something_in_site(String testData) throws Throwable {
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Search.HeaderSearchInputBox",this.ormData),testData);
    }
    
    @Then("^User should be able to type search in site$")
    public void user_should_be_able_to_type_search_in_site() throws Throwable
    {
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Search.HeaderSearchInputBox",this.ormData),"$MiralGlobal.DesTNSearchName.<<site>>");
    }

    @Then("^Verify that user is able to see the no search result message If there are no matching results$")
    public void verify_that_user_is_able_to_see_the_no_search_result_message_if_there_are_no_matching_results() throws Throwable {
    	 catsAction.pageLoadWait();
    	 Thread.sleep(3000);
    	 switch(language){
    	 case("ar"):	
    		 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Search.SearchResultMessage",this.ormData));
        	 catsAction.verifyTextIgnoreCase(CustomRules.locatorPresentInSite(website+".Search.SearchResultMessage",this.ormData), "لم يتم العثور على أي نتائج بحث");
    	 	break;	
    	 default:
    		 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Search.SearchResultMessage",this.ormData));
        	 catsAction.verifyTextIgnoreCase(CustomRules.locatorPresentInSite(website+".Search.SearchResultMessage",this.ormData), "No search results found");
        	 break;
    	 }
    }
    @Then("^User redirects to search results page$")
    public void user_redirects_to_search_results_page() throws Throwable {
    	 catsAction.pageLoadWait();
    	 System.out.println("Reached at search result page");
    	 catsAction.verifyURL("https://www.yaswaterworld.com/en/no-search?q=leg");
    }
    @When("^User clears the search input box in Searach result page$")
    public void user_clears_the_search_input_box_in_searach_result_page() throws Throwable {
    	catsAction.clearField(CustomRules.locatorPresentInSite(website+".Search.SearchResultInputBox",this.ormData));
    }
    @And("^User clicks on search textbox on search result page$")
    public void user_clicks_on_search_textbox_on_search_result_page() throws Throwable {
    	catsAction.click(CustomRules.locatorPresentInSite(website+".Search.SearchResultInputBox",this.ormData));
    }
    @When("^User starts writing \"([^\"]*)\" in search bar on search result page in WBW site$")
    public void user_starts_writing_something_in_search_bar_on_search_result_page_in_wbw_site(String searchData) throws Throwable {
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Search.SearchResultInputBox",this.ormData), searchData);
    }   
    @Then("^Verify User should not be able to see auto suggestion search feature on search result page until user has entered 3 characters$")
    public void verify_user_should_not_be_able_to_see_auto_suggestion_search_feature_on_search_result_page_until_user_has_entered_3_characters() throws Throwable {
    	catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Search.AutoSuggest",this.ormData));
    }
    @When("^User has written 3 or more or than 3 characters \"([^\"]*)\" in search keyword on searh result page$")
    public void user_has_written_3_or_more_or_than_3_characters_something_in_search_keyword_on_searh_result_page(String searchDataThreeChar) throws Throwable {
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Search.SearchResultInputBox",this.ormData), searchDataThreeChar);
    }
    
    @When("^User has written 3 or more or than 3 characters in search keyword on searh result page$")
    public void user_has_written_3_or_more_or_than_3_characters_in_search_keyword_on_searh_result_page() throws Throwable
    {
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Search.SearchResultInputBox",this.ormData),"$MiralGlobal.SearchThreeChar.<<site>>");
   }
    
    @Then("^Verify User is able to see auto suggestion search feature on search result page$")
    public void verify_user_is_able_to_see_auto_suggestion_search_feature_on_search_result_page() throws Throwable {
    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Search.AutoSuggest",this.ormData));
    }

    @Then("^Verify that user is able to see the search result found message If there are matching results$")
    public void verify_that_user_is_able_to_see_the_search_result_found_message_if_there_are_matching_results() throws Throwable {
    	catsAction.pageLoadWait();
   	 catsAction.verifyTextIgnoreCase(CustomRules.locatorPresentInSite(website+".Search.SearchResultMessage",this.ormData), " results found for ");
    }

    @And("^Verify search result on serach result page$")
    public void verify_search_result_on_serach_result_page() throws Throwable {
    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Search.SearchResultSearchResultList",this.ormData));
    }
}
