package testing.test;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class Examples {
	
	private WebDriver driver;
	private String codeFolder = "C:/Users/ipgb2/Desktop/Pat/Development/testpages/";

	@BeforeTest 
	public void setUP() {
		System.setProperty("webdriver.chrome.driver","/Users/ipgb2/Development/chromedriver.exe");
		driver = new ChromeDriver();
		
		
	}
	
	@Test (enabled=false)
	public void testDriver() {
		//System.setProperty("webdriver.chrome.driver","‪C:\\Users\\ipgb2\\Development\\chromedriver.exe");
		driver.get("https://www.google.com");
		
		assert driver.getTitle().startsWith("Google");
	}
	
	@Test (enabled=false)
	public void test1() {
		driver.get("file://"+codeFolder+"scraping.html");
		List<WebElement> elementos = driver.findElements(By.className("course-title"));
		
		for (WebElement element:elementos) {
			System.out.println(element.getText());
		}
		
		elementos.forEach(x->System.out.println(x.getText()));
	}
	
	@Test (enabled=false)
	public void test2() {
		driver.get("https://learn.letskodeit.com/p/practice");
		List<WebElement> elementos = driver.findElements(By.id("radio-btn-example"));
		elementos.forEach(x->System.out.println(x.getText()));
	}
	
	@Test (enabled=false)
	public void test3() {
		driver.get("file://"+codeFolder+"scraping.html");
		WebElement link = driver.findElement(By.linkText("Location"));
		System.out.println(link.getAttribute("href"));
		
		WebElement link2 = driver.findElement(By.partialLinkText("Contact"));
		System.out.println(link2.getAttribute("href"));
		
	}
	
	@Test (enabled=false)
	public void test4() {
		driver.get("https://google.com");
		WebElement text = driver.findElement(By.name("q"));
		text.sendKeys("Sergio Garcia Gonzalez",Keys.ENTER);
		WebElement btn = driver.findElement(By.partialLinkText("Imágenes"));
		btn.click();
		WebElement link = driver.findElement(By.id("VVCm327L9cppjM:"));
		link.click();
		
	}
	
	@Test (enabled=false)
	public void test5() {
		driver.get("file://"+codeFolder+"scraping.html");
		List <WebElement> links = driver.findElements(By.tagName("a"));
		links.forEach(x->System.out.println(x.getText()));
	}
	
	@Test (enabled=false)
	public void test6() {
		driver.get("file://"+codeFolder+"scraping.html");
		WebElement elemento = driver.findElement(By.className("author-about"));
		
		System.out.println("Text: "+elemento.getText());
		System.out.println("Tag: " +elemento.getTagName());
		System.out.println("Color: "+elemento.getCssValue("color"));
		System.out.println("Location X: " + elemento.getLocation().getX());
		System.out.println("Location Y:" + elemento.getLocation().getY());
		System.out.println("Size Width: " + elemento.getSize().getWidth());
		System.out.println("Size Height: " + elemento.getSize().getHeight());
		System.out.println("Name: "+elemento.getAttribute("name"));
	}
	
	//Getting information from tables
	@Test (enabled=false)
	public void test7() {
		driver.get("file://"+codeFolder+"scrapeprices.html");
		List <WebElement> rows = driver.findElements(By.tagName("tr"));
		assert rows.size()==5;
		
		//We have to create an String builder to collect all the text from the table
		StringBuilder rowText = new StringBuilder();
		
		for(WebElement row:rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			System.out.println("dentro del row: " + row.getText());
			for (WebElement col:cols) {
				System.out.println("dentro del cols:" + col.getText());
				rowText.append(col.getText()+"\t");
			}
			rowText.append("\n");
		}
		
		System.out.println(rowText);
	}
	
	//CSS Selectors
	@Test (enabled=false)
	public void test8() throws InterruptedException {
		driver.get("file://"+codeFolder+"button.html");
		WebElement btn1 = driver.findElement(By.id("increase"));
		//btn1.click();
		Thread.sleep(2000);
		WebElement btn2 = driver.findElement(By.id("decrease"));
		
		int xoffset = btn2.getLocation().getX() - btn1.getLocation().getX();
		System.out.println("Boton 2: "+btn2.getLocation().getX()+"Boton 1: "+btn1.getLocation().getX()+" "+btn1.getLocation().getY());
		System.out.println(xoffset);
		
		Actions builder = new Actions(driver);
		builder.moveByOffset(9, 9).click().perform();
		Thread.sleep(2000);
		builder.moveByOffset(-xoffset-2, 0).click().perform();
	}
	
	//DROPDOWN LIST
	@Test (enabled=false)
	public void test9() {
		driver.get("https://learn.letskodeit.com/p/practice");
		Select carDD = new Select(driver.findElement(By.id("carselect")));
		
		carDD.getOptions().forEach(x->System.out.println(x.getText()));
		
		carDD.selectByValue("benz");
		assert carDD.getFirstSelectedOption().getText().equals("Benz");		
	}
	
	@Test (enabled=false)
	public void test10() {
		driver.get("https://learn.letskodeit.com/p/practice");
		Select carDD = new Select(driver.findElement(By.id("multiple-select-example")));
		
		carDD.getOptions().forEach(x->System.out.println(x.getText()));
		
		carDD.selectByValue("apple");
		carDD.selectByValue("peach");
		
		carDD.getAllSelectedOptions().forEach(x->assertTrue(x.getText().equals("Apple")||x.getText().equals("Peach")));
		
		carDD.deselectAll();
		//assert carDD.getFirstSelectedOption().getText().equals("Benz");		
	}
	
	@Test (enabled=true)
	public void test11() {
		driver.get("https://learn.letskodeit.com/p/practice");
		
		List<WebElement> radios = driver.findElements(By.xpath("//input[@type='radio'and@name='cars']"));
		
		radios.forEach(x->System.out.println(x.getText()));
		
		for(WebElement radio:radios) {
			
			System.out.println(radio.getAttribute("value"));
			if(radio.getAttribute("value").equals("benz")) {
				radio.click();
			}
		}
		
		assert radios.get(1).isSelected();

	}
	
	@AfterTest
	public void cleanUp() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
}
