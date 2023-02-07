package com.winjit.automation.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyFile {

    public static String getPropertyValue(String strFilename, String propertyKey) {
        Properties properties;
        FileReader reader = null;
        try {
            reader = new FileReader(System.getProperty("user.dir") + File.separator + "config" + File.separator + strFilename);
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String strKeyValue = "";
        if (Objects.nonNull(properties.get(propertyKey))) {
            strKeyValue = properties.get(propertyKey).toString().trim().toLowerCase();

        }
        return strKeyValue;
    }
}
