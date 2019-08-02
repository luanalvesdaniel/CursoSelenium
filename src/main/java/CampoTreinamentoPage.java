import org.openqa.selenium.WebDriver;

/**
 * Classe para interagir com a página de fato, sendo esta chamada pela classe de testes
 * @author Luan Alves Daniel
 *
 */

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public String getNome() {
		return dsl.obterValorCampo("elementosForm:nome");
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public String getSobreNome() {
		return dsl.obterValorCampo("elementosForm:sobrenome");
	}
	
	public void setSugestao(String sugestao) {
		dsl.escreve("elementosForm:sugestoes", sugestao);
	}
	
	public String getSugestao() {
		return dsl.obterValorCampo("elementosForm:sugestoes");
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public boolean getSexoMasculino() {
		return dsl.isRadioMarcado("elementosForm:sexo:0");
	}
	
	public boolean getSexoFeminino() {
		return dsl.isRadioMarcado("elementosForm:sexo:1");	}
	
	
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	public boolean getComidaCarne() {
		return dsl.isCheckMarcado("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango() {
		dsl.clicarRadio("elementosForm:comidaFavorita:1");
	}
	
	public boolean getComidaFrango() {
		return dsl.isCheckMarcado("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public boolean getComidaPizza() {
		return dsl.isCheckMarcado("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public boolean getComidaVegetariano() {
		return dsl.isCheckMarcado("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public String getEscolaridade(String id) {
		return dsl.obterValorCombo(id);
	}
	
	//diferente pois pode-se escolher mais de um ao mesmo tempo
	public void setEsporte(String... valores) {
		for(String valor: valores)
			dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void unSetEsporte(String... valores) {
		for(String valor: valores)
			dsl.deselecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public void cliqueMe() {
		dsl.clicarBotao("buttonSimple");
	}
	
	public void cliqueVoltar() {
		dsl.clicarLink("Voltar");
	}
	
	public String obterResultadoVoltar() {
		return dsl.obterTexto("resultado");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto("resultado");
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	
	public String obterSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	
	public String obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}
	
	public String obterEsporteCadastro() {
		return dsl.obterTexto("descEsportes");
	}
}
