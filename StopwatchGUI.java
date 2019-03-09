/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment04.GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author ben
 */
public class StopwatchGUI {
    
    RunnableWatch timer;
    Thread t;
    TextArea text;
    
    public StopwatchGUI(){
        timer = new RunnableWatch();
        timer.start();
        t = new Thread(timer);
        text = new TextArea("00:00");
    }
    
    public void startThread(){
        t.start();
    }
    
    
    public void setText(){
        text.setText(""+timer.getTime());
    }
}
