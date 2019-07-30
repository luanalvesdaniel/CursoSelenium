import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {

	@Test	
	public void deveInteragirComAlertSimples() {
		
		//inicializa o driver geckodriver que est� na pasta do path do windows
		WebDriver driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a p�gina e pega o t�tulo dela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		//encontrar o bot�o que executa um alert simples
		driver.findElement(By.id("alert")).click();
		
		//pedir ao selenium alterar o foco ao alerta, pois a tela de alert est� fora da p�gina
		//como retorna um alert, capturaremos essa vari�vel
		Alert alert = driver.switchTo().alert();
		
		//atribuir o texto do alert � uma vari�vel
		String texto = alert.getText();
		
		//validar a mensagem da tela de alert
		Assert.assertEquals("Alert Simples", texto);
		
		//para tirar o alert da tela para possibilizar o teste a seguir
		alert.accept();
		
		//pegar o texto do alert e inserir o mesmo texto no campo Nome
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		
		
		driver.quit();
		
	}
	@Test	
	public void deveInteragirComAlertConfirm() {
		
		//inicializa o driver geckodriver que est� na pasta do path do windows
		WebDriver driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a p�gina e pega o t�tulo dela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		//realizar o clique no bot�o confirm
		driver.findElement(By.id("confirm")).click();
		
		//mudar o foco para a tela de confirm
		Alert alerta = driver.switchTo().alert();
		
		//aceitar a tela de confirm
		alerta.accept();
		
		//pegar o texto ap�s confirmar para validar se aparece "confirmar"
		String confirmado = alerta.getText();
		
		//validar a mensagem em tela com o texto do alert de confirm
		//em seguida dar click no ok para fechar a tela
		Assert.assertEquals("Confirmado", confirmado);
		alerta.accept();
		
		//teste com o cancelar do confirm
		//realizar o clique no bot�o confirm
		driver.findElement(By.id("confirm")).click();
		
		//cancelar o confirm
		alerta.dismiss();
		
		//pegar o texto e validar se foi cancelado
		confirmado = alerta.getText();
				
		//validar o texto em tela e sair da mesma
		Assert.assertEquals("Negado", confirmado);
		alerta.accept();
		
		driver.quit();
	}
	@Test	
	public void deveInteragirComAlertPrompt() {
		
		//inicializa o driver geckodriver que est� na pasta do path do windows
		WebDriver driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a p�gina e pega o t�tulo dela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		//clicar no alert
		driver.findElement(By.id("prompt")).click();
	
		//mudar o foco para o alert
		Alert alertp = driver.switchTo().alert();
		
		//validar se aparece o texto em tela
		Assert.assertEquals("Digite um numero", alertp.getText());
		
		//digitar um valor (parecido com textfield)
		alertp.sendKeys("12");
		
		//dar ok ap�s digitar o valor
		alertp.accept();
		
		//validar a pergunta ap�s o valor informado
		Assert.assertEquals("Era 12?", alertp.getText());
		
		//dar um ok para validar a pr�xima mensagem
		alertp.accept();
		Assert.assertEquals(":D", alertp.getText());
		
		alertp.accept();
		
		driver.quit();
		
	}
}






