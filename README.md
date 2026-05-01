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

## Week 5 (Week5/day2) — Test Case Explanations

Below are concise explanations for each test/program in Week 5 (`Week5/day2`). Each bullet summarizes the intent, main steps, and key validations.

- **`Amazon.java`**: [seleniumtestleaf/src/test/java/Week5/day2/Amazon.java](seleniumtestleaf/src/test/java/Week5/day2/Amazon.java#L1)
	- **Purpose:** Search for a product on Amazon, capture price/ratings/title, take a screenshot, add the product to cart, and validate the cart subtotal.
	- **Main steps:** open Amazon homepage, search for "oneplus 9 pro", read the first product's price and ratings, click product, switch to product window, capture product title and screenshot, add to cart, wait for subtotal and compare it against captured price.
	- **Validation:** Checks that the displayed subtotal contains the previously captured price.

- **`BigBasketApp.java`**: [seleniumtestleaf/src/test/java/Week5/day2/BigBasketApp.java](seleniumtestleaf/src/test/java/Week5/day2/BigBasketApp.java#L1)
	- **Purpose:** Navigate BigBasket category menus, find a specific product (`Tamil Ponni Boiled Rice`), select a size (5 kg), add to basket, and assert the success toast.
	- **Main steps:** open BigBasket, hover through category menus, locate the target product, open size dropdown, choose "5 kg", click add, capture the toast message and screenshot.
	- **Validation:** Asserts the toast message equals the expected success message.

- **`Frames.java`**: [seleniumtestleaf/src/test/java/Week5/day2/Frames.java](seleniumtestleaf/src/test/java/Week5/day2/Frames.java#L1)
	- **Purpose:** Demonstrate interacting with an in-page JavaScript prompt inside an iframe (w3schools example).
	- **Main steps:** navigate to the sample page, switch to the `iframeResult` frame, trigger the prompt, send the name value, accept the alert, and read the resulting text on the page.
	- **Validation:** Asserts the resulting message matches the expected greeting `Hello <name>! How are you today?`.

- **`MergeContacts.java`**: [seleniumtestleaf/src/test/java/Week5/day2/MergeContacts.java](seleniumtestleaf/src/test/java/Week5/day2/MergeContacts.java#L1)
	- **Purpose:** End-to-end flow to merge two contacts in the CRM application using helper methods from `BaseClass` and test data from `mergecontacts-testdata.json`.
	- **Main steps:** load JSON test data, login (via `BaseClass`), navigate to Merge Contacts, open the lookup popups to select the "From" and "To" contacts (handles window switching), perform merge and accept the confirmation alert.
	- **Validation:** Confirms the page title indicates a successful merge (contains "View Contact").

- **`SnapDeal.java`**: [seleniumtestleaf/src/test/java/Week5/day2/SnapDeal.java](seleniumtestleaf/src/test/java/Week5/day2/SnapDeal.java#L1)
	- **Purpose:** Navigate SnapDeal men's shoes category, apply filters (price range and color), sort by price low-to-high, verify filters, open quick view for the first product, and capture price/discount.
	- **Main steps:** hover menus to reach Training Shoes, apply sorting and price range (500–700), apply color filter (White & Blue), verify applied filters list, perform quick view and log price/discount.
	- **Validation:** Confirms both price range and color appear in the applied filters list.

- **`FinanceYahoo.java`**: [seleniumtestleaf/src/test/java/Week5/day2/windowhandles/FinanceYahoo.java](seleniumtestleaf/src/test/java/Week5/day2/windowhandles/FinanceYahoo.java#L1)
	- **Purpose:** Demonstrate table parsing on Yahoo Finance's Crypto Heatmap page — switch to table view and print crypto names from rows.
	- **Main steps:** open Yahoo Finance, click "Crypto Heatmap", switch to table view, iterate table rows and print the crypto names.
	- **Validation:** No explicit assertion; useful as a data-extraction example and DOM-table traversal demo.

- **`SalesForce.java`**: [seleniumtestleaf/src/test/java/Week5/day2/sales/SalesForce.java](seleniumtestleaf/src/test/java/Week5/day2/sales/SalesForce.java#L1)
	- **Purpose:** Launch Salesforce with a persistent Chrome profile to reuse session/OTP state; attempts to login if credentials fields are present.
	- **Main steps:** start `ChromeDriver` with `--user-data-dir` and `--profile-directory`, open login URL, try to enter credentials and click `Login` (surrounded by a try/catch to handle already-logged-in profile state).
	- **Validation:** No explicit assertions; useful for manual or profile-based automated runs where OTP/profile caching is used.

- **TestNG-based Salesforce tests** (`testngsalesforce`):
	- **`ProjectSpecificMethod.java`**: [seleniumtestleaf/src/test/java/Week5/day2/testngsalesforce/ProjectSpecificMethod.java](seleniumtestleaf/src/test/java/Week5/day2/testngsalesforce/ProjectSpecificMethod.java#L1)
		- **Purpose:** Base class for TestNG tests; annotated `@BeforeMethod` and accepts parameters (`url`, `username`, `password`) to perform login and driver setup with a Chrome profile.
	- **`CreateLegalEntityTest1.java`**: [seleniumtestleaf/src/test/java/Week5/day2/testngsalesforce/CreateLegalEntityTest1.java](seleniumtestleaf/src/test/java/Week5/day2/testngsalesforce/CreateLegalEntityTest1.java#L1)
		- **Purpose:** Create a Legal Entity record, verify the saved name matches input, and demonstrate delete flow for cleanup.
		- **Validation:** Asserts the displayed name equals the created name.
	- **`CreateLegalEntityTest2.java`**: [seleniumtestleaf/src/test/java/Week5/day2/testngsalesforce/CreateLegalEntityTest2.java](seleniumtestleaf/src/test/java/Week5/day2/testngsalesforce/CreateLegalEntityTest2.java#L1)
		- **Purpose:** Fill some fields but intentionally leave a mandatory field empty to validate mandatory-field error handling.
		- **Validation:** Asserts that an error message containing "Complete this field" is shown.

Notes and prerequisites for Week 5 tests:
- **Browser driver:** Ensure a compatible ChromeDriver is available on `PATH` or configured by your IDE. Some tests use `ChromeOptions` with a profile directory — ensure the profile path exists if reusing session state.
- **Test data:** `mergecontacts-testdata.json` and other JSON files are under `seleniumtestleaf/src/main/resources/` and should contain required credentials and test values.
- **TestNG:** The `testngsalesforce` classes use TestNG annotations — include TestNG on the classpath (Maven dependency) to run these tests via `mvn test` or your IDE runner.

If you'd like, I can also:
- insert per-method inline comments into each source file, or
- run `mvn -Dtest=CreateLegalEntityTest1 test` to execute a specific TestNG test and capture the output.
