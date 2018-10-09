/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.weight;

import anejamo.sort.selectionSort;
import anejamo.util.printClass;

/**
 *
 * @author chuan
 */
public class weightMatchSequence {
    double weights[];
    int index[];
    int resultSequence[];
    int n = 0;

    public void setData(double _weights[]){
        n = _weights.length;
        weights = new double[n];
        index = new int[n];
        resultSequence = new int[n];

        for(int i = 0 ; i < n ; i ++){
            weights[i] = _weights[i];
            index[i] = i + 1;
        }
    }

    public int[] getMatchSequence(boolean reverse){
        //System.out.println("getMatchSequence");
        printClass printClass1 = new printClass();
        //printClass1.printMatrix("weights", weights);
        //printClass1.printMatrix("index", index);
        //Sorting the weights data
        selectionSort selectionSort1 = new selectionSort();
        selectionSort1.setNomialData(index);
        selectionSort1.setData(weights);
        selectionSort1.selectionSort_withNomialDescending();
        int orderedIndex[] = selectionSort1.getNomialData();
        //printClass1.printMatrix("orderedIndex", orderedIndex);

        for(int i = 0 ; i < n ; i ++){
            resultSequence[orderedIndex[i]-1] = i + 1;
        }

        //printClass1.printMatrix("orderedIndex", orderedIndex);
        //printClass1.printMatrix("resultSequence", resultSequence);
        return resultSequence;
    }

    public int[] getMatchSequenceSPT(int processingTime[]){
        //System.out.println("getMatchSequence");
        printClass printClass1 = new printClass();
        //printClass1.printMatrix("weights", weights);
        //printClass1.printMatrix("index", index);
        //Sorting the weights data
        selectionSort selectionSort1 = new selectionSort();
        selectionSort1.setNomialData(index);
        selectionSort1.setData(processingTime);
        selectionSort1.selectionSort_int_withNomial();
        int orderedIndex[] = selectionSort1.getNomialData();
        //printClass1.printMatrix("orderedIndex", orderedIndex);

        for(int i = 0 ; i < n ; i ++){
            resultSequence[orderedIndex[i]-1] = i + 1;
        }
        return resultSequence;
    }
}
