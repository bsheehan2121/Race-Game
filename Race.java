/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;

/**
 *
 * @author Elizabeth Chapolini
 */
public class Race {
    
    private long raceTime;
    private Car[] raceCars;
    private char[] racetrack;
    private char curPos;
    private Stopwatch raceTimer;
    
    public Race(){
        raceCars = new Car[2];
        racetrack = new char[4];
        racetrack[0] = 'A';
        racetrack[1] = 'B';
        racetrack[2] = 'C';
        racetrack[3] = 'D';
        raceTime = 0;
        raceTimer = new Stopwatch();
        curPos = 'A';
    }
    
    public void runRace(){
        
        raceTimer.startTime();
        
    }
    
    public boolean checkWinner(){
        for(int i = 0; i < raceCars.length; i++){
            if(raceCars[i].getEnd() == curPos)
                return true;
        }
        return false;
    }
    
}
