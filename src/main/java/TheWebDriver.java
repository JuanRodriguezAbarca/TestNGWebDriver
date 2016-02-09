package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;

import java.lang.reflect.Constructor;

/**
 * Created by Juan_Rodriguez on 1/27/2016.
 */
class TheWebDriver {


    private static WebDriver driver = null;

    private TheWebDriver() {

    }

    public synchronized static WebDriver getTheDriver() {
        if (null == driver) {
            try {
                driver = driverSelection();
                driver.manage().window().maximize();
            } finally {
                Runtime.getRuntime().addShutdownHook(
                        new Thread(new BrowserClearUp()));
            }
        }
        return driver;
    }


    private static WebDriver driverSelection() {
        String browserSelection = System.getProperty("browser");
        switch (browserSelection) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
            case "ie":
                return new InternetExplorerDriver();
            default:
                return new FirefoxDriver();
        }
    }

    public static void quitTheDriver() {
        try {
            getTheDriver().quit();
            driver = null;
            System.out.println("Closing the browser");
        } catch (UnreachableBrowserException e) {
            System.out.println("Can't close the browser");
        }
    }

    private static class BrowserClearUp implements Runnable {
        public void run() {
            quitTheDriver();
        }
    }
}

