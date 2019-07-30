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
	
	@Before
	public void inicializa() {
		//inicializa o driver geckodriver que está na pasta do path do windows
		driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a página e pega o título dela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComTextField() {
		
		//encontrar elemento do tipo textfield pelo id e inserir o texto (sendkeys)
		driver.findElement(By.id("elementosForm:nome")).sendKeys("teste de escrita");
		//no mesmo elemento acima, validar o que foi escrito
		Assert.assertEquals("teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
	}
	
	@Test
	public void deveInteragirComTextArea() {
		
		//busca elemento pelo id e insere teste, em seguida valida o texto
		//para pular linha basta usar o \n
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		
		//encontrar elemento por id e dar um clique no radiobutton
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		//validar se o elemento está clicado
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		
		//encontrar elemento por id e dar um check
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//validar se o elemento está com check
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		
	}
	
	@Test
	public void deveInteragirComCombo() {
		
		//Instanciando como WebElement pois é como o selenium retorna em findElement
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//dando select passando o element criado
		Select combo = new Select(element);
		
		//selecionar algum elemento da lista por index, onde se inicia em 0 (zero)
//		combo.selectByIndex(2);
		//selecionar algum elemento da lista pelo valor que vai no banco (value)
//		combo.selectByValue("superior");
		//selecionar algum elemento da lista pelo texto visível
		combo.selectByVisibleText("1o grau completo");
		
		//Para validar devera primeiro pegar o primeiro valor selecionado e pegar o texto
		Assert.assertEquals("1o grau completo", combo.getFirstSelectedOption().getText());
		
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
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
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
		
		//clicar no botao pelo id v1
//		driver.findElement(By.id("buttonSimple")).click();
		
		//clicar no botao pelo id v2
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		//validar se alterou o conteúdo do botão
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}
	
	@Test
	//@Ignore
	public void deveInteragirComLinks() {
		
		//busca o link por linktest, visto que não tem id no elemento
		driver.findElement(By.linkText("Voltar")).click();	
		
		//procurar na página se acha o texto "voltou" após clicar no link voltar
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		
		//para não esquecer de finalizar um teste, basta forçar a falha v1
		//v2 seria o @ignore que está acima deste método
//		Assert.fail();
		
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		
		//fazendo busca por toda a página no elemento body e pegar todo o texto
//		driver.findElement(By.tagName("body")).getText();
		
		//pegar todo o texto e verificar se contém o mesmo v1
//		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
	
		//fazer busca por h3
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		
		//fazer busca por span em tagname
//		Assert.assertEquals("Cuidado onde clica, muitas amardilhas...", driver.findElement(By.tagName("span")).getText());
		
		//fazer busca por classename
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		
	}
		
}
