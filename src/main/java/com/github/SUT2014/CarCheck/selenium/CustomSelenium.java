/*
 *
 *  * Copyright (c) 2021.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.CarCheck.selenium;

import com.github.SUT2014.CarCheck.properties.ConfigProperties;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class CustomSelenium {
    private final Logger LOGGER;
    private Long DELAY = 50L;
    private WebDriver browser = null;
    private String URL;

    public CustomSelenium(Logger logger, Long seconds) {
        LOGGER = logger;
        this.DELAY = seconds;
    }

    public CustomSelenium(Logger logger) {
        LOGGER = logger;
    }
    // load chrome driver
    private void loadChromeDriver(String str) {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getInstance().getBroswerDir() +"/"+str+"/chromedriver");
        browser = new ChromeDriver();
    }

    //load firefox driver
    private void loadFireFoxDriver(String str){
        System.setProperty("webdriver.gecko.driver", ConfigProperties.getInstance().getBroswerDir() + "/"+str+"/geckodriver");
        browser = new FirefoxDriver();
    }

    // Return webdriver handle, customise it by sending in browser type (firefox,chrome,ie..)
    public void setDriver(String str) {
        switch(str){
            case "firefox":
                loadFireFoxDriver(str);
                break;
            case "chrome":
                loadChromeDriver(str);
                break;
        }
    }

    // Navigate to page
    public void navigateTo(String URL){
        browser.get(URL);
    }

    // getTitle of a page
    public String getTitle(){
        return(browser.getTitle());
    }

    // timeout value
    public void setImplicitTimeout(Long timeout){
        browser.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    //get xpath element, ignore case
    public String getElementTextIgnoreCase(String str){
        WebElement ele = browser.findElement(By.xpath("//dt[contains(translate(., '" + str + "', '"
                + str.toUpperCase() + "'), '"+ str.toUpperCase() + "')]/following-sibling::dd"));
        return(ele.getText());
    }

    //send keys by id
    public void sendById(String id, String key){
        browser.findElement(By.id(id)).sendKeys(key);
    }

    //find and click button by xpath
    public void findAndClick(String key){
        WebElement button = browser.findElement(By.xpath("//button[contains(.,'" + key + "')]"));
        button.click();
    }

    //send and click button
    public void findSendAndClick(String URL, String id, String input, String buttonStr) throws InterruptedException {
        navigateTo(URL);
        Thread.sleep(DELAY);
        sendById(id, input);
        findAndClick(buttonStr);
        Thread.sleep(DELAY);
    }
    // retrieve details by keys
    public String retrieveDetailsForKeys(String keys){
        StringBuilder returnCSV = new StringBuilder();
        String tmp;
        for(String name : keys.split(",")){
            tmp = getElementTextIgnoreCase(name.toLowerCase());
            if (tmp.isEmpty()){
                return tmp;
            }
            returnCSV.append(tmp).append(",");
        }
        return returnCSV.toString();

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    //close webdriver handle
    public void close(){
        if (browser!=null){
            browser.close();
        }
    }

}
