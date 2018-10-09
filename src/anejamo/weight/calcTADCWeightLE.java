/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.weight;

/**
 *
 * @author chuan
 */
public class calcTADCWeightLE implements weightCalcI{
    double alpha = 0;
    double weights[];
    int n = 0;//the number of jobs

    public calcTADCWeightLE(double alpha, int n){
        this.alpha = alpha;
       this.n = n;
       weights = new double[n];
    }

    public calcTADCWeightLE(){

    }

    public void setData(double alpha, int n){
        this.alpha = alpha;
       this.n = n;
       weights = new double[n];
    }

    public void setData(double alpha, double b, int n){
        System.exit(0);
    }

    public void startCalc(){
        for(int r = 1 ; r <= n ; r ++){
            weights[r-1] = (r-1)*(n-r+1)* Math.pow(r, alpha);
        }
    }

    public void startCalc(int processingTime[]){
        System.exit(0);
    }

    public double[] getWeights(){
        return weights;
    }
}
