package de.aquaristik.kosmos.fertilizerService.service;

import de.aquaristik.kosmos.fertilizerService.model.DoseMixer;
import de.aquaristik.kosmos.fertilizerService.model.Fertilizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component("doseMixerService")
public class DoseMixerService {

    @Autowired
    FertilizerService fertilizerService;

    public boolean calculateDosage(List<Fertilizer> fertilizerList, DoseMixer doseMixer) {
        int numFertilizer = fertilizerList.size();

        //0.1-10ml = 100
        int steps = 100;

        Iterator<double[]> iter = createMlCombinations(numFertilizer, steps).iterator();

        while (iter.hasNext()) {
            double[] fertilizerMl = iter.next();

            for (int i = 0; i < fertilizerMl.length; i++) {
                fertilizerList.get(i).setDosage(fertilizerMl[i]);
            }

            double[][] nutrientsMatrix = calculateNutrientsMatrix(fertilizerList);
            boolean[] test = checkBuffer(nutrientsMatrix, doseMixer, fertilizerList.size());

            if (test[0] == true && test[1] == true && test[2] == true && test[3] == true) {
                return true;
            };
        }
        return false;
    }

    public double[][] calculateNutrientsMatrix(List<Fertilizer> fertilizerList) {
        int matrixColumns = fertilizerList.size();
        int counter = 0;
        double[][] nutrientsMatrix = new double[4][matrixColumns + 1];

        for (Fertilizer fertilizer : fertilizerList) {
            double[] nutrientArray = fertilizerService.nutrientsPerMl(fertilizer);
            // specific fertilizer column
            nutrientsMatrix[0][counter] = nutrientArray[0];
            nutrientsMatrix[1][counter] = nutrientArray[1];
            nutrientsMatrix[2][counter] = nutrientArray[2];
            nutrientsMatrix[3][counter] = nutrientArray[3];

            // sum up of all nutrients of all fertilizers
            nutrientsMatrix[0][matrixColumns] += nutrientArray[0];
            nutrientsMatrix[1][matrixColumns] += nutrientArray[1];
            nutrientsMatrix[2][matrixColumns] += nutrientArray[2];
            nutrientsMatrix[3][matrixColumns] += nutrientArray[3];

            counter++;
        }

        return nutrientsMatrix;
    }

    private boolean[] checkBuffer(double[][] nutrientLevels, DoseMixer doseMixer, int fertilizerListSize) {
        boolean[] isInBuffer = {true, true, true, true};

        for (int i = 0; i < nutrientLevels.length; i++) {
            double nutrientLevel = nutrientLevels[i][fertilizerListSize];
            double nutrientMin = 0.0;
            double nutrientMax = 0.0;
            switch (i) {
                case 0:
                    nutrientMin = doseMixer.getNitrateMin();
                    nutrientMax = doseMixer.getNitrateMax();
                    break;
                case 1:
                    nutrientMin = doseMixer.getPhosphateMin();
                    nutrientMax = doseMixer.getPhosphateMax();
                    break;
                case 2:
                    nutrientMin = doseMixer.getPotassiumMin();
                    nutrientMax = doseMixer.getPotassiumMax();
                    break;
                case 3:
                    nutrientMin = doseMixer.getIronMin();
                    nutrientMax = doseMixer.getIronMax();
                    break;
                default:
                    break;
            }
            if ((nutrientLevel < nutrientMin || nutrientLevel > nutrientMax) && nutrientMin != 0 && nutrientMax != 0) {
                isInBuffer[i] = false;
            }
        }
        return isInBuffer;
    }

    public static Iterable<double[]> createMlCombinations(int numFertilizer, int steps) {
        return () -> new Iterator<>() {
            private int index = 99;

            @Override
            public boolean hasNext() {
                return index < Math.pow(steps, numFertilizer);
            }

            @Override
            public double[] next() {
                double[] combination = new double[numFertilizer];
                for (int i = 0; i < numFertilizer; i++) {
                    int div = (int) Math.pow(steps, numFertilizer - 1 - i);
                    combination[i] = ((index / div) % steps + 1) * 0.1;
                }
                index++;
                return combination;
            }
        };
    }
}