package com.corsarineri.portale.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;



@Data
@Entity
@Table(name = "ordine", schema = "corsarineri")
public class Ordine {

	/*     nome strings NOT NULL,
	tavolo strings,
	ordine strings,
	orario_ordine timestamp without time zone
    */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String tavolo;
	private String ordine;
	private String statoOrdine;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioOrdine;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTavolo() {
		return tavolo;
	}
	public void setTavolo(String tavolo) {
		this.tavolo = tavolo;
	}
	public String getOrdine() {
		return ordine;
	}
	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}
	public String getStatoOrdine() {
		return statoOrdine;
	}
	public void setStatoOrdine(String statoOrdine) {
		this.statoOrdine = statoOrdine;
	}
	public Date getOrarioOrdine() {
		return orarioOrdine;
	}
	public void setOrarioOrdine(Date orarioOrdine) {
		this.orarioOrdine = orarioOrdine;
	}
		
}