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
   

    
    public TrackAnimate(TextArea tx){
        super();
     
        leaders = tx;
        t= new PathTransition[4][3];
        
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                t[i][j] = new PathTransition();
            }
           
            
        }
    }
      
    
    public Group[] getRectangles(){
        Group[] r = new Group[4];
        for(int i = 0; i < 4; i++)
            r[i] = super.getRaceCars()[i].getDraw();
        return r;
    }
    
    public void animateCars(){
        super.addCars();
        Ellipse car1path = new Ellipse(-338,0,338,218);
       // car1path.intersects(400, 0, 400, 1000);
        Ellipse car2path = new Ellipse(-313,0,313,193);
        Ellipse car3path = new Ellipse(-288,0,288,168);
        Ellipse car4path = new Ellipse(-263,0,263,143);
        
        
        
        Duration temp;
        for(int i=0;i<4;i++){
            super.getRaceCars()[i].setStarted();
            if(i==0){
                super.getRaceCars()[i].getDraw().relocate(738, 250);
            }else if(i==1){
                super.getRaceCars()[i].getDraw().relocate(713, 250);
            }else if(i==2){
                super.getRaceCars()[i].getDraw().relocate(688, 250);
            }else{
                super.getRaceCars()[i].getDraw().relocate(663, 250);
            }
            super.getRaceCars()[i].getDraw().setRotate(90);
            
            
            for(int j=0;j<3;j++){
                
            
                t[i][j].setNode(super.getRaceCars()[i].getDraw());
                t[i][j].setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                temp = Duration.seconds(super.getRaceCars()[i].getSpeed());
                t[i][j].setDuration(temp);
                
                if(j<2){
                    t[i][j].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            startnext(event.getSource());
                            updateleaders();
                        }
                    });
                }else{
                    t[i][j].setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            endRace(event.getSource());
                        }
                    });
                }
            
        
                t[1][j].setPath(car2path);
                t[2][j].setPath(car3path);
                t[3][j].setPath(car4path);
                t[0][j].setPath(car1path);  
            }
            t[i][0].setDelay(Duration.seconds(super.getRaceCars()[i].getDelay()));
        }
        
    }
    
    public void play(){
        super.raceTimer.startTime();   
        for(int i = 0; i<4;i++){
            t[i][super.getRaceCars()[i].getLap()].play();
        }
        updateleaders();
    }
    public void startnext(Object o){
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                if(((PathTransition)o).equals(t[i][j])){
                    super.getRaceCars()[i].setSplit(j, raceTimer.getTimed());
                    
                    super.getRaceCars()[i].setLap(j+1);
                    t[i][super.getRaceCars()[i].getLap()].playFromStart();
                   
                }
            }
        }
        updateleaders();
    }
    
    public void pause(){
        super.raceTimer.pause();
        for(int i = 0; i<4;i++){
            t[i][super.getRaceCars()[i].getLap()].pause();
        }
    }
    
    public void resume(){
        super.raceTimer.resume();
        for(int i = 0; i<4;i++){
            t[i][super.getRaceCars()[i].getLap()].play();
        }
    }
    
    // needs work
    public void restart(){
        super.raceTimer.startTime();
        for(int i = 0; i<4;i++){
            t[i][super.getRaceCars()[i].getLap()].playFromStart();
            t[i][super.getRaceCars()[i].getLap()].pause();
            super.getRaceCars()[i].reset();
            t[i][0].playFromStart();
        }
        updateleaders();
        
    }
    
    
    private void updateleaders(){
        String s="   Leader Board \n\n";
        int place = 1;
        DrawCar[] temp= super.getRaceCars().clone();
        DrawCar temp2;
        
        
        for(int i=0;i<4;i++){
            for(int j=i+1;j<4;j++){
                if(temp[i].getLap()<temp[j].getLap()){
                    temp2 = temp[i];
                    temp[i] = temp[j];
                    temp[j] = temp2;
                }else if((temp[i].getLap()==temp[j].getLap())&&(temp[i].getLap()>0)){
                    if(temp[i].getTotalTime()>temp[j].getTotalTime()){
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
        
        
        leaders.setText(s);
    }
    
    public void endRace(Object o){
        for(int i=0;i<4;i++){
            if(((PathTransition)o).equals(t[i][2])){
                super.getRaceCars()[i].setSplit(2, super.raceTimer.getTimed());
                super.getRaceCars()[i].setTotalTime(super.raceTimer.getTimed());
                super.getRaceCars()[i].setLap(4);
                if(!super.getRaceCars()[i].invisible){
                    finished++;
                }
            }
        }
        updateleaders();
    }
    
    
    @Override
    public void handle(long now){ 
        super.handle(now);
        updateleaders();
        if(finished==numRacers){
            finished++;
            super.raceTimer.pause();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            String s = "Race Over! \n";
            Text text = new Text(s);
            text.setText(s+leaders.getText()); 
            dialogVbox.getChildren().add(text);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }
        
    }
    
}
