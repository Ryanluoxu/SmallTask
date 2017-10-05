package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestURL {
	
	public static void main(String[] args) throws IOException {
		
		String browser = "firefox";	// chrome or firefox
		String URLsFile = "URL.properties";
		String XPathFile = "ElementXPath.properties";

		// open browser
		WebDriver webDriver = getWebDriver(browser);
		
		Properties propertiesURL = getProperties(URLsFile);
		Properties propertiesXPath = getProperties(XPathFile);
		
		testURL(propertiesURL, propertiesXPath, webDriver);
		
	}


	private static void testURL(Properties propertiesURL, Properties propertiesXPath, WebDriver webDriver) {

		Enumeration<?>  eURL = propertiesURL.propertyNames();
		Enumeration<?>  eXPath = propertiesXPath.propertyNames();
		
		System.out.println(propertiesURL.toString());
		System.out.println(propertiesXPath.toString());
		
		while (eURL.hasMoreElements()) {
			
			String keyURL = (String) eURL.nextElement();
			String URL = propertiesURL.getProperty(keyURL);
			
			
			
		}
	}


	private static Properties getProperties(String uRLsFile) throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream(uRLsFile);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		return properties;
	}

	
	private static WebDriver getWebDriver(String browser) throws IOException {
		
		String projectDir = System.getProperty("user.dir");	//	"/home/luoxu/workspace/URLsTesting";
		
		if (browser.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", projectDir + "/lib/geckodriver");
			WebDriver driver = new FirefoxDriver();
			return driver;
			
		} else if (browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", projectDir + "/lib/chromedriver");
			WebDriver driver = new ChromeDriver();
			return driver;
			
		} else {
			
			System.err.println("Browser should be either chrome or firefox..");
			return null;
		}
		
	}
	
	
	
	
	

}
