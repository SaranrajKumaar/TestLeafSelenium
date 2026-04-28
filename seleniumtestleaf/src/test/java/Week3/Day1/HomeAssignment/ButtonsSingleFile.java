package Week3.Day1.HomeAssignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.assertTrue;
public class ButtonsSingleFile {

    public static void main(String[] args) {

        // ================= SETUP =================
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://leafground.com/button.xhtml");

        // ======== 1. CLICK + VERIFY TITLE =========
        driver.findElement(By.xpath("//span[text()='Click']/parent::button")).click();

        String title = driver.getTitle();
        System.out.println("Title: " + title);

        assertTrue(title.toLowerCase().contains("dashboard"));

        driver.navigate().back();

        // 2. CHECK DISABLED 
        WebElement disabledBtn = driver.findElement(
                By.xpath("//span[text()='Disabled']/parent::button"));

        System.out.println("Is Disabled: " + !disabledBtn.isEnabled());

        //  3. POSITION 
        WebElement submitBtn = driver.findElement(
                By.xpath("//span[text()='Submit']/parent::button"));

        int x = submitBtn.getLocation().getX();
        int y = submitBtn.getLocation().getY();

        System.out.println("Submit Button Position → X: " + x + ", Y: " + y);

        // ===== 4. COLOR =========
        WebElement saveBtn = driver.findElement(
                By.xpath("//span[text()='Save']/parent::button"));

        String color = saveBtn.getCssValue("background-color");
        System.out.println("Save Button Color: " + color);

        // ===== 5. SIZE =======
        WebElement sizeBtn = driver.findElement(
                By.xpath("(//span[text()='Submit']/parent::button)[2]"));

        int height = sizeBtn.getSize().getHeight();
        int width = sizeBtn.getSize().getWidth();

        System.out.println("Button Size → Height: " + height + ", Width: " + width);

        // ========= 6. MOUSE HOVER ============
        WebElement successBtn = driver.findElement(
                By.xpath("//span[text()='Success']/parent::button"));

        String beforeColor = successBtn.getCssValue("background-color");

        Actions action = new Actions(driver);
        action.moveToElement(successBtn).perform();

        String afterColor = successBtn.getCssValue("background-color");

        System.out.println("Before Hover: " + beforeColor);
        System.out.println("After Hover: " + afterColor);

        // ======= 7. COUNT ROUNDED BUTTONS ==========
        List<WebElement> buttons = driver.findElements(
                By.xpath("//button[contains(@class,'rounded-button')]"));

        System.out.println("Rounded Buttons Count: " + buttons.size());

        // ========== CLOSE ============
        driver.quit();
    }
}