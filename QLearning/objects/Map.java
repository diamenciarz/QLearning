package objects;

import entities.Actor;
import objects.Tile.TileType;
import utils.Dimensions;

public class Map {

    public Dimensions dimensions;
    public Tile[][] tiles;
    public Actor[] actors;

    public Map(Dimensions dim) {
        this.dimensions = dim;
        tiles = new Tile[dimensions.y][dimensions.x];

        fillEmptyMap();
    }

    private void fillEmptyMap() {
        for (int y = 0; y < dimensions.y; y++) {
            for (int x = 0; x < dimensions.x; x++) {
                tiles[y][x] = new Tile(TileType.NORMAL);
            }
        }
    }

    public void setMapTile(Dimensions dim, TileType type) {
        tiles[dim.y][dim.x] = new Tile(type);
    }

    @Override
    public String toString() {
        String mapString = "";
        for (int y = 0; y < dimensions.y; y++) {
            for (int x = 0; x < dimensions.x; x++) {
                mapString += tiles[y][x];
            }
            mapString += "\n";
        }

        return mapString;
    }
}
