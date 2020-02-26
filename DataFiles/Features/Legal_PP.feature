Feature: Legal page view

        Scenario: Verify user is able to view all legal tab
        When user is on Home Page
        Then  user click on Legal section link
        And  User verify TERMS & CONDITIONS section present 
        Then User verify ATTRACTION ENTRY TERMS section present
        And  User verify TICKETS TERMS section present
        Then User verify PRIVACY POLICY section present
        And User verify COOKIES POLICY section present  
        Then Close browser 
        
