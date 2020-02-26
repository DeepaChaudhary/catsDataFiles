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

public class Contaactus_Validation extends CATSCucumberConfig {

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

	@And("^Click on contact us cta$")
	public void click_on_contact_us_cta() throws Throwable 
	{
		//catsAction.scrollDownByOffset("1000");
		catsAction.scrollPageDown();
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Contactuserror.Contactus",this.ormData));

	}

	@Then("^Click on contact us submit contact us submit cta$")
	public void click_on_contact_us_submit_contact_us_submit_cta() throws Throwable 
	{
		catsAction.scrollDownByOffset("1500");	
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Contactuserror.Contactussubmit",this.ormData));
	}

	@And("^Verify that first name error message for contact us$")
	public void verify_that_first_name_error_message_for_contact_us() throws Throwable 
	{
		Thread.sleep(6000);	
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contactuserror.Contactusfirst",this.ormData));

	}

	@Then("^Verify that Second name error message for contact us$")
	public void verify_that_second_name_error_message_for_contact_us() throws Throwable {

		Thread.sleep(6000);	
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contactuserror.ContactusSecond",this.ormData));	        
	}

	@And("^Verify that mail error message for contact us$")
	public void verify_that_mail_error_message_for_contact_us() throws Throwable
	{
		Thread.sleep(6000);	
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contactuserror.mailerror",this.ormData));
	}
	@Then("^Verify that EnquiryType error message for contact us$")
	public void verify_that_enquirytype_error_message_for_contact_us() throws Throwable
	{
		Thread.sleep(6000);	
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contactuserror.Enquiryerror",this.ormData)); 
	}
	@And("^Verify that user message error for contact us$")
	public void verify_that_user_message_error_for_contact_us() throws Throwable {
		Thread.sleep(5000);	
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contactuserror.Usermsgerror",this.ormData)); 

	}
	@Then("^Verify that user is able view captcha image$")
	public void verify_that_user_is_able_view_captcha_image() throws Throwable {
		Thread.sleep(5000);
		catsAction.scrollDownByOffset("1400");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contactuserror.Captchimage",this.ormData));

	}

	@Then("^user fill  contact us form$")
	public void user_fill_contact_us_form() throws Throwable {

		catsAction.enter(CustomRules.locatorPresentInSite(website+".Contactuserror.FirstName",this.ormData), "$MiralGlobal.NameForGuestForm.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Contactuserror.lastname",this.ormData), "$MiralGlobal.SurnameForGuestForm.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData), "$MiralGlobal.EmailforLogin.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Contactuserror.PhoneNo",this.ormData), "$MiralGlobal.PhoneNoForGuestForm.<<site>>");
		catsAction.selectElementByIndex(CustomRules.locatorPresentInSite(website+".Contactuserror.ReasonDropDown",this.ormData) ,"2");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Contactuserror.Message",this.ormData), "$MiralGlobal.ADCB_Coupon.<<site>>");
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Ticket.Terms&Conditions",this.ormData));
	}
	
	@Then("^user enter wrong captcha$")
	public void user_enter_wrong_captcha() throws Throwable
	{
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Contactuserror.Captcha",this.ormData), "$MiralGlobal.ADCB_Coupon.<<site>>");

	}
	
	@And("^user able to see Captcha error message$")
	public void user_able_to_see_captcha_error_message() throws Throwable 
	{
		Thread.sleep(9000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Contactuserror.CaptchError",this.ormData));
	}
	
	@Then("^user verify prefilled details in form$")
    public void user_verify_filled_contact_us_form() throws Throwable {
       
          catsAction.scrollPageDown();
    catsAction.scrollIntoView(CustomRules.locatorPresentInSite(website+".Contactuserror.FirstName",this.ormData));
    catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Contactuserror.FirstName",this.ormData),"5");
    catsAction.verifyAttributeValue(CustomRules.locatorPresentInSite(website+".Contactuserror.FirstName",this.ormData),"value","YA");
          Thread.sleep(1000);
    catsAction.verifyAttributeValue(CustomRules.locatorPresentInSite(website+".Contactuserror.lastname",this.ormData),"value","Sap");
          Thread.sleep(1000);
    catsAction.verifyAttributeValue(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData),"value","SAPYA@MAILINATOR.COM");
          Thread.sleep(1000);
    catsAction.verifyAttributeValue(CustomRules.locatorPresentInSite(website+".Contactuserror.PhoneNo",this.ormData),"value","9898989878");
          Thread.sleep(1000);
    catsAction.selectElementByIndex(CustomRules.locatorPresentInSite(website+".secure3d.ReasonDropDown",this.ormData) ,"2");
          Thread.sleep(1000);
          // select country india from dropdown
    catsAction.click(CustomRules.locatorPresentInSite(website+".Contactuserror.countrydropdown",this.ormData));
          Thread.sleep(1500);
    catsAction.click(CustomRules.locatorPresentInSite(website+".Contactuserror.countrydropdown1",this.ormData));
    catsAction.enter(CustomRules.locatorPresentInSite(website+".Contactuserror.Message",this.ormData), "This is Test Mesage");
    catsAction.scrollIntoView(CustomRules.locatorPresentInSite(website+".Contactuserror.Message",this.ormData));
          Thread.sleep(2000);
    catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Contactuserror.TermsAndCondition",this.ormData));
          
    
    }



}
