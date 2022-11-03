package com.corsarineri.portale.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.corsarineri.portale.model.Wordlist;
import com.corsarineri.portale.repos.WordlistRepository;
import com.corsarineri.portale.utils.Anagrammatore;
@CrossOrigin
@RestController
public class AnagrammaController {
	Logger logger = LoggerFactory.getLogger(Anagrammatore.class);

	@Autowired
	private WordlistRepository repository;
	
	private List<String> dizionario = new ArrayList<>();
	
	@GetMapping("/anagramma/{parola}")
	  List<String> anagramma(@PathVariable String parola) {
		parola=parola.toLowerCase();
		logger.info("Receieved anagram request for "+parola);
		if (dizionario.isEmpty()) {
			if (riempiDizionario()) {
				logger.info("Dizionario riempito, era vuoto");
			}
			else {
				logger.info("Impossibile riempire il dizionario!");

			}
		}
		//Dizionario pieno
		
		
	    return Anagrammatore.anagramma(parola, dizionario);
	  }
	
	
	private boolean riempiDizionario() {
		boolean result = false;
		dizionario.clear();
		try {
			
			List<Wordlist> wlist = repository.findAll();
			for (Wordlist word : wlist) {
				dizionario.add(word.getWord());
			}
			dizionario.replaceAll(String::toLowerCase); 
			result=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		return result;
	}
	
}
