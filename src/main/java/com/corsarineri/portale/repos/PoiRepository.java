package com.corsarineri.portale.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.corsarineri.portale.model.Poi;

public interface PoiRepository extends JpaRepository<Poi, Long> {

	@Query(value = "SELECT * FROM corsarineri.poi WHERE LOWER(LUOGO) LIKE %?1% or LOWER(TAG) LIKE %?1% ", nativeQuery = true)
	List<Poi> findByNameOrTag(String keyword);
	
	

}