/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.objectiveFunctions;

/**
 *
 * @author chuan
 */
public class objectiveTADCLE  implements objectiveI{
    int sequence[];
    int processingTime[];
    double alpha = 0;
    double weights[];
    int n = 0;//the number of jobs

    public double startCalc(int sequence[], int processingTime[], double alpha){
        n = sequence.length;
        this.alpha = alpha;
        double obj = 0;
        weights = new double[n];

        for(int i = 1 ; i <= n ; i ++){
            weights[i-1] = (i-1)*(n-i+1)*Math.pow(i, alpha);
            obj += weights[i-1]*processingTime[sequence[i-1]-1];
        }
        return obj;
    }

    public double startCalc(int sequence[], double processingTime[], double weights[]){
        n = sequence.length;
        double obj = 0;

        for(int i = 0 ; i < n ; i ++){
            obj += weights[i]*processingTime[sequence[i]-1];
        }
        return obj;
    }

    public double startCalc(int sequence[], int processingTime[], double alpha, double b){
        return 0;
    }
}
