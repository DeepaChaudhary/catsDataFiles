package cats.selenium.bdd.stepdef;
import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class WishList_verify_StepDef extends  CATSCucumberConfig{
	
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
	   **********************Start writing your Step definition below this section***************************************************************
	   *****************************************************************************************************************************************
	   */	  
	 
       @Given("^User, click on wishlist icon$")
	    public void user_click_on_wishlist_icon() throws Throwable {
    	   catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Wishlist.WishListIcon",this.ormData), "30");
    	   catsAction.click(CustomRules.locatorPresentInSite(website+".Wishlist.WishListIcon",this.ormData));
    	   
	    }

	    @And("^User verify wishlist is empty$")
	    public void user_verify_wishlist_is_empty() throws Throwable 
	    {
	    	
	    	catsAction.verifyElementNotPresent(CustomRules.locatorPresentInSite(website+".Wishlist.WishlistItem",this.ormData));
	    	//System.out.println("wishlist empty message");
	    	
	    }
	    

	    @Then("^User click on hotels navigation link$")
	    public void user_click_on_hotels() throws Throwable {
	    Thread.sleep(5000);
	    catsAction.click(CustomRules.locatorPresentInSite(website+".Home.HoteNavigationLink",this.ormData));
	    catsAction.pageLoadWait();
	
	    }

	    @And("^User click on hotels wishlist icon$")
	    public void user_click_on_hotels_wishlist_icon() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".HotelPage.WishListIconOnHotel",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".HotelPage.CloseLoginPopUp",this.ormData));
		catsAction.pageLoadWait();	 
	    }

	    @And("^User verify wishlist on wishlist page$")
	    public void user_verify_wishlist_on_wishlist_page() throws Throwable {
	    catsAction.click(CustomRules.locatorPresentInSite(website+".Home.WishlistFilledIconHeader",this.ormData));
	    catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Wishlist.WishlistItem",this.ormData), "30");
	    catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Wishlist.WishlistItem",this.ormData));
 
	    }
	  }
