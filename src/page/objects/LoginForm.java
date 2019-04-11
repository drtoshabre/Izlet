package page.objects;

import org.openqa.selenium.WebDriver;
import resources.FinalVariables;

public class LoginForm extends BasePageMethods {
	private static String userNameFieldLocator = "(//input[@name='username'])[1]";
	private static String passwordFieldLocator = "(//input[@name='password'])[1]";
	private static String loginButtonLocator = "//input[@name='login']";

	public static void loginUser(WebDriver driver, String username, String password) {
		openPage(FinalVariables.TEST_URL,driver);
		findByXPathAndSendKeys(userNameFieldLocator, username,driver);
		findByXPathAndSendKeys(passwordFieldLocator, password,driver);
		findByXPathAndClick(loginButtonLocator,driver);
	}
}
