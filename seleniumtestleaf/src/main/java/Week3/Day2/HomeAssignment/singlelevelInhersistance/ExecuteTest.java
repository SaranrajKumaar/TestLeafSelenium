package Week3.Day2.HomeAssignment.singlelevelInhersistance;

public class ExecuteTest {
public static void main(String[] args) {

        // Create object of subclass
        LoginTestData login = new LoginTestData();

        // Call superclass methods
        login.enterCredentials();
        login.navigateToHomePage();

        // Call subclass methods
        login.enterUsername();
        login.enterPassword();
}
}
