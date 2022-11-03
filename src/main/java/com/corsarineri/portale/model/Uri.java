package com.corsarineri.portale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "uri", schema = "corsarineri")
public class Uri {

	/*    id serial not null,
    percorso StringL not null,
    indizio integer,
    tipo StringS,
    */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long indizio;
	private String percorso;
	private String tipo;//auito // autio2
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
	public String getPercorso() {
		return percorso;
	}
	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

	
}