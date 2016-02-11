package main.java;

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

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Juan on 10/02/2016.
 */
public class ListenerClass extends TestListenerAdapter {

    WebDriver driver = TheWebDriver.getTheDriver();

    @Override
//    @AfterMethod(alwaysRun = true)
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getStatus());
        if (!result.isSuccess()) {
            try {
                takeScreenShot(result.getMethod());
            } catch (ScreenshotException se) {
                se.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void takeScreenShot(ITestNGMethod testNGMethod) throws IOException {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
//        WebDriver augmentedDriver = new Augmenter().augment(driver);
        if (driver != null) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String nameScreenShot = testNGMethod.getTestClass().getRealClass().getSimpleName() + "_" + testNGMethod.getMethodName();
                String path = getPath(nameScreenShot);
                FileUtils.copyFile(screenshot, new File(path));
                Reporter.log("<a title= \"Mierda\" href=\"../html/screenShots/" + getFileName(nameScreenShot) + "\">" +
                                "<img alt=\"Pie de Foto\" title= \"MierdaIMG\" src=\"../html/screenShots/"+getFileName(nameScreenShot)+"\"></a>");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileName(String nameTest) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss");
        Date date = new Date();
        return dateFormat.format(date) + "_" + nameTest + ".png";
    }

    private String getPath(String nameTest) throws IOException {
        File directory = new File(".");
        return directory.getCanonicalPath() + "\\screenShots\\" + getFileName(nameTest);
    }
}


