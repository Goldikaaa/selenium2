package test.java.tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

public class AuthenticationTest extends BaseTest {
    
    private String randomEmail;
    private String randomName;
    private final String PASSWORD = "TestPassword123!";

    @Test(description = "Verify user can register with generated random data")
    public void testUserRegistration() {
        Faker faker = new Faker();
        randomName = faker.name().firstName();
        randomEmail = faker.internet().emailAddress();

        HomePage homePage = new HomePage(driver);
        homePage.clickLoginMenu();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.startSignup(randomName, randomEmail);

        SignupPage signupPage = new SignupPage(driver);
        signupPage.fillAccountInformation(PASSWORD);
        signupPage.fillAddressDetailsAndSubmit(
                randomName, faker.name().lastName(), faker.address().streetAddress(),
                faker.address().state(), faker.address().city(), 
                faker.address().zipCode(), faker.phoneNumber().cellPhone()
        );
        
        // Assertions could be added here to verify successful account creation
    }

    @Test(dependsOnMethods = "testUserRegistration", description = "Verify registered user can login and logout")
    public void testLoginAndLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginMenu();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(randomEmail, PASSWORD);
        
        loginPage.logout();
    }
}