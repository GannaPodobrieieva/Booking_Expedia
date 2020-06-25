package Travel.Booking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Screenshots;
//import Travel.Booking.SearchPage;

public class TestNGParameters {
	private WebDriver driver;
	private String baseUrl;
	
	static Logger log = LogManager.getLogger(TestNGParameters.class);
	
	@Parameters("browserType")
	@BeforeClass()
	public void beforeClass(String browser) {
		
		if (browser.equalsIgnoreCase("firefox")) {
			driver = utilities.DriverFactory.open("firefox");
		} else if (browser.equalsIgnoreCase("chrome")) {
			// http://chromedriver.storage.googleapis.com/index.html
			driver = utilities.DriverFactory.open("chrome");
		}
		
		
		
		baseUrl = "https://www.expedia.com/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		PropertyConfigurator.configure("log4j.properties");
		driver.get(baseUrl);
	}
	
	@DataProvider(name="fieldsInputs")
	public static Object[][] searchData() {
		return new Object[][] {{"New York", "Chicago", "06/26/2020", "06/28/2020"},
				{"New York", "Boston", "06/26/2020", "06/28/2020"}};
	}
	
	@Parameters( { "origin", "dest", "depDate", "retDate" } )
	@Test
	public void searchFlights(String origin, String dest,
			String depDate, String retDate) throws Exception {
		SearchPage.navigateToFlightsTab(driver);
		SearchPage.fillOriginTextBox(driver, origin);
		SearchPage.fillDestinationTextBox(driver, dest);
		SearchPage.fillDepartureDateTextBox(driver, depDate);
		SearchPage.fillReturnDateTextBox(driver, retDate);
	}
	
	@Test(dataProvider="fieldsInputs")
	public void searchFlightsWithMultiData(String origin, String dest,
			String depDate, String retDate) throws Exception {
		SearchPage.navigateToFlightsTab(driver);
		SearchPage.clearAllFields(driver);
		Thread.sleep(3000);
		SearchPage.fillOriginTextBox(driver, origin);
		SearchPage.fillDestinationTextBox(driver, dest);
		SearchPage.fillDepartureDateTextBox(driver, depDate);
		SearchPage.fillReturnDateTextBox(driver, retDate);
		Thread.sleep(3000);
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			Screenshots.takeScreenshot(driver, testResult.getName());
			log.info("[fail]");
		}
	}

	@AfterClass
	public void afterClass() {		
		driver.quit();
	}
}
