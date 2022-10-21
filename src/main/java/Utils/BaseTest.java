package Utils;

import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeClass
	public void openBrowser(String browser) {
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
		option.addArguments("start-maximized");
		
	    FirefoxBinary firefoxBinary = new FirefoxBinary();
	    firefoxBinary.addCommandLineOptions("--headless");
		FirefoxOptions foptions = new FirefoxOptions();
		foptions.setBinary(firefoxBinary);
		
		
		//System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		//driver = new ChromeDriver();
		if(browser.equalsIgnoreCase("chrome")) {
		
		//driver = WebDriverManager.chromedriver().create();
			
	
		driver = new ChromeDriver(option);
		
		}else if(browser.equalsIgnoreCase("firefox")) {
		
		//driver = WebDriverManager.firefoxdriver().create();
			
			driver = new FirefoxDriver(foptions);
		
		}else if(browser.equalsIgnoreCase("edge")) {
			
			driver = WebDriverManager.edgedriver().create();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().setSize(new Dimension(1400, 900));
		driver.get("https://Keybooks.ro");			
		//driver.get("https://the-internet.herokuapp.com/javascript_alerts");	
	}
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();	
	}
	
    @AfterMethod
    public void recordFailure(ITestResult result) throws IOException {
    
    	if(ITestResult.FAILURE == result.getStatus()) {
    		
    		TakesScreenshot poza = (TakesScreenshot) driver;
    		File file = poza.getScreenshotAs(OutputType.FILE);
    		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    		Files.copy(file, new File("screenshots/"+ result.getName() + timeStamp + ".png"));
    	}
    	
    	
	}

}
