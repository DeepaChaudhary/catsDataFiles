Feature: Articlepage verify

        Scenario: Verify user is able to view article list page  
        When user is on Home Page
        Then user click experience under INDOOR SKYDIVING on mega menu
        And  Check the article tile  
        Then Close browser