import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
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
	public void TesteCadastro1() {
	
		//Preenche os campos Nome e Sobrenome
		dsl.escreve("elementosForm:nome", "Luan");
		dsl.escreve("elementosForm:sobrenome", "Alves Daniel");
		
		//seleciona o sexo masculino no radiobutton
		dsl.clicarRadio("elementosForm:sexo:0");
		
		//escolhe a pizza no checkbox
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
				
		//escolhe Superior no combobox da escolaridade
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
				
		//escolhe natacao no combobox de multiplas escolhas
		dsl.selecionarCombo("elementosForm:esportes","Natacao");
				
		//clicar em cadastrar
		dsl.clicarBotao("elementosForm:cadastrar");
				
		//Pegar o texto que comece com Cadastrado, visto que não há id no elemento que queremos validar
		Assert.assertTrue(dsl.obterTexto(By.id("resultado")).startsWith("Cadastrado!"));		
		Assert.assertTrue(dsl.obterTexto(By.id("descNome")).endsWith("Luan"));
		Assert.assertTrue(dsl.obterTexto(By.id("descSobrenome")).endsWith("Alves Daniel"));
		Assert.assertTrue(dsl.obterTexto(By.id("descSexo")).endsWith("Masculino"));
		Assert.assertTrue(dsl.obterTexto(By.id("descComida")).endsWith("Pizza"));
		Assert.assertTrue(dsl.obterTexto(By.id("descEscolaridade")).endsWith("superior"));
		//OU
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto(By.id("descEsportes")));
		Assert.assertEquals("Sugestoes:", dsl.obterTexto(By.id("descSugestoes")));

	}
	
	@Test
	public void validaNomeNaoPreenchido() {
		
		//clica no cadastrar
		dsl.clicarBotao("elementosForm:cadastrar");
		
		//muda o foco para a tela de alert
		Alert alert = dsl.mudaFocoAlert();
		//Pega o texto do alert
		String texto = alert.getText();
		//validar a mensagem da tela de alert
		Assert.assertEquals("Nome eh obrigatorio", texto);
		//dar ok no alert
		alert.accept();
		
	}

	@Test
	public void validaSobrenomeNaoPreenchido() {
		
		//Informa o nome
		dsl.escreve("elementosForm:nome", "Luan");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = dsl.mudaFocoAlert();
		String texto = alert.getText();
		Assert.assertEquals("Sobrenome eh obrigatorio", texto);
		alert.accept();
		
	}

	@Test
	public void validaSexoNaoPreenchido() {
		
		dsl.escreve("elementosForm:nome", "Luan");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = dsl.mudaFocoAlert();

		String texto = alert.getText();
		Assert.assertEquals("Sexo eh obrigatorio", texto);
		alert.accept();
		
	}

	@Test
	public void validaComidaPreenchidaIncorretamente() {
		
		dsl.escreve("elementosForm:nome", "Luan");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");;
		Alert alert = dsl.mudaFocoAlert();
		String texto = alert.getText();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", texto);
		alert.accept();
		
	}

	@Test
	public void validaEsportePreenchidoIncorretamente() {
		
		dsl.escreve("elementosForm:nome", "Luan");
		dsl.escreve("elementosForm:sobrenome", "Alves");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		dsl.clicarBotao("elementosForm:cadastrar");;
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Voce faz esporte ou nao?", texto);
		alert.accept();
		
	}
}
