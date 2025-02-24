package com.example.evola.repositories;

import com.example.evola.tables.ConstrOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstrOptionRepository extends JpaRepository<ConstrOption, Long> {
}
