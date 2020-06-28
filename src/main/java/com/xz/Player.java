package com.xz;

import com.xz.strategy.fire.FireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.xz.Config.playerTankFireSpeedPerSecond;
import static com.xz.Config.playerTankMoveSpeed;

/**
 * @Package: com.xz
 * @ClassName: Tank
 * @Author: xz
 * @Date: 2020/6/25 14:54
 * @Version: 1.0
 */
public class Player extends BaseTank {

    private final Random random = new Random();
    private int x, y;
    private Dir dir;
    private boolean bL, bR, bU, bD;
    private List<Dir> dirs = new ArrayList<>(5);
    private Dir lastDir;
    private boolean moving = false;
    private final Group group = Group.GOOD;
    private Long lastFireTimeStamp;
    private boolean living = true;
    private FireStrategy fireStrategy;

    public Player(int x, int y,FireStrategy fireStrategy) {
        this.x = x;
        this.y = y;
        lastDir = randomDir();
        dir = lastDir;
        this.fireStrategy = fireStrategy;
    }

    @Override
    public void paint(Graphics g) {
//        g.fillRect(x, y, WIDTH, HEIGHT);
//        paintBarrel(g);
        if (!living) {
            return;
        }
        Dir dir;
        if (dirs.isEmpty()) {
            dir = lastDir;
        } else {
            lastDir = dir = dirs.get(0);
        }
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
                fire(fireStrategy);
                break;
            default:
                break;
        }
        setDir();
    }

    private void fire(FireStrategy fireStrategy) {
        fireStrategy.fire(this);
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
                x -= playerTankMoveSpeed;
                break;
            case D:
                y += playerTankMoveSpeed;
                break;
            case R:
                x += playerTankMoveSpeed;
                break;
            case U:
                y -= playerTankMoveSpeed;
                break;
            case LD:
                x -= playerTankMoveSpeed;
                y += playerTankMoveSpeed;
                break;
            case LU:
                x -= playerTankMoveSpeed;
                y -= playerTankMoveSpeed;
                break;
            case RD:
                x += playerTankMoveSpeed;
                y += playerTankMoveSpeed;
                break;
            case RU:
                x += playerTankMoveSpeed;
                y -= playerTankMoveSpeed;
                break;
            default:
                break;
        }
//        Sound.moveSound();
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

    @Override
    public void die() {
        living = false;
        TankFrame.INSTANCE.addExplode(new Explode(x, y));
    }

    @Override
    public boolean isLiving() {
        return living;
    }

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public Long getLastFireTimeStamp() {
        return lastFireTimeStamp;
    }

    public void setLastFireTimeStamp(Long lastFireTimeStamp) {
        this.lastFireTimeStamp = lastFireTimeStamp;
    }
}
