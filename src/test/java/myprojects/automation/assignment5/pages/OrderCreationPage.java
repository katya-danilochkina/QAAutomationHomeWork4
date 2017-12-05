package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.model.UniqueEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderCreationPage {
    private EventFiringWebDriver driver;
    private By firstNameField= By.name("firstname");
    private By lastNameField = By.name("lastname");
    private By emailField = By.name("email");
    private By addressField = By.name("address1");
    private By postcodeField = By.name("postcode");
    private By cityField = By.name("city");
    private By deiveryOptions = By.className("delivery-options-list");
    private By firstPaymentOption = By.id("payment-option-1");
    private By termsApproveCheckbox = By.name("conditions_to_approve[terms-and-conditions]");
    private By paymentOptions = By.className("payment-options");
    private By finishOrderButton = By.cssSelector("#payment-confirmation button.btn-primary");

    private By continueButton = By.cssSelector(".form-footer .continue.btn-primary");
    private By confirmDeliveryButton = By.name("confirmDeliveryOption");

    private By personalInformationSection = By.id("checkout-personal-information-step");
    private By addressSection = By.id("checkout-addresses-step");
    private By deliverySection = By.id("checkout-delivery-step");
    private By paymentSection = By.id("checkout-payment-step");

    private final String personalInfoFirstName = "Kate";
    private final String personalInfoLastName = "Danylochkina";

    private final String addressFullAddres = "Прорізна, 9";
    private final String addressCity = "Київ";
    private final String addressZipCode = "01001";

   private UniqueEmail uniqueEmail;

    public OrderCreationPage(EventFiringWebDriver driver) {
        this.driver = driver;
        this.uniqueEmail = UniqueEmail.generate();
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalInformationSection));
    }

    private void waitForPersonalInfoSectionLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    private void waitForAddressSectionLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressField));
    }

    private void waitForDeliverySectionLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(deiveryOptions));
    }

    private void waitForPaymentSectionLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentOptions));
    }

    public void clickPersonalInfoLink() {
        waitForPageLoad();
        driver.findElement(personalInformationSection).click();
    }

    public void fillPersonalInfo() {
        waitForPersonalInfoSectionLoad();
        driver.findElement(personalInformationSection).findElement(firstNameField).sendKeys(personalInfoFirstName);
        driver.findElement(personalInformationSection).findElement(lastNameField).sendKeys(personalInfoLastName);
        driver.findElement(personalInformationSection).findElement(emailField).sendKeys(uniqueEmail.getEmail());
        driver.findElement(personalInformationSection).findElement(continueButton).click();

    }

    public void fillAddressInfo() {
        waitForAddressSectionLoad();
        driver.findElement(addressSection).findElement(addressField).sendKeys(addressFullAddres);
        driver.findElement(addressSection).findElement(cityField).sendKeys(addressCity);
        driver.findElement(addressSection).findElement(postcodeField).sendKeys(addressZipCode);
        driver.findElement(addressSection).findElement(continueButton).click();
    }

    public void fillDeliveryInfo() {
        waitForDeliverySectionLoad();
        driver.findElement(deliverySection).findElement(confirmDeliveryButton).click();
    }

    public void fillPaymentInfo() {
        waitForPaymentSectionLoad();
        driver.findElement(firstPaymentOption).click();
        driver.findElement(termsApproveCheckbox).click();
        driver.findElement(paymentSection).findElement(finishOrderButton).click();
    }
}
