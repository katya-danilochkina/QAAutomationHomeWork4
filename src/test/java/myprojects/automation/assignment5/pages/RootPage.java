package myprojects.automation.assignment5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RootPage {
    private String mainPageUrl = "http://prestashop-automation.qatestlab.com.ua/ru/";
    private EventFiringWebDriver driver;
    private By productsListLink = By.className("all-product-link");
    private By containerWrapper = By.className("container");
    private By mobileElement = By.id("_mobile_user_info");
    private By desktopElement = By.id("_desktop_user_info");

    public RootPage(EventFiringWebDriver driver) {
        this.driver = driver;

    }

    public void open() {
        driver.get(mainPageUrl);
    }

    private void waitForProductsLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsListLink));
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(containerWrapper));
    }

    public void clickProductsListLink() {
        waitForProductsLoad();
        driver.findElement(productsListLink).click();
    }

    public void checkSiteMobileVersion() {
        waitForPageLoad();
        Assert.assertTrue(driver.findElement(mobileElement).isDisplayed());
        Assert.assertFalse(driver.findElement(desktopElement).isDisplayed());
    }

    public void checkDesktopVersion() {
        waitForPageLoad();
        Assert.assertFalse(driver.findElement(mobileElement).isDisplayed());
        Assert.assertTrue(driver.findElement(desktopElement).isDisplayed());
    }
}
