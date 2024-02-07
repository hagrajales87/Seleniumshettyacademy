package testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pageObjects.LandingPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver setUp() throws IOException {
        //properties class
        Properties prop = new Properties();
        //FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
        //        "src//main//java//resources//GlobalData//GlobalData.properties");
        FileInputStream file = new FileInputStream("src/main/java/resources/GlobalData/GlobalData.properties");
        prop.load(file);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //Firefox
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            //Edge
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        driver = setUp();
        landingPage = new LandingPage(driver);
        landingPage.goTo();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }


    public String getScreenshot(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                File file = new File("src/main/java/resources/screenShots"+result.getName()+".png");
                Files.move(src, file);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return  "src/main/java/resources/screenShots"+result.getName()+".png";

    }

    //Extent Reports
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

        return data;
    }
}
