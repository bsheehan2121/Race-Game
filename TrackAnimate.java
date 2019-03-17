/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author ben
 */
public class TrackAnimate extends Race{
    
    PathTransition[][] t;
    TextArea leaders;
    int finished = 0;
    DrawCar winner;
    DrawCar allstar;
    String allstarstats;
   

    
    public TrackAnimate(TextArea tx){
        super();
     
        leaders = tx;
        winner = new DrawCar();
        allstar = new DrawCar();
        allstar.setTotalTime(10000000);
        t= new PathTransition[4][3];
        allstarstats = "";
        
        //the 2-d array sets a pathTransition for each lap for each car
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                t[i][j] = new PathTransition();
            } 
        }
    }
      
    // used for drawring the objects
    public Group[] getRectangles(){
        Group[] r = new Group[4];
        for(int i = 0; i < 4; i++)
            r[i] = super.getRaceCars()[i].getDraw();
        return r;
    }
    
    
    // this sets up the animation
    public void animateCars(){
        
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                t[i][j] = new PathTransition();
            } 
        }
        
        super.addCars();
        Ellipse car1path = new Ellipse(-338,0,338,218);
       // car1path.intersects(400, 0, 400, 1000);
        Ellipse car2path = new Ellipse(-313,0,313,193);
        Ellipse car3path = new Ellipse(-288,0,288,168);
        Ellipse car4path = new Ellipse(-263,0,263,143);
        
        
        finished =0;
        Duration temp;
        for(int i=0;i<4;i++){
            super.getRaceCars()[i].setStarted(); //this helps with tostring of cars
            switch (i) {
                case 0:
                    super.getRaceCars()[i].getDraw().relocate(738, 250); // moves cars onto track
                    break;
                case 1:
                    super.getRaceCars()[i].getDraw().relocate(713, 250);
                    break;
                case 2:
                    super.getRaceCars()[i].getDraw().relocate(688, 250);
                    break;
                default:
                    super.getRaceCars()[i].getDraw().relocate(663, 250);
                    break;
            }
            super.getRaceCars()[i].getDraw().setRotate(90);
            
            //represents the 3 laps each car needs to complete
            for(int j=0;j<3;j++){
                
            
                t[i][j].setNode(super.getRaceCars()[i].getDraw());
                t[i][j].setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                temp = Duration.seconds(super.getRaceCars()[i].getSpeed()); 
                t[i][j].setDuration(temp); //allows for each lap to take a dif emount of time
                
                if(j<2){ //if there is another lap
                    t[i][j].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            startnext(event.getSource()); // this method handles end of lap animation switching
                            updateleaders();
                        }
                    });
                }else{  //if car has finished
                    t[i][j].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            endRace(event.getSource());  // this ends that cars race and checks if all cars have finished
                        }
                    });
                }
            
        
                t[1][j].setPath(car2path);
                t[2][j].setPath(car3path);
                t[3][j].setPath(car4path);
                t[0][j].setPath(car1path);  
            }
            t[i][0].setDelay(Duration.seconds(super.getRaceCars()[i].getDelay()));  // ads a delay to the start of race
        }
        
    }
    
    public void play(){
        super.raceTimer.startTime();  //controls timer 
        for(int i = 0; i<4;i++){
            t[i][super.getRaceCars()[i].getLap()].play(); //only starts the current lap animation
        }
        updateleaders();
    }
    
   
    private void startnext(Object o){
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                if(((PathTransition)o).equals(t[i][j])){  // finds the animation that just finished
                    super.getRaceCars()[i].setSplit(j, raceTimer.getTimed()); // adds the time split
                    
                    if(super.getRaceCars()[i].getLap()<2){
                        super.getRaceCars()[i].setLap(super.getRaceCars()[i].getLap()+1); // updates the lap
                    }
                    t[i][super.getRaceCars()[i].getLap()].playFromStart(); // starts the next lap animation
                   
                }
            }
        }
        updateleaders();
    }
    
    public String allstar(){
        return allstarstats;
    }
    
    public void pause(){
        super.raceTimer.pause(); // pauses the time
        for(int i = 0; i<4;i++){
            t[i][super.getRaceCars()[i].getLap()].pause(); // pauses the current lap animation
        }
    }
    
    public void resume(){
        super.raceTimer.resume(); //resumes the time
        for(int i = 0; i<4;i++){
            t[i][super.getRaceCars()[i].getLap()].play(); // starts animation where it left off
        }
    }
    
    // restarts the race and clears lap and splits
    public void restart(){
        super.raceTimer.startTime();
        finished=0;
        for(int i = 0; i<4;i++){
            t[i][1].playFromStart();
            t[i][1].pause();
            t[i][2].playFromStart();
            t[i][2].pause();
            super.getRaceCars()[i].reset(2);
            t[i][0].playFromStart();
            t[i][0].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            startnext(event.getSource()); // this method handles end of lap animation switching
                            updateleaders();
                        }
                    });
            t[i][1].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            startnext(event.getSource()); // this method handles end of lap animation switching
                            updateleaders();
                        }
                    });
            t[i][2].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            endRace(event.getSource());  // this ends that cars race and checks if all cars have finished
                        }
                    });
            
        }
        updateleaders();
        
    }
    
    
    // this is used to update the leader board
    private void updateleaders(){
        String s="   Leader Board \n\n";
        int place = 1;
        DrawCar[] temp= super.getRaceCars().clone();
        DrawCar temp2;
        
        
        for(int i=0;i<4;i++){
            for(int j=i+1;j<4;j++){
                if(temp[i].getLap()< temp[j].getLap()){ // first checks based on lap 
                        temp2 = temp[i];
                        temp[i]=temp[j];
                        temp[j]=temp2;
                }else if(temp[i].getLap()== temp[j].getLap()){
                    if(temp[i].getTotalTime()>temp[j].getTotalTime()){  //then by time
                        temp2 = temp[i];
                        temp[i]=temp[j];
                        temp[j]=temp2;
                        }
                    } 
                }
            
            s+="\n "+place+")  "+temp[i].toString();
            place++;
            }
        
            s+="\n";
        winner = temp[0];
        
        leaders.setText(s);
    }
    
    
    // when a car finishes this runs
    public void endRace(Object o){
        for(int i=0;i<4;i++){
            if(((PathTransition)o).equals(t[i][2])){ //gets current animation
                super.getRaceCars()[i].setSplit(2, super.raceTimer.getTimed()); // sets final split
                super.getRaceCars()[i].setTotalTime(super.raceTimer.getTimed()); // sets final time
                super.getRaceCars()[i].setLap(3); // sets lap for to string reasons
                if(!super.getRaceCars()[i].invisible){ // doesnt count filler cars
                    finished++;
                }
            }
        }
        updateleaders();
    }
    
    public void replay(){
        Duration temp = Duration.seconds(winner.getTotalTime()-5);
        System.out.println(winner.getTotalTime()-5);
        super.raceTimer.setTime((long) temp.toSeconds());
        finished = 0;
        for(int i=0;i<4;i++){
            t[i][2].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            endRace(event.getSource());  // this ends that cars race and checks if all cars have finished
                        }
                    });
            temp = Duration.seconds(super.getRaceCars()[i].totalTime -super.raceTimer.getTimed());
            t[i][0].playFromStart();
            t[i][0].pause();
            if(temp.toSeconds()> super.getRaceCars()[i].getSplit(2)){
                t[i][2].playFromStart();
                t[i][2].pause();
                t[i][1].playFrom(Duration.seconds(super.getRaceCars()[i].getSplit(1)).subtract(temp.subtract(Duration.seconds(super.getRaceCars()[i].getSplit(2)))));
            }else{
                t[i][1].playFromStart();
                t[i][1].pause();
                t[i][2].playFromStart();
                t[i][2].pause();
                t[i][2].playFrom(Duration.seconds(super.getRaceCars()[i].getSplit(2)).subtract(temp));
            }
            super.getRaceCars()[i].reset(1);
        }
        super.raceTimer.resume();
    }
    
    
    @Override
    public void handle(long now){ //this is things that happen every frame
        super.handle(now);
        updateleaders();
        if(finished==numRacers){ // checks if all racers finished
            if(winner.getTotalTime()<allstar.getTotalTime()){
                allstar = winner;
                allstarstats = "\nFastest Car: "+allstar.toString();
            }
            finished++;
            super.raceTimer.pause();
            final Stage winner = new Stage();
            VBox dialogVbox = new VBox();
            String s = "Race Over! \n";
            Text text = new Text(s);
            text.setText(s+leaders.getText());
            Button replay = new Button("Instant Replay");
            
            replay.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent e){
                          replay();
                          winner.close();
                        }
                    });
            
            dialogVbox.getChildren().addAll(text,replay);
            Scene dialogScene = new Scene(dialogVbox, 500, 500);
            winner.setScene(dialogScene);
            winner.show();
        }
        
    }
    
}
