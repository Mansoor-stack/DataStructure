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
public class BillPaymentPage extends BasePage{
	
	WebDriver driver;
	
	public BillPaymentPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath= "//input[@name='payee.name']")
	private WebElement name;
	
	@FindBy(xpath= "//input[@name='payee.address.street']")
	private WebElement address;
	
	@FindBy(xpath= "//input[@name='payee.address.city']")
	private WebElement city;
	
	@FindBy(xpath= "//input[@name='payee.address.state']")
	private WebElement state;
	
	@FindBy(xpath= "//input[@name='payee.address.zipCode']")
	private WebElement zipCode;
	
	@FindBy(xpath= "//input[@name='payee.phoneNumber']")
	private WebElement phoneNumber;
	
	@FindBy(xpath= "//input[@name='payee.accountNumber']")
	private WebElement accountNumber;
	
	@FindBy(xpath= "//input[@name='verifyAccount']")
	private WebElement verifyAccountNumber;
	
	@FindBy(xpath= "//input[@name='amount']")
	private WebElement amount;

	@FindBy(xpath= "//select[@name='fromAccountId']")
	private WebElement fromAccountId;
	
	@FindBy(xpath= "//input[@type='submit'][@value='Send Payment']")
	private WebElement sendPayment;
	
	@FindBy(xpath = "//div[@ng-show='showResult']//h1")
	private WebElement billPaymentStatus;
	
	@FindBy(xpath = "//div[@ng-show='showResult']//p")
	private WebElement billPaymentDetail;
	
	@FindBy(xpath = "//span[@id='payeeName']")
	private WebElement billPayDetailPayeeName;
	
	@FindBy(xpath = "//span[@id='amount']")
	private WebElement billPayDetailAmount;
	
	@FindBy(xpath = "//span[@id='fromAccountId']")
	private WebElement billPayDetailFromAccntId;
	
	
	public void typeName(final String value) {
		type(name, value);
	}
	
	public void typeAddress(final String value) {
		type(address, value);
	}
	
	public void typeCity(final String value) {
		type(city, value);
	}
	
	public void typeState(final String value) {
		type(state, value);
	}
	
	public void typeZipCode(final String value) {
		type(zipCode, value);
	}
	
	public void typePhoneNumber(final String value) {
		type(phoneNumber, value);
	}
	
	public void typeAccountNumber(final String value) {
		type(accountNumber, value);
	}
	
	public void typeVerifyAccountNumber(final String value) {
		type(verifyAccountNumber, value);
	}
	
	public void typeAmount(final String value) {
		type(amount, value);
	}
	
	public void selectFromAccountId(final String value) {
		select(fromAccountId, "visibleText", value, 2);
	}
	
	public void clickSendPayment() {
		click(sendPayment);
	}
	
	public String getBillPaymentStatus() {
		return getText(billPaymentStatus);
	}
	
	public String getBillPaymentDetail() {
		return getText(billPaymentDetail);
	}
	
	public String getBillPayDetailPayeeName() {
		return getText(billPayDetailPayeeName);
	}
	
	public String getBillPayDetailAmount() {
		return getText(billPayDetailAmount);
	}
	
	public String getBillPayDetailFromAccountId() {
		return getText(billPayDetailFromAccntId);
	}
	
}
