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


public class B2B_Form extends CATSCucumberConfig {
    
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

	@When("^B2B User click on ContactUs$")
    public void b2b_user_click_on_contactus() throws Throwable {
		Thread.sleep(3000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Contact.ContactUs",this.ormData));
		
	}
	
	@Then("^User select the contactUs reason$")
    public void user_select_the_contactus_reason() throws Throwable {
		Thread.sleep(3000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Contact.ContactReason",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Contact.ContactReasonDropdown",this.ormData));
	//	Thread.sleep(2000);
	//	catsAction.click(website+".Contact.SecondOption");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Contact.SecondDropdown",this.ormData));
		
	}

	@And("^User write message for reason$")
    public void user_write_message_for_reason() throws Throwable {
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Contact.Message",this.ormData), "$MiralGlobal.Message.<<site>>");
	}
	
	@When("^B2B User click on Submit button$")
    public void b2b_user_click_on_submit_button() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Contact.SubmitContactUs",this.ormData));
		Thread.sleep(5000);
	}
	
	@Then("^B2B User get confirmation message$")
    public void b2b_user_get_confirmation_message() throws Throwable {
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Contact.ThankyouMsg",this.ormData), "40");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contact.ThankyouMsg",this.ormData));
	}
	
	@And("^B2B user fill all information to create an Operator$")
    public void b2b_user_fill_all_information_to_create_an_operator() throws Throwable {
    	Thread.sleep(3000);
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.LastName",this.ormData), "$MiralGlobal.B2BOperatorLastName.<<site>>");
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.FirstName",this.ormData), "$MiralGlobal.B2BOperatorFirstName.<<site>>");
    	catsAction.click(CustomRules.locatorPresentInSite(website+".NewUserForm.Country",this.ormData));
    	catsAction.click(CustomRules.locatorPresentInSite(website+".NewUserForm.CountryDropdown",this.ormData));
    	catsAction.getRandomMailId("{{myEmailID}}"	, "");
    	randomUser="{{myEmailID}}";
		catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.email",this.ormData), "{{myEmailID}}");
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.PhoneNo",this.ormData), "$MiralGlobal.B2BOperatorPhoneNo.<<site>>");
    	
    	catsAction.click(CustomRules.locatorPresentInSite(website+".NewUserForm.Operator",this.ormData));
    	
	}
	
	@And("^B2B user fill all information to create an Agent$")
    public void b2b_user_fill_all_information_to_create_an_agent() throws Throwable {
		Thread.sleep(3000);
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.LastName",this.ormData), "$MiralGlobal.B2BOperatorLastName.<<site>>");
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.FirstName",this.ormData), "$MiralGlobal.B2BOperatorFirstName.<<site>>");
    	catsAction.click(CustomRules.locatorPresentInSite(website+".NewUserForm.Country",this.ormData));
    	catsAction.click(CustomRules.locatorPresentInSite(website+".NewUserForm.CountryDropdown",this.ormData));
    	catsAction.getRandomMailId("{{myEmailID}}"	, "");
    	randomUser="{{myEmailID}}";
		catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.email",this.ormData), "{{myEmailID}}");
    	catsAction.enter(CustomRules.locatorPresentInSite(website+".NewUserForm.PhoneNo",this.ormData), "$MiralGlobal.B2BOperatorPhoneNo.<<site>>");
    	
	}
	
	@Then("^B2B User click on Save button$")
    public void b2b_user_click_on_save_button() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".NewUserForm.Save",this.ormData));
    	Thread.sleep(5000);
		
	}
	
	
	
	
}



