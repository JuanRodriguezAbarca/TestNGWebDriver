package main.java.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Juan_Rodriguez on 1/28/2016.
 */
public class BuscadorBlock {

    WebDriver driver;

    public BuscadorBlock(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement precioLeftSlide() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][1]//span[@class='irs-from']"));
    }

    private WebElement cookiesBar(){
        return driver.findElement(By.id("barraaceptacion"));
    }

    private WebElement acceptCookies(){
        return driver.findElement(By.xpath("//a[@class='ok']"));
    }

    private WebElement precioLeftButton() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][1]/span/span[6]"));
    }

    private WebElement precioRightSlide() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][1]//span[@class='irs-to']"));
    }

    private WebElement precioRightButton() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][1]/span/span[7]"));
    }

    private WebElement overlappedPrices() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][1]//span[@class='irs-single']"));
    }

    private WebElement leftSurfaceButton() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][2]/span/span[6]"));
    }

    private WebElement leftSurfaceValue() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][2]//span[@class='irs-from']"));
    }

    private WebElement rightSurfaceValue() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][2]//span[@class='irs-to']"));
    }

    private WebElement rightSurfaceButton() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][2]/span/span[7]"));
    }

    private WebElement overlappedSurfaces() {
        return driver.findElement(By.xpath("//span[@class='buscador_tipo_in'][2]//span[@class='irs-single']"));
    }

    private Select selectTipoInmueble() {
        return new Select(driver.findElement(By.id("buscador_tipo")));
    }

    private Select selectTipoOperacion() {
        return new Select(driver.findElement(By.id("buscador_operacion")));
    }

    private Select selectLocalidad() {
        return new Select(driver.findElement(By.id("buscador_localidad")));
    }

    private Select selectZona() {
        return new Select(driver.findElement(By.id("buscador_zona")));
    }

    private Select selectNumberRooms() {
        return new Select(driver.findElement(By.id("buscador_habitaciones")));
    }

    private WebElement buscarButton() {
        return driver.findElement(By.xpath("//div[@id='buscador_boton']/a"));
    }

//TODO divide los dos metodos de las barras para asegurarte que no sobrepasan los limites y no se cruzaran los valores. Divide en metodos mas pequenios

    private void setThePriceRange(int minPrice, int maxPrice) throws InterruptedException {
        if (minPrice < 65000) {
            moveTheSlider(minPrice, precioLeftSlide(), precioLeftButton(), overlappedPrices(), "right");
        }
        if (maxPrice < 70000) {
            moveTheSlider(maxPrice, precioRightSlide(), precioRightButton(), overlappedPrices(), "left");
        } else {
            moveTheSlider(maxPrice, precioRightSlide(), precioRightButton(), overlappedPrices(), "right");
        }
        if (minPrice >= 65000) {
            moveTheSlider(minPrice, precioLeftSlide(), precioLeftButton(), overlappedPrices(), "right");
        }
        Thread.sleep(3000);
    }


    private void setTheSurfaceRange(int minArea, int maxArea) throws InterruptedException {
        if (minArea < 80) {
            moveTheSlider(minArea, leftSurfaceValue(), leftSurfaceButton(), overlappedSurfaces(), "right");
        }
        if (maxArea < 90) {

            moveTheSlider(maxArea, rightSurfaceValue(), rightSurfaceButton(), overlappedSurfaces(), "left");
        } else {
            moveTheSlider(maxArea, rightSurfaceValue(), rightSurfaceButton(), overlappedSurfaces(), "right");
        }
        if (minArea >= 80) {
            moveTheSlider(minArea, leftSurfaceValue(), leftSurfaceButton(), overlappedSurfaces(), "right");
        }
        Thread.sleep(3000);
    }

    private int findCorrectValueWhenSlideIsHidden(WebElement slide, WebElement ovelapped) {
        int currentValue;
        if (slide.isDisplayed()) {
            currentValue = Integer.valueOf(slide.getText().replaceAll("€", "").replaceAll("m2", "").replaceAll(" ", ""));
        } else {
            String range = ovelapped.getText();
            String[] rangesSplitted = range.split("—");

            currentValue = Integer.valueOf(rangesSplitted[1].replaceAll("€", "").replaceAll("m2","").replaceAll(" ", ""));
        }
        return currentValue;
    }

    private void moveTheSlider(int slideTarget, WebElement sliderValue, WebElement sliderButton, WebElement overllaped, String direction) {
        Actions moveSlider = new Actions(driver);
        int sliderPosition = 0;
        while (findCorrectValueWhenSlideIsHidden(sliderValue,overllaped) < slideTarget) {
            Action leftAction = moveSlider.dragAndDropBy(sliderButton, sliderPosition, 0).build();
            leftAction.perform();
            if (direction.contentEquals("right"))
                sliderPosition++;
            else if (direction.contentEquals("left"))
                sliderPosition--;
        }
    }


    private void selectFromSelectsBlock(String selectList, String value) {
        Select list = null;
        switch (selectList) {
            case "inmueble":
                list = selectTipoInmueble();
                break;
            case "operacion":
                list = selectTipoOperacion();
                break;
            case "localidad":
                list = selectLocalidad();
                break;
            case "zona":
                list = selectZona();
                break;
            case "habitaciones":
                list = selectNumberRooms();
                break;
            default:
                throw new RuntimeException();

        }

        list.selectByValue(value);
    }

    public void queryIntheSearchBlock(String inmueble, String operacion, String localidad, String habitaciones, int minPrice, int maxPrice, int minArea, int maxArea) throws InterruptedException {
        selectFromSelectsBlock("inmueble", inmueble);
        selectFromSelectsBlock("operacion", operacion);
        selectFromSelectsBlock("localidad", localidad);
        selectFromSelectsBlock("habitaciones", habitaciones);
        setThePriceRange(minPrice, maxPrice);
        setTheSurfaceRange(minArea, maxArea);
        buscarButton().click();
    }

    public void acceptCookiesIfBar(){
        if(cookiesBar().isDisplayed())
            acceptCookies().click();
    }
}