/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo;

/**
 *
 * @author Shih-HSin Chen
 * 20121209
 */
public class ValidationTADC {
    int sequence[];
    int processingTime[];
    double alpha = 0;
    double b = 0;
    double weights[];
    int n = 0;//the number of jobs
    
    public double startCalc(int sequence[], int processingTime[], double alpha, double b){
        n = sequence.length;
        this.alpha = alpha;
        this.b = b;
        double obj = 0;
        weights = new double[n];

        for(int i = 1 ; i <= n ; i ++){
            weights[i-1] = (i-1)*(n-i+1);

            for(int j = i + 1 ; j <= n ; j ++){
                weights[i-1] += b * (j - 1)*(n - j + 1);
            }

            obj += weights[i-1]*processingTime[sequence[i-1]-1];
            //weights[i-1] = weights[i-1] * Math.pow(i, alpha);
            System.out.println(i+": "+weights[i-1]+" "+weights[i-1]*processingTime[sequence[i-1]-1]);
        }
         System.out.println(obj);
        return obj;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ValidationTADC v1 = new ValidationTADC();
        int sequence[] = new int[]{3, 1, 2, 4, 5, 6, 7};
        int processingTime[] = new int[]{2, 3, 6, 9, 21, 65, 82};
        double alpha = 0;
        double b = 0.5;
        v1.startCalc(sequence, processingTime, alpha, b);
    }
}
