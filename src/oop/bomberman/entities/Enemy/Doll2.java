package oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

public class Doll2 extends Enemy {



    public Doll2(int x, int y, Image img) {
        super(x, y, img);
        direction = 0;
        afterKill = 50;
        alive = true;
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.doll_right2, Sprite.doll_right3, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.doll_left2, Sprite.doll_left3, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.doll_dead.getFxImage();
                break;
        }
    }

    @Override
    public void killed() {
        if (afterKill > 0) {
            afterKill--;
            img = Sprite.movingSprite(Sprite.doll_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        }
        else {
            remove = true;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\BOM_11_M.wav");
//            int dx = (int) x / Sprite.SCALED_SIZE;
//            int dy = (int) y / Sprite.SCALED_SIZE;
//            ArrayList<Entity> explodes = new ArrayList<>();
//            Entity top = BombermanGame.map.getEntity(dx, dy - 1);
//            Entity bot = BombermanGame.map.getEntity(dx, dy + 1);
//            Entity left = BombermanGame.map.getEntity(dx - 1, dy);
//            Entity right = BombermanGame.map.getEntity(dx + 1, dy);
//            Entity mid = BombermanGame.map.getEntity(dx, dy);
//            if (mid instanceof Brick) {
//                mid.setBeDestroy(true);
//            }
//            if (!(mid instanceof Brick) && BombermanGame.map.getObject(dx, dy) instanceof Grass) {
//                explodes.add(new Explode(dx, dy, 4));
//            }
//            if (top instanceof Brick) {
//                top.setBeDestroy(true);
//            }
//            if (!(top instanceof Brick) && BombermanGame.map.getObject(dx, dy - 1) instanceof Grass) {
//                explodes.add(new Explode(dx, dy - 1, 0));
//            }
//            if (left instanceof Brick) {
//                left.setBeDestroy(true);
//            }
//            if (!(left instanceof Brick) && BombermanGame.map.getObject(dx - 1, dy) instanceof Grass) {
//                explodes.add(new Explode(dx - 1, dy, 3));
//            }
//            if (right instanceof Brick) {
//                right.setBeDestroy(true);
//            }
//            if (!(right instanceof Brick) && BombermanGame.map.getObject(dx + 1, dy) instanceof Grass) {
//                explodes.add(new Explode(dx + 1, dy, 1));
//            }
//            if (bot instanceof Brick) {
//                bot.setBeDestroy(true);
//            }
//            if (!(bot instanceof Brick) && BombermanGame.map.getObject(dx, dy + 1) instanceof Grass) {
//                explodes.add(new Explode(dx, dy + 1, 2));
//            }
//
////            explodes.add(new Explode(dx, dy + 1, Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20).getFxImage()));
////            explodes.add(new Explode(dx + 1, dy, Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,Sprite.explosion_horizontal_right_last2, animate, 20).getFxImage()));
//
//
//            BombermanGame.map.addAllExplodes(explodes);
        }

    }

}

