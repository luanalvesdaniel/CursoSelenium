import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Interagir e validar os elementos da página
 * @author Luan Alves Daniel
 *	
 */

public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
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
		
		//Instanciando a page
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
//		driver.quit();
	}
	
	@Test
	public void deveInteragirComTextField() {		
		//encontrar elemento do tipo textfield pelo id e inserir o texto (sendkeys)
		page.setNome("Luan");
				
		//no mesmo elemento acima, validar o que foi escrito
		Assert.assertEquals("Luan", page.getNome());
		
	}
	
	@Test
	public void testTextFieldDuplo(){
		//escrever e validar alteração no mesmo
		page.setNome("Luan");
		Assert.assertEquals("Luan", page.getNome());
		page.setNome("Alves");
		Assert.assertEquals("Alves", page.getNome());
	}
	
	@Test
	public void deveInteragirComTextArea() {		
		//busca elemento pelo id e insere teste, em seguida valida o texto
		//para pular linha basta usar o \n
		page.setSugestao("teste");
		Assert.assertEquals("teste", page.getSugestao());
	
	}
	
	@Test
	public void deveInteragirComRadioButton() {		
		//encontrar elemento por id e dar um clique no radiobutton
		page.setSexoMasculino();
		//validar se o elemento está clicado
		Assert.assertTrue(page.getSexoMasculino());

	}
	
	@Test
	public void deveInteragirComCheckBox() {		
		//encontrar elemento por id e dar um check
		page.setComidaCarne();
		//validar se o elemento está com check
		Assert.assertTrue(page.getComidaCarne());
		
	}
	
	@Test
	public void deveInteragirComCombo() {		
		//selecionando o valor pelo texto visível
		page.setEscolaridade("Mestrado");
		
		//Para validar devera primeiro pegar o primeiro valor selecionado e pegar o texto
		Assert.assertEquals("Mestrado", page.getEscolaridade("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void deveVerificarValoresCombo() {		
		//validar se possui exatamente a quantidade esperada
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));			
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {		
		//selecionar mais de um item ao mesmo tempo
		page.setEsporte("Natacao","Corrida","O que eh esporte?");
				
		//validar se a quantidade selecionada corresponde a 3 (três)
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
				
		//para desmarcar alguma opção
		page.unSetEsporte("Corrida");
				
		//validar se a quantidade selecionada corresponde a 2 (dois) e validar
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));		
	}
	
	@Test
	public void deveInteragirComBotoes() {		
		//Clicar no botao pelo id
		page.cliqueMe();
		
		//validar se alterou o conteúdo do botão
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
		
	}
	
	@Test
	public void deveInteragirComLinks() {		
		//busca o link por linktest, visto que não tem id no elemento
		page.cliqueVoltar();
		
		//procurar na página se acha o texto "voltou" após clicar no link voltar
		Assert.assertEquals("Voltou!", page.obterResultadoVoltar());
		
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {	
		//fazer busca por h3
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		//fazer busca por span em tagname
//		Assert.assertEquals("Cuidado onde clica, muitas amardilhas...", driver.findElement(By.tagName("span")).getText());
		
		//fazer busca por classename
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				dsl.obterTexto(By.className("facilAchar")));		
	}
	
	//Testando usando a classe JavaScript do Selenium
	@Test
	public void testJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("alert('Testando js via selenium')");
		
		//Achando o campo nome pelo id e passando um valor via JS
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Luan via js'");
		
		//achando o campo e mudando o tipo para radiobutton
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'"); 
		
		//iniciando um elemento com o id do campo Nome
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		//interagir com o element via JS utilizando a variável arguments
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
		
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		//procura pelo nome da coluna e clica
		dsl.clicarBotaoTabela("Escolaridade", "Doutorado", "Radio", "elementosForm:tableUsuarios");
	}
	
		
}
