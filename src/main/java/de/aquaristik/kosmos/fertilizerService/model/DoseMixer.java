package de.aquaristik.kosmos.fertilizerService.model;

import java.util.List;
public class DoseMixer {

    private double nitrateConsumption;

    private double nitrateMin;

    private double nitrateMax;

    private double phosphateConsumption;

    private double phosphateMin;

    private double phosphateMax;

    private double potassiumConsumption;

    private double potassiumMin;

    private double potassiumMax;

    private double ironConsumption;

    private double ironMin;

    private double ironMax;

    private List<Fertilizer> fertilizerList;

    public DoseMixer(double nitrateConsumption, double phosphateConsumption, double potassiumConsumption, double ironConsumption, List<Fertilizer> fertilizerList){
        this.nitrateConsumption = nitrateConsumption;
        this.phosphateConsumption = phosphateConsumption;
        this.potassiumConsumption = potassiumConsumption;
        this.ironConsumption = ironConsumption;
        this.fertilizerList = fertilizerList;
        calculateMinMax();
    }

    private void calculateMinMax(){
        double buffer = 0.1;
        nitrateMin = nitrateConsumption - (nitrateConsumption * buffer);
        nitrateMax = nitrateConsumption + (nitrateConsumption * buffer);

        phosphateMin = phosphateConsumption - (phosphateConsumption * buffer);
        phosphateMax = phosphateConsumption + (phosphateConsumption * buffer);

        potassiumMin = potassiumConsumption - (potassiumConsumption * buffer);
        potassiumMax = potassiumConsumption + (potassiumConsumption * buffer);

        ironMin = ironConsumption - (ironConsumption * buffer);
        ironMax = ironConsumption + (ironConsumption * buffer);
    }

    public double getNitrateMin() {
        return nitrateMin;
    }

    public double getNitrateMax() {
        return nitrateMax;
    }

    public double getPhosphateMin() {
        return phosphateMin;
    }

    public double getPhosphateMax() {
        return phosphateMax;
    }

    public double getPotassiumMin() {
        return potassiumMin;
    }

    public double getPotassiumMax() {
        return potassiumMax;
    }

    public double getIronMin() {
        return ironMin;
    }

    public double getIronMax() {
        return ironMax;
    }
}
