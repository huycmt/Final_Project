package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.ContactManagerPage;
import pageobject.NewContactPage;

import static helpers.DataHelper.*;
import static pageobject.BasePage.Tab.*;
import static utilities.Constant.*;
import static utilities.Log.info;

public class ContactsTest extends BaseTest {
    ContactManagerPage contactManagerPage = new ContactManagerPage();
    NewContactPage newContactPage = new NewContactPage();
    String name = "1+" +title();

    @BeforeMethod(description = "User can create new contact with valid information")
    public void TO_JOOMLA_CONTACTS_001(){

        info("[STEP 2 - 4]");
        contactManagerPage.login(USERNAME,PASSWORD);

        info("[STEP 5]");
        contactManagerPage.clickTab(COMPONENTS);
        contactManagerPage.clickTab(CONTACTS);

        info("[STEP 6]");
        contactManagerPage.clickNewBtn();

        info("[STEP 7 - 10]");
        newContactPage.createNewContact(name,CONTACT_CATEGORY.get(1),PUBLISHED);


        info("[STEP 11]\nVerify the contact is saved successfully");
        Assert.assertEquals(contactManagerPage.getMessage(),SAVED_CONTACT_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(contactManagerPage.checkContactDisplay(name),"Contact is not displayed");

    }

    @Test(testName = "TO_JOOMLA_CONTACTS_002",description = "User can edit a contact")
    public void TO_JOOMLA_CONTACTS_002(){
        info("[STEP 12]");
        contactManagerPage.clickTab(COMPONENTS);
        contactManagerPage.clickTab(CONTACTS);

        info("[STEP 13]");
        contactManagerPage.clickContactCheckBox(name);

        info("[STEP 14]");
        contactManagerPage.clickEditBtn();

        info("[STEP 15 - 17]");
        String newName ="1+" +title();
        newContactPage.createNewContact(newName,CONTACT_CATEGORY.get(0),PUBLISHED);

        info("[STEP 18]\nVerify the contact is saved successfully");
        Assert.assertEquals(contactManagerPage.getMessage(),SAVED_CONTACT_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(contactManagerPage.checkContactDisplay(newName),"Contact is not displayed");

    }
    @Test(testName = "TO_JOOMLA_CONTACTS_009",description = "User can search for contacts using the filter text field")
    public void TO_JOOMLA_CONTACTS_009(){
        info("[STEP 12 - 13]");
        contactManagerPage.findByTitle(name);

        info("[STEP 14]\nVerify user can search for contacts using the filter text field");
        Assert.assertTrue(contactManagerPage.checkTitleMatchesKeywordEntered(name),"The titles of displayed contacts are not matched with the entered keyword");


    }
}
