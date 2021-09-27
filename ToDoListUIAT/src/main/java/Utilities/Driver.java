package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver(String driverType){
        if (driver==null) {
            if(driverType.equals("Chrome")){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gulce\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            else if (driverType.equals("Firefox")){
                System.setProperty("webdriver.firefox.driver", "FIREFOX DRIVER LINK HERE");
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }
    public static void closeDriver(){
        if (driver!=null){
            driver.close();
            driver=null;
        }
    }
}
