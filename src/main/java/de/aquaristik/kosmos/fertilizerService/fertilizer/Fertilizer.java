package de.aquaristik.kosmos.fertilizerService.fertilizer;

public class Fertilizer {

    private String name;

    private double nitrat;

    private double phosphat;

    private double kalium;

    private double eisen;

    private double magnesium;

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

    public void setEisen(double eisen) {
        this.eisen = eisen;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public Fertilizer(String name, double nitrat, double phosphat, double kalium, double eisen, double magnesium) {
        this.name = name;
        this.nitrat = nitrat;
        this.phosphat = phosphat;
        this.kalium = kalium;
        this.eisen = eisen;
        this.magnesium = magnesium;
    }

}
