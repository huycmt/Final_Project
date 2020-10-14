package pageobject;

import org.openqa.selenium.WebElement;

public class BannersClientsManagerPage extends ManagerPage {
    //Locator
    private  String _bannersClients = "//table//td[count(//th[contains(.,'Client')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";


    //Element
    private WebElement bannersClients(String title){return getText()}


}
