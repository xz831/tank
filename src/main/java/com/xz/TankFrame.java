package com.xz;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
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

    private List<Enemy> enemy;

    private List<Bullet> bullets;

    private List<Explode> explodes;

    private Image offScreenImage;

    public static int SCORE;

    private static boolean gameOver;

    private TankFrame() throws HeadlessException {
        super(gameName);
        init();
    }

    private void init() {
        setLocation(locationX, locationY);
        setSize(gameWidth, gameHeight);
        setVisible(true);
        tank = new Player(playerX, playerY,fireStrategy);
        enemy = Enemy.getEnemyList(enemyTankCount);
        explodes = new ArrayList<>();
        bullets = new ArrayList<>();
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
        SCORE = 0;
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
        tank.paint(g);
        enemy.removeIf(item -> !item.isLiving());
        enemy.forEach(item -> item.paint(g));
        explodes.removeIf(item -> !item.isLiving());
        explodes.forEach(item -> item.paint(g));
        if (!bullets.isEmpty()) {
            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Bullet next = iterator.next();
                if (next.getGroup().equals(Group.GOOD)) {
                    if (next.collidesWithTanks(enemy)) {
                        SCORE++;
                    }
                } else {
                    if (next.collidesWithTank(tank)) {
                        gameOver = true;
                    }
                }
                if (!next.isLiving()) {
                    iterator.remove();
                } else {
                    next.paint(g);
                }
            }
        }
        g.setColor(Color.white);
        g.setFont(new Font(null, Font.BOLD, 36));
        g.drawString(SCORE_STRING + SCORE, gameWidth - 250, 75);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void addExplode(Explode explode) {
        explodes.add(explode);
    }

    public List<BaseTank> getAllBaseTank() {
        List<BaseTank> list = new ArrayList<>(enemy);
        list.add(tank);
        return list;
    }
}
