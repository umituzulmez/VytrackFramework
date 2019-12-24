package cbt.cybertek.tests;

import cbt.cybertek.pages.CalendarEventsPage;
import cbt.cybertek.pages.CalenderEventsInfo;
import cbt.cybertek.pages.DashboardPage;
import cbt.cybertek.pages.LoginPage;
import cbt.cybertek.utilities.BrowserUtils;
import cbt.cybertek.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VytrackTestCases extends TestBase {

    @BeforeMethod
    public void repeatingPart(){

        extentLogger = report.createTest("VytrackTestCases 1to5");
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
    public void testCase1(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager (storemanager85) (UserUser123)
        3. Navigate to “Activities -> Calendar Events”
        4. Verify that page subtitle "Options" is displayed
        */

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        extentLogger.info("Verify page subtitle Options is displayed");
        BrowserUtils.verifyElementDisplayed(calendarEventsPage.options);
    }

    @Test
    public void testCase2(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Verify that page number is equals to "1"
         */

        String actual = new CalendarEventsPage().pageNumber.getAttribute("value");
        String expected = "1";
        extentLogger.info("Verify page number is 1");
        Assert.assertEquals(expected,actual,"verify page number is 1");
    }

    @Test
    public void testCase3(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Verify that view per page number is equals to "25"
         */

        String actual = new CalendarEventsPage().viewPerPage.getText();
        String expected = "25";
        extentLogger.info("Verify view per page number is 25");
        Assert.assertEquals(actual,expected,"verify view per page number is 25");
    }

    @Test
    public void testCase4(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Verify that number of calendar events (rows in the table) is equals to number of records
        Hint: In HTML, <table> tag represents table, <thread> - table header, <tr> tag represents table row element, <th> table header cell and <td> table data.
        Css selector: table > tr
         */

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        String[] arr = calendarEventsPage.totalRecords.getText().split(" ");
        //System.out.println(arr[2]);
        extentLogger.info("get the number of records");
        int numberOfRecords = Integer.parseInt(arr[2]);
        extentLogger.info("get the number of rows");
        int numberOfRows = BrowserUtils.getElementsText(calendarEventsPage.rows).size();
        //System.out.println("Rows = " + Rows);
        extentLogger.info("Verify the numbers of records and rows are equal");
        Assert.assertEquals(numberOfRecords,numberOfRows,"verify the numbers of records and rows are equal");
    }

    @Test
    public void testCase5(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Click on the top checkbox to select all
        5. Verify that all calendar events were selected
         */

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        extentLogger.info("Click the Top Check Box");
        //calendarEventsPage.waitUntilLoaderScreenDisappear();
        calendarEventsPage.topCheckBox.click();
        //BrowserUtils.selectCheckBox(calendarEventsPage.topCheckBox,true);

        extentLogger.info("get the numbers of all Check boxes");
        //List<WebElement> allCheckBoxes = driver.findElements(By.xpath("//table[@class='grid table-hover table table-bordered table-condensed']/tbody/tr"));
        int numOfAllCheckBoxes = calendarEventsPage.allCheckBoxes.size();

        extentLogger.info("get the number of selected check boxes");
        int counter = 0;
        for (WebElement allCheckBox : calendarEventsPage.allCheckBoxes) {

            allCheckBox.getAttribute("class").contains("row-selected");

            counter++;
        }

        System.out.println("counter = " + counter);

        extentLogger.info("Verify all check boxes are selected");
        Assert.assertEquals(counter,numOfAllCheckBoxes,"verify all check boxes are selected");
    }

    @Test
    public void testCase6(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Select “Testers Meeting”
        5. Verify that following data is displayed:
         */

        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        extentLogger.info("Click the Testers Meeting event");
        calendarEventsPage.waitUntilLoaderScreenDisappear();
        calendarEventsPage.getEventTitle("Testers Meeting").click();

        extentLogger.info("get the info of clicked eveent");
        CalenderEventsInfo calenderEventsInfo = new CalenderEventsInfo();
        String actual = calenderEventsInfo.infoText("Testers Meeting").getText();
        String expected = "Testers Meeting";

        extentLogger.info("Verify info is correct");
        Assert.assertEquals(actual,expected,"verify info is correct");
    }

}
