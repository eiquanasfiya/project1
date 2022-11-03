package com.corsarineri.portale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "utente", schema = "corsarineri")
public class Utente {

	/*    nome strings NOT NULL,
	username strings,
	password strings,
	ultimo_ip strings,
	ultimo_host strings 
    */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String username;
	private String password;
	private String ultimoIp;
	private String ultimoHost;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUltimoIp() {
		return ultimoIp;
	}
	public void setUltimoIp(String ultimoIp) {
		this.ultimoIp = ultimoIp;
	}
	public String getUltimoHost() {
		return ultimoHost;
	}
	public void setUltimoHost(String ultimoHost) {
		this.ultimoHost = ultimoHost;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}