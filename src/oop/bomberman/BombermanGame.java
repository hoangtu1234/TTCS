package oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.Map.Map;


public class BombermanGame extends Application {

    public static int WIDTH = 20;
    public static int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean up, down, right, left, space;
    public static Map map;
    public static int State = 0;
    public boolean pause;
    public boolean replay;
    public boolean start;
    public int checkAlive = 1;
    public int startnew = 0;
    public static boolean auto = false;
    public static void main(String[] args) {
//        Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\soundtrack.wav");
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        checkAlive = 1;
        start = false;
        map = new Map(3);
        map.createMap();
        replay = false;
        HEIGHT = map.getHeight();
        WIDTH = map.getWidth();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT + 30 );
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                //vong lap cua game day
                //input
                scene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case UP: up = true;break;
                        case DOWN: down = true;break;
                        case LEFT: left = true;break;
                        case RIGHT: right = true;break;
                        case SPACE: space = true;break;
                        case ESCAPE: State = 2;break;
                        case ENTER: State = 1;break;
                        case R: replay = true; break;
                        case F : start = true; break;
                        case A : {
                            if (auto) {
                                auto = false;
                            }
                            else {
                                auto = true;
                            }
                            break;
                        }
                    }
                });
                scene.setOnKeyReleased(event -> {
                    switch (event.getCode()) {
                        case UP: up = false;break;
                        case DOWN: down = false;break;
                        case LEFT: left = false;break;
                        case RIGHT: right = false;break;
                        case SPACE: space = false;break;
                        case R : replay = false; break;
                        case F : start = false; break;
                    }
                });
                // ve ra man hinh
                switch (State) {
                    case 0:
                        StartGame();
                        if (start) {
                            State = 1;
                        }
                        break;
                    case 1:
                        update();
                        render();
                        break;
                    case 2 :
                        pauseGame();
                        break;
                    case 3 :
                        Game_Over();
                        if (replay) {
                            map.replayGame();
                            replay = false;
                            State = 1;
                        }
                        break;
                    case 4:
                        Game_Win();
                        if (replay) {
                            map.replayGame();
                            replay = false;
                            State = 1;
                        }
                        break;

                }

            }
        };

        timer.start();
        createMap();
    }

    public void StartGame() {
        gc.clearRect(0, 0, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc.drawImage(new Image("/textures/startgame.jpg"), 0, 0, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc.setFont(new Font("Family",25));
        gc.fillText("nhấn F để vào game ", 370, 400);
        gc.setFill(Color.GREEN);
    }

    public void pauseGame() {
        gc.clearRect(0,0,Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
        gc.drawImage(new Image("/textures/1686792-pause_menu.jpg"),0,0, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc.setFont(new Font("Family",25));
        gc.fillText("nhấn Enter để chơi tiếp ", 300, 400);
        gc.setFill(Color.GREEN);
    };
    public void Game_Over() {
        gc.clearRect(0,0,Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
//        gc.drawImage(new Image("textures/1686792-pause_menu.jpg"),0,0);
        gc.drawImage(new Image("/textures/GameOver.jpg"), 0, 0, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc.setFont(new Font("Family",25));
        gc.fillText("nhấn R để chơi lai ", 400, 400);
        gc.setFill(Color.GREEN);
    }

    public void Game_Win() {
        gc.clearRect(0,0,Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
//        gc.drawImage(new Image("textures/1686792-pause_menu.jpg"),0,0);
        gc.drawImage(new Image("/textures/wingame.jpg"), 0, 0, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc.setFont(new Font("Family",25));
        gc.fillText("nhấn R để chơi lai ", 400, 400);
        gc.setFill(Color.GREEN);
    }

    public void createMap() {
        WIDTH = map.getWidth();
        HEIGHT = map.getHeight();
    }



    public void update() {
        map.update();
    }

    public void render() {

        map.render(gc, canvas);
    }
}