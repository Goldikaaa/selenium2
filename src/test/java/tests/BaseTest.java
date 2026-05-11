package tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        
        options.addArguments("--host-resolver-rules=MAP pagead2.googlesyndication.com 127.0.0.1, MAP googleads.g.doubleclick.net 127.0.0.1, MAP adservice.google.com 127.0.0.1");

        String isHeadless = ConfigReader.getProperty("headless");
        if (isHeadless != null && isHeadless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
        }

        try {
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseUrl"));

        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            org.openqa.selenium.WebElement consentBtn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.xpath("//p[text()='Consent'] | //button[contains(., 'Consent')]")));
            consentBtn.click();
            System.out.println("Consent popup sikeresen eltüntetve!");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Nem jelent meg Consent popup, megyünk tovább...");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File screenshotDir = new File("screenshots");
                if (!screenshotDir.exists()) {
                    screenshotDir.mkdirs();
                }
                
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                File destination = new File("screenshots/" + result.getName() + ".png");
                
                Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Screenshot saved: " + destination.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot: " + e.getMessage());
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }
}