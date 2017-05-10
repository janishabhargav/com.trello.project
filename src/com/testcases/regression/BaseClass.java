package com.testcases.regression;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.javascript.host.Map;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Map;

public class BaseClass {

	static String baseDir = System.getProperty("user.dir");

	// String driverPath_firefox = baseDir
	// +"\\drivers\\geckoriver\\geckodriver.exe";
	String driverPath_chrome = baseDir + "\\drivers\\chromedriver\\chromedriver.exe";

	//String driverPath_firefox = "C:\\Project\\com.trello.project\\drivers\\geckoriver\\geckodriver.exe";
	public static String Username = "bhargav.kadiya@yahoo.co.in";
	public static String Password = "bhargav07";
	public static WebDriver driver = null;
	static String baseurl = "https://trello.com/login";

	@BeforeMethod
	public void launchBrowser() throws MalformedURLException {

		try {
			System.out.println("launching firefox browser");
			System.setProperty("webdriver.chrome.driver", driverPath_chrome);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver( options );
			//driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Error 111:::" + e);
		}
	}
	/**Test 4. File Upload Delete from same page
	Steps:
	1. Launch browser and autheticate
	2. Create board name 
	3. Seach and verify if board is created
	4. Create card and click on it
	5. Upload file in the card and verify file is uploaded successfully.
	6. Delete file in the card and verify file is deleted successfully.*/
	
	@SuppressWarnings("deprecation")
	@Test(priority = 1)
	public void Test_File_Upload_Delete() throws InterruptedException {
		System.out.println("-----------------------Start :::    Test_File_Upload_Delete-----------------------");
		try {
			String BoardName_Demo =  Util.GetRandomBoardName();
			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			Util.Login(Username, Password);
			Util.CreateCardInsideBoard(BoardName_Demo);
			Util.UploadFileBoard();
			String CheckFileName = "TrelloSampleFile.xlsx";
			String expected_FileName = Util.VerifyTheUploadedFileInsideBoard(CheckFileName);
			System.out.println("Uploaded_FileName :::" + expected_FileName);

			Assert.assertEquals(CheckFileName.toLowerCase(), expected_FileName.toLowerCase(),
					"Error: File not Matched");
			boolean FileDeleted_Succssfully = Util.DeleteTheUploadedFileInsideBoard();
			System.out.println("FileDeleted_Succssfully :::" + FileDeleted_Succssfully);
			Assert.assertEquals(FileDeleted_Succssfully, true);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			System.out.println("Error :::" + e);
			System.out.println("Error :::" + e);
		}
		System.out.println("-----------------------End :::    Test_File_Upload_Delete-----------------------");
	}

	/**3. File Upload
	Steps:
	1. Launch browser and autheticate
	2. Create board name 
	3. Seach and verify if board is created
	4. Create card and click on it
	5. Upload file in the card and verify file is uploaded successfully.*/
	
	@SuppressWarnings("deprecation")
	@Test(priority = 1)
	public void Test_File_Upload() throws InterruptedException {
		System.out.println("-----------------------Start :::    Test_File_Upload-----------------------");
		try {
			String BoardName_Demo =  Util.GetRandomBoardName();
			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			Util.Login(Username, Password);
			Util.CreateCardInsideBoard(BoardName_Demo);
			Util.UploadFileBoard();
			String CheckFileName = "TrelloSampleFile.xlsx";
			String expected_FileName = Util.VerifyTheUploadedFileInsideBoard(CheckFileName);
			System.out.println("Uploaded_FileName :::" + expected_FileName);

			Assert.assertEquals(CheckFileName.toLowerCase(), expected_FileName.toLowerCase(),
					"Error: File not Matched");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			System.out.println("Error :::" + e);
		}
		System.out.println("-----------------------End :::    Test_File_Upload-----------------------");
	}

	/**5. File Upload and delete from popup
	Steps:
	1. Launch browser and autheticate
	2. Create board name 
	3. Seach and verify if board is created
	4. Create card and click on it
	5. Upload file in the card and verify file is uploaded successfully.
	6. Click on file name and verify popup*/
	@SuppressWarnings("deprecation")
	@Test(priority = 1)
	public void Test_File_Upload_Delete_from_Popup() throws InterruptedException {
		System.out.println("-----------------------Start :::    Test_File_Upload_Delete_from_Popup-----------------------");
		try {
			String BoardName_Demo =  Util.GetRandomBoardName();
			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			Util.Login(Username, Password);
			Util.CreateCardInsideBoard(BoardName_Demo);
			Util.UploadFileBoard();
			String CheckFileName = "TrelloSampleFile.xlsx";
			String expected_FileName = Util.VerifyTheUploadedFileInsideBoard(CheckFileName);
			System.out.println("Uploaded_FileName :::" + expected_FileName);

			Assert.assertEquals(CheckFileName.toLowerCase(), expected_FileName.toLowerCase(),
					"Error: File not Matched");
			
			 driver.findElement(By.linkText("xlsx")).click();
			    driver.findElement(By.cssSelector("a.js-open-delete-confirm")).click();
			    driver.findElement(By.linkText("Delete forever.")).click();
			
			String  FileDeleted_Check = Util.VerifyTheUploadedFileInsideBoard(CheckFileName);
			boolean checkDeleted = false;
			if(FileDeleted_Check == null || FileDeleted_Check.isEmpty()){
				checkDeleted = true;
			}
			
			Assert.assertEquals(checkDeleted, true, "Error: File is not deleted  ");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			System.out.println("Error :::" + e);
			System.out.println("Error :::" + e);
		}
		System.out.println("-----------------------End :::    Test_File_Upload_Delete_from_Popup-----------------------");
	}

	/**1. Create Search
	Steps:
	1. Launch browser and autheticate
	2. Create board name 
	3. Seach and verify if board is created*/
	
	@Test(priority = 2)
	public void Test_Create_Search() throws InterruptedException {
		System.out.println("-----------------------Start :::    Test_Create_Search-----------------------");
		try {
			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			String BoardName_Demo =  Util.GetRandomBoardName();
			Util.Login(Username, Password);
			Util.CreateCardInsideBoard(BoardName_Demo);

			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			boolean Check_boardAvailable = Util.SearchTheBoard(BoardName_Demo);

			Thread.sleep(5000);

			Assert.assertEquals(true, Check_boardAvailable, "Error: Board name is available in search list");
			System.out.println("Board name is available in search list");
			driver.navigate().to(baseurl);
			Thread.sleep(5000);
			Util.DeleteTheBoard(BoardName_Demo);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			System.out.println("Error :::" + e);
		}
		System.out.println("-----------------------End :::    Test_Create_Search-----------------------");
	}

	/**2. Delete Board
	Steps:
	1. Launch browser and autheticate
	2. Create board name 
	3. Seach and verify if board is created
	4. Deleted created board
	5. Seach and verify if board is not displayed in list*/
	@Test(priority = 2)
	public void Test_DeleteBoard() throws InterruptedException {
		System.out.println("-----------------------Start :::    Test_DeleteBoard-----------------------");
		try {

			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			String BoardName_Demo =  Util.GetRandomBoardName();
			Util.Login(Username, Password);
			Util.CreateCardInsideBoard(BoardName_Demo);

			driver.navigate().to(baseurl);
			Thread.sleep(5000);

			boolean Check_boardAvailable = Util.SearchTheBoard(BoardName_Demo);

			Thread.sleep(5000);

			Assert.assertEquals(true, Check_boardAvailable, "Error: Board name is available in search list");
			System.out.println("Board name is available in search list");
			driver.navigate().to(baseurl);
			Thread.sleep(5000);
			Util.DeleteTheBoard(BoardName_Demo);

			boolean Check_Available = Util.SearchTheBoard(BoardName_Demo);
			Assert.assertEquals(false, Check_Available, "Error: Board name is still available after deleteion in search list");
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			System.out.println("Error :::" + e);
		}
		System.out.println("-----------------------end :::    Test_DeleteBoard-----------------------");
	}

	@AfterMethod
	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}
}
