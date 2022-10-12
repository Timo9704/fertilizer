package de.aquaristik.kosmos.fertilizerService.nutrient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientRepository extends JpaRepository<Nutrient, Long> {

    Nutrient findByName(String name);

}
