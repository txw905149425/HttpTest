package jibin;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.*;
public class SeleniumTest {

	public static void main(String[] args) {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("network.proxy.no_proxies_on","localhost");
		System.setProperty("webdriver.firefox.bin","D:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		new FirefoxDriver(firefoxProfile);
		
				
//		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(60,  TimeUnit.SECONDS);
//		driver.get("http://www.creditchina.gov.cn/toPunishList#getNo=1");
//		Thread.sleep(3000);
//		String html=driver.getPageSource();
//		Actions action = new Actions(driver);
//		WebElement but =driver.findElement(By.cssSelector("#promptspage > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > a"));
//		action.click(but).perform();
		
	
	
	}
	
	
}
