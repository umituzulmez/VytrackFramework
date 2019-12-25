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

    @FindBy(xpath = "//a[@title='Grid Settings']")
    public WebElement gridOptionsButton;

    @FindBy(xpath = "//a/span[contains(text(),'Title')]")
    public WebElement titleColumn;

    public List<WebElement> allCheckBoxes = Driver.get().findElements(By.xpath("//table//tbody/tr"));

    public List<WebElement> optionsRelatedThreeDots = Driver.get().findElements(By.xpath("//td[contains(text(),'Testers meeting')]/parent::*/td/div/div/a[@class='dropdown-toggle']/following-sibling::*//li/a[@title]"));

    public WebElement eventTitle(String title){

        String titleXpath = "//td[contains(text(),'" + title + "')] [@data-column-label='Title']";
        WebElement titleName = Driver.get().findElement(By.xpath(titleXpath));

        return titleName;
    }

    public WebElement threeDotsRelatedTitle(String title) {

        String threeDotsXpath = "//td[contains(text(),'" + title + "')]/parent::*/td/div/div/a[@class='dropdown-toggle']";
        WebElement threeDots = Driver.get().findElement(By.xpath(threeDotsXpath));

        return threeDots;
    }

    public WebElement gridOptionsElement(String name){

        String gridOptionXpath = "//tbody[@class='ui-sortable']/tr//label[contains(text(),'" + name + "')]";
        WebElement gridOptionsElement = Driver.get().findElement(By.xpath(gridOptionXpath));

        return gridOptionsElement;
    }

}
