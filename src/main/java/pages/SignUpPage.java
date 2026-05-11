package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {
    private By titleMrRadio = By.id("id_gender1");
    private By passwordField = By.id("password");
    private By daysDropdown = By.id("days");
    private By monthsDropdown = By.id("months");
    private By yearsDropdown = By.id("years");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By addressField = By.id("address1");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipcodeField = By.id("zipcode");
    private By mobileField = By.id("mobile_number");
    private By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void fillAccountInformation(String password) {
        clickElement(titleMrRadio);
        enterText(passwordField, password);
        
        new Select(waitForElement(daysDropdown)).selectByValue("15");
        new Select(waitForElement(monthsDropdown)).selectByValue("5");
        new Select(waitForElement(yearsDropdown)).selectByValue("1995");
    }

    public void fillAddressDetailsAndSubmit(String fName, String lName, String address, String state, String city, String zip, String mobile) {
        enterText(firstNameField, fName);
        enterText(lastNameField, lName);
        enterText(addressField, address);
        enterText(stateField, state);
        enterText(cityField, city);
        enterText(zipcodeField, zip);
        enterText(mobileField, mobile);
        clickElement(createAccountBtn);
    }
}
