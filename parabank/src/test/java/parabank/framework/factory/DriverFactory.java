package parabank.framework.factory;

import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.SWITCH;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.net.URL;

import parabank.framework.utils.PropertyReader;

import parabank.framework.constants.UIConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static final String USERNAME = "avinash139";
	public static final String AUTOMATE_KEY = "Ehdxz9iyqbZUyFmqvnT2";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		PropertyReader props = new PropertyReader();
		String browser = props.getProperty(UIConstants.BROWSER);
		ChromeOptions chromeOptions;

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//ChromeOptions chromeOptions = new ChromeOptions();
			//chromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "chromedebug":
			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("debuggerAddress", "localhost:9014");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "browserstack":
	        ChromeOptions caps = new ChromeOptions();
			caps.addArguments("start-maximized");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "80");
			caps.setCapability("name", "avinash139's First Test");
			try {
				driver = new RemoteWebDriver(new URL(URL), caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		case "chromeheadless":
			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("window-size=1920,1080");
			chromeOptions.addArguments("start-maximized");
			//options.addArguments("--headless");
			chromeOptions.setHeadless(true);
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(ffOptions);
			driver.manage().window().maximize();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;
		default:
			driver = null;
			break;
		}
		
		//driver.manage().window().maximize();
		Integer waitTime = Integer.parseInt(props.getProperty(UIConstants.IMPLICITLY_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.get(props.getProperty(UIConstants.BASE_URL));
		
		return driver;
	}

}
