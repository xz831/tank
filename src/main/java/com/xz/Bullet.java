package com.xz;

import java.awt.*;
import java.util.List;

import static com.xz.Config.*;

/**
 * @Package: com.xz
 * @ClassName: Bullet
 * @Author: xz
 * @Date: 2020/6/27 13:01
 * @Version: 1.0
 */
public class Bullet {
    private int x, y;
    private Dir dir;
    private final Group group;
    private boolean living = true;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case D:
                g.drawImage(ImageResource.bulletD, x, y, null);
                break;
            case L:
                g.drawImage(ImageResource.bulletL, x, y, null);
                break;
            case R:
                g.drawImage(ImageResource.bulletR, x, y, null);
                break;
            case U:
                g.drawImage(ImageResource.bulletU, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
            case L:
                x -= bulletSpeed;
                break;
            case D:
                y += bulletSpeed;
                break;
            case R:
                x += bulletSpeed;
                break;
            case U:
                y -= bulletSpeed;
                break;
            default:
                break;
        }
        boundsCheck();
    }

    public boolean collidesWithTank(BaseTank tank) {
        if (tank.isLiving()) {
            Rectangle rect = new Rectangle(x, y, ImageResource.bulletU.getWidth(), ImageResource.bulletU.getHeight());
            Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), ImageResource.badTankU.getWidth(), ImageResource.badTankU.getHeight());
            if (rect.intersects(rectTank)) {
                die();
                tank.die();
                return true;
            }
        }
        return false;
    }

    private void boundsCheck() {
        if (x < 0 || x > gameWidth || y < 20 || y > gameHeight) {
            living = false;
        }
    }

    public boolean isLiving() {
        return living;
    }

    private void die() {
        living = false;
    }

    public Group getGroup() {
        return group;
    }

    public boolean collidesWithTanks(List<Enemy> enemy) {
        for (Enemy tank : enemy) {
            if (tank.isLiving()) {
                Rectangle rect = new Rectangle(x, y, ImageResource.bulletU.getWidth(), ImageResource.bulletU.getHeight());
                Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), ImageResource.badTankU.getWidth(), ImageResource.badTankU.getHeight());
                if (rect.intersects(rectTank)) {
                    die();
                    tank.die();
                    return true;
                }
            }
        }
        return false;
    }
}
