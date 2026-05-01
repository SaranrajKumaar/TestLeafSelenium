package Week5.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertTrue;

public class BigBasketApp {

    public static void main(String[] args) throws IOException {


        String originalProductName = "Tamil Ponni Boiled Rice";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.bigbasket.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click Shop by Category
        WebElement shopByCategory = driver.findElement(By.xpath("//button[@id=\"headlessui-menu-button-:Ramkj6:\"]"));
        shopByCategory.click();
        Actions ac = new Actions(driver);
        // Hover actions
        WebElement foodgrains = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Oil & Masala')])[2]")));
        ac.moveToElement(foodgrains).perform();

        WebElement rice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Rice Products')])[1]")));
        ac.moveToElement(rice).perform();

        WebElement boiled = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Steam Rice')]")));
        ac.moveToElement(boiled).click().perform();

        // Get all products
        List<WebElement> productList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(@class,'SKUDeck')]")));
           
        for (WebElement product : productList) {

            String brandName = product.findElement(By.xpath(".//span[contains(@class,'BrandName')]")).getText();
            System.out.println("Brand Name: " + brandName);

            if (brandName.equalsIgnoreCase("bb Royal")) {

                String productName = product.findElement(By.xpath(".//div[contains(@class,'break-words')]")).getText();

                if (productName.equalsIgnoreCase(originalProductName)) {

                    WebElement particularsProduct = driver.findElement(By.xpath("(//h3[text()=\"" + originalProductName + "\"])[1]"));
                    // Scroll into view
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", particularsProduct);

                    // Open dropdown
                    WebElement dropdown = product.findElement(By.xpath(".//button[contains(@class,'Button')]"));
                    dropdown.click();

                    // Wait for dropdown options
                      List<WebElement> sizes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'kg')]")));

                    // Select 5 kg
                    for (WebElement size : sizes) {
                        if (size.getText().contains("5 kg")) {
                            size.click();
                            break;
                    }

                }

                    // Get price
                    String price = product.findElement(By.xpath(".//span[contains(@class,'hpkXHR')]")).getText();
                    System.out.println("Price: " + price);

                    // Click Add

                    WebElement addButton = product.findElement(By.xpath(".//button[contains(@class,'Add')]"));
                    addButton.click();

                    //message 
                    String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toast')]//p"))).getText();

                    assertTrue(message.equalsIgnoreCase("An item has been added to your basket successfully"));


                    TakesScreenshot ts =(TakesScreenshot) driver;
                    File src =ts.getScreenshotAs(OutputType.FILE);

                    File path = new File("./screenshot/bigbasket.png");
                    FileUtils.copyFile(src,path);
                    break;
                }
            }
        }

  


    }
}