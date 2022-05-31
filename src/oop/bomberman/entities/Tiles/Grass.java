package oop.bomberman.entities.Tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.bomberman.entities.Entity;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public void render (GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    @Override
    public boolean collide(Entity a) {
        return false;
    }
}
