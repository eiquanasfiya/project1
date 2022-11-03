package com.corsarineri.portale.controllers.db;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.corsarineri.portale.model.Poi;
import com.corsarineri.portale.repos.PoiRepository;
import com.corsarineri.portale.utils.MapSorter;
import com.corsarineri.portale.exceptions.PoiNotFoundException;
@CrossOrigin
@RestController
class PoiController {
	private Logger logger = LoggerFactory.getLogger(PoiController.class);
	private final PoiRepository repository;

	PoiController(PoiRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	@GetMapping("/poi")
	List<Poi> all() {
		logger.info("Request list all poi");
		return repository.findAll();
	}

	@PostMapping("/poi")
	Poi newPoi(@RequestBody Poi newPoi) {
		logger.info("Request to create new poi: "+newPoi);
		return repository.save(newPoi);
	}

	// Single item

	@GetMapping("/poi/{id}")
	Poi one(@PathVariable Long id) {
		logger.info("Request to get poi with id "+id);
		return repository.findById(id)
				.orElseThrow(() -> new PoiNotFoundException(id));
	}


	@PutMapping("/poi/{id}")
	Poi replacePoi(@RequestBody Poi newPoi, @PathVariable Long id) {
		logger.info("Request to replace poi: "+id+ " with poi "+newPoi);
		return repository.findById(id)
				.map(poi -> {
					logger.info("Found already existing poi: "+poi);
					if (newPoi.getCap()!=null) poi.setCap(newPoi.getCap());
					if (newPoi.getCommento()!=null) poi.setCommento(newPoi.getCommento());
					if (newPoi.getComune()!=null) poi.setComune(newPoi.getComune());
					if (newPoi.getFrazione()!=null) poi.setFrazione(newPoi.getFrazione());
					if (newPoi.getLat()!=null) poi.setLat(newPoi.getLat());
					if (newPoi.getLon()!=null) poi.setLon(newPoi.getLon());
					if (newPoi.getLuogo()!=null) poi.setLuogo(newPoi.getLuogo());
					if (newPoi.getTag()!=null) poi.setTag(newPoi.getTag());

					return repository.save(poi);
				})
				.orElseGet(() -> {
					logger.info("Creating new poi "+newPoi);
					newPoi.setId(id);
					return repository.save(newPoi);
				});
	}

	@DeleteMapping("/poi/{id}")
	void deletePoi(@PathVariable Long id) {
		logger.info("Request to delete poi with id:"+id);
		repository.deleteById(id);
	}



	@GetMapping("/poiByKeyword/{keywords}") 
	ResponseEntity<Object> byKeywords(@PathVariable String keywords) {
		logger.info("Request to get poi by keywords: "+keywords);

		String [] keywordArray = keywords.split(" ");
		Set<Poi> uniquePois = new HashSet<>();
		String[] keywordArray2 = Arrays.stream(keywordArray).distinct().toArray(String[]::new);
		List<String> keywordList = Arrays.asList(keywordArray2);
		keywordList.replaceAll(String::toLowerCase); //keywordList contains only lowercase
		logger.info("Unique keywords: " +Arrays.asList(keywordList));
		HashMap<String,List<Poi>> resultsMap = new HashMap<>();
		for (String keyword : keywordList) {
			List<Poi> pois = repository.findByNameOrTag(keyword);

			resultsMap.put(keyword,pois);
		}
		logger.info("resultsMap: " +resultsMap);
		for(String key : resultsMap.keySet()) {
			for (Poi poi : resultsMap.get(key)) 
				uniquePois.add(poi); 
		}
		logger.info("uniquePois: " +uniquePois);
		Map<Poi,Integer> scores = new HashMap<>();
		for (Poi poi : uniquePois) {
			scores.put(poi, 0); //initialize scores to 0
		}
		for (Poi poi : uniquePois) {
			for (String keyword : keywordList) {

				if (!poi.getLuogo().toLowerCase().contains(keyword) && !poi.getTag().toLowerCase().contains(keyword)) {
					scores.remove(poi); //basta una keyword mancante e salto il poi
					logger.info("Removing poi " + poi.getLuogo() + " because luogo and tags " + poi.getTag() + " do not contain "+keyword);
					break;
				}
				else {
					logger.info("Considering poi " + poi.getLuogo());
					if (poi.getLuogo().toLowerCase().contains(keyword)) {
						scores.put(poi, scores.get(poi)+5);
						logger.info("adding 5 points to POI " + poi.getLuogo() + " because it contains "+keyword);
					}
					if (poi.getTag().toLowerCase().contains(keyword)) {
						scores.put(poi, scores.get(poi)+3);
						logger.info("adding 3 points to POI " + poi.getLuogo() + " because its tag contains "+keyword);
					}
				}
			}
		}
		logger.info("scores: " +scores);
		Map<Poi,Integer> sorted = MapSorter.sortByValue(scores);
		logger.info("sorted: " +sorted);
		return ResponseEntity.ok(sorted.keySet());
	}
	//	@GetMapping("/poiByKeyword/{keywords}") 
	//	ResponseEntity<Object> ultimo(@PathVariable String keywords) {
	//		logger.info("Request to get poi by keywords: "+keywords);
	//
	//		String [] keywordArray = keywords.split(" ");
	//		Set<Poi> uniquePois = new HashSet<>();
	//		String[] keywordArray2 = Arrays.stream(keywordArray).distinct().toArray(String[]::new);
	//		List<String> keywordList = Arrays.asList(keywordArray2);
	//		keywordList.replaceAll(String::toLowerCase); //keywordList contains only lowercase
	//		logger.info("Unique keywords: " +Arrays.asList(keywordList));
	//		HashMap<String,List<Poi>> resultsMap = new HashMap<>();
	//		for (String keyword : keywordList) {
	//			resultsMap.put(keyword,repository.findByNameOrTag(keyword));
	//		}
	//		logger.info("resultsMap: " +resultsMap);
	//		for(String key : resultsMap.keySet()) {
	//			for (Poi poi : resultsMap.get(key)) 
	//				uniquePois.add(poi); 
	//		}
	//		logger.info("uniquePois: " +uniquePois);
	//		Map<Poi,Integer> scores = new HashMap<>();
	//		for (Poi poi : uniquePois) {
	//			scores.put(poi, 0); //initialize scores to 0
	//		}
	//		for (Poi poi : uniquePois) {
	//			for (String keyword : keywordList) {
	//				if (poi.getLuogo().toLowerCase().contains(keyword)) scores.put(poi, scores.get(poi)+5);
	//				logger.info("adding 5 points to POI " + poi.getLuogo() + " because it contains "+keyword);
	//				if (poi.getTag().toLowerCase().contains(keyword)) scores.put(poi, scores.get(poi)+3);
	//				logger.info("adding 3 points to POI " + poi.getLuogo() + " because its tag contains "+keyword);
	//
	//			}
	//		}
	//		logger.info("scores: " +scores);
	//		Map<Poi,Integer> sorted = MapSorter.sortByValue(scores);
	//		logger.info("sorted: " +sorted);
	//		return ResponseEntity.ok(sorted.keySet());
	//	}
	//	

}