package StepDefinition;

import PageObjectModel.ToDoList_Page;
import Utilities.Driver;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class ToDolist_steps {
    ToDoList_Page toDoList_page = new ToDoList_Page();
    WebDriver driver = Driver.getDriver("Chrome");

    @Given("^Empty ToDo list$")
    public void empty_ToDo_list() throws Throwable {
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/vue/");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @When("^I write \"([^\"]*)\" to <text box> and press <enter>$")
    public void i_write_to_text_box_and_press_enter(String toDoItemName) throws Throwable {
        WebElement inputArea = toDoList_page.typeItemName(toDoItemName);
        inputArea.sendKeys(Keys.ENTER);
    }

    @Then("^I should see \"([^\"]*)\" item in ToDo list$")
    public void i_should_see_item_in_ToDo_list(String toDoItemName) throws Throwable {
        toDoList_page.validateItemExist(toDoItemName);
    }

    @Given("^ToDo list with \"([^\"]*)\" item$")
    public void todo_list_with_item(String initializedArgument) throws Throwable {
        toDoList_page.clearBrowserSession();
        i_write_to_text_box_and_press_enter(initializedArgument);
    }

    @Then("^I should see \"([^\"]*)\" item inserted to ToDo list below \"([^\"]*)\" item$")
    public void i_should_see_item_inserted_to_ToDo_list_below_item(String bottomItemName, String upperItemName) throws Throwable {
        int upperItemIndex = toDoList_page.getItemIndex(upperItemName);
        Assert.assertTrue(upperItemIndex > -1);
        toDoList_page.validateItemExistAtWithName(bottomItemName,upperItemIndex + 1);
    }

    @When("^I click on <checkbox> next to \"([^\"]*)\" item$")
    public void iClickOnCheckboxNextToItem(String itemName) throws Throwable {
        toDoList_page.clickCheckBoxOf(itemName);
    }

    @Then("^I should see \"([^\"]*)\" item marked as DONE$")
    public void iShouldSeeItemMarkedAsDONE(String itemName) throws Throwable {
        toDoList_page.checkItemMarkedAsDone(itemName);
    }


    @Given("^ToDo list with marked \"([^\"]*)\" item$")
    public void todoListWithMarkedItem(String initializedArgument) throws Throwable {
            toDoList_page.clearBrowserSession();
            i_write_to_text_box_and_press_enter(initializedArgument);
            toDoList_page.clickCheckBoxOf(initializedArgument);
    }

    @Then("^I should see \"([^\"]*)\" item marked as UNDONE$")
    public void iShouldSeeItemMarkedAsUNDONE(String itemName) throws Throwable {
        toDoList_page.checkItemMarkedAsUndone(itemName);
    }

    @When("^I click on <delete button> next to \"([^\"]*)\" item$")
    public void iClickOnDeleteButtonNextToItem(String itemName) throws Throwable {
        toDoList_page.clickDeleteButtonOfItem(itemName);
    }

    @Then("^List should be empty$")
    public void listShouldBeEmpty() {
        toDoList_page.checkListEmpty();
    }

    @Given("^ToDo list with \"([^\"]*)\" and \"([^\"]*)\" item in order$")
    public void todoListWithAndItemInOrder(String firstInitializedArgument, String secondInitializedArgument) throws Throwable {
        toDoList_page.clearBrowserSession();
        i_write_to_text_box_and_press_enter(firstInitializedArgument);
        i_write_to_text_box_and_press_enter(secondInitializedArgument);
    }
}

