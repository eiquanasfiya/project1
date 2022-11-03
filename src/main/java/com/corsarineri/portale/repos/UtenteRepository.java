package com.corsarineri.portale.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.corsarineri.portale.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
	@Query(value = "SELECT * FROM corsarineri.utente WHERE username = ?1 AND password = ?2", nativeQuery = true)
	  Utente authenticate(String username, String password);
	
}