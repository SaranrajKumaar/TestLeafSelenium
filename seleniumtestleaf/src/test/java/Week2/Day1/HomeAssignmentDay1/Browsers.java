package Week2.Day1.HomeAssignmentDay1;

public class Browsers {

    String  launchBrowser(String browserName){
        System.out.println("Browser launched successfully");
        return browserName;

    }
    void loadUrl() {
        System.out.println("Application url loaded successfully");
    }

    public static void main(String[] args) {
        Browsers browser = new Browsers();
        String launchedBrowser = browser.launchBrowser("Chrome");
        System.out.println("Launched browser: "+ launchedBrowser);
        
        browser.loadUrl();
    }

}
