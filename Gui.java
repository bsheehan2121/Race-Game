/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment04.GUI;


import java.awt.Transparency;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author ben
 */
public class Gui extends Application{
    boolean raceing;
    ArrayList<DrawCar> cars;
    
    Stopwatch s;
    Label time;
    StopwatchGUI timer;
    
    //RunnableLabel r = new RunnableLabel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        s = new Stopwatch();
        time = new Label("00:00");
        timer= new StopwatchGUI(s,time);
        cars = new ArrayList<DrawCar>();
        //***************for testing perpouses*********************
        cars.add(new DrawCar());
        cars.add(new DrawCar());
        cars.add(new DrawCar());
        cars.add(new DrawCar());
        
        /**
         * ************Start Screen********************
         */
        VBox root = new VBox();
        TextArea display = new TextArea("Welcome to racing game");
        display.setMinSize(200, 200);
        Button start = new Button("Start");
        Button addPlayer = new Button("Add Player");
        
        addPlayer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                cars.add(new DrawCar());
                String s = "";
                for(DrawCar c: cars){
                    s+= c.toString()+"\n";
                }
                display.setText(s);
            }
        });
        
        start.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                /**
                 * *********Race Screen*************
                 */
                Pane secondaryLayout = new Pane();
                
                VBox v = new VBox();
                
                HBox h1 = new HBox();
                HBox h2 = new HBox();
                HBox h3 = new HBox();
                //****************Header***************************************************************************
                h1.setMinSize(500,100);
                s.startTime();
                timer.start();
                h1.getChildren().addAll(time);
                //***************Center***************************************************************************
                
                h2.setPrefSize(500, 500);
                Pane race = new Pane();
                race.setBackground(new Background(new BackgroundFill(
                 Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
                race.setMinSize(1200, 500);
                
                Ellipse track = new Ellipse(400,250,350,230);
                track.setFill(Color.DARKGRAY);
                
                Button b = new Button("test");
                
                Ellipse lines = new Ellipse(400,250,325,205);
                lines.getStrokeDashArray().addAll(25d, 10d);
                lines.setFill(Color.DARKGRAY);
                lines.setStroke(Color.BLACK);
                
                Ellipse lines2 = new Ellipse(400,250,300,180);
                lines2.getStrokeDashArray().addAll(25d, 10d);
                lines2.setFill(Color.DARKGRAY);
                lines2.setStroke(Color.BLACK);
                
                Ellipse lines3 = new Ellipse(400,250,275,155);
                lines3.getStrokeDashArray().addAll(25d, 10d);
                lines3.setFill(Color.DARKGRAY);
                lines3.setStroke(Color.BLACK);
                
                
                Ellipse track2 = new Ellipse(400,250,250,130);
                
                Rectangle car1= cars.get(0).getDraw();
                Rectangle car2= cars.get(1).getDraw();
                Rectangle car3= cars.get(2).getDraw();
                Rectangle car4= cars.get(3).getDraw();
                
                   //**************animation*************************************
                
                PathTransition t1 = new PathTransition();
                PathTransition t2 = new PathTransition();
                PathTransition t3 = new PathTransition();
                PathTransition t4 = new PathTransition();
                Ellipse car1path = new Ellipse(400,250,338,218);
                Ellipse car2path = new Ellipse(400,250,313,193);
                Ellipse car3path = new Ellipse(400,250,288,168);
                Ellipse car4path = new Ellipse(400,250,263,143);
                t1.setNode(car1);
                t2.setNode(car2);
                t3.setNode(car3);
                t4.setNode(car4);
                
                t2.setPath(car2path);
                t2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                t2.setDuration(Duration.seconds(10));
                t2.play();
                
                t3.setPath(car3path);
                t3.setDuration(Duration.seconds(10));
                t3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                t3.play();
                
                t4.setPath(car4path);
                t4.setDuration(Duration.seconds(10));
                t4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                t4.play();
                
                t1.setPath(car1path);
                t1.setDuration(Duration.seconds(10));
                t1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                t1.play();
                race.getChildren().addAll(track,lines,lines2,lines3,track2,car1,car2,car3,car4);
                h2.getChildren().add(race);
                
                //***************Bottom*****************************************************************************
                
                h3.setMinSize(200, 200);
                
                h3.setPadding(new Insets(15, 12, 15, 12));
                h3.setSpacing(10);
                //Pane p = new Pane();
                //p.setMinSize(200, 200);
                Button pause = new Button("Pause");
                Button play = new Button("Play");
                Button restart = new Button("Restart");
                
                //p.getChildren().addAll(pause,play,restart);
                h3.getChildren().addAll(pause,play,restart);
                //**********************************************************************************************************8
                v.getChildren().addAll(h1,h2,h3);
                secondaryLayout.getChildren().addAll(v);
                Scene secondScene = new Scene(secondaryLayout, 1200, 700);
                Stage secondStage = new Stage();
                secondStage.setTitle("Race");
                secondStage.setScene(secondScene);
                secondStage.show();
                primaryStage.close();
                
                
                
            }  
        });
        
        
        Scene scene = new Scene(root, 500, 500);
        
       
        
        root.getChildren().addAll(display,addPlayer,start);
        //root.getChildren().addAll(display,addPlayer,start);
        
        primaryStage.setTitle("Race Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
