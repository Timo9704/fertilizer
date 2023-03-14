package de.aquaristik.kosmos.fertilizerService.aquarium;

public class AquariumController {
    private Aquarium aquarium;

    public AquariumController(Aquarium aquarium){
        this.aquarium = aquarium;
    }

    public double[] consumption(){

        double[] consumption = new double[4];

        consumption[0] = Math.round(((aquarium.getNitrateIs1() - aquarium.getNitrateIs2())/7) * 100.0) / 100.0;
        consumption[1] = Math.round(((aquarium.getPhosphateIs1() - aquarium.getPhosphateIs2())/7) * 100.0) / 100.0;
        consumption[2] = Math.round(((aquarium.getPotassiumIs1() - aquarium.getPotassiumIs2())/7) * 100.0) / 100.0;
        consumption[3] = Math.round(((aquarium.getIronIs1() - aquarium.getIronIs2())/7) * 100.0) / 100.0;

        return consumption;
    }
}
