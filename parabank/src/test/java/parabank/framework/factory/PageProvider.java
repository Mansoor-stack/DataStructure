package parabank.framework.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import parabank.framework.pageobject.AccountDetailsPage;
import parabank.framework.pageobject.BillPaymentPage;
import parabank.framework.pageobject.HomePage;
import parabank.framework.pageobject.OpenAccountPage;
import parabank.framework.pageobject.OverviewPage;

public class PageProvider {
	
	WebDriver driver;
	
	public PageProvider(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage getHomePage() {
		HomePage homePage  = new HomePage(driver);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}
	
	public OverviewPage getOverviewPage() {
		OverviewPage overviewPage  = new OverviewPage(driver);
		PageFactory.initElements(driver, overviewPage);
		return overviewPage;
	}

	public OpenAccountPage getOpenAccountPage() {
		OpenAccountPage openAccountPage  = new OpenAccountPage(driver);
		PageFactory.initElements(driver, openAccountPage);
		return openAccountPage;
	}
	
	public AccountDetailsPage getAccountDetailsPage() {
		AccountDetailsPage acctDetailsPage  = new AccountDetailsPage(driver);
		PageFactory.initElements(driver, acctDetailsPage);
		return acctDetailsPage;
	}
	
	public BillPaymentPage getBillPaymentPage() {
		BillPaymentPage billPaymentPage  = new BillPaymentPage(driver);
		PageFactory.initElements(driver, billPaymentPage);
		return billPaymentPage;
	}
}
