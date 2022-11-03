package com.corsarineri.portale.controllers.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.corsarineri.portale.exceptions.OrdineNotFoundException;
import com.corsarineri.portale.model.Ordine;
import com.corsarineri.portale.repos.OrdineRepository;

@CrossOrigin
@RestController
class OrdineController {
	private Logger logger = LoggerFactory.getLogger(OrdineController.class);

	private final OrdineRepository repository;

	OrdineController(OrdineRepository repository) {
		this.repository = repository;
	}


	@PostMapping("/ordine")
	Ordine newOrdine(@RequestBody Ordine ordine) {
		logger.info("Request to create new ordine: "+ordine);
		return repository.save(ordine);
	}

	// Single item
	@GetMapping("/ordine/{id}")
	Ordine one(@PathVariable Long id) {
		logger.info("Request to get ordine with id "+id);
		return repository.findById(id)
				.orElseThrow(() -> new OrdineNotFoundException(id));
	}

	@DeleteMapping("/ordine/{id}")
	void deleteOrdine(@PathVariable Long id) {
		logger.info("Request to delete ordine with id:"+id);
		repository.deleteById(id);
	}

	@PutMapping("/ordine/{id}")
	Ordine replaceOrdine(@RequestBody Ordine newOrdine, @PathVariable Long id) {
		logger.info("Request to replace ordine: "+id+ " with ordine "+newOrdine);
		return repository.findById(id)
				.map(ordine -> {
					logger.info("Found already existing URI: "+ordine);
					if (newOrdine.getNome()!=null) ordine.setNome(newOrdine.getNome());
					if (newOrdine.getOrdine()!=null) ordine.setOrdine(newOrdine.getOrdine());
					if (newOrdine.getStatoOrdine()!=null) ordine.setStatoOrdine(newOrdine.getStatoOrdine());
					if (newOrdine.getOrarioOrdine()!=null) ordine.setOrarioOrdine(newOrdine.getOrarioOrdine());
					if (newOrdine.getTavolo()!=null) ordine.setTavolo(newOrdine.getTavolo());

					return repository.save(ordine);
				})
				.orElseGet(() -> {
					logger.info("Creating new ordine "+newOrdine);
					newOrdine.setId(id);
					return repository.save(newOrdine);
				});
	}

	
}