package src.main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By loginMenuBtn = By.xpath("//div[@class='shop-menu pull-right']//a[contains(@href, '/login')]");
    private By testCasesBtn = By.xpath("//ul[contains(@class, 'nav')]//a[contains(text(), 'Test Cases')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickLoginMenu() {
        clickElement(loginMenuBtn);
    }
    
    public void clickTestCases() {
        clickElement(testCasesBtn);
    }
}
