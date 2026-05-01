package Week5.day2.testngsalesforce;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateLegalEntityTest2 extends ProjectSpecificMethod {

    @Test
    public void validateMandatoryField() {

        // App Launcher
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        driver.findElement(By.xpath("//button[text()='View All']")).click();

        // Search Legal Entities
        driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']"))
                .sendKeys("Legal Entities");

        driver.findElement(By.xpath("//p[text()='Legal Entities']")).click();

        // Click New
        driver.findElement(By.xpath("//div[@title='New']")).click();

        // Fill other fields
        driver.findElement(By.xpath("//input[@name='CompanyName']"))
                .sendKeys("TestLeaf");

        driver.findElement(By.xpath("//textarea[@name='Description']"))
                .sendKeys("Salesforces");

        // Select Status
        driver.findElement(By.xpath("//button[@aria-label='Status']")).click();
        driver.findElement(By.xpath("//span[text()='Active']")).click();

        // Save
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

        // Verify error
        String error = driver.findElement(
                By.xpath("//div[contains(text(),'Complete this field')]"))
                .getText();

        Assert.assertTrue(error.contains("Complete this field"));
        System.out.println(" Mandatory field validation working");
    }
}
