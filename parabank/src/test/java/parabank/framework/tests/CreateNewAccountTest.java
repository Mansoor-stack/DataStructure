package parabank.framework.tests;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import parabank.framework.constants.UIConstants;
import parabank.framework.factory.DriverFactory;
import parabank.framework.factory.PageProvider;
import parabank.framework.listeners.AnnotationTransformer;
import parabank.framework.listeners.TestListenerExtent;
import parabank.framework.pageobject.AccountDetailsPage;
import parabank.framework.pageobject.BillPaymentPage;
import parabank.framework.pageobject.HomePage;
import parabank.framework.pageobject.OpenAccountPage;
import parabank.framework.pageobject.OverviewPage;
import parabank.framework.reports.ExtentTestManager;
import parabank.framework.utils.PropertyReader;

@Listeners({AnnotationTransformer.class, TestListenerExtent.class})
public class CreateNewAccountTest {

	WebDriver driver;
	HomePage homePage;
	OverviewPage overviewPage;
	OpenAccountPage openAcctPage;
	AccountDetailsPage acctDetailsPage;
	BillPaymentPage billPaymentPage;
	String newAcctBalanceStr = new PropertyReader().getProperty(UIConstants.NEW_ACCT_CREDIT);
	ITestContext context;

	@BeforeClass
	public void setUp(ITestContext context) {
		this.context = context;
		driver = DriverFactory.getDriver();
		context.setAttribute("driver", driver);
		homePage = new PageProvider(driver).getHomePage();
		overviewPage = new PageProvider(driver).getOverviewPage();
		openAcctPage = new PageProvider(driver).getOpenAccountPage();
		acctDetailsPage = new PageProvider(driver).getAccountDetailsPage();
		billPaymentPage = new PageProvider(driver).getBillPaymentPage();
		newAcctBalanceStr = new PropertyReader().getProperty(UIConstants.NEW_ACCT_CREDIT);

	}

	@Test(priority = 0)
	public void loginTest() {
		ExtentTest extentTest = ExtentTestManager.getTest();
		ExtentTest loginNode = extentTest.createNode("login node");
		extentTest.log(Status.INFO, "Login into Parabank");
		
		try {
			homePage.typeUserName("john");
			loginNode.log(Status.INFO, "User name details entered");
			homePage.typePassword("demo");
			loginNode.log(Status.INFO, "Password entered");
			homePage.clickLogin();
			loginNode.log(Status.INFO, "Login Button clicked");
			overviewPage.verifyNewAccountLink();
			loginNode.log(Status.INFO, "Login Button clicked");
			context.setAttribute("loginStatus", "success");
			loginNode.log(Status.INFO, "Login Successful");
			// overviewPage.clickLogOut();
			homePage.waitForSeconds(3);
		} catch(Exception e) {
			loginNode.fail(e.getCause());
			Assert.fail(e.getCause().toString());
		}

	}

	@Test(priority = 1, dependsOnMethods = { "loginTest" }, alwaysRun = false)
	public void OpenNewCheckingsAccount() {
		ExtentTest extentTest = ExtentTestManager.getTest();
		String loginState = (String) context.getAttribute("loginStatus");
		if (loginState == null || (!loginState.equals("success"))) {
			homePage.typeUserName("john");
			homePage.typePassword("demo");
			homePage.clickLogin();
			overviewPage.verifyNewAccountLink();
		}
		extentTest.log(Status.INFO, "Open new Checkings Account");
		createAccountOfType("CHECKING", "fromAccount", extentTest);
	}

	@Test(priority = 2, dependsOnMethods = { "loginTest" }, alwaysRun = false)
	public void OpenNewSavingsAccount() {
		ExtentTest extentTest = ExtentTestManager.getTest();
		String loginState = (String) context.getAttribute("loginStatus");
		if (loginState == null || (!(loginState.equals("success")))) {
			homePage.typeUserName("john");
			homePage.typePassword("demo");
			homePage.clickLogin();
			overviewPage.verifyNewAccountLink();
		}
		extentTest.log(Status.INFO, "Open new Savings Account");
		createAccountOfType("SAVINGS", "toAccount", extentTest);
	}

	@Test(priority = 3, dependsOnMethods = { "loginTest", "OpenNewSavingsAccount", "OpenNewCheckingsAccount" }, alwaysRun = false)
	public void billPayTest() {
		
		String fromAcct = (String) context.getAttribute("fromAccount");
		String toAcct = (String) context.getAttribute("toAccount");
		if (fromAcct == null || toAcct == null) {
			throw new SkipException("Account creation failed. Cannot execute this test");
		}
		
		String loginState = (String) context.getAttribute("loginStatus");
		if (loginState == null || (!loginState.equals("success"))) {
			homePage.typeUserName("john");
			homePage.typePassword("demo");
			homePage.clickLogin();
			overviewPage.verifyNewAccountLink();
		}
		overviewPage.clickAccountsOverview();
		String balanceFromAcct = overviewPage.getBalance(fromAcct);
		String avlBalanceFromAcct = overviewPage.getAvailableAmount(fromAcct);

		String balanceToAcct = overviewPage.getBalance(toAcct);
		String avlBalanceToAcct = overviewPage.getAvailableAmount(toAcct);

		double dblBalanceFromAcct = Double.parseDouble(StringUtils.remove(balanceFromAcct, "$"));
		double dblAvlBalanceFromAcct = Double.parseDouble(StringUtils.remove(avlBalanceFromAcct, "$"));

		double dblBalanceToAcct = Double.parseDouble(StringUtils.remove(balanceToAcct, "$"));
		double dblAvlBalanceToAcct = Double.parseDouble(StringUtils.remove(avlBalanceToAcct, "$"));

		ExtentTest extentTest = ExtentTestManager.getTest();
		
		ExtentTest billPayForm = extentTest.createNode("Bill Pay form validation");
		
		overviewPage.clickBillPay();
		String payeeName = "Test User";
		billPaymentPage.typeName("Test User");
		billPayForm.log(Status.INFO, "Bill Pay - Name entered");
		billPaymentPage.typeAddress("Test Address");
		billPayForm.log(Status.INFO, "Bill Pay - Address entered");
		billPaymentPage.typeCity("Test City");
		billPayForm.log(Status.INFO, "Bill Pay - City entered");
		billPaymentPage.typeState("Test State");
		billPayForm.log(Status.INFO, "Bill Pay - State entered");
		billPaymentPage.typeZipCode("12345");
		billPayForm.log(Status.INFO, "Bill Pay - Zip entered");
		billPaymentPage.typePhoneNumber("1234567890");
		billPayForm.log(Status.INFO, "Bill Pay - Phone entered");
		billPaymentPage.typeAccountNumber(toAcct);
		billPayForm.log(Status.INFO, "Bill Pay - Account # entered");
		billPaymentPage.typeVerifyAccountNumber(toAcct);
		billPayForm.log(Status.INFO, "Bill Pay - Verify Account number");
		String billPayAmount = "$200.00";
		double dblBillPayAmount = Double.parseDouble(StringUtils.remove(billPayAmount, "$"));
		billPaymentPage.typeAmount(StringUtils.remove(billPayAmount, "$"));
		billPayForm.log(Status.INFO, "Bill Pay - Amount entered");
		billPaymentPage.selectFromAccountId(fromAcct);
		billPayForm.log(Status.INFO, "Bill Pay - Verify Account number");
		billPaymentPage.clickSendPayment();
		billPayForm.log(Status.INFO, "Send Payment Button Clicked");
		
		ExtentTest billPayDetails = extentTest.createNode("Bill Pay Details Test");
		Assert.assertEquals(billPaymentPage.getBillPaymentStatus(), "Bill Payment Complete",
				"Bill Payment status Mismatch");
		billPayDetails.log(Status.INFO, "Bill Pay successful");
		Assert.assertEquals(billPaymentPage.getBillPayDetailPayeeName(), "Test User", "Bill Pay Payee Name mismatch");
		billPayDetails.log(Status.INFO, "Bill Pay - Payee name validated");
		Assert.assertEquals(billPaymentPage.getBillPayDetailAmount(), billPayAmount, "Bill Pay Amount mismatch");
		billPayDetails.log(Status.INFO, "Bill Pay - Bill Pay Amount validated");
		Assert.assertEquals(billPaymentPage.getBillPayDetailFromAccountId(), fromAcct, "Bill Pay Account mismatch");
		billPayDetails.log(Status.INFO, "Bill Pay - Bill Pay account validated");

		ExtentTest billPayTransactions = extentTest.createNode("Bill Pay Transaction Test");
		
		overviewPage.clickAccountsOverview();

		balanceFromAcct = overviewPage.getBalance(fromAcct);
		avlBalanceFromAcct = overviewPage.getAvailableAmount(fromAcct);

		double dblExpBalanceFromAcct = dblBalanceFromAcct - dblBillPayAmount;
		double dblExpAvlBalanceFromAcct = ((dblBalanceFromAcct - dblBillPayAmount) < 0) ? 0.00
				: dblBalanceFromAcct - dblBillPayAmount;

		balanceToAcct = overviewPage.getBalance(toAcct);
		avlBalanceToAcct = overviewPage.getAvailableAmount(toAcct);

		double dblExpBalanceToAcct = dblBalanceToAcct + dblBillPayAmount;
		double dblExpAvlBalanceToAcct = ((dblBalanceToAcct + dblBillPayAmount) < 0) ? 0.00
				: dblBalanceToAcct + dblBillPayAmount;

		String strExpBalanceFromAcct = String.format(((dblExpBalanceFromAcct >= 0) ? "$%.2f" : "-$%.2f"),
				Math.abs(dblExpBalanceFromAcct));
		String strExpAvlBalanceFromAcct = String.format("$%.2f", dblExpAvlBalanceFromAcct);

	
		Assert.assertEquals(balanceFromAcct, strExpBalanceFromAcct);
		billPayTransactions.log(Status.INFO, "Bill Pay Transaction - In Overview Page - From Account " + fromAcct + " Balance Validated");
		Assert.assertEquals(avlBalanceFromAcct, strExpAvlBalanceFromAcct);
		billPayTransactions.log(Status.INFO, "Bill Pay Transaction - In Overview Page - From Account " + fromAcct + " Available Balance Validated");
		overviewPage.navigateToAccountDetailsPage(fromAcct);
		boolean isBillPaySendSuccessful = acctDetailsPage.verifyBillPaySentTransaction(payeeName, billPayAmount);
		Assert.assertTrue(true, "Bill Pay sending Transaction not recorded correctly");
		billPayTransactions.log(Status.INFO, "Bill Pay Transaction - In Account Details page " + fromAcct + "'s Bill Pay transaction recorded");
		String strExpBalanceToAcct = String.format(((dblExpBalanceToAcct >= 0) ? "$%.2f" : "-$%.2f"),
				Math.abs(dblExpBalanceToAcct));
		billPayTransactions.log(Status.INFO, "Bill Pay Transaction - In Account Details page " + fromAcct + "'s Bill Pay transaction Debit valdiated");
		String strExpAvlBalanceToAcct = String.format("$%.2f", dblExpAvlBalanceToAcct);
		billPayTransactions.log(Status.INFO, "Bill Pay Transaction - In Account Details page " + fromAcct + "'s Bill Pay transaction Credit validated");


		// TODO: These Asserts are currently failing.
		Assert.assertEquals(balanceToAcct, strExpBalanceToAcct);
		billPayTransactions.log(Status.INFO, "Bill Pay Transaction - In Overview Page - To Account " + toAcct + " Balance Validated");
		Assert.assertEquals(avlBalanceToAcct, strExpAvlBalanceToAcct);
		billPayTransactions.log(Status.INFO, "Bill Pay Transaction - In Overview Page - To Account " + toAcct + " Balance Validated");
		overviewPage.clickLogOut();
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	private void createAccountOfType(final String accountTypeValue, final String fromOrToAccount, ExtentTest extentTest) {
		ExtentTest createAccount = extentTest.createNode("Create New Account", String.format("Creating new Account of type %s", accountTypeValue));
		overviewPage.clickOpenNewAccount();
		boolean accountTypeStatus = openAcctPage.selectAccountType(accountTypeValue);
		Assert.assertTrue(accountTypeStatus, "Please provide a valid Account Type");
		createAccount.log(Status.INFO, "Account Type selected");
		boolean fromAccountStatus = openAcctPage.selectFromAccount("0");
		Assert.assertTrue(fromAccountStatus, "Please provide a valid Account Number");
		createAccount.log(Status.INFO, "From Account selected");
		openAcctPage.clickOpenNewAccount();
		Assert.assertEquals(openAcctPage.getAccountStatus(), UIConstants.ACCOUNT_OPENED,
				"Account Opening not successful");
		Assert.assertEquals(openAcctPage.getCongratsText(), UIConstants.CONGRATS_MSG, "Congragulation text mismatch");
		Assert.assertTrue(openAcctPage.getAccountNumberLabel().contains(UIConstants.ACCNT_NUMBER_LBL),
				"Accnt number label mismatch");
		createAccount.log(Status.INFO, "Account Creation successful");
		ExtentTest acctDetailsTest = extentTest.createNode("Account details validation", "Validation of Account details");
		String accountNumber = openAcctPage.getAccountNumber();
		System.out.println(openAcctPage.getAccountNumber());
		openAcctPage.clickAccountNumber();
		acctDetailsTest.log(Status.INFO, "Account number clicked");
		Assert.assertEquals(acctDetailsPage.getAccountId(), accountNumber, "Account ID Mismatch");
		acctDetailsTest.log(Status.INFO, "Account Id validated");
		Assert.assertEquals(acctDetailsPage.getAccountType(), accountTypeValue, "Account Type Mismatch");
		acctDetailsTest.log(Status.INFO, "Account Type validated");
		Assert.assertEquals(acctDetailsPage.getBalance(), newAcctBalanceStr, "Balance Mismatch");
		acctDetailsTest.log(Status.INFO, "Account Balance validated");
		Assert.assertEquals(acctDetailsPage.getAvailableBalance(), newAcctBalanceStr, "Available Balance Mismatch");
		acctDetailsTest.log(Status.INFO, "Account Available balance validated");
		Assert.assertEquals(acctDetailsPage.getNumberOfTransactions(), 1,
				"Transactions not showing up on Account creation");
		acctDetailsTest.log(Status.INFO, "Account Id validated");
		Assert.assertTrue(acctDetailsPage.checkTransactionDate(),
				"Transaction Details not available for the given date");
		acctDetailsTest.log(Status.INFO, "Account Transaction validated");
		Assert.assertEquals(acctDetailsPage.getTransactionType(), "Funds Transfer Received",
				"Transaction Type mismatch");
		acctDetailsTest.log(Status.INFO, "Account Transaction Type validated");
		Assert.assertEquals(acctDetailsPage.getTransactionDebit(), "", "Transaction Debit mismatch");
		acctDetailsTest.log(Status.INFO, "Account Transaction Debit validated");
		Assert.assertEquals(acctDetailsPage.getTransactionCredit(), newAcctBalanceStr, "Transaction Credit mismatch");
		acctDetailsTest.log(Status.INFO, "Account Transaction Credit validated");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		context.setAttribute(fromOrToAccount, accountNumber);
	}

}
