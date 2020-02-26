package cats.selenium.bdd.stepdef;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Payment_StepDef extends CATSCucumberConfig {

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

	@When("user enter payment information with credit card")
	public void user_enter_payment_information_with_credit_card() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
		catsAction.scrollPageDown();


		catsAction.enterAppend(CustomRules.locatorPresentInSite(website+".Ticket.CardNo",this.ormData), "$MiralGlobal.CardNumberForCardPayment.<<site>>");

		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.ExpDate",this.ormData), "$MiralGlobal.ExpDateForCardPayment.<<site>>");

		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.CVV",this.ormData), "$MiralGlobal.CVVForCardPayment.<<site>>");
		Thread.sleep(2000);
	}

	@When("select terms and condition checkbox")
	public void select_terms_and_condition_checkbox() {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.T&C",this.ormData));
	}

	@And("^click on Pay Now button$")
	public void click_on_pay_now_button() throws Throwable {
		Thread.sleep(3000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.payNow",this.ormData));
		Thread.sleep(7000);
		catsAction.pageLoadWait();
	}

	@Then("^user redirected to payment confirmation page$")
	public void user_redirected_to_payment_confirmation_page() throws Throwable {
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Payment.ticketConfirmation",this.ormData),"60");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.ticketConfirmation",this.ormData));
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.ticket_Booking_id",this.ormData));
	}
	
	@Then("^user click on Download ticket button$")
	public void user_click_on_Download_ticket_button() throws Throwable {
		CustomRules.delAllFiles(System.getProperty("user.home")+"\\Downloads");
		Thread.sleep(3000);
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Payment.downloadTicket",this.ormData));
		Thread.sleep(40000);
		String result=CustomRules.isFilePresent(System.getProperty("user.home")+"\\Downloads");  
		if(!result.equals("null")){
			catsAction.reportResultPass("file is downloaded successfully","true" , "true", result);
		}else{
			catsAction.reportResultFail("file is downloaded successfully","False" , "False", result);

		}
		System.out.println("isFilePresent::::::::"+CustomRules.isFilePresent(System.getProperty("user.home")+"\\Downloads"));
		
	}
	
	
	

	@And("^select terms and condition checkbox for paypal$")
	public void select_terms_and_condition_checkbox_for_paypal() throws Throwable {
		catsAction.scrollDownByOffset("600");
		Thread.sleep(4000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.paypalButton",this.ormData));
		Thread.sleep(12000);
		catsAction.scrollDownByOffset("200");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.T&CforPaypal",this.ormData));
		catsAction.scrollDownByOffset("200");

	}

	@When("^user enter payment information with paypal$")
	public void user_enter_payment_information_with_paypal() throws Throwable {
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.paypalProceed",this.ormData));
		Thread.sleep(4000);
		catsAction.switchWindowByID("1");
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Payment.EmailId",this.ormData),"5");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.EmailId",this.ormData));
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Payment.EmailId",this.ormData),"$MiralGlobal.EmailForPayPal.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.BtnNext",this.ormData));
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Payment.Password",this.ormData),"20");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Payment.Password",this.ormData), "$MiralGlobal.PasswordForPayPal.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.Login",this.ormData));
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Payment.continueOnPaypal",this.ormData),"10");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.continueOnPaypal",this.ormData));
		catsAction.switchWindowByID("0");
		Thread.sleep(2000);

	}


	@Then("^user able to see mandatory fields$")
	public void user_able_to_see_mandatory_fields() throws Throwable 
	{
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.ErrorField",this.ormData));
	}


	@Then("^user enter Debit or Credit card num and verify$")
	public void user_enter_debit_or_credit_card_no_and_verify() throws Throwable
	{
		catsAction.scrollDownByOffset("600");
		catsAction.enterAppend(CustomRules.locatorPresentInSite(website+".Ticket.CardNo",this.ormData), "$MiralGlobal.CardNumberPartical.<<site>>");
		Thread.sleep(2000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.ADCBcard",this.ormData));
		catsAction.scrollPageUp();


	}
	@Then("^user enter wrong Debit Or Credit card details$")
	public void user_enter_wrong_debit_or_credit_card_details() throws Throwable 
	{
		catsAction.scrollDownByOffset("600");
		catsAction.enterAppend(CustomRules.locatorPresentInSite(website+".Ticket.CardNo",this.ormData), "$MiralGlobal.InvalidCardNum.<<site>>");
		Thread.sleep(2000);
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.ExpDate",this.ormData), "$MiralGlobal.ExpDateForCardPayment.<<site>>");

		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.CVV",this.ormData), "$MiralGlobal.CVVForCardPayment.<<site>>");
		Thread.sleep(2000);

	}

	@Then("^user verify error mgs for invalid input$")
	public void user_verify_error_mgs_for_invalid_input() throws Throwable
	{
		Thread.sleep(5000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.ErrorMgsForCard",this.ormData));

	}
	@And("^select terms and condition checkbox for paypalPPAD$")
	public void select_terms_and_condition_checkbox_for_paypalPPAD() throws Throwable {
		Thread.sleep(5000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.paypalButton",this.ormData));
		Thread.sleep(6000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.T&CforPaypal",this.ormData));
	}
	@Then("^User able to see ADCB error message$")
	public void user_able_to_see_adcb_error_message() throws Throwable
	{
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.NonAdcb",this.ormData));

	}
	@Then("^verify filled data should be display confirmation page$")
	public void verify_filled_data_should_come_on_confirmation_page() throws Throwable {


		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.RefBookId",this.ormData));
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".ConformationPage.GivenMail",this.ormData));

	}
	@When("^user enter ADBC card Expaiy date and CVV num$")
	public void user_enter_adbc_card_expaiy_date_and_cvv_num() throws Throwable 
	{
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.ExpDate",this.ormData), "$MiralGlobal.ExpDateForCardPayment.<<site>>");

		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.CVV",this.ormData), "$MiralGlobal.CVVForCardPayment.<<site>>");
		Thread.sleep(2000);
	}
	@And("^User is able to see  payment page$")
	public void user_is_able_to_see_payment_page() throws Throwable 
	{
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData),"60");

		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData));
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData));
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData));


	}

	@And("^User verify cart is not expanded state$")
	public void user_verify_cart_is_not_expanded_state() throws Throwable 
	{
		catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Payment.GridExpandBtnPayment",this.ormData));

	}
	@And("^user enter 3d secure pin code$")
	public void user_enter_3d_secure_pin_code() throws Throwable
	{
		catsAction.enter(CustomRules.locatorPresentInSite(website+".secure3d.PinNum",this.ormData), "$MiralGlobal.Secure3dpin.<<site>>");
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".secure3d.sumbitBtn",this.ormData));

	}

}
