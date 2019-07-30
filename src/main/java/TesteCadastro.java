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
	public void TesteCadastro1() {
	
		//Preenche os campos Nome e Sobrenome
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luan");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves Daniel");
		
		//seleciona o sexo masculino no radiobutton
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		//escolhe a pizza no checkbox
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		//escolhe Superior no combobox da escolaridade
//		WebElement element1 = driver.findElement(By.id("elementosForm:escolaridade"));
//		Select combo1 = new Select(element1);		
//		combo1.selectByVisibleText("Superior");
		
		//ou
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
		
		//escolhe natacao no combobox de multiplas escolhas
//		WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
//		Select combo2 = new Select(element2);		
//		combo2.selectByVisibleText("Natacao");		
		
		//ou
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		
		
		//clicar em cadastrar
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		//Pegar o texto que comece com Cadastrado, visto que não há id no elemento que queremos validar
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Luan"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Alves Daniel"));
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		Assert.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Pizza"));
		Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
		//OU
		Assert.assertEquals("Esportes: Natacao", 		driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes:", 				driver.findElement(By.id("descSugestoes")).getText());

	}
	
	@Test
	public void validaNomeNaoPreenchido() {
		
		//clica no cadastrar
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		//muda o foto para a tela de alert
		Alert alert = driver.switchTo().alert();
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
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luan");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Sobrenome eh obrigatorio", texto);
		alert.accept();
		
	}

	@Test
	public void validaSexoNaoPreenchido() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luan");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Sexo eh obrigatorio", texto);
		alert.accept();
		
	}

	@Test
	public void validaComidaPreenchidaIncorretamente() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luan");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", texto);
		alert.accept();
		
	}

	@Test
	public void validaEsportePreenchidoIncorretamente() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Luan");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		//Instanciando como WebElement pois é como o selenium retorna em findElement
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		//dando select passando o element criado
		Select combo = new Select(element);
		//selecionar mais de um item ao mesmo tempo
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Voce faz esporte ou nao?", texto);
		alert.accept();
		
	}
}
