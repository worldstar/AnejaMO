/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.weight;

/**
 *
 * @author chuan
 */
public interface weightCalcI {
    public void setData(double alpha, double b, int n);
    public void setData(double alpha, int n);
    public void startCalc();
    public void startCalc(int processingTime[]);
    public double[] getWeights();
}
