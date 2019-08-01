import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Interagir e validar os elementos da p�gina
 * @author Luan Alves Daniel
 *	
 */

public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		//inicializa o driver geckodriver que est� na pasta do path do windows
		driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a p�gina e pega o t�tulo dela
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
	public void testTextFieldDuplo(){
		//escrever e validar altera��o no mesmo
		dsl.escreve("elementosForm:nome", "Luan");
		Assert.assertEquals("Luan", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Alves");
		Assert.assertEquals("Alves", dsl.obterValorCampo("elementosForm:nome"));
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
		//validar se o elemento est� clicado
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		
	}
	
	@Test
	public void deveInteragirComCheckBox() {		
		//encontrar elemento por id e dar um check
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		//validar se o elemento est� com check
		Assert.assertTrue(dsl.isCheckMarcado(("elementosForm:comidaFavorita:0")));
		
	}
	
	@Test
	public void deveInteragirComCombo() {		
		//selecionando o valor pelo texto vis�vel
		dsl.selecionarCombo("elementosForm:escolaridade", "1o grau completo");
		
		//Para validar devera primeiro pegar o primeiro valor selecionado e pegar o texto
		Assert.assertEquals("1o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
		
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
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
				
		//validar se a quantidade selecionada corresponde a 3 (tr�s)
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
				
		//para desmarcar alguma op��o
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
				
		//validar se a quantidade selecionada corresponde a 2 (dois) e validar
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));		
	}
	
	@Test
	public void deveInteragirComBotoes() {		
		//Clicar no botao pelo id
		dsl.clicarBotao("buttonSimple");
		
		//validar se alterou o conte�do do bot�o
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
		
	}
	
	@Test
	public void deveInteragirComLinks() {		
		//busca o link por linktest, visto que n�o tem id no elemento
		dsl.clicarLink("Voltar");	
		
		//procurar na p�gina se acha o texto "voltou" ap�s clicar no link voltar
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		
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
		
}
