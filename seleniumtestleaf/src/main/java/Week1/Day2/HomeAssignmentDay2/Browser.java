package Week1.Day2.HomeAssignmentDay2;

public class Browser {

    public String launchBrowser(String browserName){
        System.out.println("Browser launched successfully");
        return browserName;

    }

   public void loadUrl() {
        System.out.println("Application url loaded successfully");
    }


    public static void main(String[] args) {
        Browser browser = new Browser();
        String launchedBrowser = browser.launchBrowser("Chrome");
        System.out.println("Launched browser: "+ launchedBrowser);
        
        browser.loadUrl();

        
    }

}
