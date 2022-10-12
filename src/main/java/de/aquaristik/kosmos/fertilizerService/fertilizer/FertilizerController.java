package de.aquaristik.kosmos.fertilizerService.fertilizer;

import java.text.DecimalFormat;
import java.util.Arrays;

public class FertilizerController {
    DecimalFormat df = new DecimalFormat("###.##");


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
        double ml = 0.5;
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

    public boolean npkIsInPuffer(double nValue, double pValue, double kValue, double[] optimal) {

        double nMin = optimal[0]*0.9;
        double nMax = optimal[0]*1.05;
        double pMin = optimal[1]*0.9;
        double pMax = optimal[1]*1.05;
        double kMin = optimal[2]*0.9;
        double kMax = optimal[2]*1.05;
        System.out.println(nValue);
        System.out.println(pValue);
        System.out.println(kValue);
        System.out.println(optimal[0]);


        int counter = 0;

        if(nValue > nMin && nValue < nMax){
            counter++;
        }
        if(pValue > pMin && pValue < pMax){
            counter++;
        }
        if(kValue > kMin && kValue < kMax){
            counter++;
        }

        System.out.println(counter);

        return counter >= 2 ? true : false;
    }




}
