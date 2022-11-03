package com.corsarineri.portale.exceptions;

public class UtenteNotFoundException extends RuntimeException {
	public UtenteNotFoundException(Long id) {
	    super("Impossibile trovare utente " + id);
	  }
}
