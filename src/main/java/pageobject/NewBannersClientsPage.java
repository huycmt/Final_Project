package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewBannersClientsPage extends BannersClientsManagerPage {
    //Locators
    private By _nameTb = By.cssSelector("input#jform_name");
    private By _contactNameTb = By.cssSelector("input#jform_contact");
    private By _contactEmailTb = By.cssSelector("input#jform_email");

    private By _statusArrowDown = By.cssSelector("div#jform_state_chzn");
    private String _statusItem = "//fieldset//div[@id='jform_state_chzn']//li[contains(.,'%s')]";
    private By _saveAndCloseBtn = By.cssSelector("div#toolbar-save button");
    //Element

    private WebElement nameTb(){return getDriver().findElement(_nameTb);}
    private WebElement contactNameTb(){return getDriver().findElement(_contactNameTb);}
    private WebElement contactEmailTb(){return getDriver().findElement(_contactEmailTb);}
    private WebElement statusArrowDown(){return getDriver().findElement(_statusArrowDown);}
    private WebElement statusItem(String item){return getDriver().findElement(By.xpath(String.format(_statusItem,item)));}
    private WebElement saveAndCloseBtn(){return getDriver().findElement(_saveAndCloseBtn);}

    //Methods
    public void createNewBannersClients(String name, String contactName, String contactEmail, String status) {
        enterData(nameTb(), name);
        enterData(contactNameTb(), contactName);
        enterData(contactEmailTb(),contactEmail);

        clickElement(statusArrowDown());
        clickElement(statusItem(status));

        clickElement(saveAndCloseBtn());
    }
}
