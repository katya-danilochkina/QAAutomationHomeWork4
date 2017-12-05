package myprojects.automation.assignment5.tests;

import myprojects.automation.assignment5.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    @Test
    @Parameters("version")
    public void checkSiteVersion(String version) {
        actions.checkSiteVersion(version);
    }

    @Test
    public void createNewOrder() {
        actions.openRandomProduct();
        actions.saveProductParameters();
        actions.addProductToCart();
        actions.proceedOrderCreation();
        actions.fillOrderInfo();
        actions.checkCreatedOrderInfo();
        actions.checkProductCountChange();

        // open random product

        // save product parameters

        // add product to Cart and validate product information in the Cart

        // proceed to order creation, fill required information

        // place new order and validate order summary

        // check updated In Stock value
    }

}
