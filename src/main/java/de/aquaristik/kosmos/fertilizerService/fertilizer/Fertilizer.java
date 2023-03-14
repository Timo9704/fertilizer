package de.aquaristik.kosmos.fertilizerService.fertilizer;

public class Fertilizer {

    private String name;

    private double nitrate;

    private double phosphate;

    private double potassium;

    private double iron;

    private double magnesium;

    private double dosage = 0.1;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setNitrate(double nitrate) {
        this.nitrate = nitrate;
    }

    public void setPhosphate(double phosphate) {
        this.phosphate = phosphate;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public void setIron(double iron) { this.iron = iron; }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public Fertilizer(String name, double nitrat, double phosphat, double kalium, double eisen, double magnesium) {
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

}
