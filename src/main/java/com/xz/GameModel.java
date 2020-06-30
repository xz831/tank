package com.xz;

import com.xz.collider.ColliderChain;
import com.xz.collider.ColliderUtil;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.xz.Config.*;
import static com.xz.Constant.GAME_OVER;
import static com.xz.Constant.SCORE_STRING;

/**
 * @Package: com.xz
 * @ClassName: GameModel
 * @Author: xz
 * @Date: 2020/6/30 10:38
 * @Version: 1.0
 */
public class GameModel implements Serializable {

    private Player tank;

    private List<AbstractGameObject> abstractGameObjectList;

    public int score;

    public boolean gameOver;

    private ColliderChain colliderChain;

    public GameModel() {
        initBaseParam();
        initColliderChain();
        initPlayer();
        initWall();
        initEnemy();
    }

    public void paint(Graphics g){
        if (gameOver) {
            g.setColor(Color.white);
            g.setFont(new Font(null, Font.BOLD, 50));
            g.drawString(GAME_OVER, gameWidth / 2 - 150, gameHeight / 2);
            return;
        }
        //碰撞检测
        for (int i = 0; i < abstractGameObjectList.size(); i++) {
            for (int j = i+1; j < abstractGameObjectList.size() ; j++){
                colliderChain.collide(abstractGameObjectList.get(i),abstractGameObjectList.get(j));
            }
        }
        for (int i = 0; i < abstractGameObjectList.size(); i++) {
            if(abstractGameObjectList.get(i).isLiving()){
                abstractGameObjectList.get(i).paint(g);
            }else{
                abstractGameObjectList.remove(i);
            }
        }
        g.setColor(Color.white);
        g.setFont(new Font(null, Font.BOLD, 36));
        g.drawString(SCORE_STRING + score, gameWidth - 250, 75);
    }

    private void initBaseParam(){
        abstractGameObjectList = new ArrayList<>();
        score = 0;
    }

    private void initColliderChain() {
        colliderChain = new ColliderChain();
    }

    private void initEnemy() {
        AbstractGameObject abstractGameObject;
        for (int i = 0; i < enemyTankCount; i++) {
            do {
                abstractGameObject = new Enemy();
            }while (!checkPosition(abstractGameObject));
            addGameObject(abstractGameObject);
        }
    }

    private void initPlayer() {
        tank = new Player(playerX, playerY,fireStrategy);
        addGameObject(tank);
    }

    public void addGameObject(AbstractGameObject abstractGameObject){
        abstractGameObjectList.add(abstractGameObject);
    }

    public void addGameObjects(List<AbstractGameObject> abstractGameObject){
        abstractGameObjectList.addAll(abstractGameObject);
    }

    public boolean checkPosition(AbstractGameObject abstractGameObject){
        for (AbstractGameObject gameObject : abstractGameObjectList) {
            if(ColliderUtil.collideCheck(gameObject,abstractGameObject)){
                return false;
            }
        }
        return true;
    }

    private void initWall() {
        Wall wall = new Wall(500,300,200,50);
        if(checkPosition(wall)){
            addGameObject(wall);
        }
    }

    public Player getTank() {
        return tank;
    }
}
