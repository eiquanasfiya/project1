package com.corsarineri.portale.model;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "soluzione", schema = "corsarineri")
public class Soluzione {

	/*    id serial not null,
    indizio integer,
    ragionamento StringXL not null,
	stato Stato,
	fase Fase,
	scartato boolean,
    orario_invio_a_TC timestamp without time zone not null,
    orario_archiviazione timestamp without time zone,
    orario_stampa timestamp without time zone,
    semaforo Giudizio,
    correzione StringXL,
	luogo_soluzione StringL,
	correzione_luogo_soluzione StringXL;
    primary key(id)
    */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long indizio;
	private String ragionamento;
	private String stato;
	private String fase;
	private Boolean scartato;
	@Column(name="orario_invio_a_tc")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioInvioATc;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioArchiviazione;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioStampa;
	private String semaforo;
	private String correzione;
	private String luogoSoluzione;
	private String correzioneLuogoSoluzione;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIndizio() {
		return indizio;
	}
	public void setIndizio(Long indizio) {
		this.indizio = indizio;
	}
	public String getRagionamento() {
		return ragionamento;
	}
	public void setRagionamento(String ragionamento) {
		this.ragionamento = ragionamento;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public Boolean getScartato() {
		return scartato;
	}
	public void setScartato(Boolean scartato) {
		this.scartato = scartato;
	}
	public Date getOrarioInvioATc() {
		return orarioInvioATc;
	}
	public void setOrarioInvioATc(Date orarioInvioATc) {
		this.orarioInvioATc = orarioInvioATc;
	}
	public Date getOrarioArchiviazione() {
		return orarioArchiviazione;
	}
	public void setOrarioArchiviazione(Date orarioArchiviazione) {
		this.orarioArchiviazione = orarioArchiviazione;
	}
	public Date getOrarioStampa() {
		return orarioStampa;
	}
	public void setOrarioStampa(Date orarioStampa) {
		this.orarioStampa = orarioStampa;
	}
	public String getSemaforo() {
		return semaforo;
	}
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}
	public String getCorrezione() {
		return correzione;
	}
	public void setCorrezione(String correzione) {
		this.correzione = correzione;
	}
	public String getLuogoSoluzione() {
		return luogoSoluzione;
	}
	public void setLuogoSoluzione(String luogoSoluzione) {
		this.luogoSoluzione = luogoSoluzione;
	}
	public String getCorrezioneLuogoSoluzione() {
		return correzioneLuogoSoluzione;
	}
	public void setCorrezioneLuogoSoluzione(String correzioneLuogoSoluzione) {
		this.correzioneLuogoSoluzione = correzioneLuogoSoluzione;
	}

	

	
}