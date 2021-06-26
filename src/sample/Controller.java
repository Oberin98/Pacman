package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;

import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import sample.enums.Direction;

public class Controller implements EventHandler<KeyEvent> {
    final private View view;
    final private Game game;
    Timeline timer;

    public Controller(Game game, View view) {
        this.view = view;
        this.game = game;

        startTimer();
    }

    public void update() {
        if (!game.getIsGameOver()) {
            game.update();
            view.update();
        } else {
            view.gameOver();
        }

    }

    public void startTimer() {
        timer = new Timeline(new KeyFrame(Duration.millis(200), e -> update()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Direction direction = game.getDirection();

        switch (keyEvent.getCode()) {
            case UP -> direction = Direction.UP;
            case RIGHT -> direction = Direction.RIGHT;
            case DOWN -> direction = Direction.DOWN;
            case LEFT -> direction = Direction.LEFT;
        }

        game.setDirection(direction);
        keyEvent.consume();
    }
}
