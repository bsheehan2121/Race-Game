/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment04.GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author ben
 */
public class StopwatchGUI extends AnimationTimer{
    
    Label l;
    Stopwatch s;
    
    public StopwatchGUI(Stopwatch sw, Label lb){
        super();
        l = lb;
        s = sw;
    }
    
    
    @Override
    public void handle(long now) {
        s.setTime();
        l.setText(""+s.getTime());
    }
    
    
    public Label getText(){
        return l;
    }
}
