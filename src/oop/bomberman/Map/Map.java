package oop.bomberman.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import oop.bomberman.BombermanGame;
import oop.bomberman.entities.Character.Bomber;
import oop.bomberman.entities.Enemy.*;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.Tiles.Brick;
import oop.bomberman.entities.Tiles.Grass;
import oop.bomberman.entities.Tiles.Portal;
import oop.bomberman.entities.Tiles.Wall;
import oop.bomberman.graphics.Sprite;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Map {

    private int level;
    private int height;
    private int width;
    private String [] _lineTiles;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> stillObjects = new ArrayList<>();

    public static ArrayList<Entity> explodes = new ArrayList<>();
    public static ArrayList<Entity> enemies = new ArrayList<>();

    public Map(int level) {
        this.level = level;
    }

    public void readMap() {
        String filename = "res/levels/Level" + level + ".txt";

        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            line = bufferedReader.readLine();
            StringTokenizer tokens = new StringTokenizer(line);

            level = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            width = Integer.parseInt(tokens.nextToken());

            _lineTiles = new String[height];

            for(int i = 0; i < height; ++i) {
                _lineTiles[i] = bufferedReader.readLine().substring(0, width);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }



    public void createMap() {
        readMap();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; ++j) {
                switch (_lineTiles[i].charAt(j)) {
                    case '#':
                        stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case 'x':
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        break;
                    case 's':
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        //stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                    case 'f':
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // stillObjects.add(new Flame(j, i, Sprite.powerup_flames.getFxImage()));
                        break;
                    case 'm':
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        //astillObjects.add(new MultiItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        break;
                    default:
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; ++j) {
                Entity enemy;
                switch (_lineTiles[i].charAt(j)) {
                    case '1':
                        enemy = new Ballon2(j, i, Sprite.balloom_left1.getFxImage());
                        //entities.add(new Ballon2(j, i, Sprite.balloom_left1.getFxImage()));
                        enemies.add(enemy);
                        break;
                    case '3':
                        enemy = new Doll2(j, i, Sprite.doll_right1.getFxImage());
                         //entities.add(new Doll2(j, i, Sprite.doll_right1.getFxImage()));
                        enemies.add(enemy);
                        break;
                    case '4':
                        enemy = new Kondoria2(j, i, Sprite.kondoria_right1.getFxImage());
                        //entities.add(new Kondoria2(j, i, Sprite.kondoria_right1.getFxImage()));
                        enemies.add(enemy);
                        break;
                    case '5':
                        enemy = new Oneal2(j, i, Sprite.oneal_right1.getFxImage());
                        enemies.add(enemy);
                        break;
                    case '6':
                        enemy = new Conma2(j, i, Sprite.conma_right1.getFxImage());
                        //entities.add(new Conma2(j, i, Sprite.conma_right1.getFxImage()));
                        enemies.add(enemy);
                        break;
                    case '7':
                        enemy = new Conlon2(j, i, Sprite.conlon_right1.getFxImage());
                        enemies.add(enemy);
                        break;
                    case '*':
                    case 's':
                    case 'x':
                    case 'f':
                    case 'm':
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;

                    case 'p':
                        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                        entities.add(bomberman);
                }

            }
        }
    }

   /* public Entity getExplode(int dx, int dy) {
        Entity a = null;
        for (int i = 0; i < explodes.size(); i++) {
            if (explodes.get(i).getX() == dx && explodes.get(i).getY() == dy) {
                return explodes.get(i);
            }
        }
        return a;
    }*/



    public void addAllEnemies(ArrayList<Entity> enmemii) {
        for (int i = 0; i < enmemii.size(); i++) {
            enemies.add(enmemii.get(i));
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public void remove(Entity a) {
        stillObjects.remove(a);
    }

    public Entity getEntity(int i, int j) {
        i *= Sprite.SCALED_SIZE;
        j *= Sprite.SCALED_SIZE;
        //if (getExplode(i, j) != null) {
           // return getExplode(i, j);
       // }

        for (int id = 0; id < entities.size(); id++) {
            if (entities.get(id).getX() == i && entities.get(id).getY() == j) {
                return entities.get(id);
            }
        }
        return null;
    }

    public Entity getEntity(int i, int j, Entity a) {
        i *= Sprite.SCALED_SIZE;
        j *= Sprite.SCALED_SIZE;
       // if (getExplode(i, j) != null) {
        //    return getExplode(i, j);
       // }
        Iterator<Entity> entityIterator = enemies.iterator();
        while (entityIterator.hasNext()) {
            Entity entity = entityIterator.next();
            if (!(entity instanceof Bomber) && a.intersects(entity)) {
                return entity;
            }
        }
        for (int id = 0; id < entities.size(); id++) {
            if (entities.get(id).getX() == i && entities.get(id).getY() == j) {
                return entities.get(id);
            }
        }
        return null;
    }

    public Entity getObject(int i, int j) {
        i *= Sprite.SCALED_SIZE;
        j *= Sprite.SCALED_SIZE;
        for (int id = 0; id < stillObjects.size(); id++) {
            if (stillObjects.get(id).getX() == i && stillObjects.get(id).getY() == j) {
                return stillObjects.get(id);
            }
        }
        return null;
    }

    public Entity getObjectnotGrass(int i, int j) {
        i *= Sprite.SCALED_SIZE;
        j *= Sprite.SCALED_SIZE;
        for (int id = 0; id < stillObjects.size(); id++) {
            if (stillObjects.get(id).getX() == i && stillObjects.get(id).getY() == j && !(stillObjects.get(id) instanceof Grass)) {
                if (stillObjects.get(id) instanceof Wall) {
                    return stillObjects.get(id);
                }
                return stillObjects.get(id);
            }
        }
        return null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void update() {
        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i).isRemove()) {
                stillObjects.remove(i);

            }
            else {
                stillObjects.get(i).update();
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemove()) {
                entities.remove(i);
            }
            else {
                entities.get(i).update();
            }

        }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isRemove()) {
                enemies.remove(i);
            }
            else {
                enemies.get(i).update();
            }

        }
    }


    public void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
//        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Family",  25));

        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).render(gc);
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(gc);
        }
        gc.fillText("level :" + String.valueOf(level), 10, canvas.getHeight() - 5);
        gc.fillText("score :" + String.valueOf(Bomber.score), 400, canvas.getHeight() - 5);


    }



    public void changeLevel() {
        if (level < 5) {
            level ++;
        } else {
            BombermanGame.State = 4;
        }
        entities = new ArrayList<>();
        stillObjects = new ArrayList<>();
        enemies = new ArrayList<>();
        createMap();
    }

    public void replayGame() {
        level = 3;
        enemies = new ArrayList<>();
        stillObjects = new ArrayList<>();
        entities = new ArrayList<>();
        BombermanGame.auto = false;
        createMap();
    }

    public Entity getEntity(int x1,int x2,int y1, int y2) {
        x1 *= Sprite.SCALED_SIZE;
        x2 *= Sprite.SCALED_SIZE;
        y1 *= Sprite.SCALED_SIZE;
        y2 *= Sprite.SCALED_SIZE;
        for (int id = 0; id < entities.size(); id++) {
            if ((entities.get(id).getX() >= x1 && entities.get(id).getX() <= x2) && entities.get(id).getY() >= y1 && entities.get(id).getY() <= y2 && !(entities.get(id) instanceof Bomber)) {
                return entities.get(id);
            }
        }
        return null;
    }

    public char getChar(int x, int y) {
        return _lineTiles[y].charAt(x);
    }


}
