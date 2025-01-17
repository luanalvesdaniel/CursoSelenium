import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Realiza o cadastro e valida as regras de neg�cio do mesmo
 * @author Luan Alves Daniel
 *	
 */


public class TesteCadastro {	
	private WebDriver driver;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		//inicializa o driver geckodriver que est� na pasta do path do windows
		driver = new FirefoxDriver();
		
		//ajuste da janela do navegador
		driver.manage().window().setSize(new Dimension(800, 600));
		
		//inicializa a p�gina e pega o t�tulo dela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//Instanciando a page
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	
	}
	
	@Test
	public void TesteCadastro1() {	
		//Preenche os campos Nome e Sobrenome
		page.setNome("Luan");
		page.setSobrenome("Alves Daniel");
				
		//seleciona o sexo masculino no radiobutton
		page.setSexoMasculino();
				
		//escolhe a pizza no checkbox
		page.setComidaPizza();
						
		//escolhe Superior no combobox da escolaridade
		page.setEscolaridade("Superior");
				
		//escolhe natacao no combobox de multiplas escolhas
		page.setEsporte("Natacao");
				
		//clicar em cadastrar
		page.cadastrar();
				
		//Pegar o texto que comece com Cadastrado, visto que n�o h� id no elemento que queremos validar
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));		
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Luan"));
		Assert.assertTrue(page.obterSobrenomeCadastro().endsWith("Alves Daniel"));
		Assert.assertTrue(page.obterSexoCadastro().endsWith("Masculino"));
		Assert.assertTrue(page.obterComidaCadastro().endsWith("Pizza"));
		Assert.assertTrue(page.obterEscolaridadeCadastro().endsWith("superior"));
		//OU
		Assert.assertEquals("Esportes: Natacao", page.obterEsporteCadastro());
	}
	
}
