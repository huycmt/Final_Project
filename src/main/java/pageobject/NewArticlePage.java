package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static helpers.DriverHelper.getDriver;

public class NewArticlePage extends ArticleManagerPage {
    //Locator
    private By _titleTb = By.cssSelector("input#jform_title");
    private By _categoryDrd = By.cssSelector("select#jform_catid");
    private By _categoryArrowDown = By.cssSelector("div#jform_catid_chzn");
    private By _statusArrowDown = By.cssSelector("div#jform_state_chzn");
    private String _dropdownList = "//ul[@class='chzn-results']//li[contains(@class,'active-result')][contains(.,'%s')]";
    private By _articleTextTa = By.cssSelector("iframe#jform_articletext_ifr");
    private By _saveAndCloseBtn = By.cssSelector("div#toolbar-save button");

    //Elements
    private WebElement titleTb(){
        return getDriver().findElement(_titleTb);
    }
    private WebElement statusArrowDown(){
        return getDriver().findElement(_statusArrowDown);
    }
    private WebElement categoryArrowDown(){
        return getDriver().findElement(_categoryArrowDown);
    }
    private WebElement categoryDrdE(){return getDriver().findElement(_categoryDrd);}
    private Select categoryDrd(){ return new Select(categoryDrdE());}
    private WebElement dropdownList(String dropdown){
        return getDriver().findElement(By.xpath(String.format(_dropdownList,dropdown)));
    }
    private WebElement articleTextTa(){return getDriver().findElement(_articleTextTa);}
    private WebElement saveAndCloseBtn(){return getDriver().findElement(_saveAndCloseBtn);}

    //Methods
    public void createNewArticle(String title, String articleText,String category,String status){
        enterData(titleTb(),title);
        enterData(articleTextTa(),articleText);

        selectItem(categoryArrowDown(),category);
        selectItem(statusArrowDown(),status);

        clickElement(saveAndCloseBtn());
    }
    public void selectItem(WebElement arrowDown,String dropdown){
        clickElement(arrowDown);
        clickElement(dropdownList(dropdown));
    }

}
