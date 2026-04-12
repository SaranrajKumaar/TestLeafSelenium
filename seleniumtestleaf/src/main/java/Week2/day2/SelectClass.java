package Week2.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.JsonReader;
import org.json.JSONObject;

public class SelectClass {
    public static void main(String[] args) throws Exception {

        JSONObject object = JsonReader.getTestData();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");

        WebDriver driver = new ChromeDriver(options);
        driver.get("http://leaftaps.com/opentaps/control/main");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ================= LOGIN =================
        driver.findElement(By.id("username"))
                .sendKeys(object.getString("username"));

        driver.findElement(By.id("password"))
                .sendKeys(object.getString("password"));

        driver.findElement(By.className("decorativeSubmit")).click();

        driver.findElement(By.linkText("CRM/SFA")).click();

        // ================= NAVIGATION =================
        driver.findElement(By.linkText("Accounts")).click();
        driver.findElement(By.linkText("Create Account")).click();

        // ================= FORM DATA =================
        String expectedName = object.getString("accountName");

        driver.findElement(By.id("accountName")).sendKeys(expectedName);

        driver.findElement(By.name("description"))
                .sendKeys(object.getString("description"));

        driver.findElement(By.id("numberEmployees"))
                .sendKeys(object.getString("numberEmployees"));

        driver.findElement(By.id("officeSiteName"))
                .sendKeys(object.getString("siteName"));

        new Select(driver.findElement(By.name("industryEnumId")))
                .selectByValue(object.getString("industry"));

        new Select(driver.findElement(By.name("ownershipEnumId")))
                .selectByVisibleText(object.getString("ownership"));

        new Select(driver.findElement(By.name("dataSourceId")))
                .selectByIndex(object.getInt("sourceIndex"));

        new Select(driver.findElement(By.id("initialTeamPartyId")))
                .selectByValue(object.getString("state"));

        new Select(driver.findElement(By.id("marketingCampaignId")))
                .selectByVisibleText(object.getString("campaign"));

        // ================= SUBMIT =================
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        boolean duplicateHandled = false;
        String newName = "";

        try {
            // Check duplicate message
            WebElement message = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(text(),'Duplicates found:')]")));

            if (message.isDisplayed()) {

                System.out.println("Duplicate found! Updating name...");

                duplicateHandled = true;

                newName = expectedName + "_" + System.currentTimeMillis();

                WebElement accountNameField = driver.findElement(By.id("accountName"));
                accountNameField.clear();
                accountNameField.sendKeys(newName);

                driver.findElement(By.xpath("//input[@type='submit']")).click();
            }

        } catch (Exception e) {
            System.out.println("No duplicate found");
        }

        // ================= ASSERTION =================

        // Decide expected name
        if (duplicateHandled) {

            expectedName = newName;
        }

        // Get actual name from
        WebElement accountText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//span[@class=\"tabletext\"])[3]")));

        String actualText = accountText.getText();

        // Remove (ID) part
        String actualName = actualText.split("\\(")[0].trim();

        System.out.println("Expected: " + expectedName);
        System.out.println("Actual: " + actualName);

        // Validate
        if (actualName.equals(expectedName)) {

            System.out.println("Account created successfully!.");
        } else {
            System.out.println("Account name mismatch!");
        }

        // ================= CLOSE =================
        driver.quit();
    }
}