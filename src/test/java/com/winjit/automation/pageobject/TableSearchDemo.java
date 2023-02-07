package com.winjit.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableSearchDemo extends BasePage {

    @FindBy(css = "input#task-table-filter")
    WebElement webElementFilterTask;

    @FindBy(css = "button.btn-filter")
    WebElement webElementBtnEnableFilter;

    @FindBy(xpath = "//h4[text()=\"Popular Posts\"]")
    WebElement webElementScrollTo;

    @FindBy(css = "table#task-table tbody>tr:not([style=\"display: none;\"])")
    List<WebElement> webElementRecords;

    @FindBy(css = "table.table input.form-control")
    List<WebElement> webElementsFilterSearchText;

    String strText;

    public void enterTextToFilterRecord(String strFilterText) {
        webElementFilterTask.sendKeys(strFilterText);
        strText = strFilterText;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isRecordFound() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return webElementRecords.size() > 0;
    }

    public void getRecordDetails() {
        if (isRecordFound()) {
            for (WebElement webElement : webElementRecords) {
                List<WebElement> webElementListTd = webElement.findElements(By.tagName("td"));
                for (WebElement webElementTd : webElementListTd) {
                    if (webElementTd.getText().contains(strText)) {
                        String strData = webElementTd.getText();
                        Pattern pattern = Pattern.compile("^\\d+");
                        Matcher matcher = pattern.matcher(webElement.getText());
                        if (matcher.find()) {
                            System.out.println("Record Found :: " + strData.replaceAll("^\\d+ ", "") + ", and present against record number :: " + matcher.group());
                        }
                    }
                }
            }
        }
    }

    public void clickOnBtnFilter() {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", webElementScrollTo);
        webElementBtnEnableFilter.click();
    }

    private boolean isAllFilterSearchEnabled(int count) {
        List<WebElement> webElementList = new ArrayList<>();
        for (WebElement webElement : webElementsFilterSearchText) {
            if (webElement.isEnabled()) {
                webElementList.add(webElement);
            }
        }
        return webElementList.size() == count;
    }

}
