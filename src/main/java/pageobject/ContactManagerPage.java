package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import static helpers.DriverHelper.getDriver;

public class ContactManagerPage extends ManagerPage{
    //Locator
    private  String _contactTitle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";
    private String _contactCheckBox = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/..//input";


    //Elements
    private List<WebElement> titleContacts(String title) {
        return getDriver().findElements(By.xpath(String.format(_contactTitle,title)));
    }
    private WebElement contactCheckBox(String title) {
        return getDriver().findElement(By.xpath(String.format(_contactCheckBox, title)));
    }

    //Methods
    public boolean checkContactDisplay(String title) {
        try {
            return titleContacts(title).get(0).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < titleContacts(title).size(); i++) {
            if (!getText(titleContacts(title).get(i)).contains(title)) return false;
        }
        return true;
    }

    public void clickContactCheckBox(String title) {
        clickElement(contactCheckBox(title));
    }

}
