package cats.selenium.bdd.stepdef;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class YAB extends CATSCucumberConfig{
	public String site;
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
	  
	  public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

	@When("^click Buy Tickets Button on homepage$")
	public void click_buy_tickets_button_on_homepage() throws Throwable {
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".HomePage.HP_Buy_Tickets", this.ormData));
	}
	@When("^click on smart carousel image on homepage$")
    public void click_on_smart_carousel_image_on_homepage() throws Throwable {
		Thread.sleep(6000);
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".HomePage.HP_carousel_Image", this.ormData));
		
    }
	@Then("^Verify User navigate to event listing page$")
	public void verify_user_navigate_to_event_listing_page() throws Throwable {
		Thread.sleep(8000);
    	getDriver().getCurrentUrl().contains("eventbooking");
	}
	
	@When("^User Select the date and time and click on buy ticket button on event listing page$")
    public void user_select_the_date_and_time_and_click_on_buy_ticket_button_on_event_listing_page() throws Throwable {
		Thread.sleep(5000);
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".EventBookingPage.EB_BuyTicketsButton", this.ormData));
    }
	
	@Then("^verify I understand Overlay should open and click on I understand Button$")
    public void verify_i_understand_overlay_should_open_and_click_on_i_understand_button() throws Throwable {
		Thread.sleep(5000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".EventBookingPage.EB_UnderstandPopup", this.ormData));
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".EventBookingPage.EB_UnderstandPopup_btn", this.ormData));
       
    }

	@Then("^Verify User navigate to buy ticket page$")
    public void verify_user_navigate_to_buy_ticket_page() throws Throwable {
		catsAction.pageLoadWait();
    	getDriver().getCurrentUrl().contains("buy-tickets");
    }

    @When("^User choose any sector selection$")
    public void user_choose_any_sector_selection() throws Throwable {
    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".BuyTicketPage.Arrow", this.ormData));
    }
    @And("^Click on radio button to select Ticket type$")
    public void click_on_radio_button_to_select_ticket_type() throws Throwable {
    	Thread.sleep(3000);
    	catsAction.waitUntilElementDisplay(website + ".BuyTicketPage.RadioButton", "30");
    	catsAction.click(CustomRules.locatorPresentInSite(website + ".BuyTicketPage.RadioButton", this.ormData));
    }
    @And("^click on choose your seat button$")
    public void click_on_choose_your_seat_button() throws Throwable {
    	catsAction.waitUntilElementDisplay(website + ".BuyTicketPage.ChoseYourSeatsButton", "30");
    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".BuyTicketPage.ChoseYourSeatsButton", this.ormData));
    }

    @And("^Verify User Navigate to confirm seat page and click on confirm seat button$")
    public void verify_user_navigate_to_confirm_seat_page_and_click_on_confirm_seat_button() throws Throwable {
    	catsAction.pageLoadWait();
    	getDriver().getCurrentUrl().contains("seats");
    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".BuyTicketPage.ConfirmSeatButton", this.ormData));
    	
    }
    @And("^user verifies the ticket amount listed on cart$")
    public void user_verifies_the_ticket_amount_listed_on_cart() throws Throwable {
    	catsAction.pageLoadWait();
    	getDriver().getCurrentUrl().contains("checkout");
    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".CheckoutPage.MiniCartTotalValue", this.ormData));
    	
    }
    
    @And("^User Click on proceed to pay Button$")
    public void user_click_on_proceed_to_pay_button() throws Throwable {
    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".PJPage.Viva_ProceedtoPay", this.ormData));
    }

    @When("^user enter your details at my payment page$")
    public void user_enter_your_details_at_my_payment_page() throws Throwable {
    	Thread.sleep(5000);
    	catsAction.enter(website + ".PJPage.PJ_Firstname", "$MiralGlobal.SignUpFirstName.<<site>>");
    	catsAction.enter(website + ".PJPage.PJ_LastName", "$MiralGlobal.SignUpSurName.<<site>>");
    	catsAction.enter(website + ".PJPage.PJ_Email", "$MiralGlobal.EmailforLogin.<<site>>");
    	catsAction.enter(website + ".PJPage.PJ_MobileNumber", "$MiralGlobal.SignUpPhoneNo.<<site>>");
		catsAction.selectElementByValue(website + ".PJPage.PJ_Nationality", "IN");
		catsAction.selectElementByValue(website + ".PJPage.PJ_Country", "IN");
    }
    @When("^User enter payment information with credit card$")
    public void user_enter_payment_information_with_credit_card() throws Throwable {
    	Thread.sleep(2000);
    	System.out.print("filling card details");
    	catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
      	catsAction.scrollPageDown();
      	catsAction.enterAppend(website+".PJPage.PJ_CardNumber", "$MiralGlobal.CardNumberForCardPayment.<<site>>");
      	catsAction.enter(website+".PJPage.PJ_Validity", "$MiralGlobal.ExpDateForCardPayment.<<site>>");
      	catsAction.enter(website+".PJPage.PJ_CVV", "$MiralGlobal.CVVForCardPayment.<<site>>");
      		Thread.sleep(2000);
    }

  @And("^User Click On terms and condition checkbox$")
    public void user_click_on_terms_and_condition_checkbox() throws Throwable {
    	Thread.sleep(2000);
    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".PJPage.PJ_CheckbocTnC", this.ormData));
    }
    @And("^click on pay button$")
    public void click_on_pay_button() throws Throwable {
    	Thread.sleep(2000);
    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".PJPage.PJ_Submitbutton", this.ormData));
    }
	@Then("^verify redirected to payment confirmation page$")    
	    public void verify_redirected_to_payment_confirmation_page() throws Throwable {    
		 Thread.sleep(30000);
//		 catsAction.waitUntilElementDisplay(website+".PJPage.TY_Title","30");
		 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".PJPage.TY_Title", this.ormData));
		 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".PJPage.TY_Description", this.ormData));
		 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".PJPage.TY_Id", this.ormData));
	    }
	
	@When("^User Click on arrow down to view ticket details$")
    public void user_click_on_arrow_down_to_view_ticket_details() throws Throwable {
		catsAction.pageLoadWait();
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".PJPage.PJ_Arrow_chevronDown", this.ormData));
    }
	
	@Then("^Verify user is able to view and click Change link$")
    public void verify_user_is_able_to_view_and_click_change_link() throws Throwable {
       catsAction.waitUntilElementDisplay(website + ".PJPage.PJ_ChangeLink_AtMyCart", "30");
       catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".PJPage.PJ_ChangeLink_AtMyCart", this.ormData));
    }
	
	 @And("^To make changes user click on Yes Button$")
	    public void to_make_changes_user_click_on_yes_button() throws Throwable
	  {
		 Thread.sleep(3000);
		 catsAction.click(CustomRules.locatorPresentInSite(website + ".PJPage.PJ_ChangeLink_YesButton", this.ormData));
	  }
}