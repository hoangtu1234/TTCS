package oop.bomberman.AI_f;

import oop.bomberman.BombermanGame;

import java.util.ArrayList;

public class State {

    private char State_name;
    private int x;
    private int y;
    private double f = 0;
    private double g = 0;
    private double h = 0;
    private char direction;
    private State parent=null;
    private ArrayList<State> children;

    public State() {

    }

    public State(char State_name, int x, int y) {
        this.State_name = State_name;
        this.x = x;
        this.y = y;
    }

    public State(char State_name, int x, int y, char direction, State parent) {
        this.State_name = State_name;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.parent = parent;
    }

    public double getF(){
        return this.f;
    }
    public double getG(){
        return this.g;
    }
    public double getH(){
        return this.h;
    }
    public char getState_name() {
        return State_name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDirection() {
        return direction;
    }

    public State getParent() {
        return parent;
    }

    public ArrayList<State> getChildren() {
        int w = BombermanGame.map.getWidth();
        int h = BombermanGame.map.getHeight();
        ArrayList<State> child = new ArrayList<>();
        char N_name=')';
        char E_name=')';
        char S_name=')';
        char W_name=')';
        if(y>0){
             N_name = BombermanGame.map.getChar(x, y -1);
        }
        if(x< w-1){
             E_name = BombermanGame.map.getChar(x + 1, y);
        }
        if(y < h-1){
             S_name = BombermanGame.map.getChar(x, y + 1);
        }
        if(x > 0){
             W_name = BombermanGame.map.getChar(x - 1, y);
        }

        if (N_name == ' ') {
            State N_State = new State(' ', x, y - 1, 'N', this);
            child.add(N_State);
        }
        if (E_name == ' ') {
            State E_State = new State(' ', x + 1, y, 'E', this);
            child.add(E_State);
        }
        if (S_name == ' ') {
            State S_State = new State(' ', x, y + 1, 'S', this);
            child.add(S_State);
        }
        if (W_name == ' ') {
            State W_State = new State(' ', x - 1, y, 'W', this);
            child.add(W_State);
        }
        children = child;
        return children;
    }

    public void setState_name(char state_name) {
        State_name = state_name;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setG(double g){
        this.g=g;
    }
    public void setH(double h){
        this.h=h;
    }
    public void setF(double f){
        this.f=f;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public void setChildren(ArrayList<State> children) {
        this.children = children;
    }
}
