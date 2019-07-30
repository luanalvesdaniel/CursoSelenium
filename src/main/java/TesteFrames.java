import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFrames {

	private WebDriver driver;
	
	@Before
	public void inicializa() {
		//inicializa o driver geckodriver que est� na pasta do path do windows
		driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a p�gina e pega o t�tulo dela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	
	}
		
	@Test
	public void deveInteragirComFrames() {
		
		//mudar o foco para o iframe
		driver.switchTo().frame("frame1");
		
		//clica no botao
		driver.findElement(By.id("frameButton")).click();
		
		//mudar o foco para o alert
		Alert alert = driver.switchTo().alert();
		
		//atribuir a mensagem numa vari�vel
		String msg = alert.getText();
		
		//comparar o texto do alert
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		//voltar ao foco da tela principal
		driver.switchTo().defaultContent();
		
		//pegar o texto e inserir no nome
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
		
	}
}
