/*
 *
 *  * Copyright (c) 2021.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.CarCheck.properties;

import java.util.Properties;

public class ConfigProperties {
    private static final ConfigProperties configProperties;

    static { configProperties = new ConfigProperties(); }
    private final Properties properties;
    private ConfigProperties() {

        properties = new Properties();
        try {
            properties.load(ConfigProperties.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ConfigProperties getInstance() { return configProperties; }

    public String getBrowser() { return properties.getProperty("browser"); }
    public String getBroswerDir() { return properties.getProperty("webdriverDir"); }
    public String getInputDir() {
        return properties.getProperty("inputDir");
    }
    public String getOutputDir() {
        return properties.getProperty("outputDir");
    }
    public String getCheckarUrl() { return properties.getProperty("url"); }
    public String getSearchPattern() { return properties.getProperty("searchPattern"); }
    public Integer getimplicitTimeOut() { return Integer.parseInt(properties.getProperty("implicitWaitTimeout")); }
}
