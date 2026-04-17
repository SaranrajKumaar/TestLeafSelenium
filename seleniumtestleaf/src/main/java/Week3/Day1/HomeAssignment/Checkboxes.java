package Week3.Day1.HomeAssignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertTrue;

public class Checkboxes {

        public static void main(String[] args) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--guest");

                WebDriver driver = new ChromeDriver(options);
                driver.get("https://leafground.com/checkbox.xhtml");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // ===================== 1. BASIC CHECKBOX =====================
                WebElement basic = driver.findElement(By.xpath("//span[text()='Basic']"));
                basic.click();

                WebElement basicInput = driver.findElement(
                                By.xpath("//span[text()='Basic']/preceding-sibling::div//input"));
                assertTrue(basicInput.isSelected());
                System.out.println("Basic checkbox selected");

                // ===================== 2. AJAX NOTIFICATION =====================
                WebElement ajaxCheckbox = driver.findElement(
                                By.xpath("(//span[contains(text(),'Ajax')])/parent::div"));
                ajaxCheckbox.click();

                WebElement message = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//span[contains(@class,'ui-growl-title')]")));

                assertTrue(message.getText().equals("Checked") || message.getText().equals("Unchecked"));
                System.out.println("Notification: " + message.getText());

                // ===================== 3. LANGUAGE CHECKBOX =====================
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Java']"))).click();

                WebElement javaCheckbox = driver.findElement(
                                By.xpath("//label[text()='Java']/preceding-sibling::div//div[contains(@class,'ui-chkbox-box')]"));

                assertTrue(javaCheckbox.getAttribute("class").contains("ui-state-active"));
                System.out.println("Java language selected");

                // ===================== 4. TRI-STATE CHECKBOX =====================
                WebElement triState = driver.findElement(By.id("j_idt87:ajaxTriState"));

                for (int i = 0; i < 3; i++) {
                        triState.click();

                        WebElement icon = driver.findElement(
                                        By.xpath("//div[@id='j_idt87:ajaxTriState']//span"));

                        String state = icon.getAttribute("class");

                        if (state.contains("ui-icon-check")) {
                                System.out.println("State: CHECKED");
                        } else if (state.contains("ui-icon-closethick")) {
                                System.out.println("State: CROSS");
                        } else {
                                System.out.println("State: DEFAULT");
                        }
                }

                // ===================== 5. TOGGLE SWITCH =====================
                WebElement toggle = driver.findElement(
                                By.xpath("//div[contains(@class,'ui-toggleswitch')]"));
                toggle.click();

                WebElement toggleMsg = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//span[contains(@class,'ui-growl-title')]")));

                assertTrue(toggleMsg.getText().equals("Checked") || toggleMsg.getText().equals("Unchecked"));
                System.out.println("Toggle message: " + toggleMsg.getText());

                // ===================== 6. DISABLED CHECKBOX =====================
                WebElement disabled = driver.findElement(By.id("j_idt87:j_idt102_input"));
                assertTrue(!disabled.isEnabled());
                System.out.println("Checkbox is disabled");

                // ===================== 7. MULTI SELECT DROPDOWN =====================
                // Open dropdown
                // Open dropdown
                WebElement dropdown = driver.findElement(By.xpath("//ul[@data-label='Cities']"));
                dropdown.click();

                // Wait for visible panel (IMPORTANT FIX)
                WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[contains(@class,'ui-selectcheckboxmenu-panel') and contains(@style,'display: block')]")));

                // Now select cities INSIDE panel
                String[] cities = { "London", "Paris", "Berlin" };

                for (String city : cities) {
                        WebElement option = panel.findElement(
                                        By.xpath(".//label[normalize-space()='" + city + "']"));
                        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                }
                // Validate selected values
                List<WebElement> selected = driver.findElements(
                                By.xpath("//li[contains(@class,'ui-selectcheckboxmenu-checked')]"));

                assertTrue(selected.size() == cities.length);
                System.out.println("All cities selected successfully");

                // ===================== CLOSE =====================
                driver.quit();
        }
}