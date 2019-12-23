package cbt.cybertek.tests;

import cbt.cybertek.utilities.BrowserUtils;
import cbt.cybertek.utilities.ConfigurationReader;
import cbt.cybertek.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestBase {
    protected WebDriver driver;
    protected Actions action;
    protected WebDriverWait wait;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeTest
    public void setUpTest(){
        //initialize the class
        report = new ExtentReports();
        //create report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath +"/test-output/report.html";
        //initialize the html reporter with the report path
        htmlReporter= new ExtentHtmlReporter(path);
        //attach the html report to the report object
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("Vytrack smoke test");
        //set environment information
        report.setSystemInfo("Environment","QA");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS",System.getProperty("os.name"));
    }

    @AfterTest
    public void tearDownTest(){
        //this is when the report is actually created
        report.flush();
    }

    @BeforeMethod
    public void setUpMethod(){
        driver = Driver.get();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        action = new Actions(driver);
        wait = new WebDriverWait(driver,10);
        driver.get(ConfigurationReader.get("url"));
        driver.manage().window().maximize();
    }

    //ITestResult class describes the result of a test in TestNG
    @AfterMethod
    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {

        //If test failed
        if(result.getStatus()==ITestResult.FAILURE){
            //record the name of the failed test case
            extentLogger.fail(result.getName());

            //take the screenshot and return location of screenshot
            String screenshotPath = BrowserUtils.getScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotPath);

            //capture the exception
            extentLogger.fail(result.getThrowable());

        }else if(result.getStatus()==ITestResult.SKIP){

            extentLogger.skip("Test Skipped: " + result.getName());
        }

        //Close the driver
        Thread.sleep(1000);
        Driver.closeDriver();
    }
}