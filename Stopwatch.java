/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment04.GUI;

/**
 * represents a stopwatch to keep track of elapsed time
 * @author ben
 */
public class Stopwatch {
    
    private static long startTime = 0;
    private static  long elapsedTime = 0;
    private static boolean paused = true;
    
    /**
     * Constructor for Stopwatch 
     * **does not start the time*****
     */
    public Stopwatch(){
        startTime = 0;
        elapsedTime = 0;
        paused = true;  //cant change time while paused
        
    }
    /**
     * Unpauses timer and sets the starttime to the current time
     */
    public void start(){
        elapsedTime = 0;
        startTime = System.nanoTime();
        paused = false;
    }
    
    /**
     * if the timer is not paused it calculates the elapsed time and returns it
     * if it is paused, it just returns the elapsed time
     * @return elapsedTime - double version of the elapsed time
     */
    public  double getTime(){
        //if(!paused){
            //elapsedTime+= System.nanoTime()-startTime;
        //}
        return (double)elapsedTime/1000000;
    }
    
    public void setTime(){
        if(!paused){
            elapsedTime+= System.nanoTime()-startTime;
        }
    }
    
    /**
     * calculates the current elapsed time and sets pause to true
     */
    public  void pause(){
        if(!paused){
            paused = true;  //this makes it so the time cant be changed
            elapsedTime+= System.nanoTime()-startTime;
        }
    }
    
    /**
     * if the timer is paused, sets the start time to the current time ands
     * sets paused to false
     * does nothing if timmer is not paused
     */
    public void resume(){
        if(paused){
            startTime = System.nanoTime();
            paused = false;
        }
    }

    
}
