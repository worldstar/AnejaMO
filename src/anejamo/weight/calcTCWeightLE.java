/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.weight;

/**
 *
 * @author chuan
 */
public class calcTCWeightLE implements weightCalcI{

    double alpha = 0;
    double weights[];
    int n = 0;//the number of jobs

    public calcTCWeightLE(){

    }

    public void setData(double alpha, int n){
        this.alpha = alpha;
       this.n = n;
       weights = new double[n];
    }

    public void setData(double alpha, double b, int n){
        System.out.println("This method setData(double alpha, double b, int n) is not supported in calcTCWeightLE.class.");
        System.exit(0);
    }

    public void startCalc(){
        for(int i = 1 ; i <= n ; i ++){
            weights[i-1] = (n-i+1)*Math.pow(i, alpha);
        }
    }

    public void startCalc(int processingTime[]){
        for(int i = 1 ; i <= n ; i ++){
            weights[i-1] = processingTime[i-1];
        }
    }

    public double[] getWeights(){
        return weights;
    }
}
