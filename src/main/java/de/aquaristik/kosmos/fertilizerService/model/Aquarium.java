package de.aquaristik.kosmos.fertilizerService.model;

public class Aquarium {
    private int liter = 0;

    private double nitrateIs1 = 0;
    private double nitrateIs2 = 0;

    private double phosphateIs1 = 0;
    private double phosphateIs2 = 0;

    private double potassiumIs1 = 0;
    private double potassiumIs2 = 0;

    private double ironIs1 = 0;
    private double ironIs2 = 0;

    int[] fertilizerInUse = null;

    public int getLiter() {
        return liter;
    }

    public double getNitrateIs1() {
        return nitrateIs1;
    }

    public double getNitrateIs2() {
        return nitrateIs2;
    }

    public double getPhosphateIs1() {
        return phosphateIs1;
    }

    public double getPhosphateIs2() {
        return phosphateIs2;
    }

    public double getPotassiumIs1() {
        return potassiumIs1;
    }

    public double getPotassiumIs2() {
        return potassiumIs2;
    }

    public double getIronIs1() {
        return ironIs1;
    }

    public double getIronIs2() {
        return ironIs2;
    }

    public int[] getFertilizerInUse() {
        return fertilizerInUse;
    }

}
