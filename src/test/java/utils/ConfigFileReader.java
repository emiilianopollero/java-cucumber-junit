package utils;

import enums.EDrivers;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;

    public ConfigFileReader() {
        BufferedReader reader;
        String propertyFilePath = "configs//config.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath(EDrivers driver) {
        String driverPath = null;
        switch (driver) {
            case WINDOWS_CHROME:
                driverPath = properties.getProperty("windowsChromeDriverPath");
                break;
            case WINDOWS_EDGE:
                driverPath = properties.getProperty("windowsEdgeDriverPath");
                break;
            case LINUX_CHROME:
                driverPath = properties.getProperty("linuxChromeDriverPath");
                break;
            case MAC_CHROME:
                driverPath = properties.getProperty("macChromeDriverPath");
                break;
        }
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }
}
