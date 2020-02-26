package cats.selenium.bdd.stepdef;

import com.sapient.qa.cats.core.framework.CATSCucumberConfig;
import java.io.IOException;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.When;

public class HotelBookingWidget_StepDef  extends CATSCucumberConfig{
	 
	public String website;
	public ExcelReader ormData;
	public String rooms;
	public String adult;
	public String children;
	public String dateSelected;
	public String getNoOfRooms;
	public String getNoOfGuest;
	public String compareAdult;
	public  String compareChildren;
	
	
	
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
	 
	  
	  
	  @When("^User select checkin and checkout date$")
	    public void user_select_checkin_and_checkout_date() throws Throwable {
		    catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.DatePicker",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.select",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.select1",this.ormData));
			
		    dateSelected= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Hotel_Booking.storeSelectedDate",this.ormData)).getXpath()).getText();
	        System.out.println("dateSelected: "+dateSelected);
	  }
        
	  
	  @Then("^user select \"([^\"]*)\" rooms$")
	    public void user_select_something_rooms(String strArg1) throws Throwable {
		      rooms= strArg1;
		      System.out.println("rooms: "+rooms);
		      int roomsToAdd = Integer.parseInt(rooms);
	//	      roomsToAdd =roomsToAdd-1;
		      catsAction.click(CustomRules.locatorPresentInSite(website+".Hotel_Booking.GuestSelector",this.ormData));
		      
		      for(int i=1;i<roomsToAdd;i++)
		      {
		      catsAction.click(CustomRules.locatorPresentInSite(website+".Hotel_Booking.AddRoomsCTA",this.ormData));
		      }
		 }
	  
	  @Then("^user add \"([^\"]*)\" adult and \"([^\"]*)\" child$")
	    public void user_add_something_adult_and_something_child(String strArg1, String strArg2) throws Throwable {
		   adult=strArg1;
		   children= strArg2;
		   int NoOfRooms= Integer.parseInt(rooms);
		   int NoOfChildren= Integer.parseInt(children);
		   
		   for(int i=0;i<NoOfRooms;i++)
		   {   
			   int incresePathAdults= 1+(2*i);
			   String NoOfAdultAlreadyPresent= getDriver().findElementByXPath("(//div[@class='c-spinner']//input)["+incresePathAdults+"]").getAttribute("value");
		        System.out.println("NoOfAdultAlreadyPresent: "+NoOfAdultAlreadyPresent);
			   catsAction.clickMultipleTimes("(//div[@class='c-spinner']/button[@class='decerease-btn'])["+incresePathAdults+"]",NoOfAdultAlreadyPresent);
			   catsAction.clickMultipleTimes("(//div[@class='c-spinner']/button[@class='increase-btn'])["+incresePathAdults+"]",adult);
				 
			   int incresePathChildren= 2*(i+1);
			   String NoOfChildrenAlreadyPresent= getDriver().findElementByXPath("(//div[@class='c-spinner']//input)["+incresePathChildren+"]").getAttribute("value");
		        System.out.println("NoOfChildrenAlreadyPresent: "+NoOfChildrenAlreadyPresent);
			   catsAction.clickMultipleTimes("(//div[@class='c-spinner']/button[@class='decerease-btn'])["+incresePathChildren+"]",NoOfChildrenAlreadyPresent);
			   catsAction.clickMultipleTimes("(//div[@class='c-spinner']/button[@class='increase-btn'])["+incresePathChildren+"]",children);
	
		   }
		   
		   for(int i=1;i<=(2*NoOfChildren);i++)
		   {
			   catsAction.click("(//div[@class='form-element select-box']/select)["+i+"]");
			   catsAction.click("(//div[@class='form-element select-box']/select/option[contains(text(),'4')])["+i+"]");
		   }
		   
		   getNoOfGuest= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Hotel_Booking.getNoOfGuest",this.ormData)).getXpath()).getText();
	       System.out.println("getNoOfGuest: "+getNoOfGuest);
	       //split guest
	       String[] arrOfStr = getNoOfGuest.split(",", 2);
	       compareAdult= arrOfStr[0].trim();
	       System.out.println("compareAdult:"+compareAdult);
	       compareChildren= arrOfStr[1].trim();
	       System.out.println("compareChildren:"+compareChildren);
	       
	       getNoOfRooms= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Hotel_Booking.getNoOfRooms",this.ormData)).getXpath()).getText();
	       getNoOfRooms= getNoOfRooms.trim();
	       System.out.println("getNoOfRooms: "+getNoOfRooms);
		   
	  }
	  
	    @And("^user click on Get Packages$")
	    public void user_click_on_get_packages() throws Throwable {
	    	Thread.sleep(2000);
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.GetPackages",this.ormData));
	    }

	    @And("^user is redirected to Package page$")
	    public void user_is_redirected_to_package_page() throws Throwable {
	    	catsAction.navigateBack();
	    	catsAction.pageLoadWait();
	    }
	    
	    @And("^user is redirected to Package page and verify information$")
	    public void user_is_redirected_to_package_page_and_verify_information() throws Throwable {
	    	Thread.sleep(4000);
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Hotel_Booking.brandLogo",this.ormData));
	    	
	    	String[] separateDate = dateSelected.split("-", 2); 
	    	
	    		String startDate= separateDate[0];
	    		System.out.println("startDate"+startDate);
	    		String endDate= separateDate[1];
	    		System.out.println("endDate"+endDate);
	    	
	    	String dateSelectedToVerify= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Hotel_Booking.dateDuration",this.ormData)).getXpath()).getText();
	 	    System.out.println("dateSelectedToVerify: "+dateSelectedToVerify);
	 	    
	 	   String[] separateDateToVerify = dateSelectedToVerify.split("-", 2); 
	    	
   		       String startDateToVerify= separateDateToVerify[0];
   		       System.out.println("startDateToVerify"+startDateToVerify);
   	           String endDateToVerify= separateDateToVerify[1];
   		       System.out.println("endDateToVerify"+endDateToVerify);
	 	    
	    	catsAction.verifyVariableValueContains(startDate, startDateToVerify);
	    	catsAction.verifyVariableValueContains(endDate, endDateToVerify);
//	    	catsAction.verifyTextContainsIgnoreCase(CustomRules.locatorPresentInSite(website+".Hotel_Booking.dateDuration",this.ormData),startDate);
//	    	catsAction.verifyTextContainsIgnoreCase(CustomRules.locatorPresentInSite(website+".Hotel_Booking.dateDuration",this.ormData),endDate);
	    	
	    	catsAction.verifyTextContainsIgnoreCase(CustomRules.locatorPresentInSite(website+".Hotel_Booking.NoOfRooms",this.ormData),getNoOfRooms);
	 	    
//	    	String verifyAdults= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Hotel_Booking.NoOfAdults",this.ormData)).getXpath()).getText();
//	    	verifyAdults= verifyAdults.trim();
//		    System.out.println("verifyAdults: "+verifyAdults);
//		    
//		    String verifyChildren= getDriver().findElementByXPath(catsVariable.getORM(CustomRules.locatorPresentInSite(website+".Hotel_Booking.NoOfChildren",this.ormData)).getXpath()).getText();
//		    verifyChildren= verifyChildren.trim();
//		    System.out.println("verifyChildren: "+verifyChildren);
		    
		    catsAction.verifyTextContainsIgnoreCase(CustomRules.locatorPresentInSite(website+".Hotel_Booking.NoOfAdults",this.ormData), compareAdult);
		    catsAction.verifyTextContainsIgnoreCase(CustomRules.locatorPresentInSite(website+".Hotel_Booking.NoOfChildren",this.ormData), compareChildren);
	    	
	    }

	}

