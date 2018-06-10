package testing.test;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Examples {
	
	private WebDriver driver;
	private String codeFolder = "C:/Users/ipgb2/Desktop/Pat/Development/Selenium/testpages/";

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
	
	//MULTIPLE SELECT
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
	
	//RADIO BUTTONS
	@Test (enabled=false)
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
	
	//CHECKBOXES
	@Test (enabled=false)
	public void test12() {
		driver.get("https://learn.letskodeit.com/p/practice");
		
		List<WebElement> checks = driver.findElements(By.xpath("//input[@type='checkbox'and@name='cars']"));
		
		checks.forEach(x->System.out.println(x.getText()));
		
		for(WebElement check:checks) {
			
			System.out.println(check.getAttribute("value"));
			if(check.getAttribute("value").equals("bmw")) {
				check.click();
			}
			if(check.getAttribute("value").equals("honda")) {
				check.click();
			}
		}
		
		assert checks.get(0).isSelected()&&checks.get(2).isSelected();
		
		for(WebElement check:checks) {
			
			System.out.println(check.getAttribute("value"));
			if(check.getAttribute("value").equals("bmw")) {
				check.click();
			}
			if(check.getAttribute("value").equals("honda")) {
				check.click();
			}
		}
		
		assert !checks.get(0).isSelected()&&!checks.get(2).isSelected();

	}
	
	//Drag and Drop
	@Test (enabled=false)
	public void test13() {
		driver.get("https://learn.letskodeit.com/p/practice");
		Select fruits = new Select(driver.findElement(By.id("multiple-select-example")));
		List <WebElement> fruitsOptions = fruits.getOptions();
		Actions builder = new Actions(driver);
		builder.click(fruitsOptions.get(0))
			.keyDown(Keys.LEFT_SHIFT)
			.click(fruitsOptions.get(2))
			//.keyUp(Keys.COMMAND)
			.perform();
		List <WebElement> selected = fruits.getAllSelectedOptions();
		selected.forEach(x->System.out.println(x.getText()));
	}
	
	//Double click
	@Test (enabled=false)
	public void test14(){
		driver.get("file://"+codeFolder+"doubleclick.html");
		
		WebElement elemento = driver.findElement(By.id("message"));
		System.out.println(elemento.getCssValue("background-color"));
		Actions builder = new Actions(driver);
		builder.doubleClick(elemento).perform();
		System.out.println(elemento.getCssValue("background-color"));
	}
	
	//Drag and Drop
	@Test (enabled=false)
	public void test15() throws InterruptedException{
		driver.get("file://"+codeFolder+"draganddrop.html");
		
		WebElement e1 = driver.findElement(By.id("draggable"));
		WebElement e2 = driver.findElement(By.id("droppable"));
		System.out.println(e2.getText());
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.dragAndDrop(e1, e2).perform();
		System.out.println(e2.getText());
		
		assert e2.getText().equals("Dropped!");
	}
	
	
	//RIGHT CLICK
	@Test (enabled=false)
	public void test16(){
		driver.get("http://medialize.github.io/jQuery-contextMenu/demo.html");
		WebElement elemento = driver.findElement(By.cssSelector("span.context-menu-one"));
		
		Actions builder = new Actions(driver);
		builder.contextClick(elemento).build().perform();
		
		WebElement e2 = driver.findElement(By.cssSelector(".context-menu-item.context-menu-icon.context-menu-icon-edit"));
		System.out.println(e2.getText());
	}
	
	//Maximize Window
	@Test (enabled=false)
	public void test17() throws InterruptedException{
		driver.get("file://"+codeFolder+"draganddrop.html");
		driver.manage().window().maximize();
		//minimize method doesn't exist getSize() and setSize() allow you to specify a size
		Thread.sleep(2000);
		Dimension d = new Dimension(500,600);
		driver.manage().window().setSize(d);
		Thread.sleep(2000);
	}
	
	//Navigating Buttons (forward, refresh,etc)
	@Test (enabled=false)
	public void test18() throws InterruptedException {
		driver.navigate().to("http://www.google.com");
		Thread.sleep(2000);
		driver.navigate().to("https://fb.com");
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		Thread.sleep(2000);
		driver.navigate().refresh();
	}
	
	//Handling Session  Cookies
	@Test (enabled=false)
	public void test19() throws InterruptedException {
		driver.get("https://www.huffingtonpost.com");
		
		WebElement editionBtn = driver.findElement(By.cssSelector("div[class^=editions__current-edition__edition]"));
		editionBtn.click();
		
		WebElement usEdition = driver.findElement(By.xpath("//a[contains(text(),'United States')]"));
		Thread.sleep(5000);
		usEdition.click();
		
		Cookie country = driver.manage().getCookieNamed("country_view");
		System.out.println(country.getValue());
	}

	//ImplicitWait and ExplicitWait
	@Test (enabled=false)
	public void test20(){
		driver.get("https://www.huffingtonpost.com");
		
		WebElement editionBtn = driver.findElement(By.cssSelector("div[class^=editions__current-edition__edition]"));
		editionBtn.click();
		
		WebElement usEdition = driver.findElement(By.xpath("//a[contains(text(),'United States')]"));
		//Implicit Wait
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(usEdition));
		usEdition.click();
		
		Cookie country = driver.manage().getCookieNamed("country_view");
		System.out.println(country.getValue());
	}
	
	//Frames
	@Test (enabled=false)
	public void test21(){
		driver.get("file://"+codeFolder+"frames.html");
		//To use different frames inside a window, first we should get current window "direction"
		String currentWindow = driver.getWindowHandle();
		
		//then we can move inside its frames, and get into an specific frame
		driver.switchTo().frame("left");
		WebElement eLeft = driver.findElement(By.tagName("p"));
		System.out.println(eLeft.getText());
		
		//if we want to move to another frame, first we have to go back to "parent" window
		driver.switchTo().window(currentWindow);
		
		//now, we can move to another frame
		driver.switchTo().frame("middle");
		WebElement eMid = driver.findElement(By.tagName("p"));
		System.out.println(eMid.getText());
		
		//Go back to parent Window
		driver.switchTo().window(currentWindow);
		
		//Move to another frame, do not forget that frames also have id's (left, middle, right in this example)
		driver.switchTo().frame("right");
		WebElement eRight = driver.findElement(By.tagName("p"));
		System.out.println(eRight.getText());
	}
	
	
	//Frames
	@Test (enabled=true)
	public void test22(){
		//The difference between frame and iframe is that iframe comes from an external source
		driver.get("file://"+codeFolder+"iframes.html");
		
		//for iframes we also need to get the "direction" of the current window
		String currentWindow = driver.getWindowHandle();
		
		//then we need to instanciate a WebElement to get iframe id
		WebElement iframe1 = driver.findElement(By.id("loonycorn"));
		
		//And now we can move to that frame as we did before (in frames)
		driver.switchTo().frame(iframe1);
		WebElement e1 = driver.findElement(By.tagName("p"));
		System.out.println(e1.getText());
		
		//We also need to go back to "parent" window so we can change to another iframe
		driver.switchTo().window(currentWindow);
		
		//And again go inside another iframe
		WebElement iframe2 = driver.findElement(By.id("selenium"));
		driver.switchTo().frame(iframe2);
		WebElement e2 = driver.findElement(By.tagName("p"));
		System.out.println(e2.getText());
		
		
	}
	
	
	@AfterTest
	public void cleanUp() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
}
