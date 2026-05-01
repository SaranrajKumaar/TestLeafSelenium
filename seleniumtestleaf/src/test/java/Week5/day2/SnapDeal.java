package Week5.day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SnapDeal {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.snapdeal.com/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        Actions ac = new Actions(driver);

        //men's fashion
        WebElement mensFashion = driver.findElement(By.xpath("//div[text()=\"Men's Fashion\"]"));
        ac.moveToElement(mensFashion).perform();

        WebElement sportsShoes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Sports Shoes']")));

        ac.moveToElement(sportsShoes).click().perform();

        List<WebElement> categoryList = driver.findElements(By.xpath("//ul[@id=\"js-255-nav\"]//a//div[1]"));

        for (WebElement category : categoryList) {
            String categoryName = category.getText();
            System.out.println("Category Name: " + categoryName);

            if (categoryName.equalsIgnoreCase("Training Shoes")) {
                category.click();
                break;
            }
        }

        //sortBy low to high 
        driver.findElement(By.xpath("//div[contains(@class,\"sort-drop\")]")).click();

        List<WebElement> sortOptions = driver.findElements(By.xpath("//ul[@class='sort-value']/li"));

        for (WebElement option : sortOptions) {
            String optionName = option.getText().trim();

            System.out.println("Option Name: " + optionName);
            if (optionName.equalsIgnoreCase("Price Low To High")) {
                option.click();
                break;
            }

        }

        //from Price
        WebElement fromVal = driver.findElement(By.name("fromVal"));
        fromVal.clear();
        fromVal.sendKeys("500");

        WebElement toVal = driver.findElement(By.name("toVal"));
        toVal.clear();
        toVal.sendKeys("700");

        //go 
        driver.findElement(By.xpath("//div[contains(@class,\"price-go-arrow\")]")).click();

        WebElement colorFilter = driver.findElement(
                By.xpath("//label[contains(@for,'Color_s-White%20%26%20Blue')]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", colorFilter);
        wait.until(ExpectedConditions.elementToBeClickable(colorFilter)).click();

//assert them 
// Get all applied filters
        List<WebElement> appliedFilters = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='filters']//a")
        )
        );

        boolean priceVerified = false;
        boolean colorVerified = false;

        for (WebElement filter : appliedFilters) {

            String text = filter.getText().trim();
            System.out.println("Applied Filter: " + text);

            if (text.contains("500") && text.contains("700")) {
                priceVerified = true;
            }

            if (text.equalsIgnoreCase("White & Blue")) {
                colorVerified = true;
            }
        }

        // Final assertion
        if (priceVerified && colorVerified) {
            System.out.println(" Filters applied correctly");
        } else {
            System.out.println(" Filters verification failed");
        }

        //quick view 
        // Get all products
        List<WebElement> products = driver.findElements(
                By.xpath("//div[contains(@class,'product-tuple-listing')]")
        );

        // Check product present
        if (products.size() > 0) {

            WebElement firstProduct = products.get(0);

            //  Get product name
            String productName = firstProduct.findElement(
                    By.xpath(".//p[@class='product-title']"))
                    .getText();

            System.out.println("Product Name: " + productName);

            //  Move hover
            ac.moveToElement(firstProduct).perform();

            //  Store Quick View button in variable
            WebElement quickViewBtn = firstProduct.findElement(
                    By.xpath(".//div[contains(text(),'Quick View')]"));

            // Wait until visible
            wait.until(ExpectedConditions.visibilityOf(quickViewBtn));

            //  Click Quick View
            quickViewBtn.click();

            System.out.println("Quick View clicked");

        } else {
            System.out.println(" No products found");
        }

        // print and price and discount
        String price = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'product-price')]//span[@class='payBlkBig']")))
                .getText().trim();

// Discount
        String discount = driver.findElement(
                By.xpath("//div[contains(@class,'product-price')]//span[contains(@class,'percent-desc')]"))
                .getText().trim();

        System.out.println("Price: Rs. " + price);
        System.out.println("Discount: " + discount);


        driver.quit();

    }

}
