# Selenium TestLeaf - Week 1

This repository contains simple Java exercises for Week 1 (Day 1 and Day 2).
This README documents the purpose and brief explanations of the Day 1 and Day 2 source files.

## How to build
- Build the project: `mvn clean compile`
- Run tests or classes from your IDE, or use Maven surefire to run specific test classes.

## Day 1 (Week1/Day1)

- `HelloTestLeaf.java`: A starter/demo class used to introduce the project structure and basic Java program flow. It demonstrates a simple main method or a basic test harness used to print output or verify initial setup.

- `HomeAssignmentDay1/Chrome.java`: A helper class that demonstrates launching Chrome via WebDriver. It contains browser setup and teardown logic and can be used to run simple UI checks in Chrome.

- `HomeAssignmentDay1/Firefox.java`: Similar to `Chrome.java`, this class demonstrates launching Firefox using WebDriver. It shows how to initialize Firefox driver, open a page, and close the browser.

Notes for Day 1:
- These classes are intended to show basic Selenium browser setup and simple interaction scaffolding. Use them as templates when adding tests for different browsers.

## Day 2 (Week1/Day2)

- `FindOddNumbers.java`: Contains logic to iterate over a range of integers and identify/print the odd numbers. Typical implementation uses `% 2 != 0` to test oddness.

- `Operators.java`: Demonstrates Java operators (arithmetic, relational, logical). It usually contains small examples to show operator precedence and usage.

- `HomeAssignmentDay2/Browser.java`: A lightweight browser-launching helper similar to Day 1 helpers but structured for Day 2 assignments. Contains code to pick and start a browser driver.

- `HomeAssignmentDay2/CheckNumberIsPositive.java`: A simple utility or demo that checks whether a given integer is positive, negative, or zero and prints the result.

- `HomeAssignmentDay2/FibonacciSeries.java`: Implements a Fibonacci series generator. Typical approaches include iterative loop-based generation or recursion; the class prints or returns the first N Fibonacci numbers.

- `HomeAssignmentDay2/Library.java`: A utility class that may contain reusable helper methods used across Day 2 assignments (for example, input handling or small utilities used by other classes).

Notes for Day 2:
- These files are small algorithmic and utility exercises intended to practice control flow, loops, and simple algorithm implementations in Java.

## Where to look
- Source files are under `src/main/java/Week1` and the HomeAssignment subfolders as shown in the repository tree.

If you want, I can also open each source file and add brief inline comments or more precise explanations extracted from the actual code. Would you like me to do that next?

## Week 2 (Week2/day2)

- SelectClass.java: A Selenium end-to-end example that reads test data, opens the LeafTaps application, fills the Create Account form, handles a duplicate account case, and verifies the created account name.

Step-by-step explanation for [seleniumtestleaf/src/main/java/Week2/day2/SelectClass.java](seleniumtestleaf/src/main/java/Week2/day2/SelectClass.java#L1):

1. **Load test data:** Uses `JsonReader.getTestData()` to read JSON test data (credentials and form values) from `src/main/resources/selectclass-testdata.json`.
2. **Browser setup:** Creates `ChromeOptions` with `--guest` and starts `ChromeDriver`.
3. **Open application:** Navigates to `http://leaftaps.com/opentaps/control/main` and maximizes the window.
4. **Login:** Finds `#username` and `#password` fields and submits the login button (`.decorativeSubmit`), then clicks the `CRM/SFA` link.
5. **Navigate to Create Account:** Clicks `Accounts` → `Create Account` to open the account creation form.
6. **Fill form fields:** Reads values from JSON and fills fields:
	- `accountName`, `description`, `numberEmployees`, `officeSiteName` using `sendKeys`.
	- Dropdowns handled with `new Select(...)`: `industry` (`selectByValue`), `ownership` (`selectByVisibleText`), `dataSourceId` (`selectByIndex`), `initialTeamPartyId` (`selectByValue`), `marketingCampaignId` (`selectByVisibleText`).
7. **Submit form:** Clicks the submit input to save the account.
8. **Handle duplicates:** Waits up to 10s for a message containing `Duplicates found:`. If found, it appends a timestamp to the account name, clears the `accountName` field, re-submits, and marks that a duplicate was handled.
9. **Verify creation:** Waits for the account display text, extracts the name (removing the `(ID)` part), compares it to the expected name (adjusted if duplicate was handled), and prints success or mismatch messages.
10. **Teardown:** Calls `driver.quit()` to close the browser and end the session.

Notes:
- JSON keys used by the class include `username`, `password`, `accountName`, `description`, `numberEmployees`, `siteName`, `industry`, `ownership`, `sourceIndex`, `state`, and `campaign`.
- The duplicate handling appends `System.currentTimeMillis()` to ensure a unique account name when the application reports duplicates.

If you'd like, I can also add inline comments to the source file itself or include a sample `selectclass-testdata.json` example in the README...
