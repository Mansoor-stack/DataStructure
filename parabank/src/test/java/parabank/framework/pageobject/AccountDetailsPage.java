package parabank.framework.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
public class AccountDetailsPage extends BasePage{
	
	WebDriver driver;
	String transactionDate;
	
	public AccountDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "accountId" )
	private WebElement accountId;
	
	@FindBy(how = How.ID, using = "accountType" )
	private WebElement accountType;
	
	@FindBy(how = How.ID, using = "balance" )
	private WebElement balance;
	
	@FindBy(how = How.ID, using = "availableBalance" )
	private WebElement availableBalance;
	
	@FindBy(xpath = "//td/a[contains(text(),'Funds Transfer Received')]")
	private WebElement firstTransferType;
	
	//How and Using is not mandatory
	@FindBy(xpath = "//td//a[contains(text(),'Funds Transfer Received')]/parent::td/preceding-sibling::td")
	private WebElement firstTransactionDate;
	
	//How and Using is not mandatory
	@FindBy(xpath = "//td//a[contains(text(),'Funds Transfer Received')]/parent::td/following-sibling::td[1]")
	private WebElement firstDebit;
	
	//How and Using is not mandatory
	@FindBy(xpath = "//td//a[contains(text(),'Funds Transfer Received')]/parent::td/following-sibling::td[2]")
	private WebElement firstCredit;
	
	public String getAccountId() {
		return getText(accountId);
	}
	
	public String getAccountType() {
		return getText(accountType);
	}
	
	public String getBalance() {
		return getText(balance);
	}
	
	public String getAvailableBalance() {
		return getText(availableBalance);
	}
	
	public boolean isFirstTranactionSuccessful() {
		return isElementClickable(firstTransferType);
	}
	
	public String getFirstTrnDate() {
		return getText(firstTransactionDate);
	}
	
	public String getFirstTrnDebit() {
		return getText(firstDebit);
	}
	
	public String getFirstTrnCredit() {
		return getText(firstCredit);
	}
	
	public int getNumberOfTransactions() {
		List<WebElement> transactions = driver.findElements(By.xpath("//table[@id='transactionTable']//tbody//tr"));
		return transactions.size();
	}
	
	public boolean checkTransactionDate() {
		boolean isTransactionDateVisible = false;
	    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
	    formatter.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	    transactionDate = formatter.format(new Date());
	    System.out.println(transactionDate);
	    String transDate = String.format("//td[text()='%s']", transactionDate);
	    WebElement transactionDate = driver.findElement(By.xpath(transDate));
	    waitUntilVisible(transactionDate);
	    return isVisible(transactionDate);
	}
	
	public String getTransactionType() {
		String transactionType = String.format("//td[text()='%s']/following-sibling::td[1]", transactionDate);
		return getText(driver.findElement(By.xpath(transactionType)));
	}

	public String getTransactionDebit() {
		String debit = String.format("//td[text()='%s']/following-sibling::td[2]", transactionDate);
		return getText(driver.findElement(By.xpath(debit)));
	}

	public String getTransactionCredit() {
		String credit = String.format("//td[text()='%s']/following-sibling::td[3]", transactionDate);
		return getText(driver.findElement(By.xpath(credit)));
	}
	
	public boolean verifyBillPaySentTransaction(String payeeName, String billPayAmt) {
		checkTransactionDate();
		String billPayTransaction = String.format("Bill Payment to %s", payeeName);
		String transactionType = String.format(
				"//td[text()='%s']/following-sibling::td/a[contains(text(), '%s')]", transactionDate, billPayTransaction);
		WebElement billPaySent = driver.findElement(By.xpath(transactionType));
		//String amount = String.format("$%s", billPayAmt);
		waitUntilVisible(billPaySent);
		String amt = String.format("./parent::td/following-sibling::td[contains(text(),'%s')]", billPayAmt);
		billPaySent.findElement(By.xpath(amt));
		return isVisible(billPaySent);
	}	
}
