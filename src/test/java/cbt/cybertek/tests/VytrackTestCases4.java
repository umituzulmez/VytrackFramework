package cbt.cybertek.tests;

import cbt.cybertek.pages.CalendarEventsPage;
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
    public void TestCase1() throws InterruptedException {
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Hover on three dots “...” for “Testers meeting” calendar event
        5. Verify that “view”, “edit” and “delete” options are available
         */

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        WebElement threeDots = calendarEventsPage.threeDotsRelatedTitle("Testers Meeting");
        BrowserUtils.hover(threeDots);
        //lazy way
        //BrowserUtils.hover(calendarEventsPage.threeDotsRelatedTitle("Testers Meeting"));

        List<String> expectedList = Arrays.asList("View","Edit","Delete");

        List<WebElement> actualOptions = calendarEventsPage.optionsRelatedThreeDots;

        List<String> actualList = new ArrayList<>();
        for (WebElement element : actualOptions) {
            actualList.add(element.getAttribute("title"));
        }
        //List<String> actualList = BrowserUtils.getElementsText(actualOptions);

        Assert.assertEquals(actualList,expectedList,"Verify “View”, “Edit” and “Delete” options are available");





    }
}
