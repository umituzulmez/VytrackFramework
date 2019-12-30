package cbt.cybertek.pages;

import cbt.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CreateCalendarEventsPage extends BasePage {

    public CreateCalendarEventsPage(){

        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[id^='recurrence-repeat-view']")
    public WebElement repeat;

    @FindBy(css = "[id^='recurrence-repeats-view']")
    public WebElement repeatOptions;

    @FindBy(xpath = "//span[@style='width: 292px; user-select: none;']")
    public WebElement selectedRepeatOption;

    @FindBy(className = "select2-chosen")
    public WebElement selectedOwner;

    @FindBy(css = "input[id^='oro_calendar_event_form_title-']")
    public WebElement title;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTimeBox;

    @FindBy(xpath = "(//span[@style='display:none']/input)[1]")
    public WebElement startTimeValue;

    @FindBy(xpath = "(//li[contains(@class,'selected')])[1]")
    public WebElement getSelectedStartTime;

    @FindBy(xpath = "(//li[contains(@class,'selected')])[2]")
    public WebElement getSelectedEndTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTimeBox;

    @FindBy(xpath = "(//span[@style='display:none']/input)[2]")
    public WebElement endTimeValue;

    @FindBy(xpath = "(//input[@type='radio'])[1]")
    public WebElement days;

    @FindBy(xpath = "(//input[@type='radio'])[2]")
    public WebElement weekday;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    public WebElement never;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    public WebElement after;

    @FindBy(xpath = "//input[@data-related-field='occurrences']")
    public WebElement afterOccurrencesBox;

    @FindBy(xpath = "(//input[@type='radio'])[5]")
    public WebElement by;

    @FindBy(xpath = "//div[@data-name='recurrence-summary']/div/span")
    public WebElement summaryMessage;

    @FindBy(xpath = "//div[@data-name='recurrence-summary']/div/span[2]")
    public WebElement summaryMessage2;

    @FindBy(css = ".btn-success.btn.dropdown-toggle")
    public WebElement expandButtonOfSaveAndClose;

    @FindBy(xpath = "//a[contains(text(),'Cancel')]")
    public WebElement cancelButton;

    @FindBy(name = "oro_calendar_event_form[allDay]")
    public WebElement allDayEventCheckBox;

    public List<WebElement> listOfSaveAndClose = Driver.get().findElements(By.xpath("//ul[@data-options]//button"));

    public Select repeatOptionsList(){

        return new Select(repeatOptions);
    }

    public String startTimeText(){

        String value = startTimeValue.getAttribute("value");
        String[] startArr1 = value.split("T");
        String startTime = startArr1[1].replace("Z","");

        return startTime;
    }

    public String endTimeText(){

        String value = endTimeValue.getAttribute("value");
        String[] startArr1 = value.split("T");
        String endTime = startArr1[1].replace("Z","");

        return endTime;
    }

    public WebElement selectTime(String clock){

        String clockXpath = "//li[contains(text(),'" + clock + "')]";
        WebElement clockTime = Driver.get().findElement(By.xpath(clockXpath));

        return clockTime;
    }

}

