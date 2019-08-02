import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String msg;
	
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
	
	@Parameters
	//método para inserção dos dados
	public static Collection<Object[]> getColletion() {
		//cada linha que colocar dentro dessa coleção será um conjunto de dados que vai passar pela estrutura
		//genérica, ou seja, será um novo cenário de teste
		return Arrays.asList(new Object[][]{
			
			//Validar nome obrigatório
			//nome vazio, sobrenome vazio, sexo vazio, comidas vazio, esportes vazio, mensagem do alert
			{"","","", Arrays.asList(), new String[] {}, "Nome eh obrigatorio"},
			
			//Validar sobrenome obrigatório
			//nome preenchido, sobrenome vazio, sexo vazio, comidas vazio, esportes vazio, mensagem do alert
			{"Luan","","", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio"},
			
			//Validar sexo obrigatório
			//nome preenchido, sobrenome preenchido, sexo vazio, comidas vazio, esportes vazio, mensagem do alert
			{"Luan","Alves","", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio"},
			
			//Validar comida preenchida incorretamente
			//nome preenchido, sobrenome preenchido, sexo informado, comidas informadas indevidamente, 
			//esportes vazio, mensagem do alert
			{"Luan","Alves","Masculino", Arrays.asList("Carne","Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
			
			//Validar esporte preenchido incorretamente
			//nome preenchido, sobrenome preenchido, sexo informado, comidas informadas indevidamente, 
			//esportes informados indevidamente, mensagem do alert
			{"Luan","Alves","Masculino", Arrays.asList("Carne"), new String[] {"Natacao","O que eh esporte?"}, "Voce faz esporte ou nao?"}
			
		});
	}
	
	@Test
	//teste orientado a dados
	//data driven teste
	public void deveValidarRegras() {		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		if(sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Frango")) page.setComidaFrango();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano();
		
		page.setEsporte(esportes);
		
		page.cadastrar();
		
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());		
	}
	
}
