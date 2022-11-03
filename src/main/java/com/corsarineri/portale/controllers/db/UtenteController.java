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

import com.corsarineri.portale.exceptions.UtenteNotFoundException;
import com.corsarineri.portale.model.Utente;
import com.corsarineri.portale.repos.UtenteRepository;

@CrossOrigin
@RestController
class UtenteController {
	private Logger logger = LoggerFactory.getLogger(UtenteController.class);

	private final UtenteRepository repository;

	UtenteController(UtenteRepository repository) {
		this.repository = repository;
	}


	@PostMapping("/utente")
	Utente newUtente(@RequestBody Utente utente) {
		logger.info("Request to create new utente: "+utente);
		return repository.save(utente);
	}

	// Single item

	@GetMapping("/utente/{id}")
	Utente one(@PathVariable Long id) {
		logger.info("Request to get utente with id "+id);
		return repository.findById(id)
				.orElseThrow(() -> new UtenteNotFoundException(id));
	}

	@DeleteMapping("/utente/{id}")
	void deleteUtente(@PathVariable Long id) {
		logger.info("Request to delete utente with id:"+id);
		repository.deleteById(id);
	}

	@PutMapping("/utente/{id}")
	Utente replaceUtente(@RequestBody Utente newUtente, @PathVariable Long id) {
		logger.info("Request to replace utente: "+id+ " with utente "+newUtente);
		return repository.findById(id)
				.map(utente -> {
					logger.info("Found already existing URI: "+utente);
					if (newUtente.getNome()!=null) utente.setNome(newUtente.getNome());
					if (newUtente.getUsername()!=null) utente.setUsername(newUtente.getUsername());
					if (newUtente.getPassword()!=null) utente.setPassword(newUtente.getPassword());
					if (newUtente.getUltimoIp()!=null) utente.setUltimoIp(newUtente.getUltimoIp());
					if (newUtente.getUltimoHost()!=null) utente.setUltimoHost(newUtente.getUltimoHost());

					return repository.save(utente);
				})
				.orElseGet(() -> {
					logger.info("Creating new utente "+newUtente);
					newUtente.setId(id);
					return repository.save(newUtente);
				});
	}

	@PostMapping("/autenticazioneutente")
	ResponseEntity<Object> auth(@RequestBody Utente utente) {
		logger.info("Request to authenticate utente: "+utente);

		Utente user = repository.authenticate(utente.getUsername(), utente.getPassword());
		if (user==null)
			//401
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		else
			return ResponseEntity.ok(user);
	}
}