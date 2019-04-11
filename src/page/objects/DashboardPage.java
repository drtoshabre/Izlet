package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePageMethods {
	private static String makeAPostButtonLocator = "#newPostBtn";
	private static String titleFieldLocator = "//*[@name='title']";
	private static String locationFieldLocator = "//*[@name='location']";
	private static String inputImageButtonLocator = "#image";
	private static String selectTransportationDropDownLocator = "//*[@name='transport']";
	private static String descriptionFieldLocator = "//*[@name='description']";
	private static String postButtonLocator = "//*[@name='postSubmit']";
	private static String logoutButtonLocator = "//*[@id='logoutBtn']";
	// number in square brackets is position of post on page, top to bottom
	private static String postMenuButtonLocator = "";// "//i[@class='fas fa-ellipsis-v'])[1]";
	private static String editPostButtonLocator = "";// "(//i[@class='fas fa-edit'])[1]";
	private static String deletePostButtonLocator;// "(//i[@class='fas fa-trash-alt'])[1]";
	private static String editTitleFieldLocator = "#title";
	private static String editLocationFieldLocator = "#location";
	private static String editDescriptionFieldLocator = "#description";
	private static String submitEditedPostButtonLocator = "/html[1]/body[1]/div[1]/div[6]/form[1]/input[4]";//input[@name='postSubmit'])[2]
	private static String exitEditPostMenuButtonLocator = "//*[@id='close3']";

	//CREATES POST
	public static void postNewPost(WebDriver driver, String nameOfPost, String location, String description,
			String username, String password) {
		if (!driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
			LoginForm.loginUser(driver, username, password);
		}
		findByCssAndClick(makeAPostButtonLocator, driver);
		findByXPathAndSendKeys(titleFieldLocator, nameOfPost, driver);
		findByXPathAndSendKeys(descriptionFieldLocator, description, driver);
		findByXPathAndSendKeys(locationFieldLocator, location, driver);
		findByXPathAndClick(postButtonLocator, driver);

	}
//EDITS POST
	public static void editPost(WebDriver driver, int postPosition, String username, String password,
			String editedTitle, String editedLocation, String editedDescription) {
		if (!driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
			LoginForm.loginUser(driver, username, password);
		}
		String menuButtonLocator = "(//i[@class='fas fa-edit'])[" + postPosition + "]";
		String editButtonLocator = "(//i[@class='fas fa-edit'])[" + postPosition + "]";
		setPostMenuButtonLocator(menuButtonLocator);
		setEditPostButtonLocator(editButtonLocator);
		findByXPathAndClick(postMenuButtonLocator, driver);
		findByXPathAndClick(editPostButtonLocator, driver);
		findByCssAndSendKeys(editTitleFieldLocator, editedTitle, driver);
		findByCssAndSendKeys(editLocationFieldLocator, editedLocation, driver);
		findByCssAndSendKeys(editDescriptionFieldLocator, editedDescription, driver);
		findByXPathAndClick(submitEditedPostButtonLocator, driver);

	}
//DELETES POST
	public static void deletePost(WebDriver driver, int postPosition, String username, String password)
			throws InterruptedException {
		if (!driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
			LoginForm.loginUser(driver, username, password);
		}
		String menuButtonLocator = "(//i[@class='fas fa-edit'])[" + postPosition + "]";
		String deleteButtonLocator = "(//i[@class='fas fa-trash-alt'])[" + postPosition + "]";
		setPostMenuButtonLocator(menuButtonLocator);
		setEditPostButtonLocator(deleteButtonLocator);
		findByXPathAndClick(menuButtonLocator, driver);
		findByXPathAndClick(deleteButtonLocator, driver);

	}
//DELETES POSTS IN GIVEN RANGE
	public static void deletePosts(WebDriver driver, int firstPostPosition, int lastPostPosition, String username,
			String password) {
		if (!driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
			LoginForm.loginUser(driver, username, password);
		}
		for (int i = firstPostPosition; i <= lastPostPosition; i++) {
			String menuButtonLocator = "(//i[@class='fas fa-edit'])[" + i + "]";
			String deleteButtonLocator = "(//i[@class='fas fa-trash-alt'])[" + i + "]";
			setPostMenuButtonLocator(menuButtonLocator);
			setDeletePostButtonLocator(deleteButtonLocator);
			findByXPathAndClick(postMenuButtonLocator, driver);
			findByXPathAndClick(deletePostButtonLocator, driver);
		}
	}

	public static void setPostMenuButtonLocator(String postMenuButtonLocator) {
		DashboardPage.postMenuButtonLocator = postMenuButtonLocator;
	}

	public static void setEditPostButtonLocator(String editPostButtonLocator) {
		DashboardPage.editPostButtonLocator = editPostButtonLocator;
	}

	public static void setDeletePostButtonLocator(String deletePostButtonLocator) {
		DashboardPage.deletePostButtonLocator = deletePostButtonLocator;
	}
	
	public static WebElement logoutButton(WebDriver driver) {
		return driver.findElement(By.xpath(logoutButtonLocator));
	}

	public static void clickOnLogoutButton(WebDriver driver) {
		logoutButton(driver).click();
	}
}
