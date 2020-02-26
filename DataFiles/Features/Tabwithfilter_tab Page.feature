Feature: Tabwithfilter view pages

        Scenario: Verify user is able to view tabwithfilter page  
        When user is on Home Page
        Then user is navigate to tickects page
        And  Check the tile on tab1 page  
        Then Cick on filter nexttab
        And  Check the tile on tab2 page
        Then Close browser
        
