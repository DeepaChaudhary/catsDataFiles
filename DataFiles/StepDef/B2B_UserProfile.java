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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class B2B_UserProfile extends CATSCucumberConfig {
    
	String randomUser;
	public String site;
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
	  
	    @Given("^user is on B2B Home Page$")
	    public void user_is_on_b2b_home_page() throws Throwable {
	     catsAction.launchSite("$MiralGlobal.LaunchSite<<env>>.<<site>>");
	   //  catsAction.click(website+".Language.CookieAccept");
	   
	  }
	    
	    @When("^B2B User close the notification on Homepage$")
	    public void b2b_user_close_the_notification_on_homepage() throws Throwable {
	    	Thread.sleep(2000);
	    	catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website+".UserProfile.notificationClose",this.ormData), "20");
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".UserProfile.notificationClose",this.ormData));
	    }
	    
	    @Then("^B2B User click on SignIn CTA$")
	    public void b2b_user_click_on_signin_cta() throws Throwable {
	    	Thread.sleep(4000);
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.SignIn",this.ormData));
	    }
	    
	    @Then("^B2B User enter below valid details for login$")
	    public void b2b_user_enter_below_valid_details_for_login() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.email",this.ormData), "$MiralGlobal.emailForSignWithPartner.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.Password",this.ormData), "$MiralGlobal.passwordForSignWithPartner.<<site>>");
	  
	    }
	    
	    @When("^B2B User enter below valid details for login with Agent$")
	    public void b2b_user_enter_below_valid_details_for_login_with_agent() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.email",this.ormData), "$MiralGlobal.emailForSignWithAgentwithPurchaseRights.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.Password",this.ormData), "$MiralGlobal.passwordForSignWithAgentwithPurchaseRights.<<site>>");
	  
	    }
	    
	    @When("^B2B User enter below valid details for login with Agent without Purchase Rights$")
	    public void b2b_user_enter_below_valid_details_for_login_with_agent_without_purchase_rights() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.email",this.ormData), "$MiralGlobal.emailForSignWithAgentwithoutPurchaseRights.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.Password",this.ormData), "$MiralGlobal.passwordForSignWithAgentwithoutPurchaseRights.<<site>>");
	  
	    }
	    
	    @When("^B2B User enter below valid details for login with Operator$")
	    public void b2b_user_enter_below_valid_details_for_login_with_operator() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.email",this.ormData), "$MiralGlobal.emailForSignWithOperator.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.Password",this.ormData), "$MiralGlobal.passwordForSignWithOperator.<<site>>");
	  
	    }
	    
	    @When("^B2B User enter below valid details for login for password Change$")
	    public void b2b_user_enter_below_valid_details_for_login_for_password_change() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.email",this.ormData), "$MiralGlobal.emailForSignWithPartnerForPasswordChange.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.Password",this.ormData), "$MiralGlobal.passwordForSignWithPartnerForPasswordChange.<<site>>");
	  
	    }
	    
	    @And("^B2B User click on submit button for login$")
	    public void b2b_user_click_on_submit_button_for_login() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Submit",this.ormData));
	    	Thread.sleep(2000);
	    }
	    
	    @Then("^B2B User logout$")
	    public void b2b_user_logout() throws Throwable {
	    	  Thread.sleep(3000);
			  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Profile",this.ormData));
			  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.logout",this.ormData));
			  Thread.sleep(3000);
	    }
	    
	    @When("^B2B user clicks on My Profile button present under Profile icon$")
	    public void b2b_user_clicks_on_my_profile_button_present_under_profile_icon() throws Throwable {
		  Thread.sleep(3000);
		  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Profile",this.ormData));
		  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.MyProfile",this.ormData));
		  catsAction.pageLoadWait();
		  Thread.sleep(3000);
	    }
	    
	    @When("^B2B user clicks on MyOrders button present under Profile icon$")
	    public void b2b_user_clicks_on_myorders_button_present_under_profile_icon() throws Throwable {
	    	  Thread.sleep(3000);
			  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Profile",this.ormData));
			  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.MyOrders",this.ormData));
			  Thread.sleep(3000);
	    }
	    
	    @When("^B2B User update all the details present in profile info page with valid details$")
	    public void b2b_user_update_all_the_details_present_in_profile_info_page_with_valid_details() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.edit",this.ormData));
	    	Thread.sleep(2000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.FirstName",this.ormData), "$MiralGlobal.UpdateB2BUserprofileFirstName.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.SurName",this.ormData), "$MiralGlobal.UpdateB2BUserprofileSurName.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.PhoneNo",this.ormData), "$MiralGlobal.UpdateB2BUserprofilePhoneNo.<<site>>");
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Country",this.ormData));
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.CountryDropdown",this.ormData));
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Save",this.ormData));
	    	Thread.sleep(10000);
	    }
	    
	    @When("^B2B User reset all the details present in profile info page with valid details$")
	    public void b2b_user_reset_all_the_details_present_in_profile_info_page_with_valid_details() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.edit",this.ormData));
	    	Thread.sleep(2000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.FirstName",this.ormData), "$MiralGlobal.ResetB2BUserprofileFirstName.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.SurName",this.ormData), "$MiralGlobal.ResetB2BUserprofileSurName.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.PhoneNo",this.ormData), "$MiralGlobal.ResetB2BUserprofilePhoneNo.<<site>>");
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Country",this.ormData));
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.CountryDropdownReset",this.ormData));
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Save",this.ormData));
	    	Thread.sleep(10000);
	    }
	    
	    @When("^B2B User clicks on Change Password tab$")
	    public void b2b_user_clicks_on_change_password_tab() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.clickJS(CustomRules.locatorPresentInSite(website+".UserProfile.ChangePasswordLink",this.ormData));
	    	Thread.sleep(3000);
	    	catsAction.switchWindowByID("1");
	    }
	    
	    @When("^B2B User enters new password$")
	    public void b2b_user_enters_new_password() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.OldPassword",this.ormData), "$MiralGlobal.OldPassword.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.NewPassword",this.ormData), "$MiralGlobal.NewPassword.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.ConfirmNewPassword",this.ormData), "$MiralGlobal.NewPassword.<<site>>");
	     
	    }
	    
	    @When("^B2B User reset password$")
	    public void b2b_user_reset_password() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.OldPassword",this.ormData), "$MiralGlobal.NewPassword.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.NewPassword",this.ormData), "$MiralGlobal.OldPassword.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.ConfirmNewPassword",this.ormData), "$MiralGlobal.OldPassword.<<site>>");
	     
	    }
	    
	    @And("^B2B User clicks on submit button to save new password$")
	    public void b2b_user_clicks_on_submit_button_to_save_new_password() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.SaveNewPassword",this.ormData));
	    	 Thread.sleep(3000);
		    catsAction.switchWindowByID("0");
		    catsAction.closeUnusedWindows();
	    }
	    
	    @When("^B2B user logs out$")
	    public void b2b_user_logs_out() throws Throwable {
	    	Thread.sleep(3000);
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Profile",this.ormData));
	        catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.logout",this.ormData));
	    }
	    
	    @Then("^B2B user logs in again$")
	    public void b2b_user_logs_in_again() throws Throwable {
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.SignIn",this.ormData));
	    	Thread.sleep(3000);
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.email",this.ormData), "$MiralGlobal.emailForSignWithPartnerForPasswordChange.<<site>>");
	    	catsAction.enter(CustomRules.locatorPresentInSite(website+".UserProfile.Password",this.ormData), "$MiralGlobal.NewPasswordForSignWithPartner.<<site>>");
	    	Thread.sleep(2000);
	    	catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Submit",this.ormData));
	    	
	    }
	    
	    @Then("^B2B user validate information$")
	    public void b2b_user_validate_information() throws Throwable {
			
	    	Thread.sleep(3000);
                   System.out.println("Validation B2B");
            catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.UpdatedFirstName",this.ormData), "$MiralGlobal.UpdateB2BUserprofileFirstName.<<site>>");
            catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.UpdatedSurnameName",this.ormData), "$MiralGlobal.UpdateB2BUserprofileSurName.<<site>>");
            catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.UpdatedPhone",this.ormData), "$MiralGlobal.UpdateB2BUserprofilePhoneNo.<<site>>");
            catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.UpdatedCountry",this.ormData), "$MiralGlobal.UpdateB2BUserprofileCountry.<<site>>");
                   
//            catsAction.getAttributeValue("value", "{{firstnameValue}}", website + ".UserProfile.UpdatedFirstName");
//            catsAction.verifyVariableValue("{{firstnameValue}}", "$MiralGlobal.UpdateB2BUserprofileFirstName.<<site>>");

//            catsAction.getAttributeValue("value", "{{lastNameValue}}", website+".UserProfile.UpdatedSurnameName");
//            catsAction.verifyVariableValue("{{lastNameValue}}", "$MiralGlobal.UpdateB2BUserprofileSurName.<<site>>");
//
//            catsAction.getAttributeValue("value", "{{phoneNumber}}", website+".UserProfile.UpdatedPhone");
//            catsAction.verifyVariableValue("{{phoneNumber}}", "$MiralGlobal.UpdateB2BUserprofilePhoneNo.<<site>>");
//            
//            catsAction.verifyText(website+".UserProfile.UpdatedCountry", "Albania");

	    }
	    
	    @Then("^B2B User verify dashboard$")
	    public void b2b_user_verify_dashboard() throws Throwable {
	    	catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website+".Dasboard.WelcomeMsg",this.ormData));
	    }
	    
	    @When("^B2B user clicks on Manage Account button present under Profile icon$")
	    public void b2b_user_clicks_on_manage_account_button_present_under_profile_icon() throws Throwable {
	    	  Thread.sleep(3000);
			  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.Profile",this.ormData));
			  catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.ManageAccount",this.ormData));
			  Thread.sleep(3000);
	    }
	    
	    @Then("^B2B user click on Add user button$")
	    public void b2b_user_click_on_add_user_button() throws Throwable {
	    	 catsAction.click(CustomRules.locatorPresentInSite(website+".UserProfile.AddUser",this.ormData));
	    }
	    
	    
	    
}
