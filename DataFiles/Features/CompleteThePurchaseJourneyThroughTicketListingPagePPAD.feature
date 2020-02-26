Feature: Complete The Purchase Journey Through Ticket Listing Page

Scenario: Verify that user is able Complete The Purchase Journey Through Ticket ListingPage
   Given user is on Home Page 
   Then click on Ticket link on home page
   Then verify all buyTickets links are present and click on Buy ticket
    Then User pick a date
    When User clicks on Add to Cart button on general pass adult ticket
    Then user click on Check out button on mini cart
    And user verifies the product amount listed on cart page
    Then user is able to enter details of guest form
    When user enter payment information with credit card
    And select terms and condition checkbox
    And click on Pay Now button
    Then user redirected to payment confirmation page
    Then Close browser
         