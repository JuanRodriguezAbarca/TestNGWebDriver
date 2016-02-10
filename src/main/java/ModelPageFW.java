package main.java;

import main.java.pageObjects.BuscadorBlock;
import main.java.pageObjects.XPATHduplexHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * Created by Juan_Rodriguez on 1/28/2016.
 */
public class ModelPageFW {

//    TheWebDriver theWebDriver = new TheWebDriver();
//    protected WebDriver driver = theWebDriver.getTheDriver();

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
}
