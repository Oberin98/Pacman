package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.enums.Cell;
import sample.enums.Direction;
import sample.utils.Images;
import sample.utils.Level;

public class Game {
    final private String LEVEL_PATH = "src/sample/utils/level";
    final private Level level;

    private Direction currentDirection;
    private Direction nextDirection;
    private final ImageView pacman;
    private final ImageView ghost_1;
    private final ImageView ghost_2;
    private Point2D pacmanPosition;
    private Point2D ghost1Position;
    private Point2D ghost2Position;
    private int dotsCount;
    private int points;
    private boolean isGameOver;

    public Game() {
        level = new Level(LEVEL_PATH);
        pacmanPosition = level.getInitialPacmanPosition();
        ghost1Position = level.getInitialGhost1Position();
        ghost2Position = level.getInitialGhost2Position();
        dotsCount = level.getDotsCount();

        pacman = new ImageView(Images.PACMAN_R);
        ghost_1 = new ImageView(Images.GHOST);
        ghost_2 = new ImageView(Images.GHOST);
        currentDirection = Direction.NONE;
        nextDirection = Direction.NONE;

        isGameOver = false;
    }

    public void update() {
        movePacman();
        ghost1Position = updatePositionRandom(ghost1Position);
        ghost2Position = updatePositionRandom(ghost2Position);
        isGameOver();
    }

    private void movePacman() {
        Point2D potentialPosition = getPosition(nextDirection, pacmanPosition);
        int posX = (int) potentialPosition.getX();
        int posY = (int) potentialPosition.getY();


        if (nextDirection != currentDirection && !isNextWall(posX, posY)) {
            currentDirection = nextDirection;
        } else {
            potentialPosition = getPosition(currentDirection, pacmanPosition);
            posX = (int) potentialPosition.getX();
            posY = (int) potentialPosition.getY();
        }


        if (!isNextWall(posX, posY)) {
            pacmanPosition = new Point2D(posX, posY);
            pacman.setImage(getPacmanImage(currentDirection));

            if (isNextDot(posX, posY)) {
                level.getLevel()[posY][posX] = Cell.EMPTY;
                dotsCount--;
                points += 10;
            }
        }
    }

    private Point2D updatePositionRandom(Point2D position) {
        Point2D potentialPosition = getPosition(getRandomDirection(), position);

        while (isNextWall((int) potentialPosition.getX(), (int) potentialPosition.getY())) {
            potentialPosition = getPosition(getRandomDirection(), position);
        }

        int posX = (int) potentialPosition.getX();
        int posY = (int) potentialPosition.getY();
        return new Point2D(posX, posY);
    }

    // Helpers

    private void isGameOver() {
        isGameOver = (pacmanPosition.getX() == ghost1Position.getX()
                && pacmanPosition.getY() == ghost1Position.getY())
                || (pacmanPosition.getX() == ghost2Position.getX()
                && pacmanPosition.getY() == ghost2Position.getY());
    }

    private boolean isNextWall(int x, int y) {
        return level.getLevel()[y][x] == Cell.WALL;
    }

    private boolean isNextDot(int x, int y) {
        return level.getLevel()[y][x] == Cell.DOT;
    }

    public Direction getRandomDirection() {
        int random = (int) Math.round(Math.random() * 4);

        if (random == 0) return Direction.UP;
        if (random == 1) return Direction.RIGHT;
        if (random == 2) return Direction.DOWN;
        else return Direction.LEFT;
    }

    private Point2D getPosition(Direction direction, Point2D position) {
        int posX = (int) position.getX();
        int posY = (int) position.getY();

        switch (direction) {
            case UP -> posY--;
            case RIGHT -> posX++;
            case DOWN -> posY++;
            case LEFT -> posX--;
        }

        return new Point2D(posX, posY);
    }

    private Image getPacmanImage(Direction direction) {
        Image pacmanView = pacman.getImage();

        switch (direction) {
            case UP -> pacmanView = Images.PACMAN_T;
            case RIGHT -> pacmanView = Images.PACMAN_R;
            case DOWN -> pacmanView = Images.PACMAN_B;
            case LEFT -> pacmanView = Images.PACMAN_L;
        }

        return pacmanView;
    }

    // Setters

    public void setDirection(Direction direction) {
        this.nextDirection = direction;
    }

    // Getters

    public Direction getDirection() {
        return currentDirection;
    }

    public Cell[][] getLevel() {
        return level.getLevel();
    }

    public int getColCount() {
        return level.getColCount();
    }

    public int getRowCount() {
        return level.getRowCount();
    }

    public ImageView getPacman() {
        return pacman;
    }

    public ImageView getGhost_1() {
        return ghost_1;
    }

    public ImageView getGhost_2() {
        return ghost_2;
    }

    public Point2D getPacmanPosition() {
        return pacmanPosition;
    }

    public Point2D getGhost1Position() {
        return ghost1Position;
    }

    public Point2D getGhost2Position() {
        return ghost2Position;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public String getPoints() {
        return Integer.toString(points);
    }
}
