package com.corsarineri.portale.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.corsarineri.portale.model.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

	@Query(value = "SELECT * FROM corsarineri.biblioteca WHERE lower(testo) LIKE %?1%", nativeQuery = true)
	List<Biblioteca> findByText(String keyword);
	

	@Query(value = "SELECT * FROM corsarineri.biblioteca WHERE lower(testo) LIKE '% '||?1||' %'", nativeQuery = true)
	List<Biblioteca> findByWord(String keyword);

}