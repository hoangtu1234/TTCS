package oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

public class Ballon2 extends Enemy {

    private int speed ;
    private int index;


    public Ballon2(int x, int y, Image img) {
        super(x, y, img);
        index = 0;
        direction = 0;
        afterKill = 50;
        alive = true;
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, animate, 20).getFxImage();

                break;
            case 1:
                img = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();

                break;
            case 2:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, animate, 20).getFxImage();

                break;
            case 3:
                img = Sprite.movingSprite(Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();

                break;
            default:
                img = Sprite.balloom_dead.getFxImage();
                break;
        }
    }

    @Override
    public void killed() {
        if (afterKill > 0) {
            afterKill--;
            img = Sprite.movingSprite(Sprite.balloom_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        }
        else {
            remove = true;
        }

    }


}

