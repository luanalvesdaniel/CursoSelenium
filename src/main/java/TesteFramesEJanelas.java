import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {

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
	public void deveInteragirComFrames() {
		
		//mudar o foco para o iframe
		driver.switchTo().frame("frame1");
		
		//clica no botao
		driver.findElement(By.id("frameButton")).click();
		
		//mudar o foco para o alert
		Alert alert = driver.switchTo().alert();
		
		//atribuir a mensagem numa variável
		String msg = alert.getText();
		
		//comparar o texto do alert
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		//voltar ao foco da tela principal
		driver.switchTo().defaultContent();
		
		//pegar o texto e inserir no nome
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
		
	}
	
	@Test
	public void deveInteragirComJanelas() {
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		
		//mudar o foco para a popup
		driver.switchTo().window("Popup");
		
		//escrever no campo
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
		//fechar a popup
		driver.close();
		
		//voltar o foco a tela principal (anterior)
		driver.switchTo().window("");
		
		//prencher no campo de textarea
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");

	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		//pegando o "id" das janelas para conseguir focar nelas
		//a principal
//		System.out.println(driver.getWindowHandle()); 
		//a principal mais a popup
//		System.out.println(driver.getWindowHandles());
		
		//pegar o id de forma dinâmica visto que o valor muda a toda execução e focar na popup
		//o String dentro do Window() é devido ao método window esperar uma string
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		
		//escrever no textarea da popup
		driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");
		
		//voltar o foco para a tela principal
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		
		//escrever no textarea da tela principal
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		
	}
}
