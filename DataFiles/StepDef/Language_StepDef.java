package cats.selenium.bdd.stepdef;

import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Language_StepDef  extends CATSCucumberConfig {
      
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
			   
       @When("^User clicks on language selector$")
	    public void user_clicks_on_language_selector() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Language.SelectLan_Btn",this.ormData));
	    }

	    @Then("^User able to see language drop down$")
	    public void user_able_to_see_language_drop_down() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Language.DropDwonMenu",this.ormData));
	     }
	    
	    @And("^User select languageAR$")
	    public void user_select_language() throws Throwable {
	    	catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Language.ArbLang",this.ormData), "20");
	    	 catsAction.click(CustomRules.locatorPresentInSite(website+".Language.ArbLang",this.ormData));
	    
	    }

	    @Then("^user check the url for language changed$")
	    public void user_check_the_url_for_language_changed() throws Throwable {
	        // catsAction.verifyURL("https://fe-uat2-ux-apps-cd.azurewebsites.net/ar/wbw");
	        catsAction.pageLoadWait();
	        Thread.sleep(3000);
	    	catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Language.TEXT_AR",this.ormData), "30");
	        catsAction.verifyText(CustomRules.locatorPresentInSite(website+".Language.TEXT_AR",this.ormData), "AR");
	    }
	  
        @And("^User select languageRU$")
	    public void user_select_languageru() throws Throwable {
        	 catsAction.click(CustomRules.locatorPresentInSite(website+".Language.RuLang",this.ormData));
	    }
	    @Then("^user check the url for language changed_RU$")
	    public void user_check_the_url_for_language_changedru() throws Throwable {
	    	 catsAction.pageLoadWait();
		    catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Language.TEXT_RU",this.ormData), "20");
		     catsAction.verifyText(CustomRules.locatorPresentInSite(website+".Language.TEXT_RU",this.ormData), "RU");
	    }
	    
	    @And("^User select languageZH$")
	    public void user_select_languagezh() throws Throwable {
	    	 catsAction.click(CustomRules.locatorPresentInSite(website+".Language.ChinaLang",this.ormData));
	    }
	    @Then("^user check the url for language changed_ZH$")
	    public void user_check_the_url_for_language_changedzh() throws Throwable {
	    	 catsAction.pageLoadWait();
			    catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Language.TEXT_ZH",this.ormData), "20");
		     catsAction.verifyText(CustomRules.locatorPresentInSite(website+".Language.TEXT_ZH",this.ormData), "ZH");
	    }
	   
        @And("^User select languageEN$")
	    public void user_select_languageen() throws Throwable {
        	 catsAction.click(CustomRules.locatorPresentInSite(website+".Language.EngLang",this.ormData));
	    }
	    @Then("^user check the url for language changed_EN$")
	    public void user_check_the_url_for_language_changeden() throws Throwable {
	    	 catsAction.pageLoadWait();
			  catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Language.TEXT_EN",this.ormData), "20");
		     catsAction.verifyText(CustomRules.locatorPresentInSite(website+".Language.TEXT_EN",this.ormData), "EN");
	    	 Thread.sleep(2000);

	    }
	    @When("^User click on Newsletter mail box$")
	    public void user_click_on_newsletter_mail_box() throws Throwable {
	        catsAction.scrollDownByOffset("3500");
	    }

	    @Then("^User enter the valid mail id$")
	    public void user_enter_the_valid_mail_id() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".NewsLetter.EmailTxtBx",this.ormData));
	    	catsAction.getRandomMailId("{{myEmailID}}"	, "");
		    catsAction.enter(CustomRules.locatorPresentInSite(website+".NewsLetter.EmailTxtBx",this.ormData), "{{myEmailID}}");
	    }
	    
	    @And("^User click on singup button$")
	    public void user_click_on_singup_button() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".NewsLetter.SignUpBtn",this.ormData));
	    	Thread.sleep(4000);
	    } 

	    @Then("^User able to see Thank you massage$")
	    public void user_able_to_see_thank_you_massage() throws Throwable {
	    	Thread.sleep(4000);
			  catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".NewsLetter.ThankYouMgs",this.ormData));
	    }
}
	    
	    

