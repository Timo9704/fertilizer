package de.aquaristik.kosmos.fertilizerService.fertilizer;

import java.util.Arrays;

public class FertilizerController {

    private Fertilizer fertilizer;

    public FertilizerController(Fertilizer fertilizer){
        this.fertilizer = fertilizer;
    }

    public void calculateForAquarium(int liter){
        fertilizer.setNitrat((fertilizer.getNitrat()*100)/liter);
        fertilizer.setPhosphat((fertilizer.getPhosphat()*100)/liter);
        fertilizer.setKalium((fertilizer.getKalium()*100)/liter);
        fertilizer.setEisen((fertilizer.getEisen()*100)/liter);
    }

    public double calculateNpkDose(double[] consumption){
        double[] nutrientArray = new double[3];

        double nOptimal = consumption[0] / fertilizer.getNitrat();
        double pOptimal = consumption[1] / fertilizer.getPhosphat();
        double kOptimal = consumption[2] / fertilizer.getKalium();

        nutrientArray[0] = nOptimal;
        nutrientArray[1] = pOptimal;
        nutrientArray[2] = kOptimal;

        Arrays.sort(nutrientArray);

        return Math.round(nutrientArray[2] * 10.0) / 10.0;
    }

    public double calculateFeDose(double[] consumption){
        double feOptimal = consumption[3] / fertilizer.getEisen();

        return Math.round(feOptimal * 10.0) / 10.0;
    }
}
