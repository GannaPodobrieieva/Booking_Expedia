package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverFactory {	
	
	public static WebDriver open(String browserType) {		
		
		String projectPath = System.getProperty("user.dir");
		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"\\drivers\\chromedriver.exe");
//			System.out.println("Using Chrome");
			return new ChromeDriver();	
		} 
		else if(browserType.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", projectPath+"\\drivers\\IEDriverServer.exe");
//			System.out.println("Using IE");
			return new InternetExplorerDriver();			
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath+"\\drivers\\geckodriver.exe");
//			System.out.println("Using FF");
			return new FirefoxDriver();
		}			
	}

}
