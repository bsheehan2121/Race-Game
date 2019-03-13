/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Elizabeth Chapolini
 */
public class Race {
    
    private long raceTime;
    private DrawCar[] raceCars;
    private ArrayList<DrawCar> userCars;
    private char[] racetrack;
    private char curPos;
    private Stopwatch raceTimer;
    
    public Race(){
        raceCars = new DrawCar[4];
        for(int i=0;i<4;i++)
            raceCars[i] = new DrawCar();
        racetrack = new char[4];
        racetrack[0] = 'A';
        racetrack[1] = 'B';
        racetrack[2] = 'C';
        racetrack[3] = 'D';
        raceTime = 0;
        raceTimer = new Stopwatch();
        curPos = 'A';
        userCars = new ArrayList<DrawCar>();
    }
    
    public DrawCar[] getRaceCars(){
        return raceCars;
    }
    
    public void addCar(DrawCar c){
        userCars.add(c);
    }
    
    public void addCars(){
        // ***********Draw Cars******************************************************************************************
        switch (userCars.size()) {
            case 4:
                raceCars[0] = userCars.get(0);
                raceCars[1] = userCars.get(1);
                raceCars[2] = userCars.get(2);
                raceCars[3] = userCars.get(3);
                break;
            case 3:
                raceCars[0] = userCars.get(0);
                raceCars[1] = userCars.get(1);
                raceCars[2] = userCars.get(2);
                raceCars[3] = new DrawCar();
                break;
            case 2:
                raceCars[0] = userCars.get(0);
                raceCars[1] = userCars.get(1);
                raceCars[2] = new DrawCar();
                raceCars[3] = new DrawCar();
                break;
            default:
                raceCars[0] = userCars.get(0);
                raceCars[1] = new DrawCar();
                raceCars[2] = new DrawCar();
                raceCars[3] = new DrawCar();
                break;
        }
    }
    
    public void runRace(){
        
        raceTimer.startTime();
        
    }
    
    public ArrayList<DrawCar> getCars(){
        return userCars;
    }
    
    public boolean checkWinner(){
        for(int i = 0; i < raceCars.length; i++){
            if(raceCars[i].getEnd() == curPos)
                return true;
        }
        return false;
    }
    
    public String toString(){
        String s = "";
        for(DrawCar c: userCars)
            s += c.toString();
        return s;
    }
}
