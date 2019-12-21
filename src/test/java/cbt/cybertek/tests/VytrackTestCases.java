package cbt.cybertek.tests;

import cbt.cybertek.pages.DashboardPage;
import cbt.cybertek.pages.LoginPage;
import cbt.cybertek.utilities.BrowserUtils;
import cbt.cybertek.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class VytrackTestCases extends TestBase {

    @BeforeMethod
    public void repeatingPart(){

        LoginPage loginPage = new LoginPage();
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");
        loginPage.login(username,password);

        DashboardPage dashboardPage = new DashboardPage();
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

        WebElement optionsText = driver.findElement(By.xpath("//div[@class='btn-group actions-group']/div"));
        System.out.println("optionsText = " + optionsText.getText());

        BrowserUtils.verifyElementDisplayed(optionsText);
    }

    @Test
    public void testCase2(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Verify that page number is equals to "1"
         */

        WebElement pageNumber = driver.findElement(By.xpath("//*[@data-bound-input-widget='no-name']"));
        System.out.println(pageNumber.getAttribute("value"));
        String actual = pageNumber.getAttribute("value");
        String expected = "1";

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

        WebElement viewPerPage = driver.findElement(By.xpath("(//div[@class='btn-group'])[2]"));
        System.out.println(viewPerPage.getText());
        String actual = viewPerPage.getText();
        String expected = "25";

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

        WebElement numberOfRecords = driver.findElement(By.xpath("//label[@class='dib'] [contains(text(),'records')]"));
        System.out.println(numberOfRecords.getText());

        String[] arr = numberOfRecords.getText().split(" ");
        System.out.println(arr[2]);
        int Records = Integer.parseInt(arr[2]);

        int Rows = BrowserUtils.getElementsText(By.xpath("//tbody/tr")).size();
        System.out.println("Rows = " + Rows);

        Assert.assertEquals(Records,Rows,"verify the numbers of records and rows are equal");
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

        WebElement checkBox = driver.findElement(By.xpath("(//input[@type='checkbox'])[7]"));
        BrowserUtils.selectCheckBox(checkBox,true);

        List<WebElement> allCheckBoxes = driver.findElements(By.xpath("//table[@class='grid table-hover table table-bordered table-condensed']/tbody/tr"));

        int counter = 0;
        for (WebElement allCheckBox : allCheckBoxes) {

            allCheckBox.getAttribute("class").contains("row-selected");

            counter++;
        }

        System.out.println("counter = " + counter);
        Assert.assertEquals(counter,allCheckBoxes.size(),"verify all check boxes are selected");
    }

    @Test
    public void testCase6(){
        /*
        1. Go to “https://qa1.vytrack.com/"
        2. Login as a store manager
        3. Navigate to “Activities -> Calendar Events”
        4. Select “Testers meeting”
        5. Verify that following data is displayed:
         */

        WebElement testersMeeting = driver.findElement(By.xpath("//div[@class='grid-container']//tbody//tr//td[contains(text(),'Testers Meeting')]"));
        testersMeeting.click();

        WebElement info = driver.findElement(By.xpath("//div[contains(text(),'Testers Meeting')]"));
        System.out.println("info = " + info.getText());
        String actual = info.getText();
        String expected = "Testers Meeting";

        Assert.assertEquals(actual,expected,"verify info is correct");


    }


}
