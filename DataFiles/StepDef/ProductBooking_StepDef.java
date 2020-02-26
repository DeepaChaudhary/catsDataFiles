package cats.selenium.bdd.stepdef;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.ibm.icu.util.Calendar;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductBooking_StepDef extends CATSCucumberConfig {

	public String website;
	public ExcelReader ormData;
	public static  String NoOfAnnualPasses;
	public String noOfPasses;
	public int NoOfTicketsToAdd=0;
	public String noOfUserPreSelected;
	public static String priceOfProduct;
	public String adult;
	public String junior;

	public static String priceOfProductAdult;
	public static String priceOfProductJunior;

	public static int AdultAdded;
	public static int JuniorAdded;

	public String adultPriceTocheck;
	public String juniorPriceTocheck;
	public Double discountPriceForJunior;
	public Double discountPriceForAdult;

	public static Double AmountInMinincart=0.0;

	public String priceOfPasses;
	public String totalPriceOnTicket;
	
	public String PricePerPass;

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


	@When("^User clicks on Buy Ticket CTA$")
	public void user_clicks_on_buy_ticket_cta() throws Throwable {
		Thread.sleep(3000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.Buy",this.ormData));
		catsAction.pageLoadWait();
		Thread.sleep(3000);
	}

	@When("User should see the empty state of the mini cart")
	public void User_should_see_the_empty_state_of_the_mini_cart() throws InterruptedException {
		Thread.sleep(3000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.EmptyCart",this.ormData));
	}

	@When("User clicks on Add to Cart button on general pass adult ticket")
	public void User_clicks_on_Add_to_Cart_button_on_general_pass_adult_ticket() {
		// Write code here that turns the phrase above into concrete actions
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AddToCart",this.ormData));
		switch (website){

		case("YWW"):
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		break;

		case("FWAD"):
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		break;

		default:
			break;
		}

	}

	@When("user click on Check out button on mini cart")
	public void user_click_on_Check_out_button_on_mini_cart() throws InterruptedException {
		Thread.sleep(4000);
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Ticket.CheckOut",this.ormData));
	}

	@Then("user verifies the product amount listed on cart page")
	public void user_verifies_the_product_amount_listed_on_cart_page() {
		//	 	catsAction.verifyTextContainsIgnoreCase(website+".Ticket.Amount", "AED500.00");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".CheckIn.cartValue",this.ormData));
	}

	@When("User clicks on Annual Passes")
	public void User_clicks_on_Annual_Passes() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		//	 	catsAction.scrollIntoView(website+".Ticket.AnnualPasses");
		catsAction.scrollPageUp();
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AnnualPasses",this.ormData));
		Thread.sleep(4000);
		//catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
	}

	@Then("user add Annual Passes to cart")
	public void user_add_Annual_Passes_to_cart() {
		// Write code here that turns the phrase above into concrete actions
		//	catsAction.scrollDownByOffset("400");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AnnualPassCheckbox",this.ormData));
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Ticket.AddToCartAnnual",this.ormData));
		//catsAction.click(website+".Ticket.close");
	}



	@Then("^Add ticket to cart$")
	public void add_ticket_to_cart() throws Throwable {
		catsAction.pageLoadWait();
		switch(website){
		case("FWAD"):
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.selectcart",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.date",this.ormData)); 
		Thread.sleep(5000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Add",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.AddToCart",this.ormData));
		break;

		default:
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.AddToCart",this.ormData));
			break;

		}



	}

	int counter=1;
	@Then("^Add ticket to cart for Experiences or Tour$")
	public void add_ticket_to_cart_for_experiences() throws Throwable {
		// 	catsAction.pageLoadWait();
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.SelectTicket",this.ormData));

		Thread.sleep(2000);
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd");
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		String d=dateTimeInGMT.format(new Date());
		System.out.println(d);
		int date= Integer.parseInt(d);
		int dateSearchCounter=10;

		//Integer.parseInt(catsVariable.getTDMValue("$MiralGlobal.dateSearchCounter.<<site>>"));
		while(counter<=dateSearchCounter){
			if(date>28){
				date=1;
				catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.nextMonthClickOnCalender",this.ormData));
				catsAction.click("//*[@aria-label='day-"+date+"']");
			}
			else{
				catsAction.click("//*[@aria-label='day-"+date+"']");
			}

			Thread.sleep(5000);
			System.out.println("date is vailable or not*******"+
					catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.timeSlotsAvialable",this.ormData)) + "********"
					+ "And Date is ::: "+"//*[@aria-label='day-"+date+"']");
			if(catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.timeSlotsAvialable",this.ormData))){
				catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.selectTimeSlot",this.ormData));
				catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.checkdropdownValue",this.ormData));

				catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.checkdropdownValue",this.ormData));
				Thread.sleep(5000);
				catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.addToCartOnOverlay",this.ormData));
				break;
			} 	
			else if(catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.noSlotsAvailable",this.ormData))){
				System.out.println("No Slots Avialable");
			}
			date++;
			counter++;
		}
      
		//if slots not available
	      try{
              if(getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.experienceOverlayClose",this.ormData)).getXpath()).isDisplayed())
                                                       {   
                                                                       System.out.println("slots not found");
                                                                       Thread.sleep(2000);
                                                                       catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.experienceOverlayClose",this.ormData));
                                                       }
                                       }catch(Exception e)
                                       {
                                                       System.out.println("Slots found");
                                       }

	}

	@When("^User clicks on Buy Ticket CTA via PLP$")
	public void user_clicks_on_buy_ticket_cta_via_plp() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_PLP.BuyCTA",this.ormData));
		Thread.sleep(5000);

	}

	@When("^User navigate to PLP$")
	public void user_navigate_to_plp() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_PLP.ticket_homePage",this.ormData));
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_PLP.extras",this.ormData));
		Thread.sleep(2000);
	}

	@When("^User clicks on Buy Ticket CTA via PDP$")
	public void user_clicks_on_buy_ticket_cta_via_pdp() throws Throwable {

		catsAction.scrollDownByOffset("600");
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Product_PLP.ReadMore",this.ormData), "30");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_PLP.ReadMore",this.ormData));
		//   Thread.sleep(3000);
		catsAction.scrollDownByOffset("400");
		Thread.sleep(3000);
		//	   catsAction.waitUntilElementDisplay(website+".Product_PLP.Product_PDP_BTN", "50");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_PDP.ProductPDPBuyTicket",this.ormData));
		Thread.sleep(7000);

	}

	@When("^User navigate to PLP with Experiences$")
	public void user_navigate_to_plp_with_experiences() throws Throwable {

		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_PLP.ticket_homePage",this.ormData));
		//Thread.sleep(4000);
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Product_PLP.Experiences",this.ormData), "30");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_PLP.Experiences",this.ormData));
		Thread.sleep(2000);
	}


	@When("^User clicks on TICKET link$")
	public void user_clicks_on_ticket_link() throws Throwable {
		catsAction.pageLoadWait();
		catsAction.click(CustomRules.locatorPresentInSite(website+".PLP.Tickets",this.ormData));
	} 

	@Then("^Products are appearing on the event page$")
	public void products_are_appearing_on_the_event_page() throws Throwable {
		catsAction.pageLoadWait();
		catsAction.scrollDownByOffset("800");
		Thread.sleep(3000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".PLP.ProductTiles",this.ormData));

	}


	@Then("^User pick a date$")
	public void user_pick_a_date() throws Throwable {
		if(website.equalsIgnoreCase("PPAD") && catsVariable.getSuite("env").equalsIgnoreCase("PROD")){
			Thread.sleep(2000);
			catsAction.click(CustomRules.locatorPresentInSite(website+".Home.GeneralAdmissionTab",this.ormData));
		}
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.DatePicker1",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.select",this.ormData));
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.SelectBtn",this.ormData));
		catsAction.scrollDownByOffset("200");
	}

	@Then("^User click on Tours$")
	public void user_click_on_tours() throws Throwable
	{
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.Tour",this.ormData));

	}

	@When("^User navigates to PLP for booking$")
	public void user_navigates_to_plp_for_booking() throws Throwable {
		catsAction.click(website+".Home.TicketBTN");
		Thread.sleep(4000);
		catsAction.scrollDownByOffset("450");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.BuyTicket",this.ormData));
		catsAction.scrollUpByOffset("200");
	}

	@Then("^User add more junior ticket$")
	public void user_add_more_junior_ticket() throws Throwable {
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.increaseJunior",this.ormData),"3");
		/*
		  for (int i= 1; i <=2; i++) 
		  {
			 System.out.println("print count "+i);
   WebElement webEl = getDriver().findElement(By.xpath("putXpath"));
		 Thread.sleep(3000);
		 webEl.click();

			}*/
	}

	@And("^User click on cart expand button$")
	public void user_click_on_cart_expand_button() throws Throwable 
	{
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.CartExpand",this.ormData));

	}
	@Then("^User add more junior tickets and adult tickets$")
	public void user_add_more_junior_tickets_and_adult_tickets() throws Throwable 
	{
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.AdultCartIncrease",this.ormData),"3");
		Thread.sleep(2000);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.JuniorCartIncrease",this.ormData),"3");
	}
	@And("^User Delete item from the cart$")
	public void user_delete_item_from_the_cart() throws Throwable
	{
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AdultDelete",this.ormData));
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.YESconformDelete",this.ormData));
		Thread.sleep(2000);  
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.JuniorDelete",this.ormData));
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.YESconformDelete",this.ormData));


	}
	@And("^User is able to edit guest count$")
	public void user_is_able_to_edit_guest_count() throws Throwable {

		List<WebElement> PriceList1 = getDriver().findElements(By.xpath("//p[@class='body-1 sub-total-price']"));
		double sumProduct = 0;

		for(WebElement element:PriceList1)
		{
			String str = element.getText();
			String numberOnly= str.replaceAll("[^0-9.]", "");
			double price1 = Double.valueOf(numberOnly);
			sumProduct=sumProduct+price1;


			System.out.println("price of "+numberOnly);
		}
		System.out.println("total cost"+sumProduct);
		WebElement actualTotalElmt= getDriver().findElement(By.xpath("//span[@class='cart-total-price-aed']"));
		String actCost = actualTotalElmt.getText();
		String NumVal = actCost.replaceAll("[^0-9.]", "");
		double Actualprice = Double.valueOf(NumVal);
		{

			System.out.println("Actual cost "+Actualprice);


		}
	}

	@And("^User is able to see dated ticket option$")
	public void user_is_able_to_see_dated_ticket_option() throws Throwable 
	{
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.DateSelector",this.ormData));


	}

	@Then("^User is able to see Flexiable ticket option$")
	public void user_is_able_to_see_flexiable_ticket_option() throws Throwable 
	{
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.FlexibleDateSelctor",this.ormData));
	}
	@Then("^User is able to click flexible date$")
	public void user_is_able_to_click_flexible_date() throws Throwable 
	{
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.FlexibleDateCheckBox",this.ormData));

	}
	@And("^User is not able to see calender overlay$")
	public void user_is_not_able_to_see_calender_overlay() throws Throwable 
	{
		catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Ticket.CalendarOverLay",this.ormData));

	}
	@Then("^user click on login on payment page$")
	public void user_click_on_login_on_payment_page() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.paymentLogin",this.ormData));

	}
	@Then("^User is able to see Guest form will disappear$")
	public void user_is_able_to_see_guest_form_will_disappear() throws Throwable 
	{
		catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Ticket.GuestForm",this.ormData));

	}

	@Then("^User click on grid expand button$")
	public void user_click_on_grid_expand_button() throws Throwable 
	{
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.GridExpandBtn",this.ormData));

	}

	@Then("^User addition more junior tickets and adult tickets$")
	public void user_addition_more_junior_tickets_and_adult_tickets() throws Throwable 
	{

		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.increaseAdult",this.ormData),"3");
		Thread.sleep(2000);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.increaseJunior",this.ormData),"3");


	}

	@Then("^User empty minicart if product were there$")
	public void user_empty_minicart_if_product_were_there() throws Throwable {	
		try{
			if(getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".MiniCart.collapsedMinicart",this.ormData)).getXpath()).isDisplayed())
			{   
				Thread.sleep(4000);
				catsAction.click(CustomRules.locatorPresentInSite(website+".MiniCart.collapsedMinicart",this.ormData));
			}
		}catch(Exception e)
		{
			System.out.println("Minicart already expanded");
		}

		List<WebElement> noOfProduct_MiniCart = getDriver().findElementsByXPath(catsVariable.getORM(website+".MiniCart.DeleteIcon").getXpath()); 

		System.out.println("noOfProduct_MiniCart: "+noOfProduct_MiniCart.size());

		for(int i=0; i<noOfProduct_MiniCart.size();i++)
		{
			catsAction.click(CustomRules.locatorPresentInSite(website+".MiniCart.DeleteIcon",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".MiniCart.ConfirmDelete",this.ormData));
			Thread.sleep(3000);
		}
		System.out.println("noOfProduct_MiniCart: "+noOfProduct_MiniCart);


	}

	@Then("^user expand minicart if not expanded$")
	public void user_expand_minicart_if_not_expanded() throws Throwable {
		Thread.sleep(4000);

		try{
			if(getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".MiniCart.collapsedMinicart",this.ormData)).getXpath()).isDisplayed())
			{   
				Thread.sleep(2000);
				catsAction.click(CustomRules.locatorPresentInSite(website+".MiniCart.collapsedMinicart",this.ormData));
			}
		}catch(Exception e)
		{
			System.out.println("Minicart already expanded");
		}

	}

	@Then("^user verify details for (.+) for annual Pass in Minicart$")
	public void user_verify_details_for_for_annual_pass_in_minicart(String annualtype1) throws Throwable {
		String ProductName= annualtype1;
		priceOfPasses=priceOfPasses.replaceAll("[^0-9.]", "");
		Double priceToCheck = Double.parseDouble(priceOfPasses);
		int NoOfPasses = Integer.parseInt(NoOfAnnualPasses);
		priceToCheck= priceToCheck*NoOfPasses;
		System.out.println("priceToCheck:"+priceToCheck);

		NumberFormat myFormat = NumberFormat.getInstance();
		String PassesPriceTocheck= myFormat.format(priceToCheck);
		System.out.println("PassesPriceTocheck:"+PassesPriceTocheck);

		catsAction.verifyTextContains(totalPriceOnTicket, PassesPriceTocheck);

		String productPriceXpathPasses ="((//div[@class='product-name'])/p[contains(text(), '"+ProductName+"')]/parent::div/parent::div/parent::div/div[2]/div/div/div/span/p[@class='price'])[1]";

		catsAction.verifyTextContains(productPriceXpathPasses, PassesPriceTocheck);
	}

	@Then("^user verify the details in MiniCart for (.+) for (.+)$")
	public void user_verify_the_details_in_minicart_for_for(String type1, String day) throws Throwable {	
		String ProductName = type1;
		String productPriceXpathforAdult ="((//div[@class='product-name'])/p[contains(text(), '"+ProductName+"')]/parent::div/parent::div/parent::div/div[2]/div/div/div/span/p[@class='price'])[1]";
		String productPriceXpathforJunior ="((//div[@class='product-name'])/p[contains(text(), '"+ProductName+"')]/parent::div/parent::div/parent::div/div[2]/div/div/div/span/p[@class='price'])[2]";


		Thread.sleep(3000);
		Double checkForDate= Double.parseDouble(day);

		Double priceAdult= Double.parseDouble(priceOfProductAdult);
		Double priceJunior= Double.parseDouble(priceOfProductJunior);
		NumberFormat myFormat = NumberFormat.getInstance();

		//check for adult
		if(checkForDate<=2)
		{   
			System.out.println("No Discount Range");
			discountPriceForAdult= priceAdult;
			adultPriceTocheck= myFormat.format(priceAdult);
			catsAction.verifyTextContains(productPriceXpathforAdult, adultPriceTocheck);
		}
		else if(checkForDate>=3 && checkForDate<=14)
		{
			System.out.println("10% discount range");
			discountPriceForAdult= priceAdult-((priceAdult*10)/100);
			System.out.println("discountPriceForAdult_10%:"+ discountPriceForAdult);
			adultPriceTocheck= myFormat.format(discountPriceForAdult);
			catsAction.verifyTextContains(productPriceXpathforAdult, adultPriceTocheck);
		}
		else if(checkForDate>=15)
		{   
			System.out.println("15% discount range");
			discountPriceForAdult= priceAdult-((priceAdult*15)/100);
			System.out.println("discountPrice_15%:"+ discountPriceForAdult);
			adultPriceTocheck=myFormat.format(discountPriceForAdult);
			catsAction.verifyTextContains(productPriceXpathforAdult, adultPriceTocheck);	
		}

		//check for junior
		if(checkForDate<=2)
		{   
			System.out.println("No Discount Range");
			discountPriceForJunior=priceJunior;
			juniorPriceTocheck= myFormat.format(priceJunior);
			catsAction.verifyTextContains(productPriceXpathforJunior, juniorPriceTocheck);
		}
		else if(checkForDate>=3 && checkForDate<=14)
		{
			System.out.println("10% discount range");
			discountPriceForJunior= priceJunior-((priceJunior*10)/100);
			System.out.println("discountPriceForJunior_10%:"+ discountPriceForJunior);
			juniorPriceTocheck=myFormat.format(discountPriceForJunior);
			catsAction.verifyTextContains(productPriceXpathforJunior, juniorPriceTocheck);
		}
		else if(checkForDate>=15)
		{   
			System.out.println("15% discount range");
			discountPriceForJunior= priceJunior-((priceJunior*15)/100);
			System.out.println("discountPriceForJunior_15%:"+ discountPriceForJunior);
			juniorPriceTocheck= myFormat.format(discountPriceForJunior);
			catsAction.verifyTextContains(productPriceXpathforJunior, juniorPriceTocheck);
		}

		Double totalAdultPrice = AdultAdded*discountPriceForAdult;
		Double totalJuniorPrice = JuniorAdded*discountPriceForJunior;

		Double totalAmount=totalAdultPrice+totalJuniorPrice;
		System.out.println("totalAmount:"+totalAmount);
		AmountInMinincart= AmountInMinincart+totalAmount;
		System.out.println("AmountInMinincart"+AmountInMinincart);

	}

	@Then("^user verify total price in minicart$")
	public void user_verify_total_price_in_minicart() throws Throwable {

		catsAction.scrollIntoView(CustomRules.locatorPresentInSite(website+".MiniCart.ProductTotal",this.ormData));
		NumberFormat myFormat = NumberFormat.getInstance();
		String amountToCheck= myFormat.format(AmountInMinincart);
		System.out.println("amountToCheck"+amountToCheck);
		catsAction.verifyTextContains(CustomRules.locatorPresentInSite(website+".MiniCart.ProductTotal",this.ormData), amountToCheck); 
	}

	@And("^user verify the error message in minicart$")
	public void user_verify_the_error_message_in_minicart() throws Throwable {
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".MiniCart.error",this.ormData));
	}

	@Then("^user add ticket for (.+)$")
	public void user_add_ticket_for(String day) throws Throwable {
		catsAction.scrollPageUp();
		catsAction.click(CustomRules.locatorPresentInSite(website+".DatedTicket.SelectDatedTicket",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".DatedTicket.NoDiscountTab",this.ormData));
		int checkForDate= Integer.parseInt(day);
		//today's date
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd");
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		String d=dateTimeInGMT.format(new Date());
		System.out.println(d);
		int date= Integer.parseInt(d);

		//days in month
		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("monthMaxDays:"+monthMaxDays);

		int DateToCheck= date+checkForDate;
		if(DateToCheck<=monthMaxDays)
		{    
			try{
				if(getDriver().findElementByXPath("//*[@aria-label='day-"+DateToCheck+"'][contains(@class, 'react-datepicker__day--disabled')]").isDisplayed())
				{ 
					catsAction.click("(//*[@aria-label='day-"+DateToCheck+"'])[2]");
				} }
			catch(Exception e)
			{
				catsAction.click("//*[@aria-label='day-"+DateToCheck+"']");
			}

		}
		else if(DateToCheck>=monthMaxDays)
		{
			int	DateToCheck_nextMonth= DateToCheck-monthMaxDays;
			System.out.println("DateToCheck_nextMonth:"+DateToCheck_nextMonth);
			//    int DateToCheck_nextMonth =checkForDate-remainingDays;
			catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.nextMonthClickOnCalender",this.ormData));
			catsAction.click("//*[@aria-label='day-"+DateToCheck_nextMonth+"']");
		}

		Thread.sleep(3000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.SelectTicket",this.ormData));



	}

	@Then("^user add GA ticket to cart$")
	public void user_add_ga_ticket_to_cart() throws Throwable {
		Thread.sleep(4000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AddToCart",this.ormData));
		switch (website){

		case("YWW"):
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		break;

		case("FWAD"):
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		break;

		default:
			break;
		}
		Thread.sleep(3000);
	}

	@Then("^User verify discount for (.+)$")
	public void user_verify_discount_for(String day) throws Throwable {
		Thread.sleep(3000);
		Double checkForDate= Double.parseDouble(day);

		String Ticketprice= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.TicketPrice",this.ormData)).getXpath()).getText();
		System.out.println("Ticketprice: "+Ticketprice);
		Double price=Double.parseDouble(Ticketprice);
		System.out.println("price: "+price);

		if(checkForDate<=2)
		{   
			System.out.println("No Discount Range");
			catsAction.verifyTextContains(CustomRules.locatorPresentInSite(website+".Ticket.DiscountPrice",this.ormData),Ticketprice);
		}
		else if(checkForDate>=3 && checkForDate<=14)
		{
			System.out.println("10% discount range");
			Double discountPrice= price-((price*10)/100);
			System.out.println("discountPrice_10%:"+ discountPrice);
			String check= Double.toString(discountPrice);
			catsAction.verifyTextContains(CustomRules.locatorPresentInSite(website+".Ticket.DiscountPrice",this.ormData),check);

		}
		else if(checkForDate>=15)
		{   
			System.out.println("15% discount range");
			Double discountPrice= price-((price*15)/100);
			System.out.println("discountPrice_15%:"+ discountPrice);
			String check= Double.toString(discountPrice);
			catsAction.verifyTextContains(CustomRules.locatorPresentInSite(website+".Ticket.DiscountPrice",this.ormData),check);
		}
	}

	@Then("^user add Flexible GA ticket$")
	public void user_add_flexible_ga_ticket() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.FlexibleDate",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AddToCart",this.ormData));
		switch (website){

		case("YWW"):
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		break;

		case("FWAD"):
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
		break;

		default:
			break;
		}
	}


	@When("^User add General Admission Ticket$")
	public void user_add_general_admission_ticket() throws Throwable {

		while(NoOfTicketsToAdd<90)
		{   
			noOfUserPreSelected= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.GetAdultValue",this.ormData)).getXpath()).getAttribute("value");
			System.out.println("noOfUserPreSelected: "+noOfUserPreSelected);

			catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.DecreaseCTA_GA",this.ormData),noOfUserPreSelected);
			catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.IncreaseCTA_GA",this.ormData), "10");
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.FlexibleDate",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AddToCart",this.ormData));
			switch (website){

			case("YWW"):
				catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
			break;

			case("FWAD"):
				catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
			break;

			default:
				break;
			}
			NoOfTicketsToAdd=NoOfTicketsToAdd+10;
			System.out.println("NoOfTicketsToAdd:"+ NoOfTicketsToAdd);
		}

		catsAction.scrollPageUp();
		if(NoOfTicketsToAdd==90)
		{
			noOfUserPreSelected= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.GetAdultValue",this.ormData)).getXpath()).getAttribute("value");
			System.out.println("noOfUserPreSelected: "+noOfUserPreSelected);

			catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.DecreaseCTA_GA",this.ormData),noOfUserPreSelected);
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.IncreaseCTA_GA",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.FlexibleDate",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AddToCart",this.ormData));
			switch (website){

			case("YWW"):
				catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
			break;

			case("FWAD"):
				catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.close",this.ormData));
			break;

			default:
				break;
			}
		}

	}

	@Then("^user add \"([^\"]*)\" Annual Passes to cart$")
    public void user_add_something_annual_passes_to_cart(String strArg1) throws Throwable {
	//	catsAction.scrollDownByOffset("400");
  NoOfAnnualPasses = strArg1;
  int noOfPassesToAdd=Integer.parseInt(NoOfAnnualPasses);
//  catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AnnualPassCheckbox",this.ormData));
  Thread.sleep(3000);
  noOfPasses= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.PassesCount",this.ormData)).getXpath()).getAttribute("value");
  System.out.println("noOfPasses: "+noOfPasses);
  int noOfPassesAlreadyPresent=Integer.parseInt(noOfPasses);
 
  if(noOfPassesAlreadyPresent<noOfPassesToAdd)
  {
	  int increase = noOfPassesToAdd-noOfPassesAlreadyPresent;
	  System.out.println("increase: "+increase);
	  String incresePass= Integer.toString(increase);
	  catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.IncreasePasses",this.ormData),incresePass);
		 
  }
  else if(noOfPassesAlreadyPresent>noOfPassesToAdd)
  {
	  int decrease =  noOfPassesAlreadyPresent-noOfPassesToAdd;
	  System.out.println("decrease: "+decrease);
	  String decreasePass= Integer.toString(decrease);
	  catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.decreasePasses",this.ormData),decreasePass);
		 
	  
  }
}


	@When("^user click on extras tab$")
	public void user_click_on_extras_tab() throws Throwable {
		Thread.sleep(3000);
		catsAction.scrollPageUp();
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Extras",this.ormData));
	}


	@Then("^Add extras ticket to cart$")
	public void add_extras_ticket_to_cart() throws Throwable {
		Thread.sleep(7000);
		try{
			System.out.println("in try.....................");
			if(getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.Extras_passValue",this.ormData)).getXpath())!=null)
			{     
				System.out.println("in if ............");
				String adultsPresent= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.Extras_passValue",this.ormData)).getXpath()).getAttribute("value");
				System.out.println("adultsPresent: "+adultsPresent);
				catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.Extras_passDecrease",this.ormData),adultsPresent);
				catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.Extras_passIncrease",this.ormData),"2");

				catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.Extras_AddToCart",this.ormData));
			}
		}
		catch(Exception e)
		{
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.selectcart",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.date",this.ormData)); 
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Add",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.AddToCart",this.ormData));
		}
	}



	@Then("^user apply PromoCode$")
	public void user_apply_promocode() throws Throwable 
	{

		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.EnterPromoCode",this.ormData));
		Thread.sleep(1000);
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.coupon",this.ormData), "$MiralGlobal.Coupon.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.Apply",this.ormData));
	}	
	@And("^user is able getting promo discount$")
	public void user_is_able_getting_promo_discount() throws Throwable 
	{
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.Discount",this.ormData));

	}
	@Then("^user apply ADBCPromoCode$")
	public void user_apply_adbcpromocode() throws Throwable 
	{
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Ticket.EnterPromoCode",this.ormData),"30");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.EnterPromoCode",this.ormData));
		Thread.sleep(1000);
		catsAction.enter(CustomRules.locatorPresentInSite(website+".Ticket.coupon",this.ormData), "$MiralGlobal.ADCB_Coupon.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.Apply",this.ormData));
		Thread.sleep(3000);

	}

	@Then("^User click on general Admission btn$")
	public void user_click_on_general_admission_btn() throws Throwable 
	{
		catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.GenaralAdmission",this.ormData));
		Thread.sleep(3000);

	}


	@Then("^user add Annual Pass ticket to cart$")
	public void user_add_annual_pass_ticket_to_cart() throws Throwable {
		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Ticket.AddToCartAnnual",this.ormData));

	}

	@Then("^User select the (.+) of General Admission ticket$")
	public void user_select_the_of_general_admission_ticket(String type) throws Throwable {
		Thread.sleep(3000);
		System.out.println("type:"+type);
		List<WebElement> productList = getDriver().findElementsByXPath(catsVariable.getORM(website+".Ticket.GA_TicketType").getXpath()); 

		System.out.println(": "+productList.size());

		for(int i=0; i<productList.size();i++)
		{
			System.out.println("list:"+productList.get(i).getText());
			if(productList.get(i).getText().equalsIgnoreCase(type))
			{  
				System.out.println("product list: "+productList.get(i).getText());
				Thread.sleep(2000);
				int chkboxindex = i+3;
				catsAction.clickJS("(//div[@class='checkbox'])["+chkboxindex+"]");
				Thread.sleep(2000);
				int priceindex= i+1;
				priceOfProductAdult=getDriver().findElementByXPath("(//div[@class='visitor-type']/span[contains(text(),'Adult')]/following-sibling::span[2])["+priceindex+"]").getText();
				System.out.println("priceOfProductAdult:"+priceOfProductAdult);
				Thread.sleep(2000);
				priceOfProductJunior=getDriver().findElementByXPath("(//div[@class='visitor-type']/span[contains(text(),'Junior')]/following-sibling::span[2])["+priceindex+"]").getText();
				System.out.println("priceOfProductJunior:"+priceOfProductJunior);	

				break;
			}

		}
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AddToCartTypeTicket",this.ormData));

	}

	@Then("^User select the (.+) of Annual Pass ticket$")
    public void user_select_the_of_annual_pass_ticket(String annualtype) throws Throwable {
	 Thread.sleep(3000);
	 System.out.println("annualtype:"+annualtype);
	List<WebElement> productList = getDriver().findElementsByXPath(catsVariable.getORM(website+".AnnualPass.ProductList").getXpath()); 
	
		System.out.println("productList: "+productList.size());
		
		for(int i=0; i<productList.size();i++)
		{
		    System.out.println("list:"+productList.get(i).getText());
		    if(productList.get(i).getText().contains(annualtype))
		//	if(productList.get(i).getText().equalsIgnoreCase(annualtype))
			{   
		    	int priceindex;
				System.out.println("annualtype product list: "+productList.get(i).getText());
				Thread.sleep(2000);
				int chkboxindex = i+1;
				catsAction.clickJS("(//input[contains(@value,'Annual')]/following-sibling::div)["+chkboxindex+"]");
				if(website.equals("FWAD"))
				{
					priceindex=i+2;
				}
				else
				{
				    priceindex=i+1;
				}
				priceOfPasses=getDriver().findElementByXPath("(//span[@class='currency'])["+priceindex+"]").getText();
				System.out.println("priceOfPasses:"+priceOfPasses);
            	Thread.sleep(3000);
				totalPriceOnTicket = getDriver().findElementByXPath("(//div[@class='amount']/h3)[2]").getText();
			    System.out.println("totalPriceOnTicket:"+totalPriceOnTicket);
			    break;
			}
			
		}
	
	   
		Thread.sleep(2000);
		
	//	catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AddToCartTypeTicket",this.ormData));
	     
    }

	@And("^user add \"([^\"]*)\" adult and \"([^\"]*)\" junior$")
	public void user_add_something_adult_and_something_junior(String strArg1, String strArg2) throws Throwable {
		Thread.sleep(3000);
		adult=strArg1;
		AdultAdded= Integer.parseInt(strArg1);
		junior= strArg2;
		JuniorAdded= Integer.parseInt(strArg2);

		catsAction.scrollPageUp();
		String adultsPresent= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.PresentAdult",this.ormData)).getXpath()).getAttribute("value");
		System.out.println("adultsPresent: "+adultsPresent);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.DecreaseAdult_GA",this.ormData),adultsPresent);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.IncreaseAdult_GA",this.ormData),adult);

		String juniorPresent= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.PresentJunior",this.ormData)).getXpath()).getAttribute("value");
		System.out.println("juniorPresent: "+juniorPresent);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.DecreaseJunior_GA",this.ormData),adultsPresent);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.IncreaseJunior_GA",this.ormData),junior);

	}
	@And("^user only add \"([^\"]*)\" adults only$")
	public void user_only_add_something_adults_only(String strArg1) throws Throwable {
		Thread.sleep(3000);
		String adultonly=strArg1;

		catsAction.scrollPageUp();
		String adultsPresent= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Ticket.PresentAdult",this.ormData)).getXpath()).getAttribute("value");
		System.out.println("adultsPresent: "+adultsPresent);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.DecreaseAdult_GA",this.ormData),adultsPresent);
		catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Ticket.IncreaseAdult_GA",this.ormData),adultonly);

	}

	@Then("^User check help icon is present against product present$")
	public void user_check_help_icon_is_present_against_product_present() throws Throwable {
		Thread.sleep(3000);

		List<WebElement> productList = getDriver().findElementsByXPath(catsVariable.getORM(website+".Extras.ProductList").getXpath()); 

		System.out.println("productList: "+productList.size());

		for(int i=0; i<productList.size();i++)
		{
			System.out.println("list:"+productList.get(i).getText());
			Thread.sleep(4000);
			int helpiconindex = i+1;
			catsAction.verifyElementPresent("//*[@id='panel3']/div/div/div[1]/div/div/div["+helpiconindex+"]/div/div/div[2]/div[3]/div/a");
		}   

	}

	@Then("^user check for early bird text in minicart$")
	public void user_check_for_early_bird_text_in_minicart() throws Throwable {
		Thread.sleep(2000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".MiniCart.EarlyBirdText",this.ormData));
	}

	@When("^user click on experiences tab$")
	public void user_click_on_experiences_tab() throws Throwable {
		Thread.sleep(3000);
		catsAction.scrollPageUp();
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Experiences",this.ormData));

	}

	@When("^user click on offers tab$")
	public void user_click_on_offers_tab() throws Throwable {
		Thread.sleep(3000);
		catsAction.scrollPageUp();
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Offers",this.ormData));

	}

	@Then("^User select the Offers ticket$")
	public void user_select_the_offers_ticket() throws Throwable { 
		Thread.sleep(3000);

		catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Offers.Product",this.ormData));



	}


	@Then("^user add \"([^\"]*)\" Offers Ticket to cart$")
	public void user_add_something_offers_ticket_to_cart(String strArg1) throws Throwable {
		String NoOfOffersTickets = strArg1;
		int noOfTicketsToAdd=Integer.parseInt(NoOfOffersTickets);
		//  catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AnnualPassCheckbox",this.ormData));
		Thread.sleep(3000);
		String noOfTickets= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Offers.TicketCount",this.ormData)).getXpath()).getAttribute("value");
		System.out.println("noOfTickets: "+noOfTickets);
		int noOfPassesAlreadyPresent=Integer.parseInt(noOfTickets);

		if(noOfPassesAlreadyPresent<noOfTicketsToAdd)
		{
			int increase = noOfTicketsToAdd-noOfPassesAlreadyPresent;
			System.out.println("increase: "+increase);
			String incresePass= Integer.toString(increase);
			catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Offers.IncreaseTickets",this.ormData),incresePass);

		}
		else if(noOfPassesAlreadyPresent>noOfTicketsToAdd)
		{
			int decrease =  noOfPassesAlreadyPresent-noOfTicketsToAdd;
			System.out.println("decrease: "+decrease);
			String decreasePass= Integer.toString(decrease);
			catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Offers.decreaseTickets",this.ormData),decreasePass);
		}

		try{
			if(getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Offers.TicketCount2",this.ormData)).getXpath()).isDisplayed())
			{   
				String noOfTickets2= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Offers.TicketCount2",this.ormData)).getXpath()).getAttribute("value");
				System.out.println("noOfTickets2: "+noOfTickets2);
				int noOfPassesAlreadyPresent2=Integer.parseInt(noOfTickets2);

				if(noOfPassesAlreadyPresent2<noOfTicketsToAdd)
				{
					int increase = noOfTicketsToAdd-noOfPassesAlreadyPresent2;
					System.out.println("increase: "+increase);
					String incresePass= Integer.toString(increase);
					catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Offers.IncreaseTickets2",this.ormData),incresePass);

				}
				else if(noOfPassesAlreadyPresent2>noOfTicketsToAdd)
				{
					int decrease =  noOfPassesAlreadyPresent2-noOfTicketsToAdd;
					System.out.println("decrease: "+decrease);
					String decreasePass= Integer.toString(decrease);
					catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Offers.decreaseTickets2",this.ormData),decreasePass);
				}                                
			}
		}catch(Exception e)
		{
			System.out.println("Only one type of offer tickets available");
		}


	}


	@Then("^user add date and ticket to cart$")
	public void user_add_date_and_ticket_to_cart() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Offers.calender",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Offers.date",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Offers.select",this.ormData));

		catsAction.click(CustomRules.locatorPresentInSite(website+".Offers.AddToCart",this.ormData));
	}


}


