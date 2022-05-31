package oop.bomberman.entities.Tiles;

import javafx.scene.image.Image;
import oop.bomberman.entities.Character.Bomber;
import oop.bomberman.entities.Enemy.Enemy;
import oop.bomberman.entities.Entity;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }


    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber || a instanceof Enemy) {

            return true;
        }
        return false;
    }
}
