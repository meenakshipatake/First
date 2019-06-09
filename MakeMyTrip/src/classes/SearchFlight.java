package classes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SearchFlight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String from="Mumbai";
		String to="Delhi";
		String month_year="July 2019";
		String date="20";
		String num_adults = "2";
		String num_children="1";
		String num_infant="1";
		String travel_type="Economy";

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Meenu\\Desktop\\selenium\\chromedriver_win32 (2)\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.makemytrip.com/international-flights/");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		Actions actions=new Actions(driver);
		
		//actions.click(driver.findElement(By.xpath("//span[@class='truncate airPortName']"))).build().perform();
		
		actions.click(driver.findElement(By.xpath("//span[@class='lbl_input latoBold  appendBottom5'][contains(text(),'From')]"))).build().perform();

		//actions.contextClick(driver.findElement(By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']")));
		
		
		WebElement fromCity=driver.findElement(By.xpath("//input[@placeholder='From']"));
	//	actions.doubleClick(fromCity);
		fromCity.sendKeys(from);
		
		
		
		//driver.findElement(By.xpath("//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")).click();
		
		List<WebElement> from_web=driver.findElements(By.xpath("//div[@class='calc60']"));
		
		System.out.println(from_web.size());
		
		
		
		
		for(WebElement temp:from_web)
		{
			WebElement inner=temp.findElement(By.tagName("p"));
			
			
			if(inner.getText().contains(from))
			{
				temp.click();
				//System.out.println(inner.getText());
				break;
				
			}
			
			
			
		}
		
		
		
		actions.click(driver.findElement(By.xpath("//span[@class='lbl_input latoBold  appendBottom5'][contains(text(),'To')]"))).build().perform();
		
		WebElement toCity=driver.findElement(By.xpath("//input[@placeholder='To']"));
		//	actions.doubleClick(fromCity);
			toCity.sendKeys(to);
			
			
			
			//driver.findElement(By.xpath("//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")).click();
			List<WebElement> to_web=driver.findElements(By.xpath("//div[@class='calc60']"));
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for(WebElement temp:to_web)
			{
				WebElement inner=temp.findElement(By.tagName("p"));
				if(inner.getText().contains(to))
				{
					temp.click();
					//System.out.println(inner.getText());
					break;
					
				}
				
				
				
			}
			
			
			//Date Logic
			driver.findElement(By.xpath("//span[@class='lbl_input latoBold appendBottom10'][contains(text(),'DEPARTURE')]")).click();
			
			WebElement monthyear=driver.findElement(By.xpath("//div[@class='DayPicker-Caption'][1]"));
			
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
			
			
		
			//Numberof traveller
			
			
			driver.findElement(By.xpath("//span[@class='appendRight10']")).click();
			
			
			WebElement adults=driver.findElement(By.xpath("//div[@class='appendBottom20']/p[@class='latoBold font12 grayText appendBottom10'][contains(text(),'ADULTS')]//following::ul"));
			
			List<WebElement> num_adults_web=adults.findElements(By.tagName("li"));
			
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
			
WebElement children=driver.findElement(By.xpath("//div[@class='appendBottom20']/p[@class='latoBold font12 grayText appendBottom10'][contains(text(),'CHILDREN')]//following::ul"));
			
			List<WebElement> num_children_web=children.findElements(By.tagName("li"));
			
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
			
WebElement infants=driver.findElement(By.xpath("//div[@class='appendBottom20']/p[@class='latoBold font12 grayText appendBottom10'][contains(text(),'INFANT')]//following::ul"));
			
			List<WebElement> num_infants_web=infants.findElements(By.tagName("li"));
			
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
			//driver.manage().deleteAllCookies();
			driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']")).click();
			
			
			
			
			
			
			
			driver.findElement(By.xpath("//div[@class='sortby-dom-sctn dep']/span[contains(text(),'Departure')]")).click();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			//price
			
			try {
				Robot robot= new Robot();
				for(int i=0;i<30;i++)
				{
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
				}
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				
			//	robot.keyPress(KeyEvent.VK_ENTER);
			//	robot.keyRelease(KeyEvent.VK_ENTER);
				
				
				
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			List<String> price_list=new ArrayList<String>();
			
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<WebElement> price_WE=driver.findElements(By.xpath("//span[@class='actual-price']"));
			for(WebElement temp:price_WE)
			{
				price_list.add(temp.getText());
			}
			
			
			
			System.out.println(price_list);
			
			List<Integer> list_int=new ArrayList<Integer>();
			
			for(String str:price_list)
			{
				String temp;
				String temp2[]=str.split(" ");
				//temp=str.replace("\\u20B9", "");
				//temp=temp[1].replace(" ", "");
				temp=temp2[1].replace(",", "");
				list_int.add(Integer.parseInt(temp));
			}
			
			Collections.sort(list_int);
			
			for(Integer x:list_int)
			{
				System.out.println(x);
			}
			
			int size=list_int.size();
			System.out.println("size price list"+size);
			System.out.println("Highest price is : "+list_int.get(size-1));
			
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();
	

	}
}
