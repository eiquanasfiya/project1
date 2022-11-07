package com.corsarineri.portale.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.corsarineri.portale.model.Indizio;

import java.util.List;

public interface IndizioRepository extends JpaRepository<Indizio, Long> {

	@Query(value = "SELECT * \r\n" + 
	  		"FROM corsarineri.indizio\r\n" + 
	  		"WHERE numero >= ALL(select numero from corsarineri.indizio)", nativeQuery = true)
	  Indizio findLatest();
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM corsarineri.indizio WHERE numero = ?1", nativeQuery = true)
	  int deleteByNumber(Long number);

	public List<Indizio> findByBlockId(Integer blockId);

}