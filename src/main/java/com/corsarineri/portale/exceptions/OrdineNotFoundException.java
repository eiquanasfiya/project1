package com.corsarineri.portale.exceptions;

public class OrdineNotFoundException extends RuntimeException {
	public OrdineNotFoundException(Long id) {
	    super("Impossibile trovare ordine " + id);
	  }
}
