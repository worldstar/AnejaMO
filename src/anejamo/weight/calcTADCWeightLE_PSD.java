/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.weight;

/**
 *
 * @author chuan
 */
public class calcTADCWeightLE_PSD implements weightCalcI{
    double alpha = 0;
    double b = 0;
    double weights[];
    int n = 0;//the number of jobs

    public calcTADCWeightLE_PSD(double alpha, int n){
        this.alpha = alpha;
        this.n = n;
       weights = new double[n];
    }
    
    public calcTADCWeightLE_PSD(double alpha, double b, int n){
        this.alpha = alpha;
        this.b = b;
        this.n = n;        
        weights = new double[n];
    }    

    public calcTADCWeightLE_PSD(){

    }

    public void setData(double alpha, int n){

    }    

    public void setData(double alpha, double b, int n){
        this.alpha = alpha;
        this.b = b;
        this.n = n;
        weights = new double[n];
    }

    public void startCalc(){
        for(int i = 1 ; i <= n ; i ++){
            double tempSum = 0;
   
            for(int j = i; j <= n - 1; j ++){
              tempSum += j;
            }
            weights[i-1] = (b*tempSum+(i-1))*Math.pow(i, alpha);
        }
    }

    public void startCalc(int processingTime[]){
        System.exit(0);
    }

    public double[] getWeights(){
        return weights;
    }
}
