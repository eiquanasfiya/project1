package com.corsarineri.portale.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.corsarineri.portale.model.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
	
	
}