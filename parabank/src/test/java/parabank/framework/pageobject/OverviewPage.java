package parabank.framework.pageobject;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Slf4j
public class OverviewPage extends BasePage {

	WebDriver driver;

	public OverviewPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(how = How.LINK_TEXT, using = "Open New Account")
	private WebElement openNewAccountLink;

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Accounts Overview")
	private WebElement accountsOverview;

	@FindBy(how = How.LINK_TEXT, using = "Bill Pay")
	private WebElement billPayLink;

	@FindBy(how = How.LINK_TEXT, using = "Log Out")
	private WebElement logoutLink;

	public void verifyNewAccountLink() {
		waitUntilElementIsClickable(openNewAccountLink);
	}

	public void clickOpenNewAccount() {
		click(openNewAccountLink);
	}

	public void clickBillPay() {
		click(billPayLink);
	}

	public void clickLogOut() {
		click(logoutLink);
	}

	public void clickAccountsOverview() {
		click(accountsOverview);
	}

	public String getBalance(String acctNumber) {
		String locator = String.format("//a[text()='%s']/parent::td/following-sibling::td[1]", acctNumber);
		return driver.findElement(By.xpath(locator)).getText();
	}
	
	public String getAvailableAmount(String acctNumber) {
		String locator = String.format("//a[text()='%s']/parent::td/following-sibling::td[2]", acctNumber);
		return driver.findElement(By.xpath(locator)).getText();
	}
	
	public void navigateToAccountDetailsPage(String acctNumber) {
		WebElement accountNumber = driver.findElement(By.linkText(acctNumber));
		click(accountNumber);
	}
	
	
}
