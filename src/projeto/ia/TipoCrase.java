package projeto.ia;

public enum TipoCrase {
	
	A ("à"),
	AS ("às");
	
	
	private String extenso;
	
	TipoCrase(String ext) {
		this.extenso = ext;
	}
	public String getExtenso() {
		return extenso;
	}
}



