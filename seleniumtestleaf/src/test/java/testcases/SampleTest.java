package testcases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
public class SampleTest {

@Test
    public void sampleTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

}
