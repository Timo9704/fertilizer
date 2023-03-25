package de.aquaristik.kosmos.fertilizerService.controller;

import com.google.gson.Gson;
import de.aquaristik.kosmos.fertilizerService.model.Aquarium;
import de.aquaristik.kosmos.fertilizerService.service.AquariumService;
import de.aquaristik.kosmos.fertilizerService.model.Fertilizer;
import de.aquaristik.kosmos.fertilizerService.service.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class AppController {

    @Autowired
    FertilizerService fertilizerService;

    @PostMapping(value = "/convert")
    public ResponseEntity convert(@RequestBody String jsonString) throws InterruptedException{
        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);

        int[] fertilizerInUse = aquarium.getFertilizerInUse();

        List<Fertilizer> respList = new ArrayList<>();
        for (int i = 0; i < fertilizerInUse.length; i++) {
            Fertilizer fertilizer = fertilizerService.getFertilizer(fertilizerInUse[i]);
            fertilizer.calculateForAquarium(aquarium.getLiter());
            fertilizer.setDosage(1);
            respList.add(fertilizer);
        }
        return ResponseEntity.ok(respList);
    }

    @PostMapping(value = "/consumption")
    public ResponseEntity consumption(@RequestBody String jsonString) throws InterruptedException {
        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);
        AquariumService aquariumService = new AquariumService(aquarium);

        double[] consumptionArray = aquariumService.consumption();

        Map<String,Double> resp = new HashMap<>();

        resp.put("nitrate", consumptionArray[0]);
        resp.put("phosphate", consumptionArray[1]);
        resp.put("potassium", consumptionArray[2]);
        resp.put("iron", consumptionArray[3]);

        return ResponseEntity.ok(resp);
    }
}
