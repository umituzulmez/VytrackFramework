package cbt.cybertek.pages;

import cbt.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CalenderEventsInfo extends BasePage{

    public WebElement infoText(String text){

        String textXpath = "//div[contains(text(),'" + text + "')]";
        WebElement info = Driver.get().findElement(By.xpath(textXpath));

        return info;
    }

}
