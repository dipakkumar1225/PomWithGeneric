package com.winjit.automation.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TablePaginationDemo extends BasePage {

    @FindBy(css = "ul#myPager>li:first-child>a:not([style=\"display: none;\"])")
    WebElement webElementPreviousPagination;

    @FindBy(css = "ul#myPager>li:last-child>a:not([style=\"display: none;\"])")
    WebElement webElementNextPagination;

    @FindBy(css = "tbody#myTable>tr[style=\"display: table-row;\"]")
    List<WebElement> webElementRecordCount;

    public void clickOnPreviousPagination() {
        while (isElementPresent(webElementPreviousPagination)) {
            System.out.println("Record Count " + getRecordCount());
            webElementPreviousPagination.click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Previous Pagination Present");
        }
    }

    public void clickOnNextPagination() {
        while (isElementPresent(webElementNextPagination)) {
            System.out.println("Record Count " + getRecordCount());
            webElementNextPagination.click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Next Pagination Present");
        }
    }

    private int getRecordCount(){
        return webElementRecordCount.size();
    }

}
