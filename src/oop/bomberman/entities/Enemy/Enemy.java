package oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import oop.bomberman.entities.Character.Bomber;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.BombermanGame;
import oop.bomberman.entities.Entity;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int animate;
    protected int speed;
    protected boolean alive;
    protected int step;
    protected int direction;
    protected int afterKill;
    protected int point;

    protected boolean die;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        animate = 0;
        step = 160;
        direction = 0;
        speed = Sprite.SCALED_SIZE / 32;
        afterKill = 100;
        width = Sprite.SCALED_SIZE - 10;
        height = Sprite.SCALED_SIZE - 10;
        die = false;
        point = 10;
    }

    public Enemy(int x, int y, Image img, int animate) {
        super(x, y, img);
        this.animate = animate;
    }


    public void animate() {
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
    }


    public void update() {
        animate();
        if (alive) {
            if ( step <= 0 ) {
                step = 160;
                Random rand = new Random();
                direction = rand.nextInt(4);
            }
            else {
                step --;
                move();
            }
        }
        else {
            killed();
        }
    }

    private void move() {
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case 0:
                dy -= speed;
                break;
            case 1:
                dx += speed;
                break;
            case 2:
                dy += speed;
                break;
            case 3:
                dx -= speed;
                break;
        }

        chooseSprite();

        if (canMove(dx, dy)) {
            x += dx;
            y += dy;
        }
        else {
            Random rand = new Random();
            direction = rand.nextInt(4);
            step = 96;
        }


    }

    public abstract void chooseSprite();


    public boolean canMove(int _x, int _y) {
        int dx = x + _x;
        int dy = y + _y;
        int x1 = myround(dx);
        int x2 = myround2(dx);
        int y1 = myround(dy);
        int y2 = myround3(dy);
        Entity o1 = BombermanGame.map.getObject(x1, y1);
        Entity o2 = BombermanGame.map.getObject(x1, y2);
        Entity o3 = BombermanGame.map.getObject(x2, y1);
        Entity o4 = BombermanGame.map.getObject(x2, y2);
        Entity b1 = BombermanGame.map.getEntity(x1, y1);
        Entity b2 = BombermanGame.map.getEntity(x1, y2);
        Entity b3 = BombermanGame.map.getEntity(x2, y1);
        Entity b4 = BombermanGame.map.getEntity(x2, y2);
        if (b1 != null) {
            if (b1.collide(this)) {
                return false;
            }
        }
        if (b2 != null) {
            if (b2.collide(this)) {
                return false;
            }
        }
        if (b3 != null) {
            if (b3.collide(this)) {
                return false;
            }
        }
        if (b4 != null) {
            if (b4.collide(this)) {
                return false;
            }
        }

        if (o1 != null) {
            if (o1.collide(this)) {
                return false;
            }
        }
        if (o2 != null) {
            if (o2.collide(this)) {
                return false;
            }
        }
        if (o3 != null) {
            if (o3.collide(this)) {
                return false;
            }
        }
        if (o4 != null) {
            if (o4.collide(this)) {
                return false;
            }
        }

        return true;
    }

    /** Góc trái trên. */
    private int myround(double x) {
        return (int) ((x + 2) / Sprite.SCALED_SIZE );
    }

    /** Góc phải trên. */
    private int myround2(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 2 ) / Sprite.SCALED_SIZE);
    }

    /** Trục bên dưới. */
    private int myround3(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 2) / Sprite.SCALED_SIZE);
    }

    public void killed() {

    }



    public boolean collide(Entity a) {
        if ( a instanceof Bomber) {
            ((Bomber) a).setAlive();

       if (a instanceof Enemy) {
           return true;
        }
        }
            return false;
    }

}
