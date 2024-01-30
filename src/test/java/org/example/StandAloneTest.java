package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("hagrajales87@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test123!");
        driver.findElement(By.id("login")).click();

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        //products.stream().filter(s-> s.getText())
        WebElement desireElement =  products.stream().filter(product -> product.findElement(
                By.cssSelector("h5 b")).getText().equalsIgnoreCase("ZARA COAT 3")).
                findFirst().orElse(null);

        desireElement.findElement(By.cssSelector(".card-body button:last-of-type")).click();

    }
}
