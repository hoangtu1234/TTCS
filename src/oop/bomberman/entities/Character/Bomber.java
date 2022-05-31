package oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.AI_f.State;
import oop.bomberman.BombermanGame;
import oop.bomberman.entities.Entity;

import java.util.ArrayList;

public class Bomber extends Entity {
    private int animate = 0;
    private int maxAnimate = 7500;
    private int direction;
    private int speed;
    private boolean moving;
    private boolean alive;
    private int timePower;
    private boolean checkSpeed;
    private int afterKill;
    public static int score = 0;
    private int step;
    private int stepAt = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        speed = Sprite.SCALED_SIZE / 16 ;
        alive = true;
        timePower = 200;
        afterKill = 50;
        score = 0;
        step = 32;
    }

    @Override
    public void update() {
        if (animate < maxAnimate) {
            animate ++;
        } else {
            animate = 0;
        }

        input();
        if (checkSpeed && timePower >= 0) {
            speed = 1 + Sprite.SCALED_SIZE / 16;
            timePower--;
            if (timePower < 0) {
                speed = Sprite.SCALED_SIZE / 16;
                timePower = 200;
            }
        }
        if (!alive) {
            killed();
        }
    }

    @Override
    public boolean collide(Entity a) {
        return false;
    }

    private void input() {
        int dx = 0, dy = 0;
        if (BombermanGame.up) {
            dy -= 1 * speed;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\SFX\\verticalmove.wav");
            moving = true;
        }
        if (BombermanGame.down) {
            dy = 1 * speed;
            moving = true;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\SFX\\verticalmove.wav");
        }
        if (BombermanGame.left) {
            dx = -1 * speed;
            moving = true;
//            Sound.play("D:\\DEV_FILE\\OS_Bomber\\res\\SFX\\horizonalmove.wav");
        }
        if (BombermanGame.right) {
            dx = 1* speed;
            moving = true;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\AA126_11.wav");
        }
        if  (BombermanGame.auto) {
            String s = ai_sequence();
            System.out.println(x + " " + y + " " + direction);
            System.out.println(s);
            move(s);
            chooseSprite(1);

        }
        if (!BombermanGame.auto) {
            stepAt = 0;
        }
        move(dx, dy);
        chooseSprite(direction);

//        if (!BombermanGame.up && !BombermanGame.down && !BombermanGame.right && !BombermanGame.left) {
//            move(0, 0);
//        }
    }

    public void move(char s) {
        switch (s) {
            case 'N':
                if (step >= 0) {
                    move(0, -speed);
                    step -= speed;
                }
                break;
            case 'E':
                if (step >= 0) {
                    move(speed, 0);
                    step -= speed;
                }
                break;
            case 'W':
                if (step >= 0) {
                    move(-speed, 0);
                    step -= speed;
                }
                break;
            case 'S':
                if (step >= 0) {
                    move(0,  speed);
                      step -= speed;
                }
                break;
        }
    }

    public String ai_sequence() {
        int t = x;
        int q = y;
        if ( x % 32 >= 1 && (direction == 3) ){
            t += x - (x % 32 );
        }
        int posX = (t) / 32;
        System.out.println(posX);
        int posY = (q) / 32;
        System.out.println(posY);
        String ans="";
        char initName = BombermanGame.map.getChar(posX, posY);
        State initState = new State(initName, posX, posY);
        ArrayList<State> openSet = new ArrayList();
        ArrayList<State> closeSet = new ArrayList();
        openSet.add(initState);
        int WIDTH = BombermanGame.map.getWidth();
        int HEIGHT= BombermanGame.map.getHeight();
        while(!openSet.isEmpty()){
            int indexofLowest_f_state = 0;
            for (int i = 0; i < openSet.size();i++){
                if(openSet.get(i).getF() < openSet.get(indexofLowest_f_state).getF()){
                    indexofLowest_f_state = i;
                }
            }
            State current_state = openSet.get(indexofLowest_f_state);
            if(current_state.getX() == WIDTH - 2 && current_state.getY() == HEIGHT - 2 ){
                State querry = current_state;
                while(querry.getParent()!=null){
                    ans+=querry.getDirection();
                    querry=querry.getParent();
                }
                StringBuilder temp_string = new StringBuilder(ans);
                ans = temp_string.reverse().toString();
               System.out.println(ans);
            }
            openSet.remove(indexofLowest_f_state);
            closeSet.add(current_state);
            ArrayList<State> children = current_state.getChildren();
            for(State a:children){
                if(!closeSet_include(a,closeSet)){
                    double value_g = current_state.getG() + 1;
                    double dis = distance(a);

                    if(openSet_include(a,openSet)!=null) {
                        State b = openSet_include(a,openSet);
                        if (value_g < b.getG()) {
                            b.setG(value_g);
                            b.setDirection(a.getDirection());
                            b.setParent(a.getParent());
                            b.setH(distance(b));
                            b.setF(b.getG()+b.getH());
                        }
                    }else{
                        a.setG(value_g);
                        openSet.add(a);
                        a.setParent(current_state);
                    }
                    a.setH(dis);
                    a.setF(dis + a.getG());//heuristic
                    a.setParent(current_state);
                }
            }


        }

        return ans;
    }
    public boolean closeSet_include(State a,ArrayList<State> closeSet){
        for(State x:closeSet){
            if(x.getX()==a.getX() && x.getY()==a.getY())return true;
        }
        return false;
    }
    public State openSet_include(State a,ArrayList<State> openSet){
        for(State x:openSet){
            if(x.getX()==a.getX() && x.getY()==a.getY())return x;
        }
        return null;
    }
    public double distance(State a){
        int w=BombermanGame.map.getWidth();
        int h=BombermanGame.map.getHeight();
        return Math.sqrt((w-a.getX())*(w-a.getX())+(h-a.getY())*(h-a.getY()));
    }


    public void move(String s) {
        if (stepAt < s.length()) {
            move(s.charAt(stepAt));
            if (step <= 0) {
                step = 32;
            }
        }

    }

    public void move(int dx, int dy) {

        if (dx == 0 && dy < 0) {
            direction = 0;//up
        }
        if (dx== 0 && dy > 0) {
            direction = 2;//down
        }
        if (dx > 0 && dy == 0) {
            direction = 1;//right
        }
        if (dx < 0 && dy == 0) {
            direction = 3;//left
        }
        if (dx == 0 && dy == 0) {
            moving = false;
        }

        if (canMove(dx, dy)) {
            x += dx;
            y += dy;
        }
        else {
            if ((x + dx) % 32 > 20 && (direction == 0 || direction == 2)) {
                int r = 32 - ((x + dx) % 32);
                x = x + dx + r;
            }

            if ((y + dy) % 32 > 20 && (direction == 1 || direction == 3)) {
                int r = 32 - ((y + dy) % 32);
                y = y + dy + r;
            }

            if ((x + dx) % 32 < 10 && (direction == 0 || direction == 2)) {
                int r = (x + dx) % 32;
                x = x + dx - r;
            }

            if ((y + dy) % 32 < 10 && (direction == 1 || direction == 3)) {
                int r = (y + dy) % 32;
                y = y + dy - r;
            }
        }
    }

    private void chooseSprite(int _direction) {
        if (alive) {
            switch (direction) {
                case 0:
                    img = Sprite.player_up.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20).getFxImage();
                    }
                    break;
                case 1:
                    img = Sprite.player_right.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();
                    }
                    break;
                case 2:
                    img = Sprite.player_down.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20).getFxImage();
                    }
                    break;
                case 3:
                    img = Sprite.player_left.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20).getFxImage();
                    }
                    break;
                default:
                    img = Sprite.player_down.getFxImage();
                    break;
            }
        }

    }

    public boolean canMove(int _x, int _y) {
        int dx = x + _x;
        int dy = y + _y;
        int x1 = myround(dx);
        int x2 = myround2(dx);
        int y1 = myround(dy);
        int y2 = myround3(dy);
        Entity o1 = BombermanGame.map.getObjectnotGrass(x1, y1);
        Entity o2 = BombermanGame.map.getObjectnotGrass(x1, y2);
        Entity o3 = BombermanGame.map.getObjectnotGrass(x2, y1);
        Entity o4 = BombermanGame.map.getObjectnotGrass(x2, y2);
        Entity b1 = BombermanGame.map.getEntity(x1, y1, this);
        Entity b2 = BombermanGame.map.getEntity(x1, y2, this);
        Entity b3 = BombermanGame.map.getEntity(x2, y1, this);
        Entity b4 = BombermanGame.map.getEntity(x2, y2, this);
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


    private int myround(double x) {
        return (int) (x / Sprite.SCALED_SIZE);
    }

    private int myround2(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 8) / Sprite.SCALED_SIZE);
    }

    private int myround3(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 2) / Sprite.SCALED_SIZE);
    }

    public void killed() {
        if (afterKill >= 0) {
            afterKill--;
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, animate, 20).getFxImage();
        } else {
            remove = true;
            BombermanGame.State = 3;
        }
    }
    public void setAlive() {
        alive = false ;
    }



}
