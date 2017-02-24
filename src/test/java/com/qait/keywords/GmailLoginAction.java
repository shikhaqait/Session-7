package com.qait.keywords;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.CsvReader;

public class GmailLoginAction extends GetPage {
	
	public GmailLoginAction(WebDriver driver) {
		super(driver,"GmailPage");
	}
	public void EnterEmailAndPassword(String username,String Password)
	{
    wait.waitForElementToBeVisible(element("email_Field"));
	sendText(element("email_Field"),username);
	click(element("Next_btn"));
	wait.waitForElementToBeVisible(element("Password_Field"));
	sendText(element("Password_Field"),Password);
    click(element("Submit"));
    wait.hardWait(4);
	}

}
