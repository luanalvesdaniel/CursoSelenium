import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Interagir e validar os frames da página
 * @author Luan Alves Daniel
 *	
 */	

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
		dsl.entrarFrame("frame1");
		
		//clica no botao
		dsl.clicarBotao("frameButton");

		//atribuir a mensagem numa variável
		String msg = dsl.alertaObterTextoEAceita();
		
		//comparar o texto do alert
		Assert.assertEquals("Frame OK!", msg);

		//voltar ao foco da tela principal
		driver.switchTo().defaultContent();
		
		//pegar o texto e inserir no nome
		dsl.escreve("elementosForm:nome", msg);
				
	}
}
