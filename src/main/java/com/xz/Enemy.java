package com.xz;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.xz.Config.*;
import static com.xz.TankFrame.*;

/**
 * @Package: com.xz
 * @ClassName: Tank
 * @Author: xz
 * @Date: 2020/6/25 14:54
 * @Version: 1.0
 */
public class Enemy extends BaseTank {

    private final Random random = new Random();
    private int x = randomCoordinate(gameWidth), y = randomCoordinate(gameHeight);
    /**
     * 每分钟多少发
     */
    private Dir dir = randomDir();
    private boolean moving = true;
    private final Group group = Group.BAD;
    private Long lastFireTimeStamp = System.currentTimeMillis() + random.nextInt(3000);
    private boolean living = true;
    private Long nextDirTimeStamp;

    private Enemy() {
    }

    public static List<Enemy> getEnemyList(int size) {
        List<Enemy> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Enemy());
        }
        return list;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            return;
        }
        switch (dir) {
            case D:
                g.drawImage(ImageResource.badTankD, x, y, null);
                break;
            case L:
                g.drawImage(ImageResource.badTankL, x, y, null);
                break;
            case R:
                g.drawImage(ImageResource.badTankR, x, y, null);
                break;
            case U:
                g.drawImage(ImageResource.badTankU, x, y, null);
                break;
            default:
                break;
        }
        if (moving) {
            move();
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    private void fire() {
        if (/*lastFireTimeStamp == null||*/ lastFireTimeStamp + 60 * 1000 / enemyTankFireSpeedPerSecond <= System.currentTimeMillis()) {
            Bullet bullet;
            switch (dir) {
                case L:
                    bullet = new Bullet(x - 20, y + 15, dir, group);
                    break;
                case D:
                    bullet = new Bullet(x + 15, y + 55, dir, group);
                    break;
                case R:
                    bullet = new Bullet(x + 50, y + 15, dir, group);
                    break;
                case U:
                    bullet = new Bullet(x + 15, y - 20, dir, group);
                    break;
                default:
                    return;
            }
            TankFrame.INSTANCE.addBullet(bullet);
            lastFireTimeStamp = System.currentTimeMillis() + random.nextInt(2000);
            Sound.fireSound();
        }
    }


    private void move() {
        switch (dir) {
            case L:
                x -= enemyTankMoveSpeed;
                break;
            case D:
                y += enemyTankMoveSpeed;
                break;
            case R:
                x += enemyTankMoveSpeed;
                break;
            case U:
                y -= enemyTankMoveSpeed;
                break;
            default:
                break;
        }
        fire();
        Dir dir = boundsCheck();
        if (nextDirTimeStamp == null || nextDirTimeStamp <= System.currentTimeMillis() || dir != null) {
            do {
                this.dir = randomDir();
            } while (this.dir == dir);
            nextDirTimeStamp = System.currentTimeMillis() + new Random().nextInt(500) + 500;
        }
    }

    private Dir boundsCheck() {
        if (x < 30) {
            return Dir.L;
        } else if (x > gameWidth - 70) {
            return Dir.R;
        } else if (y < 30) {
            return Dir.U;
        } else if (y > gameHeight - 70) {
            return Dir.D;
        }
        return null;
    }

    private Dir randomDir() {
        int i = random.nextInt(4);
        switch (i) {
            case 0:
                return Dir.L;
            case 1:
                return Dir.D;
            case 2:
                return Dir.R;
            case 3:
                return Dir.U;
            default:
                return null;
        }
    }

    private int randomCoordinate(int max) {
        return random.nextInt(max - 50) + 50;
    }

    @Override
    public void die() {
        living = false;
        INSTANCE.addExplode(new Explode(x, y));
    }

    @Override
    public boolean isLiving() {
        return living;
    }

    public boolean collidesWithTanks(List<BaseTank> enemy) {
        enemy.removeIf(this::equals);
        for (BaseTank tank : enemy) {
            if (tank.isLiving()) {
                Rectangle rect = new Rectangle(x, y, ImageResource.bulletU.getWidth(), ImageResource.bulletU.getHeight());
                Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), ImageResource.badTankU.getWidth(), ImageResource.badTankU.getHeight());
                if (rect.intersects(rectTank)) {
                    return true;
                }
            }
        }
        return false;
    }
}
