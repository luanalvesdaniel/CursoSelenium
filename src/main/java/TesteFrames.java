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
	private DSL dsl;
	
	@Before
	public void inicializa() {
		//inicializa o driver geckodriver que está na pasta do path do windows
		driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a página e pega o título dela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//inicializa a DSL
		dsl = new DSL(driver);
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
		dsl.clicarBotao("frameButton");
				
		//mudar o foco para o alert
		Alert alert = dsl.mudaFocoAlert();
		
		//atribuir a mensagem numa variável
		String msg = alert.getText();
		
		//comparar o texto do alert
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		//voltar ao foco da tela principal
		driver.switchTo().defaultContent();
		
		//pegar o texto e inserir no nome
		dsl.escreve("elementosForm:nome", msg);
				
	}
}
