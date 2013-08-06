import java.sql.Driver;

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver

driver = { new FirefoxDriver() }
/*driver = {
	def chromeDriver = new File('../WebTestLibs/bin/chromedriver')
	System.setProperty('webdriver.chrome.driver', chromeDriver.absolutePath)
	new ChromeDriver()
}*/
baseUrl = "http://localhost/pi/"