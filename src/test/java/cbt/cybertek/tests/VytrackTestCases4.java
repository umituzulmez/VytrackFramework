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
    public void TestCase1() {
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
}
