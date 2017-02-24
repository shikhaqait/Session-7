package com.qait.test;

import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.CsvReader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LoginTest {
	TestSessionInitiator testSessionInitiator;

	@Test(dataProvider="getRows")
	public void LoginToTheApplication(String username,String Password) {
		testSessionInitiator=new TestSessionInitiator("LoginTest");
		testSessionInitiator.launchApplication();           
		testSessionInitiator.loginActions.EnterEmailAndPassword(username, Password);
		testSessionInitiator.closeBrowserSession();

	}
	
	

	@DataProvider
	public Object[][] getRows()
	{
		return CsvReader.ReadRowsFromCsv();

	}
	
	


}
