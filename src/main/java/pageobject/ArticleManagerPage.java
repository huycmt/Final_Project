package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import static helpers.DriverHelper.getDriver;

public class ArticleManagerPage extends ManagerPage {

    //Locators
    private String _articleCheckBox = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//input";
    private String _articleTitle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[@data-original-title='Edit'][contains(.,'%s')]";
    private String _featuredStatusIcon = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//a[@data-original-title='Toggle featured status.']/span";


    //Elements
    private WebElement articleCheckBox(String title, String author) {
        return getDriver().findElement(By.xpath(String.format(_articleCheckBox, title, author)));
    }

    private List<WebElement> articlesTitle(String title) {
        return getDriver().findElements(By.xpath(String.format(_articleTitle, title)));
    }

    private WebElement featuredStatusIcon(String title, String author) {
        return getDriver().findElement(By.xpath(String.format(_featuredStatusIcon, title, author)));
    }

    //Methods
    public void clickArticleCheckBox(String title, String author) {
        clickElement(articleCheckBox(title, author));
    }

    public String getAttributeFeatureIcon(String title, String author) {
        return featuredStatusIcon(title, author).getAttribute("class").trim();
    }

    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < articlesTitle(title).size(); i++) {
            if (!getText(articlesTitle(title).get(i)).contains(title)) return false;
        }
        return true;
    }

    public boolean checkArticleDisplay(String title) {
        try {
            return articlesTitle(title).get(0).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void toggleFeatured(String title, String author) {
        clickArticleCheckBox(title, author);
        clickElement(featuredStatusIcon(title, author));
    }

}
