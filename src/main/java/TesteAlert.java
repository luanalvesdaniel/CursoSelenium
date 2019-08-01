import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Valida e verifica os textos dos alertas da página
 * @author Luan Alves Daniel
 *	
 */

public class TesteAlert {

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
	public void deveInteragirComAlertSimples() {
	
		//encontrar o botão que executa um alert simples
		dsl.clicarBotao("alert");
		
		//atribuir o texto do alert à uma variável
		String texto = dsl.alertaObterTextoEAceita();
		
		//validar a mensagem da tela de alert
		Assert.assertEquals("Alert Simples", texto);
		
		//pegar o texto do alert e inserir o mesmo texto no campo Nome
		dsl.escreve("elementosForm:nome", texto);
				
	}
	
	@Test	
	public void deveInteragirComAlertConfirm() {
		
		//realizar o clique no botão confirm
		dsl.clicarBotao("confirm");
				
		//validar a mensagem em tela com o texto do alert de confirm
		//em seguida dar click no ok para fechar a tela
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		//teste com o cancelar do confirm
		//realizar o clique no botão confirm
		dsl.clicarBotao("confirm");
				
		//validar o texto em tela e sair da mesma
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
		
	}
	
	@Test	
	public void deveInteragirComAlertPrompt() {
	
		//clicar no alert
		dsl.clicarBotao("prompt");
			
		//validar se aparece o texto em tela
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		
		//digitar um valor (parecido com textfield)
		dsl.alertaEscrever("12");
		
		//validar a pergunta após o valor informado
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		
		//validar a próxima mensagem
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
				
	}
}
