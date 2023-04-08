package de.aquaristik.kosmos.fertilizerService.controller;

import com.google.gson.Gson;
import de.aquaristik.kosmos.fertilizerService.model.Aquarium;
import de.aquaristik.kosmos.fertilizerService.model.DoseMixer;
import de.aquaristik.kosmos.fertilizerService.model.Fertilizer;
import de.aquaristik.kosmos.fertilizerService.service.AquariumService;
import de.aquaristik.kosmos.fertilizerService.service.DoseMixerService;
import de.aquaristik.kosmos.fertilizerService.service.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class AppController {

    @Autowired
    FertilizerService fertilizerService;

    @Autowired
    DoseMixerService doseMixerService;


    @GetMapping(value = "/getAllFertilizer")
    public ResponseEntity getAllFertilizer(){
        fertilizerService.init();
        return ResponseEntity.ok(fertilizerService.getAllFertilizers());
    }

    @PostMapping(value = "/convert")
    public ResponseEntity convert(@RequestBody String jsonString){
        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);

        int[] fertilizerInUse = aquarium.getFertilizerInUse();

        fertilizerService.init();

        List<Fertilizer> respList = new ArrayList<>();
        for (int i = 0; i < fertilizerInUse.length; i++) {
            Fertilizer fertilizer = fertilizerService.getFertilizer(fertilizerInUse[i]);
            fertilizer.calculateForAquarium(aquarium.getLiter());
            fertilizer.setDosageModfiy(aquarium.getDosage());
            respList.add(fertilizer);
        }
        return ResponseEntity.ok(respList);
    }

    @PostMapping(value = "/consumption")
    public ResponseEntity consumption(@RequestBody String jsonString){
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

    @PostMapping(value = "/calculation")
    public ResponseEntity calculation(@RequestBody String jsonString) throws InterruptedException {
        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);
        AquariumService aquariumService = new AquariumService(aquarium);

        double[] consumptionArray = aquariumService.consumption();

        int[] fertilizerInUse = aquarium.getFertilizerInUse();

        fertilizerService.init();

        List<Fertilizer> fertilizerList = new ArrayList<>();
        for (int i = 0; i < fertilizerInUse.length; i++) {
            Fertilizer fertilizer = fertilizerService.getFertilizer(fertilizerInUse[i]);
            fertilizer.calculateForAquariumPrecise(aquarium.getLiter());
            fertilizerList.add(fertilizer);
        }

        DoseMixer doseMixer = new DoseMixer(consumptionArray[0], consumptionArray[1], consumptionArray[2], consumptionArray[3], fertilizerList);

        boolean optimalDosage = doseMixerService.calculateDosage(fertilizerList, doseMixer);

        Map<String, Double> resp = new HashMap<>();

        if(optimalDosage) {
            System.out.println(fertilizerList.get(0).getName() + " " + fertilizerList.get(0).getDosage());
            for (int i = 0; i < fertilizerList.size(); i++) {
                resp.put(String.valueOf(fertilizerList.get(i).getName()), fertilizerList.get(i).getDosage());
            }
        }else{
            resp.put("Error", 0.0);
        }

        return ResponseEntity.ok(resp);
    }
}
