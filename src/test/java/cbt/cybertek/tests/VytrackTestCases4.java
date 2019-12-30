package cbt.cybertek.tests;

import cbt.cybertek.pages.CalendarEventsPage;
import cbt.cybertek.pages.CreateCalendarEventsPage;
import cbt.cybertek.pages.DashboardPage;
import cbt.cybertek.pages.LoginPage;
import cbt.cybertek.utilities.BrowserUtils;
import cbt.cybertek.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VytrackTestCases4 extends TestBase{

    @BeforeMethod
    public void RepeatingPart(){

        extentLogger = report.createTest("VytrackTestCases4");
        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");
        extentLogger.info("username: " + username);
        extentLogger.info("password: " + password);
        extentLogger.info("Login as a storemanager");
        loginPage.login(username,password);

        DashboardPage dashboardPage = new DashboardPage();
        extentLogger.info("Navigate to Activities/Calendar Events module page");
        dashboardPage.navigateToModule("Activities","Calendar Events");

    }

    @Test
    public void TestCase1(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Hover on three dots “...” for “Testers meeting” calendar event
        5. Verify that “view”, “edit” and “delete” options are available
         */

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        WebElement threeDots = calendarEventsPage.threeDotsRelatedTitle("Testers Meeting");

        extentLogger.info("Hover on three dots “...” for “Testers meeting” calendar event");
        BrowserUtils.hover(threeDots);
        //lazy way
        //BrowserUtils.hover(calendarEventsPage.threeDotsRelatedTitle("Testers Meeting"));

        List<String> expectedList = Arrays.asList("View","Edit","Delete");

        List<WebElement> actualOptions = calendarEventsPage.optionsRelatedThreeDots;

        List<String> actualList = new ArrayList<>();
        for (WebElement element : actualOptions) {
            //actualList.add(element.getAttribute("title"));
            BrowserUtils.verifyElementDisplayed(element);
        }
        //List<String> actualList = BrowserUtils.getElementsText(actualOptions);

        //extentLogger.info("Verify “View”, “Edit” and “Delete” options are available");
        //Assert.assertEquals(actualOptions,expectedList,"Verify “View”, “Edit” and “Delete” options are available");

    }

    @Test
    public void TestCase2(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Grid Options” button
        5. Deselect all options except “Title”
        6. Verify that “Title” column still displayed
         */


        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        extentLogger.info("Click on “Grid Options” button");
        calendarEventsPage.gridOptionsButton.click();

        extentLogger.info("Deselect all options except “Title”");
        calendarEventsPage.gridOptionsElement("Calendar").click();
        calendarEventsPage.gridOptionsElement("Start").click();
        calendarEventsPage.gridOptionsElement("End").click();
        calendarEventsPage.gridOptionsElement("Recurrent").click();
        calendarEventsPage.gridOptionsElement("Recurrence").click();
        calendarEventsPage.gridOptionsElement("Invitation status").click();

        extentLogger.info("Verify that “Title” column still displayed");
        Assert.assertTrue(calendarEventsPage.titleColumn.isDisplayed());
        //Or
        //BrowserUtils.verifyElementDisplayed(calendarEventsPage.titleColumn);

    }

    @Test
    public void testCase3(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Expand “Save And Close” menu
        6. Verify that “Save And Close”, “Save And New” and “Save” options are available
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        new CalendarEventsPage().createCalendarEvent.click();

        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        extentLogger.info("Expand “Save And Close” Menu");
        createCalendarEventsPage.expandButtonOfSaveAndClose.click();

        List<String> expectedList = Arrays.asList("Save And Close","Save And New","Save");

        List<WebElement> actualElements = createCalendarEventsPage.listOfSaveAndClose;

        extentLogger.info("get the text of listed elements");
        List<String> actualList = new ArrayList<>();
        for (WebElement element : actualElements) {
            actualList.add(element.getText());
        }

        extentLogger.info("Verify “Save And Close”, “Save And New” and “Save” options are available");
        Assert.assertEquals(expectedList,actualList,"Verify “Save And Close”, “Save And New” and “Save” options are available");
    }

    @Test
    public void testCase4(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Then, click on “Cancel” button
        6. Verify that “All Calendar Events” page subtitle is displayed
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.createCalendarEvent.click();

        extentLogger.info("Click on “Cancel” button");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        calendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.cancelButton.click();

        extentLogger.info("Verify “All Calendar Events” page subtitle is displayed");
        String expected = "All Calendar Events";
        String actual = calendarEventsPage.getPageSubTitle();
        Assert.assertEquals(expected,actual,"Verify “All Calendar Events” page subtitle is displayed");
    }

    @Test
    public void testCase5() {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Verify that difference between end and start time is exactly 1 hour
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.createCalendarEvent.click();

        extentLogger.info("Find start time");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        String start = createCalendarEventsPage.startTimeText();
        String end = createCalendarEventsPage.endTimeText();
        int startTime = Integer.parseInt(start.substring(0,2));
        int endTime = Integer.parseInt(end.substring(0,2));

        extentLogger.info("Verify difference between end and start time is exactly 1 hour");
        Assert.assertTrue(endTime-startTime==1,"Verify difference between end and start time is exactly 1 hour");

    }

    @Test
    public void tesCase6() {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Select “9:00 PM” as a start time
        6. Verify that end time is equals to “10:00 PM”
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.createCalendarEvent.click();

        extentLogger.info("Select “9:00 PM” as a start time");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.startTimeBox.click();
        createCalendarEventsPage.selectTime("9:00 PM").click();

        extentLogger.info("Verify end time equals to “10:00 PM”");
        createCalendarEventsPage.endTimeBox.click();
        String endTime = createCalendarEventsPage.getSelectedEndTime.getText();

        Assert.assertEquals(endTime,"10:00 PM","Verify end time equals to “10:00 PM”");

    }

    @Test
    public void testCase7(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Select “All-Day Event” checkbox
        6. Verify that “All-Day Event” checkbox is selected
        7. Verify that start and end time input boxes are not displayed
        8. Verify that start and end date input boxes are displayed
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        new CalendarEventsPage().createCalendarEvent.click();

        extentLogger.info("Select “All-Day Event” checkbox");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.allDayEventCheckBox.click();

        extentLogger.info("Verify “All-Day Event” checkbox is selected");
        Assert.assertTrue(createCalendarEventsPage.allDayEventCheckBox.isSelected(),"Verify “All-Day Event” checkbox is selected");

        extentLogger.info("Verify start time input box is not displayed");
        BrowserUtils.waitFor(1);
        Assert.assertFalse(createCalendarEventsPage.startTimeBox.isDisplayed(),"Verify start time input box is not displayed");

        extentLogger.info("Verify end time input box is not displayed");
        Assert.assertFalse(createCalendarEventsPage.endTimeBox.isDisplayed(),"Verify end time input box is not displayed");

        extentLogger.info("Verify start date input box is displayed");
        Assert.assertTrue(createCalendarEventsPage.startDate.isDisplayed(),"Verify start date input box is displayed");

        extentLogger.info("Verify start date input box is displayed");
        Assert.assertTrue(createCalendarEventsPage.endDate.isDisplayed(),"Verify start date input box is displayed");

    }

    @Test
    public void testCase8() {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Select “Repeat” checkbox
        6. Verify that “Repeat” checkbox is selected
        7. Verify that “Daily” is selected by default and following options are available in   “Repeats” drop-down:
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        new CalendarEventsPage().createCalendarEvent.click();

        extentLogger.info("Select “Repeat” checkbox");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.repeat.click();

        extentLogger.info("Verify “Repeat” checkbox is selected");
        Assert.assertTrue(createCalendarEventsPage.repeat.isSelected(),"Verify “Repeat” checkbox is selected");

        extentLogger.info("Verify “Daily” is selected by default");
        String defaultSelected = createCalendarEventsPage.selectedRepeatOption.getText();
        Assert.assertEquals(defaultSelected,"Daily","Verify “Daily” is selected by default");

        extentLogger.info("Verify following options are available in “Repeats” drop-down");
        List<String> expectedList = Arrays.asList("Daily","Weekly","Monthly","Yearly");
        List<WebElement> actualOptions = createCalendarEventsPage.repeatOptionsList().getOptions();
        List<String> actualList = BrowserUtils.getElementsText(actualOptions);
        Assert.assertEquals(actualList,expectedList,"Verify following options are available in “Repeats” drop-down");
    }

    @Test
    public void testCase9() {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Select “Repeat” checkbox
        6. Verify that “Repeat” checkbox is selected
        7. Verify that “Repeat Every” radio button is selected
        8. Verify that “Never” radio button is selected as an “Ends” option.
        9. Verify that following message as a summary is displayed: “Summary: Daily every 1 day”
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        new CalendarEventsPage().createCalendarEvent.click();

        extentLogger.info("Select “Repeat” checkbox");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.repeat.click();

        extentLogger.info("Verify “Repeat” checkbox is selected");
        Assert.assertTrue(createCalendarEventsPage.repeat.isSelected(),"Verify “Repeat” checkbox is selected");

        extentLogger.info("Verify “Repeat Every” radio button is selected");
        Assert.assertTrue(createCalendarEventsPage.days.isSelected(),"Verify “Repeat Every” radio button is selected");

        extentLogger.info("Verify “Never” radio button is selected as an “Ends” option");
        Assert.assertTrue(createCalendarEventsPage.never.isSelected(),"Verify “Never” radio button is selected as an “Ends” option");

        extentLogger.info("Verify following message as a summary is displayed: “Summary: Daily every 1 day”");
        String expectedMessage = "Daily every 1 day";
        String actualMessage = createCalendarEventsPage.summaryMessage.getText();
        Assert.assertEquals(actualMessage,expectedMessage,"Verify message is displayed: “Summary: Daily every 1 day”");
    }

    @Test
    public void testCase10() throws InterruptedException {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Select “Repeat” checkbox
        6. Select “After 10 occurrences” as an “Ends” option.
        7. Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences”
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        new CalendarEventsPage().createCalendarEvent.click();

        extentLogger.info("Select “Repeat” checkbox");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.repeat.click();

        extentLogger.info("Select “After 10 occurrences” as an “Ends” option");
        createCalendarEventsPage.after.click();
        createCalendarEventsPage.afterOccurrencesBox.sendKeys("10");
        createCalendarEventsPage.afterOccurrencesBox.click();

        extentLogger.info("Verify following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences”");
        String expectedMessage = "Daily every 1 day, end after 10 occurrences";
        String actualMessage = createCalendarEventsPage.summaryMessage.getText() + createCalendarEventsPage.summaryMessage2.getText();
        Assert.assertEquals(expectedMessage,actualMessage,"Verify message is displayed: “Summary: Daily every 1 day, end after 10 occurrences");
    }

    @Test
    public void testCase11() throws InterruptedException {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Select “Repeat” checkbox
        6. Select “By Nov 18, 2021” as an “Ends” option.
        7. Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        new CalendarEventsPage().createCalendarEvent.click();

        extentLogger.info("Select “Repeat” checkbox");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.repeat.click();

        extentLogger.info("Select “By Nov 18, 2021” as an “Ends” option");
        createCalendarEventsPage.by.click();
        createCalendarEventsPage.chooseADateBox.click();
        createCalendarEventsPage.yearOptionsList().selectByVisibleText("2021");
        createCalendarEventsPage.monthOptionsList().selectByVisibleText("Nov");
        createCalendarEventsPage.selectDayFromTable("18").click();

        extentLogger.info("Verify message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”");
        String expectedMessage = "Daily every 1 day, end by Nov 18, 2021";
        String actualMessage = createCalendarEventsPage.summaryMessage.getText() + createCalendarEventsPage.summaryMessage2.getText();
        Assert.assertEquals(actualMessage,expectedMessage,"Verify message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”");
    }

    @Test
    public void testCAse12() throws InterruptedException {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on “Create Calendar Event” button
        5. Select “Repeat” checkbox
        6. Select “Weekly” options as a “Repeat” option
        7. Select “Monday and Friday” options as a “Repeat On” options
        8. Verify that “Monday and Friday” options are selected
        9. Verify that following message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday”
         */

        extentLogger.info("Click on “Create Calendar Event” button");
        new CalendarEventsPage().createCalendarEvent.click();

        extentLogger.info("Select “Repeat” checkbox");
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.waitUntilLoaderScreenDisappear();
        createCalendarEventsPage.repeat.click();

        extentLogger.info("Select “Weekly” options as a “Repeat” option");
        createCalendarEventsPage.repeatOptions.click();
        createCalendarEventsPage.repeatOptionsList().selectByVisibleText("Weekly");

        extentLogger.info("Select “Monday and Friday” options as a “Repeat On” options");
        createCalendarEventsPage.selectDayRepeatOn("Monday").click();
        createCalendarEventsPage.selectDayRepeatOn("FRIDAY").click();

        extentLogger.info("Verify “Monday and Friday” options are selected");
        Assert.assertTrue(createCalendarEventsPage.selectDayRepeatOn("monday").isSelected(),"Verify “Monday” is selected");
        Assert.assertTrue(createCalendarEventsPage.selectDayRepeatOn("FRIDAY").isSelected(),"Verify “ Friday” is selected");

        extentLogger.info("Verify message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday”");
        String expectedMessage = "Weekly every 1 week on Monday, Friday";
        String actualMessage = createCalendarEventsPage.summaryMessage.getText();
        Assert.assertEquals(actualMessage,expectedMessage,"Verify message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday”");
    }


}
