
package racegame;



import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author ben
 */
// the DrawCar class creates a car that can be represented graphically
public class DrawCar extends Car{
    private Group g;
    private Shape s;
    private HashMap hm;
    private double[] splits;
    private int lap;
    private double totalTime;
    private boolean started;
    
    public DrawCar(){
        super();
        g = new Group();
        splits = new double[3];
        splits[0]=0.0;
        splits[1]=0.0;
        splits[2]=0.0;
        lap = 0;
        totalTime = 0;
        s= new Rectangle(0,0);
    }
    public DrawCar(String c,String n,int i,String ty){
        super(c,n,i,ty);
        hm = new HashMap();
        g = new Group();
        hm.put("red", Color.RED);
        hm.put("blue", Color.BLUE);
        hm.put("black", Color.BLACK);
        hm.put("green", Color.GREEN);
        hm.put("yellow", Color.YELLOW);
        hm.put("purple", Color.PURPLE);
        s= new Rectangle(15,13);
        s.setFill((Color)hm.get(c));
        splits = new double[3];
        splits[0]=0.0;
        splits[1]=0.0;
        splits[2]=0.0;
        lap = 0;
        totalTime = 0;
        started = false;
        
      
    }
    
    public void setDraw(){
        if(super.getType().equals("racecar")){
            

            Polygon p = new Polygon();
            p.getPoints().addAll(new Double[]{
                0.0, 0.0,
                0.0, 10.0,
                10.0, 5.0 });
            p.setFill((Color)hm.get(super.getColor()));
            g.getChildren().add(p);
        }else if(super.getType().equals("truck")){
            s= new Rectangle(20,15);
            s.setFill((Color)hm.get(super.getColor()));
            g.getChildren().add(s);   
        }else if(super.getType().equals("hybrid")){
            s = new Rectangle(12,12);
            s.setFill((Color)hm.get(super.getColor()));
            g.getChildren().add(s);  
        }else{
            g.getChildren().add(s);
        }
        
    }
    
    public void setSplit(int i,double d){
        if(i>0){
            splits[i] = d-totalTime;
        }else{
            splits[i] = d;
        }
        totalTime = d;
    }
    
    public double getCurrentSplit(){
        if(lap==0){
            return splits[lap];
        }else{
            return splits[lap-1];
        }
    }
    
    public double getSplit(int i){
        return splits[i];
    }
    
    public void setTotalTime(double d){
        totalTime = d;
    }
    
    public double getTotalTime(){
        return totalTime;
    }
    
    public Group getDraw(){
        return g;
    }
    public void setLap(int l){
        lap = l;
    }
    
    public int getLap(){
        return lap;
    }
    
    public void setStarted(){
        started = true;
    }
    public void reset(int i){
        if(i==1){
            totalTime += 0-splits[2];
            splits[2]=0.0;
            lap=2;
            
        }else{
            splits[0]=0.0;
            splits[1]=0.0;
            splits[2]=0.0;
            lap=0;
            totalTime = 0;
        }
    }
    
    public String toString(){
        String s = super.toString();
        if(!super.getInvisible()){
            if(lap<3){
                if(started){
                    s += "   Lap: "+(lap+1);
                }
            }else{
                s+= "    finished race";
            }
            int count = 1;
            for(double d:splits){
                if(d>0){
                    s +="\n    Split "+count+":   "+d;
                    count++;
                }
            }
            if(lap>2){
                s += "\n    Final Time: "+totalTime;
            }else if(totalTime>0){
                s += "\n    Elapsed Time: "+totalTime;
            }
        }
        s+="\n";
        return s;
        
    }
    
}
