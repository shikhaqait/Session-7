package com.qait.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class LoginActions extends GetPage {
	
	public  LoginActions(WebDriver driver)
	{
		super(driver,"LoginPage");
	}
	
	
	public void EnterEmailAndPassword(String username,String Password)
	{
    wait.waitForElementToBeVisible(element("email_Field"));
	sendText(element("email_Field"),username);
	wait.waitForElementToBeVisible(element("Password_Field"));
	sendText(element("Password_Field"),Password);
    click(element("Submit"));
    wait.hardWait(4);
	
	
	
	}
	

}
