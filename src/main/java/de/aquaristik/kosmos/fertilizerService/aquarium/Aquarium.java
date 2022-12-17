package de.aquaristik.kosmos.fertilizerService.aquarium;

public class Aquarium {
    private int liter = 0;

    private double nitratIs1 = 0;
    private double nitratIs2 = 0;

    private double phosphatIs1 = 0;
    private double phosphatIs2 = 0;

    private double kaliumIs1 = 0;
    private double kaliumIs2 = 0;

    private double eisenIs1 = 0;
    private double eisenIs2 = 0;

    int[] fertilizerInUse = null;

    public int getLiter() {
        return liter;
    }

    public double getNitratIs1() {
        return nitratIs1;
    }

    public double getNitratIs2() {
        return nitratIs2;
    }

    public double getPhosphatIs1() {
        return phosphatIs1;
    }

    public double getPhosphatIs2() {
        return phosphatIs2;
    }

    public double getKaliumIs1() {
        return kaliumIs1;
    }

    public double getKaliumIs2() {
        return kaliumIs2;
    }

    public double getEisenIs1() {
        return eisenIs1;
    }

    public double getEisenIs2() {
        return eisenIs2;
    }

    public int[] getFertilizerInUse() {
        return fertilizerInUse;
    }

}
