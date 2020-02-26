package cats.selenium.bdd.stepdef;

import com.ibm.icu.impl.Assert;
import java.io.IOException;
import com.sapient.qa.cats.core.framework.CATSCucumberConfig;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class Registration_StepDef extends CATSCucumberConfig {

	String randomUser;
	public String website;
	public ExcelReader ormData;
	public String language;

	@Before()
	public void launch(Scenario scenario) throws IOException {
		String ormFile=System.getProperty("user.dir")+"/../DataFiles/ORM.xlsx";
		this.ormData=new ExcelReader(ormFile);
		scenario.write("I am in before launch");
		website= catsVariable.getSuite("site");
		language=catsVariable.getSuite("lang").toLowerCase();
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

	@When("^user clicks on My Profile button present under Profile icon$")
	public void user_clicks_on_my_profile_button_present_under_profile_icon() throws Throwable {
		Thread.sleep(12000);
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".UserProfile.Profile",this.ormData));
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".UserProfile.MyProfile",this.ormData));
		catsAction.pageLoadWait();
		Thread.sleep(3000);
	}

	@When("^user logs out$")
	public void user_logs_out() throws Throwable {
		catsAction.scrollUpByOffset("800");
		Thread.sleep(5000);
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".UserProfile.Profile",this.ormData));
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".UserProfile.logout",this.ormData));
	}

	@Then("^by default tab selected is MyProfile$")
	public void by_default_tab_selected_is_myprofile() throws Throwable {
		catsAction.getAttributeValue("class", "{{val}}", CustomRules.locatorPresentInSite(website + ".UserProfile.ProfileTab",this.ormData));
		catsAction.veifyTextContainsIgnoreCase("{{val}}", "is-active");

	}

	@When("^User enter below valid details for registration$")
	public void user_enter_below_valid_details_for_registration() throws Throwable {
		catsAction.getRandomMailId("{{myEmailID}}", "");
		randomUser = "{{myEmailID}}";
		System.out.println("email id*******************************************" + "{{myEmailID}}");
		System.out.println("randomUser++++++++++++++++++++++++++++++++++++++++++" + randomUser);
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".Registration.EmailId",this.ormData), "{{myEmailID}}");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".Registration.Password",this.ormData), "$MiralGlobal.SignUpPassword.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".Registration.RenterPassword",this.ormData), "$MiralGlobal.SignUpRenterPassword.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".Registration.FirstName",this.ormData), "$MiralGlobal.SignUpFirstName.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".Registration.SurName",this.ormData), "$MiralGlobal.SignUpSurName.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".Registration.PhoneNo",this.ormData), "$MiralGlobal.SignUpPhoneNo.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.Nationality",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.India",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.Country",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.NIndia",this.ormData));
	}

	
	@When("^User update all the details present in profile info page with valid details$")
	public void user_update_all_the_details_present_in_profile_info_page_with_valid_details() throws Throwable {
		System.out.println("website value" + website);
		switch(website){

		case("YI"):
			System.out.println("filling data for YasIsland");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.FirstName",this.ormData), "$MiralGlobal.UpdateUserprofileFirstName.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.SurName",this.ormData), "$MiralGlobal.UpdateUserprofileSurName.<<site>>");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.PhoneNo",this.ormData), "$MiralGlobal.UpdateUserprofilePhoneNo.<<site>>");
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".UserProfile.DOB",this.ormData));
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.DOB",this.ormData), "$MiralGlobal.UpdateUserprofileDate.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.NationalityUpdated",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.NindiaUpdated",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.Country",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.India",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".UserProfile.Save",this.ormData));
		Thread.sleep(10000);
		break;

		default:
			System.out.println("filling data for tenant");
			catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.FirstName",this.ormData), "$MiralGlobal.UpdateUserprofileFirstName.<<site>>");
			catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.SurName",this.ormData), "$MiralGlobal.UpdateUserprofileSurName.<<site>>");
			catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.PhoneNo",this.ormData), "$MiralGlobal.UpdateUserprofilePhoneNo.<<site>>");
			// Thread.sleep(2000);
			catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".UserProfile.DOB",this.ormData));
			catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.Date",this.ormData), "$MiralGlobal.UpdateUserprofileDate.<<site>>");
			catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.Month",this.ormData), "$MiralGlobal.UpdateUserprofileMonth.<<site>>");
			catsAction.enter(CustomRules.locatorPresentInSite(website + ".UserProfile.Year",this.ormData), "$MiralGlobal.UpdateUserprofileYear.<<site>>");
			catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.NationalityUpdated",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.NindiaUpdated",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.Country",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website + ".Registration.India",this.ormData));
			catsAction.click(CustomRules.locatorPresentInSite(website + ".UserProfile.Save",this.ormData));
			Thread.sleep(10000);
			break;
		}

	}

	@Then("^user logs in again$")
	public void user_logs_in_again() throws Throwable {

		Thread.sleep(5000);
		catsAction.click(CustomRules.locatorPresentInSite(website + ".Login.Profile",this.ormData));
		catsAction.pageLoadWait();

		catsAction.enter(CustomRules.locatorPresentInSite(website + ".CheckIn.EmailId",this.ormData), "{{myEmailID}}");
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".CheckIn.EmailId",this.ormData), randomUser);
		catsAction.enter(CustomRules.locatorPresentInSite(website + ".CheckIn.Password",this.ormData), "$MiralGlobal.SignUpPassword.<<site>>");
		catsAction.click(CustomRules.locatorPresentInSite(website + ".CheckIn.Submit",this.ormData));
		catsAction.pageLoadWait();
	}

	@When("^User clicks on MyPreferences tab$")
	public void user_clicks_on_mypreferences_tab() throws Throwable {
		// catsAction.click(website+".ProfilePagePreference.PreferenceLink");
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PreferenceLink",this.ormData));
	}

	@Then("^verify user is successfully switched to My preferences tab$")
	public void verify_user_is_successfully_switched_to_my_preferences_tab() throws Throwable {
		catsAction.getAttributeValue("class", "{{val}}", CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PreferenceLinkTab",this.ormData));

		if (catsAction.veifyTextContainsIgnoreCase("{{val}}", "is-active")) {
			System.out.println("My preferences tab is selected");
		} else {
			Assert.fail("My preferences tab is not selected");
		}

	}

	@When("^User selects value \"([^\"]*)\" in travel selection box$")
	public void user_selects_value_something_in_travel_selection_box(String valueToSelect) throws Throwable {

		Thread.sleep(2000);
		catsAction.click(CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PreferenceTravellingSelectionField",this.ormData));
		catsAction.click(CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PreferenceTravellingSelectionFieldOptions",this.ormData));

		/*
		 * catsAction.clickNClickPartialLink(website+
		 * ".ProfilePagePreference.PreferenceTravellingSelectionField", valueToSelect);
		 * catsAction.clickNClickPartialLink(website+
		 * ".ProfilePagePreference.PreferenceTravellingSelectionField",
		 * "with_family_kids"); catsAction.clickOnIndexSubElement(website+
		 * ".ProfilePagePreference.PreferenceTravellingSelectionField", "3");
		 */

	}

	@And("^User select the Intrested checkBox$")
	public void user_select_the_intrested_checkbox() throws Throwable {
		Thread.sleep(3000);
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PreferenceInterestedCheckBoxList",this.ormData));

		// catsAction.clickMultipleTimes(website+".ProfilePagePreference.PreferenceInterestedCheckBoxList",
		// "2");
	}

	@And("^User clicks on Save changes button on PreferencePage$")
	public void user_clicks_on_save_changes_button_on_preferencepage() throws Throwable {
		Thread.sleep(2000);
		catsAction.waitUntilElementDisplay(CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PreferencePageSaveButton",this.ormData), "10");
		catsAction.clickJS(CustomRules.locatorPresentInSite(website + ".ProfilePagePreference.PreferencePageSaveButton",this.ormData));
		Thread.sleep(10000);
	}

	/*
	 * @Then("^validate information$") public void validate_information() throws
	 * Throwable { catsAction.verifyText(website+".UserProfile.FirstName", "Div");
	 * catsAction.verifyText(website+".UserProfile.SurName", "Gupta");
	 * catsAction.verifyText(website+".UserProfile.PhoneNo", "9170358356567");
	 * catsAction.verifyText(website+".UserProfile.Date", "20");
	 * catsAction.verifyText(website+".UserProfile.Month", "11");
	 * catsAction.verifyText(website+".UserProfile.Year", "1998");
	 * catsAction.verifyText(website+".UserProfile.India", "India");
	 * catsAction.verifyText(website+".UserProfile.NIndia", "India"); }
	 * 
	 */
	@Then("^validate information$")
	public void validate_information() throws Throwable {

		switch(website){

		case("YI"):
			System.out.println("Validation YasIsland");
		catsAction.getAttributeValue("value", "{{firstnameValue}}", CustomRules.locatorPresentInSite(website + ".UserProfile.FirstName",this.ormData));
		catsAction.verifyVariableValue("{{firstnameValue}}", "$MiralGlobal.UpdateUserprofileFirstName.<<site>>");

		catsAction.getAttributeValue("value", "{{lastNameValue}}", CustomRules.locatorPresentInSite(website+".UserProfile.SurName",this.ormData));
		catsAction.verifyVariableValue("{{lastNameValue}}", "$MiralGlobal.UpdateUserprofileSurName.<<site>>");

		catsAction.getAttributeValue("value", "{{phoneNumber}}", CustomRules.locatorPresentInSite(website+".UserProfile.PhoneNo",this.ormData));
		catsAction.verifyVariableValue("{{phoneNumber}}", "$MiralGlobal.UpdateUserprofilePhoneNo.<<site>>");

		catsAction.getAttributeValue("value", "{{Date}}", CustomRules.locatorPresentInSite(website+".UserProfile.DOB",this.ormData));
		catsAction.verifyVariableValue("{{Date}}", "$MiralGlobal.UpdateUserprofileDate.<<site>>");

		switch(language){
			case("ar"):
			catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.India",this.ormData), "الهند");
			catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.NIndia",this.ormData), "الهند");
			break;
			default:
				catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.India",this.ormData), "India");
				catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.NIndia",this.ormData), "India");
				break;
		}
		
		break;

		default:
			System.out.println("Validation Other Tenanat");
			catsAction.getAttributeValue("value", "{{firstnameValue}}", CustomRules.locatorPresentInSite(website + ".UserProfile.FirstName",this.ormData));
			catsAction.verifyVariableValue("{{firstnameValue}}", "$MiralGlobal.UpdateUserprofileFirstName.<<site>>");

			catsAction.getAttributeValue("value", "{{lastNameValue}}", CustomRules.locatorPresentInSite(website+".UserProfile.SurName",this.ormData));
			catsAction.verifyVariableValue("{{lastNameValue}}", "$MiralGlobal.UpdateUserprofileSurName.<<site>>");

			catsAction.getAttributeValue("value", "{{phoneNumber}}", CustomRules.locatorPresentInSite(website+".UserProfile.PhoneNo",this.ormData));
			catsAction.verifyVariableValue("{{phoneNumber}}", "$MiralGlobal.UpdateUserprofilePhoneNo.<<site>>");

			catsAction.getAttributeValue("value", "{{Date}}", CustomRules.locatorPresentInSite(website+".UserProfile.Date",this.ormData));
			catsAction.verifyVariableValue("{{Date}}", "$MiralGlobal.UpdateUserprofileDate.<<site>>");

			catsAction.getAttributeValue("value", "{{month}}", CustomRules.locatorPresentInSite(website+".UserProfile.Month",this.ormData));
			catsAction.verifyVariableValue("{{month}}", "$MiralGlobal.UpdateUserprofileMonth.<<site>>");

			catsAction.getAttributeValue("value", "{{year}}", CustomRules.locatorPresentInSite(website+".UserProfile.Year",this.ormData));
			catsAction.verifyVariableValue("{{year}}", "$MiralGlobal.UpdateUserprofileYear.<<site>>");

			switch(language){
			case("ar"):
			catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.India",this.ormData), "الهند");
			catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.NIndia",this.ormData), "الهند");
			break;
			default:
				catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.India",this.ormData), "India");
				catsAction.verifyText(CustomRules.locatorPresentInSite(website+".UserProfile.NIndia",this.ormData), "India");
				break;
		}
			break;
		}	


	}

	@And("^User clicks on save changes button for My profile$")
	public void user_clicks_on_save_changes_button_for_my_profile() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website + ".UserProfile.Save",this.ormData));
	}

	@And("^user click on signIn button$")
	public void user_click_on_signin_button() throws Throwable {
		catsAction.click(CustomRules.locatorPresentInSite(website + ".CheckIn.Submit",this.ormData));
		Thread.sleep(8000);
		catsAction.pageLoadWait();
	}

	@Then("^User redirects to registration page$")
	public void user_redirects_to_registration_page() throws Throwable {
		Thread.sleep(3000);
		catsAction.verifyElementPresent(CustomRules.locatorPresentInSite(website + ".Registration.EmailId",this.ormData));
	}


}
