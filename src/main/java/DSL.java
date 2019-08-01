import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Descrição: DSL é uma linguagem própria criada especificamente para resolver um problema.
 * Criar métodos genéricos para executar métodos específicos em outras classes.
 * Aqui usaremos para padronizar os métodos do selenium webdriver.
 * @author Luan Alves Daniel
 *	
 */

public class DSL {
	
	//instancia o driver com seu construtor
	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	/** Campos do tipo TextField e TextArea **/

	public void escreve(By by, String texto){		
		//encontrar elemento, limpar e escrever algum texto
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escreve(String id_campo, String texto) {		
		//chama o método anterior, passa o id para encontrar o campo e escreve o texto
		escreve(By.id(id_campo), texto);
		
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/** Radio button e Checkbox ****/
	
	public void clicarRadio(String id) {
		//acha o elemento pelo id e clica sobre ele
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		//acha o elemento pelo id e valida se está marcado
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		//acha o check e marca
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id){
		//acha o elemento pelo id e valida se está com check
		return driver.findElement(By.id(id)).isSelected();
	}
	
	/** Combo **/
	
	public void selecionarCombo(String id, String valor) {
		//Instanciando como WebElement pois é como o selenium retorna em findElement
		WebElement element = driver.findElement(By.id(id));
		//dando select passando o element criado
		Select combo = new Select(element);
		
		//selecionar algum elemento da lista por index, onde se inicia em 0 (zero)
		//combo.selectByIndex(2);
		//selecionar algum elemento da lista pelo valor que vai no banco (value)
		//combo.selectByValue("superior");
		//selecionar algum elemento da lista pelo texto visível
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		//quase igual ao método anterior, com a diferença de que vai
		//desmarcar o combo pelo texto visível
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id) {
		//encontra o elemento que pode selecionar mais de um item
		//ao mesmo tempo
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		//pega todas as opções e atribui a uma lista
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		//como nome do método já diz, irá pegar a lista do combo e 
		//retornar a quantidade
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		//irá validar se a opção do combo realmente foi marcado
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	/** Botao **/
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	/** Link **/
	
	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	/** Textos **/
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	/** Alerts **/
	
	public String alertaObterTexto(){
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObterTextoENega(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
		
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	/** Frames e Janelas **/
	
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
	
}
