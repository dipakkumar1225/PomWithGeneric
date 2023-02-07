package com.winjit.automation.pageobject;

import com.winjit.automation.utilities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    WebDriver webDriver;

    public BasePage() {
        this.webDriver = BaseTest.getWebDriver();
        System.out.println("inside BasePage" + this.webDriver);
        PageFactory.initElements(webDriver, this);
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected boolean isElementPresent(final By by) {
        List<WebElement> webElementList = webDriver.findElements(by);
        return webElementList.size() != 0;
    }

    protected boolean isElementPresent(final WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }

    protected WebDriverWait getWebDriverWait(int waitingTime) {
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(waitingTime));
    }

}
