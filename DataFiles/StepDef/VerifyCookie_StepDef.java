package cats.selenium.bdd.stepdef;
import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VerifyCookie_StepDef extends  CATSCucumberConfig {

	public String website;
	public ExcelReader ormData;
	
	@Before()
	  public void launch(Scenario scenario) throws IOException {
		String ormFile=System.getProperty("user.dir")+"/../DataFiles/ORM.xlsx";
		this.ormData=new ExcelReader(ormFile);
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
	
	@When("^User, click on accepts cookie button$")
	public void user_click_on_accepts_cookie_button() throws Throwable {

		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.CookieAccept",this.ormData));
	}
	
	@And("^User click on Offer page$")
	public void user_click_on_offer_page() throws Throwable {
		Thread.sleep(5000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.OfferNavigationLink",this.ormData));
		Thread.sleep(5000);
	}
	
	@Then("^User verify no cookies present in Offer page$")
	public void user_verify_no_cookies_present_in_offer_page() throws Throwable {
		catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Home.CookieAccept",this.ormData));	

	}
	
	@And("^User click on Contact Us page$")
	public void user_click_on_contact_us_page() throws Throwable {
		//catsAction.scrollPageDown();
		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.ContactUsNavigationLink",this.ormData));
		Thread.sleep(5000);
	}

	@Then("^User verify no cookies present in Contact Us page$")
	public void user_verify_no_cookies_present_in_contact_us_page() throws Throwable {
		catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Home.CookieAccept",this.ormData));

	}




}
