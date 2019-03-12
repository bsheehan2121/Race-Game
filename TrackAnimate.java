/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;

import javafx.animation.PathTransition;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author ben
 */
public class TrackAnimate {
    
    Car[] c;
    PathTransition[] t;

    
    public TrackAnimate(){
        c = new Car[4];
        t= new PathTransition[4];
        for(int i=0;i<4;i++){
            c[i]= new Car();
            t[i]= new PathTransition();
        }
        
        
    }
    
    
    public void animateCars(Rectangle[] draw){
        
        Ellipse car1path = new Ellipse(400,250,338,218);
        Ellipse car2path = new Ellipse(400,250,313,193);
        Ellipse car3path = new Ellipse(400,250,288,168);
        Ellipse car4path = new Ellipse(400,250,263,143);
        for(int i=0;i<4;i++){
            t[i].setNode(draw[i]);
            t[i].setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            t[i].setDuration(Duration.seconds(c[i].getSpeed()));
        }
        t[1].setPath(car2path);
        t[2].setPath(car3path);
        t[3].setPath(car4path);
        t[0].setPath(car1path);  
    }
    
    public void play(){
        for(PathTransition p:t){
            p.play();
        }
    }
    
    public void pause(){
        for(PathTransition p:t){
            p.pause();
        }
    }
    
    public void resume(){
        for(PathTransition p:t){
            p.play();
        }
    }
    
    public void restart(){
        for(PathTransition p:t){
            p.playFromStart();
        }
    }
    
}
