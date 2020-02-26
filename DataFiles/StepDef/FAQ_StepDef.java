package cats.selenium.bdd.stepdef;
import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class FAQ_StepDef  extends  CATSCucumberConfig{
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



	  @And("^User click on Faq link$")
	    public void user_click_on_faq_link() throws Throwable {
	    	catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Home.Faq",this.ormData), "30");
            catsAction.click(CustomRules.locatorPresentInSite(website+".Home.Faq",this.ormData));
	    }
	  @Then("^User click on FAQ section$")
	    public void user_click_on_faq_section() throws Throwable {
		  catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Home.FaqSec",this.ormData), "30");
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Home.FaqSec",this.ormData));
		  
       }
       @And("^User verify FAQ section present$")
	    public void user_verify_faq_section_present() throws Throwable {
    	   Thread.sleep(4000);
    	   catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Home.FaqExpand",this.ormData));
	    }
}