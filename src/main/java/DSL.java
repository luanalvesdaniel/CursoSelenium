import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author Luan Alves Daniel
 *	Descrição: DSL é uma linguagem própria criada especificamente para resolver um problema.
 *	Criar métodos genéricos para executar métodos específicos em outras classes.
 *	Aqui usaremos para padronizar os métodos do selenium webdriver.
 */

public class DSL {
	
	//instancia o driver com seu construtor
	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void escreve(String id_campo, String texto) {
		
		//encontrar elemento do tipo textfield pelo id e inserir o texto (sendkeys)
		driver.findElement(By.id(id_campo)).sendKeys(texto);
		
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public Boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		//Instanciando como WebElement pois é como o selenium retorna em findElement
		WebElement element = driver.findElement(By.id(id));
		//dando select passando o element criado
		Select combo = new Select(element);
		
		//selecionar algum elemento da lista por index, onde se inicia em 0 (zero)
//		combo.selectByIndex(2);
		//selecionar algum elemento da lista pelo valor que vai no banco (value)
//		combo.selectByValue("superior");
		//selecionar algum elemento da lista pelo texto visível
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {

		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	public Alert mudaFocoAlert() {
		return driver.switchTo().alert();
	}
	
}
