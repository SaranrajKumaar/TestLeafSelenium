package Week2.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import utils.BaseClass;

public class SelectClass extends BaseClass {

    public static void main(String[] args) throws Exception {

        SelectClass test = new SelectClass();

        test.loadTestData("selectclass-testdata.json");
        test.launchBrowser();
        test.login();

        test.navigateToCreateAccount();
        test.fillForm();
        test.handleDuplicateAndSubmit();
        test.validateAccount();

        test.closeBrowser();
    }

    String expectedName;
    String newName = "";
    boolean duplicateHandled = false;

    // ================= NAVIGATION =================
    public void navigateToCreateAccount() {
        driver.findElement(By.linkText("Accounts")).click();
        driver.findElement(By.linkText("Create Account")).click();
    }

    // ================= FORM =================
    public void fillForm() {

        expectedName = object.getString("accountName");

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
    }

    // ================= SUBMIT + DUPLICATE =================
    public void handleDuplicateAndSubmit() {

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(),'Duplicates found:')]")));

            if (message.isDisplayed()) {

                System.out.println("Duplicate found! Updating name...");

                duplicateHandled = true;

                newName = expectedName + "_" + System.currentTimeMillis();

                WebElement accountField = driver.findElement(By.id("accountName"));
                accountField.clear();
                accountField.sendKeys(newName);

                driver.findElement(By.xpath("//input[@type='submit']")).click();
            }

        } catch (Exception e) {
            System.out.println("No duplicate found");
        }
    }

    // ================= VALIDATION =================
    public void validateAccount() {

        if (duplicateHandled) {
            expectedName = newName;
        }

        WebElement accountText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//span[@class='tabletext'])[3]")));

        String actualText = accountText.getText();
        String actualName = actualText.split("\\(")[0].trim();

        System.out.println("Expected: " + expectedName);
        System.out.println("Actual: " + actualName);

        if (actualName.equals(expectedName)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account name mismatch!");
        }
    }
}