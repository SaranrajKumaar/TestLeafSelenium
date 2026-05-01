package Week5.day2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import utils.BaseClass;

public class MergeContacts extends BaseClass {

    public static void main(String[] args) throws Exception {

        MergeContacts merge = new MergeContacts();

        merge.loadTestData("mergecontacts-testdata.json");
        merge.launchBrowser();
        merge.login();

        merge.navigateToMergeContacts();
        merge.selectFromContact();
        merge.selectToContact();
        merge.mergeContacts();
        merge.verifyMerge();

        merge.closeBrowser();
    }

    //  Navigation
    public void navigateToMergeContacts() {
        driver.findElement(By.linkText("Contacts")).click();
        driver.findElement(By.linkText("Merge Contacts")).click();
    }

    //  Select From Contact
    public void selectFromContact() {
        driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
        switchToNewWindow();
        driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();
        switchToMainWindow();
    }

    //  Select To Contact
    public void selectToContact() {
        driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
        switchToNewWindow();
        driver.findElement(By.xpath("(//a[@class='linktext'])[2]")).click();
        switchToMainWindow();
    }

    //  Merge + Alert
    public void mergeContacts() {
        driver.findElement(By.linkText("Merge")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    //  Verification
    public void verifyMerge() {
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        if (title.contains("View Contact")) {
            System.out.println(" Merge Successful");
        } else {
            System.out.println(" Merge Failed");
        }
    }

    //  Reusable Window Methods
    String mainWindow;

    public void switchToNewWindow() {
        mainWindow = driver.getWindowHandle();
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
    }

    public void switchToMainWindow() {
        driver.switchTo().window(mainWindow);
    }
}
