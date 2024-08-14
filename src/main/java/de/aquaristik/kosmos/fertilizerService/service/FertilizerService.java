package de.aquaristik.kosmos.fertilizerService.service;

import de.aquaristik.kosmos.fertilizerService.model.Fertilizer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("fertilizerService")
public class FertilizerService {

    private List<Fertilizer> fertilizers;

    //Rechne von z.B. 2m auf 50L erst auf 100L und dann von 2ml auf 100L auf 1ml je 100L

    public void init(){
        fertilizers = Arrays.asList(
                new Fertilizer(0,"Greenscaping NPK Power", 0.62, 0.035, 0.309, 0, 0.028),
                new Fertilizer(1,"Greenscaping Eisen Power", 0, 0, 0.309, 0.038, 0.149),
                new Fertilizer(2,"Greenscaping N Power", 1.06, 0, 0.44, 0, 0),
                new Fertilizer(3,"Greenscaping P Power", 0, 0.07, 0.083, 0, 0),
                new Fertilizer(4,"Greenscaping K Power", 0, 0, 0.29, 0, 0),
                new Fertilizer(5,"AquaRebell Makro Basic NPK", 0.25, 0.025, 0.325, 0, 0.025),
                new Fertilizer(6,"AquaRebell Mikro Basic Eisen", 0, 0, 0.006, 0.01, 0),
                new Fertilizer(7,"AquaRebell Mikro Spezial Eisen", 0, 0, 0.01, 0.12, 0),
                new Fertilizer(8,"AquaRebell Makro Spezial N", 0.5, 0, 0.1, 0, 0.035),
                new Fertilizer(9,"AquaRebell Makro Basic Nitrat", 0.5, 0, 0.1, 0, 0),
                new Fertilizer(10,"AquaRebell Makro Basic Phosphat", 0, 0.05, 0.02, 0, 0),
                new Fertilizer(11,"AquaRebell Makro Basic Kalium", 0, 0, 0.025, 0, 0),
                new Fertilizer(12,"AquaRebell Makro Basic Estimative Index", 0.368, 0.068, 0.28, 0, 0.01),
                new Fertilizer(13,"AquaRebell Mikro Spezial Flowgrow", 0, 0, 0.006, 0.041, 0.01),
                new Fertilizer(14,"AquaRebell Advanced GH Boost N", 0.75, 0, 0, 0, 0.135),
                new Fertilizer(15,"Masterline 1", 0, 0, 0, 0.027, 0),
                new Fertilizer(16,"Masterline 2", 0.36, 0.06, 0.036, 0, 0),
                new Fertilizer(17,"Masterline Nitrate", 0.3, 0, 0.189, 0, 0),
                new Fertilizer(18,"Masterline Phosphate", 0, 0.15, 0.06, 0, 0),
                new Fertilizer(19,"Masterline Potassium", 0, 0, 0.3, 0, 0),
                new Fertilizer(20,"Masterline Iron", 0, 0, 0, 0.03, 0),
                new Fertilizer(21,"EasyLife Ferro", 0, 0, 0, 0.1, 0),
                new Fertilizer(22,"EasyLife Nitro", 1, 0, 0.65, 0, 0),
                new Fertilizer(23,"EasyLife Kalium", 0, 0, 0.4, 0, 0),
                new Fertilizer(24,"Microbe-Lift Plants Fe", 0, 0, 0, 0.02, 0),
                new Fertilizer(25,"Microbe-Lift Plants N", 1, 0, 0, 0, 0),
                new Fertilizer(26,"Microbe-Lift Plants P", 0, 0.01, 0, 0, 0),
                new Fertilizer(27,"Microbe-Lift Plants K", 0, 0, 1, 0, 0),
                new Fertilizer(28,"Microbe-Lift Plants NPK", 0.086, 0.02, 0.268, 0, 0.02)
        );
    }

    public List<Fertilizer> getAllFertilizers(){
        return fertilizers;
    }

    public Fertilizer getFertilizer(int id){
        return fertilizers.stream().filter(f -> f.getId() == id).findFirst().get();
    }


    public double[] nutrientsPerMl(Fertilizer fertilizer){
        double[] nutrientArray = new double[4];
        nutrientArray[0] = fertilizer.getNitrate() * fertilizer.getDosage();
        nutrientArray[1] = fertilizer.getPhosphate() * fertilizer.getDosage();
        nutrientArray[2] = fertilizer.getPotassium() * fertilizer.getDosage();
        nutrientArray[3] = fertilizer.getIron() * fertilizer.getDosage();

        return nutrientArray;
    }
}
