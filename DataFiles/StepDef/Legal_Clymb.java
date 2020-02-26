package cats.selenium.bdd.stepdef;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sapient.qa.cats.core.framework.CATSCucumberConfig;


import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Legal_Clymb extends CATSCucumberConfig {
	
	String randomUser;
	public String site;
	public String website;
	public ExcelReader ormData;
	
	@Before()
	  public void launch(Scenario scenario) {
		try{
			String ormFile=System.getProperty("user.dir")+"/../DataFiles/ORM.xlsx";
			this.ormData=new ExcelReader(ormFile);
		}catch (Exception e) {
			catsAction.reportResultFail("Read ORM file", e.toString(), "Orm file should be accessible", "Not able to read orm file");
			e.printStackTrace();
		}	
		scenario.write("I am in before launch");
		website= catsVariable.getSuite("site");
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
	   
	  @Then("^user click on Legal section link$")
	    public void user_click_on_legal_section_link() throws Throwable {
		  Thread.sleep(5000);	    
		  catsAction.clickJS(website+".Legal.Legalclick");
		    
		    }
	  @And("^User verify TERMS & CONDITIONS section present$")
	    public void user_verify_terms_conditions_section_present() throws Throwable {
		catsAction.verifyElementPresent(website+".Legal.Terms");
		     
		  }
	  @Then("^User verify ATTRACTION ENTRY TERMS section present$")
	    public void user_verify_attraction_entry_terms_section_present() throws Throwable {
		  Thread.sleep(5000);
		  catsAction.clickJS(website+".Legal.Attraction_term");
		  catsAction.verifyElementPresent(website+".Legal.Attraction_term");
	    }

	  @And("^User verify TICKETS TERMS section present$")
	    public void user_verify_tickets_terms_section_present() throws Throwable {
	    	Thread.sleep(5000);
			catsAction.clickJS(website+".Legal.Tickects_term");
			catsAction.verifyElementPresent(website+".Legal.Tickects_term");
		    }
	    @Then("^User verify PRIVACY POLICY section present$")
	    public void user_verify_privacy_policy_section_present() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.clickJS(website+".Legal.Privacy_policy");
			catsAction.verifyElementPresent(website+".Legal.Privacy_policy");
		    }  
	    @And("^User verify COOKIES POLICY section present$")
	    public void user_verify_cookies_policy_section_present() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.clickJS(website+".Legal.Cookies_policy");
			catsAction.verifyElementPresent(website+".Legal.Cookies_policy");
	    	
	    }

}