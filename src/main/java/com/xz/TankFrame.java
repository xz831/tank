package com.xz;

import com.xz.collider.ColliderChain;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static com.xz.Config.*;
import static com.xz.Constant.GAME_OVER;
import static com.xz.Constant.SCORE_STRING;

/**
 * @Package: com.xz
 * @ClassName: TankFrame
 * @Author: xz
 * @Date: 2020/6/25 13:55
 * @Version: 1.0
 */
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();

    private Player tank;

    private List<AbstractGameObject> abstractGameObjectList;

    private Image offScreenImage;

    public int score;

    public boolean gameOver;

    private ColliderChain colliderChain;

    private TankFrame() throws HeadlessException {
        super(gameName);
        init();
    }

    private void init() {
        initBaseParam();
        initColliderChain();
        initPlayer();
        initWall();
        initEnemy();
        initKeyListener();
    }

    private void initWall() {
        Wall wall = new Wall(500,300,200,50);
        addGameObject(wall);
    }

    private void initBaseParam(){
        setLocation(locationX, locationY);
        setSize(gameWidth, gameHeight);
        setVisible(true);
        abstractGameObjectList = new ArrayList<>();
        score = 0;
    }

    private void initKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                tank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                tank.keyReleased(e);
            }
        });
    }

    private void initColliderChain() {
        colliderChain = new ColliderChain();
    }

    private void initEnemy() {
        addGameObjects(Enemy.getEnemyList(enemyTankCount));
    }

    private void initPlayer() {
        tank = new Player(playerX, playerY,fireStrategy);
        addGameObject(tank);
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(gameWidth, gameHeight);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, gameWidth, gameHeight);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
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
        abstractGameObjectList.removeIf(item->!item.isLiving());
        for (int i = 0; i < abstractGameObjectList.size(); i++) {
            abstractGameObjectList.get(i).paint(g);
        }
        g.setColor(Color.white);
        g.setFont(new Font(null, Font.BOLD, 36));
        g.drawString(SCORE_STRING + score, gameWidth - 250, 75);
    }

    public void addGameObject(AbstractGameObject abstractGameObject){
        abstractGameObjectList.add(abstractGameObject);
    }

    public void addGameObjects(List<AbstractGameObject> abstractGameObject){
        abstractGameObjectList.addAll(abstractGameObject);
    }
}
