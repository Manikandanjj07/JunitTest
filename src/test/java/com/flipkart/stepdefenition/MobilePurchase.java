package com.flipkart.stepdefenition;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class MobilePurchase {
	public static WebDriver driver;
@BeforeClass
	public static void launchbrowser() throws InterruptedException{
	System.out.println("Task Start");
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\DD\\workspace\\SeleniumNew\\Driver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.flipkart.com/");
	driver.manage().window().maximize();
	
	}
@Before				
	public void bf()
	{
		System.out.println("Start Time");
		System.out.println(java.time.LocalTime.now());
	} 
@Test
public void m1()
	{
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//button[text()='✕']")).click();
	}
 @Test
	public void m2() throws Exception 
	{
	System.out.println("SEARCH MOBILE & EXCEL CREATION");
	driver.findElement(By.name("q")).sendKeys("iphone", Keys.ENTER);
	driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	List<String> al = new ArrayList<String>();
	List<WebElement> products = driver.findElements(By.xpath("(//a//div//div//div[contains(text(),'APPLE iPhone')])"));
	for (WebElement x : products){
	String text = x.getText();
	al.add(text);
	 }
	File ip = new File("C:\\Users\\DD\\Desktop\\ip1.xlsx"); 
	FileInputStream f = new FileInputStream(ip);
	Workbook wb = new XSSFWorkbook(f);
	Sheet sh = wb.createSheet("Models"); 
	for(int i =0;i<al.size(); i++)
	   {
	Row cr = sh.createRow(i);
		Cell cc = cr.createCell(0);
		cc.setCellValue(al.get(i));
	   }
		FileOutputStream v = new FileOutputStream(ip);
		wb.write(v);
	}
 @Test
	public void m3()
	{
	 	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	 	driver.findElement(By.xpath("//span[text()='Filters']")).click();
	 	System.out.println("SWITCHING TAB");
	 	driver.findElement(By.xpath("(//a//div//div//div[contains(text(),'APPLE iPhone')])[2]")).click();
	 	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);		
	 	Set<String> wH = driver.getWindowHandles();
	 	List<String> listWindow = new ArrayList<String>(wH);
	 	driver.switchTo().window(listWindow.get(1));
	}
 @Test
	public void m4() throws Exception
	{
	 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		TakesScreenshot ts =(TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File trg= new File("C:\\Users\\DD\\workspace\\JunitTest\\target\\newwindow.png");
		FileUtils.copyFile(src, trg);
		WebElement product = driver.findElement(By.tagName("h1"));
		String productName = product.getText();
		
		File ip = new File("C:\\Users\\DD\\Desktop\\ip1.xlsx"); 
		FileInputStream f = new FileInputStream(ip);
		Workbook wb = new XSSFWorkbook(f);
		Sheet sheet = wb.getSheet("Models");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String cellValue = cell.getStringCellValue();
		
		Assert.assertEquals(productName, cellValue);
		System.out.println("ASSERT PASSED -Values Equal ");
			}
 @Test
	public void m5() throws Exception
	{
	 	System.out.println("Specifications screenshot");
	 	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	 	JavascriptExecutor js =(JavascriptExecutor)driver;
	 	WebElement spec = driver.findElement(By.xpath("//div[text()='Specifications']"));
	 	js.executeScript("arguments[0].scrollIntoView(true)", spec);
	 	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		TakesScreenshot ts =(TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File trg= new File("C:\\Users\\DD\\workspace\\JunitTest\\target\\specification.png");
		FileUtils.copyFile(src, trg);
			}
 @After
	public void af()
	{
	 	System.out.println("End Time");
	 	System.out.println(java.time.LocalTime.now());
	}
@AfterClass
	public static void quitb()
	{
		System.out.println("BROWSER CLOSED");
		driver.quit();
		}
}
