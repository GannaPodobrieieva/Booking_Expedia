package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;

public class Screenshots {
	
	static String projectPath = System.getProperty("user.dir");
	
	
	public static String getRandomString(int length) {		
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}	

	public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
		Date dateNow = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk_mm_ss");
		
		fileName = fileName + "_" + getRandomString(2) + " " + format.format(dateNow) + ".png";
		String directory = projectPath+"/screenshots/";
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(sourceFile, new File(directory + fileName));
	}

}
