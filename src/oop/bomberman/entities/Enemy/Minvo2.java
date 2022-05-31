package oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

public class Minvo2 extends Enemy {


    public Minvo2(int x, int y, Image img) {
        super(x, y, img);
        direction = 0;
        afterKill = 50;
        alive = true;
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.minvo_right2, Sprite.minvo_right3, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.minvo_left2, Sprite.minvo_left3, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.minvo_dead.getFxImage();
                break;
        }
    }

    @Override
    public void killed() {
        if (afterKill > 0) {
            afterKill--;
            img = Sprite.movingSprite(Sprite.minvo_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        }
        else {
            remove = true;
        }

    }

}

