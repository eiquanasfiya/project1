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
@Table(name = "indizio", schema = "corsarineri")
public class Indizio {

	/*     id serial not null,
    numero IntegerGZ not null,
    nome StringM not null,
    anno Anno not null,
	aiuto_arrivato boolean,
    orario_ritiro_comitato timestamp without time zone not null,
    orario_arrivo_in_base timestamp without time zone not null,
    orario_aiuto timestamp without time zone not null,
    orario_soluzione timestamp without time zone not null,
    primary key(id)
    */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numero;
	private String nome;
	private Long anno;
	private Boolean aiutoArrivato;
	private Boolean aiutoArrivato2;
	private Integer blockId;
	private Boolean photo;
	private String semaforo;


	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioAiuto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioAiuto2;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioSoluzione;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioArrivoInBase;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orarioRitiroComitato;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getAnno() {
		return anno;
	}
	public void setAnno(Long anno) {
		this.anno = anno;
	}
	public Boolean getAiutoArrivato() {
		return aiutoArrivato;
	}
	public void setAiutoArrivato(Boolean aiutoArrivato) {
		this.aiutoArrivato = aiutoArrivato;
	}
	public Date getOrarioAiuto() {
		return orarioAiuto;
	}
	public void setOrarioAiuto(Date orarioAiuto) {
		this.orarioAiuto = orarioAiuto;
	}
	public Date getOrarioSoluzione() {
		return orarioSoluzione;
	}
	public void setOrarioSoluzione(Date orarioSoluzione) {
		this.orarioSoluzione = orarioSoluzione;
	}
	public Date getOrarioArrivoInBase() {
		return orarioArrivoInBase;
	}
	public void setOrarioArrivoInBase(Date orarioArrivoInBase) {
		this.orarioArrivoInBase = orarioArrivoInBase;
	}
	public Date getOrarioRitiroComitato() {
		return orarioRitiroComitato;
	}
	public void setOrarioRitiroComitato(Date orarioRitiroComitato) {
		this.orarioRitiroComitato = orarioRitiroComitato;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}
	public Boolean getPhoto() {
		return photo;
	}

	public void setPhoto(Boolean photo) {
		this.photo = photo;
	}

	public String getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}
}