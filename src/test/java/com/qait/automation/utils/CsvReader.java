package com.qait.automation.utils;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Reporter;

public class CsvReader {
	
	public static String csvFilePath = null;
    public static String tier;
    public static String setCsvFilePath() {
    	
      	 
    	String tierFromConfigFile = getProperty("Config.properties", "tier").trim();
    	tier = System.getProperty("environment",tierFromConfigFile);
    	
    	System.out.println("*******Tier********* : "+tier);
        
        if (tier.equalsIgnoreCase("dev")) {
            csvFilePath = "src/test/resources/testdata/DEV_TestData.csv";
        } else if (tier.equalsIgnoreCase("qa")) {
            csvFilePath = "src/test/resources/testdata/QA_TestData.csv";
        } else if (tier.equalsIgnoreCase("perf")) {
            csvFilePath = "src/test/resources/testdata/PERF_TestData.csv";
        } else if (tier.equalsIgnoreCase("prod") || tier.equalsIgnoreCase("production")) {
            csvFilePath = "src/test/resources/testdata/PROD_TestData.csv";
        } else {
            Reporter.log("YOU HAVE PROVIDED WRONG TIER IN CONFIG!!! using dev test data", true);
        }
        
        System.out.println("csv file path ::" + csvFilePath);
        try {
			new FileReader(csvFilePath);
		} catch (FileNotFoundException e) {
			Reporter.log("Wrong Tier.", true);
			System.exit(0);
		}
        return csvFilePath;
    }

    
    public static String[][] ReadRowsFromCsv()
    {
    	String csvFile = setCsvFilePath();
    	 BufferedReader br = null;
         String line = "";
         String cvsSplitBy = ",";
         String [][] str=new String[2][2]; 

         try {
         
             br = new BufferedReader(new FileReader(csvFile));
             br.readLine();
             int i=0;
             while ((line = br.readLine()) != null) {

                 // use comma as separator
                 String[] UserInfo = line.split(cvsSplitBy);

                 System.out.println( UserInfo[0] + "====" + UserInfo[1] );
                 str[i][0]=UserInfo[0];
                 str[i][1]=UserInfo[1];
                 i++;

             }

         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             if (br != null) {
                 try {
                     br.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
		
    	System.out.println(str[0][0]);
    	System.out.println(str[0][1]);
    	System.out.println(str[1][0]);
    	System.out.println(str[1][1]);
    	
    	return str;

    }
    
    
    
}
