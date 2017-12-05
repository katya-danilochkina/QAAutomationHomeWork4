package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.model.ProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ProductsFindingPage {
    private EventFiringWebDriver driver;
    private By productElement = By.cssSelector(".product-description .h3.product-title");


    public ProductsFindingPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productElement));
    }

    private List<WebElement> findCreatedElements(String productName) {
        String createdProductSelector = String.format("//h1[contains(@class,\"product-title\")]/a[text()=\"%s\"]", productName);
        System.out.println(createdProductSelector);
        By createdProduct = By.xpath(createdProductSelector);
        return driver.findElements(createdProduct);
    }

    public void goToElementShow(String productName) {
        waitForPageLoad();
        findCreatedElements(productName).get(0).click();
    }
}
