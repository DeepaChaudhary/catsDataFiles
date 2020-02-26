Feature: Cardlists carousel pages

        Scenario: Verify user is able to view lists of carouse cards 
        When user is on Home Page
        And  Click on carousel cardlist1
        Then Click on carousel cardlist2 and veryfy card is present 
        Then Close browser
