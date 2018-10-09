/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo;

/**
 *
 * @author Shih-HSin Chen
 */
public class solution {
    int sequence[];
    int n = 0;
    double obj[];

    public void setData(int n){
        sequence = new int[n];
    }

    public void setData(int seq[]){
        sequence = new int[n];
        for(int i = 0 ; i < n ; i ++){
            sequence[i] = seq[i];
        }
    }

    public void setData(int seq[], double _obj[]){
        n = seq.length;
        sequence = new int[n];
        obj = new double[_obj.length];
        for(int i = 0 ; i < n ; i ++){
            sequence[i] = seq[i];
        }

        for(int i = 0 ; i < _obj.length ; i ++){
            obj[i] = _obj[i];
        }
    }

    public void setData(double _obj[]){
        obj = new double[_obj.length];
        for(int i = 0 ; i < _obj.length ; i ++){
            obj[i] = _obj[i];
        }
    }

    public int[] getSequence(){
        return sequence;
    }

    public double[] getObj(){
        return obj;
    }
}
