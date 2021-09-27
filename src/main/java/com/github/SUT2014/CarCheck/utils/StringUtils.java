/*
 *
 *  * Copyright (c) 2021.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.CarCheck.utils;

import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {

    //find all the REGs from the String, given pattern
    public static List<String> getAllREGs(String str, String pattern){
        List<String> REGs = new ArrayList<String>();
        Matcher m = Pattern.compile(pattern).matcher(str);
        while(m.find()) {
            REGs.add(m.group(1).replaceAll("\\s",""));
        }
        return (removeDuplicates(REGs));
    }

    //remove duplicates from a list
    public static List<String> removeDuplicates(List<String> lister){
        return(lister.stream().distinct().collect(Collectors.toList()));
    }

    //compare CSV string against a list of CSV Strings
    public static boolean findAndCompareCSV(List<String> listCSV, String str, Logger LOGGER){
        String str2List[] = str.split(",");
        for(String csv : listCSV){
            String str1List[] = csv.split(",");
            //check reg number first and then continue
            if(str1List[0].compareToIgnoreCase(str2List[0]) == 0){
                //check rest of the fields
                for(int i=1;i<str1List.length;i++){
                    if(str1List[i].compareToIgnoreCase(str2List[i]) == 0)
                    {
                        LOGGER.debug("Items Match: " + str1List[i]);
                        continue;
                    }
                    else{
                        LOGGER.debug("Items Mismatch: " + str1List[i] + ", " + str2List[i]);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
