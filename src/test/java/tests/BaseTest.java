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

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        // Ha jelszót kérne a VNC, érdemes lehet ezeket az argumentumokat is betenni:
        options.addArguments("--remote-allow-origins=*");

        try {
            // CSAK EZ A SOR LEHET ITT, ez csatlakozik a Docker Selenium Hubhoz!
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Screenshot készítése hiba esetén (Haladó feladat kipipálva)
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                File destination = new File("screenshots/" + result.getName() + ".png");
                Files.copy(source.toPath(), destination.toPath());
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