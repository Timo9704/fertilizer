package de.aquaristik.kosmos.fertilizerService.app;

import com.google.gson.Gson;
import de.aquaristik.kosmos.fertilizerService.aquarium.Aquarium;
import de.aquaristik.kosmos.fertilizerService.aquarium.AquariumController;
import de.aquaristik.kosmos.fertilizerService.fertilizer.FertilizerRepository;
import de.aquaristik.kosmos.fertilizerService.nutrient.NutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class AppController {

    @Autowired
    private final FertilizerRepository fertilizerRepo;

    @Autowired
    private final NutrientRepository nutrientRepository;

    public AppController(FertilizerRepository fertilizerRepo, NutrientRepository nutrientRepository) {
        this.fertilizerRepo = fertilizerRepo;
        this.nutrientRepository = nutrientRepository;
    }

    @GetMapping("getAllFertilizer")
    public ResponseEntity getAllFertilizer() {

        return ResponseEntity.ok(fertilizerRepo.findAll());
    }

    @PostMapping(value = "/consumption")
    public ResponseEntity consumption(@RequestBody String jsonString) throws IOException {

        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);
        AquariumController aquariumController = new AquariumController(aquarium);

        double[] consumptionArray = aquariumController.consumption();

        String response = "Der Verbrauch für 7 Tage liegt bei: \n"
                + "Nitrat = " + consumptionArray[0] + " mg/l pro Tag\n"
                + "Phosphat = " + consumptionArray[1] + " mg/l pro Tag\n"
                + "Kalium = " + consumptionArray[2] + " mg/l pro Tag\n"
                + "Eisen = " + consumptionArray[3] + " mg/ pro Tag\n";

        return ResponseEntity.ok(response);
    }
}
