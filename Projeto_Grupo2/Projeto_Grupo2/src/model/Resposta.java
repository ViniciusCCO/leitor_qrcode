package model;


public class Resposta {	
	private int codigo;
	private String mensagem;
	private String documento_descriptografado;
	
	public String getDocumento_descriptografado() {
		return documento_descriptografado;
	}
	
	public void setDocumento_descriptografado(String documento_descriptografado) {
		this.documento_descriptografado = documento_descriptografado;
	}
	public Resposta () {}
	public Resposta(int codigo, String mensagem) {
		super();
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
