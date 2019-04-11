package page.objects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageMethods {

	public static WebElement el = null;

	public static void click(WebElement el, WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	}

	public static void sendKeys(WebElement el, String keys) {
		el.sendKeys(keys);
	}

	public static void logMessage(String message) {
		System.out.println(message);
	}
	
	public static void openPage(String urlPage, WebDriver driver) {
		driver.get(urlPage);
	}

	public static void browserMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/* Method for Id */

	public static WebElement findById(String id,WebDriver driver) {
		try {
			return driver.findElement(By.id(id));
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static void findByIdAndClick(String id,WebDriver driver) {
		el = findById(id,driver);
		click(el,driver);

	}

	public static void findByIdAndSendKeys(String id, String keys,WebDriver driver) {
		el = findById(id,driver);
		sendKeys(el, keys);
	}

	public static WebElement waitPresenceById(String id, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static WebElement waitToBeClicktableById(String id, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.elementToBeClickable(By.id(id)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/* Methods for xpath */

	public static WebElement findByXpath(String xPath,WebDriver driver) {
		try {
			return driver.findElement(By.xpath(xPath));
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static void findByXPathAndClick(String xPath,WebDriver driver) {
		el = findByXpath(xPath,driver);
		click(el,driver);

	}

	public static void findByXPathAndSendKeys(String xPath, String keys,WebDriver driver) {
		el = findByXpath(xPath,driver);
		sendKeys(el, keys);
	}

	public static WebElement waitPresenceByXPath(String xPath, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static WebElement waitToBeClicktableByXPath(String xPath, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/* Methods for css selector */

	public static WebElement findByCss(String cssSelector,WebDriver driver) {
		try {
			return driver.findElement(By.cssSelector(cssSelector));
		} catch (NoSuchElementException ex) {
			return null;

		}
	}

	public static void findByCssAndClick(String cssSelector,WebDriver driver) {
		el = findByCss(cssSelector,driver);
		click(el,driver);

	}

	public static void findByCssAndSendKeys(String cssSelector, String keys,WebDriver driver) {
		el = findByCss(cssSelector,driver);
		el.clear();
		sendKeys(el, keys);
	}

	public static WebElement waitPresenceByCss(String cssSelector, int seconds,WebDriver driver) {

		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static WebElement waitToBeClicktableByCss(String cssSelector, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/* Methods for name */

	public static WebElement findByName(String name,WebDriver driver) {
		try {
			return driver.findElement(By.name(name));
		} catch (NoSuchElementException ex) {
			return null;

		}
	}

	public static void findByNameAndClick(String name,WebDriver driver) {
		el = findByName(name,driver);
		click(el,driver);

	}

	public static void findByNameAndSendKeys(String name, String keys,WebDriver driver) {
		el = findByName(name,driver);
		sendKeys(el, keys);
	}

	public static WebElement waitToBeClicktableByName(String name, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.elementToBeClickable(By.name(name)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static WebElement waitPresenceByName(String name, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/* Methods for link text */

	public static WebElement findByLinkText(String linkText,WebDriver driver) {
		try {
			return driver.findElement(By.linkText(linkText));
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static void findByLinkTextAndClick(String linkText,WebDriver driver) {
		el = findByLinkText(linkText,driver);
		click(el,driver);

	}

	public static void findByLinkTextAndSendKeys(String linkText, String keys,WebDriver driver) {
		el = findByLinkText(linkText,driver);
		sendKeys(el, keys);
	}

	public static WebElement waitToBeClicktableByLinkText(String linkText, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static WebElement waitPresenceByLinkText(String linkText, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/* Methods for class name */

	public static WebElement findByClassName(String className,WebDriver driver) {
		try {
			return driver.findElement(By.className(className));
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static void findByClassNameAndClick(String className,WebDriver driver) {
		el = findByClassName(className,driver);
		click(el,driver);

	}

	public static void findByClassNameAndSendKeys(String className, String keys,WebDriver driver) {
		el = findByClassName(className,driver);
		sendKeys(el, keys);
	}

	public static WebElement waitToBeClicktableByClassName(String className, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.elementToBeClickable(By.className(className)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static WebElement waitPresenceByClassName(String className, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/* Methods for tag name */

	public static WebElement findByTagName(String tagName,WebDriver driver) {
		try {
			return driver.findElement(By.tagName(tagName));
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static void findByTagNameAndClick(String tagName,WebDriver driver) {
		el = findByTagName(tagName,driver);
		click(el,driver);

	}

	public static void findByTagNameAndSendKeys(String tagName, String keys,WebDriver driver) {
		el = findByTagName(tagName,driver);
		sendKeys(el, keys);
	}

	public static WebElement waitToBeClicktableByTagName(String tagName, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.elementToBeClickable(By.tagName(tagName)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static WebElement waitPresenceByTagName(String tagName, int seconds,WebDriver driver) {
		try {
			WebElement waitElement = (new WebDriverWait(driver, seconds))
					.until(ExpectedConditions.presenceOfElementLocated(By.tagName(tagName)));

			return waitElement;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

}
