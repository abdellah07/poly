package com.polyfrontiere.data.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReaderUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderUtils.class);
	
	public static String readFile(String path) throws FileNotFoundException, IOException {
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		absolutePath = tempFixStr(absolutePath);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(absolutePath);
		} catch (FileNotFoundException e) {
			LOGGER.error("Error opening file at : " + path);
			throw e;
		}
        String string = "";
        int a;
        try {
			while ((a = fileReader.read())!=-1){
			    string +=(char) a;
			}
		} catch (IOException e) {
			LOGGER.error("Error reading file at : " + path);
			throw e;
		}
        try {
			fileReader.close();
		} catch (IOException e) {
			LOGGER.error("Error closing file at : " + path);
			throw e;
		}
        
        return string;
	}
	
	private static String tempFixStr(String path) {
		if (path.startsWith("/app")) return path;
		String out = "/polyfrontiere";
	    int i = path.indexOf(out);
	    
	    boolean w = false;
	    
	    if (i < 0) {
	    	w = true;
	    	out = "\\polyfrontiere";
	    	i = path.indexOf(out);
	    }
	    
	    String a = path.substring(0, i);
	    String b = path.substring(i + out.length(), path.length());
	    String abpoly = a + (w ? "\\" : "/") + "polyfrontiere" + b;
	    
	    return a + b;
	    //return a + (w ? "\\" : "/") + "polyfrontiere" +  b;
	}
}
