package page.test;

import org.openqa.selenium.WebDriver;

import page.objects.DashboardPage;
import page.objects.LoginForm;

public class LoginFormTest {

	public static void isLoginSuccessful(WebDriver driver, String username, String password) {

		// Login to check if registration was successful
		LoginForm.loginUser(driver, username, password);
		if (driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
			System.out.println("Successful registration.");
		} else {
			System.out.println("Unsuccessful registration.");
		}
		DashboardPage.clickOnLogoutButton(driver);
	}
}
