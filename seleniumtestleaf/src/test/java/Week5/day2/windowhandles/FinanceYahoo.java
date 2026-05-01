package Week5.day2.windowhandles;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class FinanceYahoo {

    public static void main(String[] args) {

        WebDriver driver =  new ChromeDriver();
        
        driver.get("https://finance.yahoo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        //cyptro  
        driver.findElement(By.xpath("//a[contains(text(),'Crypto Heatmap')]")).click();
        //table
        driver.findElement(By.xpath("//label[text()='table']/..")).click();

        org.openqa.selenium.WebElement table = driver.findElement(By.xpath("//table"));

        List<WebElement> row = table.findElements(By.xpath(".//tr"));

        for( int i=1; i<row.size();i++){
            String cyptroName = row.get(i).findElement(By.xpath(".//td[2]")).getText();

            System.out.println(cyptroName);
        }
        driver.quit();
    }

}
