/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment04.GUI;


import java.awt.Transparency;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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

/**
 *
 * @author ben
 */
public class Gui extends Application {
    boolean raceing;
    //RunnableLabel r = new RunnableLabel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
        /**
         * ************Start Screen********************
         */
        StackPane root = new StackPane();
        Button start = new Button("Start");
        
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
                
                //Thread t = new Thread(r);
                //t.start();
                Label r = new Label("00:00");
                
                h1.getChildren().addAll(r);
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
                
                
                
                race.getChildren().addAll(track,lines,lines2,lines3,track2);
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
        
        
        Scene scene = new Scene(root, 350, 120);
        
        
        root.getChildren().add(start);
        
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
