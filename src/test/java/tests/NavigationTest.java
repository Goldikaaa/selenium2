package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class NavigationTest extends BaseTest {

    @Test(description = "Verify titles on multiple pages using a loop")
    public void testMultiplePagesWithLoop() {
        String[] pages = {"/products", "/test_cases", "/contact_us"};
        
        for (String pageUrl : pages) {
            driver.get(ConfigReader.getProperty("baseUrl") + pageUrl);
            
            Assert.assertTrue(driver.getCurrentUrl().contains(pageUrl), 
                    "URL nem megfelelő ezen az oldalon: " + pageUrl);
        }
    }
}