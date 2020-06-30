package com.xz;

import java.awt.*;
import java.io.Serializable;

/**
 * @Package: com.xz
 * @ClassName: AbstractGameObject
 * @Author: xz
 * @Date: 2020/6/28 17:15
 * @Version: 1.0
 */
public abstract class AbstractGameObject implements Serializable {
    public abstract void paint(Graphics graphics);
    public abstract boolean isLiving();
    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();
}
