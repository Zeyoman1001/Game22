package views;

import engine.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.characters.Character;
import model.characters.Hero;

import java.awt.*;
import java.util.ArrayList;

public class LoadHeroes extends Application {
    BorderPane layoutheroes = new BorderPane();
    VBox layoutdetails = new VBox();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
      primaryStage.setTitle("Choose your hero(Hover to check details)");


      TextArea heroesdetails = new TextArea("Hero details:\n");
      heroesdetails.setMaxSize(375,1000);
      heroesdetails.setEditable(false);


      layoutheroes.setRight(heroesdetails);

      Scene LoadHeroes = new Scene(layoutheroes, 1500,1000);
      heroesdetails.setFont(new Font(24));
      primaryStage.setScene(LoadHeroes);
      primaryStage.show();


    }
    public void addHeroes(Button hero){
        layoutheroes.getChildren().add(hero);


    }
    public void addDetails(Hero hero){
        String health = "Max health:" + hero.getMaxHp() + '\n';
        String damage = "Damage" + hero.getAttackDmg() + '\n';
        String end = "----------";
    }
}
