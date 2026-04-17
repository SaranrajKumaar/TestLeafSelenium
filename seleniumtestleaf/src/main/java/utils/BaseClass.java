package utils;

import java.time.Duration;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    public WebDriver driver;
    public WebDriverWait wait;
    public JSONObject object;

    // Correct: return JSONObject
    public JSONObject loadTestData(String fileName) throws Exception {
        object = JsonReader.getTestData(fileName);
        return object;
    }

    //  Fixed 'public'
    public void launchBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://leaftaps.com/opentaps/control/main");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login() {
        driver.findElement(By.id("username"))
                .sendKeys(object.getString("username"));

        driver.findElement(By.id("password"))
                .sendKeys(object.getString("password"));

        driver.findElement(By.className("decorativeSubmit")).click();
        driver.findElement(By.linkText("CRM/SFA")).click();

        System.out.println("Login successful");
    }

    public void closeBrowser() {
    if (driver != null) {
        driver.quit();
    }
}
}