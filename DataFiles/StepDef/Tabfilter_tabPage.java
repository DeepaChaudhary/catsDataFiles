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

public class Tabfilter_tabPage extends CATSCucumberConfig {
	
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
	  
	  @Then("^user is navigate to tickects page$")
	    public void user_is_navigate_to_tickects_page() throws Throwable 
	  {
		  Thread.sleep(5000);
		  catsAction.hoverJS(website+".Tabwithfiltter.Tickects");
		  catsAction.clickJS(website+".Tabwithfiltter.Droplist");
	    }
	  @And("^Check the tile on tab1 page$")
	    public void check_the_tile_on_tab1_page() throws Throwable
	  {

		  catsAction.verifyElementPresent(website+".Tabwithfiltter.Tile1");  
	    }
	  
	  @Then("^Cick on filter nexttab$")
	    public void cick_on_filter_nexttab() throws Throwable
	  {
		  Thread.sleep(5000);
		  catsAction.clickJS(website+".Tabwithfiltter.Tab");  
	    }

	  @And("^Check the tile on tab2 page$")
	    public void check_the_tile_on_tab2_page() throws Throwable
	  {
	   
	    	 catsAction.verifyElementPresent(website+".Tabwithfiltter.Tile2");   
	    }

	
	    }