package com.corsarineri.portale.model;

public class Libro {
	private Boolean parolaIntera;
	public Boolean getParolaIntera() {
		return parolaIntera;
	}
	public void setParolaIntera(Boolean parolaIntera) {
		this.parolaIntera = parolaIntera;
	}
	private String titolo;
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	private String uri;
	public Libro(String titolo, String uri, Boolean parolaIntera) {
		this.titolo=titolo;
		this.uri=uri;
		this.parolaIntera=parolaIntera;
	}
}
