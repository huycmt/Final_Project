package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.DriverHelper.getDriver;
import static utilities.Constant.TIME_OUT_SHORT;
import static utilities.Log.info;

public class BasePage {
    WebDriverWait wait;
    //Locators
    private By _usernameTb = By.cssSelector("input[name='username']");
    private By _passwordTb = By.cssSelector("input[name='passwd']");
    private By _loginBtn = By.cssSelector("button");
    private By _contentTab = By.xpath("//ul[@id='menu']//a[normalize-space(text())='Content']");
    private By _articlesTab = By.xpath("//ul[@id='menu']//a[normalize-space(text())='Articles']");


    //Elements
    private WebElement username() {
        return getDriver().findElement(_usernameTb);
    }

    private WebElement password() {
        return getDriver().findElement(_passwordTb);
    }

    private WebElement loginBtn() {
        return getDriver().findElement(_loginBtn);
    }

    private WebElement contentTab() {
        return getDriver().findElement(_contentTab);
    }

    private WebElement articlesTab() {
        return getDriver().findElement(_articlesTab);
    }

    public void clickTab(Tab tab) {
        switch (tab) {
            case CONTENT:
                clickElement(contentTab());
                break;
            case ARTICLES:
                clickElement(articlesTab());
                break;
        }
    }

    public void enterData(WebElement element, String data) {
        info("\t\tEnter data for the " + element.getAttribute("name") + " field: " + data);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys("\b");
        element.sendKeys(data);
    }

    public void clickElement(WebElement element) {
        info("\t\tClick on " + getText(element));
        //scrollToView(element);
        waitForElementVisibility(element);
        waitUntilElementClickable(element);
        element.click();
    }

    public void selectFromDropDown(WebElement element, Select select, String text) {
        waitUntilElementContainsText(element,text);
        select.selectByVisibleText(text);
    }

    public void scrollToView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitUntilElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIME_OUT_SHORT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitUntilElementContainsText(WebElement element,String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIME_OUT_SHORT);
        wait.until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public void waitUntilElementClickable(WebElement element) {
        wait = new WebDriverWait(getDriver(), TIME_OUT_SHORT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementVisibility(WebElement element) {
        wait = new WebDriverWait(getDriver(), TIME_OUT_SHORT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getText(WebElement element) {
        waitForElementVisibility(element);
        return element.getText().trim();
    }

    public void login(String username, String password) {
        enterData(username(), username);
        enterData(password(), password);
        clickElement(loginBtn());
    }

    //Methods
    public enum Tab {
        CONTENT,
        ARTICLES
    }
}
