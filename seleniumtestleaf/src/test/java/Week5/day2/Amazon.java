package Week5.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class Amazon {

    public static void main(String[] args) throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Actions ac = new Actions(driver);

        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
        ac.moveToElement(search).click().sendKeys("oneplus 9 pro").perform();

        driver.findElement(By.id("nav-search-submit-button")).click();

        // Price
        String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
        System.out.println("Price: " + price);

        // Ratings
        String ratings = driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]")).getText();
        System.out.println("Ratings: " + ratings);

        // Click product
        driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();

        // Switch window
        String parent = driver.getWindowHandle();
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Title
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle"))).getText();
        System.out.println("Title: " + title);

        // Screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("./screenshot/amazon.png");
        FileUtils.copyFile(src, dest);

        //rating 
        String rating =driver.findElement(By.xpath("(//span[@id=\"acrPopover\"]//span[contains(@class,'a-color-base')])[1]")).getText();
        System.out.println("Rating: " + rating);

        // Add to cart
        driver.findElement(By.id("add-to-cart-button")).click();

        // Wait subtotal
        String subtotal = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[contains(@class,'subtotal')]")))
                .getText();

        System.out.println("Subtotal: " + subtotal);

        // Validation
        if (subtotal.contains(price)) {
            System.out.println(" Price matched");
        } else {
            System.out.println(" Price mismatch");
        }

        driver.quit();
    }
}