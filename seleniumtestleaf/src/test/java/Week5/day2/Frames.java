package Week5.day2;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Frames {

    public static void main(String[] args) {
        String name = "Saranraj";

        // 1. Launch browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2. Load URL
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");

        // 3. Switch to frame
        driver.switchTo().frame("iframeResult");

        driver.findElement(By.xpath(" //button[contains(text(),'Try it')]")).click();

        Alert alert = driver.switchTo().alert();

        alert.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        alert.sendKeys(Keys.DELETE.toString());

        alert.sendKeys(name);
        alert.accept();

        String message = driver.findElement(By.xpath("//p[@id=\"demo\"]")).getText();

        assertTrue(message.equals("Hello " + name + "! How are you today?"));

        driver.quit();
        

    }

}
