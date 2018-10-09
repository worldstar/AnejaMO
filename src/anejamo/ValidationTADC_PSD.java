/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo;

/**
 *
 * @author Shih-HSin Chen
 * PSD + LE
 * 20150208
 */
public class ValidationTADC_PSD {
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
            double tempSum = 0;
   
            for(int j = i; j <= n - 1; j ++){
              tempSum += j;
            }
            weights[i-1] = (b*tempSum+(i-1))*Math.pow(i, alpha);

            obj += weights[i-1]*processingTime[sequence[i-1]-1];
            //weights[i-1] = weights[i-1] * Math.pow(i, alpha);
            System.out.println(i+": weights[i-1]: "+weights[i-1]+", b*tempSum+(i-1): "+b*tempSum+(i-1)+", w*p: "+weights[i-1]*processingTime[sequence[i-1]-1]);
        }
         System.out.println(obj);
        return obj;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ValidationTADC_PSD v1 = new ValidationTADC_PSD();
        int sequence[] = new int[]{1, 2, 3, 4, 5, 6, 7};//2, 3, 6, 9, 21
        int processingTime[] = new int[]{2, 3, 6, 9, 21, 65, 82};//2, 3, 6, 9, 21
        double alpha = -0.322;
        double b = 0.1;
        v1.startCalc(sequence, processingTime, alpha, b);
    }
}
