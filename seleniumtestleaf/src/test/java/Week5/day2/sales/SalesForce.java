package Week5.day2.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SalesForce {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\SeleniumProfile");
        options.addArguments("--profile-directory=Default");

        //  Pass options here
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://login.salesforce.com/");

        //  First run only (for OTP setup)
        try {
            driver.findElement(By.id("username")).sendKeys("saranraj.kumaar.5b152976b585@agentforce.com");
            driver.findElement(By.id("password")).sendKeys("saranraj@123");
            driver.findElement(By.id("Login")).click();
        } catch (Exception e) {
            System.out.println("Already logged in via profile ");
        }
    }
}