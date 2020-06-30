package com.xz;

import com.xz.collider.ColliderUtil;

import java.awt.*;

import static com.xz.Config.*;

/**
 * @Package: com.xz
 * @ClassName: Bullet
 * @Author: xz
 * @Date: 2020/6/27 13:01
 * @Version: 1.0
 */
public class Bullet extends AbstractGameObject {
    private int x, y;
    private Dir dir;
    private final Group group;
    private boolean living = true;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        TankFrame.INSTANCE.getGameModel().addGameObject(this);
    }

    @Override
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
            case LD:
                g.drawImage(ImageResource.bulletDL, x, y, null);
                break;
            case LU:
                g.drawImage(ImageResource.bulletUL, x, y, null);
                break;
            case RD:
                g.drawImage(ImageResource.bulletDR, x, y, null);
                break;
            case RU:
                g.drawImage(ImageResource.bulletUR, x, y, null);
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
            case LD:
                x -= bulletSpeed;
                y += bulletSpeed;
                break;
            case LU:
                x -= bulletSpeed;
                y -= bulletSpeed;
                break;
            case RD:
                x += bulletSpeed;
                y += bulletSpeed;
                break;
            case RU:
                x += bulletSpeed;
                y -= bulletSpeed;
                break;
            default:
                break;
        }
        boundsCheck();
    }

    public void collidesWithTank(BaseTank tank) {
        if (isLiving() && tank.isLiving() && !group.equals(tank.getGroup())) {
            if (ColliderUtil.collideCheck(this, tank)) {
                if (tank.getGroup().equals(Group.GOOD)) {
                    if (!invincible) {
                        TankFrame.INSTANCE.getGameModel().gameOver = true;
                        die();
                        tank.die();
                    }
                } else {
                    TankFrame.INSTANCE.getGameModel().score++;
                    die();
                    tank.die();
                }
            }
        }
    }

    private void boundsCheck() {
        if (x < 0 || x > gameWidth || y < 20 || y > gameHeight) {
            living = false;
        }
    }

    @Override
    public boolean isLiving() {
        return living;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return ImageResource.bulletU.getWidth();
    }

    @Override
    public int getHeight() {
        return ImageResource.bulletU.getHeight();
    }

    public void die() {
        living = false;
    }

    public Group getGroup() {
        return group;
    }
}
