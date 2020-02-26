package cats.selenium.bdd.stepdef;

import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;
import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Events_StepDef extends CATSCucumberConfig {
	
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
	  

    @When("^User clicks on events link$")
    public void user_clicks_on_events_link() throws Throwable {
    	//catsAction.launchSite("https://fe-uat2-ux-apps-cd.azurewebsites.net/yww");
    	catsAction.pageLoadWait();
    	catsAction.click(CustomRules.locatorPresentInSite(website+".Header.Events",this.ormData));  
    }
    
    
    @Then("^Events are appearing on the event page$")
    public void events_are_appearing_on_the_event_page() throws Throwable {
    	catsAction.pageLoadWait();
    	catsAction.scrollDownByOffset("800");
    	Thread.sleep(3000);
        catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".EventPage.EventTiles",this.ormData));
    	
    }

  
    	
        
    }

