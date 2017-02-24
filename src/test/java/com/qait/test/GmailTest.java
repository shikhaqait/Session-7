package com.qait.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.CsvReader;

public class GmailTest {
	TestSessionInitiator testSessionInitiator;
	
	@Test(dataProvider="getGmail")
	public void LoginToGmail(String username,String Password) {
		testSessionInitiator=new TestSessionInitiator("GmailTest");
		testSessionInitiator.launchApplication();           
		testSessionInitiator.GmailLoginAction.EnterEmailAndPassword(username, Password);
		testSessionInitiator.closeBrowserSession();
	}	
	@DataProvider
	public Object[][] getGmail()
	{
		return CsvReader.ReadRowsFromCsv();
	}	
	
}
