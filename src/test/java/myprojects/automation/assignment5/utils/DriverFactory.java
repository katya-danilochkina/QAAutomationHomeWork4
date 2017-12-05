package myprojects.automation.assignment5.utils;

import myprojects.automation.assignment5.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    /**
     *
     * @param browser Driver type to use in tests.
     * @return New instance of {@link WebDriver} object.
     */
    public static WebDriver initDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(DriverFactory.class.getResource("/geckodriver").getFile()).getPath());
                return new FirefoxDriver();
            case "ie":
            case "internet explorer":
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(DriverFactory.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                return new InternetExplorerDriver(capabilities);
            case "chrome_mobile":
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(DriverFactory.class.getResource("/chromedriver").getFile()).getPath());
                Map<String, String> mobileEmulator = new HashMap<>();
                mobileEmulator.put("deviceName", "iPhone 6");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulator);
                return new ChromeDriver(chromeOptions);
            case "safari":
                return new SafariDriver();
            case "chrome":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(DriverFactory.class.getResource("/chromedriver").getFile()).getPath());
                return new ChromeDriver();
        }
    }

    private static String getResource(String resourceName) {
        try {
            return Paths.get(BaseTest.class.getResource(resourceName).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resourceName;
    }

    /**
     *
     * @param browser Remote driver type to use in tests.
     * @param gridUrl URL to Grid.
     * @return New instance of {@link RemoteWebDriver} object.
     */
    public static WebDriver initDriver(String browser, String gridUrl) {
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", getResource("geckodriver"));
            return new FirefoxDriver();
        } else if (browser.equals("safari")) {
            return new SafariDriver();
        } else if (browser.equals("chrome_mobile")) {
            System.setProperty("webdriver.gecko.driver", getResource("chromedriver"));
            Map<String, String> mobileEmulator = new HashMap<>();
            mobileEmulator.put("deviceName", "iPhone 6");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulator);
            return new ChromeDriver(chromeOptions);
        } else {
            System.setProperty("webdriver.gecko.driver", getResource("chromedriver"));
            return new ChromeDriver();
        }
    }
}
