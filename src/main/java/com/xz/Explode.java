package com.xz;

import java.awt.*;

import static com.xz.ImageResource.bombImg;

/**
 * @Package: com.xz
 * @ClassName: Explode
 * @Author: xz
 * @Date: 2020/6/28 11:43
 * @Version: 1.0
 */
public class Explode {

    private final int x, y;

    private boolean living = true;

    private int step;

    public void paint(Graphics graphics) {
        graphics.drawImage(bombImg[step++], x, y, null);
        if (step >= bombImg.length) {
            living = false;
        }
    }

    public boolean isLiving() {
        return living;
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        Sound.explodeSound();
    }
}
