package Handling;

import Objects.Tile;
import helperMethods.ImageChange;
import helperMethods.loadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.constant.Tiles.*;

public class TileHandling {
    public Tile Grass, Water, GroundU, GroundS, Sand, Rock, Grave, cornerRoadL, Lava, cornerRoadR;
    public Tile cornerRoadDR, cornerRoadDL, threeWays, twoWaysUR, twoWaysUL, twoWaysDR, twoWaysDL;
    public BufferedImage spriteBeach, spriteGround, spriteProps;
    public ArrayList<Tile> tiles = new ArrayList<>();
    public ArrayList<Tile> roads = new ArrayList<>();
    public ArrayList<Tile> corners = new ArrayList<>();
    public ArrayList<Tile> twoWays = new ArrayList<>();


    public TileHandling() {
        loadImg();
        createTiles();
    }

    private void createTiles() {
        // we can consider the sprite as a grid with each block of 32 represented by x and y coordinates
        int id = 0;
        //texture
        tiles.add(Grass = new Tile(getSprite(spriteBeach, 4, 2), id++, GRASS));
        tiles.add(Water = new Tile(getSprite(spriteBeach, 1, 2), id++, WATER));
        tiles.add(Sand = new Tile(getSprite(spriteBeach, 0, 2), id++, SAND));
        tiles.add(Lava = new Tile(getSprite(spriteGround, 1, 8), id++, LAVA));

        //props
        tiles.add(Rock = new Tile(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteProps, 4, 2, 4, 15)), id++, ROCK));
        tiles.add(Grave = new Tile(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteProps, 4, 2, 9, 8)), id++, GRAVE));

        //roads
        tiles.add(threeWays = new Tile(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 6, 7)), id++, ALL));

        roads.add(GroundS = new Tile(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 5, 8)), id++, ROAD_HORIZONTAL));
        roads.add(GroundU = new Tile(ImageChange.getRotatedImage(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 5, 8)), 90), id++, ROAD_VERTICAL));

        corners.add(cornerRoadL = new Tile(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 4, 8)), id++, RIGHT_DOWN_CORNER));
        corners.add(cornerRoadR = new Tile(ImageChange.getRotatedImage(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 4, 8)), 90), id++, LEFT_DOWN_CORNER));
        corners.add(cornerRoadDR = new Tile(ImageChange.getRotatedImage(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 4, 8)), 180), id++, LEFT_UP_CORNER));
        corners.add(cornerRoadDL = new Tile(ImageChange.getRotatedImage(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 4, 8)), 270), id++, RIGHT_UP_CORNER));

        twoWays.add(twoWaysUR = new Tile(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 7, 8)), id++, LEFT_UP_RIGHT));
        twoWays.add(twoWaysDR = new Tile(ImageChange.getRotatedImage(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 7, 8)), 90), id++, RIGHT_UP_DOWN));
        twoWays.add(twoWaysDL = new Tile(ImageChange.getRotatedImage(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 7, 8)), 180), id++, LEFT_DOWN_RIGHT));
        twoWays.add(twoWaysUL = new Tile(ImageChange.getRotatedImage(ImageChange.makeProps(getImgsToBeMixed(spriteBeach, spriteGround, 4, 2, 7, 8)), 270), id++, LEFT_UP_DOWN));


        tiles.addAll(roads);
        tiles.addAll(corners);
        tiles.addAll(twoWays);
    }

    private BufferedImage[] getImgsToBeMixed(BufferedImage sprt1, BufferedImage sprt2, int x1,int y1,int x2,int y2) {
        return new BufferedImage[] {getSprite(sprt1, x1, y1), getSprite(sprt2, x2, y2)};
    }

    private void loadImg(){
        spriteBeach = loadSave.getSpriteBeach();
        spriteGround = loadSave.getSpriteGround();
        spriteProps = loadSave.getSpriteProps();
    }

    private BufferedImage getSprite(BufferedImage sprites, int x, int y){
        return sprites.getSubimage(x*32, y*32, 32, 32);
    }
    public Tile getTile(int id){
        return tiles.get(id);
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    public ArrayList<Tile> getRoads() {
        return roads;
    }

    public ArrayList<Tile> getCorners() {
        return corners;
    }

    public ArrayList<Tile> getTwoWays() {
        return twoWays;
    }
}
