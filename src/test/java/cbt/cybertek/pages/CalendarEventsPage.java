package cbt.cybertek.pages;

import cbt.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalendarEventsPage extends BasePage {

    public CalendarEventsPage() {

        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

    @FindBy(css="div.btn-group.actions-group>div")
    public WebElement options;

    @FindBy(css="input.input-widget")
    public WebElement pageNumber;

    @FindBy(xpath = "//div[@class='btn-group']/button")
    public WebElement viewPerPage;

    @FindBy(xpath = "//label[contains(text(),'records')]")
    public WebElement totalRecords;

    public List<WebElement> rows = Driver.get().findElements(By.xpath("//tbody/tr"));

    @FindBy(xpath = "//table//tr//button/input")
    public WebElement topCheckBox;

    public List<WebElement> allCheckBoxes = Driver.get().findElements(By.xpath("//table//tbody/tr"));

    public WebElement getEventTitle(String title){

        String titleXpath = "//td[contains(text(),'" + title + "')] [@data-column-label='Title']";
        WebElement titleName = Driver.get().findElement(By.xpath(titleXpath));

        return titleName;
    }



}
