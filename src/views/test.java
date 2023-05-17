package views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class test extends Application {
    Button button ;

    Stage window  = new Stage();
    Scene scene1 , scene2 ;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        switchScenes();
//        primaryStage.setTitle("Last Of Us");
//        button = new Button();
//        button.setText("Up");
//        buttonHandler(button);
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//
//        Scene scene = new Scene(layout , 300 , 250);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    private static void buttonHandler(Button b){
        b.setOnAction(event -> {
            System.out.println("Move Up");
            System.out.println("Set all visible");
        });
    }

    private void  switchScenes(){
        Label l1 = new Label("This is window 1");
        Button b1 = new Button("Scene2 ");
        b1.setOnAction(event -> window.setScene(scene2));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(b1 , l1);
        scene1 = new Scene(layout , 300 , 250);

        Label l2 = new Label("This is window 2");

        Button b2 = new Button("Scene1");
        b2.setOnAction(e->window.setScene(scene1));

        VBox layout2 = new VBox(60);
        layout2.getChildren().addAll(b2 ,l2);
        scene2 = new Scene(layout2 , 400,350);

        window.setScene(scene1);
        window.setTitle("My App");
        window.show();
    }

}
