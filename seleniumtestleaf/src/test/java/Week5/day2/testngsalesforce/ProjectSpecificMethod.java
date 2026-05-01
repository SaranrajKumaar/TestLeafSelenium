package Week5.day2.testngsalesforce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class ProjectSpecificMethod {

    public WebDriver driver;
    public ChromeOptions options;
    public WebDriverWait wait;

    @Parameters({"url","username","password"})
    @BeforeMethod
    public void preCondtion(String url, String username, String password) {

        options = new ChromeOptions();

        options.addArguments("--user-data-dir=C:\\SeleniumProfile");
        options.addArguments("--profile-directory=Default");
        driver = new ChromeDriver(options);

        driver.get(url);

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("Login")).click();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        


    }

}
