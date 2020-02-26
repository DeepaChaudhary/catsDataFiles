package cats.selenium.bdd.stepdef;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
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

public class PPAD_step_Def extends CATSCucumberConfig{

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

	@Then("^user click on zone$")
	public void user_click_on_zone() throws Throwable 
	{
		catsAction.hoverNClickSubItem(CustomRules.locatorPresentInSite(website+".Home.Explore",this.ormData),"Zones");
		catsAction.scrollDownByOffset("600");
	}

	@Then("^user click on Read more links$")
	public void user_click_on_read_more_links() throws Throwable {

		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.ReadMoreLink",this.ormData));

	}

	@And("^Verify user able to see Palace page$")
	public void verify_user_able_to_see_palace_page() throws Throwable {

		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.ComPonent",this.ormData));
	}

	@Then("^click on Ticket link on home page$")
	public void click_on_ticket_link_on_home_page() throws Throwable 
	{
		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.TicketBTN",this.ormData));

	}
	@Then("^verify all buyTickets links are present and click on Buy ticket$")
	public void verify_all_buytickets_links_are_present_and_click_on_buy_ticket() throws Throwable {

		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Ticket.BuyTicketsAll",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.BuyTicket",this.ormData));
		catsAction.scrollPageUp();
	}

	int counterr=1;
	@And("^Add multiple Product$")
    public void add_multiple_product() throws Throwable {
        Thread.sleep(2000);
        //catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.SelectTicket",this.ormData));
        List<WebElement> PList= getDriver().findElements(By.xpath("//button[@type='button']"));
        System.out.println("Total no of links Available: "+PList.size());
        for (int i = 1; i <=PList.size(); i++) 
        {
            catsAction.click("(//button[@type='button'])["+i+"]");
            Thread.sleep(2000);
            SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd");
            dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
            String d=dateTimeInGMT.format(new Date());
            System.out.println(d);
            int date= Integer.parseInt(d);
            int futureDate=date+6;
            int dateSearchCounter=10;

 

            //Integer.parseInt(catsVariable.getTDMValue("$MiralGlobal.dateSearchCounter.<<site>>"));
            while(counterr<=dateSearchCounter){
                if(date>28){
                    date=1;

 

                    catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.nextMonthClickOnCalender",this.ormData));
                    catsAction.click("//*[@aria-label='day-"+futureDate+"']");
                }
                else{
                    catsAction.click("//*[@aria-label='day-"+futureDate+"']");
                }

 

                Thread.sleep(5000);
                System.out.println("date is vailable or not*******"+
                        catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.timeSlotsAvialable",this.ormData)) + "********"
                        + "And Date is ::: "+"//*[@aria-label='day-"+futureDate+"']");
                if(catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.timeSlotsAvialable",this.ormData))){
                    catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.selectTimeSlot",this.ormData));
                    catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.checkdropdownValue",this.ormData));

 

                    catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.checkdropdownValue",this.ormData));
                    Thread.sleep(6000);
                    catsAction.scrollIntoView(CustomRules.locatorPresentInSite(website+".Product_Booking.checkBox",this.ormData));
                    Thread.sleep(2000);
                    catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.addToCartOnOverlay",this.ormData));
                    Thread.sleep(2000);
                    if(catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.checkBox",this.ormData)));{
                        Thread.sleep(1000);
                        catsAction.clickJS(CustomRules.locatorPresentInSite(website+".Product_Booking.checkBox",this.ormData));
                        Thread.sleep(1000);
                        if(catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.addToCartOnOverlay2",this.ormData)));
                        {
        
                        try {
                            catsAction.click(CustomRules.locatorPresentInSite(website+".Product_Booking.addToCartOnOverlay2",this.ormData));
                        } catch (ElementNotInteractableException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    }     }
                else if(catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Product_Booking.noSlotsAvailable",this.ormData))){
                    System.out.println("No Slots Avialable");
                    Thread.sleep(2000);
                }

 

                {

 

                    date++;
                    counterr++;
                }}}

 

    }

	}
