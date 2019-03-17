/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Elizabeth Chapolini
 */
public class Race extends AnimationTimer{
    
    private long raceTime;
    private DrawCar[] raceCars;
    private ArrayList<DrawCar> userCars;
    
    private char curPos;
    public Stopwatch raceTimer;
    public Label l;
    public int numRacers;
    
    public Race(){
        raceCars = new DrawCar[4];
        for(int i=0;i<4;i++)
            raceCars[i] = new DrawCar();
        
        raceTime = 0;
        raceTimer = new Stopwatch();
        curPos = 'A';
        userCars = new ArrayList<DrawCar>();
        l = new Label("00:00");
        numRacers = 0;
        
    }
    
    public DrawCar[] getRaceCars(){
        return raceCars;
    }
    public Label getLabel(){
        return l;
    }
    
    public void addCar(DrawCar c){
        c.setDraw();
        userCars.add(c);
        
    }
    
    public void addCars(){
        // ***********Draw Cars******************************************************************************************
        numRacers = userCars.size();
        switch (numRacers) {
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
    
    
    
    public String toString(){
        String s = "";
        for(DrawCar c: userCars)
            s += c.toString();
        return s;
    }

    @Override
    public void handle(long now) {
        raceTimer.setTime();
        l.setText(""+raceTimer.getTime());
    }
}
