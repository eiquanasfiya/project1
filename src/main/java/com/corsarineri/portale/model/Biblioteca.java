package com.corsarineri.portale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "biblioteca", schema = "corsarineri")
public class Biblioteca {
	
	/*
		    id serial not null,
		    titolo StringM not null,
		    url StringXL not null,
			testo text,
		    tag StringL
*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titolo;
	private String url;
	private String testo;
	private String tag;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
}
