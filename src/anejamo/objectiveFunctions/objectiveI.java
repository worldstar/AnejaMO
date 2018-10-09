/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.objectiveFunctions;

/**
 *
 * @author chuan
 */
public interface objectiveI {
    public double startCalc(int sequence[], int processingTime[], double alpha, double b);
    public double startCalc(int sequence[], double processingTime[], double weights[]);
    public double startCalc(int sequence[], int processingTime[], double alpha);    
}
