package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Game game = new Game();
        View view = new View(game);
        Scene scene = new Scene(view, view.getWidth(), view.getHeight());
        scene.setOnKeyPressed(new Controller(game, view));

        stage.setResizable(false);
        stage.setTitle("Pacman");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
