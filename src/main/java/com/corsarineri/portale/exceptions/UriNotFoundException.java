package com.corsarineri.portale.exceptions;

public class UriNotFoundException extends RuntimeException {
	public UriNotFoundException(Long id) {
	    super("Impossibile trovare uri " + id);
	  }
}
