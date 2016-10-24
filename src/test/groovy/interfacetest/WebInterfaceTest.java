package interfacetest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.Selenium;

public class WebInterfaceTest {

	private static ChromeDriverService service;
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.get("http://localhost:8080");
	}

	@BeforeClass
	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("C:/Users/Maxwell/Downloads/chromedriver.exe")).usingAnyFreePort()
				.build();
		service.start();
	}

	@AfterClass
	public static void createAndStopService() {
		// service.stop();
	}

	// @Before
	// public void createDriver() {
	// driver = new RemoteWebDriver(service.getUrl(),
	// DesiredCapabilities.chrome());
	// driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	// driver.get("http://localhost:8080");
	// }

	@After
	public void closeDriver() {
		// driver.close();
	}

	@Test
	public void buttonMatriculaTest() {
		driver.get("http://localhost:8080");

		WebElement element = driver.findElement(By.className("button-init"));
		element.click();
		
		String currentUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/matricula", currentUrl);
	}
	
	@Test
	public void matriculaTest() {
		driver.get("http://localhost:8080/matricula");

		WebElement element = driver.findElement(By.partialLinkText("AlunoTeste"));
		element.click();
		
		element = driver.findElement(By.partialLinkText("Projeto de Sistema de Software I"));
		element.click();
		
		WebElement message = driver.findElement(By.className("message"));
		
		if(!message.equals(null)) {
			assertEquals("A matrícula foi efetuada com sucesso!", message.getText());
		}
	}
}