package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class ManagerPage extends BasePage {
    //Locators
    private By _newBtn = By.xpath("//div[@class='btn-toolbar']//button[contains(.,'New')]");
    private By _editBtn = By.cssSelector("div#toolbar-edit>button");
    private By _savedMessage = By.cssSelector("div#system-message-container>div.alert.alert-success>div.alert-message");
    private By _searchBtn = By.cssSelector("button.btn.hasTooltip[type='submit']");
    private By _filterSearch = By.cssSelector("input#filter_search");
    private WebElement filterSearch() {
        return getDriver().findElement(_filterSearch);
    }

    //Elements
    private WebElement newBtn() {
        return getDriver().findElement(_newBtn);
    }

    private WebElement editBtn() {
        return getDriver().findElement(_editBtn);
    }

    private WebElement savedMessage() {
        return getDriver().findElement(_savedMessage);
    }
    private WebElement searchBtn() {
        return getDriver().findElement(_searchBtn);
    }


    //Methods
    public String getMessage() {
        return getText(savedMessage());
    }
    public void clickNewBtn() {
        clickElement(newBtn());
    }
    public void findByTitle(String text) {
        enterData(filterSearch(), text);
        clickElement(searchBtn());
    }
    public void clickEditBtn() {
        clickElement(editBtn());
    }
}
