package parabank.framework.pageobject;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Slf4j
public class OpenAccountPage extends BasePage {

	WebDriver driver;

	public OpenAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "type")
	private WebElement accountType;

	@FindBy(how = How.ID, using = "fromAccountId")
	private WebElement fromAccount;

	@FindBy(how = How.XPATH, using = "//input[@type='submit'][@value='Open New Account']")
	private WebElement openNewAcctBtn;

	@FindBy(how = How.XPATH, using = "//div[@ng-if='showResult']/h1[@class='title']")
	private WebElement accountStatus;

	@FindBy(how = How.XPATH, using = "//div[@ng-if='showResult']/p[1]")
	private WebElement congratsMsg;
	
	@FindBy(how = How.XPATH, using = "//div[@ng-if='showResult']/p[2]")
	private WebElement accountNumberLabel;
	
	@FindBy(how = How.CSS, using = "a#newAccountId")
	private WebElement accountNumber;

	public boolean selectAccountType(final String value) {
		return select(accountType, "visibleText", value, 1);
	}

	public boolean selectFromAccount(final String value) {
		return select(fromAccount, "index", value, 1);
	}

	public void clickOpenNewAccount() {
		click(openNewAcctBtn);
	}
	
	public String getAccountStatus() {
		return getText(accountStatus);
	}

	public String getCongratsText() {
		return getText(congratsMsg);
	}

	public String getAccountNumberLabel() {
		return getText(accountNumberLabel);
	}
	
	public String getAccountNumber() {
		return getText(accountNumber);
	}
	
	public void clickAccountNumber() {
		click(accountNumber);
	}
}
