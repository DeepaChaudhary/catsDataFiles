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


public class B2B_Payment extends CATSCucumberConfig {
    
	String randomUser;
	public String site;
	public String website;
	public String bookingID;
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

	  @When("^B2B User click on Book Ticket CTA$")
	    public void b2b_user_click_on_book_ticket_cta() throws Throwable {
		     Thread.sleep(4000);
		     catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.BookTicket",this.ormData));
		     Thread.sleep(4000);
	  }
	  
	  @Then("^B2B User select dated ticket type$")
	    public void b2b_user_select_dated_ticket_type() throws Throwable {
		    catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.DatedTicket",this.ormData));
		    Thread.sleep(4000);
	  }
	  
	  @Then("^B2B User select Non dated ticket type$")
	    public void b2b_user_select_non_dated_ticket_type() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.NonDatedTicket",this.ormData));
		    Thread.sleep(3000);
	  }
	  
	  @And("^B2B User add single park ticket$")
	    public void b2b_user_add_single_park_ticket() throws Throwable {
		  
	     try{
			   if(getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Payment.SelectDateCTA",this.ormData)).getXpath()) != null)
			   {
		             catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.SelectDateCTA",this.ormData));
		             catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.SelectDateCalender",this.ormData));
	            	 catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.IncreaseTicket",this.ormData));
		             catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AddToCart",this.ormData));
		             Thread.sleep(3000);
		          }
		  }
		  catch(Exception e)
          {
			  System.out.println("Calender Overlay ticket is not present");
			  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.IncreaseTicket",this.ormData));
			  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AddToCart",this.ormData));
			  Thread.sleep(3000);
		  }
	  }
	  
	  @And("^B2B User add AddOn Tickets$")
	    public void b2b_user_add_addon_tickets() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AddOnTickets",this.ormData));
		  Thread.sleep(2000);
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.IncreaseTicket",this.ormData));
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AddToCart",this.ormData));
		  Thread.sleep(3000);
	  }
	  
	  @And("^B2B User add Summer Offers Tickets$")
	    public void b2b_user_add_summer_offers_tickets() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.SummerOfferTicket",this.ormData));
		  Thread.sleep(3000);
		  
		  try{
			  if(getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Payment.SelectDateCTA",this.ormData)).getXpath()) != null)
			   {
		             catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.SelectDateCTA",this.ormData));
		             catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.SelectDateCalender",this.ormData));
	            	 catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.IncreaseTicket",this.ormData));
		             catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AddToCart",this.ormData));
		             Thread.sleep(3000);
		          }
		  }
		  catch(Exception e)
         {
			  System.out.println("Calender Overlay ticket is not present");
			  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.IncreaseTicket",this.ormData));
			  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AddToCart",this.ormData));
			  Thread.sleep(3000);
		  }
	  }
	  
	  @And("^B2B User add MultiPark ticket$")
	    public void b2b_user_add_multipark_ticket() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.MultiPark",this.ormData));
		  Thread.sleep(3000);
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.FWADcheckbox",this.ormData));
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.YWWcheckbox",this.ormData));
		  Thread.sleep(2000);
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.IncreaseTicket",this.ormData));
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AddToCart",this.ormData));
		  Thread.sleep(4000);
	  }
	  
	  @Then("^B2B User check minicart is not empty$")
	    public void b2b_user_check_minicart_is_not_empty() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.checkMinicartPrice",this.ormData));
	  }
	  
	  @And("^B2B User click on checkout button$")
	    public void b2b_user_click_on_checkout_button() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.checkout",this.ormData));
		  Thread.sleep(3000);
	  }
	  
	  @Then("^B2B User add agent reference number$")
	    public void b2b_user_add_agent_reference_number() throws Throwable {
		  catsAction.enter(CustomRules.locatorPresentInSite(website+".Payment.agentNumber",this.ormData), "$MiralGlobal.agentNumberOnPaymentPage.<<site>>");
	    	
	  }
	  
	  @Then("^B2B User fill credit card detail$")
	    public void b2b_user_fill_credit_card_detail() throws Throwable {
		  catsAction.enterAppend(CustomRules.locatorPresentInSite(website+".Payment.creditCardNumber",this.ormData), "$MiralGlobal.CardNumberForCardPayment.<<site>>");
		  	
		  	catsAction.enter(CustomRules.locatorPresentInSite(website+".Payment.creditCardExpiry",this.ormData), "$MiralGlobal.ExpDateForCardPayment.<<site>>");

		  	catsAction.enter(CustomRules.locatorPresentInSite(website+".Payment.creditCardCVV",this.ormData), "$MiralGlobal.CVVForCardPayment.<<site>>");
		  		Thread.sleep(2000);
	  }
	  
	  @And("^B2B User checks the terms and conditions checkbox for Paypal$")
	    public void b2b_user_checks_the_terms_and_conditions_checkbox_for_paypal() throws Throwable {
		  catsAction.scrollDownByOffset("1900");
		  Thread.sleep(3000);
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.paypalButton",this.ormData));
	         Thread.sleep(6000);
          catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.T&CforPaypal",this.ormData));
          Thread.sleep(3000);
	  }
	  
	  @And("^B2B User checks the terms and conditions checkbox for Account Credit Payment$")
	    public void b2b_user_checks_the_terms_and_conditions_checkbox_for_account_credit_payment() throws Throwable {
		  catsAction.scrollDownByOffset("1950");
		  Thread.sleep(3000);
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AccountCreditButton",this.ormData));
	         Thread.sleep(6000);
          catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Payment.T&CforAccountCredit",this.ormData));
          Thread.sleep(2000);
	  }
	  
	  @And("^B2B User checks the terms and conditions checkbox for submitting request for approval$")
	    public void b2b_user_checks_the_terms_and_conditions_checkbox_for_submitting_request_for_approval() throws Throwable {
		  catsAction.scrollDownByOffset("1950");
		  Thread.sleep(3000);
          catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Payment.T&CforSubmittingApproval",this.ormData));
          Thread.sleep(2000);
	  }
	  
	  @Then("^B2B User pay with PayPal$")
	    public void b2b_user_pay_with_paypal() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.paypalProceed",this.ormData));
	    	Thread.sleep(2000);
	    	catsAction.switchWindowByID("1");
	    	catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Payment.EmailId",this.ormData),"10");
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
	  
	  @And("^B2B User checks the terms and conditions checkbox$")
	    public void b2b_user_checks_the_terms_and_conditions_checkbox() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.T&CinCreditCard",this.ormData));
		  Thread.sleep(3000);
	  }
      
	  @Then("^B2B User click for payment checkout$")
	    public void b2b_user_click_for_payment_checkout() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.continuePayment",this.ormData));
	  }
	  
	  @Then("^B2B User click for payment checkout with Account Credit$")
	    public void b2b_user_click_for_payment_checkout_with_account_credit() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.AccountCreditPaymentCTA",this.ormData));
	  }
	  
	  @Then("^B2B User check the payment confirmation page$")
	    public void b2b_user_check_the_payment_confirmation_page() throws Throwable {
		  catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Payment.ticketConfirmation",this.ormData),"50");
			catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.ticketConfirmation",this.ormData));
			catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.ticket_Booking_id",this.ormData));
	  }
	  
	 
}
