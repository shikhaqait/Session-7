package com.qait.automation.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

public class CustomReporter implements IReporter{
	private static String outFilename = "Test_Automation_Report.html";
	protected PrintWriter writer;
	private String htmlContent = "";
	
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		//Iterating over each suite included in the test
		int totalPassed = 0;
		int totalFailed = 0;
		int totalSkipped = 0;
		for (ISuite suite : suites) {
			//Following code gets the suite name
			String suiteName = suite.getName();
			//Getting the results for the said suite
			Map suiteResults = suite.getResults();
			for (Object sr : suiteResults.values()) {
				ITestContext tc = ((ISuiteResult) sr).getTestContext();
				System.out.println("Passed tests for suite '" + suiteName +
						"' is:" + tc.getPassedTests().getAllResults().size());
				totalPassed += tc.getPassedTests().getAllResults().size();
				System.out.println("Failed tests for suite '" + suiteName +
						"' is:" + 
						tc.getFailedTests().getAllResults().size());
				totalFailed += tc.getFailedTests().getAllResults().size();
				System.out.println("Skipped tests for suite '" + suiteName +
						"' is:" + 
						tc.getSkippedTests().getAllResults().size());
				totalSkipped += tc.getSkippedTests().getAllResults().size();
			}
		}
		System.out.println("Total Passed : " + totalPassed);
		System.out.println("Total Failed : " + totalFailed);
		System.out.println("Total Skipped : " + totalSkipped);
		System.out.println("Total Tests : " + (totalPassed + totalFailed + totalSkipped));
		generateHTMLReport(totalPassed, totalFailed, totalSkipped, outputDirectory);
	}

	private void generateHTMLReport(int totalPassed, int totalFailed, int totalSkipped, String outputDirectory) {
		System.out.println("Generating Custom Report...");
		try {
            writer = createWriter(outputDirectory);
        } catch ( IOException e ) {
            System.out.println( "Unable to create output file." + e.getMessage());
            return;
        }
		
		writeDocumentStart();
        writeHead();
        writeBody(totalPassed, totalFailed, totalSkipped);
        writeDocumentEnd();
        writer.print(htmlContent);
        writer.close();
        
	}
	
    protected void writeDocumentEnd() {
    	htmlContent += "</html>";
    }
	
    protected void writeBody(int totalPassed, int totalFailed, int totalSkipped) {
        htmlContent += "<body>";
        writeSuiteSummary(totalPassed, totalFailed, totalSkipped);
        htmlContent += "</body>";
    }
	
	private void writeSuiteSummary(int totalPassed, int totalFailed, int totalSkipped) {
		htmlContent += "<p><b class='heading'>Test Automation Report</b></br>";
		htmlContent += "<hr width='100%' />";
		htmlContent += "<p><b><u>Execution Detail</u></b></br>";
		htmlContent += "<table border='1'><tr>";
		htmlContent += "<td class='total'><b>Product</b></td><td class='Righttotal'>" + totalPassed + "</td>";
		htmlContent += "</tr>";
		htmlContent += "<tr>";
		htmlContent += "<td class='total'><b>Environment</b></td><td class='Righttotal'>" + totalFailed + "</td>";
		htmlContent += "</tr>";
		htmlContent += "<tr>";
		htmlContent += "<td class='total'><b>Browser</b></td><td class='Righttotal'>" + totalSkipped + "</td>";
		htmlContent += "</tr>";
		htmlContent += "</table>";
		htmlContent += "<p><b><u>Current Execution Report Summary</u></b></br>";
		htmlContent += "<table border='1'><tr class='pass'>";
		htmlContent += "<td >Passed</td><td>" + totalPassed + "</td>";
		htmlContent += "</tr>";
		htmlContent += "<tr class='fail'>";
		htmlContent += "<td>**Failed</td><td>" + totalFailed + "</td>";
		htmlContent += "</tr>";
		htmlContent += "<tr class='skip'>";
		htmlContent += "<td>*Skipped</td><td>" + totalSkipped + "</td>";
		htmlContent += "</tr>";
		htmlContent += "<tr class='total'>";
		htmlContent += "<td>Total Tests</td><td>" + (totalPassed + totalFailed + totalSkipped) + "</td>";
		htmlContent += "</tr>";
		htmlContent += "</table>";
	}

	protected void writeStylesheet() {
        htmlContent += "<style type=\"text/css\">";
        htmlContent += "table td{ border: 1px solid black;border-collapse: collapse } .skip { background: #ffff99; border: 1px solid #cccc00; } .fail { background-color: #ff9999; border: 1px solid #cc0000; } .pass { background-color: #99ff99; border: 1px solid #00cc00; } .total { background-color: #b478ed; border: 1px solid #0000cc; } .Righttotal { background-color: #dabcf6; border: 1px solid #0000cc; } .exec_field { background: #d9d9d9; border: 1px solid #666666; text-align: center; padding-left: 10px; padding-right: 10px; padding-top: 2px; padding-bottom: 2px; font-size: 14; height: 1.3em; line-height: 1.3em; vertical-align: middle; } .relative_right { position:absolute; right: 5px; } .relative_left { position:absolute; left: 5px; } .float_left { float: left; text-align: left; } .float_right { float: right; text-align: right; } strong { font-size: 13.5; } strong, table, p {font-family: 'Century Gothic', CenturyGothic, Geneva, AppleGothic, sans-serif;} .graph .bar span { position: absolute; left: 1em; } .note{ font-size: 10; } .heading{ font-size: 20; } .sectionHeading{ font-size: 16; } .scriptTable{ border-collapse: collapse; font-size: 13; border-spacing: 0px; } .scriptTable td{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } .scriptTable th{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } a{ color: #2A5DB0; text-decoration: none; } .buildInfoTD{ font-size: 14; color: #2A5DB0; } td.buildInfoTD span table { border-collapse: collapse; font-size: 12; border-spacing: 0px; margin-left: 6px; background: #ffffff; } td.buildInfoTD span table td{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } td.buildInfoTD span table th{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } td.buildInfoTD:hover {text-decoration:none;} td.buildInfoTD span { z-index:10;display:none; padding:0px 0px; margin-top:-0px; margin-left:0px; } .screenshot { height: 12px; } .noDetails{ font-size: 14;} td.buildInfoTD:hover > span{position:absolute;display:inline;} td.buildInfoTD > span table{border: 1px;} .scriptTable table.noborder_error td.buildInfoTD{border: 0px;} .scriptTable table.noborder_error td span table{border: 1px; width: 300px;} p.errorMessage {word-wrap: break-word; width:300px;} img { display: block; color:transparent; padding:0px; margin: 0px; -moz-box-sizing: border-box; box-sizing: border-box; height: 20px; width: 24px; background: url(http://testcafe.devexpress.com/Content/images/doc/UsingTestCafe/ControlPanel/Results/view-screenshot-button.png) no-repeat}";
        htmlContent += "</style>";
        //htmlContent += ".graphDiv { position: relative; width: 200px; border: 0px; padding: 2px; overflow:auto; } .graphDiv .bar { display: block; position: relative; color: #333; height: 1em; line-height: 1em; } table td{ border: 1px solid black;border-collapse: collapse }  .graphDiv .skip { background: #ffff99; border: 1px solid #cccc00; } .graphDiv .fail { background-color: #ff9999; border: 1px solid #cc0000; } .graphDiv .pass { background-color: #99ff99; border: 1px solid #00cc00; } .graphDiv .total { background-color: #9999ff; border: 1px solid #0000cc; } .exec_field { background: #d9d9d9; border: 1px solid #666666; text-align: center; padding-left: 10px; padding-right: 10px; padding-top: 2px; padding-bottom: 2px; font-size: 14; height: 1.3em; line-height: 1.3em; vertical-align: middle; } .relative_right { position:absolute; right: 5px; } .relative_left { position:absolute; left: 5px; } .float_left { float: left; text-align: left; } .float_right { float: right; text-align: right; } strong { font-size: 13.5; } strong, table, p {font-family: 'Century Gothic', CenturyGothic, Geneva, AppleGothic, sans-serif;} .graph .bar span { position: absolute; left: 1em; } .note{ font-size: 10; } .heading{ font-size: 20; } .sectionHeading{ font-size: 16; } .scriptTable{ border-collapse: collapse; font-size: 13; border-spacing: 0px; } .scriptTable td{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } .scriptTable th{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } a{ color: #2A5DB0; text-decoration: none; } .buildInfoTD{ font-size: 14; color: #2A5DB0; } td.buildInfoTD span table { border-collapse: collapse; font-size: 12; border-spacing: 0px; margin-left: 6px; background: #ffffff; } td.buildInfoTD span table td{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } td.buildInfoTD span table th{ border: 1px solid #666666; padding-left: 6px; padding-right: 6px; padding-top: 2px; padding-bottom: 2px; } td.buildInfoTD:hover {text-decoration:none;} td.buildInfoTD span { z-index:10;display:none; padding:0px 0px; margin-top:-0px; margin-left:0px; } .screenshot { height: 12px; } .noDetails{ font-size: 14;} td.buildInfoTD:hover > span{position:absolute;display:inline;} td.buildInfoTD > span table{border: 1px;} .scriptTable table.noborder_error td.buildInfoTD{border: 0px;} .scriptTable table.noborder_error td span table{border: 1px; width: 300px;} p.errorMessage {word-wrap: break-word; width:300px;} img { display: block; color:transparent; padding:0px; margin: 0px; -moz-box-sizing: border-box; box-sizing: border-box; height: 20px; width: 24px; background: url(http://testcafe.devexpress.com/Content/images/doc/UsingTestCafe/ControlPanel/Results/view-screenshot-button.png) no-repeat}";
        
    }
	
    private void writeHead() {
    	htmlContent += "<head>";
    	htmlContent += "<title>Test Automation Report</title>";
        writeStylesheet();
        htmlContent += "</head>";
	}

	private void writeDocumentStart() {
    	htmlContent += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">";
        htmlContent += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";	
       
	}

	protected PrintWriter createWriter(String outdir) throws IOException {
        new File(outdir).mkdirs();
        return new PrintWriter( new BufferedWriter( new FileWriter( new File( outdir, outFilename ) ) ) );
    }
    
}