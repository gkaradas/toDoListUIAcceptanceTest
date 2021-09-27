Feature: toDoList UI Test

  Scenario: toDoTests
    Given Empty ToDo list
    When I write "buy some milk" to <text box> and press <enter>
    Then I should see "buy some milk" item in ToDo list

    Given ToDo list with "buy some milk" item
    When I write "enjoy the assignment" to <text box> and press <enter>
    Then I should see "enjoy the assignment" item inserted to ToDo list below "buy some milk" item

    Given ToDo list with "buy some milk" item
    When I click on <checkbox> next to "buy some milk" item
    Then I should see "buy some milk" item marked as DONE

    Given ToDo list with marked "buy some milk" item
    When I click on <checkbox> next to "buy some milk" item
    Then I should see "buy some milk" item marked as UNDONE

    Given ToDo list with "rest for a while" item
    When I click on <delete button> next to "rest for a while" item
    Then List should be empty

    Given ToDo list with "rest for a while" and "drink water" item in order
    When I click on <delete button> next to "rest for a while" item
    Then I should see "drink water" item in ToDo list