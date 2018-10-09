/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.weight;

/**
 *
 * @author chuan
 */
public class calcTADCWeight implements weightCalcI{
    double alpha = 0;
    double b = 0;
    double weights[];
    int n = 0;//the number of jobs

    public calcTADCWeight(){

    }
    public calcTADCWeight(double alpha, double b, int n){
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
        for(int r = 1 ; r <= n ; r ++){
            weights[r-1] = (r-1)*(n-r+1);

            double temp = 0;
            for(int j = r + 1 ; j <= n ; j ++){
                temp += (j - 1)*(n - j + 1);
            }
            weights[r-1] = temp * b;
            weights[r-1] = weights[r-1] * Math.pow(r, alpha);
        }
    }

    public void startCalc(int processingTime[]){
        System.exit(0);
    }

    public double[] getWeights(){
        return weights;
    }
}
