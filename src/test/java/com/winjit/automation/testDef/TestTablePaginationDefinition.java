package com.winjit.automation.testDef;

import com.winjit.automation.pageobject.TablePaginationDemo;
import com.winjit.automation.utilities.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTablePaginationDefinition extends BaseTest {

    @BeforeClass
    public void navigateToURL(){
        navigateToUrl("table-pagination-demo.html");
    }

    @Test
    public void testDatePicker() {
        getPageObjectManager().getPageObjectInstance(TablePaginationDemo.class).clickOnNextPagination();
        getPageObjectManager().getPageObjectInstance(TablePaginationDemo.class).clickOnPreviousPagination();
    }
}
