package cats.selenium.bdd.stepdef;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.retest.web.selenium.By;

public class PLP extends CATSCucumberConfig{
	
	public String site;
	public String website;
	int days=1;
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

	/*public static Properties readPropertiesFile(String fileName) throws IOException {
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
	}*/
	//------------------------------------------------------date selection------------------------------
	 public boolean dateSelection() throws InterruptedException
      {
      boolean flag = false;
      Thread.sleep(3000);
      //String buttonList =catsVariable.getORM(website + ".HomePage.BookNowButtonList").getXpath();
      
      String availableDateInCal =catsVariable.getORM(website + ".BookNowOverlay.availabledate").getXpath();
      List<WebElement> AvailableDateCount = getDriver().findElements(By.xpath(availableDateInCal));
      for (int m=1; m<=5;m++)
		 {
			 if(AvailableDateCount.size()>0)
			 {
				 System.out.println("Slot available" +m);
				 catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".BookNowOverlay.availabledate",this.ormData));
				 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".BookNowOverlay.PreferredtimeSlots",this.ormData));
				 catsAction.clickJS(CustomRules.locatorPresentInSite(website+".BookNowOverlay.PreferredtimeSlots",this.ormData));
				 catsAction.clickJS(CustomRules.locatorPresentInSite(website+".BookNowOverlay.PreferredtimeSlotsOption",this.ormData));
				 flag = true ;
				 break;
			 }
			 else
			 {
			 Thread.sleep(5000);
			 catsAction.click(website+".Product_Booking.nextMonthClickOnCalender");
			 AvailableDateCount = getDriver().findElements(By.xpath(availableDateInCal));
			 System.out.println("Available Date Count in next month .........."+AvailableDateCount.size());
			 }
			 
		 }
      return flag;
     }
	 
	 //----------------------------------Book Now button--------------------------------------------------------------------------------
	   public void clickBookNowButtonOfGivenFeature(String featureName) throws Throwable {
		   
		   
		   String featureLabelNameListPath =catsVariable.getORM(website + ".HomePage.FeatureLabelNameListPath").getXpath();
		   System.out.println("featureLabelNameListPath........."+featureLabelNameListPath);
		   List<WebElement> FeatureLabelName = getDriver().findElements(By.xpath(featureLabelNameListPath));
	 	     System.out.println("FeatureLabelName count......"+FeatureLabelName.size());
	 	    System.out.println("featureLabelName ........."+FeatureLabelName);
	 	       for (int k=0; k<FeatureLabelName.size();k++)
	 		 {
	 	    	  System.out.println("Feature Label Name" +FeatureLabelName.get(k).getText());
	 	    	  System.out.println("Feature Label Name.........." +featureName);
	 	    	  System.out.println("(//div[@class='buy-ticket-cta']/button)["+(k+1)+"]");
	 	    	  
	 	    	  if(FeatureLabelName.get(k).getText().equalsIgnoreCase(featureName))
	 	    	  {
	 	    		 System.out.println("Inside if");
	 	    		 Thread.sleep(3000);
	 	    		 catsAction.click("(//div[@class='buy-ticket-cta']/button)["+(k+1)+"]");
	 	    		 break;
	 	    	  }
	 		 } 
	    	  
	    }
	   //----------------------------------------
	   
	   
	   public void verifyAndSelectTabOption(String menuName) throws Throwable {
	    	
		   String TabManuOptionListPath =catsVariable.getORM(website + ".Booking.TabManuOptionListPath").getXpath();
		   List<WebElement> TabManuOption = getDriver().findElements(By.xpath(TabManuOptionListPath));
		   
	 	     System.out.println("FeatureLabelName count......"+TabManuOption.size());
	 	       for (int x=0; x<TabManuOption.size();x++)
	 		 {
	 	    	  System.out.println("Menu Option name" +TabManuOption.get(x).getText());
	 	    	  System.out.println("Menu Option name.........." +menuName);
	 	    	  
	 	    	  if(TabManuOption.get(x).getText().equalsIgnoreCase(menuName))
	 	    	  {
	 	    		 System.out.println("Inside if"+TabManuOption.get(x).getText());
	 	    		 catsAction.clickJS("(//ul[@class='tabs-menu']//li//span)["+(x+1)+"]");
	 	    		 break;
	 	    	  }
	 		 } 
	    	  
	    }
	   
	   //-----------------------------------------
	   
	   @When("^User clicks on main Menu Experiences item and select \"([^\"]*)\"$")
	    public void user_clicks_on_main_menu_experiences_item_and_select_something(String subMenuItem) throws Throwable {
		   System.out.println("user is clicking experience tab");
		   System.out.println("user is clicking experience tab sub Menu item" +subMenuItem);
		   Thread.sleep(3000);
			 catsAction.hoverNClickSubItem(CustomRules.locatorPresentInSite(website + ".HomePage.GlobalHeaderMenuBarlistExperience",this.ormData),subMenuItem);
			 Thread.sleep(5000);
	    }

   
	    @When("^User navigate to PLP throught experience option for selecting date to Book product$")
	    public void user_navigate_to_plp_throught_experience_option_for_selecting_date_to_book_product() throws Throwable {
	    		Thread.sleep(10000);
	    	 
	    	// System.out.println("ORM value  .........."+catsVariable.getORM(website + ".HomePage.BookNowButtonList").getXpath());
	    	 
	    	 String buttonList =catsVariable.getORM(website + ".HomePage.BookNowButtonList").getXpath();
	    	 
	    	 System.out.println("ormValue  .........."+buttonList);
	    	 
	    //	 List<WebElement> ButtonList2 = getDriver().findElements(By.xpath("//div[@class='buy-ticket-cta']/button"));
			 List<WebElement> ButtonList1 = getDriver().findElements(By.xpath(buttonList));
			 
			 System.out.println("button count .........."+ButtonList1.size());
			 for (int i=1; i<=ButtonList1.size();i++)
			 {
				 
				 catsAction.click("(//div[@class='buy-ticket-cta']/button)["+i+"]");
				 System.out.println("button is clicked...");
				 Thread.sleep(6000);
				 catsAction.waitUntilElementDisplay(website + ".BookNowOverlay.SelectDateOverlay", "30");
				 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".BookNowOverlay.SelectDateOverlay",this.ormData));

				 if(dateSelection())
				 {
					 break;
				 }
					
				 }
			 System.out.println("for loop done");

	    }
	    
	    @And("^User click on discover More button$")
	    public void user_click_on_discover_more_button() throws Throwable {
	    	List<WebElement> discoverMoreButtonList = getDriver().findElements(By.xpath("//div[@class='readMoreCTA']"));
	    	 System.out.println("Total no of links Available: "+discoverMoreButtonList.size());
	    	 for (int i = 1; i <=discoverMoreButtonList.size(); i++) 
	    	 {
	            catsAction.click("(//div[@class='readMoreCTA'])["+i+"]");
	           Thread.sleep(3000);
	           catsAction.scrollDownByOffset("350");
	           Thread.sleep(5000);
	           catsAction.waitUntilElementDisplay(website+".PDPbook.BookNow", "40");
	           Thread.sleep(5000);
               catsAction.clickJS(website+".PDPbook.BookNow");
	      	    
	    	  	if (dateSelection())
	    	  	{
	    			break;
	    	  		
	    		} 
	    	  	else
	    		{
	    	  		catsAction.navigateBack();
	    		}
	      	  
	    	 }
	     }

	    @And("^User select country of issue$")
	    public void user_select_country_of_issue() throws Throwable {
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".BookNowOverlay.SelectCountryToIssue",this.ormData));
			 catsAction.clickJS(CustomRules.locatorPresentInSite(website+".BookNowOverlay.SelectCountryToIssueOption",this.ormData));
	    }

	    @And("^User click on Terms and condition checkbox$")
	    public void user_click_on_terms_and_condition_checkbox() throws Throwable {
	    	 catsAction.clickJS(CustomRules.locatorPresentInSite(website+".BookNowOverlay.TermsAndConditionCheckBox",this.ormData));
	    }

	    @And("^User click on Add to cart Button on select Date Overlay$")
	    public void user_click_on_add_to_cart_button_on_select_date_overlay() throws Throwable {
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".BookNowOverlay.AddToCartButton",this.ormData));
	    }
	    
	    @And("^User click on Proceed to checkout Button on select Date Overlay$")
	    public void user_click_on_proceed_to_checkout_button_on_select_date_overlay() throws Throwable {
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".BookNowOverlay.ProceedToCheckoutButton",this.ormData));
	    }
	    
	 @And("^Verify user Navigate to Booking Page and Minicart is present$")
	    public void verify_user_navigate_to_booking_page_and_minicart_is_present() throws Throwable {
	    	
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Booking.MiniCart",this.ormData));
	    }
	    
	    @And("^user verifies the product amount listed on cart$")
	    public void user_verifies_the_product_amount_listed_on_cart() throws Throwable {
	    	Thread.sleep(2000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Booking.MinicartValue",this.ormData));
	    }
	   
	    @When("^user click on Check out button of mini cart$")
	    public void user_click_on_check_out_button_of_mini_cart() throws Throwable {
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Booking.MiniCartCheckoutButton",this.ormData));
	    }
	    
	    @Then("^Verfy User Navigate to Mypayment page and invoice Summary is present$")
	    public void verfy_user_navigate_to_mypayment_page_and_invoice_summary_is_present() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".MyPayment.InvoiceSummaryTitle",this.ormData));
	    }
	    
	    @And("^User click on proceed to payment Button$")
	    public void user_click_on_proceed_to_payment_button() throws Throwable {
	    	catsAction.scrollDownByOffset("300");
	    	catsAction.waitUntilElementDisplay(website+".MyPayment.ProceedToPaymentButton", "30");
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".MyPayment.ProceedToPaymentButton",this.ormData));
	    }
	  
	   @Then("^verify user navigate to login page$")
	    public void verify_user_navigate_to_login_page() throws Throwable {
	    	catsAction.waitUntilElementDisplay(website+".CheckIn.EmailId", "30");
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".CheckIn.EmailId",this.ormData));
	    }
	    @And("^verify user Navigate to payment page$")
	    public void verify_user_navigate_to_payment_page() throws Throwable {
	    	Thread.sleep(3000);
	    	getDriver().getCurrentUrl().contains("mypayment");
	    	
	    }
	    
	    @And("^click on Confirm order button$")
	    public void click_on_confirm_order_button() throws Throwable {
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".MyPayment.ConfirmOrderButton",this.ormData));
	    	  
	    }
	    
	    @And("^User select feature \"([^\"]*)\" and click on book now button.$")
	    public void user_select_feature_something_and_click_on_book_now_button(String featureName) throws Throwable {
	    	Thread.sleep(6000);
	    	System.out.println("feature to search" +featureName);
	    	clickBookNowButtonOfGivenFeature(featureName);
	 	    dateSelection();
	    }
	    
	    @Then("^Verify chose your ticket Overlay should open$")
	    public void verify_chose_your_ticket_overlay_should_open() throws Throwable {
	    	 Thread.sleep(2000);
	    	 catsAction.waitUntilElementDisplay(website + ".BookNowOverlay.SelectDateOverlay", "30");
			 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".BookNowOverlay.SelectDateOverlay",this.ormData));
	    }

	    @And("^Verify recommended tab should come$")
	    public void verify_recommended_tab_should_come() throws Throwable {
	    	 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Booking.RecommendedTab",this.ormData));
	    }

	    @And("^User should verify recommended list and select recommended list option$")
	    public void user_should_verify_recommended_list_and_select_recommended_list_option() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Booking.RecommendedItemList",this.ormData));
	    	Thread.sleep(3000);
	    	catsAction.click(CustomRules.locatorPresentInSite(website + ".Booking.RecommendedItemSelectButton",this.ormData));
	    	
	    }
	 
	    @When("^User click on add to cart icon on home page$")
	    public void user_click_on_add_to_cart_icon_on_home_page() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website + ".HomePage.cartIcon",this.ormData));
	    	
	    }

	    @Then("^verify user navigate to my Payment page and click on Buy Experiences button$")
	    public void verify_user_navigate_to_my_payment_page_and_click_on_buy_experiences_button() throws Throwable {
	    	
	    	catsAction.waitUntilElementDisplay(website + ".MyPayment.NoTicketInCartLabel", "30");
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".MyPayment.NoTicketInCartLabel", this.ormData));
	    	catsAction.click(CustomRules.locatorPresentInSite(website + ".MyPayment.BuyExperience",this.ormData));
	    }
	  
	    @And("^Verify and select \"([^\"]*)\" Voucher tab is present on Booking page$")
	    public void verify_and_select_something_voucher_tab_is_present_on_booking_page(String tabOption) throws Throwable {
	    	verifyAndSelectTabOption(tabOption);
	    }
	    
	    @When("^User select item in gift vouher list option$")
	    public void user_select_item_in_gift_vouher_list_option() throws Throwable {
	    	
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Booking.GiftVoucherItemSelectButton", this.ormData));
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Booking.GiftVoucherItemSelectButton",this.ormData));
	    	
	    }
	    
	    @When("^User select item in MOTORSPORTS list option$")
	    public void user_select_item_in_motorsports_list_option() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Booking.MotorSportItemSelectButton", this.ormData));
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".Booking.MotorSportItemSelectButton",this.ormData));
	    	
	    }
	    
	    @Then("^User should select available date and time Slot$")
	    public void user_should_select_available_date_and_time_slot() throws Throwable {
	    	Thread.sleep(6000);
	 	    dateSelection();
	    }
	    
	@And("^User click on bookNow button$")
	public void user_click_on_booknow_button() throws Throwable {
		Thread.sleep(2000);
		catsAction.waitUntilElementDisplay(website + ".PDPbook.BookNow", "30");

		catsAction.click(website + ".PDPbook.BookNow");

		dateSelection();
		System.out.println("check---------" + dateSelection());

	}

	@Then("^User select quantity$")
	public void user_select_quantity() throws Throwable {
		Thread.sleep(2000);
		catsAction.click(website + ".BookNowOverlay.Quantity");

	}

} 

