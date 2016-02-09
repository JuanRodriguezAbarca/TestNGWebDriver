package test.java;

import main.java.ModelPageFW;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Juan_Rodriguez on 1/28/2016.
 */
public class BuscadorBlockTests extends ModelPageFW {


    @BeforeMethod
    public void checkingTheDriver() throws InterruptedException {
        if (!driver.getCurrentUrl().contentEquals("http://www.duplexinmobiliaria.es/")) {
            System.out.println("Re-Navigating to the main page");
            driver.get("http://www.duplexinmobiliaria.es/");
        } else {
            System.out.println("Current page is: "+driver.getCurrentUrl());
            System.out.println("Refreshing current page");
            driver.navigate().refresh();
        }
        System.out.println("Accepting cookies");
        buscador.acceptCookiesIfBar();
    }

    @AfterMethod
    public void cookieCleaner() {
        System.out.println("Cleaning cookies");
        driver.manage().deleteAllCookies();
    }


    @Test(dataProvider = "providingDataToBuscarBlock", priority = 0)
    public void movingTheFormsSliders(String inmueble, String operacion, String localidad, String habitaciones, int minPrice, int maxPrice, int minArea, int maxArea) throws InterruptedException {
        buscador.queryIntheSearchBlock(inmueble, operacion, localidad, habitaciones, minPrice, maxPrice, minArea, maxArea);

    }

    @DataProvider(name = "providingDataToBuscarBlock")
    private Object[][] providingDataToBuscarBlock() {
        return new Object[][]{
                {"pollas", "Venta", "Cuenca", "4", 60000, 90000, 60, 70},
                {"Adosado", "Venta", "Cuenca", "4", 80000, 90000, 80, 200},
                {"Adosado", "Venta", "Cuenca", "4", 50000, 60000, 100, 300},
                {"Adosado", "Venta", "Cuenca", "4", 20000, 30000, 50, 800000000},
                {"Adosado", "Venta", "Cuenca", "4", 10000, 90000, 200, 900}
        };
    }

}
