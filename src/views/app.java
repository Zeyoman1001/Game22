package views;

import engine.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class app extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Available Heroes");
        Game.loadHeroes("C:\\Users\\Dell\\OneDrive\\Desktop\\Eclipse_DATA\\My-Game\\Game22\\Heros.csv");

        int availableHeroesSize = Game.availableHeroes.size();

        GridPane heroesGrid = new GridPane();


        Button AddHero0 = new Button("Add Hero 1");
        AddHero0.setOnAction(event -> {
            Game.availableHeroes.get(0);
        });
        Button AddHero1 = new Button("Add Hero 2");
        Button AddHero2 = new Button("Add Hero 3");
        Button AddHero3 = new Button("Add Hero 4");
        Button AddHero4 = new Button("Add Hero 5");
        Button AddHero5 = new Button("Add Hero 6");
        Button AddHero6 = new Button("Add Hero 7");
        Button AddHero7 = new Button("Add Hero 8");


        heroesGrid.add(AddHero0,0,0 ,1 ,1);
        heroesGrid.add(AddHero1,1,0 ,1 ,1);
        heroesGrid.add(AddHero2,2,0 ,1,1);
        heroesGrid.add(AddHero3,3,0,1,1);
        heroesGrid.add(AddHero4,4,0 ,1 ,1);
        heroesGrid.add(AddHero5,5,0 ,1 ,1);
        heroesGrid.add(AddHero6,6,0 ,1,1);
        heroesGrid.add(AddHero7,7,0,1,1);
        heroesGrid.setPadding(new Insets(10,10,10,10));

        Scene allHeroes = new Scene(heroesGrid , 700 , 400);
        primaryStage.setScene(allHeroes);
        primaryStage.show();

    }

//    public boolean closeGame(){
//
//    }


}
