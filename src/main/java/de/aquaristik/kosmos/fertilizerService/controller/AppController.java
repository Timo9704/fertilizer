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

        List<Fertilizer> =
        List<Fertilizer> resp = new ArrayList<>();
        for (int i = 0; i < fertilizers.length; i++) {
            fertilizers[i].calculateForAquarium(aquarium.getLiter());
            fertilizers[i].setDosage(1);
            resp.add(fertilizers[i]);
        }

        return ResponseEntity.ok(resp);
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
