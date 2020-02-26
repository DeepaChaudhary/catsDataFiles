package cats.selenium.bdd.stepdef;

import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;
import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleSanityYWW extends CATSCucumberConfig{

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


	@Given("as a YWW user, i am on YWW Page")
	public void as_a_YWW_user_i_am_on_YWW_Page() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.launchSite("$MiralGlobal.LaunchSite<<env>>.<<site>>");
		//   catsAction.launchSite("https://www.yaswaterworld.com/");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Login.Cookie",this.ormData));
	}

	@When("YWW User clicks on REGISTER NOW link")
	public void yww_User_clicks_on_REGISTER_NOW_link() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.click(CustomRules.locatorPresentInSite(website+".Login.Profile",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Login.SignUp",this.ormData));
		catsAction.pageLoadWait();
	}

	@Then("YWW User redirects to registration page")
	public void yww_User_redirects_to_registration_page() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.verifyElementPresent("<<site>>.Registration.EmailId");
	}

	@When("YWW User enter below valid details for registration")
	public void yww_User_enter_below_valid_details_for_registration ( ) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

		catsAction.getRandomMailId("{{myEmailID}}"	, "");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Registration.EmailId",this.ormData), "{{myEmailID}}");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Registration.Password",this.ormData), "Test@123");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Registration.RenterPassword",this.ormData), "Test@123");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Registration.FirstName",this.ormData), "Sweta");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Registration.SurName",this.ormData), "Adhikari");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Registration.PhoneNo",this.ormData), "917358397864");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Registration.Nationality",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Registration.India",this.ormData));	
		catsAction.click(CustomRules.locatorPresentInSite(website+".Registration.Country",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Registration.NIndia",this.ormData));	
	}




	@When("YWW User click on submit button for registration")
	public void yww_User_click_on_submit_button_for_registration() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.click(CustomRules.locatorPresentInSite(website+".Registration.YWWCheckbox",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Registration.AckCheckbox",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Registration.Submit",this.ormData));

	}


	@When("YWW User clicks on Buy Ticket CTA")
	public void yww_User_clicks_on_Buy_Ticket_CTA() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.Buy",this.ormData));
		catsAction.pageLoadWait();
		Thread.sleep(3000);
	}

	@Then("YWW User should land on the booking journey page")
	public void yww_User_should_land_on_the_booking_journey_page() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.verifyURL("https://www.yaswaterworld.com/en/booking");
	}

	@When("YWW User should see the empty state of the mini cart")
	public void yww_User_should_see_the_empty_state_of_the_mini_cart() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.EmptyCart",this.ormData));
	}

	@When("YWW User clicks on Add to Cart button on general pass adult ticket")
	public void yww_User_clicks_on_Add_to_Cart_button_on_general_pass_adult_ticket() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Ticket.AddToCart",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
	}

	@When("YWW User clicks on Annual Passes")
	public void yww_User_clicks_on_Annual_Passes() {
		// Write code here that turns the phrase above into concrete actions
		//	catsAction.scrollIntoView(website+".Ticket.AnnualPasses");
		catsAction.scrollPageUp();
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AnnualPasses",this.ormData));
		catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
	}

	@Then("YWW add Annual Passes to cart")
	public void yww_add_Annual_Passes_to_cart() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AnnualPassCheckbox",this.ormData));
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Ticket.AddToCartAnnual",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
	}
	
	@When("user click on Sign up option on registration page")
    public void user_click_Sign_up_option_registration_page() {
           catsAction.click(CustomRules.locatorPresentInSite(website+".Login.SignUp",this.ormData));
           catsAction.pageLoadWait();
    }

	/*
@When("user click on Check out button on mini cart")
public void user_click_on_Check_out_button_on_mini_cart() {
    // Write code here that turns the phrase above into concrete actions
	catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
	catsAction.click(website+".Ticket.CheckOut");
}

@Then("user is redirected to cart page")
public void user_is_redirected_to_cart_page() {
    // Write code here that turns the phrase above into concrete actions
	catsAction.verifyURL("https://www.yaswaterworld.com/en/mypayment");
	}


@Then("user verifies the product amount listed on cart page")
public void user_verifies_the_product_amount_listed_on_cart_page() {
    // Write code here that turns the phrase above into concrete actions
//	catsAction.verifyTextContainsIgnoreCase(website+".Ticket.Amount", "AED500.00");
	catsAction.verifyElementPresent(website+".CheckIn.cartValue");
}

@When("user enter payment information with credit card")
public void user_enter_payment_information_with_credit_card() {
    // Write code here that turns the phrase above into concrete actions
	catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
	catsAction.scrollPageDown();

	catsAction.enterAppend(website+".Ticket.CardNo", "4223566675559888");

	catsAction.enter(website+".Ticket.ExpDate", "0230");

	catsAction.enter(website+".Ticket.CVV", "123");

	}

@When("select terms and condition checkbox")
public void select_terms_and_condition_checkbox() {
    // Write code here that turns the phrase above into concrete actions
	catsAction.click(website+".Ticket.T&C");
}*/

	//@When("click on Pay Now button")
	//public void click_on_Pay_Now_button() {
	//    // Write code here that turns the phrase above into concrete actions
	//   
	//}
	//
	//@Then("user redirected to payment confirmation page")
	//public void user_redirected_to_payment_confirmation_page() {
	//    // Write code here that turns the phrase above into concrete actions
	//   
	//}
}

