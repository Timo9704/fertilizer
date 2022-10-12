package de.aquaristik.kosmos.fertilizerService.fertilizer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

    Fertilizer findByName(String name);
    List<Fertilizer> findByNitratIsGreaterThan(double i);
}

