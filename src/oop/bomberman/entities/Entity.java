package oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected int width;
    protected int height;

    protected int point;

    protected Image img;
    protected boolean remove;
    protected boolean beDestroy;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        width = Sprite.SCALED_SIZE - 6;
        height = Sprite.SCALED_SIZE;
        remove = false;
        beDestroy = false;
        point = 10;
    }

    public Entity(int x, int y) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

    public int getX() {
        return x ;
    }

    public int getY() {
        return y;
    }

    public abstract boolean collide(Entity a);

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(x,y,width,height);
    }

    public boolean intersects(Entity s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    public int getPoint() {
        return this.point;
    }
}
