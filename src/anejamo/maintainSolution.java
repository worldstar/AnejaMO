/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo;

import anejamo.util.printClass;
import java.util.List;

/**
 *
 * @author Shih-HSin Chen
 */
public class maintainSolution {
    printClass printClass1 = new printClass();

    public boolean addToTempList( List<solution> majorList, List<solution> tempList, solution solution1){
        //To check the solution1 if it is a new solution
        boolean redundant = false;        
        //System.out.println("addToTempList");
        //printClass1.printMatrix("solution1", solution1.getSequence());

        //Check with the majorList
        for(int i = 0 ; i < majorList.size() ; i ++){
            redundant = checkRedundant( majorList.get(i).getSequence(), solution1.getSequence());
            //printClass1.printMatrix("majorList"+i, majorList.get(i).getSequence());

            if(redundant == true){
                break;
            }
        }

        //Check for the tempList itself
        if(redundant == false){
            for(int i = 0 ; i < tempList.size() ; i ++){
                redundant = checkRedundant( tempList.get(i).getSequence(), solution1.getSequence());
                //printClass1.printMatrix("tempList"+i, tempList.get(i).getSequence());

                if(redundant == true){
                    break;
                }
            }
        }
        
        if(redundant == false){
            tempList.add(solution1);
        }

        return redundant;
    }

    private boolean checkRedundant(int originalArray[], int solutionArray1[]){
        boolean redundant = true;

        for(int i = 0 ; i < originalArray.length; i ++){
            if(originalArray[i] != solutionArray1[i]){
                redundant = false;
                break;
            }
        }
        return redundant;
    }

  /**
   * If obj[i] > obj[j], then return true.
   * @param _obj1 the first obj
   * @param _obj2 the second obj
   * @param index where to start
   * @return
   */
  private boolean getObjcomparison(double _obj1[], double _obj2[]){
    boolean better = false;
    for(int i = 0 ; i < _obj1.length - 1 ; i ++ ){
      if((_obj1[i] < _obj2[i] && _obj1[i+1] <= _obj2[i+1]) ||
         (_obj1[i] <= _obj2[i] && _obj1[i+1] < _obj2[i+1])){
        better = true;
      }
      else{
        return false;
      }
    }
    return better;
  }

    //How many items should be copied. Not all of them in the tempList!
    public void mergeList( List<solution> majorList, List<solution> tempList, int number){
        //System.out.println("majorList, tempList, number: "+majorList.size()+" "+tempList.size()+" "+number);
        //Copy the object in tempList to majorList.
        for(int i = 0 ; i < number ; i ++){
            //To get the first item in tempList.
            majorList.add(tempList.get(0));
            //Then clear the first item in tempList
            tempList.remove(0);
        }
        //System.out.println("majorList, tempList, number: "+majorList.size()+" "+tempList.size()+" "+number);
    }

    public void mergeList( List<solution> majorList){
        for(int i = 0 ; i < majorList.size() - 1 ; i ++){
            for(int j = i + 1; j < majorList.size() ; j ++){
                //To test the dominance of solution i and j
                boolean better = getObjcomparison(majorList.get(i).getObj(), majorList.get(j).getObj());

                if(better == true){
                    majorList.remove(j);                    
                    j --;
                }
            }
        }
        //System.out.println("majorList, tempList, number: "+majorList.size()+" "+tempList.size()+" "+number);
    }
}
