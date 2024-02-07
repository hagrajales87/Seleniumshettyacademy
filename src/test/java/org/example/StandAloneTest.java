package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.LandingPage;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");


        String productName = "ZARA COAT 3";
        LandingPage landingPage = new LandingPage(driver);
        landingPage.setUserEmail("hagrajales87@gmail.com");
        landingPage.userPassword("Test123!");
        landingPage.clickOnLoginButton();

        driver.findElement(By.id("userEmail")).sendKeys("hagrajales87@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test123!");
        driver.findElement(By.id("login")).click();


        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        //products.stream().filter(s-> s.getText())
        WebElement desireElement =  products.stream().filter(product -> product.findElement(
                By.cssSelector("h5 b")).getText().equalsIgnoreCase(productName)).
                findFirst().orElse(null);

        assert desireElement != null;
        desireElement.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay div[class*='ball-scale']"))));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay div[class*='ball-scale']"))));
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(".cartSection h3"))));
        List<WebElement> myCartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        //Option 1
        WebElement cartProduct = myCartProducts.stream().filter(s -> s.getText()
                .equalsIgnoreCase(productName)).findFirst().orElse(null);
        assert cartProduct != null;
        Assert.assertEquals(cartProduct.getText(), productName, "Item not found");

        //Option 2
        boolean match = myCartProducts.stream().anyMatch( product -> product.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match,"Element was not found");

        driver.findElement(By.cssSelector(".totalRow button")).click();



        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
        //Option 1
        //driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

        //Option 2
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

        driver.findElement(By.cssSelector("div[class='actions'] a")).click();

        String  confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."),"Invalid hero message");
        Thread.sleep(5000L);
        driver.quit();



    }
}
