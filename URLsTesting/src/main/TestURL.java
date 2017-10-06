package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestURL {
	
	private static TestVO testVO = new TestVO();
	private static WebDriver webDriver;
	private static ArrayList<ResultVO> resultList = new ArrayList<ResultVO>();
	
	public static void main(String[] args) throws IOException {
		
		openBrowser();
		
		testLoop();
		
		closeBrowser();
		
		showResult();
	}

	
	private static void openBrowser() throws IOException {
		
		Properties propertiesBrowser = getProperties("browser.properties");
		String browser = propertiesBrowser.getProperty("browser");
		webDriver = getWebDriver(browser);
		
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

	
	private static void testLoop() throws IOException {

		String URLFile = "URL.properties";
		String elementFile = "ElementXPath.properties";
		
		Properties propertiesURL = getProperties(URLFile);
		Properties propertiesElement = getProperties(elementFile);
		
		Enumeration<?>  eURL = propertiesURL.propertyNames();
		Enumeration<?>  eElement = propertiesElement.propertyNames();
		
		while (eURL.hasMoreElements() && eElement.hasMoreElements()) {
			
			setTestVO(propertiesURL, propertiesElement, eURL, eElement);
			
			testURL();
		}
	}
	
	
	private static void setTestVO(Properties propertiesURL, Properties propertiesElement, Enumeration<?> eURL, Enumeration<?> eElement) {

		String URLKey = (String) eURL.nextElement();
		String URL = propertiesURL.getProperty(URLKey);
		
		String elementKey = (String) eElement.nextElement();
		String element = propertiesElement.getProperty(elementKey);
		
		testVO.setTestURL(URL);
		testVO.setTestElement(element);
	}
	
	
	private static void testURL() {

		String URL = testVO.getTestURL();
		String element = testVO.getTestElement();
		
		webDriver.get(URL);	
	
		WebElement result = webDriver.findElement(By.xpath(element));
		
		saveResult(URL, element, result);
	}
	
	
	private static void saveResult(String URL, String element, WebElement result) {
		
		ResultVO resultVO = new ResultVO();
		
		resultVO.setTestURL(URL);
		resultVO.setTestElement(element);
		
		if (result!=null) {
			resultVO.setTestResult("pass");
		} else {
			resultVO.setTestResult("fail");
		}
		
		resultList.add(resultVO);
	}
	
	
	private static void closeBrowser() {
		
		webDriver.close();
	}
	
	
	private static void showResult() {
		
		Iterator<ResultVO> i = resultList.iterator();
		int testId = 0;
		
		while (i.hasNext()) {
			ResultVO resultVO = (ResultVO) i.next();
			testId++;
			
			System.out.println("Test " + testId + " :");
			System.out.println("Test URL     >>>>> " + resultVO.getTestURL());
			System.out.println("Test Element >>>>> " + resultVO.getTestElement());
			System.out.println("Test Result  >>>>> " + resultVO.getTestResult());
			System.out.println("--------------------------");
		}
		
	}


	private static Properties getProperties(String uRLsFile) throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream(uRLsFile);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		return properties;
	}


}
