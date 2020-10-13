package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.getDriver;

public class ArticleManagerPage extends BasePage {

    //Locators
    private By _newBtn = By.xpath("//div[@class='btn-toolbar']//button[contains(.,'New')]");
    private By _editBtn = By.cssSelector("div#toolbar-edit>button");
    private By _savedMessage = By.cssSelector("div#system-message-container>div.alert.alert-success>div.alert-message");
    private String _articleCheckBox = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//input";
    private By _filterSearch = By.cssSelector("input#filter_search");
    private By _searchBtn = By.cssSelector("button.btn.hasTooltip[type='submit']");
    private String _titleArticle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[@data-original-title='Edit'][contains(.,'%s')]";
    private String _featuredStatusIcon = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//a[@data-original-title='Toggle featured status.']";


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

    private WebElement articleCheckBox(String title, String author) {
        return getDriver().findElement(By.xpath(String.format(_articleCheckBox, title, author)));
    }

    private WebElement filterSearch() {
        return getDriver().findElement(_filterSearch);
    }

    private WebElement searchBtn() {
        return getDriver().findElement(_searchBtn);
    }

    private List<WebElement> titleArticles(String title) {
        return getDriver().findElements(By.xpath(_titleArticle));
    }

    private WebElement featuredStatusIcon(String title, String author) {
        return getDriver().findElement(By.xpath(String.format(_featuredStatusIcon, title, author)));
    }

    //Methods

    public String getMessage() {
        return getText(savedMessage());
    }

    public void clickArticleCheckBox(String title, String author) {
        clickElement(articleCheckBox(title, author));
    }

    public void clickFeaturedStatusIcon(String title,String author){
        scrollToView(featuredStatusIcon(title, author));
        clickElement(featuredStatusIcon(title, author));
    }
    public void clickNewBtn() {
        clickElement(newBtn());
    }

    public void clickEditBtn() {
        clickElement(editBtn());
    }

    public void findArticles(String text) {
        enterData(filterSearch(), text);
        clickElement(searchBtn());
    }

    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < titleArticles(title).size(); i++) {
            if (!getText(titleArticles(title).get(i)).contains(title)) return false;
        }
        return true;
    }

    public void toggleFeatured(String title, String author) {
        clickArticleCheckBox(title, author);
        clickFeaturedStatusIcon(title, author);
    }

}
