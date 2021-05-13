package parabank.framework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import parabank.framework.constants.UIConstants;
import parabank.framework.utils.PropertyReader;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		int waitTime = Integer.parseInt(new PropertyReader().getProperty(UIConstants.IMPLICITLY_WAIT_TIME));
		wait = new WebDriverWait(driver, waitTime);
	}

	public void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public boolean select(WebElement element, final String by, final String value, final int noOfElements) {
		boolean selected = false;
		Select select = new Select(element);
		int counter = 0;
		while(counter < 10) {
			if(element.findElements(By.xpath("./child::option")).size() >= noOfElements) {
				break;
			}
			System.out.println("inside this block");
			waitForSeconds(1);
			counter ++;
		}
		List<WebElement> noOfOpions = select.getOptions();
		if(noOfOpions.size() > 0) {
			switch (by) {
			case "visibleText":
				select.selectByVisibleText(value);
				selected = true;
				break;
			case "value":
				select.selectByValue(value);
				selected = true;
				break;
			case "index":
				select.selectByIndex(Integer.parseInt(value));
				selected = true;
				break;
			default:
				break;
			}
		}
		waitForSeconds(2);
		return selected;
	}

	public void type(WebElement element, String value) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	public void waitUntilVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementIsClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilTheElementContainsText(WebElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public String getText(WebElement element) {
		waitForSeconds(5);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}
	
	public boolean isVisible(WebElement element) {
		return element.isDisplayed();
	}
	
	public boolean isElementClickable(WebElement element) {
		boolean isClickable = true;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			isClickable = false;
		}
		return isClickable;
	}
	
	public void waitForSeconds(int n) {
		try {
			Thread.sleep(n * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
