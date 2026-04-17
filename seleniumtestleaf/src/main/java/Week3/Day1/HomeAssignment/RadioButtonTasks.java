package Week3.Day1.HomeAssignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.testng.Assert.assertTrue;

public class RadioButtonTasks {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.leafground.com/radio.xhtml");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // =========================================================
        //  Select 'Your most favourite browser'
        // =========================================================

        WebElement firefoxLabel = driver.findElement(
                By.xpath("//label[normalize-space()='Firefox']")
        );

        WebElement firefoxBox = driver.findElement(
                By.xpath("//label[normalize-space()='Firefox']/preceding-sibling::div//div[contains(@class,'ui-radiobutton-box')]")
        );

        firefoxLabel.click();

        assertTrue(firefoxBox.getAttribute("class").contains("ui-state-active"));
        System.out.println("Firefox selected successfully");

        // =========================================================
        // Click same radio again (verify unselect behavior)
        // =========================================================

        firefoxLabel.click();

        boolean isStillSelected = firefoxBox.getAttribute("class").contains("ui-state-active");

        if (isStillSelected) {
            System.out.println("Radio button cannot be unselected (Expected behavior)");
        } else {
            System.out.println("Radio button got unselected");
        }

        // =========================================================
        //  Identify default selected radio button
        // =========================================================

        List<WebElement> defaultRadios = driver.findElements(
                By.xpath("//table[@id='j_idt87:console2']//div[contains(@class,'ui-radiobutton-box') and contains(@class,'ui-state-active')]")
        );

        WebElement selected = defaultRadios.get(0);

        String defaultOption = selected.findElement(
                By.xpath("./following::label[1]")
        ).getText();

        System.out.println("Default selected radio: " + defaultOption);

        // =========================================================
        // 4️⃣ Select Age Group (21-40 Years) if not selected
        // =========================================================

        WebElement ageLabel = driver.findElement(
                By.xpath("//label[normalize-space()='21-40 Years']")
        );

        WebElement ageBox = driver.findElement(
                By.xpath("//label[normalize-space()='21-40 Years']/preceding-sibling::div//div[contains(@class,'ui-radiobutton-box')]")
        );

        if (!ageBox.getAttribute("class").contains("ui-state-active")) {

            System.out.println("21-40 not selected, selecting now...");
            ageLabel.click();

            // re-fetch
            ageBox = driver.findElement(
                    By.xpath("//label[normalize-space()='21-40 Years']/preceding-sibling::div//div[contains(@class,'ui-radiobutton-box')]")
            );

            assertTrue(ageBox.getAttribute("class").contains("ui-state-active"));
            System.out.println("21-40 Years selected");

        } else {
            System.out.println("21-40 Years already selected");
        }

        // =========================================================
        driver.quit();
    }
}