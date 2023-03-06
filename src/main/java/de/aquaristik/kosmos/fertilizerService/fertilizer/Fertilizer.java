package de.aquaristik.kosmos.fertilizerService.fertilizer;

public class Fertilizer {

    private String name;

    private double nitrat;

    private double phosphat;

    private double kalium;

    private double eisen;

    private double magnesium;

    private double dosage = 0.1;

    public String getName() {
        return name;
    }

    public double getNitrat() {
        return nitrat;
    }

    public double getPhosphat() {
        return phosphat;
    }

    public double getKalium() {
        return kalium;
    }

    public double getEisen() {
        return eisen;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNitrat(double nitrat) {
        this.nitrat = nitrat;
    }

    public void setPhosphat(double phosphat) {
        this.phosphat = phosphat;
    }

    public void setKalium(double kalium) {
        this.kalium = kalium;
    }

    public void setEisen(double eisen) { this.eisen = eisen; }

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
        this.nitrat = nitrat;
        this.phosphat = phosphat;
        this.kalium = kalium;
        this.eisen = eisen;
        this.magnesium = magnesium;
    }

    public void calculateForAquarium(int liter) {
        nitrat = Math.round(((nitrat * 100) / liter) * 100.0) / 100.0;
        phosphat = Math.round(((phosphat * 100) / liter) * 100.0) / 100.0;
        kalium = Math.round(((kalium * 100) / liter) * 100.0) / 100.0;
        eisen = Math.round(((eisen * 100) / liter) * 100.0) / 100.0;
    }
}
