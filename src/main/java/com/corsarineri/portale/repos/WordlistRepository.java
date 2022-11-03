package com.corsarineri.portale.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsarineri.portale.model.Wordlist;

public interface WordlistRepository extends JpaRepository<Wordlist, Long> {

	

}