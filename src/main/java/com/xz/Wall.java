package com.xz;

import java.awt.*;

/**
 * @Package: com.xz.strategy.fire
 * @ClassName: Wall
 * @Author: xz
 * @Date: 2020/6/29 15:00
 * @Version: 1.0
 */
public class Wall extends AbstractGameObject {

    private final int x, y, width, height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.gray);
        graphics.fillRect(x,y,width,height);
        graphics.setColor(color);
    }

    @Override
    public boolean isLiving() {
        return true;
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
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
