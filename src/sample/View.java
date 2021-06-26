package sample;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.enums.Cell;

public class View extends Group {
    final private int CELL_SIZE = 20;
    final private int DOT_SIZE = 6;

    private GraphicsContext gc;
    final private  Game game;
    final private  int width;
    final private  int height;
    private Label score;

    public View(Game game) {
        this.game = game;
        width = game.getColCount() * CELL_SIZE;
        height = game.getRowCount() * CELL_SIZE;

        initializeDrawArea();
        initialDraw();
        initializeScore();
    }

    private void initializeDrawArea() {
        Canvas canvas = new Canvas();
        canvas.setWidth(width);
        canvas.setHeight(height);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
    }

    private void initializeIcon(ImageView icon, Point2D position) {
        icon.setLayoutX((int) position.getX() * CELL_SIZE);
        icon.setLayoutY((int) position.getY() * CELL_SIZE);
        icon.setFitWidth(CELL_SIZE);
        icon.setFitHeight(CELL_SIZE);
        this.getChildren().add(icon);
    }

    private void initializeScore() {
        score = new Label();
        score.setText("0");
        score.setTextFill(Color.YELLOW);
        score.setFont(new Font("Arial", 15));
        score.setLayoutX(width - 30);
        score.setLayoutY(height - 20);
        this.getChildren().add(score);
    }

    private void initialDraw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        for (int i = 0; i < game.getRowCount(); i++) {
            Cell[] row = game.getLevel()[i];

            for (int j = 0; j < game.getColCount(); j++) {
                int posX = j * CELL_SIZE;
                int posY = i * CELL_SIZE;
                int diff = CELL_SIZE / 2 - DOT_SIZE / 2;

                if (row[j] == Cell.WALL) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(posX, posY, CELL_SIZE, CELL_SIZE);
                }

                if (row[j] == Cell.DOT) {
                    gc.setFill(Color.LIGHTGOLDENRODYELLOW);
                    gc.fillOval(posX + diff, posY + diff, DOT_SIZE, DOT_SIZE);
                }

                if (row[j] == Cell.PACMAN) {
                    ImageView pacman = game.getPacman();
                    Point2D position = game.getPacmanPosition();
                    initializeIcon(pacman, position);
                }

                if (row[j] == Cell.GHOST_1) {
                    ImageView ghost = game.getGhost_1();
                    Point2D position = game.getGhost1Position();
                    initializeIcon(ghost, position);
                }

                if (row[j] == Cell.GHOST_2) {
                    ImageView ghost = game.getGhost_2();
                    Point2D position = game.getGhost2Position();
                    initializeIcon(ghost, position);
                }
            }
        }
    }


    public void update() {
        score.setText(game.getPoints());

        for (int i = 0; i < game.getRowCount(); i++) {
            Cell[] row = game.getLevel()[i];

            for (int j = 0; j < game.getColCount(); j++) {
                if (row[j] == Cell.PACMAN) {
                    ImageView pacman = game.getPacman();
                    Point2D position = game.getPacmanPosition();

                    int posX = (int) position.getX() * CELL_SIZE;
                    int posY = (int) position.getY() * CELL_SIZE;

                    gc.setFill(Color.BLACK);
                    gc.fillRect(posX, posY, CELL_SIZE, CELL_SIZE);

                    pacman.setLayoutX(posX);
                    pacman.setLayoutY(posY);
                }


                if (row[j] == Cell.GHOST_1) {
                    ImageView ghost = game.getGhost_1();
                    Point2D position = game.getGhost1Position();

                    int posX = (int) position.getX() * CELL_SIZE;
                    int posY = (int) position.getY() * CELL_SIZE;

                    ghost.setLayoutX(posX);
                    ghost.setLayoutY(posY);
                }

                if (row[j] == Cell.GHOST_2) {
                    ImageView ghost = game.getGhost_2();
                    Point2D position = game.getGhost2Position();

                    int posX = (int) position.getX() * CELL_SIZE;
                    int posY = (int) position.getY() * CELL_SIZE;

                    ghost.setLayoutX(posX);
                    ghost.setLayoutY(posY);
                }
            }
        }
    }

    public void gameOver() {
        Label label = new Label("Game Over");
        label.setTextFill(Color.RED);
        label.setFont(new Font("Arial", 40));

        label.setLayoutX(85);
        label.setLayoutY(100);
        this.getChildren().add(label);
    }

    // Getters

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
