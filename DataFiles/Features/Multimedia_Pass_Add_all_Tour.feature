Feature: Multimedia Pass Add all Tour Product Purchase 
 @regression
Scenario: Multimedia Pass Add all Tour Product and Purchase for upcoming days
          Given user is on Home Page   
          When User clicks on Buy Ticket CTA
          Then User click on Tours
          And  Add multiple Product
          Then user click on Check out button on mini cart
    And user verifies the product amount listed on cart page
    Then user is able to enter details of guest form
    When user enter payment information with credit card
    And select terms and condition checkbox
    And click on Pay Now button
    Then user redirected to payment confirmation page
    Then Close browser
          