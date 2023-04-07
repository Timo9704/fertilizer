package de.aquaristik.kosmos.fertilizerService.model;

public class Fertilizer {

    private int id;

    private String name;

    private double nitrate;

    private double phosphate;

    private double potassium;

    private double iron;

    private double magnesium;

    private double dosage = 0.1;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getNitrate() {
        return nitrate;
    }

    public double getPhosphate() {
        return phosphate;
    }

    public double getPotassium() {
        return potassium;
    }

    public double getIron() {
        return iron;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public void setDosageModfiy(double dosage) {
        this.nitrate = nitrate * dosage;
        this.phosphate = phosphate * dosage;
        this.potassium = potassium * dosage;
        this.iron = iron * dosage;
        this.magnesium = magnesium * dosage;
        this.dosage = dosage;
    }

    public Fertilizer(int id, String name, double nitrat, double phosphat, double kalium, double eisen, double magnesium) {
        this.id = id;
        this.name = name;
        this.nitrate = nitrat;
        this.phosphate = phosphat;
        this.potassium = kalium;
        this.iron = eisen;
        this.magnesium = magnesium;
    }

    public void calculateForAquarium(int liter) {
        nitrate = Math.round(((nitrate * 100) / liter) * 100.0) / 100.0;
        phosphate = Math.round(((phosphate * 100) / liter) * 100.0) / 100.0;
        potassium = Math.round(((potassium * 100) / liter) * 100.0) / 100.0;
        iron = Math.round(((iron * 100) / liter) * 100.0) / 100.0;
    }

    public void calculateForAquariumPrecise(int liter) {
        nitrate = (nitrate * 100) / liter;
        phosphate = (phosphate * 100) / liter;
        potassium = (potassium * 100) / liter;
        iron = (iron * 100) / liter;
    }

}
