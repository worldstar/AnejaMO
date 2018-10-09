/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo;

import anejamo.data.singleMachine;

/**
 *
 * @author chuan
 */
public class Main {

    public void simpleTest(){
            String fileName = "bagchi";
            anejaHeuristic anejaHeuristic1 = new anejaHeuristic();
            anejaHeuristic1.startAneja();
            anejaHeuristic1.printMajorListAndObjectives(fileName);
            anejaHeuristic1.printCPUTime("AnejaLE", fileName);            
    }

    public void extensiveExperiments(){
       int jobSets[] = new int[]{20, 30, 40, 50};//20, 30, 40, 50, 60, 90, 100, 200//20, 40, 60, 80
       double alpha = -0.322;
       double b = 0.2;
       //Solve the PSD problem together. 20150216
       boolean isPSD = false;//LE with PSD
       int counter = 0;
       
      //Sourd Instance
      for(int m = 0 ; m < jobSets.length ; m ++ ){//jobSets.length
        for(int k = 0 ; k < 15 ; k ++ ){
          if(jobSets[m] <= 50 ||  (jobSets[m] > 50 && k < 9)){
            System.out.println("Combinations: "+m);
            singleMachine readSingleMachineData1 = new singleMachine();

            int numberOfJobs = jobSets[m];
            String fileName = readSingleMachineData1.getFileName(numberOfJobs, k);
            //fileName = "bky"+numberOfJobs+"_1";
            System.out.println(fileName+"\t");
            readSingleMachineData1.setData("sks/"+fileName+".txt");
            readSingleMachineData1.getDataFromFile();
            int processingTime[] = readSingleMachineData1.getPtime();

            anejaHeuristic anejaHeuristic1 = new anejaHeuristic();
            anejaHeuristic1.setData(processingTime, alpha, b, isPSD);
            anejaHeuristic1.startAneja();
            anejaHeuristic1.printMajorListAndObjectives(fileName);
            anejaHeuristic1.printCPUTime("AnejaLE", fileName);
          }
        }
      }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        Main Main1 = new Main();
        Main1.simpleTest();
        //Main1.extensiveExperiments();
    }
}
