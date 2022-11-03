package com.corsarineri.portale.controllers.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.corsarineri.portale.model.Biblioteca;
import com.corsarineri.portale.model.Libro;
import com.corsarineri.portale.repos.BibliotecaRepository;

@CrossOrigin
@RestController
public class BibliotecaController {

	private Logger logger = LoggerFactory.getLogger(BibliotecaController.class);
	private final BibliotecaRepository repository;
	BibliotecaController(BibliotecaRepository repository) {
		this.repository = repository;
	}


	@GetMapping("/biblioteca/{parole}")
	public Set<Libro> biblioteca(@PathVariable String parole) {
		logger.info("Request biblioteca with keywords "+parole);
		String[] paroleArray = parole.split(" ");
		List<String> paroleList = Arrays.asList(paroleArray);
		paroleList.replaceAll(String::toLowerCase); 
		Set<Biblioteca> setBiblio = new LinkedHashSet<>();
		Set<Biblioteca> setBiblioParoleIntere = new LinkedHashSet<>();
		for (String parola : paroleList) {

			setBiblioParoleIntere.addAll(repository.findByWord(parola));

		}
		for (String parola : paroleList) {

			setBiblio.addAll(repository.findByText(parola));

		}

		Set<Biblioteca> setBiblioRisultato = new LinkedHashSet<>();
		Set<Biblioteca> setBiblioRisultatoParoleIntere = new LinkedHashSet<>();

		Set<Libro> result = new LinkedHashSet<>();
		for (Biblioteca biblioteca : setBiblioParoleIntere) {
			String[] paroleAr = new String[paroleList.size()];
			if (containsAllWholeWords(biblioteca.getTesto(),paroleList.toArray(paroleAr))) {
				setBiblioRisultatoParoleIntere.add(biblioteca);
			}
		}
		for (Biblioteca biblioteca : setBiblio) {
			String[] paroleAr = new String[paroleList.size()];
			if (containsAllWords(biblioteca.getTesto(),paroleList.toArray(paroleAr))) {
				if (!setBiblioRisultatoParoleIntere.contains(biblioteca))setBiblioRisultato.add(biblioteca);
			}
		}


		for (Biblioteca biblioteca : setBiblioRisultatoParoleIntere) {
			result.add(new Libro(biblioteca.getTitolo(),biblioteca.getUrl(),true));
		}
		for (Biblioteca biblioteca : setBiblioRisultato) {
			result.add(new Libro(biblioteca.getTitolo(),biblioteca.getUrl(),false));
		}
		return result;
	}

	private boolean containsAllWords(String word, String [] keywords) {
		for (String k : keywords)
			if (!word.toLowerCase().contains(k)) return false;
		return true;
	}
	private boolean containsAllWholeWords(String word, String [] keywords) {
		for (String k : keywords)
			if (!word.toLowerCase().contains(" "+k +" ")) return false;
		return true;
	}
}
