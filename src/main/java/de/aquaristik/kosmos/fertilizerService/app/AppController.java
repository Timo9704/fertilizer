package de.aquaristik.kosmos.fertilizerService.app;

import com.google.gson.Gson;
import de.aquaristik.kosmos.fertilizerService.aquarium.Aquarium;
import de.aquaristik.kosmos.fertilizerService.aquarium.AquariumController;
import de.aquaristik.kosmos.fertilizerService.fertilizer.Fertilizer;
import de.aquaristik.kosmos.fertilizerService.fertilizer.FertilizerController;
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

        Fertilizer npk = new Fertilizer(fertilizerRepo.findByName("NPK Power"));
        Fertilizer eisen = new Fertilizer(fertilizerRepo.findByName("Eisen Power"));

        FertilizerController npkController = new FertilizerController(npk);
        FertilizerController eisenController = new FertilizerController(eisen);

        npkController.calculateForAquarium(aquarium.getLiter());
        eisenController.calculateForAquarium(aquarium.getLiter());

        double npkMl = npkController.calculateNpkDose(consumptionArray);
        double feMl = eisenController.calculateFeDose(consumptionArray);


        return ResponseEntity.ok(sendHtml(consumptionArray, feMl, npkMl));
    }

    public String sendHtml(double[] consumptionArray, double feMl, double npkMl){

        String html=
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<style>\n" +
                        "table, th, td {\n" +
                        "  border:1px solid black;\n" +
                        "}\n" +
                        "</style>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h2>Tagesverbrauch</h2>\n" +
                        "\n" +
                        "<table style=\"width:25%\">\n" +
                        "  <tr>\n" +
                        "    <th>Nährstoff</th>\n" +
                        "    <th>Tagesverbrauch in mg/l</th>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Nitrat</td>\n" +
                        "    <td>" + consumptionArray[0] + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Phosphat</td>\n" +
                        "    <td>" + consumptionArray[1] + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Kalium</td>\n" +
                        "    <td>" + consumptionArray[2] + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Eisen</td>\n" +
                        "    <td>" + consumptionArray[3] + "</td>\n" +
                        "  </tr>\n" +
                        "</table>\n" +
                        "<h2>Empfehlung für die Zugabe</h2>\n" +
                        "\n" +
                        "<table style=\"width:25%\">\n" +
                        "  <tr>\n" +
                        "    <th>Dünger</th>\n" +
                        "    <th>Zugabemenge in ml pro Tag</th>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Eisen Power</td>\n" +
                        "    <td>" + feMl + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>NPK Power</td>\n" +
                        "    <td>" + npkMl + "</td>\n" +
                        "  </tr>\n" +
                        "</table>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>";

        return html;
    };
}
