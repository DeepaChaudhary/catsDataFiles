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

public class Articlelists extends CATSCucumberConfig {
	
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
	   
	  @Then("^user click experience under INDOOR SKYDIVING on mega menu$")
	    public void user_click_experience_under_indoor_skydiving_on_mega_menu() throws Throwable {
		 Thread.sleep(5000);
		 catsAction.hoverNClickSubItem(website+".Articlelist.HeaderExp","THE EXPERIENCE");
		 //catsAction.verifyElementPresent(objLocator);
	    }
	  @And("^Check the article tile$")
	    public void check_the_article_tile() throws Throwable 
	    {
	    catsAction.verifyElementPresent(website+".Articlelist.Articlecards");
	    catsAction.scrollDownByOffset("350");
	    
	    }
	     
}