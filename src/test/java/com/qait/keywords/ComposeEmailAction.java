package com.qait.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class ComposeEmailAction extends GetPage {

	public ComposeEmailAction(WebDriver driver) {
		super(driver,"ComposePage");
	}
	public void ComposeEmailNew()
	{
	  wait.waitForElementToBeVisible(element("Compose_btn"));
	  click(element("Compose_btn"));
	  wait.hardWait(4);
//  wait.waitForElementToBeVisible(element("email_Field"));
//	sendText(element("email_Field"),username);
//	click(element("Next_btn"));
//	wait.waitForElementToBeVisible(element("Password_Field"));
//	sendText(element("Password_Field"),Password);
//  click(element("Submit"));
//  wait.hardWait(4);
	}

}
