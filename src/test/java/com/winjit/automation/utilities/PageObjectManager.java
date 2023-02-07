package com.winjit.automation.utilities;

public class PageObjectManager {
    public <TPageClass> TPageClass getPageObjectInstance(Class<TPageClass> pageClass) {
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
