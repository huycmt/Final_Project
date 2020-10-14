package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.ArticleManagerPage;
import pageobject.NewArticlePage;

import static helpers.DataHelper.*;
import static pageobject.BasePage.Tab.*;
import static utilities.Log.*;
import static utilities.Constant.*;

public class ArticleTest extends BaseTest{
    ArticleManagerPage articleManagerPage = new ArticleManagerPage();
    NewArticlePage newArticlePage = new NewArticlePage();
    String title = title();
    String articleText = articleText();

    @BeforeMethod(description = "User can create new article with valid information")
    public void TO_JOOMLA_ARTICLE_001(){

        info("[STEP 2 - 4]");
        articleManagerPage.login(USERNAME,PASSWORD);

        info("[STEP 5]");
        articleManagerPage.clickTab(CONTENT);
        articleManagerPage.clickTab(ARTICLES);

        info("[STEP 6]");
        articleManagerPage.clickNewBtn();

        info("[STEP 7 - 11]");
        newArticlePage.createNewArticle(title,articleText,ARTICLE_CATEGORY.get(1),PUBLISHED);


        info("[STEP 12]\nVerify the article is saved successfully");
        Assert.assertEquals(articleManagerPage.getMessage(),SAVED_ARTICLE_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(articleManagerPage.checkArticleDisplay(title),"Article is not displayed");

    }
    @Test(testName = "TO_JOOMLA_ARTICLE_002",description = "User can edit an article")
    public void TO_JOOMLA_ARTICLE_002(){
        info("[STEP 13]");
        articleManagerPage.clickTab(CONTENT);
        articleManagerPage.clickTab(ARTICLES);

        info("[STEP 14]");
        articleManagerPage.clickArticleCheckBox(title,AUTHOR);

        info("[STEP 15]");
        articleManagerPage.clickEditBtn();

        info("[STEP 16 - 18]");
        String newTitle =title();
        newArticlePage.createNewArticle(newTitle,articleText(),ARTICLE_CATEGORY.get(0),PUBLISHED);

        info("[STEP 19]\nVerify the article is saved successfully");
        Assert.assertEquals(articleManagerPage.getMessage(),SAVED_ARTICLE_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(articleManagerPage.checkArticleDisplay(newTitle),"Article is not displayed");

    }

    @Test(testName = "TO_JOOMLA_ARTICLE_009",description = "User can search for articles using the filter text field")
    public void TO_JOOMLA_ARTICLE_009(){
        info("[STEP 13 - 14]");
        articleManagerPage.findByTitle(title);

        info("[STEP 15]\nVerify user can search for articles using the filter text field");
        Assert.assertTrue(articleManagerPage.checkTitleMatchesKeywordEntered(title),"The titles of displayed articles are not matched with the entered keyword");

    }
    @Test(testName = "TO_JOOMLA_ARTICLE_016",description = "User can change the feature property of articles using the Featured column")
    public void TO_JOOMLA_ARTICLE_016(){
        info("[STEP 13 - 14]");
        articleManagerPage.toggleFeatured(title,AUTHOR);

        info("[STEP 15]\nVerify the article is featured successfully");
        Assert.assertEquals(articleManagerPage.getMessage(),FEATURED_MESSAGE,"Article feature status toggle failed");
        Assert.assertEquals(articleManagerPage.getAttributeFeatureIcon(title,AUTHOR),"icon-featured", "The icon of the selected item displayed is not 'Featured'.");

        info("[STEP 16 - 17]");
        articleManagerPage.toggleFeatured(title,AUTHOR);

        info("[STEP 18]\nVerify the article is un-featured successfully");
        Assert.assertEquals(articleManagerPage.getMessage(),UN_FEATURED_MESSAGE,"Article feature status toggle failed");
        Assert.assertEquals(articleManagerPage.getAttributeFeatureIcon(title,AUTHOR),"icon-unfeatured", "The icon of the selected item displayed is not 'Unfeatured'.");

    }
}
