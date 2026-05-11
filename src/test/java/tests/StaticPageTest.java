package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class StaticPageTest extends BaseTest {

    @Test(description = "Verify Static Test Cases page is accessible")
    public void verifyTestCasesPageTitle() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getTitle().contains("Automation Exercise"), "Home page title mismatch");
        
        homePage.clickTestCases();
        Assert.assertTrue(driver.getCurrentUrl().contains("test_cases"), "Not on Test Cases page");
    }
}