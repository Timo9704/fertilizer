package de.aquaristik.kosmos.fertilizerService.app;

import com.google.gson.Gson;
import de.aquaristik.kosmos.fertilizerService.aquarium.Aquarium;
import de.aquaristik.kosmos.fertilizerService.aquarium.AquariumController;
import de.aquaristik.kosmos.fertilizerService.fertilizer.Fertilizer;
import de.aquaristik.kosmos.fertilizerService.fertilizer.FertilizerController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

    Fertilizer npk_power;

    Fertilizer eisen_power;


    @PostMapping(value = "/consumption")
    public ResponseEntity consumption(@RequestBody String jsonString) {
        initialize();
        Gson gson = new Gson();
        Aquarium aquarium = gson.fromJson(jsonString, Aquarium.class);
        AquariumController aquariumController = new AquariumController(aquarium);

        double[] consumptionArray = aquariumController.consumption();

        FertilizerController npk_power_controller = new FertilizerController(npk_power);
        FertilizerController eisen_power_controller = new FertilizerController(eisen_power);

        npk_power_controller.calculateForAquarium(aquarium.getLiter());
        eisen_power_controller.calculateForAquarium(aquarium.getLiter());

        double[] array = new double[6];

        array[0] = consumptionArray[0];
        array[1] = consumptionArray[1];
        array[2] = consumptionArray[2];
        array[3] = consumptionArray[3];
        array[4] = npk_power_controller.calculateNpkDose(consumptionArray);
        array[5] = eisen_power_controller.calculateFeDose(consumptionArray);

        return ResponseEntity.ok(sendResponse(array));
    }

    public String sendResponse(double[] consumptionArray){

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"nitrat\":" + consumptionArray[0]);
        json.append(",");
        json.append("\"phosphat\":" + consumptionArray[1]);
        json.append(",");
        json.append("\"kalium\":" + consumptionArray[2]);
        json.append(",");
        json.append("\"eisen\":" + consumptionArray[3]);
        json.append(",");
        json.append("\"npkMenge\":" + consumptionArray[4]);
        json.append(",");
        json.append("\"eisenMenge\":" + consumptionArray[5]);
        json.append("}");
        return json.toString();
    };

    public void initialize(){
        npk_power = new Fertilizer("NPK Power",0.62, 0.035, 0.309, 0, 0.028);
        eisen_power = new Fertilizer("Eisen Power",0, 0, 0.309, 0.038, 0.149);
    };


}
