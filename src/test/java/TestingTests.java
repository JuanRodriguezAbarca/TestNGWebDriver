package test.java;

import main.java.ModelPageFW;
import org.testng.annotations.Test;

/**
 * Created by Juan_Rodriguez on 1/27/2016.
 */
public class TestingTests extends ModelPageFW {


    @Test(priority = 2)
    public void testTheTest() throws InterruptedException {
        System.out.println(xpatHduplexHomePage.getTitle());
        Thread.sleep(5000);
    }

}
