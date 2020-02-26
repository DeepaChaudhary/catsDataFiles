package cats.selenium.bdd.stepdef;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.retest.web.selenium.By;
import junit.framework.Assert;
import net.java.quickcheck.generator.support.IntegerGenerator;

public class Regression extends CATSCucumberConfig {
	
	public String site;
	public String website;
	int days=1;
	String productName;
	String UpgradeMyTicketName;
	int productPrice;
	int guestCount;
	int myCartguestCountOldvalue;
	int MycarTGuestCountNewValue;
	String orderID;
	//CustomRules customrules;
	
	
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
	  
	  //----------------------------------------------
	  public void clickOnBookNowButtonOfGivenFeature(String ListLocator, String featureName) throws Throwable {
		   
		  // String featureLabelNameListPath =catsVariable.getORM(website + ".HomePage.FeatureLabelNameListPath").getXpath();
		  
		   System.out.println("featureLabelNameListPath........."+ListLocator);
		   List<WebElement> featureLabelNameList = getDriver().findElements(By.xpath(ListLocator));
	 	   System.out.println("FeatureLabelName count......"+featureLabelNameList.size());
	 	   
	 	   for (int k=0; k<featureLabelNameList.size();k++)
	 		 {
	 	    	  System.out.println("Feature Label Name" +featureLabelNameList.get(k).getText());
	 	    	  System.out.println("Feature Label Name.........." +featureName);
	 	    	  
	 	    	  if(featureLabelNameList.get(k).getText().equalsIgnoreCase(featureName))
	 	    	  {
	 	    		 System.out.println("Inside if");
	 	    		 Thread.sleep(3000);
	 	    		 catsAction.click("(//ul[@class='c-feature-grid ']//a[contains(text(),'BUY NOW FOR AED')])["+(k+1)+"]");
	 	    		 break;
	 	    	  }
	 		 } 
	    	  
	    }
	  //-------------------------------------------------------------------------------------------
	  public String afterSixMonthdate()
	  {
	  	DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("dd MMM, yyyy");
	    String dateAfterSixMonth = formatterr.format(LocalDate.now().plusMonths(6).minusDays(1));
	    System.out.println(dateAfterSixMonth);
	    return dateAfterSixMonth;
	    
	  }
	  
	  public String getAddedProductName()
	  { 
		  //	String productNamePath =catsVariable.getORM(website + ".Ticketpage.ExtraTicketName").getXpath();
		  
		    String productNamePath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Ticketpage.ExtraTicketName", this.ormData)).getXpath();
	        WebElement productNameElement = getDriver().findElement(By.xpath(productNamePath));
	        System.out.println("Product name" +productNameElement.getText());
			return productNameElement.getText();  
	  }
	  public int getAddedProductPrice()
	  { 
		  	//String productPricePath =catsVariable.getORM(website + ".Ticketpage.ExtraTicketPrice").getXpath();
		  	String productPricePath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Ticketpage.ExtraTicketPrice", this.ormData)).getXpath();
	        WebElement productPriceElement = getDriver().findElement(By.xpath(productPricePath));
	        int ProductPrice =Integer.parseInt(productPriceElement.getText().split(" ")[1]);
	        System.out.println("Product price" +ProductPrice);
	        return ProductPrice;   
	  }
	  public int getAddedGuestCount()
	  { 
		  	String guestCountPath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Ticketpage.ExtraGuestCountInputBox",this.ormData)).getXpath();
	        WebElement guestCountPathElement = getDriver().findElement(By.xpath(guestCountPath));
	        System.out.println("Guest Count" +Integer.parseInt(guestCountPathElement.getAttribute("value")));
	        return Integer.parseInt(guestCountPathElement.getAttribute("value"));
	  }
	  
	  public int getMycartGuestCount() throws InterruptedException
	  { 
		    Thread.sleep(3000);
		    String myCartGuestCountPath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartGuestCount",this.ormData)).getXpath();
	        WebElement myCartGuestCountElement = getDriver().findElement(By.xpath(myCartGuestCountPath));
	        System.out.println("val----------------------------"+myCartGuestCountElement.getAttribute("value"));
	        return Integer.parseInt(myCartGuestCountElement.getAttribute("value"));
	        
	  }
	  
	  
	  
	  
  //----------------------------------------------------------------------------------------

	  @When("^User clicks on main Menu Ticket item and select \"([^\"]*)\"$")
	    public void user_clicks_on_main_menu_ticket_item_and_select_something(String SubMenuItem) throws Throwable {
		  catsAction.pageLoadWait();
		  	Thread.sleep(13000);
		  	System.out.println("user is clicking ticket tab sub Menu item" +SubMenuItem);
			catsAction.hoverNClickSubItem(CustomRules.locatorPresentInSite(website + ".Header.Tickets",this.ormData),SubMenuItem);
		  
	    }
	  @Then("^Verify user navigate to ticket page$")
	    public void verify_user_navigate_to_ticket_page() throws Throwable {
		  catsAction.pageLoadWait();
		  getDriver().getCurrentUrl().contains("tickets");
	    }

	  @When("^User click on Book Now Button of \"([^\"]*)\" Feature$")
	    public void user_click_on_book_now_button_of_something_feature(String TicketToselect) throws Throwable {
		  Thread.sleep(8000);
		  String featureLabelNameListPath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Ticketpage.GeneralAdmissionFeatures",this.ormData)).getXpath();
		  System.out.println("featureLabelNameListPath......"+featureLabelNameListPath);
		  clickOnBookNowButtonOfGivenFeature(featureLabelNameListPath, TicketToselect);
	    }
	  

	    @Then("^Verify In Ticket page \"([^\"]*)\" Ticket is selected$")
	    public void verify_in_ticket_page_something_ticket_is_selected(String ticketName) throws Throwable {
	    	Thread.sleep(8000);
	    	System.out.println("ticketName..........."+ticketName);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Ticketpage.selectedTicket", this.ormData)), ticketName);
	    	
	    }
	    
	    @And("^User click on add to cart button on General addmission page$")
	    public void user_click_on_add_to_cart_button_on_general_addmission_page() throws Throwable {
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Ticketpage.GeneralAdmissionAddToCartButton", this.ormData));
	    }
	    
	    @Then("^Verify In Ticket page \"([^\"]*)\" Ticket is selected on Page$")
	    public void verify_in_ticket_page_something_ticket_is_selected_on_page(String selectedTicketName) throws Throwable {
	    	Thread.sleep(8000);
	    	System.out.println("ticketName..........."+selectedTicketName);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Ticketpage.ExtraSelectedTicket", this.ormData)), selectedTicketName);
	    	
	    }
	    @And("^User click on add to cart button on Extra ticket page$")
	    public void user_click_on_add_to_cart_button_on_extra_ticket_page() throws Throwable {
	    	productName=getAddedProductName();
	    	productPrice=getAddedProductPrice();
	    	guestCount=getAddedGuestCount();		
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Bookingpage.ExtraAddToCartButton", this.ormData));
	    }
	    
	    @Then("^User Open Mini cart details$")
	    public void user_open_mini_cart_details() throws Throwable 
	    {
	    	Thread.sleep(2000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Bookingpage.OpenMiniCartArrow", this.ormData));
	    }

	    @And("^Verify Ticket Valid upto six month from current date on mini cart$")
	    public void verify_ticket_valid_upto_six_month_from_current_date_on_mini_cart() throws Throwable {
	    	String ValidUntil = afterSixMonthdate();
	    	System.out.println("valid upto ........"+ValidUntil);
	    	Thread.sleep(2000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".MiniCartDetails.ValidUntildate", this.ormData)), afterSixMonthdate());
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".MiniCartDetails.ValidUntildate", this.ormData)), ValidUntil);
	    }
	    @And("^Verify Ticket Valid upto six month from current date in Cart Summary$")
	    public void verify_ticket_valid_upto_six_month_from_current_date_in_cart_summary() throws Throwable {
	    	catsAction.pageLoadWait();
	    	Thread.sleep(10000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.OpencartSummaryArrow", this.ormData));
	    	
	    	Thread.sleep(2000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".MyPaymentPage.ValidUntildate", this.ormData)), afterSixMonthdate());
	    	//catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".MiniCartDetails.ValidUntildate", this.ormData)), ValidUntil);
	    }

	    @When("^User Increase the spinner count of Adult and junior if exceed 14 Group booking error message should display$")
	    public void user_increase_the_spinner_count_of_adult_and_junior_if_exceed_14_group_booking_error_message_should_display() throws Throwable {
	    	Thread.sleep(10000);
	    	Thread.sleep(2000);
	    	
	    	String AdultCountPath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Ticketpage.AdultInputBox",this.ormData)).getXpath();
	        WebElement AdultCountElement = getDriver().findElement(By.xpath(AdultCountPath));
	        int adultCount = Integer.parseInt(AdultCountElement.getAttribute("value"));
	         System.out.println("adult count......"+adultCount);
	         
	         String juniorCountPath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Ticketpage.JuniorInputBox",this.ormData)).getXpath();
	        WebElement juniorCountElement = getDriver().findElement(By.xpath(juniorCountPath));
	        int juniorCount = Integer.parseInt(juniorCountElement.getAttribute("value")); 
	        System.out.println("junior count......"+juniorCount);
	        
	        if((adultCount+juniorCount)%2==0) {
	        	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Ticketpage.JuniorSpinner", this.ormData));
	        }
	        
	        while(14>=(adultCount+juniorCount)) {
	        	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Ticketpage.AdultSpinnerAdd", this.ormData));
	        	Thread.sleep(100);
	        	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Ticketpage.JuniorSpinnerAdd", this.ormData));
	        	Thread.sleep(100);
	        	adultCount = Integer.parseInt(AdultCountElement.getAttribute("value"));
	        	juniorCount = Integer.parseInt(juniorCountElement.getAttribute("value")); 
			}
	        Thread.sleep(1000);
	        System.out.println("total...."+(adultCount+juniorCount));
	        catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Ticketpage.GroupBookingError", this.ormData));
	    	
	    }
	    
	    @Then("^Verify Add to cart button is disabled$")
	    public void verify_add_to_cart_button_is_disabled() throws Throwable {
	    	 Thread.sleep(1000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Ticketpage.AddToCartDisabledButton", this.ormData));
	    }
	    

	    @And("^Verify Ticket Valid upto six month from current date in Confirmation Page$")
	    public void verify_ticket_valid_upto_six_month_from_current_date_in_confirmation_page() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".PaymentConfirmationpage.ValidUntildate", this.ormData)), afterSixMonthdate());
	    }
	    

	    @And("^User click on Extra Tab$")
	    public void user_click_on_extra_tab() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Bookingpage.ExtraTab", this.ormData));
	    }
	    
	    @And("^User Should add Guest Count$")
	    public void user_should_add_guest_count() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Ticketpage.ExtratabSpinnerAdd", this.ormData));
	    }

	    @Then("^user Verify Products added with correct price, date and guest count in Mini cart$")
	    public void user_verify_products_added_with_correct_price_date_and_guest_count_in_mini_cart() throws Throwable {
	    	
	    	Thread.sleep(4000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartProductName", this.ormData)), productName);
	    	Thread.sleep(5000);
	    	
	    		String guestQuantityPath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartGuestCount",this.ormData)).getXpath();
		        WebElement guestQuantityElement = getDriver().findElement(By.xpath(guestQuantityPath));
		        System.out.println("val----------------------------"+guestQuantityElement.getAttribute("value"));
		        catsAction.verifyVariableValueContainsIgnoreCase(guestQuantityElement.getAttribute("value"), Integer.toString(guestCount));
		        
	    	
	    	int totalAmount=guestCount*productPrice;
	    	System.out.println("totalPrice..."+totalAmount);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartTotalPrice", this.ormData)),Integer.toString(totalAmount));
	    	
	    }
	    
	    @Then("^user Verify Products added with correct price, date and guest count in in Cart Summary$")
	    public void user_verify_products_added_with_correct_price_date_and_guest_count_in_in_cart_summary() throws Throwable {
	    	
	    	catsAction.pageLoadWait();
	    	Thread.sleep(10000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.OpencartSummaryArrow", this.ormData));
	    	
	    	Thread.sleep(1000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartProductName", this.ormData)), productName);
	    	Thread.sleep(1000);
    	
	    	String guestQuantityPath1 =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.GuestCount",this.ormData)).getXpath();
	        WebElement guestQuantityElement1 = getDriver().findElement(By.xpath(guestQuantityPath1));
	        System.out.println("val----------------------------"+guestQuantityElement1.getAttribute("value"));
	        catsAction.verifyVariableValueContainsIgnoreCase(guestQuantityElement1.getAttribute("value"), Integer.toString(guestCount));
	    	
	    	int totalAmount=guestCount*productPrice;
	    	System.out.println("totalPrice..."+totalAmount);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartTotalPrice", this.ormData)),Integer.toString(totalAmount));
	    	
	    }
	    

	    @Then("^user Verify Products added with correct price, date and guest count in in Confirmation Page$")
	    public void user_verify_products_added_with_correct_price_date_and_guest_count_in_in_confirmation_page() throws Throwable {
	    	
	    	catsAction.pageLoadWait();
	    	Thread.sleep(10000);
	    	//catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.OpencartSummaryArrow", this.ormData));
	    	Thread.sleep(1000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".PaymentConfirmationpage.Productname", this.ormData)), productName);
	    	Thread.sleep(1000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".PaymentConfirmationpage.Quantity", this.ormData)),Integer.toString(guestCount));
	    	int totalAmount=guestCount*productPrice;
	    	System.out.println("totalPrice..."+totalAmount);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".PaymentConfirmationpage.TotalPrice", this.ormData)),Integer.toString(totalAmount));
	    }
	    
	    @And("^Verify that orignal product will be added in the cart$")
	    public void verify_that_orignal_product_will_be_added_in_the_cart() throws Throwable {

	    	Thread.sleep(3000);
	        catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartProductName", this.ormData)),"1 Day 1 Park");
	    }
	    
	    @And("^User will select ticket \"([^\"]*)\" in Upgrade My ticket Overlays$")
	    public void user_will_select_ticket_something_in_upgrade_my_ticket_overlays(String UpgardeTicketname) throws Throwable {
	    	UpgradeMyTicketName=UpgardeTicketname;
	    	System.out.println("upgrade ticket name" +UpgradeMyTicketName);
	    	Thread.sleep(3000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Bookingpage.UpgradeMyTicketButton", this.ormData));
	    }
	    
	    @And("^Verify that Upgrade ticket get added in the cart$")
	    public void verify_that_upgrade_ticket_get_added_in_the_cart() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartProductName", this.ormData)),UpgradeMyTicketName);
	    }
	    
	    @Then("^User Click on Add to cart button and Upgrade My ticket OverLay should Open$")
	    public void user_click_on_add_to_cart_button_and_upgrade_my_ticket_overlay_should_open() throws Throwable {
	    	catsAction.click(website+".Ticket.AddToCart");
			switch (website){

			case("YWW"):
				Thread.sleep(3000);
				catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Bookingpage.UpgradeMyTicketOverlay", this.ormData));
			break;

			case("FWAD"):
				catsAction.click(website+".Ticket.close");
			break;

			default:
				break;
			}

		}
	
	    @And("^User Increase Guest Count in My cart by \"([^\"]*)\"$")
	    public void user_increase_guest_count_in_my_cart_by_something(String count) throws Throwable {
	    	Thread.sleep(3000);
	    	myCartguestCountOldvalue=getMycartGuestCount();
	    	System.out.println("myCartguestCountOldvalue........."+myCartguestCountOldvalue);
	    	Thread.sleep(1000);
	    	catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartSpinnerAdd", this.ormData),count);
	    }
	    
	    @And("^Verify Guest Count value get increased in My cart by \"([^\"]*)\"$")
	    public void verify_guest_count_value_get_increased_in_my_cart_by_something(String count) throws Throwable {
	    	Thread.sleep(2000);
	    	MycarTGuestCountNewValue= myCartguestCountOldvalue + Integer.parseInt(count);
	    	System.out.println("MycarTGuestCount........."+MycarTGuestCountNewValue);
	    	Thread.sleep(3000);
	    	catsAction.verifyVariableValueContainsIgnoreCase(Integer.toString(MycarTGuestCountNewValue), Integer.toString(getMycartGuestCount()));
	    }
	    
	    @And("^Verify Guest Count value get increased in Cart Summary$")
	    public void verify_guest_count_value_get_increased_in_cart_summary() throws Throwable {
	    	
	    	catsAction.pageLoadWait();
	    	Thread.sleep(10000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.OpencartSummaryArrow", this.ormData));
	    	Thread.sleep(3000);
	    	System.out.println("MycarTGuestCount........."+MycarTGuestCountNewValue);
	    	catsAction.verifyVariableValueContainsIgnoreCase(Integer.toString(MycarTGuestCountNewValue), Integer.toString(getMycartGuestCount()));
	    }


	    @Then("^Verify In General Admission multi park tickets will be displayed in carousel form$")
	    public void verify_in_general_admission_multi_park_tickets_will_be_displayed_in_carousel_form() throws Throwable {
	    	Thread.sleep(10000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Ticketpage.GeneralAdmissionCarousel", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Ticketpage.GeneralAdmissionCarouselNextArrow", this.ormData));
	    	
	    }
	    
	    @When("^User Click on payment Tab from header$")
	    public void user_click_on_payment_tab_from_header() throws Throwable {
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Bookingpage.PaymentTab", this.ormData));
	    }
	    
	    @Then("^Verify user Navigate to Booking Page$")
	    public void verify_user_navigate_to_booking_page() throws Throwable {
	    	
	    	Thread.sleep(10000);
	    	getDriver().getCurrentUrl().contains("booking");
	    }
	    
	    @Then("^Verify that multiple tickets get added from different tabs$")
	    public void verify_that_multiple_tickets_get_added_from_different_tabs() throws Throwable {
	    	
	    	catsAction.verifyElementsCount(CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartProductNameList", this.ormData), "3");
	    }
	    
	    @When("^Verify User is on home Page$")
	    public void verify_user_is_on_home_page() throws Throwable {
	    }

	    @Then("^Verify Product name and product description is present on Flash Sale$")
	    public void verify_product_name_and_product_description_is_present_on_flash_sale() throws Throwable {
	    	Thread.sleep(10000);
	    	catsAction.scrollPageDown();
	    	catsAction.scrollIntoView(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleProductName", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleProductName", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleProductDescription", this.ormData));
	    }

	    @Then("^Verify Product Time is present on Flash Sale$")
	    public void verify_product_time_is_present_on_flash_sale() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleTimer", this.ormData));
	    }

	    @Then("^Verify Discount amount, Saving amount and Price is present on Flash Sale$")
	    public void verify_discount_amount_saving_amount_and_price_is_present_on_flash_sale() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleDiscountAmt", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleSavingAmt", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSalePrice", this.ormData));
	    }

	    @Then("^Verify Quantity selection is present and Working on Flash Sale$")
	    public void verify_quantity_selection_is_present_and_working_on_flash_sale() throws Throwable {
	    	
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleQtySelection", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleSpinnerMinus", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleSpinnerAdd", this.ormData));
	    	
	    	Thread.sleep(1000);
	    	String FlashSaleguestQtyPath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleQtySelection",this.ormData)).getXpath();
	        WebElement FlashSaleguestQtyElement = getDriver().findElement(By.xpath(FlashSaleguestQtyPath));
	        
	        int FlashSaleguestQty = Integer.parseInt(FlashSaleguestQtyElement.getAttribute("value"));
	        System.out.println("FlashSaleguestQty1----------------------------"+FlashSaleguestQtyElement.getAttribute("value"));
	        
	        Thread.sleep(100);
	        catsAction.click(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleSpinnerMinus", this.ormData));
	        catsAction.verifyVariableValueContainsIgnoreCase(FlashSaleguestQtyElement.getAttribute("value"), Integer.toString(FlashSaleguestQty-1));
	        
	        FlashSaleguestQty = Integer.parseInt(FlashSaleguestQtyElement.getAttribute("value"));
	        System.out.println("FlashSaleguestQty2----------------------------"+FlashSaleguestQtyElement.getAttribute("value"));
	        
	        Thread.sleep(100);
	        catsAction.click(CustomRules.locatorPresentInSite(website + ".HomePage.FlashSaleSpinnerAdd", this.ormData));
	    	catsAction.verifyVariableValueContainsIgnoreCase(FlashSaleguestQtyElement.getAttribute("value"), Integer.toString(FlashSaleguestQty+1));
	    	
	    }
	         
	    
	    @And("^Verify When user removes the valid coupon code then discounted price will change to original price$")
	    public void verify_when_user_removes_the_valid_coupon_code_then_discounted_price_will_change_to_original_price() throws Throwable {
	    	 Thread.sleep(8000);
	    	String DiscountedPricePath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.TotalPriceHeader", this.ormData)).getXpath();
	        WebElement DiscountedPriceElement = getDriver().findElement(By.xpath(DiscountedPricePath));
	        System.out.println("DiscountedPriceElemnt----------------------------"+DiscountedPriceElement.getText());
	        int DiscountedPrice = Integer.parseInt(DiscountedPriceElement.getText().replaceAll(",", "").split("\\.")[0]);
	        System.out.println("DiscountedPrice----------------------------"+DiscountedPrice);
	        
	        Thread.sleep(5000);
	         catsAction.clickJS(CustomRules.locatorPresentInSite(website+".MyPaymentPage.PromoCodeCancelButton",this.ormData));
	         Thread.sleep(5000);
	         
	         String OriginalPricePath =catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.TotalPriceHeader",this.ormData)).getXpath();
		        WebElement OriginalPriceElement = getDriver().findElement(By.xpath(OriginalPricePath));
		        System.out.println("OriginalPriceElemnt----------------------------"+OriginalPriceElement.getText());
		        int OriginalPrice = Integer.parseInt(OriginalPriceElement.getText().replaceAll(",", "").split("\\.")[0]);
		        System.out.println("OriginalPrice----------------------------"+OriginalPrice);
		        
		        if(DiscountedPrice<OriginalPrice)
		        {
		        	System.out.println("PromoCode Removed Successfully");
		        	catsAction.reportResultPass("Promo code is removed successfully", "Original price is high than discounted price", Integer.toString(DiscountedPrice), Integer.toString(OriginalPrice));
		        }
	    	
	    }
	    
	    @When("^User click on Header logo and Navigate to home page$")
	    public void user_click_on_header_logo_and_navigate_to_home_page() throws Throwable {
	    	
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".HomePage.HeaderHomePageLogo",this.ormData));
	    }

	    @And("^User Click on Add to cart button on Flash Sale$")
	    public void user_click_on_add_to_cart_button_on_flash_sale() throws Throwable {
	    	Thread.sleep(10000);
	    	catsAction.scrollPageDown();
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".HomePage.FlashSaleCheckoutButton",this.ormData));
	    }

	    @Then("^Verify that flash sale product get merged with already existing products$")
	    public void verify_that_flash_sale_product_get_merged_with_already_existing_products() throws Throwable {
	    	catsAction.pageLoadWait();
	    	Thread.sleep(10000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.OpencartSummaryArrow", this.ormData));
	    	catsAction.verifyElementsCount(CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartProductNameList", this.ormData), "2");
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".MyPaymentPage.FlashSaleProductName", this.ormData)),"The Flash Pass");
	    }
   
	    @When("^User click on any Annual pass ticket checkbox$")
	    public void user_click_on_any_annual_pass_ticket_checkbox() throws Throwable {
	    	Thread.sleep(1000);
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.AnnualPassCheckbox", this.ormData));
	    }

	    @Then("^Verify Add to cart is appearing$")
	    public void verify_add_to_cart_is_appearing() throws Throwable {
	    	Thread.sleep(1000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.AddToCartAnnual", this.ormData));
	    }

	    @Then("^Verify Add to cart button is greyed out$")
	    public void verify_add_to_cart_button_is_greyed_out() throws Throwable {
	    	Thread.sleep(1000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Bookingpage.AnnualPassAddToCartGreyedOutBtn", this.ormData));
	    }

	    @And("^User decrease Guest Count in My cart by \"([^\"]*)\"$")
	    public void user_decrease_guest_count_in_my_cart_by_something(String count) throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website+".Bookingpage.AnnualPassAdultSpinnerMinus", this.ormData),"2");
	    	//catsAction.clickMultipleTimes(CustomRules.locatorPresentInSite(website + ".Bookingpage.MyCartSpinnerAdd", this.ormData),count);
	    } 

	    @Then("^Verify Add to cart is not appearing$")
	    public void verify_add_to_cart_is_not_appearing() throws Throwable {
	    	Thread.sleep(1000);
	    	catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Ticket.AddToCartAnnual", this.ormData));
	    }
	    
	    @When("^User refresh the confirmation page$")
	    public void user_refresh_the_confirmation_page() throws Throwable {
	    	Thread.sleep(1000);
	    	catsAction.refresh();
	    	
	    }

	    @Then("^Verify User is not able to access confirmation page 2nd time$")
	    public void verify_user_is_not_able_to_access_confirmation_page_2nd_time() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Payment.ticketConfirmation", this.ormData));
	    	catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Payment.ticket_Booking_id", this.ormData));
	    	
	    }
	    @Then("^User validate mandatory field error message$")
	    public void user_validate_mandatory_field_error_message() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".MyPaymentPage.CardNumberErrorvalidation", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".MyPaymentPage.validityErrorvalidation", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".MyPaymentPage.CvvNumberErrorvalidation", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".MyPaymentPage.TermAndCondErrorvalidation", this.ormData));

	    }
	    
	    @And("^User expand cart summary on My payment page$")
	    public void user_expand_cart_summary_on_my_payment_page() throws Throwable {
	    	catsAction.pageLoadWait();
	    	Thread.sleep(10000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".MyPaymentPage.OpencartSummaryArrow", this.ormData));
	    }
	    
	    @And("^User Delete item from the cart Summary$")
	    public void user_delete_item_from_the_cart_summary() throws Throwable {
	    	  catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Ticket.AdultDelete",this.ormData));
	    	  Thread.sleep(1000);
	    	  catsAction.click(CustomRules.locatorPresentInSite(website+".Ticket.YESconformDelete",this.ormData));
	    	  Thread.sleep(2000);  
	    }
	  
	    @And("^User Verify the orderId on payment confirmation page$")
	    public void user_verify_the_orderid_on_payment_confirmation_page() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".PaymentConfirmationpage.orderID", this.ormData));
	    	orderID= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".PaymentConfirmationpage.orderID",this.ormData)).getXpath()).getText();
	    	System.out.println("Order id ................."+orderID);
	    	
	    	
	    }
	    
	    @And("^User login$")
	    public void user_login() throws Throwable {
	    	catsAction.pageLoadWait();
			catsAction.enter(CustomRules.locatorPresentInSite(website+".CheckIn.EmailId",this.ormData), "$MiralGlobal.EmailIDNew.<<site>>");
			catsAction.enter(CustomRules.locatorPresentInSite(website+".CheckIn.Password",this.ormData), "$MiralGlobal.PasswordNew.<<site>>");
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Submit",this.ormData));
			catsAction.pageLoadWait();
	    }

	    @And("^User click on my purchase tab$")
	    public void user_click_on_my_purchase_tab() throws Throwable {
	    	catsAction.pageLoadWait();
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".ProfilePagePreference.MyPurchasetab",this.ormData));
	    	catsAction.pageLoadWait();
	    }
	    
	   
	    @Then("^Verify purchase order id is available in my purchase tab$")
	    public void verify_purchase_order_id_is_available_in_my_purchase_tab() throws Throwable {
	    	Thread.sleep(40000);
	    	String PurchasedtaborderID= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PurchaseHistoryOrderId",this.ormData)).getXpath()).getText();
	    	catsAction.verifyVariableValueContainsIgnoreCase(orderID,PurchasedtaborderID);

	    }
	    
	    //----------------explore------------------------------
	    
	    @When("^User clicks on main Menu explore item and select \"([^\"]*)\"$")
	    public void user_clicks_on_main_menu_explore_item_and_select_something(String exploreSubMenu) throws Throwable {
	    	
	    	 catsAction.pageLoadWait();
			  	Thread.sleep(13000);
			  	System.out.println("user is clicking ticket tab sub Menu item" +exploreSubMenu);
				catsAction.hoverNClickSubItem(CustomRules.locatorPresentInSite(website + ".Header.Explore",this.ormData),exploreSubMenu);
	       
	    }
	   
	    
	    @Then("^Verify user navigate to dinning listing page and click on Read more Button$")
	    public void verify_user_navigate_to_dinning_listing_page_and_click_on_read_more_button() throws Throwable {
	    	catsAction.pageLoadWait();
	    	Thread.sleep(8000);
	    	getDriver().getCurrentUrl().contains("dining");
	    	catsAction.scrollDownByOffset("800");
	    	catsAction.scrollIntoView((CustomRules.locatorPresentInSite(website+".DiningPage.ReadMore",this.ormData)));
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".DiningPage.ReadMore",this.ormData));
	    	
	    }

	    @And("^Verify user navigate to Dining Detail page$")
	    public void verify_user_navigate_to_dining_detail_page() throws Throwable {
	    	catsAction.pageLoadWait();
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".DiningPage.landIcon", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".DiningPage.CuisineIcon", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".DiningPage.DineTypeIcon", this.ormData));
	    	}
	    
	    @Then("^Verify user navigate to Ride And Attraction listing page and click on Read more Button$")
	    public void verify_user_navigate_to_ride_and_attraction_listing_page_and_click_on_read_more_button() throws Throwable {
	    	catsAction.pageLoadWait();
	    	Thread.sleep(10000);
	    	getDriver().getCurrentUrl().contains("rides");
	    	catsAction.scrollDownByOffset("400");
	    	catsAction.scrollIntoView((CustomRules.locatorPresentInSite(website+".DiningPage.ReadMore",this.ormData)));
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".DiningPage.ReadMore",this.ormData));
	    }

	    @And("^Verify user navigate to Ride And Attraction Detail page$")
	    public void verify_user_navigate_to_ride_and_attraction_detail_page() throws Throwable {
	    	catsAction.pageLoadWait();
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".RideAdventurePage.RideTypeIcon", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".RideAdventurePage.landIcon", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".RideAdventurePage.MinHieghtIcon", this.ormData));
	    }
	    //-----------------------------------------yadId------------------------------------
	    @And("^User Click on Yas Id link$")
	    public void user_click_on_yas_id_link() throws Throwable {
	    	Thread.sleep(10000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".PaymentConfirmationpage.YasIdRegistrationLink",this.ormData));
	    }

	    @And("^verify user will able to register through YAS ID and receives email to reset password msg Should come$")
	    public void verify_user_will_able_to_register_through_yas_id_and_receives_email_to_reset_password_msg_should_come() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".PaymentConfirmationpage.YasIdRegistrationCongrOverlay", this.ormData));
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".PaymentConfirmationpage.YasIdRegistrationCongrMsg", this.ormData)),"Your YAS ID is all set and a confirmation has been sent to");
	    }
	    
	    @And("^user is able to enter yas id registered user details in guest form$")
	    public void user_is_able_to_enter_yas_id_registered_user_details_in_guest_form() throws Throwable {
	    	catsAction.action("Move", "SKMouseMove", "", "", "", "", "", "", "");
	 	   // 	catsAction.scrollPageDown();
	 	    //	catsAction.scrollDownByOffset("1500");
	 	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData));
	 	   // 	website+".Search.cookieClose"
	 	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData));
	 	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Fname",this.ormData), "$MiralGlobal.YasIdRegisteredUserFirstName.<<site>>");
	 	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Lname",this.ormData), "$MiralGlobal.YasIdRegisteredUserLastName.<<site>>");
	 	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData));
	 	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.email",this.ormData), "$MiralGlobal.YasIdRegisteredUserEmailId.<<site>>");
	 	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".GuestCheckout.Contact",this.ormData), "$MiralGlobal.YasIdRegisteredUserPhnNumber.<<site>>");
	 	    	catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.Nationality",this.ormData));
	 	    	catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.NIndia",this.ormData));
	 	    	catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.Country",this.ormData));
	 	    	catsAction.click(CustomRules.locatorPresentInSite(website+".GuestCheckout.India",this.ormData));
	 	    	
	 	    	Thread.sleep(2000);
	    }
	    @And("^verify user should get pop up message that he or she is already registered$")
	    public void verify_user_should_get_pop_up_message_that_he_or_she_is_already_registered() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".PaymentConfirmationpage.YasIdRegistrationCongrOverlay", this.ormData));
	    	catsAction.verifyTextContainsIgnoreCase((CustomRules.locatorPresentInSite(website + ".PaymentConfirmationpage.YasIdRegistrationCongrMsg", this.ormData)),"Your Yas ID already exist ");
	
	    }
	    @Then("^verify User redirects to Login page successfully$")
	    public void verify_user_redirects_to_login_page_successfully() throws Throwable {
	    	
	    	catsAction.pageLoadWait();
	    	Thread.sleep(3000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Login.GoogleIcon", this.ormData));
	    }

	    @Then("^User clicks on GoogleIcon on login page and it redirects to respective social site page$")
	    public void user_clicks_on_googleicon_on_login_page_and_it_redirects_to_respective_social_site_page() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".Login.GoogleIcon",this.ormData));
	    	
	    }
	    
	    @Then("^verify User Logged in$")
	    public void verify_user_logged_in() throws Throwable {
	    	catsAction.pageLoadWait();
	    	Thread.sleep(3000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Login.UserLoggedInIcon", this.ormData));
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Login.WelcomeLabel", this.ormData));
	    	
	    }
	    
	    @When("^User enter valid credential for Google$")
	    public void user_enter_valid_credential_for_google() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Login.GoogleEmailIdInputBox",this.ormData), "$MiralGlobal.SocialGoogleUserName.<<site>>");
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".Login.EmailNextButton",this.ormData));
	    	Thread.sleep(5000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Login.GooglePasswordInputBox",this.ormData), "$MiralGlobal.SocialGooglePassword.<<site>>");
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".Login.PswdNextButton",this.ormData));
	    }
	    

	    @When("^User enter valid credential for facebook login$")
	    public void user_enter_valid_credential_for_facebook_login() throws Throwable {
	    	Thread.sleep(5000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Login.FacebookEmailIdInputBox",this.ormData), "$MiralGlobal.SocialfacebookUserName.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".Login.FacebookPswdInputBox",this.ormData), "$MiralGlobal.SocialfacebookPassword.<<site>>");
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".Login.FaceBookLoginBtn",this.ormData));
	    	
	    }

	    @Then("^User clicks on facebookIcon on login page and it redirects to respective social site page$")
	    public void user_clicks_on_facebookicon_on_login_page_and_it_redirects_to_respective_social_site_page() throws Throwable {
	    	
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".Login.FacebookIcon",this.ormData));
	    }


}
