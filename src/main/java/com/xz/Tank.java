package com.xz;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.xz.ImageResource.bombImg;

/**
 * @Package: com.xz
 * @ClassName: Tank
 * @Author: xz
 * @Date: 2020/6/25 14:54
 * @Version: 1.0
 */
@Deprecated
public class Tank {

    private int x, y;
    private static final int SPEED = 5;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int BARREL_SHORT = 5;
    private static final int BARREL_LONG = 30;
    /**
     * 每分钟多少发
     */
    private static final int FIRE_SPEED = 300;
    private Dir dir;
    private boolean bL, bR, bU, bD;
    private List<Dir> dirs = new ArrayList<>(2);
    private Dir lastDir;
    private boolean moving = false;
    private final Group group;
    private Long lastFireTimeStamp;
    private boolean living = true;
    private int currentDieImage;

    public Tank(int x, int y, Group group) {
        this.x = x;
        this.y = y;
        this.group = group;
        lastDir = randomDir();
        dir = lastDir;
    }

    public void paint(Graphics g) {
//        g.fillRect(x, y, WIDTH, HEIGHT);
//        paintBarrel(g);
        if (!living) {
            if (currentDieImage < bombImg.length) {
                g.drawImage(bombImg[currentDieImage++], x, y, null);
            }
            return;
        }
        Dir dir;
        if (dirs.isEmpty()) {
            dir = lastDir;
        } else {
            lastDir = dir = dirs.get(0);
        }
        if (group.equals(Group.GOOD)) {
            switch (dir) {
                case D:
                    g.drawImage(ImageResource.goodTankD, x, y, null);
                    break;
                case L:
                    g.drawImage(ImageResource.goodTankL, x, y, null);
                    break;
                case R:
                    g.drawImage(ImageResource.goodTankR, x, y, null);
                    break;
                case U:
                    g.drawImage(ImageResource.goodTankU, x, y, null);
                    break;
                default:
                    break;
            }
        } else {
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
        }
        if (moving) {
            move();
        }
    }

    private void paintBarrel(Graphics g) {
        Dir dir;
        if (dirs.isEmpty()) {
            dir = lastDir;
        } else {
            lastDir = dir = dirs.get(0);
        }
        switch (dir) {
            case D:
                g.fillRect(x + WIDTH / 2 - BARREL_SHORT / 2, y + HEIGHT, BARREL_SHORT, BARREL_LONG);
                break;
            case L:
                g.fillRect(x - BARREL_LONG, y + HEIGHT / 2 - BARREL_SHORT / 2, BARREL_LONG, BARREL_SHORT);
                break;
            case R:
                g.fillRect(x + WIDTH, y + HEIGHT / 2 - BARREL_SHORT / 2, BARREL_LONG, BARREL_SHORT);
                break;
            case U:
                g.fillRect(x + WIDTH / 2 - BARREL_SHORT / 2, y - BARREL_LONG, BARREL_SHORT, BARREL_LONG);
                break;
            default:
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                setDirs(Dir.L);
                bL = true;
                break;
            case 's':
                setDirs(Dir.D);
                bD = true;
                break;
            case 'd':
                setDirs(Dir.R);
                bR = true;
                break;
            case 'w':
                setDirs(Dir.U);
                bU = true;
                break;
            case ' ':
                fire();
                break;
            default:
                break;
        }
        setDir();
    }

    private void fire() {
        if (lastFireTimeStamp == null || lastFireTimeStamp + 60 * 1000 / FIRE_SPEED <= System.currentTimeMillis()) {
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
            TankFrame.INSTANCE.addGameObject(bullet);
            lastFireTimeStamp = System.currentTimeMillis();
        }
    }

    private void setDirs(Dir dir) {
        if (dirs.size() < 3) {
            dirs.add(dir);
        }
    }

    private void setDir() {
        if (!bL && !bU && !bR && !bD) {
            moving = false;
            return;
        } else if (bL && !bU && !bR && !bD) {
            dir = Dir.L;
        } else if (!bL && bU && !bR && !bD) {
            dir = Dir.U;
        } else if (!bL && !bU && bR && !bD) {
            dir = Dir.R;
        } else if (!bL && !bU && !bR && bD) {
            dir = Dir.D;
        } else if (bL && bU && !bR && !bD) {
            dir = Dir.LU;
        } else if (bL && !bU && !bR && bD) {
            dir = Dir.LD;
        } else if (!bL && bU && bR && !bD) {
            dir = Dir.RU;
        } else if (!bL && !bU && bR && bD) {
            dir = Dir.RD;
        }
        moving = true;
    }

    private void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case LD:
                x -= SPEED;
                y += SPEED;
                break;
            case LU:
                x -= SPEED;
                y -= SPEED;
                break;
            case RD:
                x += SPEED;
                y += SPEED;
                break;
            case RU:
                x += SPEED;
                y -= SPEED;
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                releasedDirs(Dir.L);
                bL = false;
                break;
            case 's':
                releasedDirs(Dir.D);
                bD = false;
                break;
            case 'd':
                releasedDirs(Dir.R);
                bR = false;
                break;
            case 'w':
                releasedDirs(Dir.U);
                bU = false;
                break;
            default:
                break;
        }
        setDir();
    }

    private void releasedDirs(Dir dir) {
        dirs.removeIf(item -> item.equals(dir));
    }

    private Dir randomDir() {
        Random random = new Random();
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

    public void die() {
        living = false;
    }

    public boolean isLiving() {
        return living;
    }
}
