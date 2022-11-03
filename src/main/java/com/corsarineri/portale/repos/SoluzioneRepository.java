package com.corsarineri.portale.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.corsarineri.portale.model.Soluzione;

public interface SoluzioneRepository extends JpaRepository<Soluzione, Long> {

	@Query(value = "SELECT * FROM corsarineri.soluzione WHERE lower(stato) = 'corretto' and id > ?1", nativeQuery = true)
	  List<Soluzione> findSoluzioniCorretteAfterId(Long id);
	
	@Query(value = "SELECT * FROM corsarineri.soluzione WHERE lower(stato) = 'attesa' and id > ?1", nativeQuery = true)
	  List<Soluzione> findSoluzioniInAttesaAfterId(Long id);
	
	@Query(value = "SELECT * FROM corsarineri.soluzione WHERE lower(stato) = 'inviato' and id > ?1", nativeQuery = true)
	  List<Soluzione> findSoluzioniInviateAfterId(Long id);
	
	@Query(value = "SELECT * FROM corsarineri.soluzione WHERE scartato=true and id > ?1", nativeQuery = true)
	  List<Soluzione> findSoluzioniScartateAfterId(Long id);

	@Query(value = "SELECT * FROM corsarineri.soluzione WHERE id > ?1", nativeQuery = true)
	  List<Soluzione> findSoluzioniAfterId(Long id);
}