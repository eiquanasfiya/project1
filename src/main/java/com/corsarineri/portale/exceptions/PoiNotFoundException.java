package com.corsarineri.portale.exceptions;

public class PoiNotFoundException extends RuntimeException {
	public PoiNotFoundException(Long id) {
	    super("Impossibile trovare poi " + id);
	  }
}
