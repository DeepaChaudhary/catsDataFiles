Feature: Contact us error validation 

        Scenario: Verify user is able to view contact us error 
        When user is on Home Page
        And  Click on contact us cta 
        Then Click on contact us submit contact us submit cta
        And  Verify that first name error message for contact us 
        Then Verify that Second name error message for contact us
        And  Verify that mail error message for contact us
        Then Verify that EnquiryType error message for contact us
        And  Verify that user message error for contact us
        Then Verify that user is able view captcha image
        Then Close browser
      
