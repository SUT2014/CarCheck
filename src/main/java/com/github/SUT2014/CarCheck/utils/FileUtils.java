/*
 *
 *  * Copyright (c) 2021.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.CarCheck.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    //read 1 file into a string
    public static String readFileToString(String filename) throws IOException {
        return(Files.readString(Path.of(filename)));
    }

    //get all input files from input folder
    public static String readAllFiles(String path) throws IOException {
        StringBuilder contents = new StringBuilder();
        File dir = new File(path);
        File[] fileList = dir.listFiles();

        //iterate through files and extract content
        for (File file : fileList){
            if (file.isFile() && file.getName().endsWith(".txt")){
                contents.append(readFileToString(file.getPath()));
            }
        }
        return contents.toString();
    }

    //get output CSV file , populate map information
    //first line in each file has Keys, append the rest
    public static List<String> populateOutList(String path) throws IOException {
        List<String> outputCSV = new ArrayList<String>();
        File dir = new File(path);
        File[] fileList = dir.listFiles();
        //iterate through files and extract content
        for (File file : fileList){
            if (file.isFile() && file.getName().endsWith(".txt")){
                outputCSV = Files.readAllLines(Path.of(file.getPath()));
            }
        }
        return (StringUtils.removeDuplicates(outputCSV));
    }
}
