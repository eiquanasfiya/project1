package com.corsarineri.portale.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.corsarineri.portale.model.Uri;

public interface UriRepository extends JpaRepository<Uri, Long> {
	@Query(value = "SELECT * FROM corsarineri.uri WHERE indizio = ?1", nativeQuery = true)
	  List<Uri> findByNumber(Long number);
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM corsarineri.uri WHERE indizio = ?1", nativeQuery = true)
	  int deleteByIndizioNumber(Long number);
}