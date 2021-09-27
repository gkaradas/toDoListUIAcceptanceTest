package PageObjectModel;

import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ToDoList_Page extends AbstractClass{
    WebDriver driver = Driver.getDriver("Chrome");

    public ToDoList_Page() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void clearBrowserSession(){
        clearLocalStorage();
        driver.get("https://todomvc.com/examples/vue/");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @FindBy(xpath ="/html/body/section/header/input")
    private WebElement writeTextBox;
    public WebElement typeItemName(String input){
        sendKeysFunction(writeTextBox, input);
        return writeTextBox;
    }

    @FindBy(xpath ="/html/body/section/section/ul")
    private WebElement toDoListItemContainer;

    public void validateItemExist(String input) {
        boolean found = false;
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath("./li"));
        for (WebElement toDoListItem : toDoListItems) {
            if(toDoListItem.findElement(By.xpath("./div/label")).getAttribute("innerText").equals(input)){
                found = true;
            }
        }
        Assert.assertTrue(found);
    }

    public int getItemIndex(String input) {
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath("./li"));
        WebElement currentItem;
        for (int i = 0; i < toDoListItems.size(); i++) {
            currentItem = toDoListItems.get(i);
            if(currentItem.findElement(By.xpath("./div/label")).getAttribute("innerText").equals(input)){
                return i;
            }
        }
        return -1;
    }

    public void validateItemExistAtWithName(String input, int position) {
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath("./li"));
        WebElement targetItem = toDoListItems.get(position);
        Assert.assertEquals(input, targetItem.findElement(By.xpath("./div/label")).getAttribute("innerText"));
    }

    public void clickCheckBoxOf(String input) {
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath("./li"));
        WebElement targetItem = toDoListItems.stream().filter(item -> input.equals(item.getAttribute("innerText"))).findFirst().orElse(null);
        Assert.assertNotNull(targetItem);
        click(targetItem.findElement(By.xpath("./div/input")));
    }

    public void checkItemMarkedAsDone(String itemName) {
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath("./li"));
        WebElement targetItem = toDoListItems.stream().filter(item -> itemName.equals(item.getAttribute("innerText"))).findFirst().orElse(null);
        Assert.assertTrue(targetItem.getAttribute("class").contains("completed"));
    }

    public void checkItemMarkedAsUndone(String itemName) {
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath("./li"));
        WebElement targetItem = toDoListItems.stream().filter(item -> itemName.equals(item.getAttribute("innerText"))).findFirst().orElse(null);
        Assert.assertFalse(targetItem.getAttribute("class").contains("completed"));
    }

    public void clickDeleteButtonOfItem(String itemName) {
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath("./li"));
        WebElement targetItem = toDoListItems.stream().filter(item -> itemName.equals(item.getAttribute("innerText"))).findFirst().orElse(null);
        Assert.assertNotNull(targetItem);
        WebElement deleteButton = targetItem.findElement(By.xpath("./div/button"));
        mouseHoverAndClick(targetItem,deleteButton);
    }

    public void checkListEmpty() {
        List<WebElement> toDoListItems = toDoListItemContainer.findElements(By.xpath(".//*"));
        Assert.assertTrue(toDoListItems.isEmpty());
    }
}
