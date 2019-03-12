/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;



import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author ben
 */
public class RunnableWatch extends Stopwatch implements Runnable{
    
    public RunnableWatch(){
        super();
        
    }

    @Override
    public void run() {
        boolean t = true;
        while(t){
            this.setTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                t= false;
                Logger.getLogger(RunnableWatch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
