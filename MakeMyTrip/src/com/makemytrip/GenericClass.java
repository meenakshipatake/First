package com.makemytrip;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class GenericClass {

	
	/*
	 * This method initiates the WebDriver with the object of ChromeDriver
	 * */
	public WebDriver initiateDriver()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Meenu\\Desktop\\selenium\\chromedriver_win32 (2)\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		return driver;
	}
	
	
	/*
	 * This method opens the URL passed in the method
	 * This also maximizes the window , delete cookies and add implicit wait
	 * */
	public void openUrl(WebDriver driver,String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	
	public void searchFlight(WebDriver driver,String from,String to ,String month_year,String date,String num_adults,String num_children,String num_infant,String travel_type)
	{
		
		Actions actions=new Actions(driver);

		/*
		 * Logic to input value in "From" Field
		 * */
		
		//Click on the WebElement and the send keys on the input "from" field
		actions.click(driver.findElement(By.xpath("//span[@class='lbl_input latoBold  appendBottom5'][contains(text(),'From')]"))).build().perform();
		WebElement fromCity=driver.findElement(By.xpath("//input[@placeholder='From']"));
		fromCity.sendKeys(from);
		
		//Click on the desired city name in the dropdown
		List<WebElement> from_web=driver.findElements(By.xpath("//div[@class='calc60']"));
		for(WebElement temp:from_web)
		{
			WebElement inner=temp.findElement(By.tagName("p"));
			if(inner.getText().contains(from))
			{
				temp.click();
				break;	
			}		
		}
		
		
		/*
		 * Logic to input value in "To" Field
		 * */
		//Click on the WebElement and the send keys on the input "To" field
		actions.click(driver.findElement(By.xpath("//span[@class='lbl_input latoBold  appendBottom5'][contains(text(),'To')]"))).build().perform();
		WebElement toCity=driver.findElement(By.xpath("//input[@placeholder='To']"));
		toCity.sendKeys(to);
		
		//Click on the desired city name in the dropdown
		List<WebElement> to_web=driver.findElements(By.xpath("//div[@class='calc60']"));
		for(WebElement temp:to_web)
			{
				WebElement inner=temp.findElement(By.tagName("p"));
				if(inner.getText().contains(to))
				{
					temp.click();
					break;	
				}
			}
			
	/*****************************************************************************************************************************/		
		//Date Logic
		driver.findElement(By.xpath("//span[@class='lbl_input latoBold appendBottom10'][contains(text(),'DEPARTURE')]")).click();
		WebElement monthyear=driver.findElement(By.xpath("//div[@class='DayPicker-Caption'][1]"));
		
		//Traverse through the loop until the desired "Month Year" 
		WebElement next=driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']"));
		while(!monthyear.getText().equals(month_year))
			{
				next.click();
			}
			
		List<WebElement> date_web=driver.findElements(By.xpath("//div[@class='dateInnerCell']"));
			
		for(WebElement temp:date_web)
		{
			if(temp.findElement(By.tagName("p")).getText().equals(date))
			{
				temp.click();
				break;
			}
		}
			
		/*****************************************************************************************************************************/		
		
		//Logic to select No. of Adults
		driver.findElement(By.xpath("//span[@class='appendRight10']")).click();
		WebElement adults=driver.findElement(By.xpath("//div[@class='appendBottom20']/p[@class='latoBold font12 grayText appendBottom10'][contains(text(),'ADULTS')]//following::ul"));
		List<WebElement> num_adults_web=adults.findElements(By.tagName("li"));
		//if number of adults is more than 1 then enter loop and selected required number of adults
		if(!num_adults.equals("1"))

		{
			for(WebElement temp:num_adults_web)
			{
				if (temp.getText().equals(num_adults))
				{
					temp.click();
					break;
				}
			}

		}

		//Logic to select No. of children
		WebElement children=driver.findElement(By.xpath("//div[@class='appendBottom20']/p[@class='latoBold font12 grayText appendBottom10'][contains(text(),'CHILDREN')]//following::ul"));
		List<WebElement> num_children_web=children.findElements(By.tagName("li"));
		//if number of children is more than 0 then enter loop and selected required number of children
		if(!num_children.equals("0"))
		{
			for(WebElement temp:num_children_web)
			{
				if (temp.getText().equals(num_children))
				{
					temp.click();
					break;
				}
			}

		}

		//Logic to select No. of infant
		WebElement infants=driver.findElement(By.xpath("//div[@class='appendBottom20']/p[@class='latoBold font12 grayText appendBottom10'][contains(text(),'INFANT')]//following::ul"));
		List<WebElement> num_infants_web=infants.findElements(By.tagName("li"));
		//if number of infants is more than 0 then enter loop and select required number of infants
		if(!num_infant.equals("0"))
		{
			for(WebElement temp:num_infants_web)
			{
				if (temp.getText().equals(num_infant))
				{
					temp.click();
					break;
				}
			}

		}

		//Logic to select travel type
		WebElement traveltype=driver.findElement(By.xpath("//ul[@class='guestCounter classSelect font12 darkText']"));
		List<WebElement> all_traveltypes=traveltype.findElements(By.tagName("li"));
		if(!travel_type.equals("Economy"))
		{
			for(WebElement temp:all_traveltypes)
			{
				if(temp.getText().equals(travel_type))
				{
					temp.click();
					break;
				}
			}

		}

		
		driver.findElement(By.xpath("//button[@class='primaryBtn btnApply pushRight ']")).click();
		//Click on the search flight button
		driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']")).click();


	}

	
	
	/*
	 * 
	 * Method to calculate the highest fare of the flight
	 * 
	 * */
	public int calculateHighestFlightRate(WebDriver driver)
	{

		//Scrolling down the page to reach end of the page
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		
		/*try {
			Robot robot= new Robot();
			for(int i=0;i<40;i++)
			{
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
			}
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
*/
		//To fetch the flight rates
		List<String> price_list=new ArrayList<String>();
		List<WebElement> price_WE=driver.findElements(By.xpath("//span[@class='actual-price']"));
		for(WebElement temp:price_WE)
		{
			price_list.add(temp.getText());
		}

		//Calling method ConvertStringToInt for converting String ArrayList to Int Array List
		List<Integer> list_int=convertStringToInt(driver, price_list);
		
		//Logic to find out highest flight rate by sorting the ArrayList
		Collections.sort(list_int);
		for(Integer x:list_int)
		{
			System.out.println(x);
		}

		int size=list_int.size();
		System.out.println("size price list"+size);
		System.out.println("Highest price is : "+list_int.get(size-1));

		return list_int.get(size-1);
	}
	
	
	
	/*
	 * 
	 * Method to covert string array which are having values as for ex "Rs 1,234" to integer format for ex 1234 
	 * 
	 * */
	public List<Integer> convertStringToInt(WebDriver driver,List<String> price_list)
	{
		List<Integer> list_int=new ArrayList<Integer>();
		for(String str:price_list)
		{
			String temp;
			String temp2[]=str.split(" ");
			temp=temp2[1].replace(",", "");
			list_int.add(Integer.parseInt(temp));
		}
		return list_int;
	}
}
