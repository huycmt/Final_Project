package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Constant;

import static utilities.Log.info;

public class DriverHelper {
    static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    //This should be startBrowser and it should not return anything, you should a another method (getWebDriver() to return driver - Done.
    public void startBrowser(String browser) {
        info("-----------------------Start browser------------------------\n");
        switch (browserType.valueOf(browser)) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        info("Navigate to url: " + url);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Constant.TIME_OUT_SHORT, TimeUnit.SECONDS);
    }

    public void quitDriver() {
        if (null != driver) {
            info("-----------------------Close browser------------------------\n\n");
            driver.quit();
            driver = null;
        }
    }

    public enum browserType {
        CHROME, FIREFOX, EDGE
    }
}
