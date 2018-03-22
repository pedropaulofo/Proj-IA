package projeto.ia;

import org.jpl7.Query;

public class Analise {
	private String frase = null;
	private TipoCrase tipo;
	private String textoAnterior = "";
	private String palavraPrecedente = "";
	private String textoPosterior = "";
	private String palavraSucessora = "";
	private Boolean precedenteProibido = false;
	private Boolean sucessorProibido = false;
	private String dica = "Dica: ";

	/*
	 * public static void main(String[] args){ System.out.println("Main:"); Analise
	 * anal; try { String texto = "Eu fui à praia"; anal = new Analise(texto);
	 * Boolean resultado = anal.analisar(); if(resultado)
	 * System.out.println("Caso proibitivo"); else System.out.println("Crase OK");
	 * System.out.println(anal.dica); } catch (Exception ex) {
	 * System.out.println(ex.getMessage()); }
	 * 
	 * }
	 */

	public Analise(String textoInput) throws Exception {
		frase = textoInput;
		tipo = null;

	}

	public Boolean analisar() throws Exception {

		if (frase == null || frase.equals("")) {
			throw new Exception("Frase vazia.");
		}
		identificarPartes();
		precedenteProibido = consultaProibicaoPrecedente();
		sucessorProibido = consultaProibicaoSucessor();

		// retorna TRUE se um dos casos identificar uso proibido da crase
		// retorna FALSE se nao for identificada irregularidade no uso da crase.
		return precedenteProibido || sucessorProibido;
	}

	private Boolean consultaProibicaoPrecedente() {
		if (palavraPrecedente.isEmpty())
			return false; // se nao ha palavra predecessora, nao ha o que se avaliar

		Query abrirArquivo = new Query("consult('caso_precedente.pl')");
		abrirArquivo.hasSolution();

		// checar se a palavra precedente se enquadra num dos casos proibitivos do uso
		// da crase
		Query checarPrecedente = new Query("pro(" + palavraPrecedente.toLowerCase() + ")");
		if (checarPrecedente.hasSolution()) {
			dica += "A crase não pode ser precedida por palavras como '" + palavraPrecedente + "'. ";

			// retorna verdadeiro se o precedente torna o uso proibido
			return true;
		}
		abrirArquivo.close();

		// retorna falso se o precedente nao implica na proibicao da crase
		return false;
	}

	private Boolean consultaProibicaoSucessor() {
		if (palavraSucessora.isEmpty()) {
			dica += "A crase nao pode ser usada ao final de frases.";
			return true; // se nao ha palavra sucessora, o uso eh indevido.
		}
		
		if(Character.isUpperCase(palavraSucessora.charAt(0))) { //Se a sucessor inicia com letra maiuscula, eh nome proprio
			dica +=  "Evite usar crase antes de nomes próprios e siglas. Pode ser usada com cautela apanas para nomes femininos e de acordo com o numero concordante. Para certos nomes de paises e cidades, o uso eh obrigatorio.";
			return true;
		}

		char last = palavraSucessora.charAt(palavraSucessora.length() - 1);
		if (contemNumeral(palavraSucessora) && tipo == TipoCrase.AS && last == 'h') // em casos como "vou às 10h" devem
																					// ser aceitos
			return false;
		if (contemNumeral(palavraSucessora) && tipo == TipoCrase.A && last == 'ª') // casos como "me refiro à 1ª" devem
																					// ser aceitos
			return false;
		else if (contemNumeral(palavraSucessora) && textoPosterior.split(" ").length > 1) {
			palavraSucessora = textoPosterior.split(" ")[1]; // em casos como "às 10 horas" será repassada a
																// concordancia para a palavra seguinte ao numeral
		} else if (contemNumeral(palavraSucessora)) {
			dica += "A crase nao deve ser usada para se referir a numerais cardinais.";
			return true;
		}

		Query abrirArquivo;
		abrirArquivo = new Query("consult('caso_precedente.pl')");
		abrirArquivo.hasSolution();
		Query checarPrepConj = new Query("pro(" + palavraSucessora.toLowerCase() + ")");
		if (checarPrepConj.hasSolution()) {
			dica += "A crase não pode ser sucedida por palavras como'" + palavraSucessora + "'. ";
			return true;
		}
		abrirArquivo.close();
		checarPrepConj.close();

		// casos proibitivos gerais do sucessor para plural ou singular
		abrirArquivo = new Query("consult('caso_geral.pl')");
		abrirArquivo.hasSolution();
		Query checarCasoGeral = new Query("p(" + palavraSucessora.toLowerCase() + ")");
		if (checarCasoGeral.hasSolution()) {
			dica += "A crase não pode ser sucedida por palavras como'" + palavraSucessora
					+ "' (artigos, adverbios, pronomes não-possessivos. ";
			return true;
		}
		abrirArquivo.close();
		checarCasoGeral.close();

		// abrir o arquivo correspondente se eh plural ou singular
		if (tipo == TipoCrase.A) {
			abrirArquivo = new Query("consult('caso_singular.pl')");
		} else if (tipo == TipoCrase.AS) {
			abrirArquivo = new Query("consult('caso_plural.pl')");
		}
		abrirArquivo.hasSolution();

		// checar de acordo com as regras do caso plural ou singular
		Query checarSucessor = new Query("proibida(" + palavraSucessora.toLowerCase() + ")");
		if (checarSucessor.hasSolution()) {
			dica += "A crase neste caso nao pode ser sucedida por '" + palavraSucessora + "'.";
			return true;
		}
		abrirArquivo.close();

		return false;
	}

	private String identificarPartes() throws Exception {
		String[] palavras = removePontuacao(frase).split(" ");
		int posCrase = -1;

		for (int i = 0; i < palavras.length; i++) {
			if (palavras[i].toLowerCase().equals(TipoCrase.A.getExtenso())) {
				if (posCrase >= 0)
					throw new Exception("A frase contem mais de uma crase. Digite uma oracao de cada vez.");
				posCrase = i;
				tipo = TipoCrase.A;
				if (i > 0)
					palavraPrecedente = palavras[i - 1];
				if (i < palavras.length - 1)
					palavraSucessora = palavras[i + 1];
			} else if (palavras[i].toLowerCase().equals(TipoCrase.AS.getExtenso())) {
				if (posCrase >= 0)
					throw new Exception("A frase contem mais de uma crase. Digite uma oracao de cada vez.");
				posCrase = i;
				tipo = TipoCrase.AS;
				if (i > 0)
					palavraPrecedente = palavras[i - 1];
				if (i < palavras.length - 1)
					palavraSucessora = palavras[i + 1];
			} else if (posCrase < 0)
				textoAnterior = textoAnterior + palavras[i] + " ";
			else
				textoPosterior = textoPosterior + palavras[i] + " ";
		}

		if (posCrase < 0)
			throw new Exception("A frase dada nao contem uso de crase.");

		return String.join(" ", palavras);
	}

	private String removePontuacao(final String input) {
		final StringBuilder builder = new StringBuilder();

		for (final char c : input.toCharArray())
			if (Character.isLetterOrDigit(c) || c == ' ')
				builder.append(c);
		return builder.toString();
	}


	public TipoCrase getTipo() {
		return tipo;
	}

	public String getFrase() {
		return frase;
	}

	public String getTextoAnterior() {
		return textoAnterior;
	}

	public String getTextoPosterior() {
		return textoPosterior;
	}

	public Boolean getPrecedenteProibido() {
		return precedenteProibido;
	}

	public Boolean getSucessorProibido() {
		return sucessorProibido;
	}

	public String getDica() {
		return dica;
	}

	private boolean contemNumeral(String termo) {
		for (int i = 0; i < termo.length(); i++) {
			if (Character.isDigit(termo.charAt(i)))
				return true;
		}
		return false;
	}

}
