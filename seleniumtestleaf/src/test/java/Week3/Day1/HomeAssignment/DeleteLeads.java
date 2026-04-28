package Week3.Day1.HomeAssignment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import utils.BaseClass;

public class DeleteLeads extends BaseClass {

        public static void main(String[] args) throws Exception {

                DeleteLeads test = new DeleteLeads();

                test.loadTestData("deletelead-testdata.json");
                test.launchBrowser();
                test.login();

                test.createLead();
                test.editLead();
                String leadId = test.findLead();
                test.deleteLead();
                test.verifyDelete(leadId);

                test.closeBrowser();
        }

        // ================= CREATE LEAD =================
        public void createLead() {

                driver.findElement(By.linkText("Leads")).click();
                driver.findElement(By.linkText("Create Lead")).click();

                driver.findElement(By.id("createLeadForm_companyName"))
                                .sendKeys(object.getString("companyName"));

                driver.findElement(By.id("createLeadForm_firstName"))
                                .sendKeys(object.getString("firstName"));

                driver.findElement(By.id("createLeadForm_lastName"))
                                .sendKeys(object.getString("lastName"));

                driver.findElement(By.id("createLeadForm_primaryPhoneNumber"))
                                .sendKeys(object.getString("phoneNumber"));

                driver.findElement(By.id("createLeadForm_departmentName"))
                                .sendKeys(object.getString("department"));

                driver.findElement(By.id("createLeadForm_primaryEmail"))
                                .sendKeys(object.getString("email"));

                new Select(driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId")))
                                .selectByVisibleText(object.getString("state"));

                driver.findElement(By.id("createLeadForm_importantNote"))
                                .sendKeys(object.getString("oldDescription"));

                driver.findElement(By.name("submitButton")).click();

                System.out.println("Lead created");
        }

        // ================= EDIT LEAD =================
        public void editLead() {

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[text()='Edit']"))).click();

                WebElement desc = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("updateLeadForm_importantNote")));

                desc.clear();
                desc.sendKeys(object.getString("newDescription"));

                driver.findElement(By.name("submitButton")).click();

                System.out.println("Lead edited");
        }

        // ================= FIND LEAD =================
        public String findLead() {

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.linkText("Find Leads"))).click();

                driver.findElement(By.xpath("//span[text()='Phone']")).click();

                driver.findElement(By.name("phoneNumber"))
                                .sendKeys(object.getString("phoneNumber"));

                driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.className("x-grid3-body")));

                boolean isNoRecord = driver.findElements(
                                By.xpath("//*[contains(text(),'No records')]")).size() > 0;

                if (isNoRecord) {
                        System.out.println("No leads found");
                        return null;
                }

                WebElement firstLead = driver.findElement(
                                By.xpath("(//a[contains(@href,'viewLead')])[1]"));

                String leadId = firstLead.getText();
                System.out.println("Lead ID: " + leadId);

                firstLead.click();

                return leadId;
        }

        // ================= DELETE =================
        public void deleteLead() {
                driver.findElement(By.linkText("Delete")).click();
                System.out.println("Lead deleted");
        }

        // ================= VERIFY DELETE =================
        public void verifyDelete(String leadId) {

                driver.findElement(By.linkText("Find Leads")).click();

                driver.findElement(By.name("id")).sendKeys(leadId);

                driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

                WebElement noRecord = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//*[contains(text(),'No records to display')]")));

                assertTrue(noRecord.isDisplayed());
                System.out.println("Verified lead deletion");
        }
}