package stepDefinitions;

import static org.junit.Assert.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.cucumber.listener.Reporter;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AutomationFrontendSteps {

	WebDriver driver;
	private String titulo = null;
	private String precio = null;
	
	// Pasos comunes
	
	@Given("^el usuario entra a MercadoLibre$")
	public void el_usuario_entra_a_MercadoLibre() {
		String exePath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.mercadolibre.com.ar");
	};

	@When("^accede a Categorias$")
	public void accede_a_Categorias() throws InterruptedException {
		/*
		driver.findElement(By.linkText("Categorías")).click();
		*/
		Actions action = new Actions(driver);
		WebElement btn = driver.findElement(By.linkText("Categorías"));
		action.moveToElement(btn).perform();
		Thread.sleep(1000);
	}
	
	// Pasos del primer escenario

	@When("^accede a la seccion \"([^\"]*)\"$")
	public void accede_a_la_seccion(String arg1) throws InterruptedException{
		driver.findElement(By.linkText(arg1)).click();
		Thread.sleep(500);
	}

	@When("^accede a la categoria \"([^\"]*)\"$")
	public void accede_a_la_categoria(String arg1) throws Throwable {
		driver.findElement(By.linkText(arg1)).click();
		Thread.sleep(500);
	}

	@Then("^valido que el titulo sea igual a \"([^\"]*)\"$")
	public void valido_que_el_titulo_sea_igual_a(String arg1) throws Throwable {
		assertEquals(driver.findElement(By.className("breadcrumb__title")).getText(), arg1);
	}
	
	@Then("^imprimo cantidad de resultados$")
	public void imprimo_cantidad_de_resultados() {
		String resultados = driver.findElement(By.className("quantity-results")).getText();
		Reporter.addStepLog(resultados);
	}
	
	// Pasos del segundo escenario
	
	@When("^accede a la seccion Tecnología$")
	public void accede_a_la_seccion_Tecnología() throws InterruptedException{
		driver.findElement(By.linkText("Tecnología")).click();
		Thread.sleep(500);
	}

	@When("^accede a la categoria Celulares y Smartphones$")
	public void accede_a_la_categoria_Celulares_y_Smartphones() throws InterruptedException {
		driver.findElement(By.linkText("Celulares y Smartphones")).click();
		Thread.sleep(500);
	}
	
	@When("^filtra por ubicación Capital Federal$")
	public void filtra_por_ubicación_Capital_Federal() throws InterruptedException {
		WebElement seccion = driver.findElement(By.id("id_state"));
		WebElement filtros = seccion.findElement(By.className("filters__group__option"));
		WebElement capfed = filtros.findElement(By.xpath("//*[@title='Capital Federal']"));
		capfed.click();

		Thread.sleep(500);
	}

	@Then("^accede al primer artículo$")
	public void accede_al_primer_artículo() throws InterruptedException{
	    titulo = driver.findElement(By.className("main-title")).getText();
	    precio = driver.findElement(By.className("price__fraction")).getText();
	    Reporter.addStepLog("El título del artículo es: " + titulo);
	    Reporter.addStepLog("El precio del artículo es: " + precio);
	    driver.findElement(By.className("main-title")).click();
	    Thread.sleep(1000);
	}
	
	@Then("^Verifico que el titulo y el precio sean correctos$")
	public void verifico_que_el_titulo_y_el_precio_sean_correctos(){
		assertEquals(driver.findElement(By.className("item-title__primary")).getText(), titulo);
		assertEquals(driver.findElement(By.className("price-tag-fraction")).getText(), precio);
	}
	
	@After()
	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			
		}
	}
	
}