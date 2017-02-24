package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.qait.automation.WebDriverFactory;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.TakeScreenshot;
import com.qait.keywords.*;


public class TestSessionInitiator {

	// protected WebDriver driver;
	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;
	
	public LoginActions loginActions;
	public GmailLoginAction GmailLoginAction;
	public ComposeEmailAction ComposeEmailAction;

	/**
	 * Initiating the page objects
	 */

	
		public TakeScreenshot takescreenshot;
	
	private String testname;
	
	
	public Random randomGenerator;

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
		//loginActions=new LoginActions(driver);
		GmailLoginAction=new GmailLoginAction(driver);
		ComposeEmailAction = new ComposeEmailAction(driver);
	}

	public void modify_java_file()
	{
	
		File log= new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qait\\hbp\\ereader\\keywords\\AnnotationHighlightActions.java");
		String search = "WebDriver driver";
		String replace = "AndroidDriver driver";
		String search1= "AndroidDriver driver";
		String replace1="WebDriver driver";
		List<String> list1=new ArrayList<String>();

		try{
		    FileInputStream fr = new FileInputStream(log);
		    String s;
		    try (BufferedReader br = new BufferedReader(new InputStreamReader(fr))) {

		        while ((s = br.readLine()) != null) {
		        	if(ConfigPropertyReader.getProperty("browser").equals("mobile"))
		        	   s=s.replaceAll(search, replace);
		        	else
		        	   s=s.replaceAll(search1, replace1);
		        	list1.add(s);
		        }
		        //totalStr = totalStr.;
		        FileWriter fw = new FileWriter(log);
		        BufferedWriter bw=new BufferedWriter(fw);
		        for(String s1:list1)
		        	{
		        	  bw.write(s1);
		        	  bw.newLine();
		        	}
		        
		        br.close();
		    bw.close();
		    }
		}catch(Exception e){
		    e.printStackTrace();
		}
		
			  
	}
	
	
	/**
	 * Page object Initiation done
	 *
	 * @param testname
	 */
	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
		this.testname = testname;
	}

	public TestSessionInitiator(String testname, String browserName) {
		wdfactory = new WebDriverFactory(browserName);
		testInitiator(testname);
		this.testname = testname;

	}

	private void testInitiator(String testname) {
		setYamlFilePath();
		_configureBrowser();
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		if (!(_getSessionConfig().get("browser").toLowerCase().trim().equalsIgnoreCase("mobile"))||!(_getSessionConfig().get("browser").toLowerCase().trim().equalsIgnoreCase("mobile"))) {
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			
						
			/*Dimension d=new Dimension(Integer.parseInt((YamlReader.getData("Resolutions.iPhone5s_x"))), 
					Integer.parseInt((YamlReader.getData("Resolutions.iPhone5s_y"))));
			driver.manage().window().setSize(d);*/
			
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(getProperty("timeout")),
						TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver",
				"seleniumserverhost", "timeout", "driverpath", "appiumServer",
				"mobileDevice" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

//	public void launchApplication() {
//		String env=getProperty("tier");
//		if(env.equalsIgnoreCase("Prod"))
//		{
//			launchApplication("https://mapworks.skyfactor.com");
//			
//		}
//		if(env.equalsIgnoreCase("Qa"))
//       {
//			launchApplication("https://mapworks-qa.skyfactor.com");
//                  
//       }
//	}
	public void launchApplication() {
		
			launchApplication("https://gmail.com");
			        
       }

	public void launchApplication(String base_url) {
		Reporter.log("\n[INFO]: The application url is :- " + base_url, true);
		driver.manage().deleteAllCookies();
		
		driver.get(base_url);
		
	}
	
	
//	public void login_Into_CB_and_Launch_EPub(String baseURL)
//	{
//		//1. launch login URL
//	    loginAndEPubLaunchingPage.is_User_Able_To_Launch_Login_URL(baseURL);
//		
//	    //2. Enter user name and pass word
//		loginAndEPubLaunchingPage.enter_UserName_And_PassWord(
//				YamlReader.getData("users.student.username2"), YamlReader.getData("users.student.password2"));
//		
//		//3. launch login URL
//	    loginAndEPubLaunchingPage.is_user_Able_To_Login_Into_Application();
//	    
//	    //1. launch login URL
//	    loginAndEPubLaunchingPage.is_User_Able_To_Navigate_To_Coursepacks_Page();
//	    
//	    //1. launch login URL
//	    loginAndEPubLaunchingPage.is_User_Able_To_Navigate_To_Particular_Coursepack(YamlReader.getData("CoursePackName"));
//	    
//	    String epubNameReadFromYAML=YamlReader.getData("ePub");
//	    epubNameReadFromYAML=epubNameReadFromYAML.replace("_",": ");
//	    Reporter.log("[INFO]: EPUB title is : "+epubNameReadFromYAML,true);
//	    
//	    //1. launch login URL
//	    loginAndEPubLaunchingPage.is_User_Able_To_Click_View_Document_Link_For_EPUB(epubNameReadFromYAML);
//		
//	}
	public void openUrl(String url) {
		driver.get(url);
	}

	public void closeBrowserSession() {
		Reporter.log("[INFO]: The Test: " + this.testname.toUpperCase() + " COMPLETED!"
				+ "\n", true);
		driver.quit();
	}

	public void closeTestSession() {
		closeBrowserSession();
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
}
