import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
//		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");

		//inicializa o driver geckodriver que est� na pasta do path do windows
		WebDriver driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
//		driver.manage().window().setPosition(new Point(100, 100));
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicia maximizado
//		driver.manage().window().maximize();
		
		//inicializa a p�gina e pega o t�tulo dela
		driver.get("http://www.google.com");
//		System.out.println(driver.getTitle());

		//compara o t�tulo da p�gina onde "Google" � o esperado e o driver.getTitle() � o t�tulo da p�gina real 
		Assert.assertEquals("Google", driver.getTitle());
		
		//sempre fechar o navegador e o servi�o do gecko
		driver.quit();
	}

}
