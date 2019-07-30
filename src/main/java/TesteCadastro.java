import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

	@Test
	public void TesteCadastro1() {
		
		//inicializa ambiente
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(800, 600));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
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
		
		Assert.assertEquals("Nome: Luan", 				driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Alves Daniel", 	driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", 			driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", 			driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", 	driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao", 		driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes:", 				driver.findElement(By.id("descSugestoes")).getText());
		
		driver.quit();
	}
}
