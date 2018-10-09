/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.weight;

/**
 *
 * @author chuan
 */
public class calcTCWeight implements weightCalcI{

    double alpha = 0;
    double b = 0;
    double weights[];
    int n = 0;//the number of jobs

    public calcTCWeight(){
        
    }

    public calcTCWeight(double alpha, double b, int n){
        this.alpha = alpha;
        this.b = b;
       this.n = n;
       weights = new double[n];
    }

    public void setData(double alpha, double b, int n){
        this.alpha = alpha;
        this.b = b;
       this.n = n;
       weights = new double[n];
    }

    public void setData(double alpha, int n){
        System.exit(0);
    }   

    public void startCalc(){
        for(int i = 1 ; i <= n ; i ++){
            weights[i-1] = (n-i+1)*(1+b*(n-i)/2)*Math.pow(i, alpha);
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
