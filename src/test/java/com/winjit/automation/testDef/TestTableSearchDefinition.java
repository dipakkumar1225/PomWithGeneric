package com.winjit.automation.testDef;

import com.winjit.automation.pageobject.TableSearchDemo;
import com.winjit.automation.utilities.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTableSearchDefinition extends BaseTest {

    @BeforeClass
    public void navigateToURL(){
        navigateToUrl("table-search-filter-demo.html");
    }

    @Test
    public void testDatePicker() {
        getPageObjectManager().getPageObjectInstance(TableSearchDemo.class).enterTextToFilterRecord("failed qa");
    }
}
