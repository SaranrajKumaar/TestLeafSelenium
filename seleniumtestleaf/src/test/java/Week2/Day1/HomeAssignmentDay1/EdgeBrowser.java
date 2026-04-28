package Week2.Day1.HomeAssignmentDay1;



public class EdgeBrowser {

    public static void main(String[] args) {
        Browsers browser = new Browsers();
        String launchedBrowser = browser.launchBrowser("Edge");
        System.out.println("Launched browser: "+ launchedBrowser);
        
        browser.loadUrl();
    }

}
