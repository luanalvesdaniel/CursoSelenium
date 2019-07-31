import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

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
	public void deveInteragirComTextField() {
		
		//encontrar elemento do tipo textfield pelo id e inserir o texto (sendkeys)
		dsl.escreve("elementosForm:nome", "teste de escrita");
		
		//no mesmo elemento acima, validar o que foi escrito
		Assert.assertEquals("teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
		
	}
	
	@Test
	public void deveInteragirComTextArea() {
		
		//busca elemento pelo id e insere teste, em seguida valida o texto
		//para pular linha basta usar o \n
		dsl.escreve("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
	
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		
		//encontrar elemento por id e dar um clique no radiobutton
		dsl.clicarRadio("elementosForm:sexo:0");
		//validar se o elemento está clicado
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		
		//encontrar elemento por id e dar um check
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		//validar se o elemento está com check
		Assert.assertTrue(dsl.isRadioMarcado(("elementosForm:comidaFavorita:0")));
		
	}
	
	@Test
	public void deveInteragirComCombo() {
		
		//selecionando o valor pelo texto visível
		dsl.selecionarCombo("elementosForm:escolaridade", "1o grau completo");
		
		//Para validar devera primeiro pegar o primeiro valor selecionado e pegar o texto
		Assert.assertEquals("1o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		
		//Instanciando como WebElement pois é como o selenium retorna em findElement
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//dando select passando o element criado
		Select combo = new Select(element);
		
		//Pegar todos as opções do combo, onde o getoptions retorna uma lista de elements
		List<WebElement> options = combo.getOptions();
		
		//validar se possui exatamente a quantidade esperada
		Assert.assertEquals(8, options.size());
		
		//validar se algum opção está disponível no combo
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}			
		}
		Assert.assertTrue(encontrou);
		
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		
		//Instanciando como WebElement pois é como o selenium retorna em findElement
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		//dando select passando o element criado
		Select combo = new Select(element);
		
		//selecionar mais de um item ao mesmo tempo
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
				
		//validar se a quantidade selecionada corresponde a 3 (três)
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
				
		//para desmarcar alguma opção
		combo.deselectByVisibleText("Corrida");		
				
		//validar se a quantidade selecionada corresponde a 2 (três)
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		
	}
	
	@Test
	public void deveInteragirComBotoes() {
		
		//Clicar no botao pelo id
		dsl.clicarBotao("buttonSimple");
		
		//clicar no botao pelo id v2
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		//validar se alterou o conteúdo do botão
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}
	
	@Test
	public void deveInteragirComLinks() {
		
		//busca o link por linktest, visto que não tem id no elemento
		dsl.clicarLink("Voltar");	
		
		//procurar na página se acha o texto "voltou" após clicar no link voltar
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		
	
		//fazer busca por h3
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		//fazer busca por span em tagname
//		Assert.assertEquals("Cuidado onde clica, muitas amardilhas...", driver.findElement(By.tagName("span")).getText());
		
		//fazer busca por classename
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
		
	}
		
}
