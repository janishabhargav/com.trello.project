package com.testcases.regression;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util extends BaseClass {

	public static void Login(String Username, String Password) {
		try {

			driver.navigate().to(baseurl);

			boolean checkLogin = Util.isElementExists(driver, By.id("user"));
			System.out.println("Login page not found.....");
			if (!checkLogin) {
				System.out.println("log out process started");
				LogOut();
			}

			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			WebElement wb_element = driver.findElement(By.id("user"));
			System.out.println("Login page found.....");
			wb_element.clear();
			wb_element.sendKeys(Username);

			wb_element = driver.findElement(By.id("password"));
			wb_element.clear();
			wb_element.sendKeys(Password);

			wb_element = driver.findElement(By.id("login"));
			wb_element.click();

			System.out.println("Login Successfully.....");
			wb_element = driver.findElement(By.cssSelector("span.header-btn-text.js-member-name"));
			String profilename = wb_element.getText();

			System.out.println("Username displayed: -" + profilename);

		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}

	}

	public static void LogOut() {
		try {

			WebElement wb_element = driver.findElement(By.cssSelector("span.header-btn-text.js-member-name"));
			System.out.println("Profile name is available: -" + wb_element.getText());
			wb_element.click();

			wb_element = driver.findElement(By.linkText("Log Out"));
			wb_element.click();
			System.out.println("log out succcessfully");
		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}

	}

	public static boolean SearchTheBoard(String Expected_boardName) throws InterruptedException {
		boolean checkIfBoardIsclicked = false;
		try {

			boolean BoardMenu = false;
			boolean BoardMenu_displayed = false;

			do {
				WebElement BoardOption = driver.findElement(By.linkText("Boards"));
				BoardOption.click();
				Thread.sleep(5000);
				System.out.println(" board Menu is clicked..... ");

				BoardMenu = Util.isElementExists(driver, By.cssSelector("div.boards-drawer.is-shown"));
				System.out.println(" board Menu is Opened or not ..... " + BoardMenu);
				if (BoardMenu) {
					System.out.println(" board Menu is Displayed or not ..... ");
					WebElement isDisplayed = driver.findElement(By.cssSelector("div.boards-drawer.is-shown"));
					BoardMenu_displayed = isDisplayed.isDisplayed();
					System.out.println(" board Menu is Displayed or not ..... " + BoardMenu_displayed);
				}
			} while (!BoardMenu_displayed);

			System.out.println(" board Menu is Displayed  Now checking the list of boards...");

			List<WebElement> ListBoards = driver.findElements(By.cssSelector("span.compact-board-tile-link-text-name"));
			System.out.println(" board name List is available..... ");
			System.out.println(" Total board in list..... " + ListBoards.size());

			for (WebElement Boardname : ListBoards) {
				System.out.println(" Searching the boards ..... ");
				WebElement wb_boardName = Boardname;
				String Text_boardName = wb_boardName.getAttribute("title");
				System.out.println(
						" Actual BoardName - " + Text_boardName + "\n Expected BoardName - " + Expected_boardName);
				if (Text_boardName.equalsIgnoreCase(Expected_boardName)) {
					// WebElement element =
					// driver.findElement(By("element_path"));

					// Actions actions = new Actions(driver);
					// actions.moveToElement(wb_boardName).click().perform();

					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].scrollIntoView()", wb_boardName);
					wb_boardName.click();
					Thread.sleep(5000);

					String NewURL = driver.getCurrentUrl();
					checkIfBoardIsclicked = NewURL.toLowerCase().contains(Expected_boardName.toLowerCase());
					if (checkIfBoardIsclicked) {
						System.out.println(Expected_boardName + " board name is available..... ");
						return checkIfBoardIsclicked;
					}
				}
			}
			if (!checkIfBoardIsclicked) {
				System.out.println(Expected_boardName + " board name is not available ");
			}

			return checkIfBoardIsclicked;
		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}
		return checkIfBoardIsclicked;
	}

	
	public static String VerifyTheUploadedFileInsideBoard(String Expected_FileName) throws InterruptedException {

		String FileName = "";
		try {
			System.out.println("Verifying if File is upload....");
			WebElement wb_FileName = driver.findElement(By.cssSelector("span.js-attachment-name"));

			System.out.println("Verifying-- File is uploaded....");
			FileName = wb_FileName.getText();
			System.out.println("Verifying-- File Uploaded is ...." + FileName);
			System.out.println("Actual-- File " + FileName);
			System.out.println("Expected-- File " + Expected_FileName);

			WebElement wb_FileAddedInfo = driver.findElement(By.cssSelector("a.js-open-attachment-viewer"));
			String FileName_info = wb_FileAddedInfo.getText();
			System.out
					.println("FileName_info-- File " + FileName_info + "\nExpected Filenamr info " + Expected_FileName);
		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}
		return FileName;
	}

	public static boolean DeleteTheUploadedFileInsideBoard() throws InterruptedException {
		boolean checkdelete = false;
		boolean fileDeleted = true;
		try {

			WebElement wb_FileName = driver.findElement(By.cssSelector("span.js-attachment-name"));
			boolean fileUploaded = isElementExists(driver, By.cssSelector("span.js-attachment-name"));
			System.out.println("fileUploaded ....should be true = " + fileUploaded);
			System.out.println("Verifying-- File is uploaded....");
			String FileName = wb_FileName.getText();
			System.out.println("Verifying-- File Uploaded is ...." + FileName);

			Thread.sleep(3000);

			List<WebElement> listButtonrow = driver
					.findElements(By.cssSelector("span.attachment-thumbnail-details-options-item-text"));

			if (listButtonrow.size() != 0) {

				for (WebElement button : listButtonrow) {

					WebElement wb_TEXT = button;
					System.out.println("Deleting-- File wb_TEXT.getText();  ...." + wb_TEXT.getText());
					if (wb_TEXT.getText().toLowerCase().equalsIgnoreCase("delete")) {
						wb_TEXT.click();
						System.out.println("Deleting-button clicked");
						break;
					}
				}
			}

			// driver.findElement(By.xpath("//span[3]/a[4]/span[2]")).click();
			System.out.println("Deleting-- File Uploaded  ....");
			Thread.sleep(3000);
			if (isElementExists(driver, By.cssSelector("input.js-confirm.full.negate"))) {

				driver.findElement(By.cssSelector("input.js-confirm.full.negate")).click();
				System.out.println("Deleting-- Click Delete Button ....");
			}

			fileDeleted = isElementExists(driver, By.cssSelector("span.js-attachment-name"));
			System.out.println("fileDeleted ....should be false = " + fileDeleted);
			System.out.println("fileDeleted ....Successfully");

			WebElement message = driver.findElement(By.cssSelector("div.phenom-desc"));
			String txt_msg = message.getText();
			System.out.println("txt_msg ....Successfully" + txt_msg);

			String verifyConfirmDeleteMsg = "deleted the TrelloSampleFile.xlsx attachment from testing_CardInfo_XYZ";

			if (txt_msg.contains(verifyConfirmDeleteMsg)) {
				checkdelete = true;
				System.out.println("checkdelete ....Successfully" + checkdelete);
			}
			System.out.println("checkdelete ....Successfully" + checkdelete);
		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}
		return checkdelete;
	}

	public static void UploadFileBoard() throws InterruptedException {

		try {

			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.button-link.js-attach")).click();
			System.out.println("Attachment-- is clicked....");
			Thread.sleep(3000);
			
			String FilePath = baseDir + "\\FileUpload\\TrelloSampleFile.xlsx";
			System.out.println("FilePath...."+FilePath);

			WebElement UploadFile = driver.findElement(By.cssSelector("input.js-attach-file"));
			System.out.println("Upload element found-- is clicked....");
			Thread.sleep(5000);
			UploadFile.sendKeys(FilePath);
			UploadFile.sendKeys(Keys.ENTER);

			Thread.sleep(5000);
			System.out.println("Upload element found-- File is uploaded....");
		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}
	}

	public static String GetRandomBoardName() {
		Random rand = new Random();
		int numNoRange = rand.nextInt();
		// System.out.println("Generated Random Number without specifying any
		// range is : " + numNoRange);
		String RandomStr = numNoRange + "BoardName";
		// System.out.println("RandomStr... : " + RandomStr);
		return RandomStr;
	}

	public static void CreateCardInsideBoard(String BoradName) throws InterruptedException {

		try {
			Thread.sleep(5000);
			driver.findElement(By.linkText("Boards")).click();
			System.out.println("Boards clicked.....");

			Thread.sleep(5000);
			driver.findElement(By.linkText("Create new board…")).click();
			System.out.println("Create Board clicked.....");

			Thread.sleep(5000);
			driver.findElement(By.id("boardNewTitle")).clear();
			driver.findElement(By.id("boardNewTitle")).sendKeys(BoradName);
			driver.findElement(By.xpath("//input[@value='Create']")).click();
			System.out.println("Boards Created....." + BoradName);

			Thread.sleep(5000);
			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("name")).sendKeys("testing_CardABC");
			driver.findElement(By.xpath("//input[@value='Save']")).click();
			System.out.println("List is  Created.....testListABC");

			Thread.sleep(5000);
			driver.findElement(By.linkText("Add a card…")).click();
			driver.findElement(By.cssSelector("textarea.list-card-composer-textarea.js-card-title")).clear();
			driver.findElement(By.cssSelector("textarea.list-card-composer-textarea.js-card-title"))
					.sendKeys("testing_CardInfo_XYZ");
			driver.findElement(By.xpath("//input[@value='Add']")).click();
			System.out.println("Card infor mation is saved.....testing_CardInfo_XYZ");

					Thread.sleep(5000);
			driver.findElement(By.linkText("testing_CardInfo_XYZ")).click();
			System.out.println("testing_CardInfo_XYZ-- is clicked....");

		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}
	}

	public static void DeleteTheBoard(String BoradName) throws InterruptedException {

		try {

			boolean boardavailable = SearchTheBoard(BoradName);
			System.out.println("boardavailable...." + boardavailable);
			System.out.println("boardavailable....Starting deleting process..." + boardavailable);
			if (boardavailable) {
				Thread.sleep(5000);
				driver.findElement(By.cssSelector("a.board-menu-navigation-item-link.js-open-more")).click();
				System.out.println("more Option- clicked....");

				Thread.sleep(5000);
				driver.findElement(By.linkText("Close Board…")).click();
				System.out.println("Close Board- clicked....");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@value='Close']")).click();
				System.out.println("Close - clicked....");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Permanently Delete Board…")).click();
				System.out.println("Permanently Delete Board - clicked....");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@value='Delete']")).click();
				System.out.println("Delete - clicked....");
			}
		} catch (Exception e) {
			System.out.println("Error :::" + e);
		}
	}

	/**
	 * This Method is used to check the availability of WebElement Parameters:
	 * WebDriver Object, Locator and WaitTime Return Type: Boolean True: If the
	 * Element is Found False: If the Element is not Found
	 */
	public static boolean isElementExists(WebDriver driver, By bylocator) throws Exception {
		int iWaitTime = 30;
		boolean bFlag = false;
		WebDriverWait wait = new WebDriverWait(driver, iWaitTime);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
			if (driver.findElement((bylocator)).isDisplayed())
				bFlag = true;
		} catch (Exception e) {
			bFlag = false;
		}
		return bFlag;
	}

}
