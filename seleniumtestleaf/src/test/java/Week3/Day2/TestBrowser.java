package Week3.Day2;

public class TestBrowser {

    public static void main(String[] args) {
        Chrome chrome = new Chrome();
        chrome.openURL();
        chrome.openIncognitoMode();
        chrome.clearCache();
        chrome.closeBrowser();

        System.out.println("********************");

        Edge edge = new Edge();
        edge.openURL();
        edge.takeSnap();
        edge.clearCookies();
        edge.closeBrowser();

        System.out.println("********************");

        Safari safari = new Safari();
        safari.openURL();
        safari.readMode();
        safari.fullScreenMode();
        safari.closeBrowser();
    }

}
