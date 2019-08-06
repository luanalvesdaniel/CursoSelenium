import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Interagir e validar os frames e janelas da página
 * @author Luan Alves Daniel
 *	
 */

public class TesteFramesEJanelas {

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
		dsl.sairFrame();
		
		//pegar o texto e inserir no nome
		dsl.escreve("elementosForm:nome", msg);				
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		//buscar o elemento dentro do iframe para usar o JS
		WebElement frame = driver.findElement(By.id("frame2"));
		//usar o scrollby passando a posição exata do elemento como parâmetro ao JS
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		
	}
	
	@Test
	public void deveInteragirComJanelas() {
		
		dsl.clicarBotao("buttonPopUpEasy");
		
		//mudar o foco para a janela popup
		dsl.trocarJanela("Popup");
		
		//escrever no campo
		dsl.escreve(By.tagName("textarea"),("Deu certo?"));
		
		//fechar a popup
		driver.close();
		
		//voltar o foco a tela principal (anterior)
		dsl.trocarJanela("");
		
		//prencher no campo de textarea
		dsl.escreve(By.tagName("textarea"), "e agora?");

	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		
		dsl.clicarBotao("buttonPopUpHard");
			
		//pegar o id de forma dinâmica visto que o valor muda a toda execução e focar na popup
		//o String dentro do Window() é devido ao método window esperar uma string
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		
		//escrever no textarea da popup
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		
		//voltar o foco para a tela principal
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		
		//escrever no textarea da tela principal
		dsl.escreve(By.tagName("textarea"), "e agora?");
		
	}
}
