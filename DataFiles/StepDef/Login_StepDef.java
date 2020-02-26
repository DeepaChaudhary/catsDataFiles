package cats.selenium.bdd.stepdef;

import java.io.File;

import org.openqa.selenium.logging.LogType;

import com.sapient.qa.cats.core.actions.LaunchNewDriver;
import com.sapient.qa.cats.core.framework.*;

import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Login_StepDef extends CATSCucumberConfig {

	public String website;
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

	@Given("^user is on Home Page$")
	public void user_is_on_home_page() throws Throwable {
		catsAction.launchSite("$MiralGlobal.LaunchSite<<env>>.<<site>>");
		catsAction.pageLoadWait();
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".Language.CookieClose",this.ormData),"20");
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".Language.CookieAccept",this.ormData));
	}

	@Given("^user update URL$")
	public void user_update_URL() throws Throwable {
		//catsAction.launchSite("$MiralGlobal.LaunchSite<<env>>.<<site>>");
		Thread.sleep(10000);
		getDriver().get("https://fe-qa5-ux-apps-cd.azurewebsites.net/en/ya");
		catsAction.click(CustomRules.locatorPresentInSite(website+".Language.CookieAccept",this.ormData));
	}

	@Then("^user click on login option$")
	public void user_click_on_login_option() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Payment.loginOnPaymentPage",this.ormData));
	}


	@Then("^user logs in$")
	public void user_logs_in() throws Throwable {
		catsAction.pageLoadWait();
		catsAction.enter(CustomRules.locatorPresentInSite(website+".CheckIn.EmailId",this.ormData), "$MiralGlobal.EmailforLogin.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website+".CheckIn.Password",this.ormData), "$MiralGlobal.PasswordforLogin.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Submit",this.ormData));
		catsAction.pageLoadWait();
	}

	@When("User clicks on MyProfile link")
	public void User_clicks_on_MyProfile_link() {
		catsAction.click(CustomRules.locatorPresentInSite(website+".Login.Profile",this.ormData));
		catsAction.pageLoadWait();
	}

	@When("User enters emailid")
	public void User_enters_emailid() {
		catsAction.enter(CustomRules.locatorPresentInSite(website+".CheckIn.EmailId",this.ormData), "$MiralGlobal.EmailforLogin.<<site>>");
	}

	@When("User enters password")
	public void User_enters_password() {
		catsAction.enter(CustomRules.locatorPresentInSite(website+".CheckIn.Password",this.ormData), "$MiralGlobal.PasswordforLogin.<<site>>");
	}

	@When("user click on submit button")
	public void submit_button() {
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.Submit",this.ormData));
		catsAction.pageLoadWait();
	}

	@And("^User enters valid  emailid for resetpassword$")
	public void user_enters_valid_emailid_for_resetpassword() {
		catsAction.enter(CustomRules.locatorPresentInSite(website+".CheckIn.Resetmailid",this.ormData), "$MiralGlobal.EmailforLogin.<<site>>");
	}

	@And("^user click on continue button$")
	public void user_click_on_continue_button() throws Throwable
	{
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".CheckIn.continueRest",this.ormData), "10");
		catsAction.click(CustomRules.locatorPresentInSite(website+".CheckIn.continueRest",this.ormData));
	}


	@Then("^user able to see reset comform mail send$")
	public void user_able_to_see_reset_comform_mail_send() throws Throwable
	{
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".CheckIn.ConformTxt",this.ormData), "10");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".CheckIn.ConformTxt",this.ormData));

	}


	@When("^User click on forgot Password$")
	public void user_click_on_forgot_password() throws Throwable 
	{
		catsAction.click(CustomRules.locatorPresentInSite(website+".Home.ForgotButton",this.ormData));
	}

	@Then("^User click on logout button$")
	public void user_click_on_logout_button() throws Throwable  {
		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.logout",this.ormData));
		Thread.sleep(8000);
	}

	@Then("^Close browser$")
	public void close_browser(){
		catsAction.quit();
	}

	@Then("^User Hover and Click on my profile$")
	public void user_hover_and_click_on_my_profile() throws Throwable {

		catsAction.hoverNClickSubItem(CustomRules.locatorPresentInSite(website + ".Login.Profile",this.ormData),"MY PROFILE");

	}
	
	
	
}


