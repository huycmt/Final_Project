package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewContactPage extends ContactManagerPage {
    //Locators
    private By _nameTb = By.cssSelector("input#jform_name");
    private By _categoryArrowDown = By.cssSelector("div#jform_catid_chzn");
    private By _statusArrowDown = By.cssSelector("div#jform_published_chzn");
    private String _statusItem = "//fieldset//div[@id='jform_published_chzn']//li[contains(.,'%s')]";
    private String _categoryItem = "//fieldset//div[@id='jform_catid_chzn']//li[contains(.,'%s')]";
    private By _saveAndCloseBtn = By.cssSelector("div#toolbar-save button");

    //Elements
    private WebElement nameTb(){ return getDriver().findElement(_nameTb);}
    private WebElement statusArrowDown() {
        return getDriver().findElement(_statusArrowDown);
    }

    private WebElement categoryArrowDown() {
        return getDriver().findElement(_categoryArrowDown);
    }
    private WebElement saveAndCloseBtn() {
        return getDriver().findElement(_saveAndCloseBtn);
    }

    private WebElement statusItem(String item) {
        return getDriver().findElement(By.xpath(String.format(_statusItem, item)));
    }
    private WebElement categoryItem(String item) {
        return getDriver().findElement(By.xpath(String.format(_categoryItem, item)));
    }
    //Methods
    public void createNewContact(String name, String category, String status) {
        enterData(nameTb(),name);

        clickElement(categoryArrowDown());
        clickElement(categoryItem(category));

        clickElement(statusArrowDown());
        clickElement(statusItem(status));

        clickElement(saveAndCloseBtn());
    }



}
