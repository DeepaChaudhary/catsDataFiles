Feature: User Profile and Registration
  - Verify user details already present on profile page
  - Verify adding user information in the profile page
  - Verify editing user information from profile page
  - Verify user is able to discard the information changes already filled
  - Verify adding family member details in the profile
  - Verify user is able to add preferences

  Background: 
     Given user is on Home Page

  Scenario: Register new user update its details and verify it
    When YWW User clicks on REGISTER NOW link
    Then User redirects to registration page
    When User enter below valid details for registration
    And YWW User click on submit button for registration
    When user clicks on My Profile button present under Profile icon
    When User update all the details present in profile info page with valid details
    When User clicks on MyPreferences tab
    When User selects value "Family with kids" in travel selection box
    And User select the Intrested checkBox
    And User clicks on Save changes button on PreferencePage
    When user logs out
    Then user logs in again
    When user clicks on My Profile button present under Profile icon
    Then validate information
		Then Close browser
#
  #Scenario: Select Drop down Demo
    #When YWW User clicks on REGISTER NOW link
    #Then User redirects to registration page
    #Then Select Dropdown Value

