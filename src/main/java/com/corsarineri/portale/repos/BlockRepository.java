package com.corsarineri.portale.repos;

import com.corsarineri.portale.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block,Long> {
}
