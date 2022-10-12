package de.aquaristik.kosmos.fertilizerService.nutrient;

public class NutrientController {

    private Nutrient nutrient;

    public NutrientController(Nutrient nutrient){
        this.nutrient = nutrient;
    }

    public boolean isBetween(double compareValue){
        if(compareValue >= nutrient.getMin() && compareValue <= nutrient.getMax()){
            return true;
        }else{
            return false;
        }
    }

    public double getDifferenceToOptimal(double compareValue){
        if(isBetween(compareValue)){
            return 0;
        }else{
            double standardValue = ((nutrient.getMax() - nutrient.getMin())/2) + nutrient.getMin();
            double difference = standardValue - compareValue;

            return difference;
        }
    }
}
