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
    private String _tabLevel1 = "//ul[@id='menu']/li/a[normalize-space(text())='%s']";//content
    private String _tabLevel2 = "//ul[@id='menu']/li/a[normalize-space(text())='%s']/following-sibling::ul/li/a[normalize-space(text())='%s']";//articles
    private String _sideTab = "//div[@id='sidebar']//a[contains(.,'%s')]";

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
        return getDriver().findElement(By.xpath(String.format(_tabLevel1,"Content")));
    }

    private WebElement articlesTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel2,"Content","Articles")));
    }

    private WebElement componentsTab(){ return getDriver().findElement(By.xpath(String.format(_tabLevel1,"Components")));}
    private WebElement contactTab(){return getDriver().findElement(By.xpath(String.format(_tabLevel2,"Component","Contacts")));}
    private WebElement bannersTab(){return  getDriver().findElement(By.xpath(String.format(_tabLevel2,"Components","Banners")));}
    private WebElement bannersClientsTab(){ return getDriver().findElement(By.xpath(String.format(_sideTab,"Clients")));}
    private WebElement bannersCategoriesTab(){return getDriver().findElement(By.xpath(String.format(_sideTab,"Categories")));}
    public void clickTab(Tab tab) {
        switch (tab) {
            case CONTENT:
                clickElement(contentTab());
                break;
            case ARTICLES:
                clickElement(articlesTab());
                break;
            case COMPONENTS:
                clickElement(componentsTab());
                break;
            case CONTACTS:
                clickElement(contactTab());
                break;
            case BANNERS:
                clickElement(bannersTab());
                break;
            case BANNERS_CLIENTS:
                clickElement(bannersClientsTab());
                break;
            case BANNERS_CATEGORIES:
                clickElement(bannersCategoriesTab());
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
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        info("\t\tClick on " + getText(element)+" "+stackTrace[2].getMethodName());
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
        wait.until(ExpectedConditions.stalenessOf(element));
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
        ARTICLES,
        COMPONENTS,
        CONTACTS,
        BANNERS,
        BANNERS_CLIENTS,
        BANNERS_CATEGORIES
    }
}
