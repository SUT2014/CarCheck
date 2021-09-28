/*
 *
 *  * Copyright (c) 2021.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.CarCheck.carCheck;

import com.github.SUT2014.CarCheck.selenium.CustomSelenium;
import com.github.SUT2014.CarCheck.utils.FileUtils;
import com.github.SUT2014.CarCheck.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CarCheck {
    //setup logger - final
    private static final Logger LOGGER = LogManager.getLogger(CarCheck.class);


    //simple executor to try out the modules
    public static void main(String[] args) {

        //setup initial values for direct run
        Integer DELAY = 5000;
        String BROWSER_KIND = "firefox";
        String KEYS = "Registration,Make,Model,Colour,Year";
        CustomSelenium cs;
        String outDir = "src/test/resources/output";
        String inputDir = "src/test/resources/input";
        String pattern = "([A-Z]{1,2}[0-9]{2,3}( |)[A-Z]{3})";
        String url = "https://cartaxcheck.co.uk/";


        //LOGGER.debug("There is no spoon");
        cs = new CustomSelenium(LOGGER, DELAY); //over ride webdriver DELAY with local Delay setting
        try {
            cs.setDriver(BROWSER_KIND);
            List<String> outputList = FileUtils.populateOutList(outDir);
            List<String> REGs = StringUtils.getAllREGs(FileUtils.readAllFiles(inputDir), pattern);
            LOGGER.debug("RegNos retrieved from Input File:" + REGs.toString());

            for(String RegNo : REGs){
                LOGGER.debug("***************************************************************************");
                //retrieve page information for each RegNo, keys are passed to retrieve elements
                cs.findSendAndClick(url, "vrm-input", RegNo, "Free Car Check");
                String retrievedCSV = cs.retrieveDetailsForKeys(KEYS);
                if (retrievedCSV.isEmpty()){
                    LOGGER.error("Failed - RegNo:" + RegNo + " Car details not found");
                }
                else{
                    LOGGER.debug("Checking Car RegNo: " + RegNo);
                    if (StringUtils.findAndCompareCSV(outputList, retrievedCSV, LOGGER)){
                        LOGGER.debug("Passed - RegNo:" + RegNo + " Car Details Match");
                    }
                    else{
                        LOGGER.error("Failed - RegNo:" + RegNo + " Car details Dont Match");
                    }
                    LOGGER.debug(KEYS);
                    LOGGER.debug(retrievedCSV);

                }
                LOGGER.debug("***************************************************************************");
            }
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
        finally {
            //clean up and quit
            cs.close();
        }
    }
}
