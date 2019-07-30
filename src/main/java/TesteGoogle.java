import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		//inicializa o driver geckodriver que est� na pasta do path do windows
		driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a p�gina e pega o t�tulo dela
		driver.get("http://www.google.com");
		
	}
	
	@After
	public void finaliza() {
		driver.quit();
	
	}
	
	
	@Test
	public void teste() {
//		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");

		//compara o t�tulo da p�gina onde "Google" � o esperado e o driver.getTitle() � o t�tulo da p�gina real 
		Assert.assertEquals("Google", driver.getTitle());
		
	}

}
