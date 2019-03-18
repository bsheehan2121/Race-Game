
package racegame;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Elizabeth Chapolini, Ben Sheehan
 */

// The Race class represents a race with a timer and a number of cars to race
public class Race extends AnimationTimer{
    
    private long raceTime;
    private DrawCar[] raceCars;
    private ArrayList<DrawCar> userCars;
    
    private Stopwatch raceTimer;
    private Label l;
    private int numRacers;
    
    // This default constructor for Race was written initially by Elizabeth
    public Race(){
        raceCars = new DrawCar[4];
        for(int i=0;i<4;i++)
            raceCars[i] = new DrawCar();
        
        raceTime = 0;
        raceTimer = new Stopwatch();
        userCars = new ArrayList<DrawCar>();
        // Ben added this part
        l = new Label("00:00");
        l.relocate(100,100);
        l.setMinSize(50, 50);
        l.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
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
    
    public void removeCar(){
        
        userCars.remove(userCars.size()-1);
        
    }
    
    public void addCars(){
        // ***********Draw Cars******************************************************************************************
        numRacers = userCars.size();
        // Elizabeth wrote this switch statement with input from Ben and Firas
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
    
    public Stopwatch getTimer(){
        return raceTimer;
    }
    
    public int getRacerNum(){
        return numRacers;
    }
    
    // Elizabeth wrote this method
    public ArrayList<DrawCar> getCars(){
        return userCars;
    }
    
    
    // Elizabeth wrote this method
    public String toString(){
        String s = "";
        for(DrawCar c: userCars)
            s += c.toString();
        return s;
    }

    // Ben wrote this method to set the timer text
    @Override
    public void handle(long now) {
        raceTimer.setTime();
        l.setText(""+raceTimer.getTime());
    }
}
