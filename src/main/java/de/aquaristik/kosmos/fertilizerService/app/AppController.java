package de.aquaristik.kosmos.fertilizerService.app;

import com.google.gson.Gson;
import de.aquaristik.kosmos.fertilizerService.aquarium.Aquarium;
import de.aquaristik.kosmos.fertilizerService.aquarium.AquariumController;
import de.aquaristik.kosmos.fertilizerService.fertilizer.Fertilizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

    @PostMapping(value = "/convert")
    public ResponseEntity convert(@RequestBody String jsonString) throws InterruptedException{
        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);

        Fertilizer[] fertilizers = initialize(aquarium.getFertilizerInUse());
        Gson fertilizerGson = new Gson();
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("{");
        for (int i = 0; i < fertilizers.length; i++) {
            fertilizers[i].calculateForAquarium(aquarium.getLiter());
            fertilizers[i].setDosage(1);
            jsonResponse.append(fertilizerGson.toJsonTree(fertilizers[i]));
        }
        jsonResponse.append("}");

        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping(value = "/consumption")
    public ResponseEntity consumption(@RequestBody String jsonString) throws InterruptedException {
        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);
        AquariumController aquariumController = new AquariumController(aquarium);

        double[] consumptionArray = aquariumController.consumption();

        StringBuilder consumptionJson = new StringBuilder();
        consumptionJson.append("{");
        consumptionJson.append("\"nitrat\":" + consumptionArray[0]);
        consumptionJson.append(",");
        consumptionJson.append("\"phosphat\":" + consumptionArray[1]);
        consumptionJson.append(",");
        consumptionJson.append("\"kalium\":" + consumptionArray[2]);
        consumptionJson.append(",");
        consumptionJson.append("\"eisen\":" + consumptionArray[3]);
        consumptionJson.append("}");

        return ResponseEntity.ok(consumptionJson);
    }

    public Fertilizer[] initialize(int[] fertilierInUse) {
        Fertilizer[] fertilizers = new Fertilizer[fertilierInUse.length];

        for (int i = 0; i < fertilierInUse.length; i++) {

            switch (fertilierInUse[i]) {
                case 0:
                    fertilizers[i] = new Fertilizer("NPK Power", 0.62, 0.035, 0.309, 0, 0.028);
                    break;
                case 1:
                    fertilizers[i] = new Fertilizer("Eisen Power", 0, 0, 0.309, 0.038, 0.149);
                    break;
                case 2:
                    fertilizers[i] = new Fertilizer("N Power", 1.06, 0, 0.44, 0, 0);
                    break;
                case 3:
                    fertilizers[i] = new Fertilizer("P Power", 0, 0.07, 0.083, 0, 0);
                    break;
                case 4:
                    fertilizers[i] = new Fertilizer("K Power", 0, 0, 0.29, 0, 0);
                    break;
                default:
                    System.out.println("i liegt nicht zwischen null und drei");
                    break;
            }
        }

        return fertilizers;
    }

    ;
}
