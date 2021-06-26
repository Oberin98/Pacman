package sample.utils;

import javafx.geometry.Point2D;
import sample.enums.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
    private Cell[][] level;
    private int rowCount;
    private int colCount;
    private Point2D initialPacmanPosition;
    private Point2D initialGhost1Position;
    private Point2D initialGhost2Position;
    private int dotsCount;

    public Level(String filename) {
        try {
            File file = new File(filename);
            Scanner sizeScanner = new Scanner(file);
            Scanner relationScanner = new Scanner(file);

            while (sizeScanner.hasNextLine()) {
                if (colCount == 0) {
                    String[] line = sizeScanner.nextLine().replaceAll("\\s", "").split("");
                    colCount = line.length;
                } else {
                    sizeScanner.nextLine();
                }
                rowCount++;
            }

            // initialize level grid
            level = new Cell[rowCount][colCount];

            // update level values with relations in enum
            for (int i = 0; i < rowCount; i++) {
                String[] row = relationScanner.nextLine().replaceAll("\\s", "").split("");

                for (int j = 0; j < row.length; j++) {
                    level[i][j] = Relation.get(row[j]);

                    switch (level[i][j]) {
                        case GHOST_1 -> initialGhost1Position = new Point2D(j, i);
                        case GHOST_2 -> initialGhost2Position = new Point2D(j, i);
                        case DOT -> dotsCount++;
                        case PACMAN -> initialPacmanPosition = new Point2D(j, i);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND: " + e.getMessage());
        }
    }

    // Getters

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public int getDotsCount() {
        return dotsCount;
    }

    public Point2D getInitialGhost1Position() {
        return initialGhost1Position;
    }

    public Point2D getInitialGhost2Position() {
        return initialGhost2Position;
    }

    public Point2D getInitialPacmanPosition() {
        return initialPacmanPosition;
    }

    public Cell[][] getLevel() {
        return level;
    }
}
