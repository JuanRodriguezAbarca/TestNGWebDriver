package main.java.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Juan_Rodriguez on 1/27/2016.
 */
public class XPATHduplexHomePage {

    WebDriver driver;

    public XPATHduplexHomePage(WebDriver driver){
        this.driver = driver;
    }


    private WebElement theFirstTitle(){
        return driver.findElement(By.xpath("//div[@id='caja_menu'][2]"));
    }


    public String getTitle(){
        return theFirstTitle().getText();
    }
}
