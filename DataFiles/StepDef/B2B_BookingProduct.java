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


public class B2B_BookingProduct extends CATSCucumberConfig {
    
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

	  @Then("^B2B User check the payment confirmation page for submit order request$")
	    public void b2b_user_check_the_payment_confirmation_page_for_submit_order_request() throws Throwable {
		 catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Payment.orderSubmitted",this.ormData),"30");
		 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.orderSubmitted",this.ormData));
		 catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Payment.ticketBookingidFororderSubmitted",this.ormData));
		 bookingID = getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Payment.BookingID",this.ormData)).getXpath()).getText();
	     System.out.println("bookingID: "+bookingID);
	  }
	  
	  @And("^B2B User verify new operator or agent is created$")
	    public void b2b_user_verify_new_operator_or_agent_is_created() throws Throwable {
			Thread.sleep(5000);
			List<WebElement> userDataList = getDriver().findElementsByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".UserProfile.emailList",this.ormData)).getXpath()); 
		
			System.out.println("NumberOfUsers: "+userDataList.size());
			
			for(int i=0; i<userDataList.size();i++)
			{
			    System.out.println("list:"+userDataList.get(i).getText());
				if(userDataList.get(i).getText().equalsIgnoreCase(randomUser))
				{
					System.out.println("userdata email: "+userDataList.get(i).getText());
					break;
				}
				
			}
		}
	  
	  @Then("^B2B User checks the status of pending order$")
	    public void b2b_user_checks_the_status_of_pending_order() throws Throwable {
		  Thread.sleep(3000);
	//	  bookingID="FEX.WKS3.200203.SAL19";
		  System.out.println("in booking id.................");
		 System.out.println("bookingID: "+bookingID);
		 catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.bookingIDSearch",this.ormData));
		 catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.bookingIDSearch",this.ormData),bookingID);
		 catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.SearchFilter",this.ormData));
		 Thread.sleep(5000);
//		 catsAction.clickJS(CustomRules.locatorPresentInSite(website+".UserProfile.CompanyRefNo");
		 catsAction.clickJS(CustomRules.locatorPresentInSite(website+".UserProfile.Orderdropdown",this.ormData));
		 Thread.sleep(5000);
	//	 catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".UserProfile.ViewOrder", "60");
		 catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.ViewOrder",this.ormData));
		 Thread.sleep(4000);
			
	  }
	  
	  @And("^B2B User decline the order$")
	    public void b2b_user_decline_the_order() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Decline.DeclineOption",this.ormData));
		  catsAction.enter(CustomRules.locatorPresentInSite(website+".Decline.DeclineReason",this.ormData), "$MiralGlobal.ReasonToDecline.<<site>>");
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Decline.DeclineOrder",this.ormData));
		  Thread.sleep(3000);
	  }
	  
	  @Then("^B2B User check the order status in declined order$")
	    public void b2b_user_check_the_order_status_in_declined_order() throws Throwable {
		  catsAction.click(CustomRules.locatorPresentInSite(website+".Decline.DeclinedOrder",this.ormData));
		  Thread.sleep(5000);
			List<WebElement> userDataList = getDriver().findElementsByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".UserProfile.bookingID_list",this.ormData)).getXpath()); 
		
			System.out.println("NumberOfUsers: "+userDataList.size());
			
			for(int i=0; i<userDataList.size();i++)
			{
			    System.out.println("list:"+userDataList.get(i).getText());
				if(userDataList.get(i).getText().equalsIgnoreCase(bookingID))
				{
					System.out.println("userdata email: "+userDataList.get(i).getText());
					break;
				}
				
			}
	  }
	  
	  
}
