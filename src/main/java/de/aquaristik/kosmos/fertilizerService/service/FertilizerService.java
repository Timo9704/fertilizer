package de.aquaristik.kosmos.fertilizerService.service;

import de.aquaristik.kosmos.fertilizerService.model.Fertilizer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("fertilizerService")
public class FertilizerService {

    private List<Fertilizer> fertilizers = Arrays.asList(
            new Fertilizer(0,"NPK Power", 0.62, 0.035, 0.309, 0, 0.028),
            new Fertilizer(1,"Eisen Power", 0, 0, 0.309, 0.038, 0.149),
            new Fertilizer(2,"N Power", 1.06, 0, 0.44, 0, 0),
            new Fertilizer(3,"P Power", 0, 0.07, 0.083, 0, 0),
            new Fertilizer(4,"K Power", 0, 0, 0.29, 0, 0)
    );

    public List<Fertilizer> getAllFertilizers(){
        return fertilizers;
    }

    public Fertilizer getFertilizer(int id){
        return fertilizers.stream().filter(f -> f.getId() == id).findFirst().get();
    }
}
