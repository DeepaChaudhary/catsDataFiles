Feature: Search Functionality

 # Background: 
   # Given user is on Home Page
   # Given Closing cookie header if it is there

  Scenario: Verify clicking on the cross icon will close the search area
  Given user is on Home Page
    And User clicks on search icon in header
    When User click on cross icon in Search Box
    Then Search overlay will be closed in site
    
   
  Scenario: Verify autocomplete search is working on search results page as well
    And User clicks on search icon in header
    When User clicks anywhere in search bar in site
    Then User should be able to type search "leg" in site
    When User clicks on search icon in header to submit the serach
    #Then User redirects to search results page
    When User clears the search input box in Searach result page
    And User clicks on search textbox on search result page
    When User starts writing "le" in search bar on search result page in WBW site
    Then Verify User should not be able to see auto suggestion search feature on search result page until user has entered 3 characters
    When User has written 3 or more or than 3 characters in search keyword on searh result page
    Then Verify User is able to see auto suggestion search feature on search result page


  Scenario: Verify  If there are no matching results and coveo fails to make any suggestion then below message will be displayed which is CMS editable
    And User clicks on search icon in header
    When User clicks anywhere in search bar in site
    Then User should be able to type search "xyz" in site
    When User clicks on search icon in header to submit the serach
    Then Verify that user is able to see the no search result message If there are no matching results
    
   Scenario: Verify autocomplete search is working on search results page as well
    And User clicks on search icon in header
    When User clicks anywhere in search bar in site
    Then User should be able to type search in site
    When User clicks on search icon in header to submit the serach
    #Then User redirects to search results page
    #Then Verify that user is able to see the search result found message If there are matching results
    And Verify search result on serach result page
		Then Close browser