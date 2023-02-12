package graphics;

//Java libs
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Actor;
//My libs
import objects.*;
import objects.Tile.TileType;
import utils.*;

public class MapVisualizer extends JFrame {

    private static MapVisualizer instance;
    private JPanel[][] tiles;

    public static void main(String[] args) {
        Map map = new Map(new Dimensions(3, 4));
        map.setMapTile(new Dimensions(1, 2), TileType.FIRE);
        map.setMapTile(new Dimensions(2, 1), TileType.FIRE);
        map.setMapTile(new Dimensions(0, 0), TileType.FIRE);
        map.setMapTile(new Dimensions(1, 3), TileType.WIN);

        map.actors = new Actor[2];
        map.actors[0] = new Actor(new Dimensions(1, 0));
        map.actors[1] = new Actor(new Dimensions(0, 0));

        System.out.println(map);

        displayMap(map);
    }

    public static void displayMap(Map map) {
        if (instance == null) {
            instance = new MapVisualizer();
        }
        instance.displayMapSingleton(map);
    }

    private void displayMapSingleton(Map map) {
        openWindow(map.dimensions);
        displayTiles(map);
        displayActors(map);

        this.repaint();
        this.setVisible(true);
    }

    private void openWindow(Dimensions mapDimensions) {
        this.setTitle("Map view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimensions frameSize = calculateFrameSize(mapDimensions);
        this.setSize(frameSize.x, frameSize.y);
        System.out.println("Window size: " + this.getSize().width + "," + this.getSize().height);
        this.setLayout(null);
        // (175, 238, 238) is Cyan
        this.getContentPane().setBackground(new Color(175, 238, 238));
    }

    private Dimensions calculateFrameSize(Dimensions mapDimensions) {
        final int MAX_FRAME_SIZE = 750;
        SquareSize square = calculateSquareSize(mapDimensions);
        if (mapDimensions.y > mapDimensions.x) {
            int windowWidth = MAX_FRAME_SIZE / mapDimensions.y * mapDimensions.x;
            return new Dimensions(windowWidth + square.width, MAX_FRAME_SIZE + square.height);
        } else {

            int windowHeight = MAX_FRAME_SIZE / mapDimensions.x * mapDimensions.y;
            return new Dimensions(MAX_FRAME_SIZE + square.width, windowHeight + square.height);
        }
    }

    private void displayTiles(Map map) {
        Dimensions dimensions = map.dimensions;
        SquareSize squareSize = calculateSquareSize(map.dimensions);
        System.out.println("Tile size: " + squareSize.width + "," + squareSize.height);
        tiles = new JPanel[dimensions.y][dimensions.x];

        for (int y = 0; y < dimensions.y; y++) {
            for (int x = 0; x < dimensions.x; x++) {
                Dimensions position = new Dimensions(x, y);
                Color tileColor = map.tiles[y][x].getTileColor();
                displaySquare(position, squareSize, tileColor);
            }
        }
    }

    private SquareSize calculateSquareSize(Dimensions mapDimensions) {
        final int MAX_FRAME_SIZE = 750;

        int squareWidth = MAX_FRAME_SIZE / mapDimensions.x;
        int squareHeight = MAX_FRAME_SIZE / mapDimensions.y;

        int smallerDimension = Math.min(squareHeight, squareWidth);
        return new SquareSize(smallerDimension, smallerDimension);
    }

    private void displaySquare(Dimensions position, SquareSize size, Color color) {
        JPanel square = new JPanel();
        square.setBackground(color);
        square.setLayout(null);

        int x = (int) (position.x * size.width);
        int y = (int) (position.y * size.height);
        square.setBounds(x, y, (int) size.width, (int) size.height);

        tiles[position.y][position.x] = square;
        this.add(square);
    }

    private void displayActors(Map map) {
        SquareSize squareSize = calculateSquareSize(map.dimensions);

        for (Actor actor : map.actors) {
            displayActor(actor.position, squareSize);
        }
    }

    private void displayActor(Dimensions position, SquareSize size) {
        int actorSize = 25;

        // double actorX = ((double) position.x + 0.5) * size.width - (actorSize / 2);
        // double actorY = ((double) position.y + 0.5) * size.height - (actorSize / 2);
        int actorX = (size.width / 2) - (actorSize / 2);
        int actorY = (size.height / 2) - (actorSize / 2);

        JPanel actor = createActor(actorX, actorY, actorSize, Color.BLACK);
        tiles[position.y][position.x].add(actor);
    }

    private JPanel createActor(double xPos, double yPos, int squareSize, Color color) {
        JPanel square = new JPanel();
        square.setBackground(color);

        int x = (int) xPos;
        int y = (int) yPos;
        square.setBounds(x, y, squareSize, squareSize);
        return square;
    }

    private class SquareSize {
        public SquareSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int width;
        public int height;
    }
}
