/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegame;




import java.awt.Transparency;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    //ArrayList<DrawCar> cars;
    Rectangle[] draw;
    ObservableList<String> options;
    
    TrackAnimate tracks;
    
    Stopwatch s;
    Label time;
    StopwatchGUI timer;
    String color;
    //RunnableLabel r = new RunnableLabel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        s = new Stopwatch();
        time = new Label("00:00");
        timer= new StopwatchGUI(s,time);
        //cars = new ArrayList<DrawCar>();
        draw = new Rectangle[4];
        tracks = new TrackAnimate();
        color = null;
        
        
        options =  FXCollections.observableArrayList(
            "red",
            "blue",
            "black",
            "green",
            "yellow",
            "purple"
        );
        
        
        
       
        
        /**
         * ************Start Screen********************
         */
        VBox root = new VBox();
        TextArea display = new TextArea("Welcome to racing game");
        display.setMinSize(200, 200);
        Button start = new Button("Start");
        Button addPlayer = new Button("Add Player");
        /**
         * adds a car 
         */
        addPlayer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(tracks.getCars().size()==4){
                    Alert tooManyCars = new Alert(Alert.AlertType.WARNING);
                    tooManyCars.setHeaderText("Too Many Cars");
                    tooManyCars.setContentText("Already have max number of racers");
                    tooManyCars.showAndWait();
                }else{
                    
                    tracks.addCar(addCar());
                    
                    display.setText(tracks.toString());
                }
            }
        });
        /**
         * starts the main race
         */
        start.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(tracks.getCars().isEmpty()){
                    Alert noCars = new Alert(Alert.AlertType.WARNING);
                    noCars.setHeaderText("No Cars");
                    noCars.setContentText("Please add atleast 1 car to race");
                    noCars.showAndWait();
                }else{
                
                    
                    /**
                    * ********************Race Screen*********************************************************************
                    */
                    Pane secondaryLayout = new Pane();
                
                    VBox v = new VBox();
                
                    HBox h1 = new HBox();
                    HBox h2 = new HBox();
                    HBox h3 = new HBox();
                    //****************Header***************************************************************************
                    h1.setMinSize(500,100);
                    //s.startTime();
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
                
                    
                   //***********************************leader board***********************************************
                   tracks.animateCars();
                   
                   
                   
                    draw = tracks.getRectangles();
                    race.getChildren().addAll(track,lines,lines2,lines3,track2,draw[0],draw[1],draw[2],draw[3]);
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
                    
                    pause.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent e){
                            s.pause();
                            tracks.pause();
                        }
                    });
                
                    play.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent e){
                            s.resume();
                            tracks.resume();
                            
                        }
                    });
                    
                    restart.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent e){
                            s.startTime();
                            tracks.restart();
                        }
                    });
                    
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
            }  
        });
        
        
        Scene scene = new Scene(root, 500, 500);
        
       
        
        root.getChildren().addAll(display,addPlayer,start);
        //root.getChildren().addAll(display,addPlayer,start);
        
        primaryStage.setTitle("Race Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
    
    public DrawCar addCar(){
        final Stage add = new Stage();
        GridPane g = new GridPane();
        
        
        final ComboBox colors = new ComboBox(options);
        HBox h1 = new HBox();
        HBox h2 = new HBox();
        Label l = new Label("Pick Race Car Color:  ");
        h1.getChildren().add(l);
        Label l2 = new Label("Enter Racers Name  ");
        h2.getChildren().add(l2);
        TextArea name = new TextArea();
        name.setMaxSize(100, 15);
        Button done = new Button("Add Player");
        
        done.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if((!(colors.getSelectionModel().isEmpty()))&&(!(name.getText().isEmpty()))){
                    color = ""+colors.getValue();
                    options.remove(""+colors.getValue());
                    add.close();
                }else{
                    Alert invaled = new Alert(Alert.AlertType.ERROR);
                    invaled.setHeaderText("Error");
                    invaled.setContentText("Please enter a name and pick a color");
                    invaled.showAndWait();
                }
            }
            
        });
        g.setVgap(10);
        g.setHgap(10);
        g.setPadding(new Insets(10, 10, 10, 10));
        g.add(h1,2,2,4,4);
        //l.resize(50, 100);
        g.add(colors, 3, 2);
        g.add(h2, 2, 3);
        g.add(name, 3, 3);
        g.add(done,3,4);
        Scene addp = new Scene(g, 350, 200);
        add.setScene(addp);
        add.getScene().getWindow().setOnCloseRequest(event -> event.consume());
        add.showAndWait();
        
        return new DrawCar(color,name.getText());
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
