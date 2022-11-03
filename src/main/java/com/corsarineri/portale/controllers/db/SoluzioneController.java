package com.corsarineri.portale.controllers.db;

import java.util.ArrayList;
import java.util.List;

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


import com.corsarineri.portale.model.Soluzione;
import com.corsarineri.portale.repos.SoluzioneRepository;

import com.corsarineri.portale.exceptions.SoluzioneNotFoundException;
@CrossOrigin
@RestController
class SoluzioneController {
	private Logger logger = LoggerFactory.getLogger(SoluzioneController.class);
	private final SoluzioneRepository repository;
	private final List<String> fasiAmmesse = new ArrayList<>();
	private final List<String> statiAmmessi = new ArrayList<>();
	private final List<String> giudiziAmmessi = new ArrayList<>();
	
	SoluzioneController(SoluzioneRepository repository) {
		this.repository = repository;
		fasiAmmesse.add("silenzio");
		fasiAmmesse.add("aiuto");
		
		statiAmmessi.add("inviato");
		statiAmmessi.add("corretto");
		statiAmmessi.add("attesa");
		
		giudiziAmmessi.add("verde");
		giudiziAmmessi.add("giallo");
		giudiziAmmessi.add("rosso");
		
		
	}

	// Aggregate root

	@GetMapping("/soluzioni")
	List<Soluzione> all() {
		logger.info("Request list all soluzioni");
		return repository.findAll();
	}

	@PostMapping("/soluzioni")
	ResponseEntity<Object> newSoluzione(@RequestBody Soluzione newSoluzione) {
		logger.info("Request to create new soluzione: "+newSoluzione);
		if (!fasiAmmesse.contains(newSoluzione.getFase())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		if (newSoluzione.getSemaforo()!=null && newSoluzione.getSemaforo()!="" && !giudiziAmmessi.contains(newSoluzione.getSemaforo())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		if (!statiAmmessi.contains(newSoluzione.getStato())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		return ResponseEntity.ok(repository.save(newSoluzione));
	}

	// Single item

	@GetMapping("/soluzioni/{id}")
	Soluzione one(@PathVariable Long id) {
		logger.info("Request to get soluzione with id "+id);
		return repository.findById(id)
				.orElseThrow(() -> new SoluzioneNotFoundException(id));
	}

	@GetMapping("/soluzioniarchiviate/{id}")
	List<Soluzione> archiviate(@PathVariable Long id) {
		logger.info("Request to get soluzioni corrette after id "+id);
		return repository.findSoluzioniCorretteAfterId(id);

	}

	@GetMapping("/soluzioniinviate/{id}")
	List<Soluzione> inviate(@PathVariable Long id) {
		logger.info("Request to get soluzioni inviate after id "+id);
		return repository.findSoluzioniInviateAfterId(id);

	}

	@GetMapping("/soluzioniinattesa/{id}")
	List<Soluzione> inattesa(@PathVariable Long id) {
		logger.info("Request to get soluzioni in attesa after id "+id);
		return repository.findSoluzioniInAttesaAfterId(id);

	}

	@GetMapping("/soluzioniscartate/{id}")
	List<Soluzione> scartate(@PathVariable Long id) {
		logger.info("Request to get soluzioni scartate after id "+id);
		return repository.findSoluzioniScartateAfterId(id);

	}


	@GetMapping("/soluzionidopo/{id}")
	List<Soluzione> dopo(@PathVariable Long id) {
		logger.info("Request to get soluzioni after id "+id);
		return repository.findSoluzioniAfterId(id);

	}

	@PutMapping("/soluzioni/{id}")
	Soluzione replacesoluzione(@RequestBody Soluzione newSoluzione, @PathVariable Long id) {
		logger.info("Request to replace soluzione: "+id+ " with soluzione "+newSoluzione);
		return repository.findById(id)
				.map(soluzione -> {
					logger.info("Found already existing soluzione: "+soluzione);
					if (newSoluzione.getIndizio()!=null) soluzione.setIndizio(newSoluzione.getIndizio());
					if (newSoluzione.getScartato()!=null) soluzione.setScartato(newSoluzione.getScartato());
					if (newSoluzione.getCorrezione()!=null) soluzione.setCorrezione(newSoluzione.getCorrezione());
					if (newSoluzione.getCorrezioneLuogoSoluzione()!=null) soluzione.setCorrezioneLuogoSoluzione(newSoluzione.getCorrezioneLuogoSoluzione());
					if (newSoluzione.getFase()!=null) soluzione.setFase(newSoluzione.getFase());
					if (newSoluzione.getLuogoSoluzione()!=null) soluzione.setLuogoSoluzione(newSoluzione.getLuogoSoluzione());
					if (newSoluzione.getOrarioArchiviazione()!=null) soluzione.setOrarioArchiviazione(newSoluzione.getOrarioArchiviazione());
					if (newSoluzione.getOrarioInvioATc()!=null) soluzione.setOrarioInvioATc(newSoluzione.getOrarioInvioATc());
					if (newSoluzione.getOrarioStampa()!=null) soluzione.setOrarioStampa(newSoluzione.getOrarioStampa());
					if (newSoluzione.getRagionamento()!=null) soluzione.setRagionamento(newSoluzione.getRagionamento());
					if (newSoluzione.getSemaforo()!=null) soluzione.setSemaforo(newSoluzione.getSemaforo());
					if (newSoluzione.getStato()!=null) soluzione.setStato(newSoluzione.getStato());

					return repository.save(soluzione);
				})
				.orElseGet(() -> {
					logger.info("Creating new soluzione "+newSoluzione);
					newSoluzione.setId(id);
					return repository.save(newSoluzione);
				});
	}


	@DeleteMapping("/soluzioni/{id}")
	void deleteSoluzione(@PathVariable Long id) {
		logger.info("Request to delete soluzione with id:"+id);
		repository.deleteById(id);
	}

}