package com.winjit.automation.utilities;

import com.google.common.base.Strings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.time.Duration;

public class BaseTest {

    private static WebDriver webDriver;

    @BeforeSuite
    public void initBrowser(){
        String stringBrowserName = PropertyFile.getPropertyValue("generic.properties", "browser.name");
        switch (stringBrowserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("start-maximized");
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                webDriver = WebDriverManager.edgedriver().capabilities(edgeOptions).create();
                break;
            case "firefox":
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int screenWidth = screenSize.width;
                int screenHeight = screenSize.height;
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--window-size=" + screenWidth + "," + screenHeight + "");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                webDriver = WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
                break;
            case "internet":
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                internetExplorerOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                webDriver = WebDriverManager.iedriver().capabilities(internetExplorerOptions).create();
                webDriver.manage().window().maximize();
                break;
            case "safari":
                if (!System.getProperty("os.name").toLowerCase().contains("win")) {
                    SafariOptions safariOptions = new SafariOptions();
                    safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    webDriver = WebDriverManager.safaridriver().capabilities(safariOptions).create();
                    break;
                }
            default:
                throw new IllegalArgumentException("Invalid browser name" + stringBrowserName);
        }
        webDriver.get(getBaseURL());
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    protected String getBaseURL(){
        return PropertyFile.getPropertyValue("generic.properties","browser.aut.baseurl");
    }
    protected PageObjectManager getPageObjectManager(){
        return new PageObjectManager();
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    protected void navigateToUrl(final String strURL) {
        getWebDriver().navigate().to( getBaseURL()+ "/" + ((!Strings.isNullOrEmpty(strURL)) ? strURL : ""));
    }

    @AfterSuite
    public void tearDown(){
        if (webDriver != null) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            webDriver.quit();
            webDriver = null;
        }
    }
}
