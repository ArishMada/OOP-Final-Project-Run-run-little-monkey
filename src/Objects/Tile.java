package Objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage sprite;
    private int id;
    private int type;

    public Tile(BufferedImage sprite, int id, int type) {
        this.sprite = sprite;
        this.id = id;
        this.type = type;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }
}
