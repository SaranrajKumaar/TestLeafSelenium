package Week2.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class LoginPage {
        public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/login/");
        //email 
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("testleaf.2023@gmail.com");
        //password
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("TestLeaf@123");

        driver.findElement(By.xpath("(//span[contains(text(),'Log in')])[2]/..")).click();
}
}
