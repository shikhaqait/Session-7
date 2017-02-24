package com.qait.automation.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait{
    
    WebDriver driver;
    WebDriverWait wait;
    
    public static int timeout;
    
    public SeleniumWait(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        this.timeout = timeout;
    }

    /**
     * Returns webElement found by the locator if element is visible
     *
     * @param locator
     * @return
     */
    public WebElement getWhenVisible(By locator) {
        WebElement element;
        element = (WebElement) wait.until(ExpectedConditions
                .visibilityOfElementLocated(locator));
        return element;
    }
    
    public void waitForChangingText(WebElement el,String initialText)
    {
    	for(int i=0;i<=300;i++)
    	{
    		
    		if(!(el.getText().equals(initialText)))
    		{
    		
    		
    			break;
    		}
    	}
    }
    
    
    public WebElement getWhenClickable(By locator) {
        WebElement element;
        element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }
    
    public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
        return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null;
    }
    
    public boolean waitForPageTitleToContain(String expectedPagetitle) {
        return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
    }
    
    public WebElement waitForElementToBeVisible(WebElement element) {
        return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
    }
   
     
    public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
    
    public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
        return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    
    public boolean waitForElementToBeInVisible(By locator) {
    	   	
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null;
        
    }
    
    
    public void changeimpliciteTimeout(int sec)
    {
    	driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }
    public WebElement waitForElementToBeClickable(WebElement element) {
        return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void clickWhenReady(By locator) {
        WebElement element = (WebElement) wait.until(ExpectedConditions
                .elementToBeClickable(locator));
        element.click();
    }

//preloaderSpinner
    
    public void waitForLoaderToDisappearOnPayPalPage() {
        int i = 0;
        resetImplicitTimeout(10);
        try {
            while (driver.findElement(By.cssSelector("#preloaderSpinner p")).isDisplayed() && i <= timeout) {
                hardWait(1);     
                System.out.println("waiting for loader to disappear");
                i++;
            }
        } catch (Exception e) {
        	System.out.println("No loader to aapeared ");
        }
        resetImplicitTimeout(timeout);        
    }
    
    public void waitForElementToDisappear(WebElement element) {
        int i = 0;
        resetImplicitTimeout(15);
        try {
            while (element.isDisplayed() && i <= timeout) {
                hardWait(1);                
                i++;
            }
        } catch (Exception e) {
        }
        resetImplicitTimeout(timeout);        
    }
    
    public void resetImplicitTimeout(int newTimeOut) {
        try {
//        	System.out.println("Reset time out !!!");
            driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
        } catch (Exception e) {	
        }
    }

    // TODO Implement Wait for page load for page synchronizations
    public void waitForPageToLoadCompletely() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*")));
    }
    
    public WebElement checkPresenceOfElementInDom(By loc)
    {
    	return 
    	wait.until(ExpectedConditions.presenceOfElementLocated(loc));
    }
    
    public List<WebElement> checkPresenceOfElementsInDom(By loc)
    {
    	return 
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(loc));
    }

    public void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
      }
}
