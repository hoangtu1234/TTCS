package oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.BombermanGame;
import oop.bomberman.entities.Entity;

import java.util.ArrayList;

public class Kondoria2 extends Enemy {

    private int index;
    private boolean duplex;


    public Kondoria2(int x, int y, Image img) {
        super(x, y, img);
        index = 0;
        direction = 0;
        afterKill = 50;
        alive = true;
        duplex = true;
        point = 5;
    }

    public Kondoria2(int x, int y, Image img, boolean duplex) {
        super(x, y, img);
        index = 0;
        direction = 0;
        afterKill = 50;
        alive = true;
        this.duplex = duplex;
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.kondoria_dead.getFxImage();
                break;
        }
    }

    @Override
    public void killed() {
        if (afterKill > 0) {
            afterKill--;
            img = Sprite.movingSprite(Sprite.kondoria_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        }
        else {
            if (duplex) {
                ArrayList<Entity> enemies = new ArrayList<>();
                Entity bot1 = new Kondoria2(29, 11, Sprite.kondoria_right1.getFxImage(), false);
                Entity bot2 = new Kondoria2(28, 11, Sprite.kondoria_left1.getFxImage(), false);
                enemies.add(bot1);
                enemies.add(bot2);
                BombermanGame.map.addAllEnemies(enemies);
            }
            remove = true;
        }

    }

    @Override
    public int getPoint() {
        return this.point;
    }
}

