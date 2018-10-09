/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anejamo.util;

/**
 *
 * @author chuan
 */
public class timeClock {
  long start, end;

  public void start(){
    start = System.currentTimeMillis();
  }

  public void end(){
    end = System.currentTimeMillis();
  }

  public long getExecutionTime(){
    return (end - start);
  }

  public static void main(String[] args) {
  }
}
