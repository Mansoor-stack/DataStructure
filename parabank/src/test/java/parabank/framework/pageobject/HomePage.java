package parabank.framework.pageobject;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Slf4j
public class HomePage extends BasePage{
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.NAME, using = "username" )
	private WebElement userName;
	
	@FindBy(how = How.NAME, using = "password" )
	private WebElement password;
	
	@FindBy(how = How.XPATH, using = "//input[@type='submit'][@value='Log In']")
	private WebElement loginBtn;
	
	public void typeUserName(final String text) {
		log.debug("User enters the Username");
		type(userName, text);
	}
	
	public void typePassword(final String text) {
		log.debug("User enters the password");
		type(password, text);
	}
	
	public void clickLogin() {
		log.debug("User clicks on the Login button");
		click(loginBtn);
	}
}
