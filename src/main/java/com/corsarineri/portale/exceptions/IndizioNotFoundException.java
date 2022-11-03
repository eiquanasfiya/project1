package com.corsarineri.portale.exceptions;

public class IndizioNotFoundException extends RuntimeException {
	public IndizioNotFoundException(Long id) {
	    super("Impossibile trovare indizio " + id);
	  }
}
