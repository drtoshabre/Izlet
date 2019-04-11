package main;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.objects.DashboardPage;
import page.objects.LoginForm;
import page.objects.RegistrationForm;
import page.test.LoginFormTest;
import resources.FinalVariables;
import util.ApachePOIUtility;
import util.ScannerUtility;

public class IzletMainTest {

	public static void main(String[] args) {

		String firstname = "";
		String lastname = "";
		String email = "";
		String username = "dr";
		String password = "lozinka";
		int marker = -2;
		int numberOfCellsInCurrentRow;
		int postNumber = -1;
		String postTitle = "";
		String description = "";
		String location = "";
		String editedTitle = "";
		String editedLocation = "";
		String editedDescription = "";
		Scanner sc = new Scanner(System.in);

		// SELECT ACTION
		ScannerUtility.chooseOption(marker, sc,
				" 1 -register new user,\n 2 -login user,\n 3 -post new event,\n 4 -edit event,\n 5 -delete event");

		// REGISTRATION USING MANUAL INPUT
		if (marker == 1) {
			try {
				marker = -2;

				// Select user data input, manual or from Excel sheet
				ScannerUtility.chooseOption(marker, sc,
						" 0 -manualy enter registration data,\n 1 -use data from table");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (marker == 0) {
				try {
					// Manual user data entering
					ScannerUtility.inputUserData(firstname, sc, "Enter firstname : ");
					ScannerUtility.inputUserData(lastname, sc, "Enter lastname : ");
					ScannerUtility.inputUserData(email, sc, "Enter email : ");
					ScannerUtility.inputUserData(username, sc, "Enter username : ");
					ScannerUtility.inputUserData(password, sc, "Enter password : ");

					WebDriver driver = new ChromeDriver();

					// Registering new user with manually entered data
					RegistrationForm.registerNewUser(driver, firstname, lastname, email, username, password);
					Thread.sleep(2000);
					LoginFormTest.isLoginSuccessful(driver, username, password);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (marker == 1) {
			// REGISTRATION USING DATA FROM EXCEL FILE
				try {
					// Sets Excel sheet to load user data from
					ApachePOIUtility.setXLSFile(FinalVariables.SHEET_NAME_USER_DATA);
					// Loads user data from Excel sheet
					for (int i = 1; i < ApachePOIUtility.getCurrentSheet().getLastRowNum() + 1; i++) {
						WebDriver driver = new ChromeDriver();
						driver.manage().window().maximize();
						numberOfCellsInCurrentRow = ApachePOIUtility.getCurrentSheet().getRow(i).getLastCellNum();
						for (int j = 0; j < numberOfCellsInCurrentRow + 1; j++) {
							String data = ApachePOIUtility.getDataXLSCell(i, j);

							switch (j) {
							case 0:
								firstname = data;
								break;
							case 1:
								lastname = data;
								break;
							case 2:
								username = data;
								break;
							case 3:
								email = data;
								break;
							case 4:
								password = data;
								break;
							}
						}
						// Register new user with loaded data
						RegistrationForm.registerNewUser(driver, firstname, lastname, email, username, password);
						// Thread.sleep(15000);
						// Login to check if registration was successful
						LoginForm.loginUser(driver, username, password);
						if (driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
							System.out.println("User number " + i + " successfully registered.");
						} else {
							System.out.println("User number " + i + " unsuccessful registration.");
						}
						driver.quit();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		// LOG USER
		} else if (marker == 2) {
			marker = -2;
			try {
				ScannerUtility.chooseOption(marker, sc, "0 -manual input of userdata,\n 1 -using data from Excel sheet");
				WebDriver driver = new ChromeDriver();
		// LOGIN USING MANUAL INPUT
				if (marker == 0) {
					ScannerUtility.inputUserData(username, sc, "Enter username :");
					ScannerUtility.inputUserData(password, sc, "Enter password :");
					LoginForm.loginUser(driver, username, password);

					if (!driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
						System.out.println("Login " + username + " was successful.");
					} else {
						System.out.println("Login " + username + " was unsuccessful.");
					}
					DashboardPage.clickOnLogoutButton(driver);
		//LOGIN USER USING EXCEL SHEET DATA
				}else if(marker==1) {
					// Sets Excel sheet to load user data from
					ApachePOIUtility.setXLSFile(FinalVariables.SHEET_NAME_USER_DATA);
					// Loads user data from Excel sheet
					for (int i = 1; i < ApachePOIUtility.getCurrentSheet().getLastRowNum() + 1; i++) {
						numberOfCellsInCurrentRow = ApachePOIUtility.getCurrentSheet().getRow(i).getLastCellNum();
						for (int j = 0; j < numberOfCellsInCurrentRow + 1; j++) {
							String data = ApachePOIUtility.getDataXLSCell(i, j);

							switch (j) {
							case 2:
								username = data;
								break;
							case 4:
								password = data;
								break;
							}
						}
						// Login user
						LoginForm.loginUser(driver, username, password);
						//Checking if login was successful.
						if (driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
							System.out.println("User number " + i + " successfully registered.");
						} else {
							System.out.println("User number " + i + " unsuccessful registration.");
						}
						DashboardPage.clickOnLogoutButton(driver);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// POST EVENT
		else if (marker == 3) {
			marker = -2;
			try {
				ScannerUtility.chooseOption(marker, sc,
						"If you want to enter data for creating a new post manually type 1,\n if you want to create new poste use data from Excel sheet type 2 .");
				if (marker == 1) {
					ScannerUtility.inputUserData(postTitle, sc, "Enter post title : ");
					ScannerUtility.inputUserData(description, sc, "Enter post description : ");
					ScannerUtility.inputUserData(location, sc, "Enter post location : ");

					WebDriver driver = new ChromeDriver();
					// Posts new event
					DashboardPage.postNewPost(driver, postTitle, location, description, username, password);
					// Checking if post was successful
					if (driver.findElement(By.xpath("(//h2)[1]")).getText().equals(postTitle)
							&& driver.findElement(By.xpath("(//div[@class='textbody'])[1]")).getText()
									.equals(description)
							&& driver.findElement(By.xpath("(//div[@class='location'])[1]")).getText()
									.equals(location)) {
						System.out.println("You post event successfully.");
					} else {
						System.out.println("Unsuccessful post.");
					}
				} else if (marker == 2) {

					// Sets Excel sheet to load post data from
					ApachePOIUtility.setXLSFile(FinalVariables.SHEET_NAME_POST_DESCRIPTION_DATA);
					WebDriver driver = new ChromeDriver();
					driver.manage().window().maximize();
					// Loads post data from Excel sheet
					for (int i = 1; i < ApachePOIUtility.getCurrentSheet().getLastRowNum() + 1; i++) {
						postTitle = ApachePOIUtility.getDataXLSCell(i, 1);
						description = ApachePOIUtility.getDataXLSCell(i, 0);
						location = ApachePOIUtility.getDataXLSCell(i, 2);
						// Posts new event
						DashboardPage.postNewPost(driver, postTitle, location, description, username, password);
						// Checking if post was successful
						if (driver.findElement(By.xpath("(//h2)[1]")).getText().equals(postTitle)
								&& driver.findElement(By.xpath("(//div[@class='textbody'])[1]")).getText()
										.equals(description)
								&& driver.findElement(By.xpath("(//div[@class='location'])[1]")).getText()
										.equals(location)) {
							System.out.println("You post event " + i + " successfully.");
						} else {
							System.out.println("Event " + i + " . Unsuccessful post.");
						}

						// DashboardPage.clickOnLogoutButton(driver);

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		// EDIT POST
		} else if (marker == 4) {
			marker = -2;
			try {
				ScannerUtility.chooseOption(marker, sc, " 1 - manual,\n 2 - from Excel file .");
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();

				if (marker == 1) {
					ScannerUtility.chooseOption(postNumber, sc, "Enter number of post you want to edit :");
					ScannerUtility.inputUserData(editedTitle, sc, "Enter new post title : ");
					ScannerUtility.inputUserData(editedLocation, sc, "Enter new post location : ");
					ScannerUtility.inputUserData(editedDescription, sc, "Enter new post description : ");
					// Editing post with manually entered data
					DashboardPage.editPost(driver, postNumber, username, password, editedTitle, editedLocation,
							editedDescription);
					// Checking if editing was successful
					if (driver.findElement(By.xpath("(//h2)[1]")).getText().equals(editedTitle)
							&& driver.findElement(By.xpath("(//div[@class='textbody'])[1]")).getText()
									.equals(editedDescription)
							&& driver.findElement(By.xpath("(//div[@class='location'])[1]")).getText()
									.equals(editedLocation)) {
						System.out.println("Post " + 1 + " edited successfully.");
					} else {
						System.out.println("Post " + 1 + " edit unsuccessful.");
					}

				} else if (marker == 2) {
					// Sets Excel sheet to load data from for editing post
					ApachePOIUtility.setXLSFile(FinalVariables.SHEET_NAME_POST_DESCRIPTION_DATA);
					driver.manage().window().maximize();
					if (!driver.getCurrentUrl().equals("http://localhost/izlet/dashboard.php")) {
						LoginForm.loginUser(driver, username, password);
					}
					// Loads data for editing post from Excel sheet
					for (int i = 1; i < ApachePOIUtility.getCurrentSheet().getLastRowNum() + 1; i++) {
						editedTitle = ApachePOIUtility.getDataXLSCell(i, 1);
						editedDescription = ApachePOIUtility.getDataXLSCell(i, 0);
						editedLocation = ApachePOIUtility.getDataXLSCell(i, 2);

						// Editing post with loaded data
						DashboardPage.editPost(driver, i, username, password, editedTitle, editedLocation,
								editedDescription);
						// Checking if editing was successful
						if (driver.findElement(By.xpath("(//h2)[" + i + "]")).getText().equals(editedTitle)
								&& driver.findElement(By.xpath("(//div[@class='textbody'])[" + i + "]")).getText()
										.equals(editedDescription)
								&& driver.findElement(By.xpath("(//div[@class='location'])[" + i + "]")).getText()
										.equals(editedLocation)) {
							System.out.println("Post " + i + " edited successfully.");
						} else {
							System.out.println("Post " + i + " edit unsuccessful.");
						}

					}
					DashboardPage.clickOnLogoutButton(driver);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		// DELETING POST
		} else if (marker == 5) {
			marker = -2;
			try {
				postNumber = -1;
				ScannerUtility.chooseOption(postNumber, sc, "Enter number of post you want to delete : ");
				String tempDescription;
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				LoginForm.loginUser(driver, username, password);
				for (int i = 0; i < 1; i++) {
					tempDescription = driver.findElement(By.xpath("(//div[@class='textbody'])[" + postNumber + "]"))
							.getText();
					DashboardPage.deletePost(driver, postNumber, username, password);
					if (driver.findElement(By.xpath("(//div[@class='textbody'])[" + postNumber + "]")).getText()
							.equals(tempDescription)) {
						System.out.println("Post delete unsuccessful.");
					} else {
						System.out.println("Post delete successful.");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
