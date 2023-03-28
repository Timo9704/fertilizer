package de.aquaristik.kosmos.fertilizerService.model;

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

    public DoseMixer(double nitrateConsumption, double phosphateConsumption, double potassiumConsumption, double ironConsumption){
        this.nitrateConsumption = nitrateConsumption;
        this.phosphateConsumption = phosphateConsumption;
        this.potassiumConsumption = potassiumConsumption;
        this.ironConsumption = ironConsumption;
        calculateMinMax();
    }

    private void calculateMinMax(){
        double buffer = 0.025;
        nitrateMin = nitrateConsumption - (nitrateConsumption * buffer);
        nitrateMax = nitrateConsumption + (nitrateConsumption * buffer);

        phosphateMin = phosphateConsumption - (phosphateConsumption * buffer);
        phosphateMax = phosphateConsumption + (phosphateConsumption * buffer);

        potassiumMin = potassiumConsumption - (potassiumConsumption * buffer);
        potassiumMax = potassiumConsumption + (potassiumConsumption * buffer);

        ironMin = ironConsumption - (ironConsumption * buffer);
        ironMax = ironConsumption + (ironConsumption * buffer);
    }
}
