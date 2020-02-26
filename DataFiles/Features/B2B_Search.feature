Feature: Search Functionality

  Background: 
    Given user is on B2B Home Page
	Then B2B User click on SignIn CTA
    When B2B User enter below valid details for login
    And B2B User click on submit button for login
    When B2B User close the notification on Homepage
    
  Scenario: Verify clicking on the cross icon will close the search area
    And User clicks on search icon in header
    When User click on cross icon in Search Box
    Then Search overlay will be closed in site
    Then Close browser
   
  Scenario: Verify autocomplete search is working on search results page as well
    And User clicks on search icon in header
    When User clicks anywhere in search bar in site
    Then User should be able to type search "faq" in site
    When User clicks on search icon in header to submit the serach
    When User clears the search input box in Searach result page
    And User clicks on search textbox on search result page
    When User starts writing "fa" in search bar on search result page in WBW site
    Then Verify User should not be able to see auto suggestion search feature on search result page until user has entered 3 characters
    When User has written 3 or more or than 3 characters "faq" in search keyword on searh result page
    Then Verify User is able to see auto suggestion search feature on search result page
	Then Close browser

  Scenario: Verify  If there are no matching results and coveo fails to make any suggestion then below message will be displayed which is CMS editable
    And User clicks on search icon in header
    When User clicks anywhere in search bar in site
    Then User should be able to type search "xyz" in site
    When User clicks on search icon in header to submit the serach
    Then Verify that user is able to see the no search result message If there are no matching results
    Then Close browser
    
   Scenario: Verify autocomplete search is working on search results page as well
    And User clicks on search icon in header
    When User clicks anywhere in search bar in site
    Then User should be able to type search "YAS" in site
    When User clicks on search icon in header to submit the serach
    And Verify search result on serach result page
	Then Close browser