import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Realiza o cadastro e valida as regras de negócio do mesmo
 * @author Luan Alves Daniel
 *	
 */


public class TesteCadastro {	
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
				
		//Pegar o texto que comece com Cadastrado, visto que não há id no elemento que queremos validar
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));		
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Luan"));
		Assert.assertTrue(page.obterSobrenomeCadastro().endsWith("Alves Daniel"));
		Assert.assertTrue(page.obterSexoCadastro().endsWith("Masculino"));
		Assert.assertTrue(page.obterComidaCadastro().endsWith("Pizza"));
		Assert.assertTrue(page.obterEscolaridadeCadastro().endsWith("superior"));
		//OU
		Assert.assertEquals("Esportes: Natacao", page.obterEsporteCadastro());
	}
	
	@Test
	public void validaNomeNaoPreenchido() {		
		//clica no cadastrar
		page.cadastrar();

		//validar a mensagem da tela de alert
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void validaSobrenomeNaoPreenchido() {		
		//Informa o nome
		page.setNome("Luan");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void validaSexoNaoPreenchido() {
		page.setNome("Luan");
		page.setSobrenome("Alves");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void validaComidaPreenchidaIncorretamente() {
		page.setNome("Luan");
		page.setSobrenome("Alves");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void validaEsportePreenchidoIncorretamente() {		
		page.setNome("Luan");
		page.setSobrenome("Alves");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Natacao","O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());		
	}
}
