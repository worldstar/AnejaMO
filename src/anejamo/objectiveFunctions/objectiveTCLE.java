/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.objectiveFunctions;

/**
 *
 * @author chuan
 */
public class objectiveTCLE implements objectiveI{
    int sequence[];
    int processingTime[];
    double alpha = 0;
    double weights[];
    int n = 0;//the number of jobs

    public double startCalc(int sequence[], int processingTime[], double alpha){
        n = sequence.length;
        this.alpha = alpha;
        double obj = 0;

        for(int i = 0 ; i < n ; i ++){
            int r = i + 1;
            obj += (n-r+1)*Math.pow(r, alpha)*processingTime[sequence[i]-1];
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
        System.out.print("Exit startCalc(int sequence[], int processingTime[], double alpha, double b)");
        System.exit(0);

        return 0;
    }
}
