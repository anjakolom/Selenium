Feature: Zadanie20
  Scenario: Add and remove from Cart
    When We add 1 items to the cart
    Then check that the number of items in the cart 1
    When We add 1 items to the cart
    Then check that the number of items in the cart 2
    When We add 1 items to the cart
    Then check that the number of items in the cart 3
    When We are removing 3 items from the cart
    Then check that the number of items in the cart 0

  Scenario: Add and remove from Cart #2
    When We add 3 items to the cart
    Then check that the number of items in the cart 3
    When We are removing 3 items from the cart
    Then check that the number of items in the cart 0