package page.objects;

import org.openqa.selenium.WebDriver;
import resources.FinalVariables;

public class RegistrationForm extends BasePageMethods{
	private static String firstnameFieldLocator = "//input[@name='firstname']";
	private static String lastnameFieldLocator = "//input[@name='lastname']";
	private static String emailFieldLocator = "//input[@name='email']";
	private static String usernameFieldLocator = "(//input[@name='username'])[2]";
	private static String passwordFieldLocator = "(//input[@name='password'])[2]";
	private static String registerButtonLocator = "//input[@name='register']";

	public static void registerNewUser(WebDriver driver, String firstName, String lastName, String email,
			String username, String password) {
		openPage(FinalVariables.TEST_URL, driver);
		
		findByXPathAndSendKeys(firstnameFieldLocator, firstName, driver);
		findByXPathAndSendKeys(lastnameFieldLocator, lastName, driver);
		findByXPathAndSendKeys(emailFieldLocator, email, driver);
		findByXPathAndSendKeys(usernameFieldLocator, username, driver);
		findByXPathAndSendKeys(passwordFieldLocator, password, driver);
		findByXPathAndClick(registerButtonLocator, driver);
	}

}
