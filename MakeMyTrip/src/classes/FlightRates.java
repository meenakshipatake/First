package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlightRates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

System.setProperty("webdriver.chrome.driver", "C:\\Users\\Meenu\\Desktop\\selenium\\chromedriver_win32 (2)\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.makemytrip.com/flight/search?itinerary=PNQ-NAG-20/07/2019&tripType=O&paxType=A-2_C-1_I-1&intl=false&=&cabinClass=PE");
		
		driver.manage().window().maximize();
		
	
		
		List<String> price_list=new ArrayList<String>();
		
		driver.findElement(By.xpath("//div[@class='sortby-dom-sctn dep']/span[contains(text(),'Departure')]")).click();
		
		
		List<WebElement> price_WE=driver.findElements(By.xpath("//span[@class='actual-price']"));
		for(WebElement temp:price_WE)
		{
			price_list.add(temp.getText());
		}
		
		
		
		System.out.println(price_list);
		
		Collections.sort(price_list);
		System.out.println(price_list);
		
		int size=price_list.size();
		
		System.out.println(price_list.get(size-1));
		
	}

}
