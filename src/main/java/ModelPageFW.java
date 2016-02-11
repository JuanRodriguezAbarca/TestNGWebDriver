package main.java;

import main.java.pageObjects.BuscadorBlock;
import main.java.pageObjects.XPATHduplexHomePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.internal.TestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Juan_Rodriguez on 1/28/2016.
 */
public class ModelPageFW extends TestListenerAdapter {


    protected WebDriver driver = TheWebDriver.getTheDriver();


    protected BuscadorBlock buscador = new BuscadorBlock(driver);
    protected XPATHduplexHomePage xpatHduplexHomePage = new XPATHduplexHomePage(driver);


    @BeforeTest
    public void initSuite() {
        System.out.println("Navigating to the main page");
        driver.get("http://www.duplexinmobiliaria.es/");
    }


    @AfterSuite
    public void shutDown() {
        System.out.println("Shutting down the browser.\n\nSuite finished");
        TheWebDriver.quitTheDriver();
    }

//    @AfterMethod(alwaysRun = true)
//    public void setScreenShot(ITestResult result) throws IOException {
//        System.out.println(result.getStatus());
//        if (!result.isSuccess()) {
//            try {
//                takeScreenShot(result.getMethod());
//            } catch (ScreenshotException se) {
//                se.printStackTrace();
//            }
//        }
//    }

}
