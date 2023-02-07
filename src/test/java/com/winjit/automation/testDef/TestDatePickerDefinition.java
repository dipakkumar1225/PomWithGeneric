package com.winjit.automation.testDef;

import com.winjit.automation.pageobject.DatePickerDemo;
import com.winjit.automation.utilities.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDatePickerDefinition extends BaseTest {

    @BeforeClass
    public void navigateToURL(){
        navigateToUrl("bootstrap-date-picker-demo.html");
    }

    @Test
    public void testDatePicker() {
        getPageObjectManager().getPageObjectInstance(DatePickerDemo.class).selectDate("01/01/2022");
    }
}
