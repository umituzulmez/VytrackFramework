package cbt.cybertek.pages;

import cbt.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactsPage extends BasePage{

    //we dont need to create construtor and use PageFactory Class here,
    // since we are extending from basepage.

    //if you are trying to come up with dynamic xpath you cannnot use @FindBy
    @FindBy(xpath = "//td[contains(text(),'mbrackstone9@example.com')][@data-column-label='Email']")
    public WebElement email;

    //create a method that accepts email as a String, returns Webelement based on String
    public WebElement getContactEmail(String email){
        String emailXpath = "//td[contains(text(),'" + email + "')][@data-column-label='Email']";
        WebElement sendEmail = Driver.get().findElement(By.xpath(emailXpath));

        return sendEmail;
    }
}
