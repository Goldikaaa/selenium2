package src.main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By signupNameField = By.xpath("//input[@data-qa='signup-name']");
    private By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[@data-qa='signup-button']");
    
    private By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");
    private By logoutBtn = By.xpath("//a[contains(@href, '/logout')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void startSignup(String name, String email) {
        enterText(signupNameField, name);
        enterText(signupEmailField, email);
        clickElement(signupBtn);
    }

    public void loginUser(String email, String password) {
        enterText(loginEmailField, email);
        enterText(loginPasswordField, password);
        clickElement(loginBtn);
    }

    public void logout() {
        clickElement(logoutBtn);
    }
}