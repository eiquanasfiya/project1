package com.corsarineri.portale.controllers.db;

import java.util.List;
import java.util.Optional;

import com.corsarineri.portale.dto.IndizioDTO;
import com.corsarineri.portale.service.BlockService;
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

import com.corsarineri.portale.model.Indizio;
import com.corsarineri.portale.repos.IndizioRepository;
import com.corsarineri.portale.repos.UriRepository;
import com.corsarineri.portale.exceptions.IndizioNotFoundException;
@CrossOrigin
@RestController
class IndizioController {



	private Logger logger = LoggerFactory.getLogger(IndizioController.class);
	private final IndizioRepository repository;
	private final UriRepository uriRepository;
	private final BlockService blockService;

	IndizioController(IndizioRepository repository, UriRepository uriRepository, BlockService blockService) {
		this.repository = repository;
		this.uriRepository = uriRepository;
		this.blockService = blockService;
	}

	// Aggregate root
	@GetMapping("/indizi")
	List<Indizio> all() {
		logger.info("Request list all indizi");
		return repository.findAll();
	}

	@PostMapping("/indizi")
	Indizio newIndizio(@RequestBody Indizio newIndizio) {
		logger.info("Request to create new indizio: "+newIndizio);
		newIndizio.setBlockId(blockService.getCurrentBlockValue());
		newIndizio.setPhoto(false);
		newIndizio.setSemaforo("rosso");
		return repository.save(newIndizio);
	}

	// Single item

	@GetMapping("/indizi/{id}")
	Indizio one(@PathVariable Long id) {
		logger.info("Request to get indizio with id "+id);
		return repository.findById(id)
				.orElseThrow(() -> new IndizioNotFoundException(id));
	}

	@GetMapping("/indiziblock/{id}")
	List<Indizio> getAllindiziByBlockid(@PathVariable Integer id) {
		logger.info("Request to get indizio with block id "+id);
		return repository.findByBlockId(id);
	}

	@GetMapping("/faseindizio/{id}") 
	Boolean faseIndizio(@PathVariable Long id) {
		logger.info("Request to get fase for indizio "+id);
		return repository.findById(id).get().getAiutoArrivato();

	}
	@PutMapping("indiziphoto/{id}")
	public Indizio changePhoto(@RequestBody IndizioDTO photoDTO, @PathVariable Long id){
		Optional<Indizio> indizio = repository.findById(id);
		if(indizio.isPresent()){
			 indizio.get().setPhoto(photoDTO.isFlag());
			 return repository.save(indizio.get());
		}
		throw new IndizioNotFoundException(id);
	}

	@PutMapping("/indizi/{id}")
	Indizio replaceIndizio(@RequestBody Indizio newIndizio, @PathVariable Long id) {
		logger.info("Request to replace indizio: "+id+ " with indizio "+newIndizio);
		return repository.findById(id)
				.map(indizio -> {
					logger.info("Found already existing indizio: "+indizio);
					if (newIndizio.getAnno()!=null) indizio.setAnno(newIndizio.getAnno());
					if (newIndizio.getNome()!=null) indizio.setNome(newIndizio.getNome());
					if (newIndizio.getNumero()!=null) indizio.setNumero(newIndizio.getNumero());
					if (newIndizio.getAiutoArrivato()!=null) indizio.setAiutoArrivato(newIndizio.getAiutoArrivato());
					if (newIndizio.getAiutoArrivato2()!=null) indizio.setAiutoArrivato2(newIndizio.getAiutoArrivato2());
					if (newIndizio.getOrarioAiuto()!=null) indizio.setOrarioAiuto(newIndizio.getOrarioAiuto());
					if (newIndizio.getOrarioAiuto2()!=null) indizio.setOrarioAiuto2(newIndizio.getOrarioAiuto2());
					if (newIndizio.getOrarioArrivoInBase()!=null) indizio.setOrarioArrivoInBase(newIndizio.getOrarioArrivoInBase());
					if (newIndizio.getOrarioRitiroComitato()!=null) indizio.setOrarioRitiroComitato(newIndizio.getOrarioRitiroComitato());
					if (newIndizio.getOrarioSoluzione()!=null) indizio.setOrarioSoluzione(newIndizio.getOrarioSoluzione());

					return repository.save(indizio);
				})
				.orElseGet(() -> {
					logger.info("Creating new indizio "+newIndizio);
					newIndizio.setId(id);
					return repository.save(newIndizio);
				});
	}

	@DeleteMapping("/indizi/{id}")
	void deleteIndizio(@PathVariable Long id) {
		logger.info("Request to delete indizio with id:"+id);
		repository.deleteById(id);
	}

	@DeleteMapping("/indizioEUri/{number}")
	void deleteIndizioEUri(@PathVariable Long number) {
		logger.info("Request to delete indizio e uri with number:"+number);
		
		int deletedUris= uriRepository.deleteByIndizioNumber(number);
		logger.info("Deleted "+deletedUris + " uris");
		int deletedIndizi = repository.deleteByNumber(number);
		logger.info("Deleted "+deletedIndizi + " indizio");
	}


	@GetMapping("/ultimoindizio") 
	ResponseEntity<Object> ultimo() {
		logger.info("Request to get latest indizio");
		Indizio latest = repository.findLatest();
		if (latest==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else return ResponseEntity.ok(latest);
	}
}