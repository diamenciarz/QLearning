package objects;

import java.awt.Color;

public class Tile {

    public Tile(TileType type) {
        this.type = type;
    }

    public enum TileType {
        NORMAL,
        FIRE,
        WIN
    }

    public TileType type;

    public int getReward() {
        switch (type) {
            case NORMAL:
                return 0;
            case FIRE:
                return -100;
            default:
                return 1;
        }
    }

    public Color getTileColor() {
        switch (type) {
            case NORMAL:
                // Gray
                return new Color(216, 191, 216);
            // Green
            case WIN:
                return new Color(152, 251, 152);
            // Red
            default:
                return new Color(255, 69, 0);
        }
    }

    @Override
    public String toString() {
        switch (type) {
            case NORMAL:
                return "N";
            case FIRE:
                return "F";
            default:
                return "W";
        }
    }

}