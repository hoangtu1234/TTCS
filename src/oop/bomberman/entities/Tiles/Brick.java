package oop.bomberman.entities.Tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.bomberman.entities.Character.Bomber;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.entities.Enemy.Ballon2;
import oop.bomberman.entities.Enemy.Enemy;
import oop.bomberman.entities.Entity;

public class Brick extends Entity {

    private int animate;
    public boolean broken;
    private int timecount;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        animate = 0;
        broken = false;
        timecount = 20;
        remove = false;
        beDestroy = false;
    }

    @Override
    public void update() {
        int dx = (int) x / Sprite.SCALED_SIZE;
        int dy = (int) y / Sprite.SCALED_SIZE;
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
        if (beDestroy) {
            timecount --;

            img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 20).getFxImage();
            if (timecount < 0) {
                remove = true;
            }
        }
    }

    public void render (GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public void explode() {

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Ballon2) {
            return false;
        }
        if ( a instanceof Bomber || a instanceof Enemy) {
            return true;
        }
        return false;
    }

}
