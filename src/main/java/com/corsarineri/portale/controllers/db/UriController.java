package com.corsarineri.portale.controllers.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.corsarineri.portale.exceptions.UriNotFoundException;
import com.corsarineri.portale.model.Uri;
import com.corsarineri.portale.repos.UriRepository;

@CrossOrigin
@RestController
class UriController {
	private Logger logger = LoggerFactory.getLogger(UriController.class);

	private final UriRepository repository;

	UriController(UriRepository repository) {
		this.repository = repository;
	}


	@PostMapping("/uri")
	Uri newUri(@RequestBody Uri newUri) {
		logger.info("Request to create new URI: "+newUri);
		return repository.save(newUri);
	}

	// Single item

	@GetMapping("/uri/{id}")
	Uri one(@PathVariable Long id) {
		logger.info("Request to get URI with id "+id);
		return repository.findById(id)
				.orElseThrow(() -> new UriNotFoundException(id));
	}

	@DeleteMapping("/uri/{id}")
	void deleteUri(@PathVariable Long id) {
		logger.info("Request to delete URI with id:"+id);
		repository.deleteById(id);
	}

	@PutMapping("/uri/{id}")
	Uri replaceUri(@RequestBody Uri newUri, @PathVariable Long id) {
		logger.info("Request to replace URI: "+id+ " with URI "+newUri);
		return repository.findById(id)
				.map(uri -> {
					logger.info("Found already existing URI: "+uri);
					if (newUri.getPercorso()!=null) uri.setPercorso(newUri.getPercorso());
					if (newUri.getTipo()!=null) uri.setTipo(newUri.getTipo());
					if (newUri.getIndizio()!=null) uri.setIndizio(newUri.getIndizio());

					return repository.save(uri);
				})
				.orElseGet(() -> {
					logger.info("Creating new uri "+newUri);
					newUri.setId(id);
					return repository.save(newUri);
				});
	}

	@GetMapping("/uriindizionumero/{number}") 
	List<Uri> ultimo(@PathVariable Long number) {
		logger.info("Request to find URI for indizio number "+number);
		return repository.findByNumber(number);
	}
}