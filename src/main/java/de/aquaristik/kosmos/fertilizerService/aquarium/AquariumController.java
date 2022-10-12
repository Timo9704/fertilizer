package de.aquaristik.kosmos.fertilizerService.aquarium;

public class AquariumController {
    private Aquarium aquarium;

    public AquariumController(Aquarium aquarium){
        this.aquarium = aquarium;
    }


    public double[] consumption(){

        double[] consumption = new double[4];

        consumption[0] = Math.round(((aquarium.getNitratIs1() - aquarium.getNitratIs2())/6) *100.0) / 100.0;
        consumption[1] = Math.round(((aquarium.getPhosphatIs1() - aquarium.getPhosphatIs2())/6) *100.0) / 100.0;
        consumption[2] = Math.round(((aquarium.getKaliumIs1() - aquarium.getKaliumIs2())/6) *100.0) / 100.0;
        consumption[3] = Math.round(((aquarium.getEisenIs1() - aquarium.getEisenIs2())/6) *100.0) / 100.0;

        return consumption;
    }

}
