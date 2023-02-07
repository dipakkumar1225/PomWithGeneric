package com.winjit.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DatePickerDemo extends BasePage {

    @FindBy(css = "input.form-control[placeholder='dd/mm/yyyy']")
    WebElement webElementInputDate;

    @FindBy(css = "div.datepicker-days th.datepicker-switch")
    WebElement webElementDisplayedMonthYear;

    @FindBy(css = "div.datepicker-months th.datepicker-switch")
    WebElement webElementYear;

    public void selectDate(String strInputDate) {
        webElementInputDate.click();

        String strDisplayedMonthAndYear = webElementDisplayedMonthYear.getText();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        YearMonth defaultYearMonth = YearMonth.parse(strDisplayedMonthAndYear, dateTimeFormatter);

        webElementDisplayedMonthYear.click();
        webElementYear.click();

        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("strInputDate " + strInputDate);
        LocalDate inputLocalDate = LocalDate.parse(strInputDate, inputDateTimeFormatter);

        Year intInputYear = Year.of(inputLocalDate.getYear());

        if (intInputYear.isBefore(Year.of(defaultYearMonth.getYear()))) {
            By prevButton = By.cssSelector("div.datepicker-years th.prev");
            WebElement webElementPreButton = getWebDriver().findElement(prevButton);

            By byYearInList = By.xpath("//div[@class=\"datepicker-years\"]/descendant::*/span[contains(@class,\"year\")][text()=\"" + intInputYear + "\"]");
            while (!isElementPresent(byYearInList)) {
                byYearInList = By.xpath("//div[@class=\"datepicker-years\"]/descendant::*/span[contains(@class,\"year\")][text()=\"" + intInputYear + "\"]");
                webElementPreButton.click();
            }
            WebElement webElementYearFound = getWebDriver().findElement(byYearInList);
            webElementYearFound.click();

        }

        String strMonth = DateTimeFormatter.ofPattern("MMM").format(inputLocalDate.getMonth());

        By byMonth = By.xpath("//div[@class=\"datepicker-months\"]/descendant::*/span[@class=\"month\" and text()=\"" + strMonth + "\"]");
        if (isElementPresent(byMonth)) {
            WebElement webElementMonthInList = getWebDriver().findElement(byMonth);
            webElementMonthInList.click();
        }

        String strDay = String.valueOf(inputLocalDate.getDayOfMonth());
        By byMonthDay = By.xpath("//div[@class=\"datepicker-days\"]/descendant::*/td[not(contains(@class,\"old day\")) and not(contains(@class,\"new\")) and text()=\"" + strDay + "\"]");
        if (isElementPresent(byMonthDay)) {
            WebElement webElementDayInList = getWebDriver().findElement(byMonthDay);
            webElementDayInList.click();
        } else {
            WebElement webElementCountryDropDown = getWebDriver().findElement(By.cssSelector("html>body"));
            webElementCountryDropDown.click();
        }
    }
}
