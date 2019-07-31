import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
				
		//pedir ao selenium alterar o foco ao alerta, pois a tela de alert está fora da página
		//como retorna um alert, capturaremos essa variável
		Alert alert = dsl.mudaFocoAlert();
		
		//atribuir o texto do alert à uma variável
		String texto = alert.getText();
		
		//validar a mensagem da tela de alert
		Assert.assertEquals("Alert Simples", texto);
		
		//para tirar o alert da tela para possibilizar o teste a seguir
		alert.accept();
		
		//pegar o texto do alert e inserir o mesmo texto no campo Nome
		dsl.escreve("elementosForm:nome", texto);
				
	}
	
	@Test	
	public void deveInteragirComAlertConfirm() {
		
		//realizar o clique no botão confirm
		dsl.clicarBotao("confirm");
				
		//mudar o foco para a tela de confirm
		Alert alerta = dsl.mudaFocoAlert();
		
		//aceitar a tela de confirm
		alerta.accept();
		
		//pegar o texto após confirmar para validar se aparece "confirmar"
		String confirmado = alerta.getText();
		
		//validar a mensagem em tela com o texto do alert de confirm
		//em seguida dar click no ok para fechar a tela
		Assert.assertEquals("Confirmado", confirmado);
		alerta.accept();
		
		//teste com o cancelar do confirm
		//realizar o clique no botão confirm
		dsl.clicarBotao("confirm");
				
		//cancelar o confirm
		alerta.dismiss();
		
		//pegar o texto e validar se foi cancelado
		confirmado = alerta.getText();
				
		//validar o texto em tela e sair da mesma
		Assert.assertEquals("Negado", confirmado);
		alerta.accept();
		
	}
	
	@Test	
	public void deveInteragirComAlertPrompt() {
	
		//clicar no alert
		dsl.clicarBotao("prompt");
			
		//mudar o foco para o alert
		Alert alertp = dsl.mudaFocoAlert();
		
		//validar se aparece o texto em tela
		Assert.assertEquals("Digite um numero", alertp.getText());
		
		//digitar um valor (parecido com textfield)
		alertp.sendKeys("12");
		
		//dar ok após digitar o valor
		alertp.accept();
		
		//validar a pergunta após o valor informado
		Assert.assertEquals("Era 12?", alertp.getText());
		
		//dar um ok para validar a próxima mensagem
		alertp.accept();
		Assert.assertEquals(":D", alertp.getText());
		
		alertp.accept();
		
	}
}
