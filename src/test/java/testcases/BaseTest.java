package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import helpers.DriverHelper;

import static utilities.Constant.BASE_LINK;
import static utilities.Log.info;

public class BaseTest {
    DriverHelper driverHelper;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        driverHelper = new DriverHelper();
        driverHelper.startBrowser(browser);
        info("[STEP 1]");
        driverHelper.navigateTo(BASE_LINK);
    }

    @AfterMethod
    public void quit() {
        driverHelper.quitDriver();
    }
}
