package com.corsarineri.portale.exceptions;

public class SoluzioneNotFoundException extends RuntimeException {
	public SoluzioneNotFoundException(Long id) {
	    super("Impossibile trovare soluzione " + id);
	  }
}
