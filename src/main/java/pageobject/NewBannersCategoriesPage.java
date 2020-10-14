package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewBannersCategoriesPage extends BannersCategoriesManagerPage {

    //Locator
    private By _titleTb = By.cssSelector("input#jform_title");
    private By _categoryArrowDown = By.cssSelector("div#jform_catid_chzn");
    private By _statusArrowDown = By.cssSelector("div#jform_state_chzn");
    private String _dropdownList = "//ul[@class='chzn-results']//li[contains(@class,'active-result')][contains(.,'%s')]";
    private By _articleTextTa = By.cssSelector("iframe#jform_articletext_ifr");
    private By _saveAndCloseBtn = By.cssSelector("div#toolbar-save button");

    //Elements
    private WebElement titleTb() {
        return getDriver().findElement(_titleTb);
    }

    private WebElement statusArrowDown() {
        return getDriver().findElement(_statusArrowDown);
    }

    private WebElement categoryArrowDown() {
        return getDriver().findElement(_categoryArrowDown);
    }

    private WebElement dropdownList(String dropdown) {
        return getDriver().findElement(By.xpath(String.format(_dropdownList, dropdown)));
    }

    private WebElement articleTextTa() {
        return getDriver().findElement(_articleTextTa);
    }

    private WebElement saveAndCloseBtn() {
        return getDriver().findElement(_saveAndCloseBtn);
    }
}
