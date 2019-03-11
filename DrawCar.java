/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment04.GUI;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author ben
 */
public class DrawCar {
    Rectangle r;
    
    public DrawCar(){
        r = new Rectangle(15,13);
    }
    
    public Rectangle getDraw(){
        return r;
    }
    
    public String toString(){
        return "new car added";
    }
    
}
