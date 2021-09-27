package PageObjectModel;

import Utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AbstractClass {

    private WebDriver driver = Driver.getDriver("Chrome");
    WebDriverWait wait = new WebDriverWait(driver,10);

    public void verifyTitle(String title) {
        Assert.assertEquals(true, driver.getTitle().equals(title));}

    public void clickWithCaution(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();}

    public void click(WebElement element){
        element.click();}

    public void sendKeysFunction(WebElement element, String value){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);}

  //  public void sendFilePathFunction(WebElement element, String value){element.sendKeys(value);}


    public void mouseHoverAndClick(WebElement element, WebElement deleteButton){
        Actions action = new Actions(driver);
        action.moveToElement(element).moveToElement(deleteButton).click().build().perform();}

    public void terminateBrowser(){
        driver.close();
    }

    public void clearLocalStorage() {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("localStorage.clear();");
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }
    }
}
