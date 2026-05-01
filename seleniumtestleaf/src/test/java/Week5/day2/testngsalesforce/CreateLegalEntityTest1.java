package Week5.day2.testngsalesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class CreateLegalEntityTest1 extends  ProjectSpecificMethod{
@Test
    public void createLegalEntity(){

  wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("button[title=\"App Launcher\"]")))).click();

        driver.findElement(By.xpath("(//input[@class=\"slds-input\"])[2]")).sendKeys("Legal Entities");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='Legal Entities']"))).click();

        driver.findElement(By.xpath("//a[@class=\"forceActionLink\"]")).click();

        String name ="Salesforce Automation by Saranraj";
        driver.findElement(By.cssSelector("input[name=\"Name\"]")).sendKeys(name);

        driver.findElement(By.xpath("//button[@name=\"SaveEdit\"]")).click();

        String actutal =driver.findElement(By.cssSelector("lightning-formatted-text[slot=\"primaryField\"]")).getText();

        assertTrue(actutal.equalsIgnoreCase(name));

        //drop Down 
        driver.findElement(By.xpath("//span[text()='Show more actions']/..")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Delete']"))).click();

        driver.findElement(By.xpath("button[title='Delete'])")).click();
        
    }


}