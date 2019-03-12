/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment04.GUI;

import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ben
 */
public class DrawCar extends Car{
    Rectangle r;
    HashMap hm;
    
    public DrawCar(){
        super();
        r = new Rectangle(15,13);
        r.setFill(Color.BLACK);
    }
    
    public DrawCar(String c,String n){
        super(c,n);
        hm = new HashMap();
        hm.put("red", Color.RED);
        hm.put("blue", Color.BLUE);
        hm.put("black", Color.BLACK);
        hm.put("green", Color.GREEN);
        hm.put("yellow", Color.YELLOW);
        hm.put("purple", Color.PURPLE);
        r= new Rectangle(15,13);
        r.setFill((Color)hm.get(c));
    }
    
    public Rectangle getDraw(){
        return r;
    }
    
    public String toString(){
        return super.toString();
        
    }
    
}
