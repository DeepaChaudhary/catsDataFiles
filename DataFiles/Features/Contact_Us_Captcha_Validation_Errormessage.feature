Feature: Contact us Page Captcha error validation 

        Scenario: Verify user is able to view contact us captcha error mgs
        When user is on Home Page
        And  Click on contact us cta 
     	Then user fill  contact us form
        Then user enter wrong captcha
        Then Click on contact us submit contact us submit cta
        And user able to see Captcha error message
        Then Close browser 
	
	Scenario: Verify logged In user details are Pre-filled on Contact Use Page
        When user is on Home Page
        And  User clicks on MyProfile link
        And  user logs in
        And  Click on contact us cta
        Then user verify prefilled details in form
        Then user enter wrong captcha
        Then Click on contact us submit contact us submit cta
        And  user able to see Captcha error message
        Then Close browser
        
      
