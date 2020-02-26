package cats.selenium.bdd.stepdef;
import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


public class Form_StepDef extends CATSCucumberConfig {

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

	public void fillAdditionalPassHolderDetails() throws InterruptedException
	{
		System.out.println(" indide Fill Pass holder details ");
		catsAction.scrollDownByOffset("450");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname1",this.ormData));
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname1",this.ormData));
		//catsAction.verifyElementPresent(website+".GuestCheckout.DOB");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname1",this.ormData), "$MiralGlobal.NameForGuestForm.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname1",this.ormData), "$MiralGlobal.SurnameForGuestForm.<<site>>");

		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.DOBDD",this.ormData), "$MiralGlobal.DOBforAnotherPassHolderDD.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.DOBMM",this.ormData), "$MiralGlobal.DOBforAnotherPassHolderMM.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.DOBYYYY",this.ormData), "$MiralGlobal.DOBforAnotherPassHolderYYYY.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.GenderDropdownForGuestUser",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.GenderForGuestUser",this.ormData));

		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.PhoneNo",this.ormData), "$MiralGlobal.PhoneNoforAnotherPassHolder.<<site>>");
		Thread.sleep(2000);
		System.out.println(" indide Fill Pass holder details exit ");

	}

	@And("^user is able to enter details of guest form$")
	public void user_is_able_to_enter_details_of_guest_form() throws Throwable {
		catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
		// 	catsAction.scrollPageDown();
		//	catsAction.scrollDownByOffset("1500");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData));
		// 	website+".Search.cookieClose"
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData));
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData), "$MiralGlobal.NameForGuestForm.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData), "$MiralGlobal.SurnameForGuestForm.<<site>>");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData));
		catsAction.getRandomMailId("{{myEmailID}}"	, "");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData), "{{myEmailID}}");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Contact",this.ormData), "$MiralGlobal.PhoneNoForGuestForm.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.Nationality",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.India",this.ormData));	
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.Country",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.NIndia",this.ormData));
		Thread.sleep(2000);
	}

	/*@Then("^user fill information for another pass holder$")
	    public void user_fill_information_for_another_pass_holder() throws Throwable {
	    	   // 	catsAction.scrollPageDown();
	    	    	catsAction.scrollDownByOffset("7000");
	    	//    	catsAction.waitUntilElementDisplay(website+".GuestCheckout.secondHolderExpand,"20");                                                                                                           
	                Thread.sleep(4000);
	    	    	catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.secondHolderExpand",this.ormData));
	    	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname1",this.ormData));
	    	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname1",this.ormData));
	    	    	//catsAction.verifyElementPresent(website+".GuestCheckout.DOB");
	    	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname1",this.ormData), "$MiralGlobal.NameForGuestForm.<<site>>");
	    	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname1",this.ormData), "$MiralGlobal.SurnameForGuestForm.<<site>>");
	    	    	//catsAction.scrollDownByOffset("350");
	    	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Contact1",this.ormData), "$MiralGlobal.PhoneNoForGuestForm.<<site>>");
	    	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.DOBDD",this.ormData), "$MiralGlobal.DOBforAnotherPassHolderDD.<<site>>");
	    	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.DOBMM",this.ormData), "$MiralGlobal.DOBforAnotherPassHolderMM.<<site>>");
	    	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.DOBYYYY",this.ormData), "$MiralGlobal.DOBforAnotherPassHolderYYYY.<<site>>");
	    	    	Thread.sleep(3000);
	    	    	catsAction.scrollDownByOffset("500");
	    	    	catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.SaveChanges",this.ormData));


	    }*/

	@Then("^user fill information for another pass holder$")
	public void user_fill_information_for_another_pass_holder() throws Throwable {
		int i=1;
		//	int NoOfPassHolder = Integer.parseInt(ProductBooking_StepDef.NoOfAnnualPasses);
		int NoOfPassHolder = Integer.parseInt(ProductBooking_StepDef.NoOfAnnualPasses);
		System.out.println("NoOfPassHolder:"+NoOfPassHolder);
		Thread.sleep(6000);
		Thread.sleep(8000);
		catsAction.scrollDownByOffset("550");
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.MainPassHolderContact",this.ormData));
		//         catsAction.scrollIntoView(CustomRules.locatorPresentInSite(website+".GuestCheckout.passHolderClose",this.ormData));
		//    catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.passHolderClose",this.ormData));
		catsAction.click("(//*[@class='c-form']/div/div/div/span[contains(@class,'accordionHead')])["+i+"]");
		for(i=1;i<NoOfPassHolder;i++)
		{   
			catsAction.click("(//*[@class='c-form']/div/div/div/span[contains(@class,'accordionHead')])["+(i+1)+"]");
			fillAdditionalPassHolderDetails();
			catsAction.click("(//*[@class='c-form']/div/div/div/span[contains(@class,'accordionHead')])["+(i+1)+"]");
		}		    	    
		Thread.sleep(4000);
		catsAction.scrollDownByOffset("500");
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.SaveChanges",this.ormData));
	}

	@Then("^user is able to enter guest details form$")
	public void user_is_able_to_enter_guest_details_form() throws Throwable {

		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData));
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData));
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData), "$MiralGlobal.NameForGuestForm.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData), "$MiralGlobal.SurnameForGuestForm.<<site>>");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData));
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData), "$MiralGlobal.EmailforLogin.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Contact",this.ormData), "$MiralGlobal.PhoneNoForGuestForm.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.Nationality",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.India",this.ormData));	
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.Country",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.NIndia",this.ormData));
		Thread.sleep(2000);
	}


	@And("^click on purchases and verify booking id not present$")
	public void click_on_purchases_and_verify_booking_id_not_present() throws Throwable 
	{
		Thread.sleep(10000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.Purchase",this.ormData));
		catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Ticket.RefBookId",this.ormData));

	}

	@And("^user navigate back and forth to payment page$")
	public void user_navigate_back_and_forth_to_payment_page() throws Throwable 
	{
		catsAction.navigateBack();
		Thread.sleep(3000);
		catsAction.pageLoadWait();
		catsAction.navigateForward();
		Thread.sleep(3000);
		catsAction.scrollDownByOffset("400");
	}

	@Then("^user validate guest Form Remain intact$")
	public void user_validate_guest_form_remain_intact() throws Throwable 
	{

		catsAction.getAttributeValue("value", "{{fnameValue}}",CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData));
		catsAction.verifyVariableValue("{{fnameValue}}", "$MiralGlobal.NameForGuestForm.<<site>>");

		catsAction.getAttributeValue("value", "{{lnameValue}}",CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData));
		catsAction.verifyVariableValue("{{lnameValue}}", "$MiralGlobal.SurnameForGuestForm.<<site>>");

		catsAction.getAttributeValue("value", "{{EmailValue}}",CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData));
		catsAction.verifyVariableValue("{{EmailValue}}", "$MiralGlobal.EmailforLogin.<<site>>");


		catsAction.getAttributeValue("value", "{{PhValue}}",CustomRules.locatorPresentInSite(website+".GuestCheckout.Contact",this.ormData));
		catsAction.verifyVariableValue("{{PhValue}}", "$MiralGlobal.PhoneNoForGuestForm.<<site>>");

		catsAction.verifyValue(CustomRules.locatorPresentInSite(website+".GuestCheckout.Nationality",this.ormData), "IN");
		catsAction.verifyValue(CustomRules.locatorPresentInSite(website+".GuestCheckout.Country",this.ormData), "IN");


	}

}
