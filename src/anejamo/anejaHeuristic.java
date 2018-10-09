/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo;
import anejamo.objectiveFunctions.*;
import anejamo.sort.selectionSort;
import anejamo.weight.*;
import anejamo.util.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Shih-HSin Chen
 */
public class anejaHeuristic {
        weightCalcI calcTCWeight1;
        weightCalcI calcTADCWeight1;
        objectiveI objectiveTC1 = new objectiveTC();//objectiveTC, objectiveTCLE
        objectiveI objectiveTADC1 = new objectiveTADCLE_PSD();//objectiveTADC, objectiveTADCLE
        weightMatchSequence weightMatchSequence1 = new weightMatchSequence();
        List<solution> majorList = new ArrayList<solution>();
        List<solution> tempList = new ArrayList<solution>();
        maintainSolution maintainSolution1 = new maintainSolution();

        //int sequence[] = new int[]{1, 2, 3, 4};
        int processingTime[] = new int[]{2, 3, 6, 9, 21, 65, 82};
        double obj[];
        double alpha = -0.322;
        double b = 0.1;
        int n = 7;
        int objectives = 2;
        printClass printClass1 = new printClass();
        timeClock timeClock1 = new timeClock();
        
        //Solve the PSD problem together. 20150216
        boolean isPSD = true;


    public void setData(int processingTime[], double alpha, double b, boolean isPSD){
        this.processingTime = processingTime;
        this.alpha = alpha;
        this.b = b;
        n = processingTime.length;
        this.isPSD = isPSD;
    }
    
    public void startAneja(){        
        timeClock1.start();        
        init();
        solution solution1 = generateNewSolution(majorList.get(0).getObj(), majorList.get(1).getObj());
        maintainSolution1.addToTempList(majorList, tempList, solution1);

        for(int i = 0 ; i < n ; i ++){
            //System.out.println("Iteration i: "+i);
            //System.out.println("i: "+i+", "+tempList.size());
            int currenttempListSize = tempList.size();

            processEachPair();//Between the items in majorList and tempList
            maintainSolution1.mergeList(majorList, tempList, currenttempListSize);

            if(tempList.size() == 0){//No more possible solutions
                break;
            }
        }        
        timeClock1.end();
        maintainSolution1.mergeList(majorList);        
        printMajorListAndObjectives();
    }
    
    public void init(){
        obj = new double[objectives];

        //To get the first solution according to TC.
        calcTCWeight1 = new calcTCWeight();//calcTCWeight, calcTCWeightLE
        //LE weight
        //calcTCWeight1.setData(alpha, n);
        calcTCWeight1.setData(alpha, b, n);
        calcTCWeight1.startCalc(processingTime);
        
        if(isPSD == true){//LE+ PSD 20150216
            objectiveTADC1 = new objectiveTADCLE_PSD();//objectiveTADC, objectiveTADCLE
        }
        
        weightMatchSequence1.setData(calcTCWeight1.getWeights());
        weightMatchSequence1.getMatchSequenceSPT(processingTime);
        int sequence1[] = weightMatchSequence1.getMatchSequenceSPT(processingTime);
        obj[0] = objectiveTC1.startCalc(sequence1, processingTime, alpha, b);//sequence1, processingTime, alpha, b //sequence1, processingTime, alpha
        obj[1] = objectiveTADC1.startCalc(sequence1, processingTime, alpha, b);        

        System.out.println("b: "+b);;
        solution solution1 = new solution();
        solution1.setData(sequence1, obj);
        printClass1.printMatrix("calcTCWeight1.getWeights()", calcTCWeight1.getWeights());
        printClass1.printMatrix("sequence1", sequence1);
        printClass1.printMatrix("obj1", obj);
        
        //To get the 2nd solution according to TADC.
        calcTADCWeight1 = new calcTADCWeightLE_PSD();//calcTADCWeightLE calcTADCWeightLE_PSD
        //calcTADCWeight1.setData(alpha, n);
        calcTADCWeight1.setData(alpha, b, n);
        calcTADCWeight1.startCalc();
        weightMatchSequence1.setData(calcTADCWeight1.getWeights());
        int sequence2[] = weightMatchSequence1.getMatchSequence(true);
        
        double obj2[] = new double[objectives];
        obj2[0] = objectiveTC1.startCalc(sequence2, processingTime, alpha, b);
        obj2[1] = objectiveTADC1.startCalc(sequence2, processingTime, alpha, b);
        solution solution2 = new solution();
        solution2.setData(sequence2, obj2);
        printClass1.printMatrix("calcTADCWeight1.getWeights()", calcTADCWeight1.getWeights());
        printClass1.printMatrix("sequence2", sequence2);
        printClass1.printMatrix("obj2", obj2);
                
        majorList.add(solution1);
        majorList.add(solution2);
        
    }

    //index1 and index2 are the position inside the List (majorList).
    public double[] updatedObjectiveWeightVector(double solution1Obj[], double solution2Obj[]){
        double weightVector[] = new double[2];
        //For the first objective
        weightVector[0] = Math.abs(solution2Obj[1] - solution1Obj[1]);
        //For the 2nd objective
        weightVector[1] = Math.abs(solution2Obj[0] - solution1Obj[0]);

        return weightVector;
    }

    public double[] updatedPositionWeight(double a[]){
        double weightVector[] = new double[n];
        for(int i = 0 ; i < n ; i ++){
            int r = i + 1;
            weightVector[i] = a[0]*(n - r + 1)*(1 + b * (n - r)/2 )*Math.pow(r, alpha);

            double weightTADC = (r - 1)*(n - r + 1);
            for(int j = r + 1 ; j <= n ; j ++){
                weightTADC += b * (j - 1)*(n - j + 1);
            }
            weightTADC *= Math.pow(r, alpha);
            weightVector[i] += weightTADC * a[1];
        }
        return weightVector;
    }

    public double[] updatedPositionWeightLE(double a[]){
        double weightVector[] = new double[n];
        for(int i = 0 ; i < n ; i ++){
            int r = i + 1;
            weightVector[i] = a[0]*(n - r + 1)*Math.pow(r, alpha);

            double weightTADC = (r - 1)*(n - r + 1);
            weightTADC *= Math.pow(r, alpha);
            weightVector[i] += weightTADC * a[1];
        }
        return weightVector;
    }  

    public solution generateNewSolution(double solution1Obj[], double solution2Obj[]){
        double a[] = updatedObjectiveWeightVector(solution1Obj, solution2Obj);
        double weight[] = updatedPositionWeight(a);//updatedPositionWeight, updatedPositionWeightLE
        //printClass1.printMatrix("updatedPositionWeight", weight);
        
        //To get the first solution according to the weight.
        weightMatchSequence1.setData(weight);
        int sequence1[] = weightMatchSequence1.getMatchSequence(true);
        obj[0] = objectiveTC1.startCalc(sequence1, processingTime, alpha, b);
        obj[1] = objectiveTADC1.startCalc(sequence1, processingTime, alpha, b);
        //printClass1.printMatrix("sequence1", sequence1);
        //printClass1.printMatrix("obj1", obj);

        solution solution1 = new solution();
        solution1.setData(sequence1, obj);
        //printClass1.printMatrix("generateNewSolution sequence1 ", sequence1);
        return solution1;
    }

    public int processEachPair(){
        int currenttempListSize = tempList.size();
        //System.out.println("currenttempListSize: "+currenttempListSize);
        for(int i = 0 ; i < majorList.size() ; i ++){
            for(int j = 0 ; j < currenttempListSize ; j ++){
                solution solution1 = generateNewSolution(majorList.get(i).getObj(), tempList.get(j).getObj());
                maintainSolution1.addToTempList(majorList, tempList, solution1);
            }
        }

        return currenttempListSize;
    }

    public void printMajorList(){
        for(int i = 0 ; i < majorList.size() ; i ++){
            printClass1.printMatrix("majorList: "+i, majorList.get(i).getSequence());
        }
    }

    public void printMajorListAndObjectives(){        
        for(int i = 0 ; i < majorList.size() ; i ++){
            for(int j = 0 ; j < n ; j ++){
                System.out.print(majorList.get(i).getSequence()[j]+" ");
            }

            System.out.println(","+majorList.get(i).getObj()[0]+","+majorList.get(i).getObj()[1]);
        }
    }

    public void printMajorListAndObjectives(String filename){
        String results = "";
        for(int i = 0 ; i < majorList.size() ; i ++){
            for(int j = 0 ; j < n ; j ++){                
                results += majorList.get(i).getSequence()[j]+" ";
            }
            results += "\t"+majorList.get(i).getObj()[0]+"\t"+majorList.get(i).getObj()[1]+"\n";
        }
        writeFile(filename, results);
    }

    public void printCPUTime(String filename, String instance){
        String results = instance+"\t"+majorList.size()+"\t"+timeClock1.getExecutionTime()/1000.0+"\n";
        writeFile(filename, results);
    }

    public List<solution> getMajorList(){
        return majorList;
    }

  /**
   * Write the data into text file.
   */
  public void writeFile(String fileName, String _result){
      System.out.print(_result);
    fileWrite1 writeLotteryResult = new fileWrite1();
    writeLotteryResult.writeToFile(_result,"LEPSDresults2015/"+fileName+".txt");
    Thread thread1 = new Thread(writeLotteryResult);
    thread1.run();
  }
}
