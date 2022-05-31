package oop.bomberman.entities.Tiles;

import javafx.scene.image.Image;
import oop.bomberman.entities.Character.Bomber;
import oop.bomberman.BombermanGame;
import oop.bomberman.entities.Enemy.Enemy;
import oop.bomberman.entities.Entity;

public class Portal extends Entity {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber) {
            if (Bomber.score >= 10) {
                BombermanGame.map.changeLevel();
            }
            else {
                return true;
            }
        }
        if (a instanceof Enemy) {
            return true;
        }
        return false;
    }

}
