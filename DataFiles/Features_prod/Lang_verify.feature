Feature: lang_verify

			 
Scenario: Verify multi language feature 
       # Given Closing cookie header if it is there
       Given user is on Home Page
        When User clicks on language selector 
        Then User able to see language drop down 
				And User select languageAR
        Then user check the url for language changed
        
 #       When User clicks on language selector
 #       Then User able to see language drop down 
 #       And User select languageRU
 #       Then user check the url for language changed_RU
        
 #       When User clicks on language selector
 #       Then User able to see language drop down 
 #       And User select languageZH
 #       Then user check the url for language changed_ZH
        
 #        When User clicks on language selector
 #       Then User able to see language drop down 
 #       And User select languageEN
 #       Then user check the url for language changed_EN
        
Scenario: Verify Newsletter Subscription 
        When User click on Newsletter mail box
        Then User enter the valid mail id 
        And User click on singup button
        Then User able to see Thank you massage 
        Then Close browser