package Week6.Marathon;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TataCliqAssignment {

    public static void main(String[] args) throws Exception {

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--guest");

        WebDriver driver = new ChromeDriver(option);

        driver.get("https://www.tatacliq.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions ac = new Actions(driver);

        // Close popup
        try {
            driver.findElement(By.id("moe-dontallow_button")).click();
        } catch (Exception e) {}

        // Hover Brands
        WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Brands']")));
        ac.moveToElement(brand).perform();

        // Hover Watches
        WebElement watch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Watches & Accessories']")));
        ac.moveToElement(watch).perform();

        // Click Casio
        driver.findElement(By.xpath("//div[text()='Casio']")).click();

        // Sort
        WebElement sort = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("select")));
        new Select(sort).selectByVisibleText("New Arrivals");

        // Filter Men
        driver.findElement(By.xpath("(//div[text()='Men'])[1]")).click();

        // Get Prices
        List<WebElement> priceElements = driver.findElements(
                By.xpath("//h3[contains(@class,'price')]"));

        List<String> prices = new ArrayList<>();

        for (WebElement price : priceElements) {
            String p = price.getText().replaceAll("[^0-9]", "");
            prices.add(p);
            System.out.println("Price: " + p);
        }

        String listPrice = prices.get(0);

        // Click first product
        driver.findElement(By.xpath("(//div[contains(@class,'ProductDescription__content')])[1]")).click();

        // Switch window
        String parent = driver.getWindowHandle();
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
            }
        }

        // Get product price
        WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'ProductDetailsMainCard')]//h3")));

        String compare = actual.getText().replaceAll("[^0-9]", "");

        // Compare
        if (compare.equals(listPrice)) {
            System.out.println(" Price matched");
        } else {
            System.out.println(" Price mismatch");
        }

        // Add to cart
        driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();

        // Cart
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'myBagShow')]")));
        cart.click();

        // Screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File path = new File("./screenshot/tatacliq.png");
        FileUtils.copyFile(src, path);

        System.out.println("Screenshot taken");

          driver.quit();

    }

}